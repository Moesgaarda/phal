#include <iostream>
#include "Adt/Adt.h"
#include "Motor/Motor.h"

using namespace std;

int main()
{
    Motor mymotor(1);
    mymotor.on();
    if (mymotor.status())
    mymotor.off();

    std::cout << "Motor works";
    return 0;
}