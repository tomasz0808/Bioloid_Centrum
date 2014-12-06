import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Server {

	public static ServerSocket serverSocket = null;
	public static Socket socket = null;
	public static DataInputStream dataInputStream = null;
	public static DataOutputStream dataOutputStream = null;
	public JButton wakeUp;
	public String dataIn;
	public String dataOut;
	public boolean isConnected = false;
	
	private JButton connectToRobot;
	private JLabel connectionStatus;
	private int port;
	private int connectionPort= 10006;
	
 public Server() throws IOException 
 {    
	JFrame okno = Gui2.frame;
 	wakeUp = Gui2.wakeUp;
 	connectToRobot= Gui2.connectToRobot;
	connectionStatus = Gui2.connectionStatus;
	connectToRobot.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isConnected==true){
				try {
					dataInputStream.close();
					dataOutputStream.close();
					socket.close();
					t.interrupt();
					isConnected = false;
					connectionAlert(isConnected);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				connecion();
			}
		}});
	
				 	 	
	okno.addWindowListener(new WindowAdapter() {	
		@Override
		public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
			if(isConnected == true){
				try {				
					if(Server.socket.isConnected()){
//						dataInputStream.close();
						//dataOutputStream.close();
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
		 connectionStatus.setForeground(Color.RED);
		 connectionStatus.setText("Not Connected");
		 connectToRobot.setText("Connect to Robot");		 		  
	 }else{
		 connectionStatus.setForeground(new Color(60, 179, 113));
		 connectionStatus.setText("Connected on: "  + port);
		 connectToRobot.setText("Abort Connection");
	 } 
 }
 
 //--------------------- Connection --------------------------------------
 public void connecion() {
	 connectionStatus.setForeground(Color.BLUE);
	 connectionStatus.setText("Connecting...");
	 connectToRobot.setEnabled(false);
	 t.start();  
 }
 
 //--------------------- THREAD ------------------------------------------
 Thread t = new Thread(new Runnable()  
 {  
	 
 
     public void run()  
     {  
         try  
         {  
        	serverSocket = new ServerSocket(connectionPort);
          	port = serverSocket.getLocalPort();
            socket = serverSocket.accept(); // Tu czeka
            dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
            isConnected = true; 
            connectionAlert(isConnected);
            connectToRobot.setEnabled(true);
         }  
         catch(IOException ie)  
         {  
             //  
         } 
     } 
     
 });
 
 Thread tt = new Thread(new Runnable()  
 {  
	 
 
     public void run()  
     {  
    	
        socket.isConnected();
     } 
     
 });
 //----------------------------------------------------------------------
}

// dataIn = dataInputStream.readUTF();
//										    if (!dataIn.isEmpty()){    	
//										    	Gui2.addtotable(dataIn, dataIn);  
//										    }		       
						   	