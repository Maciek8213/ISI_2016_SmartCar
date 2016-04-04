package com.maciek.connection;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LedControl {
    private static boolean Status_drzwi=false;     
    GpioController gpio = GpioFactory.getInstance();// will instantly power up the pin
    GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.LOW);// creating the pin with parameter PinState.HIGH
	
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

    public void odblokuj_drzwi() { // nalezy to doprecyzowac  musi chodzic w watku bo usypia 
        new Thread(new Runnable() {
        @Override
        public void run() {
        zapal();
        spij(1000);
        zgas();
        stan_drzwi_update();//wyswietla stan dzwi i aktualizuje  
            }
        }).start();
//  gpio.shutdown();
    }

    private void stan_drzwi_update() {
        if(Status_drzwi)
        {
            System.out.println("Drzwi zablokowane");
            Status_drzwi=false;
        }else
        {
            Status_drzwi=true;
            System.out.println("Drzwi odblokowane");
        }
    }

    private void spij(int i) {
       try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(LedControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}