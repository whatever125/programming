package common.requests;

import java.io.Serializable;

public abstract class Request implements Serializable {
    final public String name;
    final public String login;
    final public String password;

    public Request(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
