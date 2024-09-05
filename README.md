
# Request-Reply Messaging App

MessagingServer.java - This class implements the server of the app. It contains the main class, which accepts the args[] array as an argument, where args[0] is the port_number. Additionally, the main connects the server with the clients using RMI.

MessagingClient - This class implements the clients of the app. It also contains the main class, and accepts the args[] array as an argument, where args[0] is the IP, args[1] is the port_number, and the rest are related to the client’s requests. At the same time, the main connects the client with the server using RMI, and distinguishes cases of FN_ID(args[2]) for the client’s requests and calls the appropriate methods from the RemoteRMI class.

Message - The Message class represents a user’s message. Its private fields are countMessages, id_message, isRead, sender, receiver, body. This class contains its constructor, and various setters and getters for its private fields.

Account - The Account class represents a user’s account. Its private fields are username, authToken, messageBox. This class contains its constructor, and various setters and getters for its private fields.

RMI - RMI is an interface. It extends Remote and contains the method signatures of RemoteRMI needed for the user’s requests.

RemoteRMI - This class extends UnicastRemoteObject and implements RMI. Its private fields are ArrayList<Account> accounts, which contains the user accounts, and systemExit, which is the message that will be displayed to the user through its getter. Additionally, the class includes methods that implement the user’s requests, which are createAccount(String username), showAccounts(int authToken), sendMessage(int authToken, String recipient, String body), showInbox(int authToken), readMessage(int authToken, int id_message), deleteMessage(int authToken, int id_message). To facilitate the implementation of the above, there are also helper methods removeFromMessageBox(ArrayList<Message> messageBox, Message messageToRemove), findUsername(int authToken), authTokenValid(int authToken), findMessageValid(int id_message, ArrayList<Message> messageBox).
