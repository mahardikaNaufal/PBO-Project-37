import java.util.ArrayList;

import model.Transaction;
import model.User;
import repository.TransactionDB;
import repository.UserDB;
import view.LoginScreen;

public class App {
    public App() {
        ArrayList<User> users = UserDB.getUsers();
        ArrayList<Transaction> transactions = TransactionDB.getTransactions();

        new LoginScreen(users, transactions);
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
