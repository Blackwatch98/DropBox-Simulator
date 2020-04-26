package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class AvailableUsers
{
    ArrayList<Client> clientsList;

    AvailableUsers()
    {
        this.clientsList = new ArrayList<Client>();

        File f = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\XxDanexX\\Dane.csv");
        try
        {


        } catch (Exception e) {
            System.out.println("Ups");
        }
    }

    public ArrayList<Client> getClientsList() {
        return clientsList;
    }





}
