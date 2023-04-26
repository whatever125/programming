package common.responses;

import common.models.Movie;

import java.util.List;

public class PrintFieldDescendingOscarsCountResponse extends Response {
    public final List<Movie> movieList;

    public PrintFieldDescendingOscarsCountResponse(String error, List<Movie> movieList) {
        super("print_field_descending_oscars_count", error);
        this.movieList = movieList;
    }
}
