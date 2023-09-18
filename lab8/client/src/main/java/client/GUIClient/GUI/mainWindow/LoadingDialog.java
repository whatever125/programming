package client.GUIClient.GUI.mainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoadingDialog extends JDialog {
    private Frame frame;
    private Window window;
    private final String text;

    public LoadingDialog(Frame owner, Locale locale) {
        super(owner, ResourceBundle.getBundle("loading", locale).getString("title"), true);
        this.frame = owner;
        this.text = ResourceBundle.getBundle("loading", locale).getString("text");

        organizeElements();
        setLocationRelativeTo(owner);
    }

    private void organizeElements() {
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel loadingLabel = new JLabel(text);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(loadingLabel, BorderLayout.NORTH);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        contentPane.add(progressBar, BorderLayout.CENTER);

        setContentPane(contentPane);
        pack();
    }
}
