package client.GUIClient.GUI.login;

import client.GUIClient.GUIClient;
import client.commands.AddUserCommand;
import client.commands.AuthenticateUserCommand;
import client.commands.Command;
import client.exceptions.AuthenticationException;
import client.exceptions.CustomIOException;
import client.exceptions.ErrorResponseException;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.responses.AddUserResponse;
import common.responses.AuthenticateUserResponse;
import common.responses.Response;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JFrame {
    GUIClient client;
    NetworkClient networkClient;
    Locale locale;

    private JLabel welcomeLabel;
    private JLabel loginLabel;
    private JTextField loginTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel helpLabel;
    private JButton registerButton;

    public LoginWindow(GUIClient client, NetworkClient networkClient, Locale locale) {
        super("Login");
        this.client = client;
        this.networkClient = networkClient;
        this.locale = locale;

        initComponents();

        loginButtonSettings();
        registerButtonSettings();

        // frame
        getRootPane().putClientProperty("apple.awt.windowTitleVisible", false);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setSize(300, 330);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void loginButtonSettings() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginTextField.getText();
                String password = new String(passwordField.getPassword());

                // Perform login in background
                LoginWorker loginWorker = new LoginWorker(username, password);
                loginWorker.execute();
            }
        });
    }

    protected void registerButtonSettings() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginTextField.getText();
                String password = new String(passwordField.getPassword());

                // Perform login in background
                RegisterWorker loginWorker = new RegisterWorker(username, password);
                loginWorker.execute();
            }
        });
    }

    private void initComponents() {
        welcomeLabel = new JLabel();
        loginLabel = new JLabel();
        loginTextField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        helpLabel = new JLabel();
        registerButton = new JButton();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //---- welcomeLabel ----
        welcomeLabel.setText(ResourceBundle.getBundle("login", locale).getString("label.title"));
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(welcomeLabel.getFont().getSize() + 11f));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        welcomeLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(welcomeLabel);

        //---- loginLabel ----
        loginLabel.setText(ResourceBundle.getBundle("login", locale).getString("label.login"));
        loginLabel.setAlignmentX(CENTER_ALIGNMENT);
        loginLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(loginLabel);

        //---- loginTextField ----
        loginTextField.setMaximumSize(new Dimension(250, 30));
        loginTextField.setAlignmentX(CENTER_ALIGNMENT);
        add(loginTextField);

        //---- passwordLabel ----
        passwordLabel.setText(ResourceBundle.getBundle("login", locale).getString("label.password"));
        passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
        passwordLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(passwordLabel);

        //---- passwordField ----
        passwordField.setMaximumSize(new Dimension(250, 30));
        passwordField.setAlignmentX(CENTER_ALIGNMENT);
        add(passwordField);

        add(Box.createVerticalStrut(10));

        //---- loginButton ----
        loginButton.setText(ResourceBundle.getBundle("login", locale).getString("button.login"));
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.setBackground(UIManager.getColor("Button.default.background"));
        loginButton.setForeground(UIManager.getColor("Button.default.foreground"));
        add(loginButton);

        add(Box.createVerticalStrut(20));

        //---- helpLabel ----
        helpLabel.setText(ResourceBundle.getBundle("login", locale).getString("label.new"));
        helpLabel.setAlignmentX(CENTER_ALIGNMENT);
        helpLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        add(helpLabel);

        //---- registerButton ----
        registerButton.setText(ResourceBundle.getBundle("login", locale).getString("button.register"));
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        add(registerButton);
    }

    private class LoginWorker extends SwingWorker<Boolean, Void> {
        private final String login;
        private final String password;


        public LoginWorker(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground() throws NetworkClientException, CustomIOException, AuthenticationException {
            // Send login request to the server and wait for response
            Command command = new AuthenticateUserCommand(client, networkClient, login, password);
            Response abstractResponse = command.execute();
            try {
                client.handleErrorResponse(abstractResponse);
            } catch (ErrorResponseException e) {
                throw new AuthenticationException(e.getMessage());
            }
            AuthenticateUserResponse response = (AuthenticateUserResponse) abstractResponse;
            if (response.error != null) {
                throw new AuthenticationException(abstractResponse.error);
            }

            client.setLogin(login);
            client.setPassword(password);
            return true;
        }

        @Override
        protected void process(java.util.List<Void> chunks) {
            // Update UI components during the login process if needed
        }

        @Override
        protected void done() {
            try {
                boolean loginSuccessful = get();
                if (loginSuccessful) {
                    // manage jFrames
                    dispose();
                    client.mainWindow();
                }
            } catch (ExecutionException e) {
                JOptionPane.showMessageDialog(LoginWindow.this,
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("login", locale).getString("dialog.error.title"),
                        JOptionPane.ERROR_MESSAGE);
            } catch (InterruptedException e) {
                // todo thread is cancelled
                throw new RuntimeException(e);
            }
        }
    }

    private class RegisterWorker extends SwingWorker<Boolean, Void> {
        private final String login;
        private final String password;


        public RegisterWorker(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground() throws NetworkClientException, CustomIOException, AuthenticationException {
            // Send register request to the server and wait for response
            Command command = new AddUserCommand(client, networkClient, login, password);
            Response abstractResponse = command.execute();
            try {
                client.handleErrorResponse(abstractResponse);
            } catch (ErrorResponseException e) {
                throw new AuthenticationException(e.getMessage());
            }
            AddUserResponse response = (AddUserResponse) abstractResponse;
            if (response.error != null) {
                throw new AuthenticationException(abstractResponse.error);
            }

            client.setLogin(login);
            client.setPassword(password);
            return true;
        }

        @Override
        protected void process(java.util.List<Void> chunks) {
            // Update UI components during the login process if needed
        }

        @Override
        protected void done() {
            try {
                boolean registerSuccessful = get();
                if (registerSuccessful) {
                    // manage jFrames
                    dispose();
                    client.mainWindow();
                }
            } catch (ExecutionException e) {
                JOptionPane.showMessageDialog(LoginWindow.this,
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("login", locale).getString("dialog.error.title"),
                        JOptionPane.ERROR_MESSAGE);
            } catch (InterruptedException e) {
                // todo thread is cancelled
                throw new RuntimeException(e);
            }
        }
    }

}
