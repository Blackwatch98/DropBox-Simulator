package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class ConnectionAccepterThread extends Thread
{
    private final ServerSocket serverSocket;
    private DiskManager diskManager;

    @FXML
    private TableView<Client> Table2;


    public ConnectionAccepterThread(ServerSocket serverSocket, DiskManager diskManager, TableView<Client>table)
    {
        this.serverSocket = serverSocket;
        this.diskManager = diskManager;
        this.Table2=table;
    }

    @Override
    public void run()
    {
        boolean acceptingConnections = true;

        while (acceptingConnections)
        {
            try
            {
                Socket socket = serverSocket.accept();
                Client k1 = new Client();
                DataInputStream var1 = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutputStream var2 = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

                k1.setUserName(var1.readUTF());
                k1.setPath(var1.readUTF());

                CsvReader cs = new CsvReader();

                cs.getClientsList();
                String[] names = cs.getUserNames();

                var2.writeInt(names.length);
                for(int i = 0; i < names.length; i++)
                    var2.writeUTF(names[i]);
                var2.flush();



                Table2.getItems().add(k1);

                new ServerThread(socket, diskManager, Table2, k1).start();

            }
            catch (SocketException e)
            {
                acceptingConnections = false;

            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}