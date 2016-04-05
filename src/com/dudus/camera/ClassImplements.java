package com.dudus.camera;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import static com.pi4j.wiringpi.Gpio.wiringPiSetup;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassImplements 
{   

    
    
    public ClassImplements()  {
            this.start();
    }
    
    
    
    
    
    
    public static void  start() 
    {
        wiringPiSetup();
        final GpioController gpio = GpioFactory.getInstance();

        GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, 
            "Sensor Trigger", PinState.LOW);

        GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, 
            "Sensor Result", PinPullResistance.PULL_DOWN);

        DistanceSensorClass rangesensor = new DistanceSensorClass(sensor_trigger, sensor_result);

        do 
        {
            double distance = rangesensor.getRange();

            System.out.println("RangeSensorresult =" + distance + "cm");
            try 
            {
              Thread.sleep(1000);
            } 
            catch (InterruptedException e) 
            {
              e.printStackTrace();
            }
        } 
        while (true);
    }
}
