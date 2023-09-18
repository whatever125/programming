package client.GUIClient.GUI.splashScreen;

import client.GUIClient.GUI.splashScreen.SplashScreen;
import client.GUIClient.GUIClient;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;

import javax.swing.*;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ConnectWorker extends SwingWorker<Void, String> {
    private final GUIClient client;
    private final SplashScreen splashScreen;
    private final NetworkClient networkClient;
    private boolean success = false;
    private Locale locale;

    public ConnectWorker(GUIClient client, SplashScreen splashScreen, NetworkClient networkClient, Locale locale) {
        this.client = client;
        this.splashScreen = splashScreen;
        this.networkClient = networkClient;
        this.locale = locale;
    }

    @Override
    protected void process(List<String> chunks) {
        String status = chunks.get(0);
        splashScreen.setStatus(status);
    }

    @Override
    protected Void doInBackground() throws InterruptedException {
        for (int countAttempts = 1; countAttempts <= 3; countAttempts ++) {
            try {
                networkClient.openConnection();
                success = true;
                break;
            } catch (NetworkClientException e) {
                String messagePattern = ResourceBundle.getBundle("splashScreen", locale).getString("connectionWorker.label");
                MessageFormat messageFormat = new MessageFormat(messagePattern);
                String formattedMessage = messageFormat.format(new Object[]{countAttempts});
                publish(formattedMessage);
                TimeUnit.SECONDS.sleep(1);
            }
        }
        if (!success) {
            try {
                networkClient.openConnection();
            } catch (NetworkClientException e) {
                System.out.println(2);
                publish(e.getMessage());
            }
        }
        return null;
    }

    @Override
    protected void done() {
        // Close the splash screen and switch to the login window
        if (success) {
            splashScreen.dispose();
            client.login();
        }
    }
}