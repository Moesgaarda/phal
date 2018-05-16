/*
PhalGroup.h - Library for Groups in the Phal language.
Created by Frederik V. Schrøder and Morfae, may 9 2018.
Used in our university assignment.
*/

#include "../Adt/Adt.h"

class PhalGroup : public Adt
{
public:
    PhalGroup(int = 0);
    void add(Adt _t);

private:
    int _size;
    int _pos = 0;
    Adt arr[];
};


PhalGroup<Adt>::PhalGroup(int size)
{
    _size = size;
    Adt arr[] = new Adt[_size];
}

void PhalGroup<Adt>::add(Adt _t)
{
    arr[_pos] = _t;
    _pos++;
}

template <typename Adt>
void PhalGroup<Adt>::on()
{
    for(Adt a: arr){
        a.on();
    }
}

void PhalGroup<Adt>::off()
{
    for(Adt a: arr){
        a.off();
    }
}

void PhalGroup<Adt>::toggleOnOff()
{
    for(Adt a: arr){
        a ? a.off() : a.on();
    }
}