package transactions;

import transactions.Trader;
import transactions.Transaction;

import java.util.*;
import java.util.stream.Collectors;

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
        return allTransactions.stream()
                .filter(t -> t.getYear() == year)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
    }

    public List<String> cities() {
        return allTransactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * @param city the trader's city
     * @return all traders from given city sorted by name.
     */
    public List<Trader> traders(String city) {
         return allTransactions.stream()
                 .map(Transaction::getTrader) // we only need traders, not transactions
                 .distinct() // we remove duplicates at this point
                 .filter(trader -> trader.getCity().equals(city)) // whoever is not from "city" is out
                 .sorted(Comparator.comparing(Trader::getName)) // let us sort the list by name now
                 .collect(Collectors.toList());
    }

    /**
     * Returns a Map of all transactions.
     *
     * @return a Map with the year as key and a list of all transaction of this year as value
     */
    public Map<Integer, List<Transaction>> transactionsByYear() {
        return allTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getYear));
    }

    /**
     * @param city the city
     * @return true if there are any trader based in given city
     */
    public boolean traderInCity(String city) {
        return traders(city).stream().count() > 0;
        // the sorting part inside traders(city) is not important, but this way we can reuse code
    }

    /**
     * @param from the trader's current location
     * @param to   the trader's new location
     */
    public void relocateTraders(String from, String to) {
        traders(from).stream().forEach(trader -> trader.setCity(to));
    }

    /**
     * @return the highest value in all the transactions
     */
    public int highestValue() {
        return allTransactions.stream()
                .map(Transaction::getValue)
                .max(Comparator.comparingInt(value -> value))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * @return the sum of all transaction values
     */
    public int totalValue() {
        return allTransactions.stream()
                .mapToInt(Transaction::getValue)
                .sum();
     // alternatively
     // return allTransactions.stream()
     //         .collect(Collectors.summingInt(Transaction::getValue));
    }

    /**
     * @return the transactions.Transaction with the lowest value
     */
    public Transaction getLowestValueTransaction(){
        return allTransactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * @return a string of all traders’ names sorted alphabetically
     */
    public String traderNames() {
        return allTransactions.stream()
                .map(Transaction::getTrader) // we only need traders, not transactions
                .distinct() // we remove duplicates at this point
                .sorted(Comparator.comparing(Trader::getName)) // let us sort the list by name now
                .map(Trader::getName)
                .collect(Collectors.joining(""));
    }

}
