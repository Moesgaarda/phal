#include "Arduino.h"
#include "Motor.h"

Motor::Motor(int pin) : public Adt 
{
  pinMode(pin, OUTPUT);
  _pin = pin;
}

void Motor::on() {
    Motor.status = true;
    digitalWrite(_pin, HIGH);
}

void Motor::off() {
    Motor.status = false;
    digitalWrite(_pin, LOW);	
}