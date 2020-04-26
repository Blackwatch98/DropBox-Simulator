package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyFiles extends Thread
{

    String sourcePath;
    String destPath;
    String [] files;


    CopyFiles(String sourcePath, String destPath, String[] files)
    {
        this.sourcePath=sourcePath;
        this.destPath=destPath;
        this.files=files;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < files.length; i++)
        {
            sendFile( files[i]);
            System.out.println(files[i]);
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException e)
            {

            }
        }

    }

    private void sendFile(String filename)
    {

        try
        {
            File fd = new File(sourcePath +"\\"+filename);
            File fs = new File(destPath+"\\"+filename);
            FileInputStream fis = new FileInputStream(fd);
            FileOutputStream fos = new FileOutputStream(fs);

            int size = (int)fd.length();
            byte[] buffer = new byte[10*1024];

            int read = 0;
            int remaining = size;


            while((read = fis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0)
            {
                fos.write(buffer, 0, read);
                remaining -= read;

                System.out.println("Plik: " + filename + "Paczka: " + read);
            }


            System.out.println("Ok wyslalem");
        }
        catch(IOException e)
        {
            System.out.println("Nie podzialam");
        }
    }

}


