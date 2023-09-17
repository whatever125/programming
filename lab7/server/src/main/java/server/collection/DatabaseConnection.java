package server.collection;

import common.models.Movie;
import common.models.MovieGenre;
import common.models.MpaaRating;
import server.exceptions.IOHandlers.InvalidDataException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

// Concrete realization
public abstract class DatabaseConnection {
    protected Connection connection;

    private DatabaseConnection() {}

    protected DatabaseConnection(String url, String login, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, login, password);
    }

    public abstract void authenticateUser(String login, String password) throws SQLException;

    public abstract void addUser(String login, String password) throws SQLException;

    public abstract int addObject(String login, Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                                   MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                                   String passportID) throws SQLException;

    public abstract int placeObjectAtKey(String login, Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                                         MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                                         String passportID) throws SQLException;

    public abstract void updateObjectByID(Integer movieID, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                                          MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                                          String passportID) throws SQLException;

    public abstract boolean isMovieKeyOwnedByUser(Integer key, String login);

    public abstract boolean isMovieIDOwnedByUser(Integer id, String login);

    public abstract HashMap<Integer, Movie> getAllMovies() throws SQLException, InvalidDataException;

    public abstract boolean removeObjectByKey(Integer movieKey) throws SQLException;

    public abstract ArrayList<Integer> clearCollectionForUser(String login) throws SQLException;
}
