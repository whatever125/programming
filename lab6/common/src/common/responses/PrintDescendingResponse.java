package common.responses;

import common.models.Movie;

import java.io.Serial;
import java.util.List;

public class PrintDescendingResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final List<Movie> movieList;

    public PrintDescendingResponse(String error, List<Movie> movieList) {
        super("print_descending", error);
        this.movieList = movieList;
    }
}
