package server.exceptions.db;

public class UserNotFoundException extends DatabaseException {
    public UserNotFoundException() {
        super("User not found");
    }
}
