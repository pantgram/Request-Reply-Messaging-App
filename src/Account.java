import java.util.ArrayList;

public class Account {
    private final String username;
    private final int authToken;
    private ArrayList<Message> messageBox;

    public Account(String username, int authToken) {
        this.username = username;
        this.authToken = authToken;
        this.messageBox = new ArrayList<>();
    }
    public int getAuthToken() {
        return authToken;
    }

    public ArrayList<Message> getMessageBox() {
        return messageBox;
    }

    public String getUsername() {
        return username;
    }

    public void setMessageBox(ArrayList<Message> messageBox) {
        this.messageBox = messageBox;
    }
}