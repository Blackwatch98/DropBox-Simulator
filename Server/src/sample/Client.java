package sample;

public class Client {
    String userName;
    String path;
    String operation;

    Client()
    {
        this.userName = "Unknown";
        this.operation = "Connected";
        this.path="Unknown";
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
