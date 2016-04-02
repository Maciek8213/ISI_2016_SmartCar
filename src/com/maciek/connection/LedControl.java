package com.maciek.connection;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LedControl {
    GpioController gpio = GpioFactory.getInstance();
        
        // creating the pin with parameter PinState.HIGH
        // will instantly power up the pin
    GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.HIGH);
	
    public LedControl() {
    	
        // get a handle to the GPIO controller
    	       
  
   System.out.println("light is: ON");
        pin.high();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LedControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // turn off GPIO 1
        pin.low();
        System.out.println("light is: OFF");
        // wait 1 second   
        // release the GPIO controller resources
      //  gpio.shutdown();
    }
    
    public void zapal()
    {
        pin.high();
    }
    
    public  void zgas()
    {
        pin.low();
    }

}