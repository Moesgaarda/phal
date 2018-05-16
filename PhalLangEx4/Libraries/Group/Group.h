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
    PhalGroup(int size);
    void add(Adt _t);
    void on();
    void off();
    void toggleOnOff();
};

#endif