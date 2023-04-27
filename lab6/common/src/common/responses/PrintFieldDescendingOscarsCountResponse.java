package common.responses;

import common.models.Movie;

import java.io.Serial;
import java.util.List;

public class PrintFieldDescendingOscarsCountResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final List<Movie> movieList;

    public PrintFieldDescendingOscarsCountResponse(String error, List<Movie> movieList) {
        super("print_field_descending_oscars_count", error);
        this.movieList = movieList;
    }
}
