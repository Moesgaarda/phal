#ifndef TemperatureSensor_h
#define TemperatureSensor_h


#include "Arduino.h"
#include "../Adt/Adt.h"

class TemperatureSensor : public Adt{
	public:
        TemperatureSensor(int pin);
        double reading();
    private:
    	int _pin;
};

#endif
