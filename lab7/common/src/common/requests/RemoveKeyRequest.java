package common.requests;

import java.io.Serial;

public class RemoveKeyRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public final Integer key;

    public RemoveKeyRequest(String login, String password, Integer key) {
        super("remove_key", login, password);
        this.key = key;
    }
}
