package common.requests;

import java.io.Serial;

public class PrintFieldDescendingOscarsCountRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public PrintFieldDescendingOscarsCountRequest(String login, String password) {
        super("print_field_descending_oscars_count", login, password);
    }
}
