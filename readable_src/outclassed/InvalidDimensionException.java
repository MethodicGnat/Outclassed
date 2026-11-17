package outclassed;

public class InvalidDimensionException extends Exception{
    public InvalidDimensionException() {
        this("Dimensions have to be greater than three and odd.");
    }

    public InvalidDimensionException(String message) {
        super(message);
    }
}
