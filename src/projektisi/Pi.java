/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektisi;

import com.maciek.connection.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;

/**
 *
 * @author cos
 */
public class Pi {

    
    public static void main(String[] args) throws IOException {
       System.out.println("Projekt ISI");
       nawiaz_polaczenie();   
    }

    private static void nawiaz_polaczenie() {
        try 
        {
            Connection polaczenie = new Connection();
            polaczenie.start();
        } 
        catch (IOException ex) 
        {
            System.out.println("Nie mozna utworzyc servera");
            Logger.getLogger(Pi.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
}
