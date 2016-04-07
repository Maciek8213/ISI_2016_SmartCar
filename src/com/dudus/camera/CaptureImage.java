package com.dudus.camera;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author f0rest
 */
public class CaptureImage 
{
    CaptureImage()
    {
        this.capture();
    }
    
    private void capture()
    {
        GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput capture_sensor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, 
                "Capture Sensor", PinState.LOW);
        
        try 
        {
            capture_sensor.high();
            Thread.sleep(1000);
            capture_sensor.low();
            gpio.shutdown();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(CaptureImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
