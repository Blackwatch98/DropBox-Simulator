package sample;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListenerThread extends Thread
{
    private final BufferedReader bufferedReader;

    public ClientListenerThread(BufferedReader bufferedReader)
    {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run()
    {
        try
        {
            String line = bufferedReader.readLine();

            while (line != null)
            {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e)
        {
            System.out.println("Blad We/Wy");
        }
    }
}
