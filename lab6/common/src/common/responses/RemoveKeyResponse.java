package common.responses;

import java.io.Serial;

public class RemoveKeyResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public RemoveKeyResponse(String error) {
        super("remove_key", error);
    }
}
