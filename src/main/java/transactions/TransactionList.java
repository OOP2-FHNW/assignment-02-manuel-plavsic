package transactions;

import transactions.Trader;
import transactions.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dieter Holz
 */
public class TransactionList {
    private final List<Transaction> allTransactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        allTransactions.add(transaction);
    }

    public int size() {
        return allTransactions.size();
    }

    public List<Transaction> transactionsInYear(int year) {
        return null;
    }

    public List<String> cities() {
        return null;
    }

    /**
     * @param city the trader's city
     * @return all traders from given city sorted by name.
     */
    public List<Trader> traders(String city) {
        return null;
    }

    /**
     * Returns a Map of all transactions.
     *
     * @return a Map with the year as key and a list of all transaction of this year as value
     */
    public Map<Integer, List<Transaction>> transactionsByYear() {
        return null;
    }

    /**
     * @param city the city
     * @return true if there are any trader based in given city
     */
    public boolean traderInCity(String city) {
        return false;
    }

    /**
     * @param from the trader's current location
     * @param to   the trader's new location
     */
    public void relocateTraders(String from, String to) {
    }

    /**
     * @return the highest value in all the transactions
     */
    public int highestValue() {
        return 0;
    }

    /**
     * @return the sum of all transaction values
     */
    public int totalValue() {
        return 0;
    }

    /**
     * @return the transactions.Transaction with the lowest value
     */
    public Transaction getLowestValueTransaction(){
        return null;
    }

    /**
     * @return a string of all traders’ names sorted alphabetically
     */
    public String traderNames() {
        return null;
    }

}
