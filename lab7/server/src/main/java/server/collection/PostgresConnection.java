package server.collection;

import common.exceptions.WrongArgumentException;
import common.models.*;
import server.exceptions.IOHandlers.InvalidDataException;
import server.exceptions.db.UserAlreadyExistsException;
import server.exceptions.db.UserNotFoundException;
import server.exceptions.db.WrongPasswordException;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PostgresConnection extends DatabaseConnection {
    protected PostgresConnection(String url, String login, String password) throws SQLException {
        super(url, login, password);
    }

    @Override
    public void authenticateUser(String login, String password) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM users WHERE login = ?");
        ps.setString(1, login);
        ResultSet result = ps.executeQuery();

        if (result.next()) {
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] salt = result.getBytes("salt");
            byte[] expectedPassword = result.getBytes("password");

            if (!Passwords.isExpectedPassword(passwordBytes, salt, expectedPassword)) {
                throw new WrongPasswordException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void addUser(String login, String password) throws SQLException {
        if (findUser(login)) {
            throw new UserAlreadyExistsException();
        }

        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO users (login, password, salt) VALUES (?, ?, ?)");

        byte[] salt = Passwords.getSalt();
        byte[] passwordHash = Passwords.hash(password.getBytes(StandardCharsets.UTF_8), salt);

        ps.setString(1, login);
        ps.setBytes(2, passwordHash);
        ps.setBytes(3, salt);

        ps.executeUpdate();
    }

    private boolean findUser(String login) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT 1 FROM users WHERE login = ?");
        ps.setString(1, login);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
    }

    private int addCoordinates(int x, int y) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO coordinates (x, y) VALUES (?, ?) RETURNING id");

        ps.setInt(1, x);
        ps.setInt(2, y);

        ResultSet resultSet = ps.executeQuery();
        int id = -1;
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }

        return id;
    }

    private void updateCoordinates(int id, int x, int y) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE coordinates SET (x, y) = (?, ?) WHERE id = ?");

        ps.setInt(1, x);
        ps.setInt(2, y);
        ps.setInt(3, id);

        ps.executeUpdate();
    }

    private int addPerson(String name, LocalDateTime birthday, Integer weight, String passportID)
            throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO person (name, birthday, weight, passportid) VALUES (?, ?, ?, ?) RETURNING id");

        ps.setString(1, name);
        ps.setDate(2, java.sql.Date.valueOf(birthday.toLocalDate()));
        ps.setInt(3, weight);
        ps.setString(4, passportID);

        ResultSet resultSet = ps.executeQuery();
        int id = -1;
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }

        return id;
    }

    private void updatePerson(int id, String name, LocalDateTime birthday, Integer weight, String passportID)
            throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE person SET (name, birthday, weight, passportid) = (?, ?, ?, ?) WHERE id = ?");

        ps.setString(1, name);
        ps.setDate(2, java.sql.Date.valueOf(birthday.toLocalDate()));
        ps.setInt(3, weight);
        ps.setString(4, passportID);
        ps.setInt(5, id);

        ps.executeUpdate();
    }

    private int addMovie(Integer key, String name, int coordinatesID, long oscarsCount, MovieGenre genre,
                         MpaaRating mpaaRating, int directorID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO movies (key, name, coordinatesID, oscarscount, genre, mpaarating, directorID) " +
                        "VALUES (?, ?, ?, ?, CAST(? AS movie_genre), CAST(? AS mpaa_rating), ?) RETURNING id");

        ps.setInt(1, key);
        ps.setString(2, name);
        ps.setInt(3, coordinatesID);
        ps.setLong(4, oscarsCount);
        ps.setString(5, genre.name());
        ps.setString(6, mpaaRating.name());
        ps.setInt(7, directorID);

        ResultSet resultSet = ps.executeQuery();
        int movieID = -1;
        if (resultSet.next()) {
            movieID = resultSet.getInt(1);
        }

        return movieID;
    }

    private void updateMovie(Integer key, String name, long oscarsCount, MovieGenre genre,
                             MpaaRating mpaaRating) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE movies SET (name, oscarscount, genre, mpaarating) = " +
                        "(?, ?, CAST(? AS movie_genre), CAST(? AS mpaa_rating)) WHERE key = ?");

        ps.setString(1, name);
        ps.setLong(2, oscarsCount);
        ps.setString(3, genre.name());
        ps.setString(4, mpaaRating.name());
        ps.setInt(5, key);

        ps.executeUpdate();
    }

    private int findCoordinatesIDByMovie(Integer key) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT coordinatesID FROM movies WHERE key = ?");

        ps.setInt(1, key);

        ResultSet resultSet = ps.executeQuery();
        int coordinatesID = -1;
        if (resultSet.next()) {
            coordinatesID = resultSet.getInt("coordinatesID");
        }

        return coordinatesID;
    }

    private int findDirectorIDByMovie(Integer key) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT directorID FROM movies WHERE key = ?");

        ps.setInt(1, key);

        ResultSet resultSet = ps.executeQuery();
        int directorID = -1;
        if (resultSet.next()) {
            directorID = resultSet.getInt("directorID");
        }

        return directorID;
    }

    private void addOwner(int movieKey, String login) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO movie_owners (movieKey, ownerLogin) VALUES (?, ?)");

        ps.setInt(1, movieKey);
        ps.setString(2, login);

        ps.executeUpdate();
    }

    @Override
    public int addObject(String login, Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                          MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                          String passportID) throws SQLException {
        int coordinatesID = addCoordinates(x, y);
        int directorID = addPerson(directorName, birthday, weight, passportID);
        int movieID = addMovie(key, movieName, coordinatesID, oscarsCount, movieGenre, mpaaRating, directorID);
        addOwner(key, login);
        return movieID;
    }

    @Override
    public int placeObjectAtKey(String login, Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                         MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                         String passportID) throws SQLException {
        removeObjectByKey(key);
        int movieID = addObject(login, key, movieName, x, y, oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID);
        return movieID;
    }

    @Override
    public void updateObjectByID(Integer movieID, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                                 MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                                 String passportID) throws SQLException {
        Integer key = findMovieKeyByID(movieID);

        int coordinatesID = findCoordinatesIDByMovie(key);
        updateCoordinates(coordinatesID, x, y);

        int directorID = findDirectorIDByMovie(key);
        updatePerson(directorID, directorName, birthday, weight, passportID);

        updateMovie(key, movieName, oscarsCount, movieGenre, mpaaRating);
    }

    @Override
    public boolean removeObjectByKey(Integer movieKey) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM movies WHERE key = ?");

        ps.setInt(1, movieKey);

        int count = ps.executeUpdate();
        if (count == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isMovieKeyOwnedByUser(Integer movieKey, String login) {
        try {
            String ownerLogin = findOwnerByKey(movieKey);
            if (Objects.equals(ownerLogin, login)) {
                return true;
            }
        } catch (SQLException e) {
            // TODO: 17/5/2023 exception
            return false;
        }
        return false;
    }

    @Override
    public boolean isMovieIDOwnedByUser(Integer id, String login) {
        try {
            String ownerLogin = findOwnerByID(id);
            if (Objects.equals(ownerLogin, login)) {
                return true;
            }
        } catch (SQLException e) {
            // TODO: 17/5/2023 exception
            return false;
        }
        return false;
    }

    private String findOwnerByKey(Integer key) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT ownerLogin FROM movie_owners WHERE movieKey = ?");

        ps.setInt(1, key);

        ResultSet resultSet = ps.executeQuery();
        String login = null;
        if (resultSet.next()) {
            login = resultSet.getString("ownerLogin");
        }

        return login;
    }

    private String findOwnerByID(Integer movieID) throws SQLException {
        Integer key = findMovieKeyByID(movieID);
        return findOwnerByKey(key);
    }

    @Override
    public HashMap<Integer, Movie> getAllMovies() throws SQLException, InvalidDataException {
        HashMap<Integer, Movie> movies = new HashMap<>();

        PreparedStatement ps = connection.prepareStatement(
                "SELECT m.*, c.x, c.y, p.name AS directorName, p.birthday, p.weight, p.passportID FROM movies m " +
                        "JOIN coordinates c on m.coordinatesID = c.id " +
                        "JOIN person p on m.directorID = p.id"
        );

        ResultSet resultSet = ps.executeQuery();
        int i = 1;

        try {
            while (resultSet.next()) {
                Integer key = resultSet.getInt("key");
                Movie movie = resultSetToMovie(resultSet);
                movies.put(key, movie);
                i += 1;
            }
        } catch (WrongArgumentException e) {
            StringBuilder errorMessage = new StringBuilder(e.getMessage());
            errorMessage.delete(0, 2);
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            throw new InvalidDataException("movie №" + i + ": " + errorMessage);
        }

        return movies;
    }

    private Movie resultSetToMovie(ResultSet resultSet) throws SQLException, WrongArgumentException {

        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        Coordinates coordinates = resultSetToCoordinates(resultSet);

        Instant instant = resultSet.getTimestamp("creationDate").toInstant();
        ZonedDateTime creationDate = instant.atZone(ZoneId.systemDefault());
        long oscarsCount = resultSet.getLong("oscarsCount");
        MovieGenre genre = MovieGenre.valueOf(resultSet.getString("genre"));
        MpaaRating mpaaRating = MpaaRating.valueOf(resultSet.getString("mpaaRating"));

        Person director = resultSetToPerson(resultSet);

        Movie movie = new Movie(name, coordinates, oscarsCount, genre, mpaaRating, director);
        movie.setID(id);
        movie.setCreationDate(creationDate);

        return movie;
    }

    private Coordinates resultSetToCoordinates(ResultSet resultSet) throws SQLException {
        int x = resultSet.getInt("x");
        int y = resultSet.getInt("y");
        Coordinates coordinates = new Coordinates(x, y);
        return coordinates;
    }

    private Person resultSetToPerson(ResultSet resultSet) throws SQLException, WrongArgumentException {
        String directorName = resultSet.getString("directorName");
        LocalDateTime birthday = resultSet.getDate("birthday").toLocalDate().atStartOfDay();
        Integer weight = resultSet.getInt("weight");
        String passportID = resultSet.getString("passportID");
        Person director = new Person(directorName, birthday, weight, passportID);
        return director;
    }

    private Integer findMovieKeyByID(int movieID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT key FROM movies WHERE id = ?");

        ps.setInt(1, movieID);

        ResultSet resultSet = ps.executeQuery();
        int key = -1;
        if (resultSet.next()) {
            key = resultSet.getInt("key");
        }
        return key;
    }

    @Override
    public ArrayList<Integer> clearCollectionForUser(String login) throws SQLException {
        ArrayList<Integer> deletedMovieKeys = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT key FROM movies " +
                        "JOIN movie_owners mo on movies.key = mo.movieKey " +
                        "WHERE ownerLogin = ?");
        ps.setString(1, login);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            deletedMovieKeys.add(resultSet.getInt("key"));
        }

        ps = connection.prepareStatement(
                "DELETE FROM movies WHERE key IN (" +
                        "SELECT key FROM movies " +
                            "JOIN movie_owners mo on movies.key = mo.movieKey " +
                            "WHERE ownerLogin = ?)");
        ps.setString(1, login);
        ps.executeUpdate();

        return deletedMovieKeys;
    }
}