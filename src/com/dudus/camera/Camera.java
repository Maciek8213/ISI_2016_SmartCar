package com.dudus.camera;

/**
 * @author f0rest
 */
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
 
public class Camera
{
	private final GpioController gpio;
        private final GpioPinDigitalOutput zielony_pin;
        private final GpioPinDigitalOutput niebieski_pin;  

	private boolean capturing = false;
 
	public Camera() 
        {
            this.gpio = GpioFactory.getInstance();
            this.niebieski_pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "czerwony", PinState.LOW);
            this.zielony_pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "zielony", PinState.HIGH);
   
            this.start();          
	}
 
	public boolean isCapturing() 
        {
		return this.capturing;
	}
        
        public void setCapturing(boolean t) 
        {
		this.capturing = t;
	}
 
	public void toggleCapture() 
        {
		this.capturing = !this.capturing;
	}
 
	public GpioPinDigitalOutput getNiebieski() 
        {
		return this.niebieski_pin;
	}
 
	public GpioPinDigitalOutput getZielony() 
        {
		return this.zielony_pin;
	}
 
	public void start() 
        {
		getZielony().high();
		System.out.println("System started");
               
		while (true) 
                {
                        try 
                        {
                            Thread.sleep(1000);
                        //    new CameraManagement(this);
			}
                        catch (InterruptedException e) 
                        {
				e.printStackTrace();
			}
		}
	}
 
}