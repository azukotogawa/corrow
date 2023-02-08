package com.kjerne.src;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameModeMenu implements State{

	private int ID;
	
	public int mouseX, mouseY;
	public String mouse = "null";
	public static String mapN = "null", modeN = "null";
	private Button buttonOne, buttonTwo, buttonThree, buttonFour;
	private boolean ready, clicked = false;;
	public Input mInput;
	public Map map = new Map(3);
	public Mode mode = new Mode(3);
	private MouseInput mListener = new MouseInput();
	
	public GameModeMenu(int id){
		this.ID = id;
	}
	
	public void init() {
	}

	public void render( Graphics g)	throws SlickException {
		mInput = gameContainer.getInput();
		
		Image im = new Image("menuBackground.png");
		im.setFilter(Image.FILTER_NEAREST);
		Image im2 = im.getScaledCopy((float) 2.5);
		g.drawImage(im2, 0, 0);
		
		//Buttons
		buttonOne = new Button("Button.png", "Button One");
		buttonOne.drawButton(Main.frame.getWidth() / 2 - 74, Main.frame.getHeight() / 4);
		
		//Buttons
		buttonTwo = new Button("Button.png", "Button Two");
		buttonTwo.drawButton(Main.frame.getWidth() / 2 - 74, Main.frame.getHeight() / 3 + 15);
		
		//Buttons
		buttonThree = new Button("Button.png", "Button Three");
		buttonThree.drawButton(Main.frame.getWidth() / 2 - 74, (int) (Main.frame.getHeight() / 2));
		
		//Buttons
		buttonFour = new Button("Button.png", "Button Four");
		buttonFour.drawButton(Main.frame.getWidth() / 2 - 74, (int) (Main.frame.getHeight() / 1.5));
		
		//Mouse Pos
		g.setColor(Color.white);
		g.drawString(mouse, 10, 480);
		
		//TEMP
		g.drawString("Mode", Main.frame.getWidth() / 2 - 25, Main.frame.getHeight() / 4 - 20);
		g.drawString("Map", Main.frame.getWidth() / 2 - 22, Main.frame.getHeight() / 3 - 4);
		g.drawString("Go!", Main.frame.getWidth() / 2 - 14, Main.frame.getHeight() / 2 + 10);
		
		g.drawString(modeN, Main.frame.getWidth() / 2 - 50, Main.frame.getHeight() / 4 + 10);
		g.drawString(mapN, Main.frame.getWidth() / 2 - 50, Main.frame.getHeight() / 3 + 25);
	
		ready = true;
	}
	
	public void nextMap(){
			mapN = map.nextMap(); 
	}
	public void nextMode(){
			modeN = mode.nextMode(); 
	}


	public void update(int delta) throws SlickException {		
		if(ready == true){
		
		//Mouse Pos
		mListener.tickMouse();
		mouseX = mListener.getX();
		mouseY = Main.frame.getHeight() - mListener.getY();
		mouse = mouseX + ":" + mouseY;
		
		//Button 1
		if(mouseX > Main.frame.getWidth() / 2 - 74 && mouseX < Main.frame.getWidth() / 2 + 70 && mouseY > Main.frame.getHeight() / 4 && mouseY < Main.frame.getHeight() / 4 + 35){
			buttonOne.highlightButton();
			if(mInput.isMouseButtonDown(0)){
				if(clicked == false){
					clicked = true;
					nextMode();
				}
			}
			if(!mInput.isMouseButtonDown(0)){
				clicked = false;
			}
		}else{
			buttonOne.unhighlightButton();
		}	
		
		//Button 2
		if(mouseX > Main.frame.getWidth() / 2 - 77 && mouseX < Main.frame.getWidth() / 2 + 74 && mouseY > Main.frame.getHeight() / 3 + 15 && mouseY < Main.frame.getHeight() / 3 + 50){
			buttonTwo.highlightButton();
			if(mInput.isMouseButtonDown(0)){
				if(clicked == false){
					nextMap();
					clicked = true;
				}
		}
			if(!mInput.isMouseButtonDown(0)){
				clicked = false;
		}
		}else{
			buttonTwo.unhighlightButton();
		}
		//Button 3
		if(mouseX > Main.frame.getWidth() / 2 - 77 && mouseX < Main.frame.getWidth() / 2 + 74 && mouseY > Main.frame.getHeight() / 2 && mouseY < Main.frame.getHeight() / 2 + 35){
			buttonThree.highlightButton();
			if(mInput.isMouseButtonDown(0)){
				if(mapN != "null" && modeN != "null"){
					stateBasedGame.enterState(4);
				}
			}
		}else{
			buttonThree.unhighlightButton();
		}
		
		//Button 4
		if(mouseX > Main.frame.getWidth() / 2 - 77 && mouseX < Main.frame.getWidth() / 2 + 74 && mouseY > Main.frame.getHeight() / 1.5 && mouseY < Main.frame.getHeight() / 1.5 + 35){
			buttonFour.highlightButton();
			if(Main.buttonClicked == false){
				if(mInput.isMouseButtonDown(0)){
					Main.buttonClicked = true;
					stateBasedGame.enterState(1);
				}
				}
			}else{
				buttonFour.unhighlightButton();
				Main.buttonClicked = false;
			}
		
		}
		
	}
	
	public int getID() {
		return ID;
	}

}
