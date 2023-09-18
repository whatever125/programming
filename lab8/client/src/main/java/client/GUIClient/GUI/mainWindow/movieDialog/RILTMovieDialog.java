package client.GUIClient.GUI.mainWindow.movieDialog;

import client.GUIClient.GUI.mainWindow.LoadingDialog;
import client.GUIClient.GUIClient;
import client.commands.AbstractCommand;
import client.commands.Insert;
import client.commands.ReplaceIfLowe;
import common.models.Movie;
import common.models.MovieGenre;
import common.models.MpaaRating;
import common.models.helpers.MovieArgumentChecker;
import common.responses.InsertResponse;
import common.responses.ReplaceIfLoweResponse;
import common.responses.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class RILTMovieDialog extends MovieDialog {
    private final Integer oldKey;

    public RILTMovieDialog(Window owner, GUIClient client, Integer oldKey) {
        super(owner, client);
        this.oldKey = oldKey;

        headingLabelSettings();
    }

    @Override
    protected void okButtonSettings() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog loadingDialog = new LoadingDialog((Frame) getOwner(), client.getLocale());

                RILTTask task = new RILTTask(RILTMovieDialog.this, loadingDialog);
                task.execute();

                loadingDialog.setVisible(true);
            }
        });
    }

    protected class RILTTask extends SwingWorker<Response, Void> {
        private final JDialog movieDialog;
        private final JDialog loadingDialog;

        public RILTTask(JDialog movieDialog, JDialog loadingDialog) {
            this.movieDialog = movieDialog;
            this.loadingDialog = loadingDialog;
        }

        @Override
        protected Response doInBackground() throws Exception {
            // todo add check
//            Integer key = Integer.parseInt(args[0]);
            MovieArgumentChecker.checkKey(oldKey);

            String movieName = movieNameTextField.getText();
            Integer x = (Integer) xSpinner.getValue();
            Integer y = (Integer) ySpinner.getValue();
            long oscarsCount = Long.valueOf((Integer) oscarsSpinner.getValue());
            MovieGenre movieGenre = (MovieGenre) genreComboBox.getSelectedItem();
            MpaaRating mpaaRating = (MpaaRating) ratingComboBox.getSelectedItem();
            String directorName = directorNameTextField.getText();
//            LocalDateTime birthday = (LocalDateTime) birthdayDatePicker.getValue();
            LocalDateTime birthday = LocalDateTime.now();
            Integer weight = (Integer) weightSpinner.getValue();
            String passportID = passportTextField.getText();

            AbstractCommand command = new ReplaceIfLowe(
                    client, client.getNetworkClient(), client.getLogin(), client.getPassword(),
                    oldKey, movieName, x, y, oscarsCount, movieGenre, mpaaRating,
                    directorName, birthday, weight, passportID);

            Response abstractResponse = client.getInvoker().execute(command);
            return abstractResponse;
        }

        @Override
        protected void done() {
            try {
                loadingDialog.dispose();
                Response abstractResponse = get();
                client.handleErrorResponse(abstractResponse);
                ReplaceIfLoweResponse response = (ReplaceIfLoweResponse) abstractResponse;
                if (response.error == null) {
                    String message;
                    if (response.replaced) {
                        message = ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("RILTMovie.true.message");
                    } else {
                        message = ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("RILTMovie.false.message");
                    }
                    movieDialog.dispose();
                    JOptionPane.showMessageDialog(
                            getOwner(),
                            message,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("RILTMovie.info.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            getOwner(),
                            response.error,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("RILTMovie.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        getOwner(),
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("RILTMovie.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void headingLabelSettings() {
        headingLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("RILTMovie.heading"));
    }
}
