public class User {
    private int accountNumber;
    private int pin;

    // Konstruktor untuk membuat objek User
    public User(int accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    // Getter untuk accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }

    // Getter untuk PIN
    public int getPin() {
        return pin;
    }

    // Override equals untuk membandingkan objek User berdasarkan nomor rekening dan PIN
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return accountNumber == user.accountNumber && pin == user.pin;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(accountNumber) * 31 + Integer.hashCode(pin);
    }
}