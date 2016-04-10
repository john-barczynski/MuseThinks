// clave code
#include<Wire.h>

//visual output pin
int led = 4;
int id = 3;

void flash(int period, int pin)
{
	digitalWrite(pin, HIGH);
	delay(pin/2);
	digitalWrite(pin, LOW);
	delay(pin/2);
}

void setup()
{
	Wire.begin(id);
	Wire.onReceive(in);
	pinMode(led, OUTPUT);
}

void loop()
{
	delay(1000);
}

void in(int byteIn)
{
	int x = 0;
	while(Wire.available() > 0)
	{
		x = Wire.read();
	}
	int size;
	switch(x)
	{
	case 1: size = 1000;
		break;
	case 2: size = 2000;
		break;
	case 3: size = 3000;
		break;
	default:size = 0;
		break;	
	}
	if(size > 0)
	{
		for(int i = 0; i < (12000 / size); i++)
		{
			flash(size, led);
		}
	}
	else
	delay(12000);
}
