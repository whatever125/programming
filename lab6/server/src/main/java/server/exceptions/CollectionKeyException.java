package server.exceptions;

public class CollectionKeyException extends Exception {
    public CollectionKeyException(String message) {
        super("! " + message + " !");
    }
}
