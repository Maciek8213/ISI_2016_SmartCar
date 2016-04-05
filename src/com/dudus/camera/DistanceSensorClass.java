package com.dudus.camera;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class DistanceSensorClass 
{
    GpioPinDigitalOutput firepulse;
    GpioPinDigitalInput result_pin;

    DistanceSensorClass(GpioPinDigitalOutput trigger, GpioPinDigitalInput result_pin) 
    {
        this.firepulse = trigger;
        this.result_pin = result_pin;
    }

    public double getRange() 
    {
        System.out.println("Uruchomiono triggera");

        long start = 0;

        try 
        {
            firepulse.high();
            Thread.sleep(10);
            firepulse.low();

            while (result_pin.isLow()) 
            { 
                start = System.nanoTime();
            }

            while (result_pin.isHigh()) {} 

            return ((System.nanoTime() - start) / 58000);
            
            } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return -1;
    }
}