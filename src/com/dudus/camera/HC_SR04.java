package com.dudus.camera;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import java.text.DecimalFormat;
import java.text.Format;

public class HC_SR04
{
    private final Format DF22 = new DecimalFormat("#0.00");
    private final Format DF_N = new DecimalFormat("#.##########################");
    private GpioPinDigitalOutput trigPin;
    private GpioPinDigitalInput  echoPin;
    private String id;
    private final double SOUND_SPEED = 34029;  // 34300;         // in cm, 340.29 m/s
    private final double DIST_FACT   = SOUND_SPEED / 2; // round trip
    private short blad = 0; 
    private int MIN_DIST = 40;
    private boolean verbose = false;
    private final long BILLION      = (long)10E9;
    private final int TEN_MICRO_SEC = 10 * 1000; // In Nano secs
    
    public HC_SR04(GpioPinDigitalOutput trigger, GpioPinDigitalInput result_pin, String id) throws InterruptedException
    {
        this.id = id;
        this.trigPin = trigger;
        this.echoPin = result_pin;
        this.manage();
    }
    
    public  void manage() throws InterruptedException
    {
        verbose = "true".equals(System.getProperty("verbose", "false"));

        Thread.sleep(2000);

        boolean go = true;

        
        while (go)
        { 
            trigPin.low();
            try 
            { 
                Thread.sleep(500); 
            } 
            catch (Exception ex) 
            { 
                ex.printStackTrace(); 
            } 

            if (echoPin.isHigh())
                  System.out.println(">>> !! Before sending signal, echo PIN is " + (echoPin.isHigh() ? "High" : "Low"));

            trigPin.high();

            try 
            { 
                Thread.sleep(0, TEN_MICRO_SEC); 
            } 
            catch (Exception ex) 
            { 
                ex.printStackTrace(); 
            } 

            trigPin.low();

            while (echoPin.isLow());
            long start = System.nanoTime();

            while (echoPin.isHigh());
            long end   = System.nanoTime();

            if (end > start)
            {
                blad=0;
                double pulseDuration = (double)(end - start) / (double)BILLION; // in seconds

                double distance = pulseDuration * DIST_FACT  ;
                double odleglosc = distance*10; //// kalibracja

                if (distance < 21) // Less than 10 meters
                {   
                  System.out.println(id +" "+ odleglosc + " cm. (" + distance + "), Duration:" + (end - start) + " nanoS"); // + " (" + pulseDuration + " = " + end + " - " + start + ")");
                }
                else
                {
                    blad++; 

                    if(blad != 1)  
                        System.out.println("   >>> Too far:" + DF22.format(distance) + " cm.");
                }
                
                if(distance > 0 && odleglosc < MIN_DIST)
                {
                    System.out.println("Wbilem tutaj bo distance = "+distance);
                    String lewy = "lewy";
                    new Thread( () -> { new ServoController(lewy); }).start();
                  //  Thread.sleep(2000);
                }
                
                
                if(!(distance > 0 && distance < MIN_DIST))
                {
                    if (distance < 0 && verbose)
                        System.out.println("Dist:" + distance + ", start:" + start + ", end:" + end);
                    try 
                    { 
                        Thread.sleep(1000L); 
                    } 
                    catch (Exception ex) {}
                }
            }
            else
            {
                if (verbose)
                    System.out.println("Hiccup! start:" + start + ", end:" + end);
                try 
                { 
                    Thread.sleep(500L); 
                } 
                catch (Exception ex) {}
            }
        }
        trigPin.low(); 
    }
}