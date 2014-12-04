import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;

	public class Client {
	
	public Socket socket2 = null;	   
	public OutputStream outputStream;
	public JButton button1;
	public Boolean press = false;
	public int port = 8845;
	
	public Client () throws IOException
	{
		button1 = Gui.przycisk1;
		DataOutputStream dataOutputStream = null;
		//DataInputStream dataInputStream = null;
		String dataOut = "siema";
		
//		while(press==true){
		
		socket2 = new Socket("192.168.0.101", port );
		System.out.println("Socket Client £¹czenie na: " + port);
		dataOutputStream = new DataOutputStream(socket2.getOutputStream());
		//dataInputStream = new DataInputStream(socket2.getInputStream());
		dataOutputStream.writeUTF(dataOut);
		
//		}
			
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				press = true;
				
			}
		});	
	  
	}
	}
	 
//	 finally{
//	  if (socket != null){
//	   try {
//	    socket.close();
//	   } catch (IOException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	   }
//	  }
//
//	  if (dataOutputStream != null){
//	   try {
//	    dataOutputStream.close();
//	   } catch (IOException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	   }
//	  }
//
//	  if (dataInputStream != null){
//	   try {
//	    dataInputStream.close();
//	   } catch (IOException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	   }
//	  }
//	 }
//	}};
	