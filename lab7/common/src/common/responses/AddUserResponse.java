package common.responses;

import java.io.Serial;

public class AuthenticateUserResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final boolean success;

    public AuthenticateUserResponse(String error, boolean success) {
        super("authenticate_user", error);
        this.success = success;
    }
}
