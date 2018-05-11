#include <iostream>
#include "Adt/Adt.h"
#include "Servo/Servo.h"

using namespace std;

int main()
{
    servo myservo;
    myservo.add(1);
    if (myservo.added())
    {
        myservo.move(20);
        int i = myservo.read();
        myservo.remove();
    }
    else {
        std::cout << "error servo not added correctly";
    }

    std::cout << "hello worldus";
    return 0;
}