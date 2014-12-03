
public class Main {

	public static void main(String[] args) {
		
		new Gui();
		Thread waitThread = new Thread(new WaitThread());
		waitThread.start();
	}
	
}
