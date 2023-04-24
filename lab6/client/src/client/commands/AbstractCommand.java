package client.commands;

import client.Client;
import server.Executor;

import java.io.Serializable;

public abstract class AbstractCommand implements Command, Serializable {
    final transient Client client;
    final String name;

    public AbstractCommand(String name, Client client) {
        this.name = name;
        this.client = client;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
