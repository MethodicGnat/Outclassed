package outclassed;
import java.util.Scanner;


public class Player {
	static BoardPlacementManager boardManager;

	private int streak;
	private int score;
	private Token token;
	private boolean playing;
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public Token getToken() {
		return token;
	}
	
	
	public void setToken(Token token) {
		this.token = token;
	}


	public boolean isPlaying() {
		return playing;
	}
	
	public int getScore() {
		return score;
	}

	public int getStreak() {
		return streak;
	}

	public void takeTurn() {
		playing = true;
		boardManager.setCurrentToken(getToken());
		boardManager.regularPlacement();
	}


	public void increaseScore() {
		score++;
	}


	public void increaseStreak() {
		streak++;
	}


	public void resetScore() {
		score = 0;
	}


	public void resetStreak() {
		streak = 0;
	}
}
