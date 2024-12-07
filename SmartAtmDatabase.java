import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SmartAtmDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/smartatm";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public void insertUser(int accountNumber, String fullname, String username, String password, String bankName, int balance) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement()) {

            String sql = "INSERT INTO user (account_number, fullname, username, password, bank_name, balance) " + "VALUES (" + accountNumber + ", '" + fullname + "', '" + username + "', '" + password + "', '" + bankName + "', " + balance + ")";
            stmt.executeUpdate(sql);

            System.out.println("User inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTransaction(String type, int amount, String date, String bankName, int accountNumber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement()) {
    
            String sql = "INSERT INTO transaction (type, amount, date, bank_name, account_number) " + "VALUES ('" + type + "', " + amount + ", '" + date + "', '" + bankName + "', " + accountNumber + ")";
            stmt.executeUpdate(sql);
    
            System.out.println("Transaction inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public User getUser(int accountNumber) {
        User user = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE account_number = " + accountNumber)) {

            if (rs.next()) {
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String password = rs.getString("password");
                String bankName = rs.getString("bank_name");
                int balance = rs.getInt("balance");

                user = new User(accountNumber, password, username, fullname, bankName, balance);
            } else {
                user = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user")) {

            while (rs.next()) {
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                int accountNumber = rs.getInt("account_number");
                String password = rs.getString("password");
                String bankName = rs.getString("bank_name");
                int balance = rs.getInt("balance");

                users.add(new User(accountNumber, password, username, fullname, bankName, balance));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(User user) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE user SET " +
                "username = '" + user.getUsername() + "', " +
                "fullname = '" + user.getFullname() + "', " +
                "account_number = " + user.getAccountNumber() + ", " +
                "balance = " + user.getBalance() + ", " +
                "password = '" + user.getPassword() + "', " +
                "bank_name = '" + user.getBankName() + "' " +
                "WHERE account_number = " + user.getAccountNumber();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transaction")) {

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                String type = rs.getString("type");
                int ammount = rs.getInt("amount");
                String date = rs.getString("date");
                String bankName = rs.getString("bank_name");
                int accountNumber = rs.getInt("account_number");

                transactions.add(new Transaction(type, ammount, date, bankName, accountNumber, transactionId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
        // java.time.LocalDateTime.now().toString()
    }

    public ArrayList<Transaction> getTransaction(User user) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transaction WHERE account_number = " + user.getAccountNumber())) {

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                String type = rs.getString("type");
                int ammount = rs.getInt("amount");
                String date = rs.getString("date");
                String bankName = rs.getString("bank_name");
                int accountNumber = rs.getInt("account_number");

                transactions.add(new Transaction(type, ammount, date, bankName, accountNumber, transactionId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
        // java.time.LocalDateTime.now().toString()
    }
}
