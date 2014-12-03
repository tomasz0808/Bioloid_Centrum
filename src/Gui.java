import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.bluetooth.*;


public class Gui {
	
	private JFrame okno;
	private JButton przycisk1;
	private JButton przycisk2;
	private JButton przycisk3;
	private JButton przycisk4;
	private JButton przycisk5;
	private JMenuItem menuItem;
	private JMenuBar menu;
	private JLabel label;
	private JMenu menuLista;
	private JTextField tabela;
	
	
	public Gui() {
		frameinit();
	}
	
	public void frameinit()
	{
		okno = new JFrame("Bioloid Center");
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Tabela
		
		tabela = new JTextField();
		tabela.setBounds(50, 400, 1000, 300);
		tabela.setVisible(true);
		tabela.setEditable(false);
		tabela.setText("siema siema siema siema siema");
		
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
		label.add(tabela);
		
		
		
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
