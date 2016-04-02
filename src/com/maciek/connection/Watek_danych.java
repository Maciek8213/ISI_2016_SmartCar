/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maciek.connection;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author cos
 */
public class Watek_danych implements Runnable{
StreamConnection polaczenie=null;
LedControl led;
String ostatnia;   
Watek_danych(StreamConnection connection) {
        
        polaczenie=connection;
        led = new LedControl();
   
    }
    
    @Override
    public void run() {
       try{
        
        int p=0;
        BufferedReader bReader=new BufferedReader(new InputStreamReader(polaczenie.openInputStream()));
        while(true)
        {
        try{
        if(p==0)    
            System.out.println("Watek rozpoczyna prace");
        p++;
        String lineRead=bReader.readLine();
        if (lineRead.equals("g") || lineRead.equals("1"))
        { 
            led.zapal();    
        }else
            led.zgas();  
        System.out.print(lineRead);
            }catch(Exception e)
            {
                Thread.sleep(1000);
              //  System.out.println(""e.getMessage());
              //  break;
            }
        }
        
    }
       catch(Exception e)
            {
                System.out.println("Watek sie wysypal soreczki :(");
            }
    
    }
    }
