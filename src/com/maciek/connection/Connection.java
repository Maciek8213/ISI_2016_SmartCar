/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maciek.connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 *
 * @author cos
 */
public class Connection {
   String adres_urzadzenia="34:4D:F7:F7:97:17";
   Thread Odczyt_danych;
   Thread Zapis_danych;// do zakodzenia w zalezności od tego co będzie po strnonie androida
    void informacje()
    {
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            System.out.println("Addres: "+localDevice.getBluetoothAddress());
            System.out.println("Name: "+localDevice.getFriendlyName());
        } catch (BluetoothStateException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void start() throws IOException {
        Informacje_o_urzadzeniu();//wyswietl info  o pi
        StreamConnection connection = this.server(); // postaw serwer 
        if(polacz_z_odpowiednim_urzadzeniem(connection))
        {
            Odczyt_danych=new Thread(new Watek_Odczyt(connection));
            Odczyt_danych.start();
        }
        else
        {
            connection.close();
            start(); 
        }
    }
    
    private boolean polacz_z_odpowiednim_urzadzeniem(StreamConnection con) throws IOException
    {
        RemoteDevice dev = RemoteDevice.getRemoteDevice(con);
        if(dev.getBluetoothAddress().equals(adres_urzadzenia))
        {
            System.out.println("Nawiazano polaczenie z:");
            System.out.println("Adres urzadzeia: "+dev.getBluetoothAddress());
            System.out.println("Nazwa urzadzenia: "+dev.getFriendlyName(true));
            return true;
        }
        else
        {
            System.out.println("Nie prawidlowe urzadznie");
            return false;
        }
    }
    
    private void Informacje_o_urzadzeniu() throws BluetoothStateException
    {
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println("Addres serwera: "+localDevice.getBluetoothAddress());
        System.out.println("Nazwa serwera: "+localDevice.getFriendlyName());          
    }

    private StreamConnection server() throws IOException {
        UUID uuid = new UUID("1101", true);//Create the servicve url
        String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";//open server url
        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open(connectionString);//Wait for client connection
        System.out.println("\nServer Started. Czekam na klienta…");
        StreamConnection connection=streamConnNotifier.acceptAndOpen();// tu zaczyna prace i czeka na polczenie
        return connection;
    }
    
}
