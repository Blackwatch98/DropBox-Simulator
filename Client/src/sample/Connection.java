package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.*;
import java.net.Socket;

public class Connection {

    private static final String HOST = "localhost";
    private static final int PORT = 999;
    Client k1;

    @FXML
    private Label stats;

    @FXML
    private ListView<String> availableUsers;

    Connection(Client k1, Label stats, ListView<String>availableUsers)
    {
        this.k1 = k1;
        this.stats=stats;
        this.availableUsers=availableUsers;
    }


    public void main()
    {

        try
        {
            //TWORZYMY GNIAZDO DO KOMUNIKACJI
            Socket socket = new Socket(HOST, PORT);
            DataInputStream var1 = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream var2 = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));


            var2.writeUTF(k1.userName);
            var2.flush();
            var2.writeUTF(k1.localFolder);
            var2.flush();

            int names_num = var1.readInt();
            for(int i = 0; i < names_num; i++)
                availableUsers.getItems().add(var1.readUTF());


            stats.setText("Waiting...");
            new ClientThread(socket, this.k1, this.stats).start();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
