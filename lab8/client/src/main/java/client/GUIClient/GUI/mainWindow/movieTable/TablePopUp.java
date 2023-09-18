package client.GUIClient.GUI.mainWindow.movieTable;

import client.GUIClient.GUI.mainWindow.ElementPopUp;
import client.GUIClient.GUI.mainWindow.movieDialog.MovieDialog;
import client.GUIClient.GUI.mainWindow.movieDialog.UpdateMovieDialog;
import client.GUIClient.GUIClient;
import common.models.Movie;

import java.awt.*;

public class TablePopUp extends ElementPopUp {
    protected final MovieTable table;

    public TablePopUp(MovieTable table, Window owner, GUIClient client) {
        super(owner, client);
        this.table = table;
    }

    @Override
    protected void updateMenuItemSettings() {
        updateMenuItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Integer key = (Integer) table.getValueAt(selectedRow, 0);
                Movie movie = table.getModel().getTableData().get(key);

                updateAction(key, movie);
            }
        });
    }

    @Override
    protected void removeMenuItemSettings() {
        removeMenuItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Integer key = (Integer) table.getValueAt(selectedRow, 0);
                removeAction(key);
            }
        });
    }

    @Override
    protected void removeGreaterMenuItemSettings() {
        removeGreaterMenuItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            Integer key = (Integer) table.getValueAt(selectedRow, 0);
            if (selectedRow != -1) {
                Movie movie = table.getModel().getTableData().get(key);
                removeGreaterAction(movie);
            }
        });
    }

    @Override
    protected void RILTMenuItemSettings() {
        RILTMenuItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Integer key = (Integer) table.getValueAt(selectedRow, 0);
                RILTAction(key);
            }
        });
    }
}
