package server.exceptions.IOHandlers;

public class InvalidDataException extends IOHandlerException {
    public InvalidDataException(String path, String message) {
        super("! invalid data in " + path + ": " + message + " !");
    }
    public InvalidDataException(String message) {
        super("! invalid data: " + message + " !");
    }
}
