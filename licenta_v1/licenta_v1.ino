  #include <Wire.h>
  #include <Adafruit_PWMServoDriver.h>

  #include <Math.h>
  // i folosita ptr interpolare
  #include <Ramp.h>

  #define DEBUG

  #define DEBUG_PRINTL(x)  Serial.println(x)
  #define DEBUG_PRINT(x)  Serial.print(x)

  #define SERVOMIN 150
  #define SERVOMAX 600
  #include <SPI.h>
  #include <RF24.h>

  // CE = 7, CSN = 8    (  astia is pini conectati de la trans-reciever la arduino mega)
  RF24 radio(8, 53);
  const byte address[6] = "00001";

  
  struct JoystickData {
    int x;
    int y;
    bool button;
  };

  JoystickData receivedData;

  // ptr prima placa
  Adafruit_PWMServoDriver pwm = Adafruit_PWMServoDriver(0x40);
  // ptr a doua placa
  Adafruit_PWMServoDriver pwm2 = Adafruit_PWMServoDriver(0x41);

  
  // Constants
  const double J2L = 110; // 110 milimetri
  const double J3L = 130; // 130 milimetri

  const double Y_Rest =  110;
  const double Z_Rest = -130.0;

  const double J3_LegAngle = 27;

  // Joint Variables
  double J1Act = 0.0;
  double J2Act = 0.0;
  double J3Act = 0.0;

  // librarie ramp pentru interpolare 
  rampDouble J1Tar[6] = 0.0;
  rampDouble J2Tar[6] = 0.0;
  rampDouble J3Tar[6] = 0.0;

  // Command Variables
  bool started = false;
  bool ended = false;
  uint8_t commandStep = 0;


  const int pathlength = 7;
  bool robotShouldMoveForward = false;





  double stepPath[pathlength][3] = {
    {0.0,  20.0,  0.0},   // Initial stance position
    {0.0,  20.0, 35.0},   // Lift leg
    {25.0, 20.0, 35.0},  // Move forward (swing phase)
    {25.0, 20.0, 35.0},  // Lower leg
    {25.0, 20.0, 35.0},  // Push body forward (stance phase)
    {25.0, 20.0, 0.0},  // Return to center (halfway)
    {25.0, 20.0, 0.0},

  };


  double stepPathToGOUP[1][3] = {
    {0.0, 20.0 , 35.0},


  };

  const int finishpathlength = 1;


  // step path ptr a impinge robotul
  double stepPathFinish[finishpathlength][3] ={
    {0.0, 20.0, 0.0},    // Return to initial position
  };






  // move left side  ( neterminat)
  double stepPathleftside[pathlength][3] = {
    {0.0, 10.0 , 0.0},
    {0.0, 10.0, 50.0},
    {-25.0,10.0,50.0},
    {-25.0,10.0,50.0},
    {-25.0,10.0,50.0},
    {-25.0,10.0,0.0},
    {0.0,10.0,0.0},

  };

  // move , right side 
  double stepPathrightside[pathlength][3] ={
    {0.0, 20.0, 30.0},
    {0.0, 20.0, 50.0},
    {45.0, 20.0, 50.0},
    {45.0, 20.0, 30.0},
    {45.0, 20.0, 30.0},
    {45.0, 20.0, 30.0},
    {0.0, 20.0, 30.0}, 


  };







  int counter_movement = 0;

  const int groupA[] = {0,2,4};
  const int groupsizeA = sizeof(groupA) / sizeof(groupA[0]);
  const int groupB[] = {1,3,5};
  const int groupsizeB = sizeof(groupB) / sizeof(groupB[0]);



  // also orderuile channelurilor influenteaza cum se misca pentru ca 3 picioare ar trebui sa se miste si 3 sa stabilizeze si sa stea pe loc 
  const int channel[6][3] = {
    {0, 1, 2},    // Leg 0 → pwm2
    {0, 1, 2},    // Leg 1 → not used here
    {3, 4, 5},    // Leg 2 → pwm2
    {3, 4, 5},    // Leg 3 → not used here
    {8, 9, 10},   // Leg 4 → pwm2, now fixed
    {6, 7, 8}     // Leg 5 → not used here
  };




  struct LegStatus {
    double (*path)[3];   // Pointer to step path (array of [x, y, z])
    int pathLength;
    int stepIndex;
    unsigned long lastStepTime;
    bool isMoving;
    int stepsRemaining;
    int lastStepIndex = -1;
    


  };

  LegStatus legs[6];
  unsigned long lastStepTime = 0;
  // trebe modificat sa fie groupsizeA + groupsizeB  , dupa ce testez pe 3 picioare




  void setup() {
    
    #ifdef DEBUG
    Serial.begin(115200);
    #endif
    radio.begin();
    delay(300);
    radio.openReadingPipe(0,address);
    
    radio.setPALevel(RF24_PA_LOW);
    radio.setChannel(108);
    radio.startListening();


    pwm.begin();             
    pwm.setPWMFreq(50);      
    pwm2.begin();
    pwm2.setPWMFreq(50);



    // aici schimbam initializarea dupa ce fac miscarea normala la robot pe partea dreapta ca sa initalizeze si sa stea in picioare correct
    
    Serial.println(" Incepe initializare picioare ");
    //delay(850);
    


  


    
    Serial.println(" Incepe initializare picioare ");
    
    delay(350);
    
    // DRIVER DREAPTA 
    pwm.setPWM(0,0,map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm.setPWM(1,0,map(140, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm.setPWM(2, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    
    
    pwm.setPWM(3, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm.setPWM(4, 0, map(140, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm.setPWM(5, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    
    pwm.setPWM(6, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm.setPWM(7, 0, map(40, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm.setPWM(8, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    
    Serial.println(" finalizare initializare prima grupa pwm");
    delay(1000);
    


  
   //DRIVER STANGA
    Serial.println(" incepe initializare ultima grupa");
    pwm2.setPWM(0,0,map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm2.setPWM(1,0,map(40, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm2.setPWM(2, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(800);
     
    pwm2.setPWM(3, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm2.setPWM(4, 0, map(40, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    pwm2.setPWM(5, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(600);
    
    pwm2.setPWM(8, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
    delay(1000);
    pwm2.setPWM(9, 0, map(140, 0, 180, SERVOMIN, SERVOMAX));
    delay(1000);
    pwm2.setPWM(10, 0, map(90, 0, 180, SERVOMIN, SERVOMAX));
  
    Serial.println(" Final initializere ultima grupa  ");
    delay(1000);
    
    

      for (int i = 0; i < 6; i++) {
    
        InverseKinematic(i,stepPath[0][0],stepPath[0][1],stepPath[0][2]);
      }
      while (!isfinished(groupA, groupsizeA)|| !isfinished(groupB, groupsizeB)) {
        movegroupA();  
        movegroupB();
      }
    
    



    for (int i = 0; i < 6; i++) {
      legs[i].isMoving = false;  // initially stationary
    } 
    Serial.print(" asta da ");
    // tre sa initializez dupa initlegs() picioarele altfel o sa am startup ala ciudat
    delay(500);
    
  }

  void pushGroup(const int group[], int groupsize) {
  for (int i = 0; i < groupsize; i++) {
    int leg = group[i];

    
    if (!legs[leg].isMoving) {
      InverseKinematic(leg,stepPathFinish[0][0],stepPathFinish[0][1],stepPathFinish[0][2]);

      
    }
  }
}



void moveupGroup(const int group[], int groupsize){
  for (int i = 0; i < groupsize; i++) {
    int leg = group[i];

    
    if (!legs[leg].isMoving) {
      
      InverseKinematic(leg,stepPathToGOUP[0][0],stepPathToGOUP[0][1],stepPathToGOUP[0][2]);

      
    }
  }
}





  int stepindex = 0;
  //unsigned long lastStepTime = 0;
  const unsigned long stepDelay = 100;

  // varianta buna e comentata , varianta necomentata acuma e pentru testare a 3 picioare
  //const bool groupA[6] = {true , false , true , false ,true ,false};



  bool isfinished(int group[] , int groupsize) {

    for(int i = 0; i < groupsize;i++){
      if(legs[group[i]].isMoving){ return false;}

    }
    return true;

  }

  bool pushIssuedThisCycle = false;  // at top level, not inside the function
const int swingStepToTriggerPush = 1;  

void movegroupA() {
    for (int i = 0; i < groupsizeA; i++) {
        int leg = groupA[i];
        // Usual movement code (interpolation, etc.)
        if (legs[leg].isMoving) {
            J1Tar[leg].update();
            J2Tar[leg].update();
            J3Tar[leg].update();

            double j1Val = J1Tar[leg].getValue();
            double j2Val = J2Tar[leg].getValue();
            double j3Val = J3Tar[leg].getValue();

            // Only leg 0, 2, 4
            if (leg == 0 || leg == 2 || leg == 4) {
                pwm2.setPWM(channel[leg][0], 0, map(90 - j1Val, 0, 180, SERVOMIN, SERVOMAX));
                pwm2.setPWM(channel[leg][1], 0, map(90 - j2Val, 0, 180, SERVOMIN, SERVOMAX));
                pwm2.setPWM(channel[leg][2], 0, map(j3Val + J3_LegAngle - 45, 0, 180, SERVOMIN, SERVOMAX));
            }

            if (J1Tar[leg].isFinished() && J2Tar[leg].isFinished() && J3Tar[leg].isFinished()) {
                legs[leg].isMoving = false;
            }
        }
    }
}

 



  void movegroupB(){
    for(int i = 0; i < groupsizeB; i++){
      int legB = groupB[i];
      if (legs[legB].isMoving) {
        J1Tar[legB].update();
        J2Tar[legB].update();
        J3Tar[legB].update();

        // Only leg 4 is mirrored; others not
        //bool isMirrored = (leg == 4);  


        double j1Val = J1Tar[legB].getValue();
        double j2Val = J2Tar[legB].getValue();
        double j3Val = J3Tar[legB].getValue();

        
      
        if(legB == 1 || legB == 3 || legB == 5){
          pwm.setPWM(channel[legB][0], 0, map(90 - j1Val, 0, 180, SERVOMIN, SERVOMAX));
          pwm.setPWM(channel[legB][1], 0, map(90 - j2Val, 0, 180, SERVOMIN, SERVOMAX));
          pwm.setPWM(channel[legB][2], 0, map(j3Val + J3_LegAngle - 45, 0, 180, SERVOMIN, SERVOMAX));
        }

        if (J1Tar[legB].isFinished() && J2Tar[legB].isFinished() && J3Tar[legB].isFinished()) {
          legs[legB].isMoving = false; 
        }
      }
    }
    


    
  }

 

void assignpathleftandright(){

  for(int i = 0; i <6; i++){
    if(i == 0 || i == 1 || i == 2) {

      legs[i].path = stepPathleftside;
    }else{

      legs[i].path = stepPathrightside;
    }
    legs[i].pathLength = pathlength;
    legs[i].stepIndex = 0;
  }

}





int lastMovedGroup = -1;  // 0 for A, 1 for B

void move_forward() {
  static bool hasLifted = false;
  static bool hasPushed = false;
  static unsigned long liftTimestamp = 0;

  bool isGroupA = (counter_movement % 2 == 0);

  // Always update both groups to continue interpolating legs
  movegroupA();
  movegroupB();

  bool groupMoving = !isfinished(isGroupA ? groupA : groupB, isGroupA ? groupsizeA : groupsizeB);
  bool otherGroupMoving = !isfinished(isGroupA ? groupB : groupA, isGroupA ? groupsizeB : groupsizeA);

  // Phase 1: Lift at step 1 (only once)
  if (!hasLifted && stepindex == 1) {
    if (isGroupA) moveupGroup(groupA, groupsizeA);
    else moveupGroup(groupB, groupsizeB);
    hasLifted = true;
    liftTimestamp = millis();
    Serial.println("Group lifted.");
  }

  // Phase 2: Wait for lifting to finish, then push other group
  if (hasLifted && !hasPushed) {
    bool liftingFinished = isGroupA ? isfinished(groupA, groupsizeA) : isfinished(groupB, groupsizeB);
    if (liftingFinished) {
      if (isGroupA) pushGroup(groupB, groupsizeB);
      else pushGroup(groupA, groupsizeA);
      hasPushed = true;
      Serial.println("Opposite group pushed.");
    }
  }

  // Phase 3: Swing group steps through path after push
  if (hasPushed && stepindex > 1) {
    const int* swing = isGroupA ? groupA : groupB;
    int sSize = isGroupA ? groupsizeA : groupsizeB;
    for (int i = 0; i < sSize; i++) {
      int leg = swing[i];
      if (!legs[leg].isMoving) {  // Only send new IK if leg is ready
        legs[leg].stepIndex = stepindex;
        InverseKinematic(
          leg,
          stepPath[stepindex][0],
          stepPath[stepindex][1],
          stepPath[stepindex][2]
        );
      }
    }
  }

  // Phase 4: Advance to next step only when BOTH groups are finished
  if (!groupMoving && !otherGroupMoving && millis() - lastStepTime > stepDelay) {
    lastStepTime = millis();
    stepindex++;
    if (stepindex >= pathlength) {
      stepindex = 0;
      counter_movement++;
      hasLifted = false;
      hasPushed = false;
      Serial.println("Step sequence complete. Switching group.");
    }
  }
}






// trebuie lucrat la miscarea in stanga 
// am realizat ca poti sa folosesc aceeasi functie numa sa schimb sau sa fac un alt inversekinematic unde nu inversez X pe grupe , ptr ca normal inversezi X,Y conform
// regulii manii , da pot pur si simplu sa nu inversez X si sa faca pe grupe de exemplu picioarele 0,2 sa mearga in spate ,picioru 4 in fata , si picioru 1 sa mearga in spate
// si picioru 3,5 sa mearga in fata , basically intorcand robotu
void moveleft(){


  bool isGroupA = (counter_movement % 2 == 0);


  if (isGroupA) {
    movegroupA();
  } else {
    movegroupB();
  }

  bool groupFinished = isGroupA 
      ? isfinished(groupA, groupsizeA)
      : isfinished(groupB, groupsizeB);

  if (groupFinished && millis() - lastStepTime > stepDelay) {
    lastStepTime = millis();
    stepindex++;

    if (stepindex >= pathlength) {
      stepindex = 0;
      counter_movement++;
    }

    if (isGroupA) {
      for (int i = 0; i < groupsizeA; i++) {
        int leg = groupA[i];
        InverseKinematic(leg,legs[leg].path[stepindex][0],legs[leg].path[stepindex][1],legs[leg].path[stepindex][2]);
      }
      Serial.println("Group A (turn left) moving to next step");
    } 
  }



}

void moveright(){


  bool isGroupA = (counter_movement % 2 == 0);


  if (isGroupA) {
    movegroupA();
  } else {
    movegroupB();
  }

  bool groupFinished = isGroupA 
      ? isfinished(groupA, groupsizeA)
      : isfinished(groupB, groupsizeB);

  if (groupFinished && millis() - lastStepTime > stepDelay) {
    lastStepTime = millis();
    stepindex++;

    if (stepindex >= pathlength) {
      stepindex = 0;
      counter_movement++;
    }

    if (isGroupA) {
      for (int i = 0; i < groupsizeA; i++) {
        int leg = groupA[i];
        InverseKinematic(leg,legs[leg].path[stepindex][0],legs[leg].path[stepindex][1],legs[leg].path[stepindex][2]);
      }
      Serial.println("Group A (turn left) moving to next step");
    } 
  }



}






void executeStop(){
  // Immediately stop interpolation at current positions
  for(int i = 0; i < 6; i++){
    J1Tar[i].go(J1Tar[i].getValue(), 0, LINEAR, ONCEFORWARD);
    J2Tar[i].go(J2Tar[i].getValue(), 0, LINEAR, ONCEFORWARD);
    J3Tar[i].go(J3Tar[i].getValue(), 0, LINEAR, ONCEFORWARD);
    
    legs[i].isMoving = false;  // ensure all legs marked stationary
  }

  Serial.println("Robot stopped.");
}





  bool wasMoving = false;
  
void loop() {
  


  
  if(radio.available()){
    radio.read(&receivedData,sizeof(receivedData));

    Serial.print(" X: ");
    Serial.println(receivedData.x);
    Serial.print(" Y: ");
    Serial.println(receivedData.y);
    Serial.print(" button value ");
    Serial.println(receivedData.button);

    if(receivedData.x > 100 && receivedData.y < 100){

      move_forward();
      delay(350);
      Serial.println(" Moving forward ");
      //wasMoving = true;
      //robotShouldMoveForward = true;
      
     
    }else{
      Serial.println(" robot idle ");
      executeStop();
      robotShouldMoveForward = false;
    }
    
  }
  
  
    
  
  
}

  


//void loop(){}
 



  void InverseKinematic(int legindex,double X, double Y, double Z){
  //invertam Z ca sa fie corect xyz rule


  Z = -Z;
  // offsetu
  Y += Y_Rest;
  Z += Z_Rest;


  double J1 = atan(X / Y) * (180 / PI);
  double H = sqrt((Y * Y) + (X * X)); 
  double L = sqrt((H * H) + (Z * Z)); 
  double J3 = acos(   ((J2L * J2L) + (J3L * J3L) - (L * L))   /   (2 * J2L * J3L)   ) * (180 / PI); //  cos alpha 
  double B = acos(   ((L * L) + (J2L * J2L) - (J3L * J3L))   /   (2 * L * J2L)   ) * (180 / PI);
  double A = atan(Z / H) * (180 / PI);  
  double J2 = (B + A);  

  if(legindex == 0  || legindex == 2  || legindex == 5) {
    
    J1 = -J1;
    J2 = -J2;
    J3 = -J3;
    //J3 = -J3;
  }

  
  Serial.print("Leg "); Serial.println(legindex);
  //Serial.print("Input X: "); Serial.println(X);
  //Serial.print("Input Y: "); Serial.println(Y);
  //Serial.print("Input Z: "); Serial.println(Z);
  Serial.print("Calculated J1: "); Serial.println(J1);
  Serial.print("Calculated J2: "); Serial.println(J2);
  Serial.print("Calculated J3: "); Serial.println(J3);

  


  UpdatePosition(legindex,J1, J2, J3);

  }


 


  void UpdatePosition(int legindex, double J1, double J2, double J3){
    if (!J1Tar[legindex].isFinished() || !J2Tar[legindex].isFinished() || !J3Tar[legindex].isFinished()) return;  
    J1Tar[legindex].go(J1, 100, LINEAR, ONCEFORWARD);
    J2Tar[legindex].go(J2, 100, LINEAR, ONCEFORWARD);
    J3Tar[legindex].go(J3, 100, LINEAR, ONCEFORWARD);
    legs[legindex].isMoving = true;


  }






