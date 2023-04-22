package sources.server.commands;

public interface CommandWithResult<T> extends Command {
    T getResult();
}
