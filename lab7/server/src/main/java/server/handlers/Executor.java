package server.handlers;

import server.IOHandlers.*;
import server.collection.DatabaseConnection;
import server.collection.MovieCollection;
import server.exceptions.*;
import common.exceptions.WrongArgumentException;
import common.models.*;
import server.exceptions.IOHandlers.IOHandlerException;
import server.exceptions.IOHandlers.SourcePermissionException;
import server.exceptions.IOHandlers.InvalidDataException;
import server.exceptions.db.AuthorizationException;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The Receiver class is responsible for managing the movie collection. It uses MovieCollection
 * to store the movies and MovieCollectionFileReader/MovieCollectionFileWriter to read/write the
 * movies from/to the XML file.
 */
public class Executor {
    protected final MovieCollection movieCollection;
    protected MovieCollectionReader movieCollectionReader;
    protected MovieCollectionWriter movieCollectionWriter;

    protected final DatabaseConnection dbConnection;

    /**
     * Creates a new Receiver instance and initializes the collection of movies from a file.
     *
     * @throws InvalidDataException if the data in the file is invalid
     * @throws FileNotFoundException    if the file cannot be found
     * @throws SourcePermissionException  if the program does not have permission to access the file
     */
    public Executor(MovieCollectionReader movieCollectionReader, MovieCollectionWriter movieCollectionWriter,
                    DatabaseConnection dbConnection) throws IOHandlerException, EnvironmentVariableException {
        this.dbConnection = dbConnection;
        this.movieCollectionReader = movieCollectionReader;
        this.movieCollectionWriter = movieCollectionWriter;

        this.movieCollection = movieCollectionReader.read();
    }

    /**
     * Returns information about the collection.
     *
     * @return a String containing information about the collection
     */
    public String info() {
        // TODO: 17/5/2023 create class MovieCollectionInfo
        return "*Collection info*\n" +
                "- Type of collection   : PostgreSQL Database of Movies\n" +
                "- Date of initializing : " + movieCollection.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n" +
                "- Number of elements   : " + movieCollection.length();
    }

    /**
     * Returns the HashMap of movies in the collection.
     *
     * @return the HashMap of movies in the collection
     */
    public HashMap<Integer, Movie> show() {
        return movieCollection.getMovieHashMap();
    }

    /**
     * Inserts a new movie into the collection.
     *
     * @param key the key of the new movie
     * @param movieName the name of the new movie
     * @param x the x coordinate of the new movie
     * @param y the y coordinate of the new movie
     * @param oscarsCount the number of Oscars the new movie has won
     * @param movieGenre the genre of the new movie
     * @param mpaaRating the MPAA rating of the new movie
     * @param directorName the name of the director of the new movie
     * @param birthday the birthday of the director of the new movie
     * @param weight the weight of the director of the new movie
     * @param passportID the passport ID of the director of the new movie
     * @throws CollectionKeyException if the specified key is already in use
     * @throws WrongArgumentException if any of the arguments are invalid
     */
    public synchronized void insert(String login, Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException, SQLException {
        if (movieCollection.getElementByKey(key) != null)
            throw new CollectionKeyException("key already exists");
        int movieID = dbConnection.addObject(login, key, movieName, x, y, oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID);
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        movie.setID(movieID);
        movieCollection.put(key, movie);
    }

    /**
     * Updates the information of the specified movie in the collection.
     *
     * @param id the ID of the movie to be updated
     * @param movieName the new name of the movie
     * @param x the new x coordinate of the movie
     * @param y the new y coordinate of the movie
     * @param oscarsCount the new number of Oscars the movie has won
     * @param movieGenre the new genre of the movie
     * @param mpaaRating the new MPAA rating of the movie
     * @param directorName the new name of the director of the movie
     * @param birthday the new birthday of the director of the movie
     * @param weight the new weight of the director of the movie
     * @param passportID the new passport ID of the director of the movie
     * @throws CollectionKeyException if the specified ID is not found in the collection
     * @throws WrongArgumentException if any of the arguments are invalid
     */
    public synchronized void update(String login, Integer id, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException, SQLException {
        Movie movie = movieCollection.getElementByID(id);
        if (movie == null)
            throw new CollectionKeyException("id does not exist");

        authorizeUserForMovieID(login, id);
        dbConnection.updateObjectByID(id, movieName, x, y, oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID);

        movie.setName(movieName);
        movie.setCoordinates(new Coordinates(x, y));
        movie.setOscarsCount(oscarsCount);
        movie.setGenre(movieGenre);
        movie.setMpaaRating(mpaaRating);
        movie.setDirector(new Person(directorName, birthday, weight, passportID));
    }

    /**
     * Removes the element with the specified key from the movie collection.
     *
     * @param key the key of the element to remove
     * @throws CollectionKeyException if the specified key does not exist in the collection
     */
    public synchronized void removeKey(String login, Integer key) throws CollectionKeyException, SQLException {
        if (movieCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");

        authorizeUserForMovieKey(login, key);
        dbConnection.removeObjectByKey(key);
        movieCollection.remove(key);
    }

    /**
     Clears the movie collection.
     */
    public synchronized void clear(String login) throws SQLException {
        ArrayList<Integer> removedMovies = dbConnection.clearCollectionForUser(login);
        for (Integer key: removedMovies) {
            movieCollection.remove(key);
        }
    }

    /**
     Saves the movie collection to an XML file.
     */
    @Deprecated
    public void save() throws IOHandlerException {
//        xmlFileWriter.write(movieCollection);
    }

    /**
     Removes all elements that are greater than the specified movie from the collection.

     @param movieName the name of the movie to compare
     @param x the x-coordinate of the movie to compare
     @param y the y-coordinate of the movie to compare
     @param oscarsCount the number of Oscars won by the movie to compare
     @param movieGenre the genre of the movie to compare
     @param mpaaRating the MPAA rating of the movie to compare
     @param directorName the name of the movie director to compare
     @param birthday the birthdate of the movie director to compare
     @param weight the weight of the movie director to compare
     @param passportID the passport ID of the movie director to compare
     @throws WrongArgumentException if any of the specified arguments are invalid
     */
    public int removeGreater(String login, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                              MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                              String passportID) throws WrongArgumentException, SQLException {
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));

        int count = 0;

        Integer[] keys = movieCollection.getMovieHashMap().keySet().toArray(new Integer[0]);
        for (Integer currentKey: keys) {
            System.out.println(movieCollection.getElementByKey(currentKey).compareTo(movie));
            if (dbConnection.isMovieKeyOwnedByUser(currentKey, login) && movieCollection.getElementByKey(currentKey).compareTo(movie) >= 0) {
                if (dbConnection.removeObjectByKey(currentKey)) {
                    movieCollection.remove(currentKey);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     Replaces the element with the specified key with the specified movie if it is lower than the original element.

     @param key the key of the element to replace
     @param movieName the name of the movie to replace the original element with
     @param x the x-coordinate of the movie to replace the original element with
     @param y the y-coordinate of the movie to replace the original element with
     @param oscarsCount the number of Oscars won by the movie to replace the original element with
     @param movieGenre the genre of the movie to replace the original element with
     @param mpaaRating the MPAA rating of the movie to replace the original element with
     @param directorName the name of the movie director to replace the original element with
     @param birthday the birthdate of the movie director to replace the original element with
     @param weight the weight of the movie director to replace the original element with
     @param passportID the passport ID of the movie director to replace the original element with
     @throws CollectionKeyException if the specified key does not exist in the collection
     @throws WrongArgumentException if any of the specified arguments are invalid
     */
    public boolean replaceIfLowe(String login, Integer key, String movieName, Integer x, Integer y, long oscarsCount,
                              MovieGenre movieGenre, MpaaRating mpaaRating, String directorName, LocalDateTime birthday,
                              Integer weight, String passportID) throws CollectionKeyException, WrongArgumentException, SQLException {
        if (movieCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");

        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        System.out.println(login);
        boolean ableToReplace = movieCollection.getMovieHashMap().entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getKey(), key) && dbConnection.isMovieKeyOwnedByUser(entry.getKey(), login) && entry.getValue().compareTo(movie) > 0)
                .findFirst()
                .orElse(null) != null;
        System.out.println();
        System.out.println(ableToReplace);
        if (ableToReplace) {
            int movieID = dbConnection.placeObjectAtKey(login, key, movieName, x, y, oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID);
            movie.setID(movieID);
            movieCollection.put(key, movie);
            return true;
        }
        return false;
    }

    /**
     Removes all elements with keys lower than the specified key from the movie collection.

     @param key the key to compare with
     */

    public int removeLowerKey(String login, Integer key) throws SQLException {
        int count = 0;

        Integer[] keys = movieCollection.getMovieHashMap().keySet().toArray(new Integer[0]);
        for (Integer currentKey : keys) {
            if (dbConnection.isMovieKeyOwnedByUser(currentKey, login) && currentKey < key) {
                if (dbConnection.removeObjectByKey(currentKey)) {
                    movieCollection.remove(currentKey);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     Returns a list of movies in ascending order based on their natural order in the movie collection.

     @return a list of movies in ascending order
     */
    public List<Movie> printAscending() {
        return movieCollection.printAscending();
    }

    /**
     Returns a list of movies in descending order based on their natural order in the movie collection.

     @return a list of movies in descending order
     */
    public List<Movie> printDescending() {
        return movieCollection.printDescending();
    }

    /**
     Returns a list of movies in descending order based on their Oscars count field in the movie collection.

     @return a list of movies in descending order based on their Oscars count field
     */
    public List<Movie> printFieldDescendingOscarsCount() {
        return movieCollection.printFieldDescendingOscarsCount();
    }

    public void authenticateUser(String login, String password) throws SQLException {
        dbConnection.authenticateUser(login, password);
    }

    public void addUser(String login, String password) throws SQLException {
        dbConnection.addUser(login, password);
    }

    private void authorizeUserForMovieKey(String login, Integer movieKey) throws SQLException {
        boolean success = dbConnection.isMovieKeyOwnedByUser(movieKey, login);
        if (!success) {
            throw new AuthorizationException("Unable to execute command: " + login + " is not the owner");
        }
    }

    private void authorizeUserForMovieID(String login, Integer movieID) throws SQLException {
        boolean success = dbConnection.isMovieIDOwnedByUser(movieID, login);
        if (!success) {
            throw new AuthorizationException("Unable to execute command: " + login + " is not the owner");
        }
    }
}
