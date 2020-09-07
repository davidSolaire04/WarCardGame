/*
 * Author: David House
 * Date: September 09, 2020
 * Purpose: The class is used to play the card game War. The client will use the play() method to deal cards to players
 * and get the information from the setters and getters to determine the winning hand and outcome.
 */

package warCardGame;

import java.util.Scanner;

public class WarGame {
	
	private War game;
	private Scanner input = new Scanner(System.in);
	
	public WarGame() {
		game = new War("Brian", "James");
	}
	
	private void displayIntro() {
		System.out.println("Welcome to the game of war");
	}
	
	private void displayRules() {
		System.out.println("The object of the game is to force the other player to run out of cards.");
		System.out.println("All the cards are dealt at the beginning of the game.");
		System.out.println("Each play both players lay the top card of their pile face up.");
		System.out.println("The player with the highest rank card, puts both cards on the bottom of his pile.");
		System.out.println("If both cards have the same rank, each player plays three cards face down and plays another round.");
		System.out.println("The winner of the tie-breaking round gets all the played cards");
		System.out.println(" (the cards in the tie, the six face down and the two in the tie-breaking play.)");
		System.out.println("");
	}
	
	private void displayStart() {
		System.out.println("Both hands have been dealt.");
		System.out.println(String.format("%s has %d cards to start.", game.getPlayer1(), game.getPlayer1Size()));
		System.out.println(String.format("%s has %d cards to start.", game.getPlayer2(), game.getPlayer2Size()));
		System.out.println("");
	}
	
	private void displayGamePlay() {
		boolean play = true;
		
		while (play) {
			
			System.out.println(String.format("%s plays %s. %s plays %s.", game.getPlayer1(), game.getPlayer1Hand(), game.getPlayer2(), game.getPlayer2Hand()));
			game.play();
			System.out.println(game.getWinner());
			if (game.hasTied())
				System.out.println(String.format("Kitty has %d cards.", game.getKittySize()));
			System.out.println(String.format("%s has %d cards left.", game.getPlayer1(), game.getPlayer1Size()));
			System.out.println(String.format("%s has %d cards left.", game.getPlayer2(), game.getPlayer2Size()));
			
			
			
			System.out.println("Hit any key to continue or Q to quit");
			String userInput = input.next();
			
			if (userInput.toUpperCase().charAt(0) == 'Q') {
				displayEndMessage();
				System.exit(0);
			}

			if (game.hasWon())
				play = false;
		}
		
		System.out.println(game.getWinner());
	}
	
	private void displayEndMessage() {
		System.out.println("Thank you for playing!");
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WarGame warGame = new WarGame();
		warGame.displayIntro();
		warGame.displayRules();
		warGame.displayStart();
		warGame.displayGamePlay();
	}

}
