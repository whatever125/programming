package common.requests;

import java.io.Serial;

public class PrintDescendingRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public PrintDescendingRequest() {
        super("print_descending");
    }
}
