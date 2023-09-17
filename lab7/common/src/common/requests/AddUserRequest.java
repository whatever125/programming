package common.requests;

import java.io.Serial;

public class AddUserRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;

    public AddUserRequest(String login, String password) {
        super("add_user", login, password);
    }
}
