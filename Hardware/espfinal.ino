#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266HTTPClient.h>
ESP8266WiFiMulti WiFiMulti;

String data ="";
String url_d="";

void setup() {

  Serial.begin(9600);      // Start Serial

  Serial.println();
  url_d.reserve(400);
  data.reserve(200);
  WiFiMulti.addAP("Nihit","54405440");
  delay(3000);
    
    
}//end setup

void loop()
{
  if(WiFiMulti.run() == WL_CONNECTED) {
   
        if (Serial.available() > 0){
          
          data = Serial.readString();
          
          //@100<50>30,50#
          int commaIndex = data.indexOf('@');
          int commaIndex1 = data.indexOf('<',commaIndex);
          //  Search for the next comma just after the first
          int secondCommaIndex = data.indexOf('>', commaIndex1 + 1);
        
          String firstValue = data.substring(commaIndex+1, commaIndex1);
          String secondValue = data.substring(commaIndex1 + 1, secondCommaIndex);
        
      
        
          //http://nihit.comli.com/Arduino.php?latitude=100&longitude=50&bus=MH%2009%20AA%204000
          // url_d = "http://nihit.comli.com/Arduino.php?latitude=76&longitude=456&bus=5440";
          url_d = "http://nihit.comli.com/Arduino.php?latitude=";
          url_d += firstValue;
          url_d +="&longitude=";
          url_d += secondValue;
          url_d +="&bus=MH%2009%20AA%204000";
          Serial.println(url_d);
      
          HTTPClient http;
          Serial.print("[HTTP] begin...\n");
          // configure traged server and url
          http.begin(url_d); //HTTP
      
          Serial.print("[HTTP] GET...\n");
          // start connection and send HTTP header
          int httpCode = http.GET();
      
          // httpCode will be negative on error
          if (httpCode > 0)
            // HTTP header has been send and Server response header has been handled
            Serial.printf("[HTTP] GET... code: %d\n", httpCode);
          } 
          else {
                  Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
          }
      
          http.end();
          data="";
          url_d="";
          delay(100);
        }
  }//end if
   
}//end loop
