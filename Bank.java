import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Describes the bank.
 */
public class Bank {

    private ArrayList<User> userCredentialsList;

    /**
     * Constructs a Bank with an empty list of users
     */
    public Bank() {
        userCredentialsList = new ArrayList<>();
    }

    public void readFile(String file) throws IOException {
        FileReader reader = new FileReader(file);
        try (Scanner in = new Scanner(reader)) {
            while (in.hasNext()) {
                int a_userNumber = in.nextInt();
                int a_pin = in.nextInt();
                User user = new User(a_userNumber, a_pin);
                this.addUser(user);
            }
        }
    }

    // Adds User to a list
    public void addUser(User user) {
        userCredentialsList.add(user);
    }

    // Determines if the user's credentials are valid
    public boolean search(User currentUser) {
        return userCredentialsList.contains(currentUser);
    }
}
