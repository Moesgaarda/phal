#include "PhalList.h"
int a = 222;
PhalList<int> list = PhalList<int>(); 

void setup(){
  Serial.begin(9600);
  list.add(1);
  list.add(2);
  list.add(3);
  int b = list.get(0);
  Serial.println(b);
  list.remove(0);
  b = list.get(0);
  
  Serial.println(b);
}
void loop(){
  
}

