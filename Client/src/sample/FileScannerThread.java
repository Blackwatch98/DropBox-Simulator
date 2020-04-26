package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


class FilesScannerThread extends Thread
{
    String path;

    @FXML
    private ListView localfiles;

    private Client k1;

    FilesScannerThread(String path, ListView localfiles, Client k1)
    {
        this.path = path;
        this.localfiles = localfiles;
        this.k1 = k1;
    }

    @Override
    public void run()
    {
        while(true)
        {
            File file = new File(this.path);
            String[] fileList = file.list();
            for (String name : fileList)
                k1.filesList.add(name);
            Platform.runLater(()->{
            localfiles.getItems().clear();
            localfiles.getItems().addAll(fileList);
            });

            try
            {
                Thread.sleep(5000);
            }
            catch(InterruptedException e)
            {
                //
            }

            this.k1.filesList.clear();
        }

    }
}