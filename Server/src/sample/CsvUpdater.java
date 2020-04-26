package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvUpdater
{
    String owner;
    String filename;
    String path;

    CsvUpdater(String filename, String owner, String path)
    {
        this.filename=filename;
        this.owner=owner;
        this.path=path;
    }
    void update()
    {
        File f = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\XxDanexX\\Dane.csv");
        try
        {
            FileWriter pw = new FileWriter(f,true);
            PrintWriter pw2 = new PrintWriter(pw);
            pw2.println(filename+","+owner+','+path);
            pw.close();
        } catch (Exception e) {
            System.out.println("Ups");
        }
    }
}
