package models;

import java.util.UUID;

public class User {
    UUID userId;
    String userName;
    Double balance;

    public User(String userName, Double balance) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.balance = balance;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Double getBalance() {
        return balance;
    }

    public void addBalance(Double amount) {
        this.balance += amount;
    }

    public boolean deductBalance(Double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
}
