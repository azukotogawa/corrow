package com.kjerne.src;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Playing extends BasicGameState{

	public int mouseX, mouseY;
	public String mouse = "null", keyName = "NONE";
	public int once = 0, temp2 = 0;;
	public Input mInput;
	public Image im;
	public int playerLocX, playerLocY;
	private MouseInput mListener = new MouseInput();
	private Player player;
	private int min, sec;
	private static int milli;
	private Input key;
	private KeyInput keyIn;
	public static Tick tick;
	
	private Projectile proj;
	
	public Playing(int state){
	}
	
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		//Time
		min = 0;
		sec = 0;
		milli = 0;
		
		
		//TEMP PROJ
	
		
		key = gameContainer.getInput();
		keyIn = new KeyInput(key);
		tick = new Tick();
	}

	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)	throws SlickException {
		mInput = gameContainer.getInput();
		//Mouse Pos
		mListener.tickMouse();
		mouseX = mListener.getX();
		mouseY = Main.frame.getHeight() - mListener.getY();
		mouse = mouseX + ":" + mouseY;
		
		if(once == 0){
			loadMap(GameModeMenu.mapN, GameModeMenu.modeN);
			player = new Player("Mage", "Mage.ani");
			once++;
		}
		
		g.drawImage(im, 0, 0);
		player.drawPlayer(playerLocX, playerLocY);
		
		//TEMP PROJ
		if(temp2 == 0){
		proj = new Projectile("Mage", "Fireball");
		proj.launch(1, 20, g);
		proj.tick();
		temp2++;
		}
		
		proj.draw();
		
		//Mouse Pos
		g.setColor(Color.white);
		g.drawString(mouse, 10, 480);
		
		//Time
		String time = min + ":" + sec + ":" + milli/100;
		g.drawString(time, 10, 465);
	}

	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		//Tick
		milli += delta;
		tick.tick(milli);
		if(milli >= 1000){
			sec++;
			milli = 0;
		}
		if(sec >= 60){
			min++;
			sec = 0;
		}
		
		//KeyListener
		keyName = keyIn.getMoveKey();
		if(keyName != "NONE"){
			if(tick.tickNew() == true){
				tick.usedTick();
		keyIn.keyPressed(keyName, player.getX(), player.getY());
			}
		}
		playerLocX = keyIn.getX();
		playerLocY = keyIn.getY();
	}

	public int getID() {
		return 2;
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