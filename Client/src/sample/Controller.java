package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Controller  implements Initializable
{
    @FXML
    private TextField username;

    @FXML
    private TextField path;

    @FXML
    private Button button1;

    @FXML
    private ListView localfiles;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private Label stats;

    @FXML
    private ListView<String> availableUsers;



    @FXML
    private void buttonclick(ActionEvent e) throws IllegalStateException
    {
        File file = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Client\\Images\\reklama.png");
        Image image = new Image(file.toURI().toString());
        image1.setImage(image);

        file = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Client\\Images\\reklama.png");
        image = new Image(file.toURI().toString());
        image2.setImage(image);

        String path1 = path.getText();
        button1.setDisable(true);
        Client k1 = new Client(username.getText(), path1, new ArrayList<>());
        new FilesScannerThread(path1, localfiles, k1).start();
        Connection connect = new Connection(k1,stats, availableUsers);
        connect.main();
    }

    public void keyReleaseProperty()
    {
            String un = username.getText();
            String pa = path.getText();
            boolean isDisabled = (un.isEmpty() || pa.isEmpty());
            button1.setDisable(isDisabled);
    }

    public void initialize(URL location, ResourceBundle resources)
    {
        button1.setDisable(true);
    }


}
