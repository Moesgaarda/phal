#ifndef TemperatureSensor_h
#define TemperatureSensor_h


#include "Arduino.h"

class TemperatureSensor{
	public:
        TemperatureSensor(int pin);
        double reading();
    private:
    	int _pin;
};

#endif
