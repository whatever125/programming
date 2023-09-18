package common.responses;

import java.io.Serial;

public class UpdateResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public UpdateResponse(String error) {
        super("update", error);
    }
}
