package model;

public class User {
    private String username;
    private String fullname;
    private int accountNumber; // primary
    private String password;
    private int balance;
    private String bankName; // foreign

    public User(int accountNumber, String password, String username, String fullname, String bankName, int balance) {
        this.username = username;
        this.fullname = fullname;
        this.accountNumber = accountNumber;
        this.password = password;
        this.bankName = bankName;
        this.balance = balance;
    }
    public String getBankName() {
        return bankName;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
