import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class RobotPlayer {
	static int[] availiableSpaces = {2,4,6,8,9,11,13,15,18,20,22,24,25,27,29,31 , 34, 36,38,40, 41, 43, 45,47,50, 52,54,56,57,59,61,63 };
	public static void findBestSpace(CheckerPanel panel, int color) { 
		if(panel.board.turn == color) { 
			// first protect pieces
			try {
			    TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
			    //Handle exception
			}
						for(Checker  c : panel.board.checkers) { 
							if(c.color != color) { 
								int[] possible= c.possibleMoves(); 
								for(int i = 0 ; i < possible.length; i++) { 
									if(isCheckerInSpace(possible[i], color, panel)) { 
										int spaceDiff = possible[i] - c.space;
										int space = possible[i] + spaceDiff;
										if(!isCheckerInSpace(space, color, panel)) { 
											for(Checker f: panel.board.checkers) { 
												int[] possiblef = f.possibleMoves();
												for(int u = 0; u < possiblef.length; u++) { 
													if(possiblef[u]== space && f.color == color) { 
														f.space = space;
														f.column = space/8;
														f.row = space% 8 - 1;
														if(space % 8 == 0) { 
															f.row =7;
															f.column = space / 8 - 1;
														}
														panel.board.turn = 0;
														f.setPosition((f.row) * panel.getWidth()/8 + 9, (f.column) * panel.getHeight()/8 + 9);
														//System.out.println("WE HER");
														panel.repaint();
														return;
													}
												}
											}
										}
									}
								}
								
							}
						//keep back few intact 
						}
			for(Checker c: panel.board.checkers) {
				int space = 0;
				if(c.color == 1)
					space = canKillAnother(c, panel);
				if(space != 0) { 
					if(allowedSpace(space)) { 
						c.space = space;
						c.column = space/8;
						c.row = space % 8 - 1; 
						if(space % 8 == 0) { 
							c.row =7;
							c.column = space / 8 - 1;
						}
						c.setPosition((c.row) * panel.getWidth()/8 + 9, (c.column) * panel.getHeight()/8 + 9);
						panel.board.turn = 0;
						panel.repaint();
						return;
//						if(RobotPlayer.canKill(c, panel)) {
//							System.out.println("DOUBLE");
//							try {
//							    TimeUnit.MILLISECONDS.sleep(500);
//							} catch (InterruptedException e) {
//							    //Handle exception
//							}
//							int spacez = RobotPlayer.canKillAnother(c, panel);
//							if(RobotPlayer.allowedSpace(space)) { 
//								//System.out.println(possible[s] + " " + space);
//								c.space = spacez;
//								c.column = spacez/8;
//								c.row = spacez % 8 - 1; 
//								if(spacez % 8 == 0) { 
//									c.row =7;
//									c.column = spacez / 8 - 1;
//								}
//								c.setPosition((c.row) * panel.getWidth()/8 + 9, (c.column) * panel.getHeight()/8 + 9);
//								panel.repaint();
//							}
//						}
						
					}
				}
			}

			// make a random move 
			ArrayList<Checker> myCheckers = new ArrayList<Checker>();
			for(Checker c: panel.board.checkers) { 
				if(color  == c.color) { 
					myCheckers.add(c);
				}
			}
			int counter = 0;
			while(true) { 
				int randomChecker = 0;
				if(counter < 1000)
					randomChecker = (int)( 8 * Math.random());
				else 
					randomChecker = (int)( 12 * Math.random());
				Checker c = myCheckers.get(randomChecker); 
				int[] possible = c.possibleMoves();
				int possrandom = (int) (possible.length * Math.random());
				int space = possible[possrandom];
				if(!isCheckerInSpace(possible[possrandom], panel)) { 
					if(allowedSpace(space)) { 
						c.space = space;
						c.column = space/8;
						if(space %8 == 0) { 
							c.row = 7;
							c.column = c.column-1;
						}
						else 
							c.row = space% 8 - 1;

						panel.board.turn = 0;
						c.setPosition((c.row) * panel.getWidth()/8 + 9, (c.column) * panel.getHeight()/8 + 9);
						panel.repaint();
						break;
					}
				}
				counter++;

			}
		}
	}

	public static boolean isCheckerInSpace(int space, int color, CheckerPanel panel) { 
		for(Checker c : panel.board.checkers ) { 
			if(c.space == space && c.color == color) { 
				return true;
			}
		}
		return false;
	}

	public static boolean isCheckerInSpace(int space, CheckerPanel panel) { 
		for(Checker c : panel.board.checkers ) { 
			if(c.space == space) { 
				return true;
			}
		}
		return false;
	}

	public static int canKillAnother(Checker c, CheckerPanel panel) { 
		int[] possible = c.possibleMoves();
		for(Checker f: panel.board.checkers) { 
			if(f.color != c.color) { 
				for(int s = 0; s < possible.length; s++) { 
					if(possible[s] == f.space && f.color != c.color && f.draw) { 
						possible[s] = possible[s] + (possible[s] - c.space);
						if(possible[s] > 64 || possible[s] < 0 || isCheckerInSpace(possible[s], panel)) { 
							return 0;
						}
						else { 
							System.out.println(possible[s]);
							f.draw = false;
							f.space = 0;
							f.row = -1;
							f.column = -1;
						}
						return possible[s];
					}
				}
			}
		}
		return 0;
	}
	
	public static boolean canKill(Checker c, CheckerPanel panel) { 
		int[] possible = c.possibleMoves();
		for(Checker f: panel.board.checkers) { 
			if(f.color != c.color) { 
				for(int s = 0; s < possible.length; s++) { 
					if(possible[s] == f.space && f.color != c.color && f.draw) { 
						possible[s] = possible[s] + (possible[s] - c.space);
						if(possible[s] > 64 || possible[s] < 0 || isCheckerInSpace(possible[s], panel)) { 
							return false;
						}
						else { 
							System.out.println(possible[s]);
							f.draw = false;
							f.space = 0;
							f.row = -1;
							f.column = -1;
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean allowedSpace(int space) { 
		for (int i = 0; i < availiableSpaces.length; i++) { 
			if(availiableSpaces[i] == space) { 
				return true;
			}
		}
		return false;
	}
}
