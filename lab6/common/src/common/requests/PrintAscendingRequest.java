package common.requests;

import java.io.Serial;

public class PrintAscendingRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public PrintAscendingRequest() {
        super("print_ascending");
    }
}
