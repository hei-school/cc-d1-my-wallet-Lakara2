import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wallet {
    private double euroConversionRate = 0.00022;
    private double dollarConversionRate = 0.00025;
    private double yenConversionRate = 0.028;
    private double balance;
    private List<Transaction> transactionHistory;

    public Wallet() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    
    // Méthodes pour définir les taux de conversion
    public void setEuroConversionRate(double rate) {
        this.euroConversionRate = rate;
    }

    public void setDollarConversionRate(double rate) {
        this.dollarConversionRate = rate;
    }

    public void setYenConversionRate(double rate) {
        this.yenConversionRate = rate;
    }

    // Méthodes pour convertir un montant d'Ariary vers Euro, Dollar et Yen
    public double convertToEuro(double amountInAriary) {
        return amountInAriary * euroConversionRate;
    }

    public double convertToDollar(double amountInAriary) {
        return amountInAriary * dollarConversionRate;
    }

    public double convertToYen(double amountInAriary) {
        return amountInAriary * yenConversionRate;
    }
public void addTransaction(String date, double amount, String description) {
    Transaction transaction = new Transaction(date, amount, description);
    transactionHistory.add(transaction);
    balance += amount;
}

public void sortTransactionsByDate() {
        List<Transaction> transactions = getTransactionHistory();
        Collections.sort(transactions, (t1, t2) -> t1.getDate().compareTo(t2.getDate()));
    }
    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}