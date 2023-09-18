package server.exceptions.db;

public class UserAlreadyExistsException extends DatabaseException {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
