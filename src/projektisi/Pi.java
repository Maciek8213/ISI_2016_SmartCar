/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektisi;

import com.maciek.connection.Connection;
import java.io.IOException;

/**
 *
 * @author cos
 */
public class Pi {

    
    public static void main(String[] args) throws IOException {
       System.out.println("Projekt ISI");
       Connection polaczenie = new Connection();
       polaczenie.start();
    }
    
}
