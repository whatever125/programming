package client.GUIClient.GUI.mainWindow.movieDialog;

import client.GUIClient.GUIClient;
import common.models.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class MovieInfoDialog extends UpdateMovieDialog {

    public MovieInfoDialog(Window owner, GUIClient client, Integer key, Movie movie) {
        super(owner, client, key, movie);

        elementsSettings();
    }

    @Override
    protected void okButtonSettings() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // action on ok
                dispose();
            }
        });
    }

    @Override
    protected void cancelButtonSettings() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // action on ok
                dispose();
            }
        });
    }

    private void elementsSettings() {
        headingLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieInfo.heading"));

        movieNameTextField.setEditable(false);

        oscarsSpinner.setEditor(new JSpinner.DefaultEditor(oscarsSpinner));
        int oscarsSpinnerValue = ((Long) oscarsSpinner.getValue()).intValue();
        oscarsSpinner.setModel(new SpinnerNumberModel(
                oscarsSpinnerValue, oscarsSpinnerValue, oscarsSpinnerValue, 0));

        genreComboBox.setEnabled(false);

        ratingComboBox.setEnabled(false);


        directorNameTextField.setEditable(false);

        birthdayDatePicker.setEditable(false);

        int weightSpinnerValue = (int) weightSpinner.getValue();
        weightSpinner.setModel(new SpinnerNumberModel(
                weightSpinnerValue, weightSpinnerValue, weightSpinnerValue, 0));
        weightSpinner.setEditor(new JSpinner.DefaultEditor(weightSpinner));

        passportTextField.setEditable(false);

        int xSpinnerValue = (int) xSpinner.getValue();
        xSpinner.setModel(new SpinnerNumberModel(
                xSpinnerValue, xSpinnerValue, xSpinnerValue, 0));
        xSpinner.setEditor(new JSpinner.DefaultEditor(xSpinner));

        int ySpinnerValue = (int) ySpinner.getValue();
        ySpinner.setModel(new SpinnerNumberModel(
                ySpinnerValue, ySpinnerValue, ySpinnerValue, 0));
        ySpinner.setEditor(new JSpinner.DefaultEditor(ySpinner));

//        cancelButton.setVisible(false);
    }
}
