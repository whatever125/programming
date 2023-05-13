package common.requests;

import java.io.Serial;

public class PrintFieldDescendingOscarsCountRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public PrintFieldDescendingOscarsCountRequest() {
        super("print_field_descending_oscars_count");
    }
}
