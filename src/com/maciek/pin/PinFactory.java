/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maciek.pin;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 *
 * @author cos
 */
public class PinFactory extends PinControl {
    
    String pin_name;
    String state;
    String in_out;
    
    GpioController gpio = GpioFactory.getInstance();

    public PinFactory(String in_out , String state , String pin_name ) 
    {
        super();
        
        this.pin_name=pin_name;
        this.state=state;
        this.in_out=in_out;
    }
    void high()
    {
        
    }
    void low()
    {
        
    }
    void maciek()
    {
        super.pin(gpio, pin_name, state);
    }
    
    
    
    
    
}
