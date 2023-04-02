//
// This code has been tested for arduino IDE ver 1.8.16. 
// Do not run on other versions unless the nano board is up to date. 
//

#include <FastLED.h> 

#define ledPin     9                     // PWM digital output pin
#define numLeds   256         // total # of LEDs in chain 
#define numLevel  3                  
#define input1 12  
#define input2 5
#define input3 7

struct colors{
int red;
int green;
int blue;
};

CRGB leds[numLeds];  // # of LEDS in strip

colors ColorLevel[numLevel] =         // fix numbers to purple, yellow, and black.
{
  {0,0,0}, //off
  {255, 191, 0}, //yellow
  {230,230,250} //purple
};

void InitSequence(){

  for(int i=0;i <numLeds; i++){
    leds[i] = CRGB(ColorLevel[i%(numLevel-1)].red, ColorLevel[i%(numLevel-1)].green, ColorLevel[i%(numLevel-1)].blue);
    FastLED.show();
    delay(100);
    }  
}
void LEDsOff(){
 // turn everything off
  int i; 
  for(i=0;i<numLeds;i++)
    {
    leds[i] = CRGB(0,0,0);
    }  
    FastLED.show();
}

void InitializeLEDs()
{
   // turn everything off
  LEDsOff();
  
  // Turn on in sequence
  InitSequence();
  
   // turn everything off
  LEDsOff();
}

 

void setup() {
  FastLED.setBrightness(255);
  FastLED.addLeds<WS2812, ledPin, GRB>(leds, numLeds ); //create LED array

  FastLED.clear(); 
  FastLED.show();   
  delay(500);
  fill_solid(leds, numLeds, CRGB(255, 0, 0));  // off
  FastLED.show();   
  delay(500);
  FastLED.show();   
  delay(500);
  fill_solid(leds, numLeds, CRGB(0, 255, 0));  // off
  FastLED.show();   
  delay(500);
  FastLED.show();   
  delay(500);
  fill_solid(leds, numLeds, CRGB(0, 0, 255));  // off
  FastLED.show();   
  delay(500);
  FastLED.clear(); 
  FastLED.show();   

  pinMode(input1, INPUT_PULLUP); 
  pinMode(input2, INPUT_PULLUP); 
  pinMode(input3, INPUT_PULLUP); 
   
  Serial.begin(9600);
}

void loop() {

  //high is OFF, low is ON
  int SW1 = digitalRead(input1);
  int SW2 = digitalRead(input2);
  int SW3 = digitalRead(input3);
  
  Serial.print ("SW1: " );
  Serial.print (SW1);
  Serial.print (" ,SW2: " );
  Serial.print (SW2);
  Serial.print (" ,SW3: " );
  Serial.println (SW3);

  
  if (digitalRead(input1) ==   HIGH && digitalRead(input2) == HIGH && digitalRead(input3) == HIGH) {
    fill_solid(leds, numLeds, CRGB(0, 0, 0));  // off
    Serial.println("case1");
  } else if (digitalRead(input1) == LOW && digitalRead(input2) == HIGH && digitalRead(input3) == HIGH ) {
    fill_solid(leds, numLeds, CRGB(255, 191, 0));  // yellow
    Serial.println("case2");
  } else if (digitalRead(input1) == HIGH && digitalRead(input2) == LOW && digitalRead(input3) == HIGH) {
    fill_solid(leds, numLeds, CRGB(230,0,250));  // purple ; unconnected one
    Serial.println("case3");
  } else if (digitalRead(input1) == HIGH && digitalRead(input2) ==   HIGH && digitalRead(input3) == LOW) {
    fill_solid(leds, numLeds, CRGB(30,30,255));  // blue
    Serial.println("case4");
  }else if (digitalRead(input1) == LOW && digitalRead(input2) == LOW && digitalRead(input3) == LOW) {
    fill_solid(leds, numLeds, CRGB(255 ,0 , 0));  // red
    Serial.println("case5");
  }else{
    fill_solid(leds, numLeds, CRGB(0 ,0 , 0));  // off
    Serial.println("case6");
    }
  FastLED.show();  // update the LED strip with the new colors

  delay (50);
}
