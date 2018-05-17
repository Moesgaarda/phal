#include "PhalList.h"
#include "PhalGroup.h"
#include "Adt.h"
#include "LightBulb.h"
int a = 222;
PhalList<int*> list = PhalList<int*>(); 

LightBulb*lb = new LightBulb(13);
PhalGroup *pg = new PhalGroup();
LightBulb *lb2 = new LightBulb(12);



void setup(){
  Serial.begin(9600);
  list.add(&a);
  int *b = list.get(0);
  *b = 5;
  Serial.println(a);
  Serial.println(*b);
  
  pg->add(lb);
  lb->on();
  delay(2000);
  lb->off();
  delay(2000);
  //*lb2 = dynamic_cast<LightBulb*>(pg->get(0));
  pg->on();
  
  /*
  Serial.begin(9600);
  list.add(1);
  list.add(2);
  list.add(3);
  int b = list.get(0);
  Serial.println(b);
  list.remove(0);
  b = list.get(0);
  
  Serial.println(b);
  lb->on();
  */
}
void loop(){
  
}

