package common.responses;

import java.io.Serial;

public class AddUserResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;

    public AddUserResponse(String error) {
        super("add_user", error);
    }
}
