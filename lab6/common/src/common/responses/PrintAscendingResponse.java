package common.responses;

import common.models.Movie;

import java.util.List;

public class PrintAscendingResponse extends Response {
    public final List<Movie> movieList;

    public PrintAscendingResponse(String error, List<Movie> movieList) {
        super("print_ascending", error);
        this.movieList = movieList;
    }
}
