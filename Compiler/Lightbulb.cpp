

#include "Lightbulb.h"
Lightbulb::Lightbulb(int pin)
{
  _isOn = false;
	_pin = pin;
  pinMode(_pin, OUTPUT);
}
void Lightbulb::on() {
	_isOn = true;
  digitalWrite(_pin, HIGH);
}
void Lightbulb::off() {
  digitalWrite(_pin, LOW);
	_isOn = false;
}
