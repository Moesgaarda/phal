
#include "PhalGroup.h"


PhalGroup::PhalGroup()
{
}

void PhalGroup::add(Adt *item) {
	list.add(item);
}

void PhalGroup::on() {
	int lstSize = list.size();

	for (int i = 0; i < lstSize; i++)
	{
		list.get(i)->on();
	}

}

Adt* PhalGroup::get(int index) {
	return list.get(index);
}
int PhalGroup::size() {
	return list.size();
}

void PhalGroup::off() {
	int lstSize = list.size();
	for (int i = 0; i < lstSize; i++)
	{
		list.get(i)->off();
	}
}
