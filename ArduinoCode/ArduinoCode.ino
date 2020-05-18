#include "DHT.h"
#define DHT_PIN 2
#define DHTTYPE DHT11
DHT dht(DHT_PIN, DHTTYPE);

unsigned long previousMillis = 0; 

const long interval = 1000; 

void setup() {
  dht.begin();
  Serial.begin(9600);
}

void loop() {

  unsigned long currentMillis = millis();

  //DO THIS BLOCK EVERY SECOND
  if (currentMillis - previousMillis >= interval) {

    previousMillis = currentMillis;


    Serial.print("Temperature: ");
    Serial.println(dht.readTemperature());
    Serial.print("Humidity: ");
    Serial.println(dht.readHumidity());
    delay(1000);
  }
}
