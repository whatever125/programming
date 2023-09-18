import java.awt.*;
import javax.swing.*;


/**
 * @author whatever125
 */
public class StartUp  {

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	panel = new JPanel();
	headingLabel = new JLabel();
	loadingLabel = new JLabel();
	iconLabel = new JLabel();
	comboBox1 = new JComboBox();

	//======== panel ========
	{
		panel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
		. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
		. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
		awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel. getBorder( )) )
		; panel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
		) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
		;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//---- headingLabel ----
		headingLabel.setText("Movie Collection Editor");
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(headingLabel);

		//---- loadingLabel ----
		loadingLabel.setText("Loading...");
		panel.add(loadingLabel);
		panel.add(iconLabel);
		panel.add(comboBox1);
	}

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel panel;
	private JLabel headingLabel;
	private JLabel loadingLabel;
	private JLabel iconLabel;
	private JComboBox comboBox1;

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	panel = new JPanel();
	headingLabel = new JLabel();
	loadingLabel = new JLabel();
	iconLabel = new JLabel();

	//======== panel ========
	{
		panel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
		. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing
		. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
		Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
		) ,panel. getBorder( )) ); panel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
		public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName (
		) )) throw new RuntimeException( ); }} );
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//---- headingLabel ----
		headingLabel.setText("Movie Collection Editor");
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(headingLabel);

		//---- loadingLabel ----
		loadingLabel.setText("Loading...");
		panel.add(loadingLabel);
		panel.add(iconLabel);
	}

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel panel;
	private JLabel headingLabel;
	private JLabel loadingLabel;
	private JLabel iconLabel;

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	panel = new JPanel();
	headingLabel = new JLabel();
	loadingLabel = new JLabel();
	statusLabel = new JLabel();

	//======== panel ========
	{
		panel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
		swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border
		. TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg"
		, java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel. getBorder
		() ) ); panel. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
		. beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException
		( ) ;} } );
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//---- headingLabel ----
		headingLabel.setText("Movie Collection Editor");
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(headingLabel);

		//---- loadingLabel ----
		loadingLabel.setText("Loading...");
		panel.add(loadingLabel);

		//---- statusLabel ----
		statusLabel.setText("status");
		statusLabel.setFont(statusLabel.getFont().deriveFont(statusLabel.getFont().getStyle() | Font.BOLD));
		panel.add(statusLabel);
	}

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel panel;
	private JLabel headingLabel;
	private JLabel loadingLabel;
	private JLabel statusLabel;
}
