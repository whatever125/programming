package sources.IOHandlers.client;

public interface BasicReader {
    String readLine();

    String readLine(String message);

    boolean hasNextLine();
}
