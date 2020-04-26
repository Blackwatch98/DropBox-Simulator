package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.io.File;


class FilesScannerThread extends Thread
{
    String path;

    @FXML
    private ListView localfiles;


    FilesScannerThread(String path, ListView localfiles)
    {
        this.path = path;
        this.localfiles = localfiles;
    }

    @Override
    public void run() {
        while (true) {
            File file = new File(this.path);
            String[] fileList = file.list();
            for (String name : fileList) ;
            Platform.runLater(() -> {
                localfiles.getItems().clear();
                localfiles.getItems().addAll(fileList);
            });

        }


    }

}