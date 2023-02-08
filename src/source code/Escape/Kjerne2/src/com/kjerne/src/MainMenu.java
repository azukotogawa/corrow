package com.kjerne.src;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainMenu extends BasicGameState{
	
	public int mouseX, mouseY;
	private Button buttonOne, buttonTwo, buttonThree;
	private MouseInput mListener = new MouseInput();
	public String mouse = "null";
	public static boolean h, hTwo, hThree, hFour;
	private boolean t;
	
	public MainMenu(int state){
	}
	
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
	}

	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)	throws SlickException {
		Image im = new Image("menuBackground.png");
		im.setFilter(Image.FILTER_NEAREST);
		Image im2 = im.getScaledCopy((float) 2.5);
		g.drawImage(im2, 0, 0);

		//Mouse Pos
		g.setColor(Color.white);
		g.drawString(mouse, 10, 480);
		
		//Buttons
		buttonOne = new Button("Button.png", "Button One");
		buttonOne.drawButton(Main.frame.getWidth() / 2 - 74, Main.frame.getHeight() / 6);
		//Buttons
		buttonTwo = new Button("Button.png", "Button Two");
		buttonTwo.drawButton(Main.frame.getWidth() / 2 - 74, Main.frame.getHeight() / 3);
		//Buttons
		buttonThree = new Button("Button.png", "Button Three");
		buttonThree.drawButton(Main.frame.getWidth() / 2 - 74, Main.frame.getHeight() / 2);
		
		t = true;
	}

	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		Input mInput = gameContainer.getInput();
		
		if(t == true){
		
		//Mouse Pos
		mListener.tickMouse();
		mouseX = mListener.getX();
		mouseY = Main.frame.getHeight() - mListener.getY();
		mouse = mouseX + ":" + mouseY;
		
		//Button 1
		if(mouseX > Main.frame.getWidth() / 2 - 77 && mouseX < Main.frame.getWidth() / 2 + 74 && mouseY > Main.frame.getHeight() / 6 && mouseY < Main.frame.getHeight() / 6 + 35){
			buttonOne.highlightButton();
			if(Main.nButtonClicked == false){
			if(mInput.isMouseButtonDown(0)){
				Main.nButtonClicked = true;
				stateBasedGame.enterState(1);
				System.out.println("1");
			}
			}
		}else{
			buttonOne.unhighlightButton();
			Main.nButtonClicked = false;
		}
		
		//Button 2
		if(mouseX > Main.frame.getWidth() / 2 - 77 && mouseX < Main.frame.getWidth() / 2 + 74 && mouseY > Main.frame.getHeight() / 3 && mouseY < Main.frame.getHeight() / 3 + 35){
			buttonTwo.highlightButton();
			
			if(mInput.isMouseButtonDown(0)){
		
			}
		}else{
			buttonTwo.unhighlightButton();
		}
		
		//Button 3
		if(mouseX > Main.frame.getWidth() / 2 - 77 && mouseX < Main.frame.getWidth() / 2 + 74 && mouseY > Main.frame.getHeight() / 2 && mouseY < Main.frame.getHeight() / 2 + 35){
			buttonThree.highlightButton();
			if(mInput.isMouseButtonDown(0)){
				Main.frame.destroy();
				System.exit(0);
			}
		}else{
			buttonThree.unhighlightButton();
		}
		}
	}

	public int getID() {
		return 0;
	}

}
