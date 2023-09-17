package common.requests;

import java.io.Serial;

public class RemoveLowerKeyRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public final Integer key;

    public RemoveLowerKeyRequest(String login, String password, Integer key) {
        super("remove_lower_key", login, password);
        this.key = key;
    }
}
