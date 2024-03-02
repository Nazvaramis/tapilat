
const int inPin = 5;                   // A5 is where you connect the sensor
int value = analogRead(inPin); // read the value from the sensor

float millivolts = (value / 1024.0) * 5000; 
float celsius = millivolts / 10;


void setup()
{
  
  Serial.begin(115200);
}
void loop()
{
  
  Serial.print("\nTemperature is:");
  Serial.print(celsius);
  Serial.print("\n");

  
  


  delay(1000);
}
