package sources.exceptions.io;

public class WrongArgumentException extends Exception {
    public WrongArgumentException(String message) {
        super("! " + message + " !");
    }
}
