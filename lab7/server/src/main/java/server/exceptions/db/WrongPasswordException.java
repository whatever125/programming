package server.exceptions.db;

public class WrongPasswordException extends DatabaseException {
    public WrongPasswordException() {
        super("WrongPassword");
    }
}
