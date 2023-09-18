package server.exceptions.db;

public class AuthorizationException extends DatabaseException {
    public AuthorizationException(String reason) {
        super(reason);
    }
}
