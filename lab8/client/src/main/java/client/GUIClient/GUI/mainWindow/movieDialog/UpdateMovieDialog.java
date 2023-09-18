package client.GUIClient.GUI.mainWindow.movieDialog;

import client.GUIClient.GUI.mainWindow.LoadingDialog;
import client.GUIClient.GUIClient;
import client.commands.AbstractCommand;
import client.commands.Insert;
import client.commands.Update;
import common.exceptions.WrongArgumentException;
import common.models.*;
import common.models.helpers.MovieArgumentChecker;
import common.responses.InsertResponse;
import common.responses.Response;
import common.responses.UpdateResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateMovieDialog extends MovieDialog {
    protected final Integer key;
    protected final Movie movie;

    public UpdateMovieDialog(Window owner, GUIClient client, Integer key, Movie movie) {
        super(owner, client);
        this.key = key;
        this.movie = movie;

        headingLabelSettings();
        setMovieData();
    }

    @Override
    protected void okButtonSettings() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog loadingDialog = new LoadingDialog((Frame) getOwner(), client.getLocale());

                SendDataTask task = new SendDataTask(UpdateMovieDialog.this, loadingDialog);
                task.execute();

                loadingDialog.setVisible(true);

                // dispose();
            }
        });
    }


    protected class SendDataTask extends SwingWorker<Response, Void> {
        private final JDialog updateMovieDialog;
        private final JDialog loadingDialog;

        public SendDataTask(JDialog updateMovieDialog, JDialog loadingDialog) {
            this.updateMovieDialog = updateMovieDialog;
            this.loadingDialog = loadingDialog;
        }

        @Override
        protected Response doInBackground() throws Exception {
            Integer id = movie.getID();
            String movieName = movieNameTextField.getText();
            Integer x = (Integer) xSpinner.getValue();
            Integer y = (Integer) ySpinner.getValue();
            long oscarsCount = Long.parseLong(oscarsSpinner.getValue().toString());
            MovieGenre movieGenre = (MovieGenre) genreComboBox.getSelectedItem();
            MpaaRating mpaaRating = (MpaaRating) ratingComboBox.getSelectedItem();
            String directorName = directorNameTextField.getText();
            LocalDateTime birthday;
            try {
                birthday = LocalDate.parse(birthdayDatePicker.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).atStartOfDay();
            } catch (DateTimeParseException e) {
                throw new WrongArgumentException("Wrong date format");
            }
            Integer weight = (Integer) weightSpinner.getValue();
            String passportID = passportTextField.getText();

            MovieArgumentChecker.checkArguments(
                    key, movieName, new Coordinates(x, y), oscarsCount, movieGenre, mpaaRating,
                    new Person(directorName, birthday, weight, passportID));

            AbstractCommand command = new Update(
                    client, client.getNetworkClient(), client.getLogin(), client.getPassword(),
                    id, movieName, x, y, oscarsCount, movieGenre, mpaaRating,
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
                UpdateResponse response = (UpdateResponse) abstractResponse;
                if (response.error == null) {
                    updateMovieDialog.dispose();
                    JOptionPane.showMessageDialog(
                            getOwner(),
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("updateMovie.ok.message"),
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("updateMovie.ok.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            getOwner(),
                            response.error,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("updateMovie.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        getOwner(),
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("updateMovie.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void headingLabelSettings() {
        headingLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("updateMovie.heading"));
    }

    private void setMovieData() {
        movieNameTextField.setText(movie.getName());
        oscarsSpinner.setValue(movie.getOscarsCount());
        genreComboBox.setSelectedItem(movie.getGenre());
        ratingComboBox.setSelectedItem(movie.getGenre());

        directorNameTextField.setText(movie.getDirector().getName());

        LocalDateTime dateTime = movie.getDirector().getBirthday();
        Date out = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", client.getLocale());
        birthdayDatePicker.setValue(format.format(out));
        weightSpinner.setValue(movie.getDirector().getWeight());
        passportTextField.setText(movie.getDirector().getPassportID());

        xSpinner.setValue(movie.getCoordinates().getX());
        ySpinner.setValue(movie.getCoordinates().getY());
    }
}
