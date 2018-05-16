#ifndef Lightbulb_h
#define Lightbulb_h

#include "Arduino.h"
#include "../Adt/Adt.h"

class Lightbulb: public Adt {
    public:
	    Lightbulb(int pin);
	    void on();
	    void off();
	    void dim(int dimValue);
    private: 
    	int _pin;
	protected:
		bool status;
};

#endif
