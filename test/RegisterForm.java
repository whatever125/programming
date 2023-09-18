import java.awt.*;
import javax.swing.*;


/**
 * @author whatever125
 */
public class RegisterForm  {

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	panel = new JPanel();
	welcomeLabel = new JLabel();
	loginLabel = new JLabel();
	loginTextField = new JTextField();
	passwordLabel = new JLabel();
	passwordField = new JPasswordField();
	loginButton = new JButton();
	label1 = new JLabel();
	button1 = new JButton();

	//======== panel ========
	{
		panel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
		swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border
		. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
		,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel. getBorder
		( )) ); panel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
		.beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
		( ); }} );
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//---- welcomeLabel ----
		welcomeLabel.setText("Welcome");
		welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(welcomeLabel.getFont().getSize() + 11f));
		panel.add(welcomeLabel);

		//---- loginLabel ----
		loginLabel.setText("Login");
		panel.add(loginLabel);

		//---- loginTextField ----
		loginTextField.setPreferredSize(new Dimension(150, 30));
		loginTextField.setMaximumSize(new Dimension(2147483647, 30));
		panel.add(loginTextField);

		//---- passwordLabel ----
		passwordLabel.setText("Password");
		panel.add(passwordLabel);

		//---- passwordField ----
		passwordField.setMaximumSize(new Dimension(2147483647, 30));
		passwordField.setPreferredSize(new Dimension(150, 30));
		panel.add(passwordField);

		//---- loginButton ----
		loginButton.setText("Register");
		panel.add(loginButton);

		//---- label1 ----
		label1.setText("Already have an account?");
		panel.add(label1);

		//---- button1 ----
		button1.setText("Login");
		panel.add(button1);
	}

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel panel;
	private JLabel welcomeLabel;
	private JLabel loginLabel;
	private JTextField loginTextField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel label1;
	private JButton button1;

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	panel = new JPanel();
	welcomeLabel = new JLabel();
	loginLabel = new JLabel();
	loginTextField = new JTextField();
	passwordLabel = new JLabel();
	passwordField = new JPasswordField();
	registerButton = new JButton();
	helpLabel = new JLabel();
	loginButton = new JButton();

	//======== panel ========
	{
		panel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
		swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border
		. TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog"
		, java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel. getBorder
		() ) ); panel. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
		. beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException
		( ) ;} } );
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//---- welcomeLabel ----
		welcomeLabel.setText("Welcome");
		welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(welcomeLabel.getFont().getSize() + 11f));
		panel.add(welcomeLabel);

		//---- loginLabel ----
		loginLabel.setText("Login");
		panel.add(loginLabel);

		//---- loginTextField ----
		loginTextField.setPreferredSize(new Dimension(150, 30));
		loginTextField.setMaximumSize(new Dimension(2147483647, 30));
		panel.add(loginTextField);

		//---- passwordLabel ----
		passwordLabel.setText("Password");
		panel.add(passwordLabel);

		//---- passwordField ----
		passwordField.setMaximumSize(new Dimension(2147483647, 30));
		passwordField.setPreferredSize(new Dimension(150, 30));
		panel.add(passwordField);

		//---- registerButton ----
		registerButton.setText("Register");
		panel.add(registerButton);

		//---- helpLabel ----
		helpLabel.setText("Already have an account?");
		panel.add(helpLabel);

		//---- loginButton ----
		loginButton.setText("Login");
		panel.add(loginButton);
	}

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel panel;
	private JLabel welcomeLabel;
	private JLabel loginLabel;
	private JTextField loginTextField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton registerButton;
	private JLabel helpLabel;
	private JButton loginButton;
}
