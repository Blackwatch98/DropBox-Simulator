package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.*;
import java.net.Socket;


public class ServerThread extends Thread
{
    private final Socket socket;
    DiskManager manager;

    @FXML
    private TableView<Client> Table2;

    Client k1;

    public ServerThread(Socket socket, DiskManager manager, TableView<Client> table, Client k1)
    {
        this.socket = socket;
        this.manager = manager;
        this.Table2=table;
        this.k1 = k1;
    }

    @Override
    public void run()
    {

        try
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Nie podzialam");
            }

            DataInputStream var1 = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream var2 = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            while(true)
            {
                String[] fileNames = manager.getFileNamesOfDisk(manager.getDiskAtIndex(0).getDiskName());
                FileChecker f1 = new FileChecker(manager.getDiskAtIndex(0).getDiskName());
                Integer files_num = f1.countFilesInDirectory();

                var2.writeInt(files_num);
                var2.flush();

                //lista plikow z dysku1
                for(int i = 0 ; i < files_num; i++)
                {
                    var2.writeUTF(fileNames[i]);
                    var2.flush();
                }


                files_num = var1.readInt();
                System.out.println("Tyle musze przyjac: " + files_num);

                //odbieranie
                for(int i = 0; i < files_num; i++)
                {
                    ExchangeFile t = new ExchangeFile("none","C:\\Users\\Daniel\\Desktop\\Dropbox\\Server\\Dyski\\Dysk1", this.socket, "download", k1);
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        System.out.println("Nie podzialam");
                    }
                }

                //lista plikow do skopiowania
                FileChecker f = new FileChecker("Dysk1");
                String[] filesToSend = f.getFileNames();


                //kopiowanie do pozostałych dysków
                for(int i = 0; i < 4; i++)
                {
                    new CopyFiles("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\Dysk1",
                            "C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\Dysk"+(i+2),
                            filesToSend).start();
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Nie podzialam");
                }
                files_num = var1.readInt();

                //wysyłanie plikow do klienta
                for(int i = 0; i < files_num; i++)
                {
                    String name = var1.readUTF();
                    System.out.println("Przyjmowany plik: " + name);
                    ExchangeFile t = new ExchangeFile(name,"C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\Dysk1", this.socket, "send",k1);
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        System.out.println("Nie podzialam");
                    }

                }

            }


        }
        catch (IOException e)
        {

            Table2.getItems().remove(k1);
        }
    }



}