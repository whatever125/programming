package common.responses;

public class RemoveGreaterResponse extends Response {
    public final int count;

    public RemoveGreaterResponse(String error, int count) {
        super("remove_greater", error);
        this.count = count;
    }
}
