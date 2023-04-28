package common.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String command) {
        super("! invalid command: " + command + " !");
    }
}
