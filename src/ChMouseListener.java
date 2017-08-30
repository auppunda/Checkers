import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.AllPermission;


public class ChMouseListener implements MouseListener {
	CheckerPanel panel;
	public ChMouseListener(CheckerPanel panel) { 
		this.panel = panel;
		panel.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int i = 0;
		int u = 0;
		if(arg0.getX() < panel.getWidth()/8) { 
			i = 0;
		}
		if(arg0.getX() >=  panel.getWidth()/8 && arg0.getX() < 2* panel.getWidth()/8 ) { 
			i = 1;
		}
		if(arg0.getX() >=  2*panel.getWidth()/8 && arg0.getX() < 3* panel.getWidth()/8 ) { 
			i = 2;
		}
		if(arg0.getX() >=  3*panel.getWidth()/8 && arg0.getX() < 4* panel.getWidth()/8 ) { 
			i = 3;
		}
		if(arg0.getX() >=  4*panel.getWidth()/8 && arg0.getX() < 5* panel.getWidth()/8 ) { 
			i = 4;
		}
		if(arg0.getX() >=  5*panel.getWidth()/8 && arg0.getX() < 6* panel.getWidth()/8 ) { 
			i = 5;
		}
		if(arg0.getX() >=  6*panel.getWidth()/8 && arg0.getX() < 7* panel.getWidth()/8 ) { 
			i = 6;
		}
		if(arg0.getX() >=  7*panel.getWidth()/8 && arg0.getX() < 8* panel.getWidth()/8 ) { 
			i = 7;
		}


		if(arg0.getY() < panel.getHeight()/8) { 
			u = 0;
		}
		if(arg0.getY() >=  panel.getHeight()/8 && arg0.getY() < 2* panel.getHeight()/8 ) { 
			u = 1;
		}
		if(arg0.getY() >=  2*panel.getHeight()/8 && arg0.getY() < 3* panel.getHeight()/8 ) { 
			u = 2;
		}
		if(arg0.getY() >=  3*panel.getHeight()/8 && arg0.getY() < 4* panel.getHeight()/8 ) { 
			u = 3;
		}
		if(arg0.getY() >=  4*panel.getHeight()/8 && arg0.getY() < 5* panel.getHeight()/8 ) { 
			u = 4;
		}
		if(arg0.getY() >=  5*panel.getHeight()/8 && arg0.getY() < 6* panel.getHeight()/8 ) { 
			u = 5;
		}
		if(arg0.getY() >=  6*panel.getHeight()/8 && arg0.getY() < 7* panel.getHeight()/8 ) { 
			u = 6;
		}
		if(arg0.getY() >=  7*panel.getHeight()/8 && arg0.getY() < 8*panel.getHeight()/8 ) { 
			u = 7;
		}
		int space = u*8 + (i+1);
		for(Checker c : panel.board.checkers) { 
			if(c.isClicked) { 
				int[] possible = c.possibleMoves();

				for(Checker f : panel.board.checkers)  {  
					for(int s = 0; s < possible.length; s++) { 
						if(possible[s] == f.space && f.color != c.color && f.draw) { 
							possible[s] = possible[s] + (possible[s] - c.space);
							if(possible[s] > 64 || possible[s] < 0) { 
								possible[s] = -1;
							}
							if(possible[s] == space) { 
								//System.out.println(c.row + " " + c.column);
								//System.out.println("KILL");
								if(RobotPlayer.allowedSpace(space)) { 
									System.out.println(possible[s] + " " + space);
									f.draw = false;
									f.space = 0;
									f.row = -1;
									f.column = -1;
									c.space = space;
									c.column = space/8;
									c.row = space % 8 - 1; 
									if(space % 8 == 0) { 
										c.row =7;
										c.column = space / 8 - 1;
									}
									c.setPosition((i) * panel.getWidth()/8 + 9, (u) * panel.getHeight()/8 + 9);
									c.isClicked = false;
									panel.repaint();
//									if(RobotPlayer.canKill(c, panel)) { 
//										int spacez = RobotPlayer.canKillAnother(c, panel);
//										if(RobotPlayer.allowedSpace(spacez)) { 
//											//System.out.println(possible[s] + " " + space);
//											c.space = spacez;
//											c.column = spacez/8;
//											c.row = spacez % 8 - 1; 
//											if(spacez % 8 == 0) { 
//												c.row =7;
//												c.column = spacez / 8 - 1;
//											}
//											c.setPosition((c.row) * panel.getWidth()/8 + 9, (c.column) * panel.getHeight()/8 + 9);
//										}
//									}
									panel.board.turn = 1;
									return;
								}
								
								//panel.board.checkers.remove(f);
							}
						}

					}
				}
				for(int s = 0; s < possible.length; s++) { 
					if(possible[s] == space) { 
						if(!RobotPlayer.isCheckerInSpace(space, panel)) { 
							if(RobotPlayer.allowedSpace(space)) { 
								c.setPosition((i) * panel.getWidth()/8 + 9, (u) * panel.getHeight()/8 + 9);
								c.space= space;
								c.row = i;
								c.column = u;
								panel.board.turn = 1;
								c.isClicked = false;
							}
						}
					}
				}
			}
			panel.repaint();
		}
		for(Checker c : panel.board.checkers) { 
			if(panel.board.turn == c.color)
				c.isClicked = c.isClickd(arg0.getX(), arg0.getY());
			panel.repaint();

		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
