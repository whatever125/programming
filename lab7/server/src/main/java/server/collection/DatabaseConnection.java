package server.database;

import common.models.Movie;
import common.models.MovieGenre;
import common.models.MpaaRating;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

// Concrete realization
public abstract class DatabaseConnection {
    protected Connection connection;

    private DatabaseConnection() {}

    protected DatabaseConnection(String url, String login, String password) throws SQLException, IOException {
        this.connection = DriverManager.getConnection(url, login, password);
    }

    public abstract void authenticateUser(String login, String password) throws SQLException;

    public abstract void addUser(String login, String password) throws SQLException;

    public abstract boolean findUser(String login) throws SQLException;

    public abstract int addCoordinates(int x, int y) throws SQLException;

    public abstract int addPerson(String name, LocalDateTime birthday, Integer weight, String passportID) throws SQLException;

    public abstract int addMovie(Integer key, String name, int coordinatesID, long oscarsCount, MovieGenre genre,
                                 MpaaRating mpaaRating, int directorID) throws SQLException;

    public abstract void addOwner(int movieID, int userID) throws SQLException;

    public abstract void addObject(int userID, Integer key, Movie movie) throws SQLException;
}
