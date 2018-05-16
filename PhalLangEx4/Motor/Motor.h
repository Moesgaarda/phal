#ifndef Motor_h
#define Motor_h

class Motor
{
    public:
	    Motor(int pin);
	    void on();
	    void off();
		bool status();
    private: 
    	int _pin;
};

#endif
