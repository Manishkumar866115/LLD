package services;

import models.*;

import java.util.ArrayList;
import java.util.List;

class UserTransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransaction() {
        return transactions;
    }
}
