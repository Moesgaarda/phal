#include "../Arduino.h"
#include "Motor.h"

Motor::Motor(int pin) : public Adt 
{
  pinMode(pin, OUTPUT);
  _pin = pin;
}

void Motor::on() {
    digitalWrite(_pin, HIGH);
}

void Motor::off() {
    digitalWrite(_pin, LOW);	
}