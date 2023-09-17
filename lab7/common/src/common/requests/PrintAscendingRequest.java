package common.requests;

import java.io.Serial;

public class PrintAscendingRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public PrintAscendingRequest(String login, String password) {
        super("print_ascending", login, password);
    }
}
