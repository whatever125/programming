package client.GUIClient.GUI.mainWindow;

import client.GUIClient.GUI.LocaleActionListener;
import client.GUIClient.GUI.mainWindow.movieDialog.MovieDialog;
import client.GUIClient.GUI.mainWindow.visualizationTab.VisualizationPanel;
import client.GUIClient.GUI.mainWindow.movieTable.MovieTable;
import client.GUIClient.GUIClient;
import client.Main;
import client.commands.AbstractCommand;
import client.commands.Show;
import client.exceptions.CustomIOException;
import client.exceptions.ErrorResponseException;
import client.exceptions.NetworkClientException;
import common.models.Movie;
import common.responses.Response;
import common.responses.ShowResponse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import static java.lang.Thread.sleep;

public class MainWindow extends JFrame implements LocaleActionListener {
    protected final GUIClient client;

    protected String login;
    protected String password;

    private JTabbedPane tabbedPane;
    private JPanel tablePanel;
    private JButton addButton;
    private JScrollPane scrollPane;
    private MovieTable table;
    private VisualizationPanel visualizationPanel;
    private JPanel optionsPanel;
    private JPanel optionsSpacer;
    private JButton optionsButton;
    private JPanel graphPanel;
    private JPanel northPanel;
    private JLabel headerLabel;
    private JButton logOutButton;
    private JPanel headerSpacer;
    private JButton languageButton;
    private JButton helpButton;
    private JComboBox<LanguageOption> languageComboBox;
    protected Locale locale;

    public MainWindow(GUIClient client) {
        super("MovieCollectionEditor");
        this.client = client;
        locale = client.getLocale();

        addLanguages();

        initComponents();
        addButtonSettings();
        optionsButtonSettings();
        logOutButtonSettings();
        tabbedPaneSettings();
        headerLabelSettings();
        languageButtonSettings();
        helpButtonSettings();

        // frame
        frameSettings();

        languageComboBoxSettings();

        // load data
        loadData();

        MoviesRetrieverWorker worker = new MoviesRetrieverWorker(client, this);
        worker.execute();
    }

    private void frameSettings() {
        getRootPane().putClientProperty("apple.awt.windowTitleVisible", false);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void languageComboBoxSettings() {
        languageComboBox.addActionListener(MainWindow.this);
        languageComboBox.addActionListener(table);
    }

    private void addLanguages() {
        LanguageOption[] languageOptions = {
                new LanguageOption("English", "client/src/main/resources/icons/en.png"),
                new LanguageOption("Русский", "client/src/main/resources/icons/ru.png"),
                new LanguageOption("Íslenska", "client/src/main/resources/icons/is.png"),
                new LanguageOption("Ελληνικά", "client/src/main/resources/icons/el.png"),
                new LanguageOption("Español (Nicaragua)", "client/src/main/resources/icons/es.png")
        };

        // Create and configure the JComboBox
        languageComboBox = new JComboBox<>(languageOptions);
        languageComboBox.setRenderer(new LanguageComboBoxRenderer());
        languageComboBox.setMaximumSize(new Dimension(100, 30));
        String localeName = getLanguageFromLocale(locale);
        for (LanguageOption option: languageOptions) {
            if (localeName == option.getLanguage()) {
                languageComboBox.setSelectedItem(option);
                return;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<LanguageOption> comboBox = (JComboBox<LanguageOption>) e.getSource();
        LanguageOption selectedOption = (LanguageOption) comboBox.getSelectedItem();
        String language = selectedOption.getLanguage();

        // Load the properties file for the selected language
        Locale locale = getLocaleFromLanguage(language);
        if (locale != null) {
            updateLocale(locale);
        }
    }
    public void updateLocale(Locale locale) {
        this.locale = locale;
        client.setLocale(locale);
        ResourceBundle.clearCache();
        Locale.setDefault(locale);
        JComponent.setDefaultLocale(locale);

        headerLabelSettings();
        addButton.setText(ResourceBundle.getBundle("mainWindow", locale).getString("button.add"));
    }

    private Locale getLocaleFromLanguage(String language) {
        switch (language) {
            case "English" -> {
                return Locale.forLanguageTag("en");
            }
            case "Русский" -> {
                return Locale.forLanguageTag("ru");
            }
            case "Íslenska" -> {
                return Locale.forLanguageTag("is");
            }
            case "Ελληνικά" -> {
                return Locale.forLanguageTag("el");
            }
            case "Español (Nicaragua)" -> {
                return Locale.forLanguageTag("es");
            }
            default -> {
                return null;
            }
        }
    }

    private String getLanguageFromLocale(Locale locale) {
        if (locale == Locale.forLanguageTag("en")) {
            return "English";
        } else if (locale == Locale.forLanguageTag("ru")) {
            return "Русский";
        } else if (locale == Locale.forLanguageTag("is")) {
            return "Íslenska";
        } else if (locale == Locale.forLanguageTag("el")) {
            return "Ελληνικά";
        } else if (locale == Locale.forLanguageTag("es")) {
            return "Español (Nicaragua)";
        }
        return null;
    }

    private static class LanguageOption {
        private String language;
        private ImageIcon icon;

        public LanguageOption(String language, String iconPath) {
            this.language = language;
            this.icon = scaledIcon(iconPath);
        }

        public String getLanguage() {
            return language;
        }

        public ImageIcon getIcon() {
            return icon;
        }

        @Override
        public String toString() {
            return language;
        }
    }

    private static class LanguageComboBoxRenderer extends JLabel implements ListCellRenderer<LanguageOption> {
        public LanguageComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends LanguageOption> list,
                                                      LanguageOption value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            setIcon(value.getIcon());
            setText(value.getLanguage());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }

    public GUIClient getClient() {
        return client;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private void addButtonSettings() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDialog dialog = new MovieDialog(MainWindow.this, client);
                dialog.setVisible(true);
            }
        });
    }

    private void optionsButtonSettings() {
        optionsButton.setIcon(scaledIcon("client/src/main/resources/icons/options.png"));
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsPopUp dialog = new OptionsPopUp(MainWindow.this, client);
                dialog.show(optionsButton, 0, optionsButton.getHeight());
            }
        });
    }

    private void logOutButtonSettings() {
        logOutButton.setIcon(scaledIcon("client/src/main/resources/icons/logout.png"));
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.setLogin(null);
                client.setPassword(null);

                MainWindow.this.dispose();
                client.login();
            }
        });
    }

    private void tabbedPaneSettings() {
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 1) {
                    visualizationPanel.startAnimation();
                }
            }
        });
    }

    private void headerLabelSettings() {
        String messagePattern = ResourceBundle.getBundle("mainWindow", locale).getString("label.header");
        MessageFormat messageFormat = new MessageFormat(messagePattern);
        String formattedMessage = messageFormat.format(new Object[]{client.getLogin()});
        headerLabel.setText(formattedMessage);
    }

    private void languageButtonSettings() {
        languageButton.setIcon(scaledIcon("client/src/main/resources/icons/english.png"));
    }

    private void helpButtonSettings() {
        helpButton.setIcon(scaledIcon("client/src/main/resources/icons/help.png"));
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        tablePanel = new JPanel();
        addButton = new JButton();
        scrollPane = new JScrollPane();
        table = new MovieTable(client, this);
        optionsPanel = new JPanel();
        optionsSpacer = new JPanel(null);
        optionsButton = new JButton();
        visualizationPanel = new VisualizationPanel(this, client);
        graphPanel = visualizationPanel;
        northPanel = new JPanel();
        headerLabel = new JLabel();
        logOutButton = new JButton();
        headerSpacer = new JPanel(null);
        helpButton = new JButton();
        languageButton = new JButton();

        setLayout(new BorderLayout());


        //======== tabbedPane ========
        {

            //======== tablePanel ========
            {
                tablePanel.setLayout(new BorderLayout());

                //---- addButton ----
                addButton.setText(ResourceBundle.getBundle("mainWindow", locale).getString("button.add"));
                tablePanel.add(addButton, BorderLayout.SOUTH);

                //======== scrollPane ========
                {
                    scrollPane.setViewportView(table);
                }
                tablePanel.add(scrollPane, BorderLayout.CENTER);

                //======== optionsPanel ========
                {
                    optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
                    optionsPanel.add(optionsSpacer);

                    //---- optionsButton ----
                    optionsPanel.add(optionsButton);
                }
                tablePanel.add(optionsPanel, BorderLayout.NORTH);
            }
            tabbedPane.addTab(ResourceBundle.getBundle("mainWindow", locale).getString("tab.table"), tablePanel);

            //======== graphPanel ========
            {
//                graphPanel.setLayout(new BorderLayout());
//                graphPanel.add(visualizationPanel);
//                {
//                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//                }
//                graphPanel.setLayout(new BorderLayout());
//                graphPanel.add(graphScrollPane, BorderLayout.CENTER);
            }
            tabbedPane.addTab(ResourceBundle.getBundle("mainWindow", locale).getString("tab.graph"), graphPanel);
        }
        add(tabbedPane, BorderLayout.CENTER);

        //======== northPanel ========
        {
            northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

            //---- headerLabel ----
            headerLabel.setFont(headerLabel.getFont().deriveFont(headerLabel.getFont().getSize() + 11f));
            northPanel.add(headerLabel);

            northPanel.add(Box.createHorizontalStrut(10));

            //---- logOutButton ----
            northPanel.add(logOutButton);

            //---- headerSpacer ----
            northPanel.add(headerSpacer);


            northPanel.add(languageComboBox);

            //---- helpButton ----
//            northPanel.add(helpButton);
        }
        add(northPanel, BorderLayout.NORTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private static ImageIcon scaledIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }

    private void loadData() {
        // Show the loading dialog
        JDialog loadingDialog = new LoadingDialog(this, locale);

        LoadTableWorker worker = new LoadTableWorker(loadingDialog);
        worker.execute();

        loadingDialog.setVisible(true);
    }

    public void updateData(HashMap<Integer, Movie> movieHashMap) {
        table.setData(movieHashMap);
        visualizationPanel.setData(movieHashMap);
    }

    private class LoadTableWorker extends SwingWorker<Void, Void> {
        private final JDialog loadingDialog;

        private LoadTableWorker(JDialog loadingDialog) {
            this.loadingDialog = loadingDialog;
        }

        @Override
        protected Void doInBackground() {
            try {
                AbstractCommand command = new Show(client, client.getNetworkClient(), client.getLogin(), client.getPassword());
                Response abstractResponse = client.getInvoker().execute(command);
                client.handleErrorResponse(abstractResponse);
                ShowResponse response = (ShowResponse) abstractResponse;

                if (response.error == null) {
                    HashMap<Integer, Movie> movieHashMap = response.getMovieHashMap();
                    table.setData(movieHashMap);
                } else {
                    // todo error
                    System.out.println(response.error);
                }
                return null;
            } catch (ErrorResponseException | NetworkClientException | CustomIOException e) {
                // todo error
                throw new RuntimeException(e);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }

        @Override
        protected void done() {
            loadingDialog.setVisible(false);
            loadingDialog.dispose();
        }
    }
}
