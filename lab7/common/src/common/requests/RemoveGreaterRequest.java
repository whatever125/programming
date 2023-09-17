package common.requests;

import common.models.MovieGenre;
import common.models.MpaaRating;

import java.io.Serial;
import java.time.LocalDateTime;

public class RemoveGreaterRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
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

    public RemoveGreaterRequest(String login, String password, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                         MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                         String passportID) {
        super("remove_greater", login, password);
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
