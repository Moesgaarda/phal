#include "Adt.h"
#include "LightBulb.h"
#include "PhalList.h"
#include "PhalGroup.h"
#include "TemperatureSensor.h"
#include "Motor.h"


  LightBulb *lb = new LightBulb(13);
  LightBulb *lb2 = new LightBulb(12);
  TemperatureSensor *ts = new TemperatureSensor(7);
  Motor *m = new Motor(5);
  Motor *mm;

  PhalList<Adt*> *pl = new PhalList<Adt*>();
  PhalGroup *pg = new PhalGroup();

void setup() {
  Serial.begin(9600);
  pl->add(m);
  pg->add(lb);
  pg->add(lb2);
  pg->add(ts);
  pg->add(m);

  mm = static_cast<Motor*>(pl->get(0));
}

void loop() {
  pg->on();
  delay(2000);
  pg->off();
  delay(2000);
  Serial.println(ts->reading());
}
