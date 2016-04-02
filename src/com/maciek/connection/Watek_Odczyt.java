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
public class Watek_Odczyt implements Runnable{
    StreamConnection polaczenie=null;
    LedControl led;   
    
    Watek_Odczyt(StreamConnection connection) {
        polaczenie=connection;
        led = new LedControl();// w zaleznosci co ma robic
        led.odblokuj_drzwi();
    }
    
    @Override
    public void run() {
        try
        {
            System.out.println("Watek_Nasluchuje...");
            BufferedReader bReader=new BufferedReader(new InputStreamReader(polaczenie.openInputStream()));
        while(true)
        {
         String lineRead=bReader.readLine();
            if (lineRead.equals("g")){ 
                led.zapal();    
            }else if(lineRead.equals("1"))
                led.zgas(); 
         System.out.print(lineRead);
        }
        }catch(Exception e)
        {
                System.out.println("Watek sie wysypal soreczki :(");
        }
    
    }
    }
