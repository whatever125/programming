package common.responses;

import java.io.Serial;

public class ClearResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public ClearResponse(String error) {
        super("clear", error);
    }
}
