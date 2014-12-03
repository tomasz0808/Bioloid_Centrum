import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextField;


public class Server {


	public String datain;
	public String dataout;
	


 public Server()
 {
	  
  ServerSocket serverSocket = null;
  Socket socket = null;
  DataInputStream dataInputStream = null;
  DataOutputStream dataOutputStream = null;
    
  try {
   serverSocket = new ServerSocket(8894);
   System.out.println(serverSocket.getLocalPort());
   
  } catch (IOException e) {
   
   e.printStackTrace();
  }
  
  while(true){
   try {
    socket = serverSocket.accept();
    dataInputStream = new DataInputStream(socket.getInputStream());
   // datain = dataInputStream.toString();
    datain = dataInputStream.readUTF();
    dataOutputStream = new DataOutputStream(socket.getOutputStream());
    if (!datain.isEmpty())
    {
    	
    	Gui.addtotable(datain, datain);
  
    }
    // Na pozniej
    dataout = dataOutputStream.toString();
    //
    
    System.out.println("ip: " + socket.getInetAddress());
    //System.out.println("message: " + dataInputStream.readUTF());
    dataOutputStream.writeUTF("Hello!");
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   finally{
    if( socket!= null){
     try {
      socket.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
    
    if( dataInputStream!= null){
     try {
      dataInputStream.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
    
    if( dataOutputStream!= null){
     try {
      dataOutputStream.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
   }
  }
 }
}