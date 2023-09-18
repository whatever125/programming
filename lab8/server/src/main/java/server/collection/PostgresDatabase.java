package server.collection;

import java.io.IOException;
import java.sql.SQLException;

// concrete fabric
public class PostgresDatabase extends Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/studs";

    public PostgresDatabase(String filepath) throws IOException {
        super(filepath);
    }

    @Override
    public PostgresConnection createConnection() throws SQLException {
        return new PostgresConnection(URL, login, password);
    }
}
