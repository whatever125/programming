package sources.common.requests;

public class RemoveLowerKeyRequest extends Request {
    public final Integer key;

    public RemoveLowerKeyRequest(Integer key) {
        super("remove_lower_key");
        this.key = key;
    }
}
