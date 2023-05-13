package common.requests;

import java.io.Serial;

public class RemoveKeyRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public final Integer key;

    public RemoveKeyRequest(Integer key) {
        super("remove_key");
        this.key = key;
    }
}
