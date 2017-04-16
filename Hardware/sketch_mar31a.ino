#include <SoftwareSerial.h>
#include <TinyGPS.h>

TinyGPS gps;
SoftwareSerial gpsSerial(2, 3);
float flat, flon;
  
static void smartdelay(unsigned long ms);

void setup()
{
  Serial.begin(9600);
  gpsSerial.begin(9600);
  
}

void loop()
{
  //@100<50>
    gps.f_get_position(&flat, &flon);
    Serial.print("@");
    Serial.print(flat, 6);
    Serial.print("<");
    Serial.print(flon, 6);  
    Serial.print(">");
    smartdelay(700);
    delay(2000);
}





static void smartdelay(unsigned long ms)
{
  unsigned long start = millis();
  do 
  {
    while (gpsSerial.available())
      gps.encode(gpsSerial.read());
  } while (millis() - start < ms);
}



