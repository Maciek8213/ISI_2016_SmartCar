package com.maciek.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.microedition.io.StreamConnection;

public class Watek_Odczyt implements Runnable
{
    StreamConnection polaczenie = null;
    MoveManagement moveManage;   
    private static String zmienna = "";
    
    Watek_Odczyt(StreamConnection connection) 
    {
        polaczenie=connection;
        moveManage = new MoveManagement();
    }
    
    @Override
    public void run() 
    {
        try
        {
            System.out.println("Watek_Nasluchuje...");
            BufferedReader bReader=new BufferedReader(new InputStreamReader(polaczenie.openInputStream()));
            
            while(true)
            {
                String lineRead=bReader.readLine();
                System.out.println(lineRead+" A ZMIENNA TERAZ "+zmienna);
                if (!lineRead.isEmpty() && !lineRead.equals(zmienna))
                { 
                    zmienna = lineRead;
                    System.out.println(zmienna);
                    moveManage.zapal(lineRead);
                }
            }
        }
        catch(Exception e)
        {
                System.out.println("Watek sie wysypal soreczki :(");
        }
    
    }
}
