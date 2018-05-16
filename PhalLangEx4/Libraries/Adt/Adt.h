#ifndef Adt_h
#define Adt_h

#include "Arduino.h"

class Adt
{
  protected:
    bool status;
  public:
    Adt() {}
    virtual void on();
    virtual void off();
    virtual void toggleOnOff(){ status ? off() : on(); }
};

#endif