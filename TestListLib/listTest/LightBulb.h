#ifndef LightBulb_h
#define LightBulb_h

#include "Arduino.h"
#include "Adt.h"

class LightBulb : public Adt
{

  public:
    LightBulb(int pin);
    void on();
    void off();
    void toggleOnOff();
  private:
    bool isOn;
    int _pin;
};

LightBulb::LightBulb(int pin){
_pin = pin;
pinMode(_pin, OUTPUT);
  isOn = false;
}


void LightBulb::on(){
  digitalWrite(_pin, HIGH);
  isOn = true;
}
void LightBulb::off(){
    digitalWrite(_pin, LOW);
    isOn = false;
}
void LightBulb::toggleOnOff(){
    if(isOn)
    {
      off();
    }
    else
    {
      on();
    }
}

#endif
