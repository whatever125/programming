import java.awt.*;
import javax.swing.*;


/**
 * @author whatever125
 */
public class MainForm extends JPanel {
	public MainForm() {
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
	tabbedPane = new JTabbedPane();
	tablePanel = new JPanel();
	addButton = new JButton();
	scrollPane = new JScrollPane();
	table = new JTable();
	optionsPanel = new JPanel();
	optionsSpacer = new JPanel(null);
	optionsButton = new JButton();
	graphPanel = new JPanel();
	graphScrollPan = new JScrollPane();
	northPanel = new JPanel();
	headerLable = new JLabel();
	logOutButton = new JButton();
	headerSpacer = new JPanel(null);
	languageButton = new JButton();
	helpButton = new JButton();

	//======== this ========
	setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
	new javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn"
	,javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
	,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12)
	,java.awt.Color.red), getBorder())); addPropertyChangeListener(
	new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
	){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
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
				optionsPanel.add(optionsButton);
			}
			tablePanel.add(optionsPanel, BorderLayout.NORTH);
		}
		tabbedPane.addTab("Table", tablePanel);

		//======== graphPanel ========
		{
			graphPanel.setLayout(new BorderLayout());
			graphPanel.add(graphScrollPan, BorderLayout.CENTER);
		}
		tabbedPane.addTab("Graph", graphPanel);
	}
	add(tabbedPane, BorderLayout.CENTER);

	//======== northPanel ========
	{
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

		//---- headerLable ----
		headerLable.setFont(headerLable.getFont().deriveFont(headerLable.getFont().getSize() + 11f));
		headerLable.setText("Movies ");
		northPanel.add(headerLable);
		northPanel.add(logOutButton);
		northPanel.add(headerSpacer);
		northPanel.add(languageButton);
		northPanel.add(helpButton);
	}
	add(northPanel, BorderLayout.NORTH);

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
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
	private JScrollPane graphScrollPan;
	private JPanel northPanel;
	private JLabel headerLable;
	private JButton logOutButton;
	private JPanel headerSpacer;
	private JButton languageButton;
	private JButton helpButton;

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
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
	graphScrollPane = new JScrollPane();
	northPanel = new JPanel();
	headerLable = new JLabel();
	logOutButton = new JButton();
	headerSpacer = new JPanel(null);
	languageButton = new JButton();
	helpButton = new JButton();

	//======== this ========
	setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
	javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax
	. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
	.awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
	. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans.
	PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .
	equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
				optionsPanel.add(optionsButton);
			}
			tablePanel.add(optionsPanel, BorderLayout.NORTH);
		}
		tabbedPane.addTab("Table", tablePanel);

		//======== graphPanel ========
		{
			graphPanel.setLayout(new BorderLayout());
			graphPanel.add(graphScrollPane, BorderLayout.CENTER);
		}
		tabbedPane.addTab("Graph", graphPanel);
	}
	add(tabbedPane, BorderLayout.CENTER);

	//======== northPanel ========
	{
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

		//---- headerLable ----
		headerLable.setFont(headerLable.getFont().deriveFont(headerLable.getFont().getSize() + 11f));
		headerLable.setText("Movies ");
		northPanel.add(headerLable);
		northPanel.add(logOutButton);
		northPanel.add(headerSpacer);
		northPanel.add(languageButton);
		northPanel.add(helpButton);
	}
	add(northPanel, BorderLayout.NORTH);

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
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
	private JScrollPane graphScrollPane;
	private JPanel northPanel;
	private JLabel headerLable;
	private JButton logOutButton;
	private JPanel headerSpacer;
	private JButton languageButton;
	private JButton helpButton;

	private void initComponents() {
		// Component initialization - DO NOT MODIFY
		// End of component initialization
	}
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
	graphScrollPane = new JScrollPane();
	northPanel = new JPanel();
	headerLable = new JLabel();
	logOutButton = new JButton();
	headerSpacer = new JPanel(null);
	scrollPane1 = new JScrollPane();
	list1 = new JList();
	languageButton = new JButton();
	helpButton = new JButton();

	//======== this ========
	setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
	border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
	, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
	.BOLD ,12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
	new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
	.equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
				optionsPanel.add(optionsButton);
			}
			tablePanel.add(optionsPanel, BorderLayout.NORTH);
		}
		tabbedPane.addTab("Table", tablePanel);

		//======== graphPanel ========
		{
			graphPanel.setLayout(new BorderLayout());
			graphPanel.add(graphScrollPane, BorderLayout.CENTER);
		}
		tabbedPane.addTab("Graph", graphPanel);
	}
	add(tabbedPane, BorderLayout.CENTER);

	//======== northPanel ========
	{
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

		//---- headerLable ----
		headerLable.setFont(headerLable.getFont().deriveFont(headerLable.getFont().getSize() + 11f));
		headerLable.setText("Movies ");
		northPanel.add(headerLable);
		northPanel.add(logOutButton);
		northPanel.add(headerSpacer);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(list1);
		}
		northPanel.add(scrollPane1);
		northPanel.add(languageButton);
		northPanel.add(helpButton);
	}
	add(northPanel, BorderLayout.NORTH);

	// Variables declaration - DO NOT MODIFY
	// End of variables declaration
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
	private JScrollPane graphScrollPane;
	private JPanel northPanel;
	private JLabel headerLable;
	private JButton logOutButton;
	private JPanel headerSpacer;
	private JScrollPane scrollPane1;
	private JList list1;
	private JButton languageButton;
	private JButton helpButton;
}
