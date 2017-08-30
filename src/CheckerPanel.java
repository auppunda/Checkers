import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class CheckerPanel extends JPanel {
	Board board;
	public CheckerPanel(int width, int height) { 
		this.setSize(width, height);
		board = new Board(this.getWidth(), this.getHeight());
		RunAI ai = new RunAI(this);
		ai.start();
	}
	
	public void paintComponent(Graphics g) { 
		//RobotPlayer.findBestSpace(this, 1);
		if(board.outcome() == 2)
			board.drawComponent(g);
		else { 
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.BLACK);
			if(board.outcome() == 0)
				g.drawString("YOU WIN", this.getWidth()/2, this.getHeight()/2);
			else 
				g.drawString("AI WINS", this.getWidth()/2, this.getHeight()/2);
		}
	}

	public class RunAI extends Thread { 
		CheckerPanel panel;
		public RunAI(CheckerPanel pan) { 
			panel = pan;
		}
		public void run() { 
			while(true) { 
				RobotPlayer.findBestSpace(panel, 1);
			}
		}
	}

}
