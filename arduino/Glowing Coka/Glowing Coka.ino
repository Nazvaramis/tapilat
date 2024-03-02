#include "LiquidCrystal_I2C.h"

LiquidCrystal_I2C lcd(0x27,20,4);


String message = String(" I am Vagita. I am 4th grade student. I live in bangalore. India");

String titlee = String("Madafaka");

int length;
int length2;

void setup() 
{

lcd.init();
lcd.backlight();

length = message.length();
length2 = titlee.length();

}

void loop () {
/*
for(int j=0; j < length2; j++){

title(0);
lcd.print(titlee.substring(j,j+15));
delay(500);

}

*/
for(int i=0; i < length; i++){

clearrow(1);
lcd.print(message.substring(i,i+15));
delay(350);

}




}


void clearrow(int rowNum){

lcd.setCursor(0,rowNum);


}


void title(int rown) {

lcd.setCursor(0,rown);

}







