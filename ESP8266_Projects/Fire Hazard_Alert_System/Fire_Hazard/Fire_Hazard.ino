#define BLYNK_TEMPLATE_ID "TMPL3SVpUlqlI"
#define BLYNK_TEMPLATE_NAME "fire hazard"
#define BLYNK_AUTH_TOKEN "-3T16jDxV9fPday7IGuMmf_m4Es5MTUu"

#define BLYNK_PRINT Serial
#include <ESP8266WiFi.h>
#include <BlynkSimpleEsp8266.h>

char auth[] = BLYNK_AUTH_TOKEN;
char ssid[] = "";  // type your wifi name
char pass[] = "";  // type your wifi password

int ledPin = D7;       
int flamePin = D6;     
int buzzerPin = D2;    
int smokePin = A0;     

/*
MQ2 Gas Sensor:
VCC to 5V of ESP8266
GND to GND of ESP8266
A0 to A0 of ESP8266
Flame Sensor:
VCC to 5V of ESP8266
GND to GND of ESP8266
D0 to D6 of ESP8266
Buzzer:
VCC to D2 of ESP8266
GND to GND of ESP8266 
LED:
Anode (+) to D7 of ESP8266 through a current-limiting resistor (e.g., 220Ω)
Cathode (-) to GND of ESP8266
*/

BlynkTimer timer;
int flag = 0;
bool smokeAlertSent = false; // Declaration of smokeAlertSent variable
bool fireAlertSent = false; // Declaration of fireAlertSent variable

void sendSensor() {
  int isButtonPressed = digitalRead(D1);

  if (isButtonPressed == 0 && flag == 0) {
    Serial.println("Fire in the House");
    Blynk.logEvent("fire_alert", "Fire Detected");
    Blynk.logEvent("gas_alert", "High Gas Level Detected");
    flag = 1;
  } else if (isButtonPressed == 1) {
    flag = 0;
  }

  int flameSensorRead = digitalRead(flamePin); // Reading the flame sensor data
  Blynk.virtualWrite(V0, flameSensorRead);     // Sending data to Blynk
  Serial.print("Flame Status: ");
  Serial.println(flameSensorRead);

  int smokeLevel = analogRead(smokePin); // Reading the smoke sensor data
  Blynk.virtualWrite(V2, smokeLevel);    // Sending data to Blynk
  Serial.print("Current Gas Level: ");
  Serial.println(smokeLevel);

  if (smokeLevel > 10 && !smokeAlertSent) {
    activateAlarm();
    smokeAlertSent = true;
    Blynk.logEvent("gas_alert", "HIGH GAS LEVEL DETECTED.....ESCAPE FROM THE PLACE");
  } else if (flameSensorRead == LOW && !fireAlertSent) {
    activateAlarm();
    fireAlertSent = true;
    Blynk.logEvent("fire_alert", "FIRE DETECTED.....EVACUATE FROM THE PLACE IMMEDIATELY");
  } else {
    deactivateAlarm();
  }
}

void activateAlarm() {
  digitalWrite(ledPin, HIGH);
  digitalWrite(buzzerPin, HIGH);
  Blynk.virtualWrite(V1, 255); // Set LED brightness to full in Blynk app (indicates alarm)
}

void deactivateAlarm() {
  digitalWrite(ledPin, LOW);
  digitalWrite(buzzerPin, LOW);
  Blynk.virtualWrite(V1, 0); // Turn off LED in Blynk app
}

void setup() {
  pinMode(ledPin, OUTPUT);
  pinMode(flamePin, INPUT);
  pinMode(buzzerPin, OUTPUT);
  pinMode(smokePin, INPUT);
  
  digitalWrite(ledPin, LOW);
  digitalWrite(buzzerPin, LOW);
  
  Serial.begin(9600);
  Blynk.begin(auth, ssid, pass);
  timer.setInterval(5000L, sendSensor); // Check sensors every 5 seconds
}

void loop() {
  Blynk.run();
  timer.run();
}