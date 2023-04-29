package common.requests;

import java.io.Serializable;

public abstract class Request implements Serializable {
    final public String name;

    public Request(String name) {
        this.name = name;
    }
}
