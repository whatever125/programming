package client.exceptions;

public class NetworkClientException extends Exception {
    public NetworkClientException(String message) {
        super("! Network client exception: " + message + " !");
    }
}
