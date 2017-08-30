import javax.swing.JFrame;


public class CheckerWindow extends JFrame{

	public CheckerWindow() { 
		CheckerPanel panel = new CheckerPanel(800, 800);
		new ChMouseListener(panel);
		this.setContentPane(panel);
	}
}
