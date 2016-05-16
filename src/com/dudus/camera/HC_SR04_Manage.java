package com.dudus.camera;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HC_SR04_Manage
{
    private final GpioController gpio = GpioFactory.getInstance();
    
    private final GpioPinDigitalOutput sensor_trigger_0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, 
    "Sensor Trigger", PinState.LOW);

    private final GpioPinDigitalInput sensor_result_0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, 
    "Sensor Result", PinPullResistance.PULL_DOWN);
    
    private final GpioPinDigitalOutput sensor_trigger_1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, 
    "Sensor Trigger", PinState.LOW);

    private final GpioPinDigitalInput sensor_result_1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, 
    "Sensor Result", PinPullResistance.PULL_DOWN);
   
    private HC_SR04 sensor;
    private String id_0 = "lewy";
    private String id_1 = "prawy";
    
    public HC_SR04_Manage() throws InterruptedException
    {
        this.manage();
    }
    
    public void manage() throws InterruptedException
    {
        new Thread( () -> { try {
            new HC_SR04(sensor_trigger_0, sensor_result_0, id_0);
            } catch (InterruptedException ex) {
                Logger.getLogger(HC_SR04_Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
        
        new Thread( () -> { try {
            new HC_SR04(sensor_trigger_1, sensor_result_1, id_1);
            } catch (InterruptedException ex) {
                Logger.getLogger(HC_SR04_Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }
}