package common.responses;

import java.io.Serial;

public class AuthenticateUserResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthenticateUserResponse(String error) {
        super("authenticate_user", error);
    }
}
