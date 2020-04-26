package sample;

public class Disk
{
    private String diskName;
    private Integer fileCount;
    private String status;

    Disk(String diskName, String status, Integer fileCount)
    {
        this.diskName = diskName;
        this.status = status;
        this.fileCount = fileCount;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}