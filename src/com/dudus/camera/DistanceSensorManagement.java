package com.dudus.camera;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import static com.pi4j.wiringpi.Gpio.wiringPiSetup;

public class DistanceSensorManagement
{   

    public DistanceSensorManagement()  
    {
            this.manage();
    }

    private void manage() 
    {
        wiringPiSetup();
        final GpioController gpio = GpioFactory.getInstance();

        GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, 
            "Sensor Trigger", PinState.LOW);

        GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, 
            "Sensor Result", PinPullResistance.PULL_DOWN);

        DistanceSensor rangesensor = new DistanceSensor(sensor_trigger, sensor_result);

        do 
        {
            double distance = rangesensor.getRange();

            System.out.println("RangeSensorresult =" + distance + "cm");
            
            if(distance < 100)
            {
                new Thread( () -> { new CaptureImage(); });
            }
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