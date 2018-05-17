#pragma once
#ifndef LightBulb_h
#define LightBulb_h

#include "Adt.h"

class LightBulb : public Adt {
public:
	LightBulb(int pin);
	virtual void on();
	virtual void off();
};

#endif