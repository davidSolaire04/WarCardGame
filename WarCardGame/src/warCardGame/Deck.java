/*
 * Author: David House
 * Date: September 09, 2020
 * Purpose: This class is used to hold the card objects. It initializes a list of cards. The class can shuffle cards. It simulates a deck of cards.
 */


package warCardGame;


import java.util.Random;

import gray_a03_classes.list.LinkedList;
import gray_a03_classes.queue.ListQueue;
import gray_a03_classes.queue.Queue;

public class Deck {
	
	private LinkedList<Card> deck = new LinkedList<Card>();
	private Queue<Card> shuffledDeck = new ListQueue<Card>();
	private int size = 0;
	private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
	private static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	public Deck() {
		for (int sIndex = 0; sIndex < SUITS.length; sIndex++)
			for (int rIndex = 0; rIndex < RANKS.length; rIndex++) 
			{
				deck.add(new Card(SUITS[sIndex], RANKS[rIndex]));
				this.size += 1;
			}
	}
	
	public void shuffle(){
		Random random = new Random();
		
		for (int iterate = 10; iterate > 0; iterate--)
			for (int dIndex = 0; dIndex < this.size; dIndex++) {
				int ran = dIndex + random.nextInt(this.size - dIndex);
				Card temp = deck.get(ran);
				deck.set(ran, deck.get(dIndex));
				deck.set(dIndex, temp);
			}
		
		for (int dIndex = 0; dIndex < this.getSize(); dIndex++)
			shuffledDeck.enqueue(deck.get(dIndex));
	}
	
	public Card deal() {
		if (shuffledDeck.isEmpty())
			return null;
		
		this.size -= 1;
		return shuffledDeck.dequeue();
	}
	
	// This method is for testing purposes
	public void dealAllCards() {
		boolean canDeal = true;
		while (canDeal) {
			if (shuffledDeck.isEmpty())
				canDeal = false;
			else {
				deal();
			}
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Card getCard(int index) {
		return deck.get(index);
	}
}
