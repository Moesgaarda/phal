#include "Servo.h"

servo::servo(string name)
{
  Servo name;
}

void servo::add(int pin){
  Servo.attach(pin)
      _pin = pin;
}

void servo::add(int pin, int min, int max){
  Servo.attach(pin, min, max);
  _pin = pin;
  _min = min;
  _max = max;
}

void servo::remove(servo name){
  name.detach();
}

void servo::move(int value){
  Servo.write(value);
}

int servo::read(servo name){
  name.read();
}

bool servo::added(){
  Servo.attached();
}
