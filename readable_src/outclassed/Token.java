package outclassed;

public class Token {
	static final char EMPTY = ' ';
	
	private char character;

	public Token() {
		this(EMPTY);
	}


	public Token(char c) {
		character = c;
	}

	/**
	 * Retrieves the token's value
	 * Implemented so it's easier to modify graphics in future
	 *
	 * @return token's value
	 */
	public char getToken() {return character;}

	/**
	 * @param t another instance of Token
	 * @return whether it's equal
	 */
	public boolean equals(Token t) {
		return getToken() == t.getToken();
	}

	/**
	 * @param t a character to be compared with
	 * @return whether it's equal
	 */
	public boolean equals(char t) {
		return getToken() == t;
	}
}
