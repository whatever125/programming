import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * @author whatever125
 */
public class movieDialog extends JDialog {
	public movieDialog(Window owner) {
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
	headingLabel = new JLabel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	moviePanel = new JPanel();
	movieLabel = new JLabel();
	movieNameLabel = new JLabel();
	movieNameTextField = new JTextField();
	oscarsLabel = new JLabel();
	oscarsSpinner = new JSpinner();
	genreLabel = new JLabel();
	genreSpinner = new JComboBox();
	ratingLabel = new JLabel();
	ratingComboBox = new JComboBox();
	directorPanel = new JPanel();
	directorLabel = new JLabel();
	directorNameLabel = new JLabel();
	directorNameTextField = new JTextField();
	heightLabel = new JLabel();
	heightSpinner = new JSpinner();
	weightLabel = new JLabel();
	weightSpinner = new JSpinner();
	passportLabel = new JLabel();
	passportTextField = new JTextField();
	panel3 = new JPanel();
	coordinatesLabel = new JLabel();
	panel4 = new JPanel();
	xPanel = new JPanel();
	xLabel = new JLabel();
	xSpinner = new JSpinner();
	yPanel = new JPanel();
	yLabel = new JLabel();
	ySpinner = new JSpinner();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
		swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
		. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog"
		,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder
		( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
		.beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
		( ); }} );
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BorderLayout());

			//---- headingLabel ----
			headingLabel.setText("Insert");
			headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
			contentPanel.add(headingLabel, BorderLayout.NORTH);

			//======== panel1 ========
			{
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

				//======== panel2 ========
				{
					panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

					//======== moviePanel ========
					{
						moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

						//---- movieLabel ----
						movieLabel.setText("Movie");
						movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getSize() + 3f));
						moviePanel.add(movieLabel);

						//---- movieNameLabel ----
						movieNameLabel.setText("Name");
						moviePanel.add(movieNameLabel);

						//---- movieNameTextField ----
						movieNameTextField.setPreferredSize(new Dimension(150, 30));
						movieNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(movieNameTextField);

						//---- oscarsLabel ----
						oscarsLabel.setText("Oscars");
						moviePanel.add(oscarsLabel);

						//---- oscarsSpinner ----
						oscarsSpinner.setPreferredSize(new Dimension(150, 30));
						oscarsSpinner.setMaximumSize(new Dimension(2147483647, 30));
						oscarsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						moviePanel.add(oscarsSpinner);

						//---- genreLabel ----
						genreLabel.setText("Genre");
						moviePanel.add(genreLabel);

						//---- genreSpinner ----
						genreSpinner.setPreferredSize(new Dimension(150, 30));
						genreSpinner.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(genreSpinner);

						//---- ratingLabel ----
						ratingLabel.setText("Rating");
						moviePanel.add(ratingLabel);

						//---- ratingComboBox ----
						ratingComboBox.setPreferredSize(new Dimension(150, 30));
						ratingComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(ratingComboBox);
					}
					panel2.add(moviePanel);

					//======== directorPanel ========
					{
						directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

						//---- directorLabel ----
						directorLabel.setText("Director");
						directorLabel.setFont(directorLabel.getFont().deriveFont(directorLabel.getFont().getSize() + 3f));
						directorPanel.add(directorLabel);

						//---- directorNameLabel ----
						directorNameLabel.setText("Name");
						directorPanel.add(directorNameLabel);

						//---- directorNameTextField ----
						directorNameTextField.setPreferredSize(new Dimension(150, 30));
						directorNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(directorNameTextField);

						//---- heightLabel ----
						heightLabel.setText("Height");
						directorPanel.add(heightLabel);

						//---- heightSpinner ----
						heightSpinner.setPreferredSize(new Dimension(150, 30));
						heightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						heightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(heightSpinner);

						//---- weightLabel ----
						weightLabel.setText("Weight");
						directorPanel.add(weightLabel);

						//---- weightSpinner ----
						weightSpinner.setPreferredSize(new Dimension(150, 30));
						weightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						weightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(weightSpinner);

						//---- passportLabel ----
						passportLabel.setText("Passport ID");
						directorPanel.add(passportLabel);

						//---- passportTextField ----
						passportTextField.setPreferredSize(new Dimension(150, 30));
						passportTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(passportTextField);
					}
					panel2.add(directorPanel);
				}
				panel1.add(panel2);

				//======== panel3 ========
				{
					panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

					//---- coordinatesLabel ----
					coordinatesLabel.setText("Coordinates");
					panel3.add(coordinatesLabel);
				}
				panel1.add(panel3);

				//======== panel4 ========
				{
					panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

					//======== xPanel ========
					{
						xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));

						//---- xLabel ----
						xLabel.setText("X");
						xPanel.add(xLabel);

						//---- xSpinner ----
						xSpinner.setPreferredSize(new Dimension(150, 30));
						xSpinner.setMaximumSize(new Dimension(2147483647, 30));
						xSpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						xPanel.add(xSpinner);
					}
					panel4.add(xPanel);

					//======== yPanel ========
					{
						yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));

						//---- yLabel ----
						yLabel.setText("Y");
						yPanel.add(yLabel);

						//---- ySpinner ----
						ySpinner.setPreferredSize(new Dimension(150, 30));
						ySpinner.setMaximumSize(new Dimension(2147483647, 30));
						ySpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						yPanel.add(ySpinner);
					}
					panel4.add(yPanel);
				}
				panel1.add(panel4);
			}
			contentPanel.add(panel1, BorderLayout.CENTER);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("OK");
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
	private JLabel headingLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel moviePanel;
	private JLabel movieLabel;
	private JLabel movieNameLabel;
	private JTextField movieNameTextField;
	private JLabel oscarsLabel;
	private JSpinner oscarsSpinner;
	private JLabel genreLabel;
	private JComboBox genreSpinner;
	private JLabel ratingLabel;
	private JComboBox ratingComboBox;
	private JPanel directorPanel;
	private JLabel directorLabel;
	private JLabel directorNameLabel;
	private JTextField directorNameTextField;
	private JLabel heightLabel;
	private JSpinner heightSpinner;
	private JLabel weightLabel;
	private JSpinner weightSpinner;
	private JLabel passportLabel;
	private JTextField passportTextField;
	private JPanel panel3;
	private JLabel coordinatesLabel;
	private JPanel panel4;
	private JPanel xPanel;
	private JLabel xLabel;
	private JSpinner xSpinner;
	private JPanel yPanel;
	private JLabel yLabel;
	private JSpinner ySpinner;
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
	headingLabel = new JLabel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	moviePanel = new JPanel();
	movieLabel = new JLabel();
	movieNameLabel = new JLabel();
	movieNameTextField = new JTextField();
	oscarsLabel = new JLabel();
	oscarsSpinner = new JSpinner();
	genreLabel = new JLabel();
	genreSpinner = new JComboBox();
	ratingLabel = new JLabel();
	ratingComboBox = new JComboBox();
	directorPanel = new JPanel();
	directorLabel = new JLabel();
	directorNameLabel = new JLabel();
	directorNameTextField = new JTextField();
	heightLabel = new JLabel();
	heightSpinner = new JSpinner();
	weightLabel = new JLabel();
	weightSpinner = new JSpinner();
	passportLabel = new JLabel();
	passportTextField = new JTextField();
	panel3 = new JPanel();
	coordinatesLabel = new JLabel();
	panel4 = new JPanel();
	xPanel = new JPanel();
	xLabel = new JLabel();
	xSpinner = new JSpinner();
	yPanel = new JPanel();
	yLabel = new JLabel();
	ySpinner = new JSpinner();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
		border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER
		, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font
		.BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (
		new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order"
		.equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BorderLayout());

			//---- headingLabel ----
			headingLabel.setText("Insert");
			headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
			contentPanel.add(headingLabel, BorderLayout.NORTH);

			//======== panel1 ========
			{
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

				//======== panel2 ========
				{
					panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

					//======== moviePanel ========
					{
						moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

						//---- movieLabel ----
						movieLabel.setText("Movie");
						movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getSize() + 3f));
						moviePanel.add(movieLabel);

						//---- movieNameLabel ----
						movieNameLabel.setText("Name");
						moviePanel.add(movieNameLabel);

						//---- movieNameTextField ----
						movieNameTextField.setPreferredSize(new Dimension(150, 30));
						movieNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(movieNameTextField);

						//---- oscarsLabel ----
						oscarsLabel.setText("Oscars");
						moviePanel.add(oscarsLabel);

						//---- oscarsSpinner ----
						oscarsSpinner.setPreferredSize(new Dimension(150, 30));
						oscarsSpinner.setMaximumSize(new Dimension(2147483647, 30));
						oscarsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						moviePanel.add(oscarsSpinner);

						//---- genreLabel ----
						genreLabel.setText("Genre");
						moviePanel.add(genreLabel);

						//---- genreSpinner ----
						genreSpinner.setPreferredSize(new Dimension(150, 30));
						genreSpinner.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(genreSpinner);

						//---- ratingLabel ----
						ratingLabel.setText("Rating");
						moviePanel.add(ratingLabel);

						//---- ratingComboBox ----
						ratingComboBox.setPreferredSize(new Dimension(150, 30));
						ratingComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(ratingComboBox);
					}
					panel2.add(moviePanel);

					//======== directorPanel ========
					{
						directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

						//---- directorLabel ----
						directorLabel.setText("Director");
						directorLabel.setFont(directorLabel.getFont().deriveFont(directorLabel.getFont().getSize() + 3f));
						directorPanel.add(directorLabel);

						//---- directorNameLabel ----
						directorNameLabel.setText("Name");
						directorPanel.add(directorNameLabel);

						//---- directorNameTextField ----
						directorNameTextField.setPreferredSize(new Dimension(150, 30));
						directorNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(directorNameTextField);

						//---- heightLabel ----
						heightLabel.setText("Height");
						directorPanel.add(heightLabel);

						//---- heightSpinner ----
						heightSpinner.setPreferredSize(new Dimension(150, 30));
						heightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						heightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(heightSpinner);

						//---- weightLabel ----
						weightLabel.setText("Weight");
						directorPanel.add(weightLabel);

						//---- weightSpinner ----
						weightSpinner.setPreferredSize(new Dimension(150, 30));
						weightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						weightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(weightSpinner);

						//---- passportLabel ----
						passportLabel.setText("Passport ID");
						directorPanel.add(passportLabel);

						//---- passportTextField ----
						passportTextField.setPreferredSize(new Dimension(150, 30));
						passportTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(passportTextField);
					}
					panel2.add(directorPanel);
				}
				panel1.add(panel2);

				//======== panel3 ========
				{
					panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

					//---- coordinatesLabel ----
					coordinatesLabel.setText("Coordinates");
					panel3.add(coordinatesLabel);
				}
				panel1.add(panel3);

				//======== panel4 ========
				{
					panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

					//======== xPanel ========
					{
						xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));

						//---- xLabel ----
						xLabel.setText("X");
						xPanel.add(xLabel);

						//---- xSpinner ----
						xSpinner.setPreferredSize(new Dimension(150, 30));
						xSpinner.setMaximumSize(new Dimension(2147483647, 30));
						xSpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						xPanel.add(xSpinner);
					}
					panel4.add(xPanel);

					//======== yPanel ========
					{
						yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));

						//---- yLabel ----
						yLabel.setText("Y");
						yPanel.add(yLabel);

						//---- ySpinner ----
						ySpinner.setPreferredSize(new Dimension(150, 30));
						ySpinner.setMaximumSize(new Dimension(2147483647, 30));
						ySpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						yPanel.add(ySpinner);
					}
					panel4.add(yPanel);
				}
				panel1.add(panel4);
			}
			contentPanel.add(panel1, BorderLayout.CENTER);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("OK");
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
	private JLabel headingLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel moviePanel;
	private JLabel movieLabel;
	private JLabel movieNameLabel;
	private JTextField movieNameTextField;
	private JLabel oscarsLabel;
	private JSpinner oscarsSpinner;
	private JLabel genreLabel;
	private JComboBox genreSpinner;
	private JLabel ratingLabel;
	private JComboBox ratingComboBox;
	private JPanel directorPanel;
	private JLabel directorLabel;
	private JLabel directorNameLabel;
	private JTextField directorNameTextField;
	private JLabel heightLabel;
	private JSpinner heightSpinner;
	private JLabel weightLabel;
	private JSpinner weightSpinner;
	private JLabel passportLabel;
	private JTextField passportTextField;
	private JPanel panel3;
	private JLabel coordinatesLabel;
	private JPanel panel4;
	private JPanel xPanel;
	private JLabel xLabel;
	private JSpinner xSpinner;
	private JPanel yPanel;
	private JLabel yLabel;
	private JSpinner ySpinner;
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
	headingLabel = new JLabel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	moviePanel = new JPanel();
	movieLabel = new JLabel();
	movieNameLabel = new JLabel();
	movieNameTextField = new JTextField();
	oscarsLabel = new JLabel();
	oscarsSpinner = new JSpinner();
	genreLabel = new JLabel();
	genreSpinner = new JComboBox();
	ratingLabel = new JLabel();
	ratingComboBox = new JComboBox();
	directorPanel = new JPanel();
	directorLabel = new JLabel();
	directorNameLabel = new JLabel();
	directorNameTextField = new JTextField();
	heightLabel = new JLabel();
	heightSpinner = new JSpinner();
	weightLabel = new JLabel();
	weightSpinner = new JSpinner();
	passportLabel = new JLabel();
	passportTextField = new JTextField();
	panel3 = new JPanel();
	coordinatesLabel = new JLabel();
	panel4 = new JPanel();
	xPanel = new JPanel();
	xLabel = new JLabel();
	xSpinner = new JSpinner();
	yPanel = new JPanel();
	yLabel = new JLabel();
	ySpinner = new JSpinner();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
		javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax
		.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
		.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
		.Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.
		PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".
		equals(e.getPropertyName()))throw new RuntimeException();}});
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BorderLayout());

			//---- headingLabel ----
			headingLabel.setText("Insert");
			headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
			contentPanel.add(headingLabel, BorderLayout.NORTH);

			//======== panel1 ========
			{
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

				//======== panel2 ========
				{
					panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

					//======== moviePanel ========
					{
						moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

						//---- movieLabel ----
						movieLabel.setText("Movie");
						movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getSize() + 3f));
						moviePanel.add(movieLabel);

						//---- movieNameLabel ----
						movieNameLabel.setText("Name");
						moviePanel.add(movieNameLabel);

						//---- movieNameTextField ----
						movieNameTextField.setPreferredSize(new Dimension(150, 30));
						movieNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(movieNameTextField);

						//---- oscarsLabel ----
						oscarsLabel.setText("Oscars");
						moviePanel.add(oscarsLabel);

						//---- oscarsSpinner ----
						oscarsSpinner.setPreferredSize(new Dimension(150, 30));
						oscarsSpinner.setMaximumSize(new Dimension(2147483647, 30));
						oscarsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						moviePanel.add(oscarsSpinner);

						//---- genreLabel ----
						genreLabel.setText("Genre");
						moviePanel.add(genreLabel);

						//---- genreSpinner ----
						genreSpinner.setPreferredSize(new Dimension(150, 30));
						genreSpinner.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(genreSpinner);

						//---- ratingLabel ----
						ratingLabel.setText("Rating");
						moviePanel.add(ratingLabel);

						//---- ratingComboBox ----
						ratingComboBox.setPreferredSize(new Dimension(150, 30));
						ratingComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(ratingComboBox);
					}
					panel2.add(moviePanel);

					//======== directorPanel ========
					{
						directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

						//---- directorLabel ----
						directorLabel.setText("Director");
						directorLabel.setFont(directorLabel.getFont().deriveFont(directorLabel.getFont().getSize() + 3f));
						directorPanel.add(directorLabel);

						//---- directorNameLabel ----
						directorNameLabel.setText("Name");
						directorPanel.add(directorNameLabel);

						//---- directorNameTextField ----
						directorNameTextField.setPreferredSize(new Dimension(150, 30));
						directorNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(directorNameTextField);

						//---- heightLabel ----
						heightLabel.setText("Height");
						directorPanel.add(heightLabel);

						//---- heightSpinner ----
						heightSpinner.setPreferredSize(new Dimension(150, 30));
						heightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						heightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(heightSpinner);

						//---- weightLabel ----
						weightLabel.setText("Weight");
						directorPanel.add(weightLabel);

						//---- weightSpinner ----
						weightSpinner.setPreferredSize(new Dimension(150, 30));
						weightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						weightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(weightSpinner);

						//---- passportLabel ----
						passportLabel.setText("Passport ID");
						directorPanel.add(passportLabel);

						//---- passportTextField ----
						passportTextField.setPreferredSize(new Dimension(150, 30));
						passportTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(passportTextField);
					}
					panel2.add(directorPanel);
				}
				panel1.add(panel2);

				//======== panel3 ========
				{
					panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

					//---- coordinatesLabel ----
					coordinatesLabel.setText("Coordinates");
					panel3.add(coordinatesLabel);
				}
				panel1.add(panel3);

				//======== panel4 ========
				{
					panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

					//======== xPanel ========
					{
						xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));

						//---- xLabel ----
						xLabel.setText("X");
						xPanel.add(xLabel);

						//---- xSpinner ----
						xSpinner.setPreferredSize(new Dimension(150, 30));
						xSpinner.setMaximumSize(new Dimension(2147483647, 30));
						xSpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						xPanel.add(xSpinner);
					}
					panel4.add(xPanel);

					//======== yPanel ========
					{
						yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));

						//---- yLabel ----
						yLabel.setText("Y");
						yPanel.add(yLabel);

						//---- ySpinner ----
						ySpinner.setPreferredSize(new Dimension(150, 30));
						ySpinner.setMaximumSize(new Dimension(2147483647, 30));
						ySpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						yPanel.add(ySpinner);
					}
					panel4.add(yPanel);
				}
				panel1.add(panel4);
			}
			contentPanel.add(panel1, BorderLayout.CENTER);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("OK");
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
	private JLabel headingLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel moviePanel;
	private JLabel movieLabel;
	private JLabel movieNameLabel;
	private JTextField movieNameTextField;
	private JLabel oscarsLabel;
	private JSpinner oscarsSpinner;
	private JLabel genreLabel;
	private JComboBox genreSpinner;
	private JLabel ratingLabel;
	private JComboBox ratingComboBox;
	private JPanel directorPanel;
	private JLabel directorLabel;
	private JLabel directorNameLabel;
	private JTextField directorNameTextField;
	private JLabel heightLabel;
	private JSpinner heightSpinner;
	private JLabel weightLabel;
	private JSpinner weightSpinner;
	private JLabel passportLabel;
	private JTextField passportTextField;
	private JPanel panel3;
	private JLabel coordinatesLabel;
	private JPanel panel4;
	private JPanel xPanel;
	private JLabel xLabel;
	private JSpinner xSpinner;
	private JPanel yPanel;
	private JLabel yLabel;
	private JSpinner ySpinner;
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
	headingLabel = new JLabel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	moviePanel = new JPanel();
	movieLabel = new JLabel();
	movieNameLabel = new JLabel();
	movieNameTextField = new JTextField();
	oscarsLabel = new JLabel();
	oscarsSpinner = new JSpinner();
	genreLabel = new JLabel();
	genreComboBox = new JComboBox();
	ratingLabel = new JLabel();
	ratingComboBox = new JComboBox();
	directorPanel = new JPanel();
	directorLabel = new JLabel();
	directorNameLabel = new JLabel();
	directorNameTextField = new JTextField();
	birthdayLabel = new JLabel();
	birthdayDatePicker = new JSpinner();
	weightLabel = new JLabel();
	weightSpinner = new JSpinner();
	passportLabel = new JLabel();
	passportTextField = new JTextField();
	panel3 = new JPanel();
	coordinatesLabel = new JLabel();
	panel4 = new JPanel();
	xPanel = new JPanel();
	xLabel = new JLabel();
	xSpinner = new JSpinner();
	yPanel = new JPanel();
	yLabel = new JLabel();
	ySpinner = new JSpinner();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
		. swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing
		.border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
		Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
		) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
		public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName (
		) ) )throw new RuntimeException( ) ;} } );
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BorderLayout());

			//---- headingLabel ----
			headingLabel.setText("Insert");
			headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
			contentPanel.add(headingLabel, BorderLayout.NORTH);

			//======== panel1 ========
			{
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

				//======== panel2 ========
				{
					panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

					//======== moviePanel ========
					{
						moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

						//---- movieLabel ----
						movieLabel.setText("Movie");
						movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getSize() + 3f));
						moviePanel.add(movieLabel);

						//---- movieNameLabel ----
						movieNameLabel.setText("Name");
						moviePanel.add(movieNameLabel);

						//---- movieNameTextField ----
						movieNameTextField.setPreferredSize(new Dimension(150, 30));
						movieNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(movieNameTextField);

						//---- oscarsLabel ----
						oscarsLabel.setText("Oscars");
						moviePanel.add(oscarsLabel);

						//---- oscarsSpinner ----
						oscarsSpinner.setPreferredSize(new Dimension(150, 30));
						oscarsSpinner.setMaximumSize(new Dimension(2147483647, 30));
						oscarsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						moviePanel.add(oscarsSpinner);

						//---- genreLabel ----
						genreLabel.setText("Genre");
						moviePanel.add(genreLabel);

						//---- genreComboBox ----
						genreComboBox.setPreferredSize(new Dimension(150, 30));
						genreComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(genreComboBox);

						//---- ratingLabel ----
						ratingLabel.setText("Rating");
						moviePanel.add(ratingLabel);

						//---- ratingComboBox ----
						ratingComboBox.setPreferredSize(new Dimension(150, 30));
						ratingComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(ratingComboBox);
					}
					panel2.add(moviePanel);

					//======== directorPanel ========
					{
						directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

						//---- directorLabel ----
						directorLabel.setText("Director");
						directorLabel.setFont(directorLabel.getFont().deriveFont(directorLabel.getFont().getSize() + 3f));
						directorPanel.add(directorLabel);

						//---- directorNameLabel ----
						directorNameLabel.setText("Name");
						directorPanel.add(directorNameLabel);

						//---- directorNameTextField ----
						directorNameTextField.setPreferredSize(new Dimension(150, 30));
						directorNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(directorNameTextField);

						//---- birthdayLabel ----
						birthdayLabel.setText("Birthday");
						directorPanel.add(birthdayLabel);

						//---- birthdayDatePicker ----
						birthdayDatePicker.setPreferredSize(new Dimension(150, 30));
						birthdayDatePicker.setMaximumSize(new Dimension(2147483647, 30));
						birthdayDatePicker.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(birthdayDatePicker);

						//---- weightLabel ----
						weightLabel.setText("Weight");
						directorPanel.add(weightLabel);

						//---- weightSpinner ----
						weightSpinner.setPreferredSize(new Dimension(150, 30));
						weightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						weightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(weightSpinner);

						//---- passportLabel ----
						passportLabel.setText("Passport ID");
						directorPanel.add(passportLabel);

						//---- passportTextField ----
						passportTextField.setPreferredSize(new Dimension(150, 30));
						passportTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(passportTextField);
					}
					panel2.add(directorPanel);
				}
				panel1.add(panel2);

				//======== panel3 ========
				{
					panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

					//---- coordinatesLabel ----
					coordinatesLabel.setText("Coordinates");
					panel3.add(coordinatesLabel);
				}
				panel1.add(panel3);

				//======== panel4 ========
				{
					panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

					//======== xPanel ========
					{
						xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));

						//---- xLabel ----
						xLabel.setText("X");
						xPanel.add(xLabel);

						//---- xSpinner ----
						xSpinner.setPreferredSize(new Dimension(150, 30));
						xSpinner.setMaximumSize(new Dimension(2147483647, 30));
						xSpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						xPanel.add(xSpinner);
					}
					panel4.add(xPanel);

					//======== yPanel ========
					{
						yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));

						//---- yLabel ----
						yLabel.setText("Y");
						yPanel.add(yLabel);

						//---- ySpinner ----
						ySpinner.setPreferredSize(new Dimension(150, 30));
						ySpinner.setMaximumSize(new Dimension(2147483647, 30));
						ySpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						yPanel.add(ySpinner);
					}
					panel4.add(yPanel);
				}
				panel1.add(panel4);
			}
			contentPanel.add(panel1, BorderLayout.CENTER);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("OK");
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
	private JLabel headingLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel moviePanel;
	private JLabel movieLabel;
	private JLabel movieNameLabel;
	private JTextField movieNameTextField;
	private JLabel oscarsLabel;
	private JSpinner oscarsSpinner;
	private JLabel genreLabel;
	private JComboBox genreComboBox;
	private JLabel ratingLabel;
	private JComboBox ratingComboBox;
	private JPanel directorPanel;
	private JLabel directorLabel;
	private JLabel directorNameLabel;
	private JTextField directorNameTextField;
	private JLabel birthdayLabel;
	private JSpinner birthdayDatePicker;
	private JLabel weightLabel;
	private JSpinner weightSpinner;
	private JLabel passportLabel;
	private JTextField passportTextField;
	private JPanel panel3;
	private JLabel coordinatesLabel;
	private JPanel panel4;
	private JPanel xPanel;
	private JLabel xLabel;
	private JSpinner xSpinner;
	private JPanel yPanel;
	private JLabel yLabel;
	private JSpinner ySpinner;
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
	headingLabel = new JLabel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	moviePanel = new JPanel();
	movieLabel = new JLabel();
	movieNameLabel = new JLabel();
	movieNameTextField = new JTextField();
	oscarsLabel = new JLabel();
	oscarsSpinner = new JSpinner();
	genreLabel = new JLabel();
	genreComboBox = new JComboBox();
	ratingLabel = new JLabel();
	ratingComboBox = new JComboBox();
	directorPanel = new JPanel();
	directorLabel = new JLabel();
	directorNameLabel = new JLabel();
	directorNameTextField = new JTextField();
	birthdayLabel = new JLabel();
	birthdayDatePicker = new JSpinner();
	weightLabel = new JLabel();
	weightSpinner = new JSpinner();
	passportLabel = new JLabel();
	passportTextField = new JTextField();
	panel3 = new JPanel();
	coordinatesLabel = new JLabel();
	panel4 = new JPanel();
	xPanel = new JPanel();
	xLabel = new JLabel();
	xSpinner = new JSpinner();
	yPanel = new JPanel();
	yLabel = new JLabel();
	ySpinner = new JSpinner();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
		. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder
		. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
		awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,dialogPane. getBorder () ) )
		; dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
		) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
		;
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BorderLayout());

			//---- headingLabel ----
			headingLabel.setText("Insert");
			headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
			contentPanel.add(headingLabel, BorderLayout.NORTH);

			//======== panel1 ========
			{
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

				//======== panel2 ========
				{
					panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

					//======== moviePanel ========
					{
						moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

						//---- movieLabel ----
						movieLabel.setText("Movie");
						movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getSize() + 3f));
						moviePanel.add(movieLabel);

						//---- movieNameLabel ----
						movieNameLabel.setText("Name");
						moviePanel.add(movieNameLabel);

						//---- movieNameTextField ----
						movieNameTextField.setPreferredSize(new Dimension(150, 30));
						movieNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(movieNameTextField);

						//---- oscarsLabel ----
						oscarsLabel.setText("Oscars");
						moviePanel.add(oscarsLabel);

						//---- oscarsSpinner ----
						oscarsSpinner.setPreferredSize(new Dimension(150, 30));
						oscarsSpinner.setMaximumSize(new Dimension(2147483647, 30));
						oscarsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						moviePanel.add(oscarsSpinner);

						//---- genreLabel ----
						genreLabel.setText("Genre");
						moviePanel.add(genreLabel);

						//---- genreComboBox ----
						genreComboBox.setPreferredSize(new Dimension(150, 30));
						genreComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(genreComboBox);

						//---- ratingLabel ----
						ratingLabel.setText("Rating");
						moviePanel.add(ratingLabel);

						//---- ratingComboBox ----
						ratingComboBox.setPreferredSize(new Dimension(150, 30));
						ratingComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(ratingComboBox);
					}
					panel2.add(moviePanel);

					//======== directorPanel ========
					{
						directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

						//---- directorLabel ----
						directorLabel.setText("Director");
						directorLabel.setFont(directorLabel.getFont().deriveFont(directorLabel.getFont().getSize() + 3f));
						directorPanel.add(directorLabel);

						//---- directorNameLabel ----
						directorNameLabel.setText("Name");
						directorPanel.add(directorNameLabel);

						//---- directorNameTextField ----
						directorNameTextField.setPreferredSize(new Dimension(150, 30));
						directorNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(directorNameTextField);

						//---- birthdayLabel ----
						birthdayLabel.setText("Birthday");
						directorPanel.add(birthdayLabel);

						//---- birthdayDatePicker ----
						birthdayDatePicker.setPreferredSize(new Dimension(150, 30));
						birthdayDatePicker.setMaximumSize(new Dimension(2147483647, 30));
						birthdayDatePicker.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(birthdayDatePicker);

						//---- weightLabel ----
						weightLabel.setText("Weight");
						directorPanel.add(weightLabel);

						//---- weightSpinner ----
						weightSpinner.setPreferredSize(new Dimension(150, 30));
						weightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						weightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(weightSpinner);

						//---- passportLabel ----
						passportLabel.setText("Passport ID");
						directorPanel.add(passportLabel);

						//---- passportTextField ----
						passportTextField.setPreferredSize(new Dimension(150, 30));
						passportTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(passportTextField);
					}
					panel2.add(directorPanel);
				}
				panel1.add(panel2);

				//======== panel3 ========
				{
					panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

					//---- coordinatesLabel ----
					coordinatesLabel.setText("Coordinates");
					panel3.add(coordinatesLabel);
				}
				panel1.add(panel3);

				//======== panel4 ========
				{
					panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

					//======== xPanel ========
					{
						xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));

						//---- xLabel ----
						xLabel.setText("X");
						xPanel.add(xLabel);

						//---- xSpinner ----
						xSpinner.setPreferredSize(new Dimension(150, 30));
						xSpinner.setMaximumSize(new Dimension(2147483647, 30));
						xSpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						xPanel.add(xSpinner);
					}
					panel4.add(xPanel);

					//======== yPanel ========
					{
						yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));

						//---- yLabel ----
						yLabel.setText("Y");
						yPanel.add(yLabel);

						//---- ySpinner ----
						ySpinner.setPreferredSize(new Dimension(150, 30));
						ySpinner.setMaximumSize(new Dimension(2147483647, 30));
						ySpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						yPanel.add(ySpinner);
					}
					panel4.add(yPanel);
				}
				panel1.add(panel4);
			}
			contentPanel.add(panel1, BorderLayout.CENTER);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("OK");
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
	private JLabel headingLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel moviePanel;
	private JLabel movieLabel;
	private JLabel movieNameLabel;
	private JTextField movieNameTextField;
	private JLabel oscarsLabel;
	private JSpinner oscarsSpinner;
	private JLabel genreLabel;
	private JComboBox genreComboBox;
	private JLabel ratingLabel;
	private JComboBox ratingComboBox;
	private JPanel directorPanel;
	private JLabel directorLabel;
	private JLabel directorNameLabel;
	private JTextField directorNameTextField;
	private JLabel birthdayLabel;
	private JSpinner birthdayDatePicker;
	private JLabel weightLabel;
	private JSpinner weightSpinner;
	private JLabel passportLabel;
	private JTextField passportTextField;
	private JPanel panel3;
	private JLabel coordinatesLabel;
	private JPanel panel4;
	private JPanel xPanel;
	private JLabel xLabel;
	private JSpinner xSpinner;
	private JPanel yPanel;
	private JLabel yLabel;
	private JSpinner ySpinner;
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
	headingLabel = new JLabel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	moviePanel = new JPanel();
	movieLabel = new JLabel();
	movieNameLabel = new JLabel();
	movieNameTextField = new JTextField();
	oscarsLabel = new JLabel();
	oscarsSpinner = new JSpinner();
	genreLabel = new JLabel();
	genreComboBox = new JComboBox();
	ratingLabel = new JLabel();
	ratingComboBox = new JComboBox();
	directorPanel = new JPanel();
	directorLabel = new JLabel();
	directorNameLabel = new JLabel();
	directorNameTextField = new JTextField();
	birthdayLabel = new JLabel();
	birthdayDatePicker = new JSpinner();
	weightLabel = new JLabel();
	weightSpinner = new JSpinner();
	passportLabel = new JLabel();
	passportTextField = new JTextField();
	panel3 = new JPanel();
	coordinatesLabel = new JLabel();
	panel4 = new JPanel();
	xPanel = new JPanel();
	xLabel = new JLabel();
	xSpinner = new JSpinner();
	yPanel = new JPanel();
	yLabel = new JLabel();
	ySpinner = new JSpinner();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();

	//======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	//======== dialogPane ========
	{
		dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
		.swing.border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing
		.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
		Font("D\u0069al\u006fg",java.awt.Font.BOLD,12),java.awt.Color.red
		),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
		public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er".equals(e.getPropertyName(
		)))throw new RuntimeException();}});
		dialogPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setLayout(new BorderLayout());

			//---- headingLabel ----
			headingLabel.setText("Insert");
			headingLabel.setFont(headingLabel.getFont().deriveFont(headingLabel.getFont().getSize() + 7f));
			contentPanel.add(headingLabel, BorderLayout.NORTH);

			//======== panel1 ========
			{
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

				//======== panel2 ========
				{
					panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

					//======== moviePanel ========
					{
						moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

						//---- movieLabel ----
						movieLabel.setText("Movie");
						movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getSize() + 3f));
						moviePanel.add(movieLabel);

						//---- movieNameLabel ----
						movieNameLabel.setText("Name");
						moviePanel.add(movieNameLabel);

						//---- movieNameTextField ----
						movieNameTextField.setPreferredSize(new Dimension(150, 30));
						movieNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(movieNameTextField);

						//---- oscarsLabel ----
						oscarsLabel.setText("Oscars");
						moviePanel.add(oscarsLabel);

						//---- oscarsSpinner ----
						oscarsSpinner.setPreferredSize(new Dimension(150, 30));
						oscarsSpinner.setMaximumSize(new Dimension(2147483647, 30));
						oscarsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						moviePanel.add(oscarsSpinner);

						//---- genreLabel ----
						genreLabel.setText("Genre");
						moviePanel.add(genreLabel);

						//---- genreComboBox ----
						genreComboBox.setPreferredSize(new Dimension(150, 30));
						genreComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(genreComboBox);

						//---- ratingLabel ----
						ratingLabel.setText("Rating");
						moviePanel.add(ratingLabel);

						//---- ratingComboBox ----
						ratingComboBox.setPreferredSize(new Dimension(150, 30));
						ratingComboBox.setMaximumSize(new Dimension(2147483647, 30));
						moviePanel.add(ratingComboBox);
					}
					panel2.add(moviePanel);

					//======== directorPanel ========
					{
						directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

						//---- directorLabel ----
						directorLabel.setText("Director");
						directorLabel.setFont(directorLabel.getFont().deriveFont(directorLabel.getFont().getSize() + 3f));
						directorPanel.add(directorLabel);

						//---- directorNameLabel ----
						directorNameLabel.setText("Name");
						directorPanel.add(directorNameLabel);

						//---- directorNameTextField ----
						directorNameTextField.setPreferredSize(new Dimension(150, 30));
						directorNameTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(directorNameTextField);

						//---- birthdayLabel ----
						birthdayLabel.setText("Birthday");
						directorPanel.add(birthdayLabel);

						//---- birthdayDatePicker ----
						birthdayDatePicker.setPreferredSize(new Dimension(150, 30));
						birthdayDatePicker.setMaximumSize(new Dimension(2147483647, 30));
						birthdayDatePicker.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(birthdayDatePicker);

						//---- weightLabel ----
						weightLabel.setText("Weight");
						directorPanel.add(weightLabel);

						//---- weightSpinner ----
						weightSpinner.setPreferredSize(new Dimension(150, 30));
						weightSpinner.setMaximumSize(new Dimension(2147483647, 30));
						weightSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
						directorPanel.add(weightSpinner);

						//---- passportLabel ----
						passportLabel.setText("Passport ID");
						directorPanel.add(passportLabel);

						//---- passportTextField ----
						passportTextField.setPreferredSize(new Dimension(150, 30));
						passportTextField.setMaximumSize(new Dimension(2147483647, 30));
						directorPanel.add(passportTextField);
					}
					panel2.add(directorPanel);
				}
				panel1.add(panel2);

				//======== panel3 ========
				{
					panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

					//---- coordinatesLabel ----
					coordinatesLabel.setText("Coordinates");
					panel3.add(coordinatesLabel);
				}
				panel1.add(panel3);

				//======== panel4 ========
				{
					panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

					//======== xPanel ========
					{
						xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));

						//---- xLabel ----
						xLabel.setText("X");
						xPanel.add(xLabel);

						//---- xSpinner ----
						xSpinner.setPreferredSize(new Dimension(150, 30));
						xSpinner.setMaximumSize(new Dimension(2147483647, 30));
						xSpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						xPanel.add(xSpinner);
					}
					panel4.add(xPanel);

					//======== yPanel ========
					{
						yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));

						//---- yLabel ----
						yLabel.setText("Y");
						yPanel.add(yLabel);

						//---- ySpinner ----
						ySpinner.setPreferredSize(new Dimension(150, 30));
						ySpinner.setMaximumSize(new Dimension(2147483647, 30));
						ySpinner.setModel(new SpinnerNumberModel(0, null, null, 1));
						yPanel.add(ySpinner);
					}
					panel4.add(yPanel);
				}
				panel1.add(panel4);
			}
			contentPanel.add(panel1, BorderLayout.CENTER);
		}
		dialogPane.add(contentPanel, BorderLayout.CENTER);

		//======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
			((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

			//---- okButton ----
			okButton.setText("OK");
			okButton.setBackground(UIManager.getColor("Button.default.background"));
			okButton.setForeground(UIManager.getColor("Button.default.foreground"));
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
	private JLabel headingLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel moviePanel;
	private JLabel movieLabel;
	private JLabel movieNameLabel;
	private JTextField movieNameTextField;
	private JLabel oscarsLabel;
	private JSpinner oscarsSpinner;
	private JLabel genreLabel;
	private JComboBox genreComboBox;
	private JLabel ratingLabel;
	private JComboBox ratingComboBox;
	private JPanel directorPanel;
	private JLabel directorLabel;
	private JLabel directorNameLabel;
	private JTextField directorNameTextField;
	private JLabel birthdayLabel;
	private JSpinner birthdayDatePicker;
	private JLabel weightLabel;
	private JSpinner weightSpinner;
	private JLabel passportLabel;
	private JTextField passportTextField;
	private JPanel panel3;
	private JLabel coordinatesLabel;
	private JPanel panel4;
	private JPanel xPanel;
	private JLabel xLabel;
	private JSpinner xSpinner;
	private JPanel yPanel;
	private JLabel yLabel;
	private JSpinner ySpinner;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
}
