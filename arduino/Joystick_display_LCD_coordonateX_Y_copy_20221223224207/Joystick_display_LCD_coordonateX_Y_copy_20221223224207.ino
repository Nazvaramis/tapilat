#include "LiquidCrystal_I2C.h"
const int SW_pin = 5; // buttonu , daca il apesi valoare se face in 0
const int x_pin = 2;
const int y_pin = 0;

// joystick cu display la LCD (sa cumpar alt LCD ca cel a lui tudor nu e bun)


LiquidCrystal_I2C lcd(0x27,20,4);





void setup() {
  
  pinMode(SW_pin, INPUT);
  digitalWrite(SW_pin,HIGH);
  Serial.begin(115200);
  lcd.init();
  lcd.backlight();

}

void loop() {
lcd.setCursor(0,0);
//lcd.print("Button: ")
//lcd.print(digitalRead(SW_pin));
lcd.print("X: ");
if(analogRead(x_pin) > 1021){
lcd.print("1021");

}
else{
lcd.print(analogRead(x_pin));
}

 // citeste pozitia potentiometrului de la joystick care are pozitia X // citeste pinu analog x de la joystick
lcd.setCursor(0,1);
lcd.print("Y: ");
// if() asta l-am scris ptr ca dupa ce miscam joystick potentiometurului mergeala 1021 si dupa ce il puneam in neutru sa zicem adica il lasam valoarea
// se schimba la 5000+ la X si 4900+ la Y: for some reason , e defect LCD-u lui , am patit si la Moving Text sa arate simboluri ciudate fara motivS
if(analogRead(y_pin) > 1021){
lcd.print("1021");

}
else{
lcd.print(analogRead(y_pin));
}

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
