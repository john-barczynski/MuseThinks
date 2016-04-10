// clave code
#include<Wire.h>

//visual output pin
int led = 4;
int id = 2;

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
	int length;
	switch(x)
	{
	case 1: length = 1000;
		break;
	case 2: length = 2000;
		break;
	case 3: length = 3000;
		break;
	default:length = 0;
		break;	
	}
	if(length > 0)
	{
		for(int i = 0; i < (12000 / length); i++)
		{
			flash(length, led);
		}
	}
	else
	delay(12000);
}
