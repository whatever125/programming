package sources.commands;

public interface CommandWithResult<T> extends Command {
    T getResult();
}
