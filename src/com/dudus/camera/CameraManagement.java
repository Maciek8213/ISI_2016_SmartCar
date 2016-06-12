package com.dudus.camera;

import java.text.SimpleDateFormat;
import java.util.Date;
 
public class CameraManagement
{
    private Date date;
    private SimpleDateFormat dateFormat;
    private final int pictureTimeOut = 500;
    
    public CameraManagement() 
    {
        this.date = new Date();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        this.takePicture();
    }

    public void takePicture()
    {
        try
        {
            System.out.println("Dudu cyka focie");
            String command = "/opt/vc/bin/raspistill -n -bm -t 500 -w 1200 -h 700 -q 100 -e jpg -o /home/pi/RaspberryPhotos/"+dateFormat.format(date)+".jpg";
            Runtime.getRuntime().exec(command.toString());
            Thread.sleep(pictureTimeOut);
        }
        catch (Exception e)
        {
            System.exit(e.hashCode());
        }
   }
}