#include "Arduino.h"
#include "Adt.h"

Type var;
bool status;

Adt::Adt(){

}

virtual void Adt::on(){}
virtual void Adt::off(){}
virtual void Adt::toggleOnOff(){
    status ? off() : on();
}