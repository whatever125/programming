package common.requests;

import java.io.Serial;

public class AuthenticateUserRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthenticateUserRequest(String login, String password) {
        super("authenticate_user", login, password);
    }
}
