/*
PhalGroup.h - Library for Groups in the Phal language.
Created by Frederik V. Schrøder, may 9 2018.
Used in our university assignment.
*/

#ifndef PhalGroup_h
#define PhalGroup_h

#include "Arduino.h"

class PhalGroup
{
public:
	PhalGroup();
	void add(Adt _t);
	void iterate();
private:
	Adt arr[];
};


PhalGroup<T>::add(Adt _t){
    
}

PhalGroup<T>::iterate(){
    
}

#endif