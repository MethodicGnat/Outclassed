package outclassed;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BoardPlacementManager {
	private Token currentToken;
	private Board board;
	
	public BoardPlacementManager(int dimension) {
		initializeBoard(dimension);
	}

	/**
	 * Creates a new tic-tac-toe-board
	 *
	 * @param dimension board's matrix scale
	 */
	public void initializeBoard(int dimension) {
		try {
			board = new Board(dimension);
		}
		catch(InvalidDimensionException e) {
			System.out.println("Invalid tic-tac-toe board dimension");
			System.out.println("Closing program");
			System.exit(0);
		}

	}

	/**
	 * ensures user can enter their board placement with checks in place for a typical set-up
	 */
	public void regularPlacement() {
		Scanner input = new Scanner(System.in);
		int x, y;
		do {
			try {
				System.out.println("Give the x and y position like \"x y\": ");
				x = input.nextInt() - 1;
				y = input.nextInt() - 1;


				if (y >= board.dimension || x >= board.dimension || !board.getToken(x,y).equals(Token.EMPTY))
					throw new InvalidPlacementException();

				board.place(x, y, currentToken);
				System.out.println(board.getToken(2, 1).getToken());
				break;
			}
			catch (InvalidPlacementException e) {
				System.out.println("Invalid placement, please try again");
				input.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input, please try again.");
				input.nextLine();
			}
		} while(true);

	}

	/**
	 * Checks if the current player won the board
	 * @return whether they won
	 */
	public boolean didCurrentPlayerSolve() {
		return board.isSolved(currentToken);
	}

	/**
	 * Added here in case of future expansion for different types of boards
	 *
	 * @return whether board is full
	 */
	public boolean isFull() {
		return board.isFull();
	}

	/**
	 * Used to switch player turns
	 * @param token player token
	 */
	public void setCurrentToken(Token token) {
		currentToken = token;
	}

	/**
	 * prints a easy-to-read tic-tac-toe board
	 */
	public void displayBoard() {
		System.out.println(board.toString());
	}
}
