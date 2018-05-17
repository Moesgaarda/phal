#ifndef PhalGroup_h
#define PhalGroup_h

#include "PhalList.h"
#include "Adt.h"


class PhalGroup
{
private:
	PhalList<Adt*> list;
public:
	PhalGroup();
	void add(Adt *item);
	Adt* get(int index);
	int size();
	void on();
	void off();
};

#endif
