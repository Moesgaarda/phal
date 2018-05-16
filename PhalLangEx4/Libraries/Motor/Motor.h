#ifndef Motor_h
#define Motor_h

#include "../Adt/Adt.h"

class Motor: public Adt
{
    public:
	    Motor(int pin);
	    void on();
	    void off();

    private: 
    	int _pin;
	bool status;
};

#endif
