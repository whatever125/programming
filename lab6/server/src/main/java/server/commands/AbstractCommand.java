package server.commands;

import server.handlers.Executor;

import java.io.Serializable;

public abstract class AbstractCommand implements Command, Serializable {
    final transient Executor executor;
    final String name;

    public AbstractCommand(String name, Executor executor) {
        this.name = name;
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
