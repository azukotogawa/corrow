package com.kjerne.src;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MultiplayerMenu implements State {

	public int mouseX, mouseY;
	private MouseInput mListener = new MouseInput();
	public String mouse = "null";
	
	private Button buttonOne, buttonTwo, buttonThree, buttonFour;
	public static boolean h, hTwo, hThree, ready = false;
	
	private Window windowS;
	
	private int ID;
	
	public MultiplayerMenu(int id){
		this.ID = id;
	}
	
	public void init(){
	}

	public void render(Graphics g) {
		mInput = gameContainer.getInput();
		
		Image im = new Image("menuBackground.png");
		im.setFilter(Image.FILTER_NEAREST);
		Image im2 = im.getScaledCopy((float) 2.5);
		g.drawImage(im2, 0, 0);

		//Buttons
		buttonOne = new Button("Button.png", "Button One");
		buttonOne.drawButton(Main.frame.getWidth() / 2 - 74, Main.frame.getHeight() / 6);
		//Buttons
		buttonTwo = new Button("Button.png", "Button Two");
		buttonTwo.drawButton(Main.frame.getWidth() / 2 + 5, Main.frame.getHeight() / 2);
		//Buttons
		buttonThree = new Button("Button.png", "Button Three");
		buttonThree.drawButton(Main.frame.getWidth() / 2 - 149, Main.frame.getHeight() / 2);
		//Buttons
		buttonFour = new Button("Button.png", "Button Four");
		buttonFour.drawButton(Main.frame.getWidth() / 2 - 74, Main.frame.getHeight() / 2 + 75);
		
		//Mouse Pos
		g.setColor(Color.white);
		g.drawString(mouse, 10, 480);
		
		
		if(!mInput.isMouseButtonDown(0)){
		ready = true;
		}
	}

	public void update(int delta) throws SlickException {
		if(ready == true){
		
		//Mouse Pos
		mListener.tickMouse();
		mouseX = mListener.getX();
		mouseY = Main.frame.getHeight() - mListener.getY();
		mouse = mouseX + ":" + mouseY;
		
				//Button 1
				if(mouseX > Main.frame.getWidth() / 2 - 74 && mouseX < Main.frame.getWidth() / 2 + 70 && mouseY > Main.frame.getHeight() / 6 && mouseY < Main.frame.getHeight() / 6 + 35){
					buttonOne.highlightButton();
					if(Main.nButtonClicked == false){
					if(mInput.isMouseButtonDown(0)){
						Main.nButtonClicked = true;
						stateBasedGame.enterState(3);
					}
					}
				}else{
					buttonOne.unhighlightButton();
					Main.nButtonClicked = false;
				}
				//Button 2
				if(mouseX > Main.frame.getWidth() / 2 + 5 && mouseX < Main.frame.getWidth() / 2 + 149 && mouseY > Main.frame.getHeight() / 2 && mouseY < Main.frame.getHeight() / 2 + 35){
					buttonTwo.highlightButton();
					if(mInput.isMouseButtonDown(0)){
					
					}
				}else{
					buttonTwo.unhighlightButton();
				}	
				
				//Button 3
				if(mouseX > Main.frame.getWidth() / 2 - 149 && mouseX < Main.frame.getWidth() / 2 - 5 && mouseY > Main.frame.getHeight() / 2 && mouseY < Main.frame.getHeight() / 2 + 35){
					buttonThree.highlightButton();
					if(mInput.isMouseButtonDown(0)){
					
					}
				}else{
					buttonThree.unhighlightButton();
				}	
				
				//Button 4
				if(mouseX > Main.frame.getWidth() / 2 - 74 && mouseX < Main.frame.getWidth() / 2 + 70 && mouseY > Main.frame.getHeight() / 2 + 75 && mouseY < Main.frame.getHeight() / 2 + 115){
					buttonFour.highlightButton();
					if(Main.buttonClicked == false){
					if(mInput.isMouseButtonDown(0)){
						Main.buttonClicked = true;
						stateBasedGame.enterState(0);
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