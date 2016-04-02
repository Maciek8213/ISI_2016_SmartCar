package com.dudus.camera;
import java.io.IOException;
import java.text.DecimalFormat;

import com.pi4j.component.sensor.DistanceSensorChangeEvent;
import com.pi4j.component.sensor.DistanceSensorListener;
import com.pi4j.component.sensor.impl.DistanceSensorComponent;
import com.pi4j.gpio.extension.ads.ADS1015GpioProvider;
import com.pi4j.gpio.extension.ads.ADS1015Pin;
import com.pi4j.gpio.extension.ads.ADS1x15GpioProvider.ProgrammableGainAmplifierValue;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.i2c.I2CBus;

public class DistanceSensorClass {
    
    
    public static void main(String args[]) throws InterruptedException, IOException {
        
        System.out.println("<--Pi4J--> ADS1015 Distance Sensor Example ... started.");

        // number formatters
        final DecimalFormat df = new DecimalFormat("#.##");
        final DecimalFormat pdf = new DecimalFormat("###.#");
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        final ADS1015GpioProvider gpioProvider = new ADS1015GpioProvider(I2CBus.BUS_1, ADS1015GpioProvider.ADS1015_ADDRESS_0x48);
        
        final GpioPinAnalogInput distanceSensorPin = gpio.provisionAnalogInputPin(gpioProvider, ADS1015Pin.INPUT_A0, "DistanceSensor-A0");
        
        gpioProvider.setProgrammableGainAmplifier(ProgrammableGainAmplifierValue.PGA_4_096V, ADS1015Pin.ALL);
                
        gpioProvider.setEventThreshold(150, ADS1015Pin.ALL);

        gpioProvider.setMonitorInterval(100);

        DistanceSensorComponent distanceSensor = new DistanceSensorComponent(distanceSensorPin);
        
        distanceSensor.addCalibrationCoordinate(21600, 13);
        distanceSensor.addCalibrationCoordinate(21500, 14);
        distanceSensor.addCalibrationCoordinate(21400, 15);
        distanceSensor.addCalibrationCoordinate(21200, 16);
        distanceSensor.addCalibrationCoordinate(21050, 17);
        distanceSensor.addCalibrationCoordinate(20900, 18); 
        distanceSensor.addCalibrationCoordinate(20500, 19);
        distanceSensor.addCalibrationCoordinate(20000, 20); 
        distanceSensor.addCalibrationCoordinate(15000, 30);  
        distanceSensor.addCalibrationCoordinate(12000, 40); 
        distanceSensor.addCalibrationCoordinate(9200,  50); 
        distanceSensor.addCalibrationCoordinate(8200,  60); 
        distanceSensor.addCalibrationCoordinate(6200,  70); 
        distanceSensor.addCalibrationCoordinate(4200,  80); 

        distanceSensor.addListener(new DistanceSensorListener()
        {
            @Override
            public void onDistanceChange(DistanceSensorChangeEvent event)
            {
                double value = event.getRawValue();

                double distance =  event.getDistance();
                
                double percent =  ((value * 100) / ADS1015GpioProvider.ADS1015_RANGE_MAX_VALUE);
                
                double voltage = gpioProvider.getProgrammableGainAmplifier(distanceSensorPin).getVoltage() * (percent/100);

                System.out.print("\r DISTANCE=" + df.format(distance) + "cm : VOLTS=" + df.format(voltage) + "  | PERCENT=" + pdf.format(percent) + "% | RAW=" + value + "       ");
            }
        });
        
        for (int count = 0; count < 600; count++) {
            Thread.sleep(1000);
        }

        gpio.shutdown();
        System.out.print("");
    }
}