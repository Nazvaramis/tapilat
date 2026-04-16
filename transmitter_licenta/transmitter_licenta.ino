#include <SPI.h>
#include <RF24.h>


struct JoystickData {
  int x;
  int y;
  bool button;
};

RF24 radio(7,8);

const byte address[6] = "00001";

void setup() {
  
Serial.begin(115200);       // Optional: for debugging
pinMode(2, INPUT_PULLUP); // SW pin as input with internal pull-up
radio.begin();
radio.setPALevel(RF24_PA_LOW);
radio.setChannel(108);
radio.openWritingPipe(address);
radio.stopListening();


}

void loop() {
  JoystickData data;
  
   data.x = analogRead(A1);  // Read horizontal axis (VRx)
   data.y = analogRead(A2);  // Read vertical axis (VRy)
   data.button = digitalRead(2) == LOW; // true when pressed

  Serial.print(" X: ");
  Serial.println(data.x);
  Serial.print(" Y: ");
  Serial.println(data.y);
  Serial.print(" button: ");
  Serial.println(data.button);

  radio.write(&data, sizeof(data));
  
  
  delay(50);
}
