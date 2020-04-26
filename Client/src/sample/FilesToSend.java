package sample;

import java.util.ArrayList;
import java.util.List;

public class FilesToSend
{

    ArrayList <String> ClientFilesList;
    ArrayList <String> ServerFilesList;

    FilesToSend(ArrayList ClientFiles, ArrayList ServerFiles)
    {
        this.ClientFilesList = ClientFiles;
        this.ServerFilesList = ServerFiles;
    }

    ArrayList <String> getFilestToSend()
    {
        ArrayList <String> FilesToSend = new ArrayList<>(this.ClientFilesList);
        FilesToSend.removeAll(this.ServerFilesList);
        return FilesToSend;
    }

    ArrayList <String> getFilestToDownload()
    {
        ArrayList <String> FilesToDownload = new ArrayList<>(this.ServerFilesList);
        FilesToDownload.removeAll(this.ClientFilesList);
        return FilesToDownload;
    }

    void listDisplay (ArrayList<String> lista)
    {
        for(int i = 0; i < lista.size(); i++)
            System.out.print(lista.get(i));
        System.out.print("\n");
    }






}
