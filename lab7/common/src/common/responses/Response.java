package common.responses;

import java.io.Serializable;

public abstract class Response implements Serializable {
    final public String name;
    final public String error;

    public Response(String name, String error) {
        this.name = name;
        this.error = error;
    }
}
