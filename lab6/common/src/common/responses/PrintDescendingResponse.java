package common.responses;

import common.models.Movie;

import java.util.List;

public class PrintDescendingResponse extends Response {
    public final List<Movie> movieList;

    public PrintDescendingResponse(String error, List<Movie> movieList) {
        super("print_descending", error);
        this.movieList = movieList;
    }
}
