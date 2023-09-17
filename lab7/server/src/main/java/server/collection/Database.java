package server.collection;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

// fabric
public abstract class Database {
    private final static int LOGIN_INDEX = 3;
    private final static int PASSWORD_INDEX = 4;

    protected final String login;
    protected final String password;

    public Database(String filepath) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filepath)))) {
            String[] lines = scanner.nextLine().split(":");
            this.login = lines[LOGIN_INDEX];
            this.password = lines[PASSWORD_INDEX];
        }

    }

    abstract public DatabaseConnection createConnection() throws SQLException, IOException;
}
