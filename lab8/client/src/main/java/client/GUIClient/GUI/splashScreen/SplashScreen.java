package client.GUIClient.GUI.splashScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class SplashScreen extends JFrame {
	private JLabel headingLabel;
	private JLabel loadingLabel;
	private JLabel statusLabel;
	private Locale locale;

	public SplashScreen(Locale locale) {
		super("MovieCollectionEditor");
		this.locale = locale;

		initComponents();

		getRootPane().putClientProperty("apple.awt.windowTitleVisible", false);
		getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setSize(350, 170);
		setMinimumSize(new Dimension(350, 170));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	private void initComponents() {
		headingLabel = new JLabel();
		loadingLabel = new JLabel();
//		iconLabel = new JLabel();
		statusLabel = new JLabel();

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		//---- headingLabel ----
		headingLabel.setText(ResourceBundle.getBundle("splashScreen", locale).getString("label.heading"));
		headingLabel.setAlignmentX(CENTER_ALIGNMENT);
		headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
		headingLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		add(headingLabel);

		//---- loadingLabel ----
		loadingLabel.setText(ResourceBundle.getBundle("splashScreen", locale).getString("label.loading"));
		loadingLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		loadingLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(loadingLabel);

		//---- statusLabel ----
		statusLabel.setAlignmentX(CENTER_ALIGNMENT);
		statusLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		statusLabel.setFont(statusLabel.getFont().deriveFont(statusLabel.getFont().getStyle() | Font.BOLD));
		add(statusLabel);
	}

	public void setStatus(String text) {
		statusLabel.setText(text);
		repaint();
	}
}
