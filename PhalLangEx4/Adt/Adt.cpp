#include "Arduino.h"
#include "Adt.h"

Type var;
bool status;

Adt::Adt(){

}

Adt::Adt(const Type &initValue) : var(initValue) {
    var = initValue;
}
void Adt::on(){
    status = off;
}
void Adt::off(){
    status = on;
}
virtual void Adt::toggleOnOff(){
    status ? off() : on();
}