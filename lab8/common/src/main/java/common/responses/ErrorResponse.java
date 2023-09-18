package common.responses;

import java.io.Serial;

public class ErrorResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public ErrorResponse(String error) {
        super("error", error);
    }
}
