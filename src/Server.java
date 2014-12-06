import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class Server {

	public static ServerSocket serverSocket = null;
	public static Socket socket = null;
	public static DataInputStream dataInputStream = null;
	public static DataOutputStream dataOutputStream = null;
	public JButton wakeUp;
	private String dataIn;
	private String dataOut;
	public boolean isConnected = false;
	
	private JButton connectToRobot;
	private JLabel connectionStatus;
	private JButton btnSendMsg;
	private int port;
	private int connectionPort= 10006;
	private JTextField sendText;
	public boolean connBool = false;
	private JButton clearLog;

	
 public Server() throws IOException 
 {    
	JFrame okno = Gui2.frame;
 	wakeUp = Gui2.wakeUp;
 	connectToRobot= Gui2.connectToRobot;
	connectionStatus = Gui2.connectionStatus;
	btnSendMsg = Gui2.btnSendText;
	sendText = Gui2.sendText;
	clearLog = Gui2.clearLogs;
	
	getTextToTable.start();
	connThread.start(); 
	connectToRobot.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isConnected==true){
				try {
					isConnected = false;
					serverSocket.close();
					dataInputStream.close();
					dataOutputStream.close();
					socket.close();
					connectionAlert(isConnected);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				
					try {
						connecion();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				  
			}
		}});
	wakeUp.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isConnected == true){
				try {
					dataOutputStream.writeUTF("wake up");
					
					System.out.println("Siema");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else{
				System.out.println("Ÿle");			}
			
		}
	});
	
	btnSendMsg.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isConnected == true){
				try {
					String rawText = sendText.getText();
					dataOutputStream.writeUTF(rawText);
					
					System.out.println("Siema");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else{
				System.out.println("Ÿle");			}
			
		}
	});
	
	
	
	
	
				 	 	
	okno.addWindowListener(new WindowAdapter() {	
		@Override
		public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
			if(isConnected == true){
				try {				
					if(Server.socket.isConnected()){
						dataInputStream.close();
						dataOutputStream.close();
						socket.close();			
					}
				} 
				catch (IOException e1) {
						e1.printStackTrace();
				}			
			}
		}	
	});
 }
 

 public void connectionAlert(boolean isConnected)
 {
	 if(isConnected == false){		
		 connectToRobot.setEnabled(true);
		 connectionStatus.setForeground(Color.RED);
		 connectionStatus.setText("Not Connected");
		 connectToRobot.setText("Connect to Robot");		 		  
	 }else{
		 connectToRobot.setEnabled(true);
		 connectionStatus.setForeground(new Color(60, 179, 113));
		 connectionStatus.setText("Connected on: "  + port);
		 connectToRobot.setText("Abort Connection");
	 } 
 }
 
 //--------------------- Connection --------------------------------------
 public void connecion() throws IOException {
	 connectionStatus.setForeground(Color.BLUE);
	 connectionStatus.setText("Connecting...");
	 connectToRobot.setEnabled(false);
	 serverSocket = new ServerSocket(connectionPort);
	 port = serverSocket.getLocalPort();
	 connBool = true ;
	

	 //onnThread.start();  
 }
 
 //--------------------- THREAD ------------------------------------------
 Thread connThread = new Thread(new Runnable()  
 {  
    public void run()  
    {  
    	while(true){
	    	 while(connBool == true){
		         try  
		         { 
		        	serverSocket.setSoTimeout(3000);
		            socket = serverSocket.accept();
		            
		            dataInputStream = new DataInputStream(socket.getInputStream());
					dataOutputStream = new DataOutputStream(socket.getOutputStream());
		            isConnected = true; 
		            connectionAlert(isConnected);	           
		            connBool = false;
		         }  
		         catch(IOException ie)  
		         {
		        	connBool = false;
		        	isConnected = false;
		        		try {
		        			serverSocket.close();
		        		}catch (IOException e){
		        			e.printStackTrace();
		        		}	
		        	connectionAlert(isConnected);
		         		} 
	    	 }
    	}
    }
}      
);
 
 Thread getTextToTable = new Thread(new Runnable()  
 {  
    public void run()  
    {  
    	while(true){	
	    	while(isConnected == true){
		    	try {
					dataIn = dataInputStream.readUTF();
					if (!dataIn.isEmpty()){    	
				    	Gui2.addtotable(dataIn, dataIn);  
				    }
					
				} 
		    	catch (IOException e) {
		    		isConnected = false;
		    		connectionAlert(isConnected);
		    		break;
					//getTextToTable.interrupt();
				}	
			}	
    	}	
    }
 }  
 );
 //----------------------------------------------------------------------
}

	       
						   	