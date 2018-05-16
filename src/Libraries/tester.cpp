#include <iostream>
#include "Group/Group.h"
#include "Adt/Adt.h"
#include "Lightbulb/Lightbulb.cpp"

using namespace std;

int main()
{
    lightbulb = new Lightbulb(2);
    lightbulb2 = new Lightbulb(3);

    PhalGroup group = PhalGroup<Lightbulb>(2);
    group.add(lightbulb);
    group.add(Lightbulb2);

    group.on();
    group.off();

    return 0;
}