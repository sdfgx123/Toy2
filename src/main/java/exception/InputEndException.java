package exception;

public class InputEndException extends RuntimeException {

    public InputEndException() {
    }

    public InputEndException(String message) {
        super(message);
    }
}
