package com.maciek.connection;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class MoveManagement
{  
    private final GpioController gpio;
    private final GpioPinDigitalOutput pin0;
    private final GpioPinDigitalOutput pin1;
    private final GpioPinDigitalOutput pin2;
    private final GpioPinDigitalOutput pin3;
	
    public MoveManagement()
    {
        gpio = GpioFactory.getInstance();
        pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Left_Wheel", PinState.LOW);
        pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Left_Wheel", PinState.LOW);
        pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Right_Wheel", PinState.LOW);
        pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Right_Wheel", PinState.LOW);
    }
    
    public void zapal(String move)
    {
        this.zgas();
        
        switch (move) {
            case "LEFT":
                pin0.high();
                break;
            case "RIGHT":
                pin2.high();
                break;
            case "FORWARD":
                pin2.high();
                pin0.high();
                break;
            case "BACK":
                pin1.high();
                pin3.high();
                break;
            case "STAY":
                break;
        }
    }
    
    public void zgas()
    {
        System.out.println("Gasze");
        pin1.low();
        pin2.low();
        pin0.low();
        pin3.low();
    }   
}