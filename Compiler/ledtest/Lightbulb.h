#pragma once
#ifndef Lightbulb_h
#define Lightbulb_h

#include "Adt.h"

class Lightbulb : public Adt {
public:
	Lightbulb(int pin);
	virtual void on();
	virtual void off();
};

#endif