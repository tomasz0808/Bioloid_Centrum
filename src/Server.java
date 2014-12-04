import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public ServerSocket serverSocket;
	public String datain;

 public Server()
 {
	  
  serverSocket = null;
  Socket socket = null;
  DataInputStream dataInputStream = null;
  DataOutputStream dataOutputStream = null;
  int port = 8865;
    
 
  try {
   serverSocket = new ServerSocket(port);
   System.out.println("Socket Server: " + serverSocket.getLocalPort());
   
  } catch (IOException e) {
   
   e.printStackTrace();
  }

 
  while(!serverSocket.isClosed()){
	 
   try {
    socket = serverSocket.accept();
    dataInputStream = new DataInputStream(socket.getInputStream());
    dataOutputStream = new DataOutputStream(socket.getOutputStream());
    datain = dataInputStream.readUTF();
    if (!datain.isEmpty())
    { 	
    	Gui.addtotable(datain, datain); 
    }
        
    System.out.println("ip: " + socket.getInetAddress());
    dataOutputStream.writeUTF("Odebra³em");
   } catch (IOException e) {
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