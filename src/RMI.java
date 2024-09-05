import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {
    String getSystemExit() throws RemoteException;

    void createAccount(String username) throws RemoteException;

    void showAccounts(int authToken) throws RemoteException;

    void sendMessage(int authToken, String recipient, String body) throws RemoteException;

    void showInbox(int authToken) throws RemoteException;

    void readMessage(int authToken, int id_message) throws RemoteException;

    void deleteMessage(int authToken, int id_message) throws RemoteException;
}