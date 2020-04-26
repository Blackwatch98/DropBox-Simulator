package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvReader {

    ArrayList<Client> clientsList = new ArrayList<>();

    CsvReader()
    {
        this.clientsList = new ArrayList<>();
    }

    public void getClientsList()
    {
        File f = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\XxDanexX\\Dane.csv");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String name;
            int l = 0;
            while ((name = bf.readLine()) != null)
            {
                l++;
                if(l == 1)
                    continue;

                Client k = new Client();

                String[] b = name.split(",");

                k.setUserName(b[1]);

                k.setPath(b[2]);

                clientsList.add(k);
            }
        } catch (Exception e) {
            System.out.println("Blad");
        }

    }

    String[] getUserNames()
    {
        String [] lista = new String[clientsList.size()];

        for(int i = 0; i < clientsList.size(); i++)
        {
            lista[i] = clientsList.get(i).userName;
        }

        String[] unique = Arrays.stream(lista).distinct().toArray(String[]::new);

        return unique;
    }

}
