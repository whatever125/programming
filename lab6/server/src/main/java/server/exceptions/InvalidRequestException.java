package server.exceptions;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String message) {
        super(message);
    }
}
