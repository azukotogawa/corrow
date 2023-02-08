package com.kjerne.src;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFW.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Playing implements State{

	private int ID;
	
	public int mouseX, mouseY;
	public String mouse = "null", keyName = "NONE", pos = "null";
	
	public Input mInput;
	public Image im;
	
	public int playerLocX, playerLocY;
	private Player player;
	
	private int min, sec, tick;
	private static int milli;
	
	private KeyHandler keyH;
	private MovementInputPlayer mip;
	
	private MouseInput mListener = new MouseInput();
	
	public GameSettings gameSettings;
	
	public Boolean inConsle=false;
	public int inConsleSwitch=0, released=1, pressed=0;
	
	//TEMP
	private Graphics graphicsTemp;
	public int once = 0, temp2 = 0;;
	
	private Projectile proj;
	private ProjectileHandler projHandler = new ProjectileHandler();;
	
	public Playing(int id){
		this.ID = id;
	}
	
	public void init() {
		//Time
		min = 0;
		sec = 0;
		milli = 0;
		
		gameSettings = new GameSettings();
		keyH = new KeyHandler();
		mip = new MovementInputPlayer(gameSettings);
	}

	public void render(Graphics g) {
		mInput = gameContainer.getInput();
		//Mouse Pos
		mListener.tickMouse();
		mouseX = mListener.getX();
		mouseY = Main.frame.getHeight() - mListener.getY();
		mouse = mouseX + ":" + mouseY;
		
		if(once == 0){
			loadMap(GameModeMenu.mapN, GameModeMenu.modeN);
			player = new Player("Mage", "Mage.ani", 5);
			once++;
		}
		
		g.drawImage(im, 0, 0);
		player.drawPlayer(playerLocX, playerLocY);
		
		
		for(int i=0; i<projHandler.projLength(); i++){
			if(projHandler.codeLength()!=0){
			Projectile proje = projHandler.getProj(i);
				proje.refresh();
			if(proje.tickReady(returnMilli())){
				proje.update();
			}
			}
		}
		//Player Pos
		pos = playerLocX + ":" + playerLocY;
		g.setColor(Color.white);
		g.drawString(pos, 10, 465);
		
		//Mouse Pos
		g.setColor(Color.white);
		g.drawString(mouse, 10, 480);
		
		//Time
		String time = min + ":" + sec + ":" + tick;
		g.drawString(time, 10, 450);
		
		//TEMP
		graphicsTemp = g;
		
		if(inConsle){
			System.out.println(inConsleSwitch);
		}
	}

	public void update(int delta) throws SlickException {
		//Tick
		milli += delta;
		if(milli >= 50){
			updateAlive();
			tick++;
			milli=0;
		}
		if(tick >= 20){
			sec++;
			tick = 0;
		}
		if(sec >= 60){
			min++;
			sec = 0;
		}
	}
	
	public void updateAlive() throws SlickException{
		//Player
	/*	KeyBinding.setKeyState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
			mip.updatePlayerMoveState(playerLocX, playerLocY, player.getSpeed());
			playerLocX = mip.getX();
			playerLocY = mip.getY();
			player.drawPlayer(playerLocX, playerLocY);
			
			if(Keyboard.getEventKey() == 28){
			int code = projHandler.codeLength()+1;
			projHandler.addCode(code);
			proj = new Projectile(code, 200, player.getX(), player.getY());
			projHandler.addObject(proj);
									
			proj.assign("Mage", "Fireball");
			proj.launch(2, graphicsTemp);
		}
		*/
	}

	public int getID() {
		return ID;
	}
	
	public void loadMap(String Map, String Mode) throws SlickException{
		//FIX
		
		Map = Map + ".mif";

		im = new Image(Map);
		im.setFilter(Image.FILTER_NEAREST);
		Image im2 = im.getScaledCopy((float)7.7);
		im = im2;
		
	}
	
	public static int returnMilli(){
		return milli;
	}
	
	public int returnSec(){
		return sec;
	}
	
	public int returnMin(){
		return min;
	}
}