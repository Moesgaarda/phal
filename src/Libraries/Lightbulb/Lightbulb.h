#ifndef Lightbulb_h
#define Lightbulb_h


#include "Arduino.h"

class Lightbulb {
    public:
	    Lightbulb(int pin);
	    void on();
	    void off();
	    void dim(int dimValue);
    private: 
    	int _pin;
}

#endif
