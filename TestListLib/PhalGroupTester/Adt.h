#pragma once
#ifndef Adt_h
#define Adt_h

#include "Arduino.h"
class Adt {
public:
	Adt();
	virtual void on();
	virtual void off();
protected:
	int _pin;
	bool _isOn;
};

#endif
