#include "Arduino.h"
#include "TemperatureSensor.h"
#include "math.h"

TemperatureSensor::TemperatureSensor(int pin){
	pinMode(pin, INPUT);
	_pin = pin;
}

double TemperatureSensor::reading(){
	 int rawValue = analogRead(_pin);
     double Temp;
     Temp = log(((10240000/rawValue) - 10000));
     Temp = 1 / (0.001129148 + (0.000234125 + (0.0000000876741 * Temp * Temp ))* Temp ); //Steinhart-Hart equation
     Temp = Temp - 273.15;              // Kelvin to Celsius
     return Temp;
}