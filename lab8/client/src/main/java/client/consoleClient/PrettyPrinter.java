package client.consoleClient;

import common.models.Movie;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 * The PrettyPrinter class contains methods for printing movie data in a user-friendly way.
 */
public class PrettyPrinter {
    static final String dateTimeFormat = "dd.MM.yy HH:mm";
    static final String birthdayTimeFormat = "dd.MM.yyyy";
    static final String keyFormat = "%6s |";
    static final String movieIDFormat = "%6s |";
    static final String movieNameFormat = "%16s |";
    static final String XFormat = "%7s |";
    static final String YFormat = "%7s |";
    static final String dateFormat = "%15s |";
    static final String oscarsFormat = "%7s |";
    static final String genreFormat = "%9s |";
    static final String ratingFormat = "%7s |";
    static final String directorNameFormat = "%21s |";
    static final String birthdayFormat = "%12s |";
    static final String weightFormat = "%10s |";
    static final String passportIDFormat = "%20s |";
    static final String ownerFormat = "%16s |";

    /**
     * Prints the movie data stored in a HashMap in a user-friendly way.
     *
     * @param hashMap the HashMap containing the movie data
     */
    public static String printMovieHashMap(HashMap<Integer, Movie> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format(keyFormat, "Key"));
        stringBuilder.append(printMovieParamNames());
        stringBuilder.append("-".repeat(187)).append("\n");

        for (Integer key : hashMap.keySet()) {
            Movie movie = hashMap.get(key);
            stringBuilder.append(String.format(keyFormat, key));
            stringBuilder.append(printMovieParams(movie));
        }

        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

        return stringBuilder.toString();
    }

    /**
     * Prints the movie data stored in a List in a user-friendly way.
     *
     * @param movieList the List containing the movie data
     */
    public static String printMovieList(List<Movie> movieList) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(printMovieParamNames());
        stringBuilder.append("-".repeat(158)).append("\n");

        for (Movie movie : movieList) {
            stringBuilder.append(printMovieParams(movie));
        }

        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

        return stringBuilder.toString();
    }

    /**
     * Prints the movie data stored in a List in a user-friendly way,
     * including the number of Oscars each movie has won.
     *
     * @param movieList the List containing the movie data
     */
    public static String printMovieListOscars(List<Movie> movieList) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format(movieIDFormat, "ID"));
        stringBuilder.append(String.format(movieNameFormat, "movieName"));
        stringBuilder.append(String.format(oscarsFormat, "oscars"));
        stringBuilder.append('\n');
        stringBuilder.append("-".repeat(33)).append('\n');

        for (Movie movie : movieList) {
            stringBuilder.append(String.format(movieIDFormat, movie.getID()));
            stringBuilder.append(String.format(movieNameFormat, movie.getName()));
            stringBuilder.append(String.format(oscarsFormat, movie.getOscarsCount()));
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    
    private static String printMovieParamNames() {

        String stringBuilder = String.format(movieIDFormat, "ID") +
                String.format(movieNameFormat, "movieName") +
                String.format(XFormat, "coordX") +
                String.format(YFormat, "coordY") +
                String.format(dateFormat, "creationDate") +
                String.format(oscarsFormat, "oscars") +
                String.format(genreFormat, "genre") +
                String.format(ratingFormat, "rating") +
                String.format(directorNameFormat, "dirName") +
                String.format(birthdayFormat, "dirBirthday") +
                String.format(weightFormat, "dirWeight") +
                String.format(passportIDFormat, "dirPassportID") +
                String.format(ownerFormat, "owner") +
                '\n';

        return stringBuilder;
    }

    private static String printMovieParams(Movie movie) {

        String stringBuilder = String.format(movieIDFormat, movie.getID()) +
                String.format(movieNameFormat, movie.getName()) +
                String.format(XFormat, movie.getCoordinates().getX()) +
                String.format(YFormat, movie.getCoordinates().getY()) +
                String.format(dateFormat, movie.getCreationDate().format(DateTimeFormatter.ofPattern(dateTimeFormat))) +
                String.format(oscarsFormat, movie.getOscarsCount()) +
                String.format(genreFormat, movie.getGenre()) +
                String.format(ratingFormat, movie.getMpaaRating()) +
                String.format(directorNameFormat, movie.getDirector().getName()) +
                String.format(birthdayFormat, movie.getDirector().getBirthday().format(DateTimeFormatter.ofPattern(birthdayTimeFormat))) +
                String.format(weightFormat, movie.getDirector().getWeight()) +
                String.format(passportIDFormat, movie.getDirector().getPassportID()) +
                String.format(ownerFormat, movie.getOwner()) +
                '\n';

        return stringBuilder;
    }
}
