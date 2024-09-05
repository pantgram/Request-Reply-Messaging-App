import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessagingServer {
    public static void main(String[] args) {
        try {
            RemoteRMI rmi = new RemoteRMI();
            // create the RMI registry on port 5000
            Registry rmiRegistry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            rmiRegistry.rebind("RMI", rmi);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
