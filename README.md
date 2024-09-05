
# Request-Reply Messaging App



Παντελεήμων Γραμματικοπουλος

ΑΕΜ 3631



**MessagingServer.java -**
 Η κλάση αυτη υλοποιεί τον server του app.
 Περιέχει την main κλάση , η οποία δέχεται σαν  όρισμα τον πίνακα args[], που έχει το port_number(args[0]).
 Επίσης η main συνδέει τον server με τους clients, χρησιμοποιόντας το RMI.

**MessagingClient -**
 Η κλάση αυτη υλοποιεί τον clients του app.
Περιέχει και αυτη την main,
και δέχεται σαν  όρισμα τον πίνακα args[], όπου το args[0] ειναι η ip , το args[1] είναι το port_number,
και τα υπόλοιπα σχετίζονται με τα requests  του client.
Παράλληλα, η main συνδέει τον client με τον server χρησιμοποιόντας RMI,
και διακρίνει περιπτώσεις του FN_ID(args[2]) για τα requests του client και καλεί τις κατάλληλες μεθόδους από την κλάση RemoteRMI.


**Message -**
Η κλάση Message αντικατροπτίζει ένα μήνυμα ενός χρήστη. Τα private πεδία του είναι τα countMessages,id_message,
isRead, sender, receiver, body. Η κλάση αυτή περιέχει τον κατασκευαστή της ,
 και διάφορους setters και getters για τα private πεδία της.

 **Account -**
Η κλάση Account αντικατροπτίζει τον λογαριασμό ενός χρήστη. Τα private πεδία του είναι τα username, authToken, messageBox.
 Η κλάση αυτή περιέχει τον κατασκευαστή της ,
 και διάφορους setters και getters για τα private πεδία της.


**RMI -** Η RMI είναι ένα interface.
 Κάνει extend την Remote και περιέχει τις υπογραφές των μεθόδων της RemoteRMI που χρειάζονται για τα requests του χρήστη.

**RemoteRMI -** Η κλάση αυτή κάνει extend την UnicastRemoteObject και κάνει implement την RMI.
Τα private πεδία της είναι το  ArrayList<Account> accounts που περιέχει τους λογαριασμούς των χρηστών
 και το systemExit που είναι το μήνυμα που θα εμφανιστεί στον χρήστη, μέσω τους getter του.
 Επίσης, η κλάση περιλαμβάνει τις μεθόδους που υλοποιούν τα αιτήματα του χρήστη,
 οι οποίες είναι η createAccount(String username), showAccounts(int authToken),
 sendMessage(int authToken, String recipient, String body), showInbox(int authToken), readMessage(int authToken, int id_message),
 deleteMessage(int authToken, int id_message).
 Παράλληλα για να διευκολυνθεί η υλοποίηση των παραπάνω υπάρχουν οι βοηθητικές μέθοδοι 
 removeFromMessageBox(ArrayList<Message> messageBox, Message messageToRemove),
 findUsername(int authToken), authTokenValid(int authToken), findMessageValid(int id_message, ArrayList<Message> messageBox)