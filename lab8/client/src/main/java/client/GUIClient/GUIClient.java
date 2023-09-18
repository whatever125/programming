package client.GUIClient;

import client.*;
import client.GUIClient.GUI.login.LoginWindow;
import client.GUIClient.GUI.mainWindow.MainWindow;
import client.GUIClient.GUI.splashScreen.SplashScreen;
import client.GUIClient.GUI.splashScreen.ConnectWorker;
import client.commands.AbstractCommand;
import client.commands.Show;
import client.exceptions.*;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.*;
import common.IOHandlers.*;
import common.exceptions.*;
import common.models.Movie;
import common.responses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

public class GUIClient extends AbstractClient {
    protected MainWindow mainWindow;
    protected Locale locale = new Locale("en");

    public GUIClient() throws UnsupportedLookAndFeelException {
        // system properties
        System.setProperty("apple.awt.application.name", "MovieCollectionEditor");
        System.setProperty("apple.awt.application.appearance", "system");

        // themes setup
        FlatLightLaf.setup();
//        UIManager.setLookAndFeel(new FlatLightLaf());
//        UIManager.setLookAndFeel(new FlatDarculaLaf());
//        UIManager.setLookAndFeel(new FlatMacDarkLaf());
        UIManager.setLookAndFeel(new FlatMacLightLaf());
    }

    @Override
    public void run() {
        // startup
//        SwingUtilities.invokeLater(() -> new SplashScreen(networkClient));
        SwingUtilities.invokeLater(this::splash);
    }

    private void splash() {
        // show splash, connect to server
        SplashScreen splashScreen = new SplashScreen(locale);
        ConnectWorker connectWorker = new ConnectWorker(this, splashScreen, networkClient, locale);
        connectWorker.execute();
    }

    public void login() {
        LoginWindow loginWindow = new LoginWindow(this, networkClient, locale);
    }

    public void mainWindow() {
        mainWindow = new MainWindow(this);
    }

    public HashMap<Integer, Movie> retrieveMovies() throws NetworkClientException, CustomIOException, ErrorResponseException {
        AbstractCommand command = new Show(this, networkClient, login, password);
        Response abstractResponse = invoker.execute(command);
        handleErrorResponse(abstractResponse);
        ShowResponse response = (ShowResponse) abstractResponse;
        if (response.error == null) {
            HashMap<Integer, Movie> movieHashMap = response.getMovieHashMap();
            return movieHashMap;
        }
        return null;
    }

    /**
     * Executes a script file containing a list of commands.
     *
     * @param path the path of the script file to execute
     * @throws CustomIOException if there is an error reading the script file
     */
    @Override
    public Response executeScript(String path) {
        JDialog resultDialog = new JDialog(mainWindow, "Execution results");

        JTextArea textArea = new JTextArea(25, 100);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textArea.setText(textArea.getText() + "*Execution results*");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        resultDialog.getContentPane().add(scrollPane);

        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.pack();
        resultDialog.setLocationRelativeTo(null);

        ExecuteWorker executeWorker = new ExecuteWorker(textArea, path);

        resultDialog.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {
                executeWorker.cancel(true);
            }

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        executeWorker.execute();

        resultDialog.setVisible(true);

        return new EmptyResponse();
    }

    private class ExecuteWorker extends SwingWorker<Response, String> {
        private final JTextArea textArea;
        private final String path;

        public ExecuteWorker(JTextArea textArea, String path) {
            this.textArea = textArea;
            this.path = path;
        }

        @Override
        protected Response doInBackground() throws Exception {
            try {
                if (pathStackContains(path)) {
                    Exception exception = new FileRecursionError(path);
                    publish(exception.getMessage());
                    throw exception;
                }

                BasicReader basicReader = new ScriptReader(path);
                pathStack.push(path);
                int lineCounter = 0;
                while (basicReader.hasNextLine()) {
                    if (isCancelled()) {
                        return new ErrorResponse("*Execution has been cancelled*");
                    }
                    try {
                        Thread.sleep(50);
                        lineCounter += 1;
                        String message = readAndExecuteCommand(basicReader);
                        publish(message);
                    } catch (InvalidCommandException | WrongNumberOfArgumentsException | WrongArgumentException |
                             InvalidScriptException | ErrorResponseException | NetworkClientException e) {
                        String message = printPathStack() + ":" + lineCounter + ": " + e.getMessage();
                        publish(message);
                        return new ErrorResponse(message);
                    }
                }
                pathStack.pop();
            } catch (FileRecursionError | FileNotFoundException | FilePermissionException e) {
                publish(e.getMessage());
                return new ErrorResponse(e.getMessage());
            }
            return new EmptyResponse();
        }

        @Override
        protected void process(List<String> chunks) {
            String message = chunks.get(0);
            if (message != null) {
                textArea.setText(textArea.getText() + "\n\n" + message);
            }
        }

        @Override
        protected void done() {
            try {
                Response response = get();
                handleErrorResponse(response);

                if (response.error != null) {
                    textArea.setText(textArea.getText() + "\n\n" + response.error);
                    JOptionPane.showMessageDialog(
                            mainWindow,
                            response.error,
                            ResourceBundle.getBundle("mainWindow", getLocale()).getString("menu.options.execute.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    textArea.setText(textArea.getText() + "\n\n" + "*Execution complete*");
                    JOptionPane.showMessageDialog(
                            mainWindow,
                            ResourceBundle.getBundle("mainWindow", getLocale()).getString("menu.options.execute.ok.message"),
                            ResourceBundle.getBundle("mainWindow", getLocale()).getString("menu.options.execute.ok.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (ErrorResponseException | InterruptedException | ExecutionException e) {
                JOptionPane.showMessageDialog(
                        mainWindow,
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", getLocale()).getString("menu.options.execute.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (CancellationException e) {
                JOptionPane.showMessageDialog(
                        mainWindow,
                        ResourceBundle.getBundle("mainWindow", getLocale()).getString("menu.options.execute.cancel.message"),
                        ResourceBundle.getBundle("mainWindow", getLocale()).getString("menu.options.execute.cancel.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
