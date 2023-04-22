package sources.common.requests;

import sources.models.MovieGenre;
import sources.models.MpaaRating;

import java.time.LocalDateTime;

public class UpdateRequest extends Request {
    public final Integer id;
    public final String movieName;
    public final Integer x;
    public final Integer y;
    public final long oscarsCount;
    public final MovieGenre movieGenre;
    public final MpaaRating mpaaRating;
    public final String directorName;
    public final LocalDateTime birthday;
    public final Integer weight;
    public final String passportID;

    public UpdateRequest(Integer id, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre, MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight, String passportID) {
        super("update");
        this.id = id;
        this.movieName = movieName;
        this.x = x;
        this.y = y;
        this.oscarsCount = oscarsCount;
        this.movieGenre = movieGenre;
        this.mpaaRating = mpaaRating;
        this.directorName = directorName;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
    }
}
