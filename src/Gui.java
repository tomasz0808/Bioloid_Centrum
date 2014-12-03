import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Gui {
	
	private JFrame okno;
	private JButton przycisk1;
	private JButton przycisk2;
	private JButton przycisk3;
	private JButton przycisk4 ;
	private JButton przycisk5;
	private JMenuItem menuItem;
	private JMenuBar menu;
	private JLabel label;
	private JMenu menuLista;
	public JPanel panelTable;
	public static DefaultTableModel model;
	public JTable table;
	public static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	public static int tableId = 1;
	//	private Calendar dateAndTime; 
	
		
	
	
	
	public Gui() {
		frameinit();
//		for(int i=0; i<50000000; i++)
//		{
//			addtotable("Gówno", "Tak");
//		}
//		
	}
	
	
	public static void addtotable(String status, String nurse)
	{
		Date dateobj = new Date();
		model.insertRow(0, new Object [] {tableId, df.format(dateobj),status, nurse});
		tableId++;
		
	}
	public void frameinit()
	{
		
		okno = new JFrame("Bioloid Center");
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println();
		
		
		
		table = new JTable();
		model = new DefaultTableModel();
		table.setModel(model);
		model.addColumn("Id");
		model.addColumn("Time");
		model.addColumn("Status");
		model.addColumn("Nurse");
		
		
		
		
	
		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(50, 400, 1100, 300);
		scrollPane.setPreferredSize(new Dimension(1100,250));
		panelTable = new JPanel();
		panelTable.setBounds(50, 400, 1100, 300);
//		panelTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelTable.add(scrollPane);
		panelTable.setVisible(true);
		
		
		
		
		//panel tabeli	
		
						
		//Menu
		menu = new JMenuBar();
		menuLista = new JMenu("siema");
		menu.add(menuLista);
		menu.setPreferredSize(new Dimension(200, 20));
		menuItem = new JMenuItem("siema",KeyEvent.VK_T);
		menuLista.add(menuItem);
		
		// label
		label = new JLabel();
		przycisk1 = new JButton("Start");
		przycisk1.setBounds(50, 50, 200, 100);
		przycisk2 = new JButton();
		przycisk2.setBounds(300, 50, 200, 100);
		przycisk3 = new JButton();
		przycisk3.setBounds(50, 200, 200, 100);
		przycisk4 = new JButton();
		przycisk4.setBounds(300, 200, 200, 100);
		przycisk5 = new JButton();
		przycisk5.setSize(100, 50);
		
		
		label.add(przycisk1);
		label.add(przycisk2);
		label.add(przycisk3);
		label.add(przycisk4);
		label.add(panelTable);
		
		
		
		
		// Frame
		okno.setSize(1200, 800);
		okno.setLocationRelativeTo(null);
		okno.setResizable(false);
		okno.setJMenuBar(menu);
		okno.getContentPane().add(label);
		//okno.getContentPane().add(label2, BorderLayout.CENTER);
		
		okno.setVisible(true);
		
	}
}
