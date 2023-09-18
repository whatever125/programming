package common.requests;

import java.io.Serial;

public class ShowRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public ShowRequest(String login, String password) {
        super("show", login, password);
    }
}
