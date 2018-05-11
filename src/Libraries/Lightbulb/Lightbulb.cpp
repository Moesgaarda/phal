#include "Arduino.h"
#include "Lightbulb.h"

Lightbulb::Lightbulb(int pin){
    pinMode(pin, OUTPUT);
    _pin = pin;
}

void Lightbulb::on(){
    digitalWrite(_pin, HIGH);
    status = true;
}

void Lightbulb::off(){
    digitalWrite(_pin, LOW);	
    status = false;
}

void Lightbulb::dim(int dimValue) {
	if(dimValue > 0 || dimValue < 255)
	    analogWrite(_pin, dimValue);
}
