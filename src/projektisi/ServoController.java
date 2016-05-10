package projektisi;
import com.dudus.camera.CameraManagement;
import java.io.File;
import java.io.FileWriter;

class ServoController
{

    static final String GPIO_OUT = "out";
    static final String GPIO_ON = "1";
    static final String GPIO_OFF = "0";
    private CameraManagement takeShot;

    public ServoController()
    {
        this.takeShot = new CameraManagement();
        this.manage();
        
    }
    
    public void manage() 
    {
        String gpioChannel = "24";
        FileWriter[] commandChannels;
        
        try 
        {
            FileWriter unexportFile = new FileWriter("/sys/class/gpio/unexport");
            FileWriter exportFile = new FileWriter("/sys/class/gpio/export");

                System.out.println(gpioChannel);
    
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
            
            int period = 20;
            int repeatLoop = 25;
            int counter;
            
            while (true) 
            {
                
                for (counter=0; counter<repeatLoop; counter++) 
                {
                    
                    commandChannel.write(GPIO_ON);
                    commandChannel.flush();               
                
                    java.lang.Thread.sleep(0, 800000);
        
                    commandChannel.write(GPIO_OFF);
                    commandChannel.flush();
                
                    java.lang.Thread.sleep(period);
                }

                for (counter=0; counter<repeatLoop; counter++) 
                {
                    commandChannel.write(GPIO_ON);
                    commandChannel.flush();               
                
                    java.lang.Thread.sleep(2, 200000);

                    commandChannel.write(GPIO_OFF);
                    commandChannel.flush();
                
                    java.lang.Thread.sleep(period);
                }
              
                takeShot.takePicture();
                Thread.sleep(2000);
            }
            
        } 
        catch (Exception exception) 
        {
            exception.printStackTrace();
        }
    }
}
