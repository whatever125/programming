package common.responses;

import java.io.Serial;

public class RemoveLowerKeyResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final int count;

    public RemoveLowerKeyResponse(String error, int count) {
        super("remove_lower_key", error);
        this.count = count;
    }
}
