/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maciek.pin;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.PinDigitalStateChangeEvent;

/**
 *
 * @author cos
 */
public class PinControl {
    String pin_name;
    String state;
    String in_out;
  
    GpioPinDigitalOutput pin(GpioController gpio , String pin_name ,String status)
     {    
        if(status.equals("HIGH"))
        {
        String pin_add="GPIO_0";
        pin_name = pin_add + pin_name;
        GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.getPinByName(pin_name), 
               "Sensor Trigger", PinState.HIGH);
               return sensor_trigger;
        }
        else
        {
         String pin_add="GPIO_0";
        pin_name = pin_add + pin_name;
        GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.getPinByName(pin_name), 
               "Sensor Trigger", PinState.LOW);
               return sensor_trigger;
        }
       
     }
}
