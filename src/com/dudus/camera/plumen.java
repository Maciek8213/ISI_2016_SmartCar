package com.dudus.camera;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class plumen
{
    private final String GPIO_OUT = "out";
    private final String GPIO_ON = "1";
    private final String GPIO_OFF = "0";
    private CameraManagement takeShot;

    public plumen(String kierunek) throws InterruptedException
    {
        this.manage(kierunek);
        
    }
    
    public void setZero(int counter, int repeatLoop, int period, FileWriter commandChannel) throws IOException, InterruptedException
    {
        for (counter=0; counter<repeatLoop; counter++) 
        {            
            commandChannel.write(GPIO_ON);
            commandChannel.flush();               
                
            java.lang.Thread.sleep(1, 500000);
        
            commandChannel.write(GPIO_OFF);
            commandChannel.flush();
               
            java.lang.Thread.sleep(period);          
        }
    }
    
    public void manage(String kierunek) 
    {
        String gpioChannel = "25";
        FileWriter[] commandChannels;
        this.takeShot = new CameraManagement();
        
        try 
        {
            FileWriter unexportFile = new FileWriter("/sys/class/gpio/unexport");
            FileWriter exportFile = new FileWriter("/sys/class/gpio/export");

                File exportFileCheck = new File("/sys/class/gpio/gpio"+gpioChannel);
                if (exportFileCheck.exists()) 
                {
                    unexportFile.write(gpioChannel);
                    unexportFile.flush();
                }
            
                exportFile.write(gpioChannel);   
                exportFile.flush();

                FileWriter directionFile = new FileWriter("/sys/class/gpio/gpio" + gpioChannel + "/direction");
            
                directionFile.write(GPIO_OUT);
                directionFile.flush();
            
            FileWriter commandChannel = new FileWriter("/sys/class/gpio/gpio" + gpioChannel + "/value");
            
            int period = 15;
            int repeatLoop = 25;
            int counter = 0;
            
            if(kierunek.equals("lewy"))
            {
                System.out.println("Wchodze do lewego");
                for (counter=0; counter<repeatLoop; counter++) 
                {     
                    commandChannel.write(GPIO_ON);
                    commandChannel.flush();               
                
                    java.lang.Thread.sleep(1, 000000);
        
                    commandChannel.write(GPIO_OFF);
                    commandChannel.flush();
               
                    java.lang.Thread.sleep(period);   
                }
                
                takeShot.takePicture();
                java.lang.Thread.sleep(1000);
                setZero(counter, repeatLoop, period, commandChannel);
            }
            else if(kierunek.equals("prawy"))
            {
                
                System.out.println("Wchodze do prawego");
                for (counter=0; counter<repeatLoop; counter++) 
                {
                    commandChannel.write(GPIO_ON);
                    commandChannel.flush();               
                
                    java.lang.Thread.sleep(2, 500000);

                    commandChannel.write(GPIO_OFF);
                    commandChannel.flush();
                
                   java.lang.Thread.sleep(period);
                }
                takeShot.takePicture();
                java.lang.Thread.sleep(1000);
                setZero(counter, repeatLoop, period, commandChannel);
            }            
            
        } 
        catch (Exception exception) 
        {
            exception.printStackTrace();
        }
    }
}
