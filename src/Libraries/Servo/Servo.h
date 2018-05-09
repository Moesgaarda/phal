/*
  Servo.h - Library for controlling a servo in PHAL.
  Created by Thomas L. Hansen, May 9, 2018.
  Released for use at Aalborg University
*/

#ifndef servo_h
#define servo_h

#include "Arduino.h"
#include "Libraries/Servo.h"

class servo
{
public:
  servo();
  int add(int pin);
  int add(int pin, int min, int max);
  void remove();
  void move(int value);
  int read();
  bool added();

private:
  int _pin;
  int _min;
  int _max;
};

servo::add(){
  Servo.attach(pin)
      _pin = pin;
}

servo::add(int pin, int min, int max){
  Servo.attach(pin, min, max);
  _pin = pin;
  _min = min;
  _max = max;
}

servo::remove(){
  Servo.detach();
}

servo::move(){
  Servo.write(value);
}

servo::read(){
  Servo.read();
}

servo::added(){
  Servo.attached();
}

#endif