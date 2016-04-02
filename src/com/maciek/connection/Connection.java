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
    
    void informacje()
    {
    
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            System.out.println("Address: "+localDevice.getBluetoothAddress());
            System.out.println("Name: "+localDevice.getFriendlyName());
        } catch (BluetoothStateException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void start() throws IOException {
        Informacje_o_urzadzeniu();
        StreamConnection connection = this.server();
        Watek_danych maciek = new Watek_danych(connection);
        Informacje_polaczenie(connection);
        new Thread(maciek).start();
    }
    
    void Informacje_polaczenie(StreamConnection con) throws IOException
    {
        RemoteDevice dev = RemoteDevice.getRemoteDevice(con);
        System.out.println("Remote device address: "+dev.getBluetoothAddress());
        System.out.println("Remote device name: "+dev.getFriendlyName(true));
    }
    
    void Informacje_o_urzadzeniu() throws BluetoothStateException
    {
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println("Address: "+localDevice.getBluetoothAddress());
        System.out.println("Name: "+localDevice.getFriendlyName());
    }

    private StreamConnection server() throws IOException {
        UUID uuid = new UUID("1101", true);
        //Create the servicve url
        String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";
        //open server url
        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open(connectionString);
        //Wait for client connection
        System.out.println("\nServer Started. Waiting for clients to connect…");
        StreamConnection connection=streamConnNotifier.acceptAndOpen();// tu zaczyna prace i czeka na polczenie
        return connection;
    }
    
}
