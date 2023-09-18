package common.requests;

import java.io.Serial;

public class InfoRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public InfoRequest(String login, String password) {
        super("info", login, password);
    }
}
