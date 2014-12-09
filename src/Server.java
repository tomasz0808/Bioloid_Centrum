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

	static ServerSocket serverSocket = null;
	static Socket socket = null;
	static DataInputStream dataInputStream = null;
	static DataOutputStream dataOutputStream = null;
	private JButton wakeUp;
	private String dataIn;
	private boolean isConnected = false;
	private int isWake = 0;

	private JButton connectToRobot;
	private JLabel connectionStatus;
	private JButton btnSendMsg;
	private int port;
	private int connectionPort = 10006;
	private JTextField sendText;
	private boolean connBool = false;
	private JLabel robotStatus;

	public Server() throws IOException {
		JFrame okno = Gui2.frame;
		wakeUp = Gui2.wakeUp;
		connectToRobot = Gui2.connectToRobot;
		connectionStatus = Gui2.connectionStatus;
		btnSendMsg = Gui2.btnSendText;
		sendText = Gui2.sendText;
		robotStatus = Gui2.robotStatus;
		btnSendMsg.setEnabled(false);
		wakeUp.setEnabled(false);

		getTextToTable.start();
		connThread.start();
		
		
//---------------- Connect to robot Action -----------------------		
		connectToRobot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isConnected == true) {
					try {
						isWake = 0;
						isConnected = false;
						serverSocket.close();
						dataInputStream.close();
						dataOutputStream.close();
						socket.close();
						connectionAlert(isConnected, isWake);
					} 
					catch (IOException e1) {
						Gui2.addtotable("Error");
					}
				} 
				else {
					try {
						connecion();
					} catch (IOException e1) {
						Gui2.addtotable("Error");
					}

				}
			}
		});
//------------------ Wake up Action -----------------------------------
		wakeUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isConnected == true) {
					try {
						dataOutputStream.writeUTF("wake up");
						Gui2.addtotable("Robot is waking up...");
					} 
					catch (IOException e1) {
						Gui2.addtotable("Error");
					}
				}
			}
		});
//------------------ Send Message Action ------------------------------
		btnSendMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isConnected == true) {
					try {
						String rawText = sendText.getText();
						dataOutputStream.writeUTF(rawText);
						Gui2.addtotable("Message: " + rawText + " send");
					} catch (IOException e1) {
						Gui2.addtotable("Error");
					}
				}
			}
		});
//------------------ Exit Action -------------------------------------
		okno.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if (isConnected == true) {
					try {
						if (Server.socket.isConnected()) {
							dataInputStream.close();
							dataOutputStream.close();
							socket.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

//------------------ METHODS ---------------------------------------------------------
//------------------ Status changer --------------------------------------------------
	public void connectionAlert(boolean isConnected, int isWake) {
		if (isConnected == false) {
			connectToRobot.setEnabled(true);
			connectionStatus.setForeground(Color.RED);
			connectionStatus.setText("Not Connected");
			connectToRobot.setText("Connect to Robot");
		} else {
			connectToRobot.setEnabled(true);
			connectionStatus.setForeground(new Color(60, 179, 113));
			connectionStatus.setText("Connected");
			connectToRobot.setText("Abort Connection");
		}
		if (isWake == 1) {
			robotStatus.setForeground(new Color(60, 179, 113));
			robotStatus.setText("Active");
			btnSendMsg.setEnabled(false);
			wakeUp.setEnabled(false);

		} else if (isWake == 2) {
			robotStatus.setForeground(Color.BLACK);
			robotStatus.setText("Sleeping");
			btnSendMsg.setEnabled(true);
			wakeUp.setEnabled(true);

		} else if (isWake == 0) {
			robotStatus.setForeground(Color.RED);
			robotStatus.setText("Not started");
			btnSendMsg.setEnabled(false);
			wakeUp.setEnabled(false);

		}

	}

// --------------------- Connection -------------------------------------------------
	public void connecion() throws IOException {
		connectionStatus.setForeground(Color.BLUE);
		connectionStatus.setText("Connecting...");
		connectToRobot.setEnabled(false);
		serverSocket = new ServerSocket(connectionPort);
		port = serverSocket.getLocalPort();
		connBool = true;
	}

	// --------------------- THREAD ------------------------------------------
	Thread connThread = new Thread(new Runnable() {
		public void run() {
			while (true) {
				while (connBool == true) {
					try {
						serverSocket.setSoTimeout(10000);
						socket = serverSocket.accept();
						dataInputStream = new DataInputStream(socket.getInputStream());
						dataOutputStream = new DataOutputStream(socket.getOutputStream());
						isConnected = true;
						Gui2.addtotable("Connected on port: " +port);
						isWake = 2;
						connectionAlert(isConnected, isWake);
						connBool = false;
						
					} 
					catch (IOException ie) {
						connBool = false;
						isConnected = false;
						try {
							serverSocket.close();
						} catch (IOException e) {
							Gui2.addtotable("Error");
						}
						connectionAlert(isConnected, isWake);
					}
				}
			}
		}
	});

	Thread getTextToTable = new Thread(new Runnable() {
		public void run() {
			while (true) {
				while (isConnected == true) {
					try {
						dataIn = dataInputStream.readUTF();
						if (!dataIn.isEmpty()) {
							if (dataIn.equalsIgnoreCase("sleep")) {
								isWake = 2;
							} else if (dataIn.equalsIgnoreCase("wake")) {
								isWake = 1;
							} else
								Gui2.addtotable(dataIn);
						}
						connectionAlert(isConnected, isWake);
					} catch (IOException e) {
						try {
							serverSocket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Gui2.addtotable("Connecion Lost");
						isConnected = false;
						isWake = 0;
						connectionAlert(isConnected, isWake);
						break;
					}
				}
			}
		}
	});
// ----------------------------------------------------------------------
}
