import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactions.Trader;
import transactions.Transaction;
import transactions.TransactionList;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransactionListTest {

	private Trader raoul;
	private Trader mario;
	private Trader alan;
	private Trader brian;

	private TransactionList transactionList;

	@BeforeEach
	void setUp() {
		raoul = new Trader("Raoul", "Basel");
		mario = new Trader("Mario", "Brugg");
		alan  = new Trader("Alan",  "Basel");
		brian = new Trader("Brian", "Basel");

		transactionList = new TransactionList();
		transactionList.addTransaction(new Transaction(brian, 2011, 800));
		transactionList.addTransaction(new Transaction(raoul, 2012, 1000));
		transactionList.addTransaction(new Transaction(raoul, 2011, 400));
		transactionList.addTransaction(new Transaction(mario, 2012, 710));
		transactionList.addTransaction(new Transaction(mario, 2012, 700));
		transactionList.addTransaction(new Transaction(alan,  2012, 950));
	}

	@Test
	void testAdd(){
		assertEquals(6, transactionList.size());
	}

	@Test
	void testSizeOfEmptyList() {
		// when
		TransactionList transactionList = new TransactionList();

		// then
		assertEquals(0, transactionList.size());
	}

	@Test
	void testSize()  {
		// given
		TransactionList transactionList = new TransactionList();

		// when
		transactionList.addTransaction(new Transaction(brian, 2011, 1000));

		// then
		assertEquals(1, transactionList.size());
	}

	@Test
	void testTransactionsInYear() {
		// when
		List<Transaction> transactions = transactionList.transactionsInYear(2011);

		// then
		assertEquals(2, transactions.size());
		assertTrue(transactions.get(0).getValue() < transactions.get(1).getValue());
	}


	@Test
	void testCities() {
		// when
		List<String> cities = transactionList.cities();

		// then
		assertEquals(2, cities.size());
		assertTrue(cities.contains("Basel"));
		assertTrue(cities.contains("Brugg"));
	}

	@Test
	void testTraders()  {
		// when
		List<Trader> traders = transactionList.traders("Basel");

		// then
		assertEquals(3, traders.size());

		assertSame(alan,  traders.get(0));
		assertSame(brian, traders.get(1));
		assertSame(raoul, traders.get(2));
	}

	@Test
	void testTradersUnknownCity() {
		// when
		List<Trader> traders = transactionList.traders("Freiburg");

		// then
		assertEquals(0, traders.size());
	}

	@Test
    void testTransactionsByYear(){
	    // when
        Map<Integer, List<Transaction>> map = transactionList.transactionsByYear();

        // then
        assertEquals(2, map.size());
        assertTrue(map.keySet().contains(2011));
        assertTrue(map.keySet().contains(2012));

        assertEquals(2, map.get(2011).size());
        assertEquals(4, map.get(2012).size());
    }
	@Test
	void testTraderInCity() {
		assertTrue(transactionList.traderInCity("Brugg"));
		assertFalse(transactionList.traderInCity("Freiburg"));
	}

	@Test
	void testRelocateTraders() {
		//when
		transactionList.relocateTraders("Basel", "London");

		//then
		assertFalse(transactionList.traderInCity("Basel"));
		assertTrue(transactionList.traderInCity("London"));

		List<Trader> tradersInLondon = transactionList.traders("London");
		assertEquals(3, tradersInLondon.size());
		assertFalse(tradersInLondon.contains(mario));
	}

	@Test
	void testHighestValue()  {
		//when
		int value = transactionList.highestValue();

		//then
		assertEquals(1000, value);
	}

    @Test
  	void testTotalValue()  {
  		//when
  		int value = transactionList.totalValue();

  		//then
  		assertEquals(4560, value);
  	}

  	@Test
    void testLowestValueTransaction(){
	    //when
        Transaction lowest = transactionList.getLowestValueTransaction();

        //then
        assertEquals(400,   lowest.getValue());
        assertEquals(2011,  lowest.getYear());
        assertEquals(raoul, lowest.getTrader());
    }

	@Test
	void testTraderNames()  {
		//when
		String names = transactionList.traderNames();

		//then
		assertEquals("AlanBrianMarioRaoul", names);
	}
}