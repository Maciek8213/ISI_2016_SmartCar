package projektisi;

import com.maciek.connection.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Raspberry 
{
    private static String Adres_Clienta_BT="F895C77145CE"; // Adress z ktorym pi moze sie polaczyc
    private static Thread Aparat;   
    
    public static void main(String[] args) throws IOException 
    {
       System.out.println("Projekt ISI");
       nawiaz_polaczenie(Adres_Clienta_BT);   
    }

    private static void nawiaz_polaczenie(String Adres_Clienta_BT)
    {
        try 
        {
            System.out.println("nawiazuje polaczenie ");
            Connection polaczenie = new Connection(Adres_Clienta_BT);
            polaczenie.start();
        } 
        catch (IOException ex) 
        {
            System.out.println("Nie mozna utworzyc servera");
            Logger.getLogger(Raspberry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}