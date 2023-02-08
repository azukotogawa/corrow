package com.escape.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Main extends Canvas implements Runnable, ActionListener, KeyListener, MouseListener{
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 600;
	public static int HEIGHT = 500;
	public static int WORLD_WIDTH = 2000;
	public static int WORLD_HEIGHT = 2000;
	public static JFrame frame;
	private Thread thread;
	private boolean running, aHeld, sHeld, dHeld, wHeld, inMenu = true;
	public static boolean inGame = false;
	public static boolean inEsc = false;
	private  Render render;
	private Menu menu;
	private Graphics g;
	private BufferStrategy bs;
	private int keyLeft, keyDown, keyRight, keyUp, keyEsc;
	
	public static void main(String[] args){		
		frame = new JFrame();
		frame.setTitle("Escape");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setFocusable(true);
		
		frame.show();
		
		Main gameWindow = new Main();
		frame.add(gameWindow);
		
		frame.setVisible(true);
		  gameWindow.start();
		  
		  frame.pack();
	}
	
	public Main(){
		render = new Render(WORLD_WIDTH, WORLD_HEIGHT, 5, 5);
		menu = new Menu();
		keyBindings();
		frame.addKeyListener(this);
		addKeyListener(this);
    	frame.addMouseListener(this);
    	addMouseListener(this);
	}
	
	private void start(){
		if(running) return;
		running = true;
			
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		while(running){
			//tick();
			Render();
		}
	}
	
	
	private void Render(){
		
		bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();

		if(inMenu == true){
			menu.startMenu(render, g, bs);
		}
		
		if(inGame == true){
		render.createCells(0, 0);
		render.drawCells(render, g, bs, 0, 0);
		render.createEntities(1);
		render.drawEntities(render, g, bs);
		}
		bs.show();
	}    

	/*KEYS
	 *  |
	 *  |
	 *  |
	 *  |
	 *  |
	 * \|/
	 */
	
	private void keyBindings(){
		keyLeft = KeyEvent.VK_A;
		keyDown = KeyEvent.VK_S;
		keyRight = KeyEvent.VK_D;
		keyUp = KeyEvent.VK_W;
		keyEsc = KeyEvent.VK_ESCAPE;
	}
	
		public void keyPressed(KeyEvent key) {
			
			if(inGame == true){
				
			bs = this.getBufferStrategy();
			if(bs == null){
				createBufferStrategy(2);
				return;
			}
			
			g = bs.getDrawGraphics();
			
			int keyPressed = key.getKeyCode();
			
			if(keyPressed == keyEsc){
				menu.Pause(render, g, bs);
				inEsc = true;
			}
	
			if(keyPressed == keyLeft || keyPressed == keyUp){
				if(aHeld == true && wHeld == true){
					if(Player.getLocationX() != 0 && Player.getLocationY() != 0){
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
					if(key.getKeyCode() == keyLeft){
						Player.setLocationY(Player.getLocationY() - 5);
					}
					
					if(key.getKeyCode() == keyUp){
						Player.setLocationX(Player.getLocationX() - 5);
					}
					
					render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
				}
			}
			
			if(keyPressed == keyLeft || keyPressed == keyDown){
				if(aHeld == true && sHeld == true){
					if(Player.getLocationX() != 0 && Player.getLocationY() != 2000){
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
					
					if(key.getKeyCode() == keyLeft){
						Player.setLocationY(Player.getLocationY() + 5);
					}
					
					if(key.getKeyCode() == keyDown){
						Player.setLocationX(Player.getLocationX() - 5);
					}
					
					render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
				}
			}
			
			if(keyPressed == keyRight || keyPressed == keyUp){
				if(dHeld == true && wHeld == true){
					if(Player.getLocationX() != 2000 && Player.getLocationY() != 0){
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
					
					if(key.getKeyCode() == keyRight){
						Player.setLocationY(Player.getLocationY() - 5);
					}
					
					if(key.getKeyCode() == keyUp){
						Player.setLocationX(Player.getLocationX() + 5);
					}
					
					render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
				}
			}

			if(keyPressed == keyRight || keyPressed == keyDown){
				if(dHeld == true && sHeld == true){
					if(Player.getLocationX() != 0 && Player.getLocationY() != 2000){
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
					
					if(key.getKeyCode() == keyRight){
						Player.setLocationY(Player.getLocationY() + 5);
					}
					
					if(key.getKeyCode() == keyDown){
						Player.setLocationX(Player.getLocationX() + 5);
					}
					
					render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
				}
			}
			
			if(keyPressed == keyLeft){
				
				aHeld = true;
				
				if(Player.getLocationX() != 0){
					if(Player.getLocationX() >= frame.getWidth() / 2){
						//5 move distance
						render.moveScreen(render, g, bs, 1);
					}
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
				Player.setLocationX(Player.getLocationX() - 5);
				render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
			}
			if(keyPressed == keyDown){
				
				sHeld = true;
				
				if(Player.getLocationY() != 2000){
					if(Player.getLocationY() <= WORLD_HEIGHT - frame.getHeight() / 2){
						//5 move distance
						render.moveScreen(render, g, bs, 2);
					}
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
				Player.setLocationY(Player.getLocationY() + 5);				
				render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
			}
			if(keyPressed == keyRight){
				
				dHeld = true;
				
				if(Player.getLocationX() != 2000){
					if(Player.getLocationX() <= WORLD_WIDTH - frame.getWidth() / 2){
						//5 move distance
						render.moveScreen(render, g, bs, 3);
					}
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
				Player.setLocationX(Player.getLocationX() + 5);
				render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
			}
			if(keyPressed == keyUp){
				
				wHeld = true;
				
				if(Player.getLocationY() != 0){
					if(Player.getLocationY() >= frame.getHeight() / 2){
						//5 move distance
						render.moveScreen(render, g, bs, 4);
					}
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
				Player.setLocationY(Player.getLocationY() - 5);
				render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
				}
			}	
			bs.show();
		}
			}
		public void keyReleased(KeyEvent key) {
			
			int keyReleased = key.getKeyCode();
			
			if(keyReleased == KeyEvent.VK_A){
				if(wHeld != true || sHeld != true){
					aHeld = false;
				}
			}
			
			if(keyReleased == KeyEvent.VK_S){
				if(aHeld != true || dHeld != true){
					sHeld = false;
				}
			}
			
			if(keyReleased == KeyEvent.VK_D){
				if(wHeld != true || sHeld != true){
					dHeld = false;
				}
			}
			
			if(keyReleased == KeyEvent.VK_W){
				if(aHeld != true || dHeld != true){
					wHeld = false;
				}
			}
			
		}

		public void keyTyped(KeyEvent e) {
		}
		
		/*Mouse Listener
		 *  |
		 *  |
		 *  |
		 *  |
		 *  |
		 * \|/
		 */
		
		public void mouseClicked(MouseEvent e) {
			
			int mousePressed = e.getButton();
		
			if(mousePressed == MouseEvent.BUTTON1){
			}
			if(mousePressed == MouseEvent.BUTTON2){
			}
			if(mousePressed == MouseEvent.BUTTON3){
			}
			
			if(inMenu == true){
			
			if(mousePressed == MouseEvent.BUTTON1){
			
			int x = e.getX();
			int y = e.getY();

			//PLAY
			if(x >= 205 && x <= 395){
				if(y >= 55 && y <= 95){
					inMenu = false;
					menu.joinGameMenu(render, g, bs);
				}
			}
			
			//OPTIONS
			if(x >= 205 && x <= 395){
				if(y >= 155 && y <= 195){
					inMenu = false;
					menu.Options(render, g, bs);
				}
			}
			
			
			//EXIT
			if(x >= 205 && x <= 395){
				if(y >= 255 && y <= 295){
					System.exit(0);
				}
			}
			
			}
			}
			
			if(inEsc == true){
				if(mousePressed == MouseEvent.BUTTON1){
				int x = e.getX();
				int y = e.getY();
				
			//RESUME
			if(x >= 205 && x <= 395){
				if(y >= 55 && y <= 95){
					render.refresh(g, bs);
					render.resetTiles(g, bs, Player.getLocationX(), Player.getLocationY());
					render.updateEntites(g, Player.getLocationX(), Player.getLocationY(), 1);
					inEsc = false;
				}
			}
			
			//OPTIONS
			if(x >= 205 && x <= 395){
				if(y >= 155 && y <= 195){
					menu.Options(render, g, bs);
				}
			}
			
			
			//LEAVE
			if(x >= 205 && x <= 395){
				if(y >= 255 && y <= 295){
					
				}
			}
			}
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

}
