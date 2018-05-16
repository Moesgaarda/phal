#include "Arduino.h"
#include "Motor.h"

Motor::Motor(int pin) : Adt() 
{
  pinMode(pin, OUTPUT);
  _pin = pin;
}

void Motor::on() {
    status = true;
    digitalWrite(_pin, HIGH);
}

void Motor::off() {
    status = false;
    digitalWrite(_pin, LOW);	
}