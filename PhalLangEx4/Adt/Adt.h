#ifndef Adt_h
#define Adt_h

#include "Arduino.h"

class Adt
{
  protected:
    Type var;
    bool status;
  public:
    Adt() {}
    Adt(const Type &initValue) : var(initValue) {}
    virtual void on();
    virtual void off();
    virtual void toggleOnOff(){ status ? off() : on(); }
};