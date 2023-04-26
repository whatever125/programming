package common.responses;

public class RemoveKeyResponse extends Response {
    public RemoveKeyResponse(String error) {
        super("remove_key", error);
    }
}
