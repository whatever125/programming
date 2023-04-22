package common.requests;

public class RemoveKeyRequest extends Request {
    public final Integer key;

    public RemoveKeyRequest(Integer key) {
        super("remove_key");
        this.key = key;
    }
}
