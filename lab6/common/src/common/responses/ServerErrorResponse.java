package common.responses;

import java.io.Serial;

public class ServerErrorResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public ServerErrorResponse(String error) {
        super("error", error);
    }
}
