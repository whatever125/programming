import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jun 22 00:07:45 MSK 2023
 */



/**
 * @author whatever125
 */
public class Dialog extends JDialog {
	public Dialog(Window owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Dmitry
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		headingLabel = new JLabel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		moviePanel = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		textField1 = new JTextField();
		label3 = new JLabel();
		textField2 = new JTextField();
		label4 = new JLabel();
		comboBox1 = new JComboBox();
		label5 = new JLabel();
		comboBox2 = new JComboBox();
		directorPanel = new JPanel();
		label6 = new JLabel();
		label7 = new JLabel();
		textField3 = new JTextField();
		label8 = new JLabel();
		spinner1 = new JSpinner();
		label9 = new JLabel();
		spinner2 = new JSpinner();
		label10 = new JLabel();
		textField4 = new JTextField();
		panel3 = new JPanel();
		label11 = new JLabel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		label12 = new JLabel();
		textField5 = new JTextField();
		panel6 = new JPanel();
		label13 = new JLabel();
		textField6 = new JTextField();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
			javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax
			. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
			.awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
			. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans.
			PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .
			equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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

							//---- label1 ----
							label1.setText("Movie");
							label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
							moviePanel.add(label1);

							//---- label2 ----
							label2.setText("Name");
							moviePanel.add(label2);

							//---- textField1 ----
							textField1.setPreferredSize(new Dimension(150, 30));
							textField1.setMaximumSize(new Dimension(2147483647, 30));
							moviePanel.add(textField1);

							//---- label3 ----
							label3.setText("Oscars");
							moviePanel.add(label3);

							//---- textField2 ----
							textField2.setPreferredSize(new Dimension(150, 30));
							textField2.setMaximumSize(new Dimension(2147483647, 30));
							moviePanel.add(textField2);

							//---- label4 ----
							label4.setText("Genre");
							moviePanel.add(label4);

							//---- comboBox1 ----
							comboBox1.setPreferredSize(new Dimension(150, 30));
							comboBox1.setMaximumSize(new Dimension(2147483647, 30));
							moviePanel.add(comboBox1);

							//---- label5 ----
							label5.setText("Rating");
							moviePanel.add(label5);

							//---- comboBox2 ----
							comboBox2.setPreferredSize(new Dimension(150, 30));
							comboBox2.setMaximumSize(new Dimension(2147483647, 30));
							moviePanel.add(comboBox2);
						}
						panel2.add(moviePanel);

						//======== directorPanel ========
						{
							directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));

							//---- label6 ----
							label6.setText("Director");
							label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 3f));
							directorPanel.add(label6);

							//---- label7 ----
							label7.setText("Name");
							directorPanel.add(label7);

							//---- textField3 ----
							textField3.setPreferredSize(new Dimension(150, 30));
							textField3.setMaximumSize(new Dimension(2147483647, 30));
							directorPanel.add(textField3);

							//---- label8 ----
							label8.setText("Height");
							directorPanel.add(label8);

							//---- spinner1 ----
							spinner1.setPreferredSize(new Dimension(150, 30));
							spinner1.setMaximumSize(new Dimension(2147483647, 30));
							spinner1.setModel(new SpinnerNumberModel(0, 0, null, 1));
							directorPanel.add(spinner1);

							//---- label9 ----
							label9.setText("Weight");
							directorPanel.add(label9);

							//---- spinner2 ----
							spinner2.setPreferredSize(new Dimension(150, 30));
							spinner2.setMaximumSize(new Dimension(2147483647, 30));
							spinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
							directorPanel.add(spinner2);

							//---- label10 ----
							label10.setText("Passport ID");
							directorPanel.add(label10);

							//---- textField4 ----
							textField4.setPreferredSize(new Dimension(150, 30));
							textField4.setMaximumSize(new Dimension(2147483647, 30));
							directorPanel.add(textField4);
						}
						panel2.add(directorPanel);
					}
					panel1.add(panel2);

					//======== panel3 ========
					{
						panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

						//---- label11 ----
						label11.setText("Coordinates");
						panel3.add(label11);
					}
					panel1.add(panel3);

					//======== panel4 ========
					{
						panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

						//======== panel5 ========
						{
							panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

							//---- label12 ----
							label12.setText("X");
							panel5.add(label12);

							//---- textField5 ----
							textField5.setPreferredSize(new Dimension(150, 30));
							textField5.setMaximumSize(new Dimension(2147483647, 30));
							panel5.add(textField5);
						}
						panel4.add(panel5);

						//======== panel6 ========
						{
							panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

							//---- label13 ----
							label13.setText("Y");
							panel6.add(label13);

							//---- textField6 ----
							textField6.setPreferredSize(new Dimension(150, 30));
							textField6.setMaximumSize(new Dimension(2147483647, 30));
							panel6.add(textField6);
						}
						panel4.add(panel6);
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
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel headingLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel moviePanel;
	private JLabel label1;
	private JLabel label2;
	private JTextField textField1;
	private JLabel label3;
	private JTextField textField2;
	private JLabel label4;
	private JComboBox comboBox1;
	private JLabel label5;
	private JComboBox comboBox2;
	private JPanel directorPanel;
	private JLabel label6;
	private JLabel label7;
	private JTextField textField3;
	private JLabel label8;
	private JSpinner spinner1;
	private JLabel label9;
	private JSpinner spinner2;
	private JLabel label10;
	private JTextField textField4;
	private JPanel panel3;
	private JLabel label11;
	private JPanel panel4;
	private JPanel panel5;
	private JLabel label12;
	private JTextField textField5;
	private JPanel panel6;
	private JLabel label13;
	private JTextField textField6;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
