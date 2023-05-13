package common.responses;

import common.models.Movie;

import java.io.Serial;
import java.util.HashMap;

public class ShowResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    private final HashMap<Integer, Movie> movieHashMap;

    public ShowResponse(String error, HashMap<Integer, Movie> movieHashMap) {
        super("show", error);
        this.movieHashMap = movieHashMap;
    }

    public HashMap<Integer, Movie> getMovieHashMap() {
        return movieHashMap;
    }
}
