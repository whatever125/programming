package client.exceptions;

public class InvalidFileDataException extends Exception {
    public InvalidFileDataException(String path, String message) {
        super("! invalid file data in " + path + ": " + message + " !");
    }
}
