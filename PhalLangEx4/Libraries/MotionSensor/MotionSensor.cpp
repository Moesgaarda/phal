#include "Arduino.h"
#include "MotionSensor.h"

MotionSensor::MotionSensor(int pin)
{
    pinMode(pin, INPUT);
    _pin = pin;
}

void MotionSensor::on()
{
    status = true;
}

void MotionSensor::off()
{
    status = false;
}

void MotionSensor::read()
{
    if (status == true)
    {
        if (digitalRead(_pin) != 0)
            reading = true;
        else
            reading = false;
    }
}
