package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread{

    Socket serverSocket;
    Client k1;

    @FXML
    private Label stats;

    ClientThread(Socket socket, Client k1, Label stats)
    {
        this.serverSocket = socket;
        this.k1 = k1;
        this.stats=stats;
    }

    @Override
    public void run()
    {
        try {
            DataInputStream var1 = new DataInputStream(this.serverSocket.getInputStream());
            DataOutputStream var2 = new DataOutputStream(this.serverSocket.getOutputStream());

            while (true) {


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Nie podzialam");
                }

                Platform.runLater(
                        () -> {
                            stats.setText("Checking files to send...");
                        }
                );

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Nie podzialam");
                }



                Integer num = var1.readInt();
                System.out.print("num = " + num);
                String[] lista = new String[num];
                System.out.println("Tyle plikow: " + num);

                for(int i = 0; i < num; i++)
                {
                    lista[i] = var1.readUTF();
                }

                FilesToSend files = new FilesToSend(k1.filesList, k1.stringToArrray(lista));
                ArrayList<String> result = files.getFilestToSend();
                files.listDisplay(result);


                var2.writeInt(result.size());   //tyle ma przyjąć
                var2.flush();



                for(int i = 0; i < result.size(); i++)
                {
                    Platform.runLater(
                            () -> {
                                stats.setText("Sending...");
                            }
                    );

                    System.out.println("To wyslalem: " + result.get(i));
                    ExchangeFile t = new ExchangeFile(result.get(i), k1.localFolder, this.serverSocket, "send");
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        System.out.println("Nie podzialam");
                    }

                }

                Platform.runLater(
                        () -> {
                            stats.setText("Checking files to download...");
                        }
                );


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Nie podzialam");
                }


                System.out.println("A tych nie mam: ");
                result.clear();
                result = files.getFilestToDownload();
                files.listDisplay(result);

                var2.writeInt(result.size());
                var2.flush();



                for(int i = 0; i < result.size(); i++)
                {
                    Platform.runLater(
                            () -> {
                                stats.setText("Downloading...");
                            }
                    );

                    var2.writeUTF(result.get(i));
                    var2.flush();
                    System.out.println("To pobralem: " + result.get(i));
                    ExchangeFile t = new ExchangeFile(result.get(i), k1.localFolder, this.serverSocket, "download");
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        System.out.println("Nie podzialam");
                    }

                }

                Platform.runLater(
                        () -> {
                            stats.setText("Waiting...");
                        }
                );
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

}
