package outclassed;


public class Board {
	protected int dimension;
	protected Token[][] board;;

	/**
	 * @param dimension matrix scale
	 * @throws InvalidDimensionException thrown when dimension < 3 or is not odd
	 */
	public Board(int dimension) throws InvalidDimensionException {
		if (dimension < 3 || dimension % 2 == 0) throw new InvalidDimensionException();
		
		this.dimension = dimension;
		
		board = new Token[3][3];
		
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++) board[i][j] = new Token(Token.EMPTY);
	}

	/**
	 * handles token retrieval according to an x,y coordinate system
	 *
	 * @param x
	 * @param y
	 * @return token in the board position
	 */
	public Token getToken(int x, int y) {
		return board[y][x];
	}

	/**
	 * Place a player token on the board
	 *
	 * @param x
	 * @param y
	 * @param t player token
	 * @throws InvalidPlacementException thrown when the x or y coordinates exceed the board dimensions.
	 */
	public void place(int x, int y, Token t) throws InvalidPlacementException {
		if (y >= board.length || x >= board.length) throw new InvalidPlacementException();
		board[y][x] = t;
	}

	/**
	 * determines if there are no more spaces to play on board
	 *
	 * @return whether board is filled
	 */
	public boolean isFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (getToken(i, j).getToken() == Token.EMPTY) return false;
			}
		}
		
		return true;
	}

	/**
	 * Examines horizontal, vertical, and diagonal patterns for player wins.
	 *
	 * @param token
	 * @return whether the player has won on the board
	 */
	public boolean isSolved(Token token) {
        return isSolvedRightDiagonally(token) ||
                isSolvedLeftDiagonally(token) ||
                isSolvedHorizontally(token) ||
                isSolvedVertically(token);
    }

	/**
	 * solves for horizontal matches
	 *
	 * @param token
	 * @return whether it found a solution
	 */
	private boolean isSolvedHorizontally(Token token) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (!getToken(j, i).equals(token)) {j = board.length; continue;}
				else if (j == board.length - 1) {return true;}
			}
		}
		return false;
	}

	/**
	 * solves for vertical matches
	 *
	 * @param token
	 * @return whether it found a solution
	 */
	private boolean isSolvedVertically(Token token) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (!getToken(i, j).equals(token)) {j = board.length; continue;}
				else if (j == board.length - 1) {return true;}
			}
		}
		return false;
	}

	/**
	 * solves for right diagonal matches
	 *
	 * @param token
	 * @return whether it found a solution
	 */
	private boolean isSolvedRightDiagonally(Token token) {
		for (int i = 0; i < board.length; i++) {
			if (!getToken(i, i).equals(token)) return false;
		}
		return true;
	}

	/**
	 * solves for left diagonal matches
	 *
	 * @param token
	 * @return whether it found a solution
	 */
	private boolean isSolvedLeftDiagonally(Token token) {
		for (int i = board.length - 1; i >= 0; i--) {
			if (!getToken(i, Math.abs(board.length - 1 - i)).equals(token)) return false;
		}
		return true;
	}

	/**
	 * Format board.
	 *
	 * Example with board.dimension = 3:
	 *
	 *    1  2  3
	 * 1 {} {} {}
	 * 2 {} {} {}
	 * 3 {} {} {}
	 *
	 * @return formatted board
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < board.length + 1; i++) {
			for (int j = 0; j < board.length + 1; j++) {
				if (j == 0 && i == 0) s += "  ";
				else if (i == 0) s += j + "  ";
				else if (j == 0) s += i + "  ";
				else s += getToken(j - 1, i - 1).getToken() + " ";
			}
			s+="\n";
		}
		return s;
	}
}
