

#include "LightBulb.h"
LightBulb::LightBulb(int pin)
{
  _isOn = false;
	_pin = pin;
  pinMode(_pin, OUTPUT);
}
void LightBulb::on() {
	_isOn = true;
  digitalWrite(_pin, HIGH);
}
void LightBulb::off() {
  digitalWrite(_pin, LOW);
	_isOn = false;
}
