package com.dudus.camera;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class DistanceSensorClass {

  GpioPinDigitalOutput firepulse;
  GpioPinDigitalInput result_pin;

  DistanceSensorClass(GpioPinDigitalOutput trigger, GpioPinDigitalInput result_pin) {
    this.firepulse = trigger;
    this.result_pin = result_pin;
  }

  /**
   * Trigger the Range Sensor and return the result
   */
  public double getRange() {
    System.out.println("Range Sensor Triggered");

    long start = 0;
    long diff = 0;

    try {
      firepulse.high();
      Thread.sleep(10);
      firepulse.low();
    System.out.println("pp");  
      while (result_pin.isLow()) { System.out.println("wpl");
        start = System.nanoTime();
      }
 System.out.println("ppl");
      while (result_pin.isHigh()) { System.out.println("wph");
      } System.out.println("pph");

      diff = (System.nanoTime() - start) / 58000;

      return diff;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return -1;
  }
}