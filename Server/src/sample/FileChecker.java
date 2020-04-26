package sample;

import java.io.File;

public class FileChecker {

    private String diskName;

    FileChecker(String diskName)
    {
        this.diskName = diskName;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public String[] getDirectoryNames() {
        File file = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\");
        String[] fileList = file.list();
        for (String name : fileList);
        return fileList;
    }

    public String[] getFileNames() {
        File file = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\"+diskName);
        String[] fileList = file.list();
        for (String name : fileList);
        return fileList;
    }

    public Integer countFilesInDirectory()
    {
        File file = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\"+this.diskName);
        Integer counter = 0;
        String[] fileList = file.list();
        for (String name : fileList)
        counter++;
        return counter;
    }
}
