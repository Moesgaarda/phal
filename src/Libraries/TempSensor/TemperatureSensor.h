#ifndef Lightbulb_h
#define Lightbulb_h


#include "Arduino.h"

class TemperatureSensor{
	public:
        TemperatureSensor(int pin);
        void reading();
    private:
    	int _pin;
}

#endif
