public class Message {
    public static int countMessages = 1;
    private final int id_message;
    private boolean isRead;
    private final String sender;
    private final String receiver;
    private final String body;

    public Message(String sender, String receiver, String body) {
        id_message = countMessages;
        this.isRead = false;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        countMessages++;
    }

    public int getId_message() {
        return id_message;
    }

    public String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }
}