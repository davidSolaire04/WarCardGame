/*
 * Author: David House
 * Date: September 09, 2020
 * Purpose: The class is used to play the card game War. The client will use the play() method to deal cards to players
 * and get the information from the setters and getters to determine the winning hand and outcome.
 */

package warCardGame;

import gray_a03_classes.queue.ListQueue;
import gray_a03_classes.queue.Queue;
import gray_a03_classes.stack.ListStack;
import gray_a03_classes.stack.Stack;

public class War {
	
	private Deck deck = new Deck();
	private String player1;
	private String player2;
	private String winner = "No winner";
	private Queue<Card> player1Pile = new ListQueue<Card>();
	private Queue<Card> player2Pile = new ListQueue<Card>();
	private Stack<Card> kitty = new ListStack<Card>();
	private boolean isTie = false;
	private boolean hasWon = false;
	private int kittySize = 0;
	private boolean hasPlayedWar = false;
	
	public War() {
		this.player1 = "Unknown";
		this.player2 = "Unknown";
		deck.shuffle();
		dealCardsToPlayers();
	}
	
	public War(String p1, String p2) {
		this.player1 = p1;
		this.player2 = p2;
		deck.shuffle();
		dealCardsToPlayers();
	}
	
	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	
	public String getPlayer1Hand() {
		if (player1Pile.isEmpty())
			return null;
		return player1Pile.peek().toString();
	}
	
	public String getPlayer2Hand() {
		if (player2Pile.isEmpty())
			return null;
		return player2Pile.peek().toString();
	}
	
	public int getPlayer1Size() {
		return this.player1Pile.size();
	}
	
	public int getPlayer2Size() {
		return this.player2Pile.size();
	}
	
	public int getKittySize() {
		return this.kittySize;
	}
	
	public String getWinner() {
		return this.winner;
	}
	
	public boolean hasTied() {
		return this.hasPlayedWar;
	}
	
	public boolean hasWon() {
		return this.hasWon;
	}
	
	
	private void dealCardsToPlayers() {
		Boolean canDeal = true;
		int currentPlayer = 1;
		while(canDeal) {
			Card temp = deck.deal();
			if (temp == null)
				canDeal = false;
			else {
				if (currentPlayer == 1) {
					player1Pile.enqueue(temp);
					currentPlayer = 2;
				}
				else if (currentPlayer == 2) {
					player2Pile.enqueue(temp);
					currentPlayer = 1;
				}
			}
		} // end while loop
	}
	
	public void play() {
		hasPlayedWar = false;
		if (checkEmptyCards())
			return;
		
		Card p1 = player1Pile.dequeue();
		Card p2 = player2Pile.dequeue();
		
		int hand = checkWinningHand(p1, p2);
		addToKitty(p1, p2);
		checkWinner(hand);
		
		if (isTie) {
			playWar();
			hasPlayedWar = true;
		}
		
	}
	
	private void playWar() {
		this.isTie = false;
		boolean canDeal = true;
		int counter = 1;
		int winner = 0;
		
		while (canDeal) {
			if (checkEmptyCards())
				canDeal = false;
			else {
				winner = checkWinningHand(player1Pile.peek(), player2Pile.peek());
				Card p1 = player1Pile.dequeue();
				Card p2 = player2Pile.dequeue();
				addToKitty(p1, p2);
				
				if (counter == 4) {
					checkWinner(winner);
					if (this.isTie)
						counter = 0;
					else
						canDeal = false;
				}
			}
			counter += 1;
		} // end while loop
	}
	
	private void winKitty(String name) {
		this.kittySize = kitty.size();
		boolean hasCards = true;
		if (player1.equals(name)) {
			while (hasCards) {
				if (kitty.isEmpty())
					hasCards = false;
				else
					player1Pile.enqueue(kitty.pop());
			}
		} // end if
		else if (player2.equals(name)) {
			while (hasCards) {
				if (kitty.isEmpty())
					hasCards = false;
				else
					player2Pile.enqueue(kitty.pop());
			}
		} // end if
	}
	
	private void addToKitty(Card p1, Card p2) {
		this.kitty.push(p1);
		this.kitty.push(p2);
	}
	
	private void endGame(String loser) {
		this.hasWon = true;
		if (this.player1.equals(loser))
			this.winner = "Player 2 has won the game.";
		else
			this.winner = "Player 1 has won the game";
		
	}
	
	private int checkWinningHand(Card p1, Card p2) {
		if (p1.getValue() > p2.getValue())
			return 1;
			
		else if (p1.getValue() < p2.getValue())
			return 2;
		
		return 0;
	}
	
	private void checkWinner(int winner) {
		if (winner == 1) {
			this.winner = String.format("%s has won this hand", player1);
			winKitty(player1);
		}
		else if (winner == 2) {
			this.winner = String.format("%s has won this hand", player2);
			winKitty(player2);
		}
		else if (winner == 0) {
			this.winner = "It's a tie! Each player lays 3 cards face down";
			isTie = true;
		}
	}
	
	private boolean checkEmptyCards() {
		if (player1Pile.isEmpty()) {
			endGame(this.getPlayer1());
			return true;
		}
			
		else if (player2Pile.isEmpty()) {
			endGame(this.getPlayer2());
			return true;
		}

		return false;
	}
}
