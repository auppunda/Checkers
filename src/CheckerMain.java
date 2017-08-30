import javax.swing.JFrame;


public class CheckerMain {
	public static void main(String[] args) { 
		CheckerWindow window = new CheckerWindow();
		window.setTitle("YOU ARE BLUE PLAYER");
		window.setSize(800,800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
