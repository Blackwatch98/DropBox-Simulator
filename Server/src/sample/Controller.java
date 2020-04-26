package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import javafx.event.*;



public class Controller implements Initializable {

    @FXML
    private TableView<Disk> Table1;
    @FXML
    private TableColumn Disknames;

    @FXML
    private TableColumn filecount;

    @FXML
    private TableColumn Operation1;

    @FXML
    private ListView Filenames;

    @FXML
    private TableView<Client> Table2;

    @FXML
    private TableColumn User;

    @FXML
    private TableColumn Operation;

    private static final int PORT = 999;

    public void initialize(URL location, ResourceBundle resources)
    {
        DiskManager manager = new DiskManager();

        Disknames.setCellValueFactory(new PropertyValueFactory<Disk, String>("diskName"));
        filecount.setCellValueFactory(new PropertyValueFactory<Disk, Integer>("fileCount"));
        Operation1.setCellValueFactory(new PropertyValueFactory<Disk, String>("status"));


        User.setCellValueFactory(new PropertyValueFactory<Client, String>("userName"));
        Operation.setCellValueFactory(new PropertyValueFactory<Client, String>("operation"));

        for(int i = 0; i < manager.countDisks(); i++)
        {
            //String diskName = manager.getDiskAtIndex(i).getDiskName();
            Table1.getItems().add(manager.getDiskAtIndex(i));
        }

        Table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                    FilesScannerThread t = new FilesScannerThread("C:\\Users\\Daniel\\Desktop\\Dropbox\\Server\\Dyski\\Dysk1\\"+newSelection.getDiskName(), Filenames);
                    String[] fileNames = manager.getFileNamesOfDisk(newSelection.getDiskName());
                    Filenames.getItems().clear();
                    Filenames.getItems().addAll(fileNames);
            }
        });


        Table1.refresh();

        try
        {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Start serwera. Oczekuje na polaczenia :)");

            new ConnectionAccepterThread(serverSocket, manager, Table2).start();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
