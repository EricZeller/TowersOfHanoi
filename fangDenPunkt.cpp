#include "mbed.h"

int count1 = 0;
float blinkrate = 0.5;

InterruptIn button1 (PA_1);
InterruptIn button3 (PB_0);

Ticker ticker;
Timeout timeout;
enum statusTyp {l1, l2, l3, l4, l5, l6, won};
statusTyp status;

DigitalOut led1 (PA_5);
DigitalOut led2 (PA_6);
DigitalOut led3 (PA_7);
DigitalOut led4 (PB_6);

DigitalOut buzzer (PB_3);

DigitalOut SerialDataInA(PA_9);
DigitalOut ShiftClock(PA_8);
DigitalOut LatchClock(PB_5);

void isrTicker();
void isrTimeout();

void shift(){
    ShiftClock = 0;
    ShiftClock = 1;
    ShiftClock = 0;
}
void clear(){
    SerialDataInA = 0;
    for(int i=0; i < 20; i++){ // 16
        ShiftClock = 1;
        ShiftClock = 0;
    }
}
void display(int index, int array[]){
    clear();
    for(int i=0; i < 8; i++){
        SerialDataInA = array[i]; 
        shift();
    }

    for(int i=0; i < 4; i++){
        shift();
    }
    if(index == 1){
         SerialDataInA = 0;
         shift();
         SerialDataInA = 0;
         shift();
         SerialDataInA = 0;
         shift();
         SerialDataInA = 1;
         shift();
    }
    if(index == 2){
         SerialDataInA = 0;
         shift();
         SerialDataInA = 0;
         shift();
         SerialDataInA = 1;
         shift();
         SerialDataInA = 0;
         shift();
    }
    if(index == 3){
         SerialDataInA = 0;
         shift();
         SerialDataInA = 1;
         shift();
         SerialDataInA = 0;
         shift();
         SerialDataInA = 0;
         shift();
    }
    if(index == 4){
         SerialDataInA = 1;
         shift();
         SerialDataInA = 0;
         shift();
         SerialDataInA = 0;
         shift();
         SerialDataInA = 0;
         shift();
    }
    LatchClock = 1;
    LatchClock = 0;
}
int sd_number_0[] = {1, 1, 0, 0, 0, 0, 0, 0};
int sd_number_1[] = {1, 1, 1, 1, 1, 0, 0, 1};
int sd_number_2[] = {1, 0, 1, 0, 0, 1, 0, 0};
int sd_number_3[] = {1, 0, 1, 1, 0, 0, 0, 0};
int sd_number_4[] = {1, 0, 0, 1, 1, 0, 0, 1};
int sd_number_5[] = {1, 0, 0, 1, 0, 0, 1, 0};
int sd_number_6[] = {1, 0, 0, 0, 0, 0, 1, 0};
int sd_number_7[] = {1, 1, 1, 1, 1, 0, 0, 0};
int sd_number_8[] = {1, 0, 0, 0, 0, 0, 0, 0};
int sd_number_9[] = {1, 0, 0, 1, 0, 0, 0, 0};



void isrTicker(){
    if (status == l1){
        led1 = 0;
        led2 = 1;
        led3 = 1;
        led4 = 1;
        status = l2;
        buzzer = 1;
    } else if (status == l2){
        led1 = 1;
        led2 = 0;
        led3 = 1;
        led4 = 1;
        status = l3;
    } else if (status == l3){
        led1 = 1;
        led2 = 1;
        led3 = 0;
        led4 = 1;
        status = l4;
    } else if (status == l4){
        led1 = 1;
        led2 = 1;
        led3 = 1;
        led4 = 0;
        status = l5;
    } else if (status == l5){
        led1 = 1;
        led2 = 1;
        led3 = 0;
        led4 = 1;
        status = l6;
    } else if (status == l6){
        led1 = 1;
        led2 = 0;
        led3 = 1;
        led4 = 1;
        status = l1;
    } else if (status == won){
        led1 = 0;
        led2 = 0;
        led3 = 0;
        led4 = 0;
        status = l1;
        count1++;
        
        switch(count1){
            case 0:
                led1 = 0;
                led2 = 0;
                led3 = 0;
                led4 = 0;               
                display(4, sd_number_0);
                break;
            case 1:
                display(4, sd_number_1);
                blinkrate = blinkrate - 0.04;
                break;
            case 2:
                display(4, sd_number_2);
                blinkrate = blinkrate - 0.04;
                break;
            case 3:
                display(4, sd_number_3);
                blinkrate = blinkrate - 0.04;
                break;
            case 4:
                display(4, sd_number_4);
                blinkrate = blinkrate - 0.04;
                break;
            case 5:
                display(4, sd_number_5);
                blinkrate = blinkrate - 0.04;
                break;
            case 6:
                display(4, sd_number_6);
                blinkrate = blinkrate - 0.04;
                break;
            case 7:
                display(4, sd_number_7);
                blinkrate = blinkrate - 0.04;
                break;
            case 8:
                display(4, sd_number_8);
                blinkrate = blinkrate - 0.04;
                break;
            case 9:
                display(4, sd_number_9);
                blinkrate = blinkrate - 0.04;
                break;
            case 10:
                count1 = 0;
                status = l1;
                display(4, sd_number_0);
                blinkrate = 0.5;
                ticker.attach(&isrTicker, blinkrate);    
        }
    }
    ticker.attach(&isrTicker, blinkrate);
}



void isrButton1() {
    if (led3 == 0){
        status = won;
    } else if (led3 != 0) {
        buzzer = 0;
        count1 = 0;
        status = l1;
        display(4, sd_number_0);
        blinkrate = 0.5;
        ticker.attach(&isrTicker, blinkrate);
    }
}

void isrButton3(){
    count1 = 0;
    status = l1;
    display(4, sd_number_0);
    blinkrate = 0.5;
    ticker.attach(&isrTicker, blinkrate);

}

int main() {
    buzzer = 1;
    status = l1;
    display(4, sd_number_0);

    button1.fall( &isrButton1 );
    button3.fall( &isrButton3 );

    ticker.attach(&isrTicker, blinkrate);

    while (true){}
}