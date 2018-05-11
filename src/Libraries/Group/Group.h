/*
PhalGroup.h - Library for Groups in the Phal language.
Created by Frederik V. Schrøder, may 9 2018.
Used in our university assignment.
*/

#ifndef PhalGroup_h
#define PhalGroup_h

//#include "Arduino.h"
#include "../Adt/Adt.h"

template <typename Adt>
class PhalGroup
{
  public:
    PhalGroup(int size);
    void add(Adt _t);
    void iterate();

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
PhalGroup<Adt>::add(Adt _t)
{
    arr[_pos] = _t;
    _pos++;
}

template <typename Adt>
PhalGroup<Adt>::iterate()
{
    // ????????????? implementus patronus
}

#endif