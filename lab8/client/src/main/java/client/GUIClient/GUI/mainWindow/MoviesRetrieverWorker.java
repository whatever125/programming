package client.GUIClient.GUI.mainWindow;

import client.GUIClient.GUI.mainWindow.MainWindow;
import client.GUIClient.GUIClient;
import client.exceptions.CustomIOException;
import client.exceptions.ErrorResponseException;
import client.exceptions.NetworkClientException;
import common.models.Movie;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class MoviesRetrieverWorker extends SwingWorker<Void, HashMap<Integer, Movie>> {
    private static final int SECONDS = 1000;
    private boolean running;
    private final GUIClient client;
    private final MainWindow mainWindow;

    public MoviesRetrieverWorker(GUIClient client, MainWindow mainWindow) {
        this.client = client;
        this.mainWindow = mainWindow;
    }

    @Override
    protected Void doInBackground() throws Exception {
        running = true;

        while (running) {
            // todo add exceptions
            HashMap<Integer, Movie> data = retrieveData();
            publish(data);
            // todo
            Thread.sleep(3 * SECONDS);
        }

        return null;
    }

    @Override
    protected void process(List<HashMap<Integer, Movie>> chunks) {
        HashMap<Integer, Movie> movieHashMap = chunks.get(0);
        mainWindow.updateData(movieHashMap);
    }

    public void stopRetrievingData() {
        running = false;
    }

    private HashMap<Integer, Movie> retrieveData() throws ErrorResponseException, NetworkClientException, CustomIOException {
        return client.retrieveMovies();
    }
}