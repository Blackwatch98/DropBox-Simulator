package sample;

import java.util.*;
import java.io.File;


public class DiskManager {

    private Vector<Disk> container = new Vector<>();

    DiskManager()
    {
        FileChecker checkDirectories = new FileChecker(null);
        String[] dirList = checkDirectories.getDirectoryNames();
        for(String directory:dirList)
        {
            Disk d = new Disk(directory, "None", 0);
            FileChecker checkFiles = new FileChecker(d.getDiskName());
            try
            {
                d.setFileCount(checkFiles.countFilesInDirectory());
            }
            catch(Exception e)
            {
                System.out.println("Error: " + e.getMessage());
            }

            container.add(d);
        }
    }

    public Disk getDiskAtIndex(Integer index)
    {
        return this.container.elementAt(index);
    }

    public String[] getFileNamesOfDisk(String diskName)
    {
        File file = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\"+diskName);
        String[] fileList = file.list();
        for (String name : fileList);
        return fileList;
    }

    public Integer countDisks()
    {
        File file = new File("C:\\Users\\Daniel\\Desktop\\Dropbox v6.0\\Server\\Dyski\\");
        String[] fileList = file.list();
        Integer counter = 0;
        for (String name : fileList)
        counter++;
        return counter;
    }

}
