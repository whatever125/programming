package client.commands;

import client.Client;
import server.Executor;

import java.io.Serializable;

public abstract class AbstractCommand implements Command, Serializable {
    final transient Client client;
    final transient Executor executor;
    final String name;

    public AbstractCommand(String name, Client client, Executor executor) {
        this.name = name;
        this.client = client;
        this.executor = executor;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
