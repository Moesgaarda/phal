#ifndef Adt_h
#define Adt_h

#include "Arduino.h"

class Adt
{
  public:
    Adt();
    void on();
    void off();
    void toggleOnOff();
   protected:
     int _pin;
     bool isOn;
};
Adt::Adt(){
  isOn = false;
}

void Adt::on(){
  digitalWrite(_pin, HIGH);
  isOn = true;
}
void Adt::off(){
    digitalWrite(_pin, LOW);
    isOn = false;
}
void Adt::toggleOnOff(){
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
