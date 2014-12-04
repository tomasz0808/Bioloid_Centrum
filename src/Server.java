import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;



public class Server {

	
	public static ServerSocket serverSocket = null;
	public static Socket socket = null;
	public static DataInputStream dataInputStream = null;
	public static OutputStream dataOutputStream = null;
	public JButton button;
	public String dataIn;
	public String dataOut;
	private Thread socketListen;
	public boolean isConnected;
	


 public Server() throws IOException 
 {    
 
	 button = Gui.przycisk1;
	 socketListen = new Thread(new SocketConn());
	 JFrame okno = Gui.okno;

	
	 serverSocket = new ServerSocket(10006);
	 System.out.println(serverSocket.getLocalPort());
//	 socketListen.start();
  
	 while(!serverSocket.isClosed()){
	    socket = serverSocket.accept();
	    if(socket.isConnected())
	    isConnected = true;
	    
	    System.out.println("Połączenie: "+isConnected);	    
	    if(isConnected){
		    dataInputStream = new DataInputStream(socket.getInputStream());
		    dataOutputStream = socket.getOutputStream();
		    dataIn = dataInputStream.readUTF();
		    
		    if (!dataIn.isEmpty()){    	
		    	Gui.addtotable(dataIn, dataIn);  
		    }		    
		    button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(socket.isConnected() ){				
						try {
							dataOutputStream.write(1);;
						} catch (IOException e1) {
							e1.printStackTrace();
						}							
					} else
						System.out.println("You are not connected");				
				}
			});		
	   	}
	  }
	 button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isConnected==true){
				
			}
			
		}
	});
	 okno.addWindowListener(new WindowAdapter() {
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			super.windowClosing(e);
				try {				
					if(Server.socket.isConnected()){
						dataInputStream.close();
						dataOutputStream.close();
						socket.close();				
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
				}			
			}
//		
		});
 }
 public static synchronized Socket getSocket()
 {
	 return socket;
 }
 public static synchronized DataInputStream getDataInputStream()
 {
	 return dataInputStream;
 }
 public static synchronized OutputStream getDataOutputStream()
 {
	 return dataOutputStream;
 }
 
 
class SocketConn extends Thread {
	
	
	 public void run() {
		 while(isConnected){
			 if (!dataIn.isEmpty()){    	
			    	Gui.addtotable(dataIn, dataIn);  
			    }		 	 
		 	}
	 	}
	}
}