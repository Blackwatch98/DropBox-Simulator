package sample;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ExchangeFile extends Thread {

    private String filename;
    private Socket socket;
    private String path;
    private String task;


    ExchangeFile(String filename, String path, Socket socket, String task)
    {
        this.filename=filename;
        this.path=path;
        this.socket=socket;
        this.task=task;
    }

    @Override
    public void run()
    {
        try
        {
            DataInputStream var1 = new DataInputStream(this.socket.getInputStream());
            DataOutputStream var2 = new DataOutputStream(this.socket.getOutputStream());
            if(this.task == "send")
            {
                sendFile(this.filename,this.path, var2);
            }
            else if(this.task == "download")
            {
                saveFile(this.filename, this.path, var1);
            }
        }
        catch(IOException e) {
            //
        }


    }

    private void sendFile(String filename, String path, DataOutputStream var2)
    {

        try
        {
            File f = new File(path+"\\"+filename);
            FileInputStream fis = new FileInputStream(f);
            int size = (int)f.length();
            var2.writeInt(size);
            var2.flush();
            byte[] buffer = new byte[32*1024];

            int read = 0;
            int totalRead = 0;
            int remaining = size;


            while((read = fis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                var2.write(buffer, 0, read);
                remaining -= read;
                var2.writeUTF(filename);
                System.out.println("Plik: " + filename + "Paczka: " + read);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(0, 500));
                } catch (InterruptedException e) {
                    System.out.println("Nie podzialam");
                }
            }

            System.out.println("Ok wyslalem");
        }
        catch(IOException e)
        {
            System.out.println("Nie podzialam");
        }
    }

    private void saveFile(String filename, String path, DataInputStream var1)
    {
        try
        {
            int file_size = var1.readInt();
            System.out.println("Rozmiar pliku: " + file_size);

            byte[] buffer = new byte[32 * 1024];

            int read = 0;
            int remaining = file_size;
            int offset = 0;

            while((read = var1.read(buffer, 0, Math.min(buffer.length, remaining))) > 0)
            {
                remaining -= read;
                String new_filename = var1.readUTF();
                File f = new File(path + "\\" + new_filename);
                FileOutputStream fos = new FileOutputStream(f, true);
                System.out.println("Tu zapisuje: " + path + "\\" + new_filename);
                System.out.println("Przeczytalem: "+read);
                System.out.println("Offset: "+offset);
                fos.write(buffer, 0, read);
                offset+=read;
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(0, 500));
                } catch (InterruptedException e) {
                    System.out.println("Nie podzialam");
                }
            }
            //System.out.println("Zapisalem plik: " + filename);
        } catch (IOException e) {
            System.out.println("Nie podzialam");
        }

    }

}
