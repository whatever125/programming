package client.GUIClient.GUI.mainWindow;

import client.GUIClient.GUI.mainWindow.movieDialog.RILTMovieDialog;
import client.GUIClient.GUI.mainWindow.movieDialog.UpdateMovieDialog;
import client.GUIClient.GUIClient;
import client.commands.*;
import client.exceptions.ErrorResponseException;
import common.models.Movie;
import common.responses.RemoveGreaterResponse;
import common.responses.RemoveKeyResponse;
import common.responses.Response;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public abstract class ElementPopUp extends JPopupMenu {
    protected JMenuItem updateMenuItem;
    protected JSeparator separator;
    protected JMenuItem removeMenuItem;
    protected JMenuItem removeGreaterMenuItem;
    protected JMenuItem RILTMenuItem;

    protected final Window owner;
    protected final GUIClient client;

    public ElementPopUp(Window owner, GUIClient client) {
        this.owner = owner;
        this.client = client;

        initComponents();
        updateMenuItemSettings();
        removeMenuItemSettings();
        removeGreaterMenuItemSettings();
        RILTMenuItemSettings();
    }

    protected abstract void updateMenuItemSettings();

    protected void updateAction(Integer key, Movie movie) {
        UpdateMovieDialog dialog = new UpdateMovieDialog(owner, client, key, movie);
        dialog.setVisible(true);
    }


    protected abstract void removeMenuItemSettings();

    protected void removeAction(Integer key) {
        JDialog loadingDialog = new LoadingDialog((Frame) owner, client.getLocale());
        RemoveTask task = new RemoveTask(loadingDialog, key);
        task.execute();
        loadingDialog.setVisible(true);
    }

    protected class RemoveTask extends SwingWorker<Response, Void> {
        private final JDialog loadingDialog;
        private final Integer key;

        public RemoveTask(JDialog loadingDialog, Integer key) {
            this.loadingDialog = loadingDialog;
            this.key = key;
        }

        @Override
        protected Response doInBackground() throws Exception {
            AbstractCommand command = new RemoveKey(client, client.getNetworkClient(), client.getLogin(), client.getPassword(), key);
            Response abstractResponse = client.getInvoker().execute(command);
            return abstractResponse;
        }

        @Override
        protected void done() {
            try {
                loadingDialog.dispose();
                Response abstractResponse = get();
                client.handleErrorResponse(abstractResponse);
                RemoveKeyResponse response = (RemoveKeyResponse) abstractResponse;
                if (response.error == null) {
                    JOptionPane.showMessageDialog(
                            owner,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.remove.ok.message"),
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.remove.ok.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            owner,
                            response.error,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.remove.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (ErrorResponseException | InterruptedException | ExecutionException e) {
                JOptionPane.showMessageDialog(
                        owner,
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.remove.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    protected abstract void removeGreaterMenuItemSettings();

    protected void removeGreaterAction(Movie movie) {
        JDialog loadingDialog = new LoadingDialog((Frame) owner, client.getLocale());
        RemoveGreaterTask task = new RemoveGreaterTask(loadingDialog, movie);
        task.execute();
        loadingDialog.setVisible(true);
    }

    protected class RemoveGreaterTask extends SwingWorker<Response, Void> {
        private final JDialog loadingDialog;
        private final Movie movie;

        public RemoveGreaterTask(JDialog loadingDialog, Movie movie) {
            this.loadingDialog = loadingDialog;
            this.movie = movie;
        }

        @Override
        protected Response doInBackground() throws Exception {
            AbstractCommand command = new RemoveGreater(
                    client, client.getNetworkClient(), client.getLogin(), client.getPassword(),
                    movie.getName(), movie.getCoordinates().getX(), movie.getCoordinates().getY(),
                    movie.getOscarsCount(), movie.getGenre(), movie.getMpaaRating(), movie.getDirector().getName(),
                    movie.getDirector().getBirthday(), movie.getDirector().getWeight(), movie.getDirector().getPassportID());
            Response abstractResponse = client.getInvoker().execute(command);
            return abstractResponse;
        }

        @Override
        protected void done() {
            try {
                loadingDialog.dispose();
                Response abstractResponse = get();
                client.handleErrorResponse(abstractResponse);
                RemoveGreaterResponse response = (RemoveGreaterResponse) abstractResponse;
                if (response.error == null) {
                    JOptionPane.showMessageDialog(
                            owner,
                            response.count == 0
                                    ? ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.removeGreater.ok.false.message")
                                    : ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.removeGreater.ok.true.message"),
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.removeGreater.ok.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            owner,
                            response.error,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.removeGreater.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (ErrorResponseException | InterruptedException | ExecutionException e) {
                JOptionPane.showMessageDialog(
                        owner,
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.removeGreater.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    protected abstract void RILTMenuItemSettings();

    protected void RILTAction(Integer oldKey) {
        RILTMovieDialog movieDialog = new RILTMovieDialog(owner, client, oldKey);
        movieDialog.setVisible(true);
    }


    protected void initComponents() {
        updateMenuItem = new JMenuItem();
        separator = new JSeparator();
        removeMenuItem = new JMenuItem();
        removeGreaterMenuItem = new JMenuItem();
        RILTMenuItem = new JMenuItem();

        //======== this ========

        //---- updateMenuItem ----
        updateMenuItem.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.update"));
        add(updateMenuItem);
        add(separator);

        //---- removeMenuItem ----
        removeMenuItem.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.remove"));
        add(removeMenuItem);

        //---- removeGreaterMenuItem ----
        removeGreaterMenuItem.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.removeGreater"));
        add(removeGreaterMenuItem);

        //---- RILTMenuItem ----
        RILTMenuItem.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("popUp.RILT"));
        add(RILTMenuItem);
    }
}
