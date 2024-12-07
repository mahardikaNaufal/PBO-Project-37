public class Transaction {
    private int transactionId; // primary key
    private String type;
    private int amount;
    private String date;
    private String bankName; // foreign key
    private int accountNumber; // foreign key

    public Transaction(String type, int amount, String date, String bankName, int accountNumber, int transactionId) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getBankName() {
        return bankName;
    }
}
