package server.collection;

import common.models.Movie;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The MovieCollection class represents a collection of movies stored in a HashMap with Integer keys.
 * It provides methods for adding, removing, and retrieving movies from the collection.
 */
public class MovieCollection {
    private HashMap<Integer, Movie> movieHashMap = new HashMap<>();
    private java.time.ZonedDateTime creationDate;

    /**
     * Constructs an empty MovieCollection object and sets the creation date to the current time.
     */
    public MovieCollection() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Adds a movie to the collection with the specified key.
     *
     * @param key the key to associate with the movie
     * @param movie the movie to add to the collection
     */
    public synchronized void put(Integer key, Movie movie) {
        movieHashMap.put(key, movie);
    }

    /**
     * Returns the movie associated with the specified key, or null if the key is not present in the collection.
     *
     * @param key the key of the movie to retrieve
     * @return the movie associated with the key, or null if the key is not present in the collection
     */
    public synchronized Movie getElementByKey(Integer key) {
        return movieHashMap.get(key);
    }

    /**
     * Returns the movie with the specified ID, or null if no movie with the ID is present in the collection.
     *
     * @param id the ID of the movie to retrieve
     * @return the movie with the specified ID, or null if no movie with the ID is present in the collection
     */
    public synchronized Movie getElementByID(Integer id) {
        Optional<Movie> optionalMovie = movieHashMap.values().stream()
                .filter(movie -> Objects.equals(movie.getID(), id)).findFirst();
        return optionalMovie.orElse(null);
    }

    /**
     * Removes the movie associated with the specified key from the collection.
     *
     * @param key the key of the movie to remove
     */
    public synchronized void remove(Integer key) {
        movieHashMap.remove(key);
    }

    /**
     * Removes all movies from the collection.
     */
    @Deprecated
    public void clear() {
        movieHashMap.clear();
    }

    /**
     * Returns the HashMap containing the movies in the collection.
     *
     * @return the HashMap containing the movies in the collection
     */
    public synchronized HashMap<Integer, Movie> getMovieHashMap() {
        return movieHashMap;
    }

    /**
     * Returns the number of movies in the collection.
     *
     * @return the number of movies in the collection
     */
    public synchronized int length() {
        return movieHashMap.size();
    }

    /**
     * Returns the date and time the collection was created.
     *
     * @return the date and time the collection was created
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the date and time the collection was created.
     *
     * @param creationDate the new creation date and time
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Removes all movies with a rating greater than the specified movie from the collection.
     *
     * @param movie the movie to compare against
     * @return the number of movies removed from the collection
     */
    @Deprecated
    public int removeGreater(Movie movie) {
        Map<Integer, Movie> filteredMap = movieHashMap.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(movie) <= 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        int count = movieHashMap.size() - filteredMap.size();
        movieHashMap = new HashMap<>(filteredMap);
        return count;
    }

    /**
     * Replaces the value associated with the specified key in this map with
     * the specified value if the current value is greater than the specified value.
     *
     * @param key the key with which the specified value is associated
     * @param movie the value to be associated with the specified key
     * @return true if the value was replaced, false otherwise
     */
    @Deprecated
    public boolean replaceIfLowe(Integer key, Movie movie) {
        return movieHashMap.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getKey(), key) && entry.getValue().compareTo(movie) > 0)
                .map(entry -> movieHashMap.compute(key, (k, v) -> movie))
                .findFirst()
                .orElse(null) != null;
    }

    /**
     * Removes all the entries with keys less than the specified key from this map.
     *
     * @param key the key that serves as the lower bound for keys to be removed
     * @return the number of entries removed from the map
     */
    @Deprecated
    public int removeLowerKey(Integer key) {
        Map<Integer, Movie> filteredMap = movieHashMap.entrySet().stream()
                .filter(entry -> entry.getKey().compareTo(key) >= 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        int count = movieHashMap.size() - filteredMap.size();
        movieHashMap = new HashMap<>(filteredMap);
        return count;
    }

    /**
     * Returns a list of the values contained in this map, sorted in ascending
     * order based on their natural ordering.
     *
     * @return a list of the values contained in this map, sorted in ascending order
     */
    public synchronized List<Movie> printAscending() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        return movieList;
    }

    /**
     * Returns a list of the values contained in this map, sorted in descending
     * order based on their natural ordering.
     *
     * @return a list of the values contained in this map, sorted in descending order
     */
    public synchronized List<Movie> printDescending() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        Collections.reverse(movieList);
        return movieList;
    }

    /**
     * Returns a list of the values contained in this map, sorted in descending
     * order based on the number of Oscars won.
     *
     * @return a list of the values contained in this map, sorted in descending
     * order based on the number of Oscars won
     */
    public synchronized List<Movie> printFieldDescendingOscarsCount() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        movieList.sort((movie1, movie2) -> (int) (movie2.getOscarsCount() - movie1.getOscarsCount()));
        return movieList;
    }

    public synchronized void setMovieHashMap(HashMap<Integer, Movie> movieHashMap) {
        this.movieHashMap = movieHashMap;
    }
}
