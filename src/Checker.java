import java.awt.Color;
import java.awt.Graphics;


public class Checker {
	int color;
	int radius;
	int x;
	int y;
	boolean isClicked = false;
	boolean king = false;
	int space = 0;
	int row = 0;
	int column = 0;
	boolean draw = true;

	public Checker(int colorC, int radiusC, int xC, int yC, int space, int row, int column) { 
		color = colorC;
		radius = radiusC;
		x = xC;
		y = yC;
		this.space = space;
		this.row =row;
		this.column =column;
	}

	public void draw(Graphics g) { 
		if(draw) { 
			if(isClicked)  { 
				g.setColor(Color.YELLOW);
				g.fillOval(x - 4,y - 4,radius+8, radius +8);
			}
			if(color == 0) { 
				g.setColor(Color.BLUE);
			}
			if(color == 1) { 
				g.setColor(Color.RED);
			}
			g.fillOval(x, y, radius, radius);
		}
	}

	public void setPosition(int x, int y) { 
		this.x = x;
		this.y = y;
	}
	public double getX() { 
		return x;
	}

	public double getY() { 
		return y;
	}
	public boolean isClickd(int x, int y) { 
		return ((y -this.y-radius/2) * (y - radius/2-this.y) + (x- radius/2-this.x) * (x-radius/2 - this.x) < (this.radius/2) *(this.radius/2)) && (color == 0);
	}

	public int[] possibleMoves() { 
		int[] possible;
		if(king) { 
			possible = new int[4];
			if(space <  8) { 
				possible = new int[2];
				possible[0] = space + 7;
				possible[1] = space + 9;
			}
			else if(space > 56) { 
				possible = new int[2];
				possible[0] = space - 7;
				possible[1] = space - 9;
			}
			else { 
				possible = new int[4];
				possible[0] = space - 7;
				possible[1] = space - 9;
				possible[2] = space + 7;
				possible[3] = space +9;
			}

		}
		else { 
			possible = new int[2];
			if(color == 0) { 
				if(this.space % 8 == 1) { 
					possible = new int[1];
					possible[0] = this.space - 7;
				}
				else if(this.space % 8 == 0) { 
					possible = new int[1];
					possible[0] = this.space - 9;
				}
				else { 
					possible = new int[2];
					possible[0] = space - 7;
					possible[1] = space - 9;
				}
			}
			if(color == 1) { 
				if(this.space % 8 == 1) { 
					possible = new int[1];
					possible[0] = this.space + 9;
				}
				else if(this.space % 8 == 0) { 
					possible = new int[1];
					possible[0] = this.space + 7;
				}
				else { 
					possible = new int[2];
					possible[0] = space + 7;
					possible[1] = space + 9;
				}
			}

		}
		return possible;
	}
}
