#ifndef MotionSensor_h
#define MotionSensor_h

#include "Arduino.h"

class MotionSensor : public Adt {
    public:
	    MotionSensor(int pin);
	    void on();
	    void off();
        void read();
    private: 
    	int _pin;
	protected:
		bool status;
        bool reading;
};

#endif
