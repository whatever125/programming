package common.requests;

import java.io.Serializable;

public abstract class Request implements Serializable {
    final private String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
