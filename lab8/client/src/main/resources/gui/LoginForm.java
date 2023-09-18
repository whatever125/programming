import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jun 22 00:16:02 MSK 2023
 */



/**
 * @author whatever125
 */
public class LoginForm extends JPanel {
	public LoginForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Dmitry
		welcomeLabel = new JLabel();
		loginLabel = new JLabel();
		loginTextField = new JTextField();
		passwordLabel = new JLabel();
		passwordField = new JPasswordField();
		loginButton = new JButton();
		helpLabel = new JLabel();
		registerButton = new JButton();

		//======== this ========
		setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
		. swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing
		.border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
		Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
		) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
		public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName (
		) ) )throw new RuntimeException( ) ;} } );
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//---- welcomeLabel ----
		welcomeLabel.setText("Welcome back");
		welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(welcomeLabel.getFont().getSize() + 11f));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		add(welcomeLabel);

		//---- loginLabel ----
		loginLabel.setText("Login");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(loginLabel);

		//---- loginTextField ----
		loginTextField.setPreferredSize(new Dimension(150, 30));
		loginTextField.setMaximumSize(new Dimension(2147483647, 30));
		loginTextField.setHorizontalAlignment(SwingConstants.CENTER);
		add(loginTextField);

		//---- passwordLabel ----
		passwordLabel.setText("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(passwordLabel);

		//---- passwordField ----
		passwordField.setMaximumSize(new Dimension(2147483647, 30));
		passwordField.setPreferredSize(new Dimension(150, 30));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		add(passwordField);

		//---- loginButton ----
		loginButton.setText("Login");
		loginButton.setBackground(UIManager.getColor("Button.default.background"));
		loginButton.setForeground(UIManager.getColor("Button.default.foreground"));
		add(loginButton);

		//---- helpLabel ----
		helpLabel.setText("Don't have account?");
		helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helpLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(helpLabel);

		//---- registerButton ----
		registerButton.setText("Register");
		add(registerButton);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JLabel welcomeLabel;
	private JLabel loginLabel;
	private JTextField loginTextField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel helpLabel;
	private JButton registerButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
