package com.dudus.camera;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class DistanceSensor 
{
    private GpioPinDigitalOutput firepulse;
    private GpioPinDigitalInput result_pin;
    private long start = 0;
    private long stop = 0;

    DistanceSensor(GpioPinDigitalOutput trigger, GpioPinDigitalInput result_pin) 
    {
        this.firepulse = trigger;
        this.result_pin = result_pin;
    }

    public int getRange() 
    {
        

        try 
        {
            firepulse.high();
            Thread.sleep(10);
            firepulse.low();

            while (result_pin.isLow()) 
            { 
                start = System.nanoTime();
            }

            while (result_pin.isHigh()) 
            {
                stop = System.nanoTime();
            } 
            if(((stop - start) / 58000) > 15000)
            {
                return 0;
            }
            else
                 return (int)((stop - start) / 58000);
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return -1;
    }
}