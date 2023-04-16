package sources.receiver;

import sources.models.Movie;

import java.time.ZonedDateTime;
import java.util.*;

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
    public void put(Integer key, Movie movie) {
        movieHashMap.put(key, movie);
    }

    /**
     * Returns the movie associated with the specified key, or null if the key is not present in the collection.
     *
     * @param key the key of the movie to retrieve
     * @return the movie associated with the key, or null if the key is not present in the collection
     */
    public Movie getElementByKey(Integer key) {
        return movieHashMap.get(key);
    }

    /**
     * Returns the movie with the specified ID, or null if no movie with the ID is present in the collection.
     *
     * @param id the ID of the movie to retrieve
     * @return the movie with the specified ID, or null if no movie with the ID is present in the collection
     */
    public Movie getElementByID(Integer id) {
        for (Movie movie : movieHashMap.values()) {
            if (Objects.equals(movie.getID(), id))
                return movie;
        }
        return null;
    }

    /**
     * Removes the movie associated with the specified key from the collection.
     *
     * @param key the key of the movie to remove
     */
    public void remove(Integer key) {
        movieHashMap.remove(key);
    }

    /**
     * Removes all movies from the collection.
     */
    public void clear() {
        movieHashMap.clear();
    }

    /**
     * Returns the HashMap containing the movies in the collection.
     *
     * @return the HashMap containing the movies in the collection
     */
    public HashMap<Integer, Movie> getMovieHashMap() {
        return movieHashMap;
    }

    /**
     * Returns the number of movies in the collection.
     *
     * @return the number of movies in the collection
     */
    public int length() {
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
    public int removeGreater(Movie movie) {
        HashMap<Integer, Movie> newMovieHashMap = new HashMap<>(movieHashMap);
        int count = 0;
        for (Integer key : movieHashMap.keySet()) {
            if (getElementByKey(key).compareTo(movie) > 0) {
                newMovieHashMap.remove(key);
                count += 1;
            }
        }
        movieHashMap = newMovieHashMap;
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
    public boolean replaceIfLowe(Integer key, Movie movie) {
        if (getElementByKey(key).compareTo(movie) > 0) {
            put(key, movie);
            return true;
        }
        return false;
    }

    /**
     * Removes all the entries with keys less than the specified key from this map.
     *
     * @param key the key that serves as the lower bound for keys to be removed
     * @return the number of entries removed from the map
     */
    public int removeLowerKey(Integer key) {
        HashMap<Integer, Movie> newMovieHashMap = new HashMap<>(movieHashMap);
        int count = 0;
        for (Integer i : movieHashMap.keySet()) {
            if (i < key) {
                newMovieHashMap.remove(i);
                count += 1;
            }
        }
        movieHashMap = newMovieHashMap;
        return count;
    }

    /**
     * Returns a list of the values contained in this map, sorted in ascending
     * order based on their natural ordering.
     *
     * @return a list of the values contained in this map, sorted in ascending order
     */
    public List<Movie> printAscending() {
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
    public List<Movie> printDescending() {
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
    public List<Movie> printFieldDescendingOscarsCount() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        movieList.sort(new Comparator<>() {
            public int compare(Movie o1, Movie o2) {
                return (int) (o2.getOscarsCount() - o1.getOscarsCount());
            }
        });
        return movieList;
    }
}
