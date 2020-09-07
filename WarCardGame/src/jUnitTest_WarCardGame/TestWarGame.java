package jUnitTest_WarCardGame;

import static org.junit.Assert.*;

import org.junit.Test;

import warCardGame.*;

public class TestWarGame {

	@Test
	public void testCardDefaultConstructor() {
		Card card = new Card();
		assertEquals("Unknown", card.getRank());
		assertEquals("Unknown", card.getSuit());
	}
	
	@Test
	public void testCardParameterizedConstructor() {
		Card card = new Card("Spades", "4");
		assertEquals("4", card.getRank());
		assertEquals("Spades", card.getSuit());
	}
	
	@Test
	public void testCardFormattedText() {
		Card card = new Card("Spades", "4");
		assertEquals("4 of Spades", card.toString());
	}
	
	@Test
	public void testDeckSize() {
		Deck deck = new Deck();
		assertEquals(52, deck.getSize());
	}
	
	@Test
	public void testDeckFirstCardAndLastCard() {
		Deck deck = new Deck();
		assertEquals("Ace of Clubs", deck.getCard(0).toString());
		assertEquals("King of Spades", deck.getCard(51).toString());
	}
	
	@Test
	public void testDeckDealMethodNullReturn() {
		Deck deck = new Deck();
		assertEquals(null, deck.deal());
	}
	
	@Test
	public void testDeckDealMethodCardReturn() {
		Deck deck = new Deck();
		deck.shuffle();
		Card card = new Card("Spades", "4");
		assertEquals(card.getClass(), deck.deal().getClass());
	}
	
	@Test
	public void testDeckNoMoreHandsDealt() {
		Deck deck = new Deck();
		deck.shuffle();
		deck.dealAllCards();
		assertEquals(0, deck.getSize());
	}
	
	@Test
	public void testDeckCheckShuffleMethodIdenticalUnder20Percent() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		int sameValue = 0;
		deck1.shuffle();
		for (int index = deck1.getSize() - 1; index >= 0; index--){
			if (deck1.getCard(index).equals(deck2.deal()))
				sameValue += 1;
		}
		assertTrue(sameValue < 20);
	}
	
	@Test
	public void testWarDefaultConstructorCheckDefaultPlayerNames() {
		War war = new War();
		assertEquals("Unknown", war.getPlayer1());
		assertEquals("Unknown", war.getPlayer2());
	}
	
	@Test
	public void testWarParameterizedConstructorCheckPlayerNames() {
		War war = new War("George", "James");
		assertEquals("George", war.getPlayer1());
		assertEquals("James", war.getPlayer2());
	}
	
}
