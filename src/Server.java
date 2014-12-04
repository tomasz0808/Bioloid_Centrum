import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;



public class Server {

	
	public static ServerSocket serverSocket;
	public String datain;
	public JButton butt;
	public String dataout;
	public  DataOutputStream dataOutputStream = null;
	
//public static final void sendMessage(String coSend) throws IOException{
//		 
//	dataOutputStream.writeUTF(coSend);
//} 

 public Server()
 {
	  
  serverSocket = null;
  Socket socket = null;
  DataInputStream dataInputStream = null;

 
  //  
  butt = Gui.przycisk1;
 
  
 
  try {
   serverSocket = new ServerSocket(8895);
   System.out.println(serverSocket.getLocalPort());
   
  } catch (IOException e) {
   
   e.printStackTrace();
  }

 
  while(!serverSocket.isClosed()){
   try {
    socket = serverSocket.accept();
    dataInputStream = new DataInputStream(socket.getInputStream());
    datain = dataInputStream.readUTF();
    dataOutputStream = new DataOutputStream(socket.getOutputStream());
    

    // Na pozniej
    //dataout = dataOutputStream.toString();
    //
//    dataOutputStream.writeUTF("Huj");
    System.out.println("ip: " + socket.getInetAddress());
    //System.out.println("message: " + dataInputStream.readUTF());
   
   
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   
   if(socket.isConnected()){
	   System.out.println("Connecnted");
   butt.addActionListener(new Butt());
   if (!datain.isEmpty())
   {
   	
   	Gui.addtotable(datain, datain);
 
   }
   }
   
   
//   finally{
//    if( socket!= null){
//     try {
//      socket.close();
//     } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//     }
//    }
//    
//    if( dataInputStream!= null){
//     try {
//      dataInputStream.close();
//     } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//     }
//    }
//    
//    if( dataOutputStream!= null){
//     try {
//      dataOutputStream.close();
//     } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//     }
//    }
//   }
  }  
 }
 class Butt implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		 try {
				dataOutputStream.writeUTF("hujaczek");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	}

 }
}