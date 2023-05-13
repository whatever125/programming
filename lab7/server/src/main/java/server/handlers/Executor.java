package server.handlers;

import server.IOHandlers.MovieCollectionFileReader;
import server.IOHandlers.MovieCollectionFileWriter;
import server.IOHandlers.MovieCollectionXMLFileReader;
import server.IOHandlers.MovieCollectionXMLFileWriter;
import server.exceptions.*;
import common.exceptions.WrongArgumentException;
import common.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Receiver class is responsible for managing the movie collection. It uses MovieCollection
 * to store the movies and MovieCollectionFileReader/MovieCollectionFileWriter to read/write the
 * movies from/to the XML file.
 */
public class Executor {
    private final MovieCollection movieCollection;
    MovieCollectionFileReader xmlFileReader;
    MovieCollectionFileWriter xmlFileWriter;

    /**
     * Creates a new Receiver instance and initializes the collection of movies from a file.
     *
     * @throws InvalidFileDataException if the data in the file is invalid
     * @throws FileNotFoundException    if the file cannot be found
     * @throws FilePermissionException  if the program does not have permission to access the file
     */
    public Executor() throws InvalidFileDataException, FileNotFoundException, FilePermissionException, EnvironmentVariableException {
        String path = System.getenv("PROGRAMMING_LAB");
        checkFile(path);

        this.xmlFileReader = new MovieCollectionXMLFileReader(path);
        this.xmlFileWriter = new MovieCollectionXMLFileWriter(path);

        this.movieCollection = xmlFileReader.read();
    }

    /**
     * Returns information about the collection.
     *
     * @return a String containing information about the collection
     */
    public String info() {
        return "*Collection info*\n" +
                "- Type of collection   : Hashmap of Movies\n" +
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
    public void insert(Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException {
        if (movieCollection.getElementByKey(key) != null)
            throw new CollectionKeyException("key already exists");
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        movie.setID();
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
    public void update(Integer id, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException {
        Movie movie = movieCollection.getElementByID(id);
        if (movie == null)
            throw new CollectionKeyException("id does not exist");
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
    public void removeKey(Integer key) throws CollectionKeyException {
        if (movieCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");
        movieCollection.remove(key);
    }

    /**
     Clears the movie collection.
     */
    public void clear() {
        movieCollection.clear();
    }

    /**
     Saves the movie collection to an XML file.
     */
    public void save() throws FileNotFoundException, CustomIOException, FilePermissionException {
        xmlFileWriter.write(movieCollection);
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
    public int removeGreater(String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                              MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                              String passportID) throws WrongArgumentException {
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        int count = movieCollection.removeGreater(movie);
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
    public boolean replaceIfLowe(Integer key, String movieName, Integer x, Integer y, long oscarsCount,
                              MovieGenre movieGenre, MpaaRating mpaaRating, String directorName, LocalDateTime birthday,
                              Integer weight, String passportID) throws CollectionKeyException, WrongArgumentException {
        if (movieCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        boolean replaced = movieCollection.replaceIfLowe(key, movie);
        if (replaced) {
            movie.setID();
        }
        return replaced;
    }

    /**
     Removes all elements with keys lower than the specified key from the movie collection.

     @param key the key to compare with
     */

    public int removeLowerKey(Integer key) {
        int count = movieCollection.removeLowerKey(key);
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

    /**
     Checks if the file at the specified path exists and can be read.

     @param path the path of the file to check
     @throws FileNotFoundException if the file does not exist
     @throws FilePermissionException if the file cannot be read
     */
    private void checkFile(String path) throws FileNotFoundException, FilePermissionException, EnvironmentVariableException {
        if (path == null) {
            throw new EnvironmentVariableException("Environment variable PROGRAMMING_LAB is null");
        }
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canRead())
            throw new FilePermissionException("! no read and/or write permission for file " + path + "  !");
    }
}
