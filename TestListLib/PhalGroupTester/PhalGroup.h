#ifndef PhalGroup_h
#define PhalGroup_h

#include "PhalList.h"
#include "Adt.h"


class PhalGroup : public Adt
{
private:
	PhalList<Adt*> list;
public:
	PhalGroup();
	void add(Adt *item);
	Adt* get(int index);
	int size();
	virtual void on();
	virtual void off();
};

#endif
