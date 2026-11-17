package outclassed;

public class InvalidPlacementException  extends Exception {

    public InvalidPlacementException() {
        this("You cannot place a token in a predetermined spot.");
    }

    public InvalidPlacementException(String message) {
        super(message);
    }
}
