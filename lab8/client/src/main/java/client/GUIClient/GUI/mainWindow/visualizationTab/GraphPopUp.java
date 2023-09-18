package client.GUIClient.GUI.mainWindow.visualizationTab;

import client.GUIClient.GUI.mainWindow.movieDialog.UpdateMovieDialog;
import client.GUIClient.GUI.mainWindow.ElementPopUp;
import client.GUIClient.GUIClient;
import common.models.Movie;

import java.awt.*;

public class GraphPopUp extends ElementPopUp {
    protected final Integer key;
    protected final Movie movie;

    public GraphPopUp(Window owner, GUIClient client, Integer key, Movie movie) {
        super(owner, client);
        this.key = key;
        this.movie = movie;
    }

    @Override
    protected void updateMenuItemSettings() {
        updateMenuItem.addActionListener(e -> {
            updateAction(key, movie);
        });
    }

    @Override
    protected void removeMenuItemSettings() {
        removeMenuItem.addActionListener(e -> {
            removeAction(key);
        });
    }

    @Override
    protected void removeGreaterMenuItemSettings() {
        removeGreaterMenuItem.addActionListener(e -> {
            removeGreaterAction(movie);
        });
    }

    @Override
    protected void RILTMenuItemSettings() {
        RILTMenuItem.addActionListener(e -> {
            RILTAction(key);
        });
    }
}
