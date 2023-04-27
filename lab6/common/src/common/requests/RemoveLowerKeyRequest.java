package common.requests;

import java.io.Serial;

public class RemoveLowerKeyRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public final Integer key;

    public RemoveLowerKeyRequest(Integer key) {
        super("remove_lower_key");
        this.key = key;
    }
}
