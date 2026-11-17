package outclassed;
import java.util.GregorianCalendar;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

public class Match {
	final private int BOARD_DIMENSION = 3;
	final private String PATH = "src/statistics.txt";

	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Player prevWinner;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
	
	
	public Match(String player1, String player2) {
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);

		this.player1.setToken(new Token('X'));
		this.player2.setToken(new Token('O'));
	}

	/**
	 * starts the match between the two players
	 *
	 * @throws Exception leaves any unhandled errors to main
	 */
	public void start() throws Exception {
		player1.resetScore(); player2.resetScore();
		player1.resetStreak(); player2.resetStreak();
		Player.boardManager = new BoardPlacementManager(BOARD_DIMENSION);

		startTime = new GregorianCalendar();
		currentPlayer = (int) Math.round(Math.random()) == 1 ? player1 : player2;
		while (player1.getScore() < 3 && player2.getScore() < 3) {
			gameLoop();
		}
		endTime = new GregorianCalendar();
		System.out.println(currentPlayer.getName() + " won in " + getMatchTime() + " seconds!");
		recordMatchResults();
		System.out.println("Best round statistics");
		System.out.println(readMatchData());
	}

	/**
	 * logic behind turn-based system, streaks, and winners
	 */
	private void gameLoop() {
		System.out.println(getMatchMessage());
		Player.boardManager.displayBoard();
		currentPlayer.takeTurn();
		if (Player.boardManager.didCurrentPlayerSolve()) {
			if (prevWinner == null) {prevWinner = currentPlayer;}
			else if (prevWinner != currentPlayer) {
				prevWinner.resetStreak();
				prevWinner = currentPlayer;
			}
			currentPlayer.increaseStreak();
			currentPlayer.increaseScore();
			Player.boardManager.displayBoard();
			System.out.println(currentPlayer.getName() + " solved the board.");
			System.out.println(getMatchScore());
			Player.boardManager.initializeBoard(BOARD_DIMENSION);
		} else if (Player.boardManager.isFull()) {
			Player.boardManager.initializeBoard(BOARD_DIMENSION);
		} else {
			currentPlayer = currentPlayer == player1 ? player2 : player1;
		}
	}

	/**
	 * Returns the score for a game within the best of 3 series
	 *
	 * @return formatted scorecard
	 */
	private String getMatchScore() {
		String s1 = player1.getName() + " " + player1.getScore();
		String s2 = player2.getName() + " " + player2.getScore();
		return "Score = " + s1 + " : " + s2;
	}

	/**
	 * Retrieves time since match.start() and when it ended
	 *
	 * @return time in seconds
	 */
	private String getMatchTime() {
		int totalMinutes = (int) (endTime.getTimeInMillis() - startTime.getTimeInMillis() ) / 1000;
		return Integer.toString(totalMinutes);
	}

	/**
	 * Alerts whose turn it is
	 *
	 * @return formatted explanation
	 */
	public String getMatchMessage() {
		return "It's " + currentPlayer.getName() + "'s turn.";
	}

	/**
	 * Either a sweep or basic. Want to add reverse sweep mechanic in the future
	 *
	 * @return description of winning fashion
	 */
	public String getWinningFashion() {
		String s = "";
		switch(currentPlayer.getStreak()) {
			case 1, 2 -> s = "Basic";
			case 3 -> s = "Sweep";
		}
		return s;
	}

	/**
	 * Stores matches with the most impressive times
	 * @throws java.io.IOException leave unhandled errors to main
	 */
	public void recordMatchResults() throws java.io.IOException {
		File file = new File(PATH);
		String statistics = "Player: " + currentPlayer.getName() + "\n" +
				"Fastest victory time: " + getMatchTime() + "\n" +
				"Winning fashion: " + getWinningFashion();

		if (!file.exists()) {
			PrintWriter writer = new PrintWriter(file);
			writer.println(statistics);
			writer.close();
			return;
		}

		Scanner feed = new Scanner(file);
		int highscore = 0;

		while(feed.hasNextLine()) {
			String line = feed.nextLine();
			if (line.startsWith("Fastest victory time: ")) {
				line = line.substring("Fastest victory time:".length()).trim();
				highscore = Integer.parseInt(line);
			}
		}
		feed.close();

		if (highscore > Integer.parseInt(getMatchTime())) {
			System.out.println("New record!");
			PrintWriter writer = new PrintWriter(file);
			writer.print(statistics);
			writer.close();
		}
	}

	/**
	 * @return parsed version of best match data
	 * @throws java.io.IOException leave unhandled errors to main
	 */
	public String readMatchData() throws java.io.IOException {
		File file = new File(PATH);
		String s = "";
		try (
				Scanner feed = new Scanner(file);
				) {
			while (feed.hasNextLine()) {
				s += feed.nextLine() + "\n";
			}
		}
		return s;
	}
}
