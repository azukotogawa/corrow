import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class BattleshipApp extends JFrame implements MouseListener, KeyListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int y = 209;
	public static int x = 6;
	public static int velX = 0;
	public static int velY = 0;
	public static int Y;
	public static int X;
	public static int StartX = 6;
	public static int StartY = 209;
	public static int CHOICE_STATE_A = 1;
	public static int CHOICE_STATE_B = 2;
	public static int CHOICE_STATE_C = 3;
	public static int CHOICE_STATE_D = 4;
	public static int CHOICE_STATE_E = 5;
	public static int CheckX;
	public static int CheckY;
	public static int Hits = 0;
	public static int HitsEnemy = 0;
	public static int Hmm;

	public static int A = 9;
	public static int B = 29;
	public static int C = 49;
	public static int D = 69;
	public static int E = 89;
	public static int F = 109;
	public static int G = 129;
	public static int H = 149;
	public static int I = 169;
	public static int J = 189;
	public static int a = 209;
	public static int b = 189;
	public static int c = 169;
	public static int d = 149;
	public static int e = 129;
	public static int f = 109;
	public static int g = 89;
	public static int h = 69;
	public static int i = 49;
	public static int j = 29;

	private int[][] gridCells;

	Rectangle character = new Rectangle(x, y);

	private static final int GAME_STATE_INTRO = 0;
	private static final int GAME_STATE_SETUP = 1;
	private static final int GAME_STATE_PLAYING = 2;
	private static final int GAME_STATE_GAMEOVER = 3;

	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 500;
	private static final int TITLE_X = 260;
	private static final int TITLE_Y = 225;
	private static final int MOUSE_MSG_X = 228;
	private static final int MOUSE_MSG_Y = 275;
	private static final int RED_GRID_X = 25;
	private static final int RED_GRID_Y = 5;
	private static final int BLUE_GRID_X = 275;
	private static final int BLUE_GRID_Y = 5;

	private Clip error;
	private Clip pegged;
	private Clip victory;
	private Clip defeat;
	private Clip hitme;
	public static Clip hitenemy;
	private Clip missme;
	public static Clip missenemy;
	private Clip entersound;
	private Clip AA;
	private Clip BB;
	private Clip CC;
	private Clip DD;
	private Clip EE;
	private Clip FF;
	private Clip GG;
	private Clip HH;
	private Clip II;
	private Clip JJ;
	private Clip Aw;
	private Clip Bw;
	private Clip Cw;
	private Clip Dw;
	private Clip Ew;
	private Clip Fw;
	private Clip Gw;
	private Clip Hw;
	private Clip Iw;
	private Clip Jw;

	private static String SOUND_PATH = "";

	// instance variables
	private Board redBoard;
	private Board blueBoard;
	private FriendlyPlayer friendlyPlayer;
	private EnemyPlayer enemyPlayer;
	public static int gameState;
	public static int choiceState;

	JFrame jf = new JFrame();

	public static int repaint;
	Timer tm = new Timer(5, this);

	public void keyTyped(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {

	}

	/**
	 * Entry point for code execution.
	 */
	public static void main(String[] args) {
		// Initialize the sound manager.
		SoundManager.INIT();

		try {
			SOUND_PATH = args[0];
		} catch (Exception e) {
		}

		new BattleshipApp();
	}

	/**
	 * Constructor for objects of class BattleshipApp
	 */
	public BattleshipApp() {
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.show();
		this.addMouseListener(this);
		this.gameState = GAME_STATE_INTRO;
		choiceState = CHOICE_STATE_A;
		this.setTitle("Battleship For The Blind");

		this.gridCells = new int[Board.ROW_COUNT][Board.COLUMN_COUNT];

		// Initialize the game boards.
		this.redBoard = new Board();
		this.blueBoard = new Board();

		tm.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);

		this.error = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/error.au");
		this.pegged = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/pegged.au");
		this.victory = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/victory.au");
		this.defeat = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/defeat.au");
		this.entersound = SoundManager.LOAD(SOUND_PATH,
				"C:/Program Files/Battleship for the Blind/Sounds/entersound.au");
		this.hitme = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/hitme.au");
		this.hitenemy = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/hitenemy.au");
		this.missme = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/missme.au");
		this.missenemy = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/missenemy.au");
		this.AA = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/A.au");
		this.BB = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/B.au");
		this.CC = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/C.au");
		this.DD = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/D.au");
		this.EE = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/E.au");
		this.FF = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/F.au");
		this.GG = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/G.au");
		this.HH = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/H.au");
		this.II = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/I.au");
		this.JJ = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/J.au");
		this.Aw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/aw.au");
		this.Bw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/bw.au");
		this.Cw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/cw.au");
		this.Dw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/dw.au");
		this.Ew = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/ew.au");
		this.Fw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/fw.au");
		this.Gw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/gw.au");
		this.Hw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/hw.au");
		this.Iw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/iw.au");
		this.Jw = SoundManager.LOAD(SOUND_PATH, "C:/Program Files/Battleship for the Blind/Sounds/Words/jw.au");
	}

	private void Sounds() {
		if (y == 189) {
			SoundManager.PLAY(this.AA);
		}
		if (y == 169) {
			SoundManager.PLAY(this.BB);
		}
		if (y == 149) {
			SoundManager.PLAY(this.CC);
		}
		if (y == 129) {
			SoundManager.PLAY(this.DD);
		}
		if (y == 109) {
			SoundManager.PLAY(this.EE);
		}
		if (y == 89) {
			SoundManager.PLAY(this.FF);
		}
		if (y == 69) {
			SoundManager.PLAY(this.GG);
		}
		if (y == 49) {
			SoundManager.PLAY(this.HH);
		}
		if (y == 29) {
			SoundManager.PLAY(this.II);
		}
		if (y == 9) {
			SoundManager.PLAY(this.JJ);
		}

		if (x == 29) {
			SoundManager.PLAY(this.Aw);
		}
		if (x == 49) {
			SoundManager.PLAY(this.Bw);
		}
		if (x == 69) {
			SoundManager.PLAY(this.Cw);
		}
		if (x == 89) {
			SoundManager.PLAY(this.Dw);
		}
		if (x == 109) {
			SoundManager.PLAY(this.Ew);
		}
		if (x == 129) {
			SoundManager.PLAY(this.Fw);
		}
		if (x == 149) {
			SoundManager.PLAY(this.Gw);
		}
		if (x == 169) {
			SoundManager.PLAY(this.Hw);
		}
		if (x == 189) {
			SoundManager.PLAY(this.Iw);
		}
		if (x == 209) {
			SoundManager.PLAY(this.Jw);
		}
	}

	/**
	 * Converts a mouse y-coordinate into a row on the blue grid.
	 */
	private int convertYtoRow(int pixelY) {
		Container clientArea = this.getContentPane();
		int borderSize = (this.getWidth() - clientArea.getWidth()) / 2;
		int titleSize = (this.getHeight() - clientArea.getHeight()) - borderSize;
		int row = (pixelY - titleSize - RED_GRID_Y) / Board.ROW_PIXEL_HEIGHT;

		// otherwise
		if (row == 7) {
			row = 9;
		}
		if (row == 6) {
			row = 8;
		}
		if (row == 5) {
			row = 7;
		}
		if (row == 4) {
			row = 6;
		}
		if (row == 3) {
			row = 5;
		}
		if (row == 2) {
			row = 4;
		}
		if (row == 1) {
			row = 3;
		}
		if (row == -1) {
			row = 0;
		}

		// special
		if (Y == C) {
			row = 2;
		}
		if (Y == B) {
			row = 1;
		}

		System.out.println(row + ":::::");

		return row;
	}

	/**
	 * Converts a mouse x-coordinate into a column on the blue grid.
	 */
	private int convertXtoCol(int pixelX) {
		Container clientArea = this.getContentPane();
		int borderSize = (this.getWidth() - clientArea.getWidth()) / 2;
		int column = (pixelX - borderSize - RED_GRID_X) / Board.COLUMN_PIXEL_WIDTH;

		// special
		if (X == a) {
			column = 9;
		}
		if (X == b) {
			column = 8;
		}
		if (X == c) {
			column = 7;
		}
		if (X == d) {
			column = 6;
		}
		if (X == e) {
			column = 5;
		}
		if (X == f) {
			column = 4;
		}
		if (X == g) {
			column = 3;
		}
		if (X == h) {
			column = 2;
		}
		if (X == i) {
			column = 1;
		}
		System.out.println(column + "::::");

		return column;
	}

	private boolean isPlayerTargetValid() {
		boolean bIsValid = false;

		int row = convertYtoRow(Y);
		int col = convertXtoCol(X);

		// Is this square on the board?
		if (row >= 0 && row < Board.ROW_COUNT &&
				col >= 0 && col < Board.COLUMN_COUNT) {
			// Is square empty?
			bIsValid = this.blueBoard.isUnpegged(row, col);
		}

		return bIsValid;
	}

	private boolean isPlayerTargetShip() {
		boolean bIsShip = false;

		int row = convertYtoRow(Y);
		int col = convertXtoCol(X);

		// Is this square on the board?
		if (row >= 0 && row < Board.ROW_COUNT &&
				col >= 0 && col < Board.COLUMN_COUNT) {
			// Is square empty?
			bIsShip = this.blueBoard.isShip(row, col);
		}

		return bIsShip;
	}

	private void Hmm() {

		EnemyPlayer.EnemyPlayer();

		if (choiceState == CHOICE_STATE_C) {
			if (this.isPlayerTargetValid()) {
				if (!(this.isPlayerTargetShip())) {

					choiceState = CHOICE_STATE_D;
					int row = convertYtoRow(Y);
					int col = convertXtoCol(X);
					SoundManager.PLAY(this.missme);
					this.blueBoard.putPegInSquare(row, col, false);

					Container clientArea = this.getContentPane();
					Graphics gfx = clientArea.getGraphics();
					gfx.setColor(Color.white);
					gfx.fillRect(X + 251 - 3, Y - 2, 17, 17);

					choiceState = CHOICE_STATE_E;
				}
			}
		}
		if (this.isPlayerTargetValid()) {
			if (this.isPlayerTargetShip()) {

				choiceState = CHOICE_STATE_D;
				int row = convertYtoRow(Y);
				int col = convertXtoCol(X);
				SoundManager.PLAY(this.hitme);
				this.blueBoard.putPegInSquare(row, col, true);

				Container clientArea = this.getContentPane();
				Graphics gfx = clientArea.getGraphics();
				gfx.setColor(Color.red);
				gfx.fillRect(X + 251 - 3, Y - 2, 17, 17);
				Hits++;

				choiceState = CHOICE_STATE_E;
			}
		}

		if (choiceState == CHOICE_STATE_C) {
			if (!(this.isPlayerTargetValid())) {
				SoundManager.PLAY(this.pegged);
				X = 0;
				Y = 0;
				choiceState = CHOICE_STATE_A;
			}
		}

		if (choiceState == CHOICE_STATE_E) {

			System.out.println("Hello");
			int row = EnemyPlayer.Row;
			int col = EnemyPlayer.Col;

			if (this.redBoard.didHitShip(row, col)) {
				SoundManager.PLAY(BattleshipApp.hitenemy);
				this.redBoard.putPegInSquare(row, col, true);
				repaint();
				Y = 0;
				X = 0;
				HitsEnemy++;
				choiceState = CHOICE_STATE_A;
			} else if (!(this.redBoard.didHitShip(row, col))) {
				SoundManager.PLAY(BattleshipApp.missenemy);
				this.redBoard.putPegInSquare(row, col, false);
				repaint();
				X = 0;
				Y = 0;
				choiceState = CHOICE_STATE_A;
			}
		}

	}

	/**
	 * Draws the game window.
	 */
	public void paint(Graphics gfx) {
		if (this.gameState == GAME_STATE_INTRO) {
			this.drawTitleScreen();
		} else if (this.gameState == GAME_STATE_SETUP) {
			this.drawGrids();
			this.drawA1();
			this.drawCharacter();
			this.Boundaries();
			this.Hmm();
			this.Sounds();
		}
		if (Hits == 17) {
			gameState = GAME_STATE_GAMEOVER;
		}
		if (HitsEnemy == 7) {
			gameState = GAME_STATE_GAMEOVER;
		}
		if (gameState == GAME_STATE_GAMEOVER) {
			if (Hits == 17) {
				Container clientArea = this.getContentPane();
				int width = clientArea.getWidth();
				int height = clientArea.getHeight();
				gfx.setColor(Color.black);
				gfx.fillRect(0, 0, width, width);
				gfx.setColor(Color.white);
				SoundManager.PLAY(this.victory);
				gfx.drawString("YOU COMPLETED YOUR MISSION!", 202, 150);
				gfx.drawString("Credits", 275, 190);
				gfx.drawString("Game Programming: Cole Withington", 200, 215);
				gfx.drawString("Sound Manager: Nate Leu", 200, 240);
				gfx.drawString("Beta Testers: ", 200, 265);
				// replace with names
				gfx.drawString(":", 270, 290);
				gfx.drawString(":", 270, 315);
				gfx.drawString(":", 270, 340);
			}
		}
		if (gameState == GAME_STATE_GAMEOVER) {
			if (HitsEnemy == 7) {
				Container clientArea = this.getContentPane();
				int width = clientArea.getWidth();
				int height = clientArea.getHeight();
				gfx.setColor(Color.black);
				gfx.fillRect(0, 0, width, width);
				gfx.setColor(Color.white);
				SoundManager.PLAY(this.defeat);
				gfx.drawString("YOU FAILED YOUR MISSION!", 205, 150);
				gfx.drawString("Credits", 275, 190);
				gfx.drawString("Game Programming: Cole Withington", 200, 215);
				gfx.drawString("Sound Manager: Nate Leu", 200, 240);
				gfx.drawString("Beta Testers: ", 200, 265);
				gfx.drawString(":", 270, 290);
				gfx.drawString(":", 270, 315);
				gfx.drawString(":", 270, 340);

			}
		}
	}

	private void Boundaries() {
		if (this.choiceState == CHOICE_STATE_B) {
			this.NumBoundAndSwap();
		}
		if (this.choiceState == CHOICE_STATE_A) {
			this.CharacterBoundriesA();
		}
	}

	/**
	 * Draws the 'Welcome to Battleship' screen.
	 */
	private void drawTitleScreen() {
		// Get an object representing the area within the window borders.
		Container clientArea = this.getContentPane();

		// Get the Graphics object associated with the client area.
		Graphics gfx = clientArea.getGraphics();

		// Get the size of the client area.
		int width = clientArea.getWidth();
		int height = clientArea.getHeight();

		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, width, height);

		gfx.setColor(Color.green);
		gfx.drawString("BATTLESHIP", TITLE_X, TITLE_Y);
		gfx.setColor(Color.gray);
		gfx.drawString("(click mouse to continue)", MOUSE_MSG_X, MOUSE_MSG_Y);
	}

	// Draws ABC's and 123's
	private void drawA1() {
		// Get an object representing the area within the window borders.
		Container clientArea = this.getContentPane();

		// Get the Graphics object associated with the client area.
		Graphics gfx = clientArea.getGraphics();

		// Actual letters
		gfx.setColor(Color.white);
		gfx.drawString("A", 9, 200);
		gfx.drawString("B", 8, 180);
		gfx.drawString("C", 8, 160);
		gfx.drawString("D", 8, 140);
		gfx.drawString("E", 8, 120);
		gfx.drawString("F", 8, 100);
		gfx.drawString("G", 8, 80);
		gfx.drawString("H", 8, 60);
		gfx.drawString("I", 11, 40);
		gfx.drawString("J", 10, 20);
		gfx.drawString("1", 33, 220);
		gfx.drawString("2", 52, 220);
		gfx.drawString("3", 72, 220);
		gfx.drawString("4", 92, 220);
		gfx.drawString("5", 112, 220);
		gfx.drawString("6", 132, 220);
		gfx.drawString("7", 152, 220);
		gfx.drawString("8", 172, 220);
		gfx.drawString("9", 192, 220);
		gfx.drawString("10", 208, 220);
	}

	/**
	 * Draw the game grids.
	 */
	private void drawGrids() {
		// Get an object representing the area within the window borders.
		Container clientArea = this.getContentPane();

		// Get the Graphics object associated with the client area.
		Graphics gfx = clientArea.getGraphics();

		// Get the size of the client area.
		int width = clientArea.getWidth();
		int height = clientArea.getHeight();

		// Fill the background with black.
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, width, height);

		// Draw the two grids.
		this.redBoard.drawGrid(gfx, Color.red, RED_GRID_X, RED_GRID_Y);
		this.blueBoard.drawGrid(gfx, Color.blue, BLUE_GRID_X, BLUE_GRID_Y);

		// Draw the ships on the grids.
		this.redBoard.drawShips(gfx, Color.gray, RED_GRID_X, RED_GRID_Y);
		this.blueBoard.drawShips(gfx, Color.gray, BLUE_GRID_X, BLUE_GRID_Y);
	}

	private void CharacterBoundriesA() {
		if (choiceState == CHOICE_STATE_A) {

			if (x == 1) {
				x = x + 5;
				SoundManager.PLAY(this.error);
				repaint();
			}
			if (x == 11) {
				x = x - 5;
				SoundManager.PLAY(this.error);
				repaint();
			}
			if (y == -11) {
				y = y + 20;
				SoundManager.PLAY(this.error);
				repaint();
			}
			if (y == 229) {
				y = y - 20;
				SoundManager.PLAY(this.error);
				repaint();
			}
		}
	}

	private void drawCharacter() {
		Container clientArea = this.getContentPane();
		Graphics gfx = clientArea.getGraphics();
		gfx.setColor(Color.white);
		gfx.drawRect(x, y, 12, 12);
	}

	private void NumBoundAndSwap() {

		if (choiceState == CHOICE_STATE_B) {

			if (x == 9) {
				x = x - 3;
				SoundManager.PLAY(this.error);
				repaint();
			}
			if (x == -14) {
				x = x + 20;
				SoundManager.PLAY(this.error);
				repaint();
			}
			if (x == 229) {
				x = x - 20;
				SoundManager.PLAY(this.error);
				repaint();
			}
			if (y == 189) {
				y = y + 20;
				SoundManager.PLAY(this.error);
				repaint();
			}
			if (y == 229) {
				y = y - 20;
				SoundManager.PLAY(this.error);
				repaint();
			}
		}
	}

	public void keyPressed(KeyEvent m) {
		int co = m.getKeyCode();

		if (co == KeyEvent.VK_A) {
			if (choiceState == CHOICE_STATE_A) {
				x = x - 5;
				y = y + 0;
				repaint();
			}
			if (choiceState == CHOICE_STATE_B) {
				x = x - 20;
				y = y + 0;
				repaint();
			}
			if (x == 6) {
				repaint();
			}
		}

		if (co == KeyEvent.VK_W) {
			if (choiceState == CHOICE_STATE_A) {
				x = x - 0;
				y = y - 20;
				repaint();
			}
			if (choiceState == CHOICE_STATE_B) {
				x = x - 0;
				y = y - 20;
				repaint();
			}
		}

		if (co == KeyEvent.VK_D) {
			if (choiceState == CHOICE_STATE_A) {
				x = x + 5;
				y = y + 0;
				repaint();
			}
			if (choiceState == CHOICE_STATE_B) {
				x = x + 20;
				y = y + 0;
				repaint();

				if (x == 26) {
					x = x + 3;
				}
				repaint();
			}
		}

		if (co == KeyEvent.VK_S) {
			if (choiceState == CHOICE_STATE_A) {
				x = x + 0;
				y = y + 20;
			}
			repaint();
			if (choiceState == CHOICE_STATE_B) {
				x = x + 0;
				y = y + 20;
			}
			repaint();
		}

		if (co == KeyEvent.VK_ENTER) {
			if (y == 189) {
				Y = J;
			}
			if (y == 169) {
				Y = I;
			}
			if (y == 149) {
				Y = H;
			}
			if (y == 129) {
				Y = G;
			}
			if (y == 109) {
				Y = F;
			}
			if (y == 89) {
				Y = E;
			}
			if (y == 69) {
				Y = D;
			}
			if (y == 49) {
				Y = C;
			}
			if (y == 29) {
				Y = B;
			}
			if (y == 9) {
				Y = A;
				System.out.println("Hello");
			}

			if (x == 209) {
				X = a;
			}
			if (x == 189) {
				X = b;
			}
			if (x == 169) {
				X = c;
			}
			if (x == 149) {
				X = d;
			}
			if (x == 129) {
				X = e;
			}
			if (x == 109) {
				X = f;
			}
			if (x == 89) {
				X = g;
			}
			if (x == 69) {
				X = h;
			}
			if (x == 49) {
				X = i;
			}
			if (x == 29) {
				X = j;
			}

			SoundManager.PLAY(this.entersound);

			y = StartY;
			x = StartX;
			repaint();
			System.out.println(X);
			System.out.println(Y);

			if (Y != 0) {
				Hmm = 1;
			}

			if (X != 0) {
				Hmm = 2;
			}

			if (Hmm == 1) {
				if (choiceState == CHOICE_STATE_A) {
					choiceState = CHOICE_STATE_B;
				}
			}
			if (Hmm == 2) {
				if (choiceState == CHOICE_STATE_B) {
					choiceState = CHOICE_STATE_C;
				}
			}
		}
	}

	/**
	 * MouseListener methods.
	 */
	public void mouseClicked(MouseEvent event) {
	}

	public void mouseEntered(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
		if (this.gameState == GAME_STATE_INTRO) {
			this.gameState = GAME_STATE_SETUP;
			this.blueBoard.placeComputerShips();
			this.redBoard.placePlayerShips();
			this.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
