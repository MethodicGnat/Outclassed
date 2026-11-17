/*
Author: Analeigh Nwokolo
Date: 11/10/2026
Program: Outclassed
Purpose: run the Outclassed game loop

I have only used resources indicated that are allowable.
The work submitted is my/our, and only my/our work.
Signature: Analeigh Nwokolo
Date: 11/17/2026

Mrs. Keller is the best teacher :>
 */


package outclassed;

import java.util.Scanner;

public class Outclassed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String player1Name;
		String player2Name;
		Match match;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Outclassed");
		System.out.println("The objective is to beat your opponent in a best of 3 series of tic-tac-toe.");
		System.out.println("This game is best played with rapid fire movements, like bullet-time chess :)");
		System.out.println("For that reason, try timing 5 seconds between each move with a stopwatch.\n");
		
		System.out.print("Enter player1's name:  ");
		player1Name = input.next();
		System.out.print("Enter player2's name: ");
		player2Name = input.next();

		match = new Match(player1Name, player2Name);

		System.out.print("Enter any character when you are ready: ");
		input.next();

		try {
			match.start();
			do {
				System.out.print("Would you like to play again? Yes or no: ");
				switch(input.next().toLowerCase()) {
					case "yes" -> match.start();
					case "no" -> System.exit(0);
					default -> System.out.println("Invalid input, please try again.");
				}
			} while (true);
		} catch (Exception e) {
			System.out.println("An error has occurred: " + e);
			for (StackTraceElement t : e.getStackTrace()) System.out.println(t);
			System.out.println("Closing program");
			System.exit(0);
		}

	}

}
