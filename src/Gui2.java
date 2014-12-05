import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;



import com.jgoodies.forms.factories.DefaultComponentFactory;


public class Gui2 {

	public static JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTable table;
	private JTextField sendText;	
	public static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	public static int tableId = 1;
	private static  DefaultTableModel model = new DefaultTableModel(
			new Object[][] {		},
			new String[] {"Id", "Time", "Patient status", "Assistance"});
	public static JButton wakeUp = new JButton("Wake up Robot");
	
	
	
	public static void addtotable(String status, String nurse){
		Date dateobj = new Date();
		model.insertRow(0, new Object [] {tableId, df.format(dateobj),status, nurse});
		tableId++;		
	}
	
	public Gui2() {
		frameinit();
	}
	
	public void frameinit(){
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelIp = new JPanel();
		panelIp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelIp.setBounds(20, 30, 480, 130);
		contentPane.add(panelIp);
		panelIp.setLayout(null);
		
		JLabel lblConnectionStatus = new JLabel("Connection status:");
		lblConnectionStatus.setBounds(217, 77, 106, 16);
		panelIp.add(lblConnectionStatus);
		
		JLabel connectionStatus = new JLabel("Connected");
		connectionStatus.setForeground(new Color(60, 179, 113));
		connectionStatus.setBounds(331, 71, 89, 28);
		panelIp.add(connectionStatus);
		
		JButton connectToRobot = new JButton("Connect to robot");
		connectToRobot.setBounds(217, 13, 143, 44);
		panelIp.add(connectToRobot);
		
		JLabel myIp = new JLabel("192.168.1.1.");
		myIp.setFont(myIp.getFont().deriveFont(myIp.getFont().getStyle() | Font.BOLD));
		myIp.setBounds(64, 76, 107, 19);
		panelIp.add(myIp);
		
		JLabel lblIp = new JLabel("<html><p>Exchange your ip addres with<br>\r\nrobot to communicate</P>\r\n<br><br>\r\n\r\n<p>Your ip:</p>\r\n</html>");
		lblIp.setFont(UIManager.getFont("CheckBox.font"));
		lblIp.setVerticalAlignment(SwingConstants.TOP);
		lblIp.setBounds(12, 13, 177, 79);
		panelIp.add(lblIp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(20, 340, 755, 250);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setFont(UIManager.getFont("Table.font"));
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(2).setPreferredWidth(295);
		scrollPane.setViewportView(table);
		
		JButton clearLogs = new JButton("Clear logs");
		clearLogs.setBounds(20, 300, 100, 25);
		contentPane.add(clearLogs);
		
		JPanel panelWakeUp = new JPanel();
		panelWakeUp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelWakeUp.setBounds(20, 180, 250, 100);
		contentPane.add(panelWakeUp);
		
		
		wakeUp.setBounds(15, 15, 140, 45);
		
		JLabel lblRobotStatus = new JLabel("Status:");
		lblRobotStatus.setBounds(15, 70, 50, 15);
		
		JLabel robotStatus = new JLabel("Sleeping");
		robotStatus.setBounds(65, 70, 60, 15);
		panelWakeUp.setLayout(null);
		panelWakeUp.add(wakeUp);
		panelWakeUp.add(lblRobotStatus);
		panelWakeUp.add(robotStatus);
		
		JPanel panelSendText = new JPanel();
		panelSendText.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSendText.setBounds(295, 180, 480, 100);
		contentPane.add(panelSendText);
		
		JButton btnSendText = new JButton("Send Message");
		btnSendText.setBounds(15, 15, 140, 45);
		
		sendText = new JTextField();
		sendText.setHorizontalAlignment(SwingConstants.CENTER);
		sendText.setText("Write your text here...");
		sendText.setBounds(175, 15, 293, 70);
		sendText.setColumns(10);
		panelSendText.setLayout(null);
		panelSendText.add(btnSendText);
		panelSendText.add(sendText);
		
		JLabel logo = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		logo.setIcon(new ImageIcon(Gui2.class.getResource("/cross/cross.png")));
		logo.setBounds(550, 30, 159, 130);
		contentPane.add(logo);
		frame.setVisible(true);
	}
}
