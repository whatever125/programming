package common.responses;

import java.io.Serial;

public class RemoveGreaterResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final Integer count;

    public RemoveGreaterResponse(String error, Integer count) {
        super("remove_greater", error);
        this.count = count;
    }
}
