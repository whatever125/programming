package common.responses;

public class RemoveLowerKeyResponse extends Response {
    public final int count;

    public RemoveLowerKeyResponse(String error, int count) {
        super("remove_lower_key", error);
        this.count = count;
    }
}
