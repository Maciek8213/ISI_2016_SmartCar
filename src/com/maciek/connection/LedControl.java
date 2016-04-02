package com.maciek.connection;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LedControl {
        // creating the pin with parameter PinState.HIGH
        // will instantly power up the pin
    GpioController gpio = GpioFactory.getInstance();
    GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.HIGH);
	
    public LedControl() {
    }
    
    public void zapal()
    {
        pin.high();
    }
    
    public  void zgas()
    {
        pin.low();
    }

    private void odblokuj_drzwi() {
       pin.high();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LedControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        pin.low();
        System.out.println("Drzwi odblokowane");
      //  gpio.shutdown();
    }

}