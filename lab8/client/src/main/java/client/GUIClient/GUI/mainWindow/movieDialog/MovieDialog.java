package client.GUIClient.GUI.mainWindow.movieDialog;

import client.GUIClient.GUI.mainWindow.LoadingDialog;
import client.GUIClient.GUIClient;
import client.commands.AbstractCommand;
import client.commands.Insert;
import client.exceptions.ErrorResponseException;
import common.exceptions.WrongArgumentException;
import common.models.Coordinates;
import common.models.MovieGenre;
import common.models.MpaaRating;
import common.models.Person;
import common.models.helpers.MovieArgumentChecker;
import common.responses.InsertResponse;
import common.responses.Response;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;

public class MovieDialog extends JDialog {
    protected static final String dateTimeFormat = "dd.MM.yy HH:mm";
    protected static final String birthdayTimeFormat = "dd.MM.yyyy";

    protected final GUIClient client;
    protected JPanel dialogPane;
    protected JPanel contentPanel;
    protected JLabel headingLabel;
    protected JPanel panel1;
    protected JPanel panel2;
    protected JPanel moviePanel;
    protected JLabel movieLabel;
    protected JLabel movieNameLabel;
    protected JTextField movieNameTextField;
    protected JLabel oscarsLabel;
    protected JSpinner oscarsSpinner;
    protected JLabel genreLabel;
    protected JComboBox<MovieGenre> genreComboBox;
    protected JLabel ratingLabel;
    protected JComboBox<MpaaRating> ratingComboBox;
    protected JPanel directorPanel;
    protected JLabel directorLabel;
    protected JLabel directorNameLabel;
    protected JTextField directorNameTextField;
    protected JLabel birthdayLabel;
    protected JFormattedTextField birthdayDatePicker;
    protected JLabel weightLabel;
    protected JSpinner weightSpinner;
    protected JLabel passportLabel;
    protected JTextField passportTextField;
    protected JPanel panel3;
    protected JLabel coordinatesLabel;
    protected JPanel panel4;
    protected JPanel xPanel;
    protected JLabel xLabel;
    protected JSpinner xSpinner;
    protected JPanel yPanel;
    protected JLabel yLabel;
    protected JSpinner ySpinner;
    protected JPanel buttonBar;
    protected JButton okButton;
    protected JButton cancelButton;

    public MovieDialog(Window owner, GUIClient client) {
        super(owner);

        this.client = client;

        initComponents();
        setGenres();
        setRatings();

        dialogPaneSettings();
        okButtonSettings();
        cancelButtonSettings();

        setModal(true);

        setSize(360, 440);
        setMinimumSize(new Dimension(300, 440));
        setMaximumSize(new Dimension(600, 440));
        setLocationRelativeTo(owner);
    }

    protected void setGenres() {
        MovieGenre[] genres = MovieGenre.values();
        DefaultComboBoxModel<MovieGenre> comboBoxModel = new DefaultComboBoxModel<>(genres);
        genreComboBox.setModel(comboBoxModel);
    }

    protected void setRatings() {
        MpaaRating[] ratings = MpaaRating.values();
        DefaultComboBoxModel<MpaaRating> comboBoxModel = new DefaultComboBoxModel<>(ratings);
        ratingComboBox.setModel(comboBoxModel);
    }

    protected void okButtonSettings() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog loadingDialog = new LoadingDialog((Frame) getOwner(), client.getLocale());

                MovieDialog.SendDataTask task = new MovieDialog.SendDataTask(MovieDialog.this, loadingDialog);
                task.execute();

                loadingDialog.setVisible(true);
            }
        });
    }

    protected class SendDataTask extends SwingWorker<Response, Void> {
        private final JDialog movieDialog;
        private final JDialog loadingDialog;

        public SendDataTask(JDialog movieDialog, JDialog loadingDialog) {
            this.movieDialog = movieDialog;
            this.loadingDialog = loadingDialog;
        }

        @Override
        protected Response doInBackground() throws Exception {
            // todo add check
//            Integer key = Integer.parseInt(args[0]);
            Integer key = new Random().nextInt(1, 100000);
            MovieArgumentChecker.checkKey(key);
            String movieName = movieNameTextField.getText();
            Integer x = (Integer) xSpinner.getValue();
            Integer y = (Integer) ySpinner.getValue();
            long oscarsCount = Long.valueOf((Integer) oscarsSpinner.getValue());
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

            AbstractCommand command = new Insert(
                    client, client.getNetworkClient(), client.getLogin(), client.getPassword(),
                    key, movieName, x, y, oscarsCount, movieGenre, mpaaRating,
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
                InsertResponse response = (InsertResponse) abstractResponse;
                if (response.error == null) {
                    movieDialog.dispose();
                    JOptionPane.showMessageDialog(
                            getOwner(),
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.ok.message"),
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.ok.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            getOwner(),
                            response.error,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        getOwner(),
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    protected void cancelButtonSettings() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    protected void dialogPaneSettings() {
        dialogPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    protected void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        headingLabel = new JLabel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        moviePanel = new JPanel();
        movieLabel = new JLabel();
        movieNameLabel = new JLabel();
        movieNameTextField = new JTextField();
        oscarsLabel = new JLabel();
        oscarsSpinner = new JSpinner();
        genreLabel = new JLabel();
        genreComboBox = new JComboBox<MovieGenre>();
        ratingLabel = new JLabel();
        ratingComboBox = new JComboBox<MpaaRating>();
        directorPanel = new JPanel();
        directorLabel = new JLabel();
        directorNameLabel = new JLabel();
        directorNameTextField = new JTextField();
        birthdayLabel = new JLabel();
        weightLabel = new JLabel();
        weightSpinner = new JSpinner();
        passportLabel = new JLabel();
        passportTextField = new JTextField();
        panel3 = new JPanel();
        coordinatesLabel = new JLabel();
        panel4 = new JPanel();
        xPanel = new JPanel();
        xLabel = new JLabel();
        xSpinner = new JSpinner();
        yPanel = new JPanel();
        yLabel = new JLabel();
        ySpinner = new JSpinner();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //---- headingLabel ----
                headingLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.heading"));
                headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
                headingLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
                contentPanel.add(headingLabel, BorderLayout.NORTH);

                //======== panel1 ========
                {
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

                        //======== moviePanel ========
                        {
                            moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

                            //---- movieLabel ----
                            movieLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.movie"));
                            movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getSize() + 3f));
                            movieLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            movieLabel.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(movieLabel);

                            //---- movieNameLabel ----
                            movieNameLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.name"));
                            movieNameLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            movieNameLabel.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(movieNameLabel);

                            //---- movieNameTextField ----
                            movieNameTextField.setMinimumSize(new Dimension(150, 30));
                            movieNameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            movieNameTextField.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(movieNameTextField);

                            //---- oscarsLabel ----
                            oscarsLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.oscars"));
                            oscarsLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            oscarsLabel.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(oscarsLabel);

                            //---- oscarsSpinner ----
                            oscarsSpinner.setMinimumSize(new Dimension(150, 30));
                            oscarsSpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            oscarsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            oscarsSpinner.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(oscarsSpinner);

                            //---- genreLabel ----
                            genreLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.genre"));
                            genreLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            genreLabel.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(genreLabel);

                            //---- genreSpinner ----
                            genreComboBox.setMinimumSize(new Dimension(150, 30));
                            genreComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            genreComboBox.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(genreComboBox);

                            //---- ratingLabel ----
                            ratingLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.rating"));
                            ratingLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            ratingLabel.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(ratingLabel);

                            //---- ratingComboBox ----
                            ratingComboBox.setMinimumSize(new Dimension(150, 30));
                            ratingComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            ratingComboBox.setAlignmentX(LEFT_ALIGNMENT);
                            moviePanel.add(ratingComboBox);
                        }
                        panel2.add(moviePanel);

                        panel2.add(Box.createHorizontalStrut(20));

                        //======== directorPanel ========
                        {
                            directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

                            //---- directorLabel ----
                            directorLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.director"));
                            directorLabel.setFont(directorLabel.getFont().deriveFont(directorLabel.getFont().getSize() + 3f));
                            directorLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            directorLabel.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(directorLabel);

                            //---- directorNameLabel ----
                            directorNameLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.directorName"));
                            directorNameLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            directorNameLabel.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(directorNameLabel);

                            //---- directorNameTextField ----
                            directorNameTextField.setMinimumSize(new Dimension(150, 30));
                            directorNameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            directorNameTextField.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(directorNameTextField);

                            //---- heightLabel ----
                            birthdayLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.birthday"));
                            birthdayLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            birthdayLabel.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(birthdayLabel);

                            //---- birthdayDatePicker ----
                            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", client.getLocale());
                            birthdayDatePicker = new JFormattedTextField();

                            birthdayDatePicker.setMinimumSize(new Dimension(150, 30));
                            birthdayDatePicker.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            birthdayDatePicker.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(birthdayDatePicker);

                            //---- weightLabel ----
                            weightLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.weight"));
                            weightLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            weightLabel.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(weightLabel);

                            //---- weightSpinner ----
                            weightSpinner.setMinimumSize(new Dimension(150, 30));
                            weightSpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            weightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            weightSpinner.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(weightSpinner);

                            //---- passportLabel ----
                            passportLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.passportID"));
                            passportLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            passportLabel.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(passportLabel);

                            //---- passportTextField ----
                            passportTextField.setMinimumSize(new Dimension(150, 30));
                            passportTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            passportTextField.setAlignmentX(LEFT_ALIGNMENT);
                            directorPanel.add(passportTextField);
                        }
                        panel2.add(directorPanel);
                    }
                    panel1.add(panel2);

                    panel1.add(Box.createVerticalStrut(10));

                    //======== panel3 ========
                    {
                        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

                        //---- coordinatesLabel ----
                        coordinatesLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.coordinates"));
                        coordinatesLabel.setFont(coordinatesLabel.getFont().deriveFont(coordinatesLabel.getFont().getSize() + 3f));
//                        coordinatesLabel.setAlignmentX(LEFT_ALIGNMENT);
                        panel3.add(coordinatesLabel);
                    }
                    panel1.add(panel3);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

                        //======== xPanel ========
                        {
                            xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));

                            //---- xLabel ----
                            xLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.x"));
                            xLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            xLabel.setAlignmentX(LEFT_ALIGNMENT);
                            xPanel.add(xLabel);

                            //---- xSpinner ----
                            xSpinner.setMinimumSize(new Dimension(150, 30));
                            xSpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            xSpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
                            xSpinner.setAlignmentX(LEFT_ALIGNMENT);
                            xPanel.add(xSpinner);
                        }
                        panel4.add(xPanel);

                        panel4.add(Box.createHorizontalStrut(10));

                        //======== yPanel ========
                        {
                            yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));

                            //---- yLabel ----
                            yLabel.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.label.y"));
                            yLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
                            yLabel.setAlignmentX(LEFT_ALIGNMENT);
                            yPanel.add(yLabel);

                            //---- ySpinner ----
                            ySpinner.setMinimumSize(new Dimension(150, 30));
                            ySpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                            ySpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
                            ySpinner.setAlignmentX(LEFT_ALIGNMENT);
                            yPanel.add(ySpinner);
                        }
                        panel4.add(yPanel);
                    }
                    panel1.add(panel4);
                }
                contentPanel.add(panel1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.button.ok"));
                okButton.setBackground(UIManager.getColor("Button.default.background"));
                okButton.setForeground(UIManager.getColor("Button.default.foreground"));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("movieDialog.button.cancel"));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
