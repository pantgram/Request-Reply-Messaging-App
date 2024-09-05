import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class MessagingClient {
    public static void main(String[] args) {
        RMI rmi = null;
        try {
            // get reference for remote object
            Registry rmiRegistry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
            rmi = (RMI) rmiRegistry.lookup("RMI");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        if (rmi != null) {
            try {
// switching methods
                switch (args[2]) {
                    case "1":
                        rmi.createAccount(args[3]);
                        break;
                    case "2":
                        rmi.showAccounts(Integer.parseInt(args[3]));
                        break;
                    case "3":
                        // creating the body from args
                        StringBuilder body = new StringBuilder();
                        for (int i = 5; i < args.length; i++) {
                            body.append(args[i]);
                            body.append(" ");
                        }
                        rmi.sendMessage(Integer.parseInt(args[3]), args[4], body.toString());
                        break;
                    case "4":
                        rmi.showInbox(Integer.parseInt(args[3]));
                        break;
                    case "5":
                        rmi.readMessage(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                        break;
                    case "6":
                        rmi.deleteMessage(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                        break;
                }
                System.out.println(rmi.getSystemExit());
            } catch (RemoteException e) {
                System.out.println("An error occurred: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
    }
}
