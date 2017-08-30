import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Board {
	ArrayList<Checker> checkers;
	int width; 
	int height;
	int turn = 0;
	public Board(int width, int height) { 
		checkers = new ArrayList<Checker>();
		this.width = width;
		this.height = height;
		for(int i = 0 ; i < 8; i++) { 
			for(int u = 0; u < 3; u++) { 
				if((u + i) % 2 != 0 ) { 
					checkers.add(new Checker(1, (width/8) - 12, (i) * width/8 + 6, (u) * height/8 + 6, u*8 + (i+1), i, u));
				}
			}
 		}
		for(int i = 0 ; i < 8; i++) { 
			for(int u = 5; u < 8; u++) { 
				if((u + i) % 2 != 0 ) { 
					checkers.add(new Checker(0, (width/8) - 12, (i) * width/8 + 6, (u) * height/8+6,  u*8 + (i+1), i, u));
				}
			}
 		}
	}
	
	public void drawComponent(Graphics g) { 
		for(int i = 0; i < 8 ; i++) { 
			for(int u = 0; u < 8; u++) { 
				if((u + i) % 2 == 0 ) { 
					g.setColor(Color.YELLOW);
				}
				else { 
					g.setColor(Color.BLACK);
				}
				g.fillRect((i)*width/8, (u)*height/8, width/8, height/8);
			}
		}
		for(Checker c : checkers)  {
			c.draw(g);
		}
	}
	public int outcome() {
		boolean c1wins = true;
		boolean c2wins = true;
		for(Checker c: checkers) { 
			if(c.color == 1) { 
				if(c.draw)
					c1wins = false;
			}
			if(c.color == 1) { 
				if(c.draw)
					c2wins = false;
			}
		}
		if(c1wins)
			return 0;
		if(c2wins)
			return 1;
		else return 2;
	}
}
