package server.database;

import common.models.*;
import server.exceptions.db.UserAlreadyExistsException;
import server.exceptions.db.UserNotFoundException;
import server.exceptions.db.WrongPasswordException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;

public class PostgresConnection extends DatabaseConnection {
    protected PostgresConnection(String url, String login, String password) throws SQLException, IOException {
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

            if (Passwords.isExpectedPassword(passwordBytes, salt, expectedPassword)) {
                System.out.println(this.getClass() + " true");
            } else {
                System.out.println(this.getClass() + " false");
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

    @Override
    public boolean findUser(String login) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT 1 FROM users WHERE login = ?");
        ps.setString(1, login);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
    }

    @Override
    public int addCoordinates(int x, int y) throws SQLException {
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

    @Override
    public int addPerson(String name, LocalDateTime birthday, Integer weight, String passportID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO person (name, birthday, weight, passportid) VALUES (?, ?, ?, ?) RETURNING id");

        ps.setString(1, name);
        ps.setDate(2, java.sql.Date.valueOf(birthday.toLocalDate()));
        ps.setInt(3, weight);
        ps.setString(4, passportID);

        ps.executeUpdate();

        ResultSet resultSet = ps.executeQuery();
        int id = -1;
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }

        return id;
    }

    @Override
    public int addMovie(Integer key, String name, int coordinatesID, long oscarsCount, MovieGenre genre,
                        MpaaRating mpaaRating, int directorID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO movies (key, name, coordinates_id, oscarscount, genre, mpaarating, director_id) " +
                        "VALUES (?, ?, ?, ?, CAST(? AS movie_genre), CAST(? AS movie_genre), ?) RETURNING id");

        ps.setInt(1, key);
        ps.setString(2, name);
        ps.setInt(3, coordinatesID);
        ps.setLong(4, oscarsCount);
        ps.setString(5, genre.name());
        ps.setString(6, mpaaRating.name());
        ps.setInt(7, directorID);

        ps.executeUpdate();

        ResultSet resultSet = ps.executeQuery();
        int movieID = -1;
        if (resultSet.next()) {
            movieID = resultSet.getInt(1);
        }

        return movieID;
    }

    @Override
    public void addOwner(int movieID, int userID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO movie_owners (movie_id, owner_id) VALUES (?, ?)");

        ps.setInt(1, movieID);
        ps.setInt(2, userID);

        ps.executeUpdate();
    }

    @Override
    public void addObject(int userID, Integer key, Movie movie) throws SQLException {
        Coordinates coordinates = movie.getCoordinates();
        int coordinatesID = addCoordinates(coordinates.getX(), coordinates.getY());

        Person director = movie.getDirector();
        int directorID = addPerson(director.getName(), director.getBirthday(), director.getWeight(), director.getPassportID());

        int movieID = addMovie(key, movie.getName(), coordinatesID, movie.getOscarsCount(), movie.getGenre(), movie.getMpaaRating(), directorID);

        addOwner(movieID, userID);
    }
}