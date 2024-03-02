
const int SW_pin = 5; // buttonu , daca il apesi valoare se face in 0
const int x_pin = 2;
const int y_pin = 0;







void setup() {
  
  pinMode(SW_pin, INPUT);
  digitalWrite(SW_pin,HIGH);
  Serial.begin(115200);
  
}

void loop() {




 // citeste pozitia potentiometrului de la joystick care are pozitia X // citeste pinu analog x de la joystick






Serial.print("X: ");
Serial.print(analogRead(x_pin));
Serial.print("\n");
Serial.print("Y: ");
Serial.print(analogRead(y_pin));
Serial.print("\n");
Serial.print("Button: ");
Serial.print(digitalRead(SW_pin));
Serial.print("\n");
//citeste pinu analog y de la joystick // citeste pozitia potentiometrului de la joystick care are pozitia X


delay(500); //delay 500milisecunde

}
