package server.exceptions.db;

import java.sql.SQLException;

public class DatabaseException extends SQLException {
    public DatabaseException(String reason) {
        super(reason);
    }
}
