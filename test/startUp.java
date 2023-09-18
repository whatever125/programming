import javax.swing.*;


/**
 * @author whatever125
 */
public class startUp  {

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	panel = new JPanel();
	headingLabel = new JLabel();
	loadingLabel = new JLabel();

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

		//---- headingLabel ----
		headingLabel.setText("Movie Collection Editor");
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(headingLabel);

		//---- loadingLabel ----
		loadingLabel.setText("Loading...");
		panel.add(loadingLabel);
	}

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel panel;
	private JLabel headingLabel;
	private JLabel loadingLabel;
}
