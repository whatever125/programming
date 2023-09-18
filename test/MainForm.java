import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Jun 21 23:43:04 MSK 2023
 */



/**
 * @author whatever125
 */
public class MainForm extends JPanel {
	public MainForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Dmitry
		tabbedPane = new JTabbedPane();
		tablePanel = new JPanel();
		addButton = new JButton();
		scrollPane = new JScrollPane();
		table = new JTable();
		optionsPanel = new JPanel();
		optionsSpacer = new JPanel(null);
		optionsButton = new JButton();
		graphPanel = new JPanel();
		northPanel = new JPanel();
		headerLable = new JLabel();
		headerSpacer = new JPanel(null);
		helpButton = new JButton();

		//======== this ========
		setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
		new javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion"
		,javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
		,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12)
		,java.awt.Color.red), getBorder())); addPropertyChangeListener(
		new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
		){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException()
		;}});
		setLayout(new BorderLayout());

		//======== tabbedPane ========
		{

			//======== tablePanel ========
			{
				tablePanel.setLayout(new BorderLayout());

				//---- addButton ----
				addButton.setText("+ Add");
				tablePanel.add(addButton, BorderLayout.SOUTH);

				//======== scrollPane ========
				{
					scrollPane.setViewportView(table);
				}
				tablePanel.add(scrollPane, BorderLayout.CENTER);

				//======== optionsPanel ========
				{
					optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
					optionsPanel.add(optionsSpacer);

					//---- optionsButton ----
					optionsButton.setText("...");
					optionsPanel.add(optionsButton);
				}
				tablePanel.add(optionsPanel, BorderLayout.NORTH);
			}
			tabbedPane.addTab("Table", tablePanel);

			//======== graphPanel ========
			{
				graphPanel.setLayout(new BorderLayout());
			}
			tabbedPane.addTab("Graph", graphPanel);
		}
		add(tabbedPane, BorderLayout.CENTER);

		//======== northPanel ========
		{
			northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

			//---- headerLable ----
			headerLable.setText("Movies");
			headerLable.setFont(headerLable.getFont().deriveFont(headerLable.getFont().getSize() + 11f));
			northPanel.add(headerLable);
			northPanel.add(headerSpacer);

			//---- helpButton ----
			helpButton.setText("?");
			northPanel.add(helpButton);
		}
		add(northPanel, BorderLayout.NORTH);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Dmitry
	private JTabbedPane tabbedPane;
	private JPanel tablePanel;
	private JButton addButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel optionsPanel;
	private JPanel optionsSpacer;
	private JButton optionsButton;
	private JPanel graphPanel;
	private JPanel northPanel;
	private JLabel headerLable;
	private JPanel headerSpacer;
	private JButton helpButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
