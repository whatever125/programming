package sources;

import sources.client.ConsoleClient;

/**
 * The Main class is responsible for running the application by creating a ConsoleClient object
 * and calling its main method.
 */
public class Main {
    /**
     * The main method creates a ConsoleClient object and calls its main method.
     * @param args an array of command-line arguments
     */
    public static void main(String[] args) {
        // voice:.idea/1695332137180.wav
        ConsoleClient consoleClient = new ConsoleClient();
        consoleClient.main();
    }
}
