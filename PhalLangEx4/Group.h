/*
PhalGroup.h - Library for Groups in the Phal language.
Created by Frederik V. Schrøder and Morfae, may 9 2018.
Used in our university assignment.
*/

#ifndef PhalGroup_h
#define PhalGroup_h

#include "../Adt/Adt.h"

template <typename Adt>
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



template <typename Adt>
PhalGroup<Adt>::PhalGroup(int size)
{
    _size = size;
    Adt arr[] = new Adt[_size];
}

template <typename Adt>
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

template <typename Adt>
void PhalGroup<Adt>::off()
{
    for(Adt a: arr){
        a.off();
    }
}

template <typename Adt>
void PhalGroup<Adt>::toggleOnOff()
{
    for(Adt a: arr){
        a ? a.off() : a.on();
    }
}

#endif