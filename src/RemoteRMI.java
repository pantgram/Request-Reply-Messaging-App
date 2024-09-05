import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class RemoteRMI extends UnicastRemoteObject implements RMI {
    private ArrayList<Account> accounts = new ArrayList<>();
    private String systemExit = "";

    public RemoteRMI() throws RemoteException {
        super();
    }

    public String getSystemExit() {
        return systemExit;
    }

    public void createAccount(String username) {
        // initializing SystemExit
        systemExit = "";
        // checking if username exists already
        if (!accounts.isEmpty()) {
            for (Account a : accounts) {
                if (username.equals(a.getUsername())) {
                    systemExit = "Sorry, the user already exists";
                    return;
                }
            }
        }
        //checking if username is valid
        if (username.matches("[_a-zA-Z0-9]+")) {
            // then generating unique  authToken
            Random rnd = new Random();
            int authToken = rnd.nextInt(9999 - 1000) + 1000;
            if (!accounts.isEmpty()) {
                boolean unique = true;
                while (true) {

                    for (Account a : accounts) {
                        if (a.getAuthToken() == authToken) {
                            unique = false;
                            break;
                        }
                    }
                    if (unique) {
                        break;
                    } else {
                        // auth token has 4 digits
                        authToken = rnd.nextInt(9999 - 1000) + 1000;
                        unique = true;
                    }
                }
            }
            //creating account and put it in the list
            Account a = new Account(username, authToken);
            accounts.add(a);
            systemExit = Integer.toString(authToken);
        } else
            systemExit = "Invalid Username";
    }

    public void showAccounts(int authToken) {
        if (authTokenValid(authToken)) {
            int i = 1;
            systemExit = "";
            for (Account a : accounts) {
                systemExit += i + ". " + a.getUsername() + "\n";
                i++;
            }
        }
    }

    public void sendMessage(int authToken, String recipient, String body) {
        if (authTokenValid(authToken)) {
            systemExit = "";
            //initializing recipients account us null
            Account recipientAc = null;
            for (Account a : accounts) {
                if (a.getUsername().equals(recipient)) {
                    //recipients account found
                    recipientAc = a;
                    break;
                }
            }
            if (recipientAc == null) {
                systemExit = "User does not exist";
            } else {
                //find sender
                String usernameSender = findUsername(authToken);
                //create message to be sent
                Message messageSent = new Message(usernameSender, recipient, body);
                //add message to messageBox
                accounts.get(accounts.indexOf(recipientAc)).getMessageBox().add(messageSent);
                systemExit = "OK";
            }


        }
    }

    public void showInbox(int authToken) {
        if (authTokenValid(authToken)) {
            systemExit = "";
            //find users messageBox
            ArrayList<Message> messageBox = new ArrayList<>();
            for (Account a : accounts) {
                if (a.getAuthToken() == authToken) {
                    messageBox = a.getMessageBox();
                    break;
                }
            }
            // SystemOut messageBox
            for (Message box : messageBox) {
                if (box.getIsRead()) {
                    systemExit += box.getId_message() + ". from: " + box.getSender() + "\n";
                } else
                    systemExit += box.getId_message() + ". from: " + box.getSender() + "*" + "\n";
            }
        }
    }

    public void readMessage(int authToken, int id_message) {
        systemExit = "";
        if (authTokenValid(authToken)) {
            Account user = null;
            //find users account
            for (Account a : accounts) {
                if (a.getAuthToken() == authToken) {
                    user = a;
                }
            }
            if (user != null) {
                //get users messageBox
                ArrayList<Message> messageBox = user.getMessageBox();
                //find given message
                Message message = findMessageValid(id_message, messageBox);
                //print message if it exists
                if (message != null) {
                    systemExit = "(" + message.getSender() + ")" + message.getBody();
                } else {
                    systemExit = "Message ID does not exist";
                }
            }
        }
    }


    public void deleteMessage(int authToken, int id_message) {
        systemExit = "";
        if (authTokenValid(authToken)) {
            //find users account
            Account user = null;
            for (Account a : accounts) {
                if (a.getAuthToken() == authToken) {
                    user = a;
                }
            }
            if (user != null) {
                //get users messageBox
                ArrayList<Message> messageBox = user.getMessageBox();
                //find given message
                Message message = findMessageValid(id_message, messageBox);
                if (message != null) {
                    //remove message if it exists
                    messageBox = removeFromMessageBox(messageBox, message);
                    //change the messageBox with the one that doesn't have the given message
                    user.setMessageBox(messageBox);
                    systemExit = "OK";
                } else
                    systemExit = "Message does not exist";
            }
        }
    }

    public ArrayList<Message> removeFromMessageBox(ArrayList<Message> messageBox, Message messageToRemove) {
        //find given message
        for (Message message : messageBox) {
            if (message == messageToRemove) {
                //remove it from messageBox
                messageBox.remove(message);
                break;
            }
        }
        return messageBox;
    }

    public String findUsername(int authToken) {
        String username = "";
        //find the authToken and get the username
        for (Account a : accounts) {
            if (a.getAuthToken() == authToken) {
                username = a.getUsername();
                break;
            }
        }
        return username;
    }

    public boolean authTokenValid(int authToken) {
        boolean valid = false;
        //find if authToken exists in any users account
        for (Account a : accounts) {
            if (a.getAuthToken() == authToken) {
                valid = true;
                break;
            }
        }
        //if it not exists
        if (!valid)
            systemExit = "Invalid Auth Token";
        return valid;
    }

    private Message findMessageValid(int id_message, ArrayList<Message> messageBox) {
//find message in messageBox and setIsRead true
        for (Message message : messageBox) {
            if (message.getId_message() == id_message) {
                message.setIsRead(true);
                return message;
            }
        }
        return null;
    }
}
