package common.responses;

import common.models.Movie;

import java.io.Serial;
import java.util.List;

public class PrintAscendingResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final List<Movie> movieList;

    public PrintAscendingResponse(String error, List<Movie> movieList) {
        super("print_ascending", error);
        this.movieList = movieList;
    }
}
