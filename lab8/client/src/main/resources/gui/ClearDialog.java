import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * @author whatever125
 */
public class ClearDialog extends JDialog {
	public ClearDialog(Window owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	dialogPane = new JPanel();
	contentPanel = new JPanel();
	button1 = new JButton();
	label1 = new JLabel();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
		( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
		.TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
		. Color .red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
		propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
		;} } );
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

			//---- button1 ----
			button1.setText("!");
			contentPanel.add(button1);

			//---- label1 ----
			label1.setText("Are you sure you want to clear the collection?");
			contentPanel.add(label1);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("Yes");
			buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- cancelButton ----
			cancelButton.setText("Cancel");
			buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		dialogPane.add(buttonBar, BorderLayout.SOUTH);
	}
	contentPane.add(dialogPane, BorderLayout.CENTER);
	pack();
	setLocationRelativeTo(getOwner());

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JButton button1;
	private JLabel label1;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
	// Generated using JFormDesigner Evaluation license - Dmitry
	dialogPane = new JPanel();
	contentPanel = new JPanel();
	button1 = new JButton();
	label1 = new JLabel();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
		new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn"
		, javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
		, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 )
		, java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (
		new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
		) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
		; }} );
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

			//---- button1 ----
			button1.setText("!");
			contentPanel.add(button1);

			//---- label1 ----
			label1.setText("Clear the collection? You will not be able to restore it");
			contentPanel.add(label1);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("Yes");
			buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- cancelButton ----
			cancelButton.setText("Cancel");
			buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		dialogPane.add(buttonBar, BorderLayout.SOUTH);
	}
	contentPane.add(dialogPane, BorderLayout.CENTER);
	pack();
	setLocationRelativeTo(getOwner());

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JButton button1;
	private JLabel label1;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
}
