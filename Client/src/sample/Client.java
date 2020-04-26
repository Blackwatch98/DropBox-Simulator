package sample;

import java.util.ArrayList;

public class Client {

    String userName;
    String localFolder;
    ArrayList<String> filesList;

    Client(String userName, String localFolder, ArrayList<String> list)
    {
        this.userName = userName;
        this.localFolder = localFolder;
        this.filesList = list;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocalFolder() {
        return localFolder;
    }

    public void setLocalFolder(String localFolder) {
        this.localFolder = localFolder;
    }

    public ArrayList<String> getFilesList() {
        return filesList;
    }

    public void setFilesList(ArrayList<String> filesList) {
        this.filesList = filesList;
    }

    public ArrayList<String> stringToArrray(String[] text)
    {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < text.length; i++)
        {
            list.add(text[i]);
        }
        return list;
    }

}
