package sources.exceptions.receiver;

public class CollectionKeyException extends Exception {
    public CollectionKeyException(String message) {
        super("! " + message + " !");
    }
}
