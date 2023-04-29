package client.exceptions;

public class ErrorResponseException extends Exception {
    public ErrorResponseException(String message) {
        super("! Error response from server: " + message + " !");
    }
}
