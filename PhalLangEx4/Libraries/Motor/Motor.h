#ifndef Motor_h
#define Motor_h

#include "Adt.h"

class Motor: public Adt
{
    public:
	    Motor(int pin);
	    virtual void on();
	    virtual void off();
};

#endif
