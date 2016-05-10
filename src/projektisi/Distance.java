package projektisi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Distance {
   
   public int getDistance() {
       
       try {
           final GpioController gpio = GpioFactory.getInstance();
           
           final GpioPinDigitalOutput gpiotrigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Trigger", PinState.LOW);
           final GpioPinDigitalInput gpioecho = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, "Echo");
           
           double start = 0;
           double stop = 0;
           
           Thread.sleep(2000);
           
           gpiotrigger.high();
           Thread.sleep(10);
           gpiotrigger.low();
           
           while (gpioecho.isLow()) {
               start = System.currentTimeMillis();
           }
           
           while (gpioecho.isHigh()) {
               stop = System.currentTimeMillis();
           }
           
           return (int) Math.round(((stop - start) * 34300) / 2);
       } catch (InterruptedException ex) {
           Logger.getLogger(Distance.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return 2;
   }
}