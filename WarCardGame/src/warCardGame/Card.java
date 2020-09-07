/*
 * Author: David House
 * Date: September 09, 2020
 * Purpose: The class is used to hold information for a card like its suit and rank. It returns a string regarding its suit and rank.
 */

package warCardGame;

public class Card {
	
	private String suit;
	private String rank;
	
	public Card() {
		this.suit = "Unknown";
		this.rank = "Unknown";
	}
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String getRank() {
		return rank;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public int getValue() {
		if (this.rank.equals("Ace"))
			return 14;
		else if (this.rank.equals("King"))
			return 13;
		else if (this.rank.equals("Queen"))
			return 12;
		else if (this.rank.equals("Jack"))
			return 11;
		else if (getRank().matches("[0-9]"))
			return Integer.parseInt(getRank());
		return 0;
	}
	
	public String toString() {
		return String.format("%s of %s", this.rank, this.suit);
	}
	
}
