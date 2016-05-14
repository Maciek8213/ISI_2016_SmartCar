package projektisi;

import com.dudus.camera.*;
import com.maciek.connection.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cos
 */
public class Raspberry 
{
    private static String Adres_Clienta_BT="F895C77145CE"; // Adress z ktorym pi moze sie polaczyc
    private static Thread Aparat;   
    
    public static void main(String[] args) throws IOException 
    {
       System.out.println("Projekt ISI");
       Obsluga_aparatu_watek(); // to jest cześć sewcia odkomentuj sobie o zakomentuja moja to Ci smignie
       //nawiaz_polaczenie(Adres_Clienta_BT);   // Czesc maćka BT+ drzwi

    }

    private static void nawiaz_polaczenie(String Adres_Clienta_BT)
    {
        try 
        {
            Connection polaczenie = new Connection(Adres_Clienta_BT);
            polaczenie.start();
        } 
        catch (IOException ex) 
        {
            System.out.println("Nie mozna utworzyc servera");
            Logger.getLogger(Raspberry.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    private static void Obsluga_aparatu_watek() 
{
        Aparat = new Thread( () -> {
            try 
            {
                new HC_SR04_Manage();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Raspberry.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Aparat.start();
        /*Aparat = new Thread( () -> { new ServoController(); });
        Aparat.start();*/
       
    } 
}