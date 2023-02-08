package com.kjerne.src;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GLContext;

public class MainMenu implements State{
	
	private MouseInput mListener = new MouseInput();
	public int mouseX, mouseY;
	public String mouse = "null";
	
	private Font fnt;
	
	public static boolean h, hTwo, hThree, hFour;
	private Button buttonOne, buttonTwo, buttonThree;
	
	private boolean t;
	private int delta=0;
	
	public Window windowS;
	
	public MainMenu(int id){
		super();
		this.windowS=Main.windowS;
		delta=0;
	}
	
	public void init() {
		System.out.println("rar");
		GLContext.createFromCurrent();
		
		fnt = new Font();
	
		runLoop();
	}

	public void runLoop(){
		while(Main.getState()==1){
		try {
			delta=(int) (delta-GLFW.glfwGetTime());
			
			render();
			update(delta);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void render(){
		VisualDriver im = new VisualDriver(windowS, "C:\\Users\\Cole\\Desktop\\Escape\\Kjerne\\menuBackground.png", 0, 0, 600, 500);
		im.drawImage(0, 0);
		
		//Mouse Pos
		fnt.print(10, 480, mouse);

		//Buttons
		buttonOne = new Button("/Kjerne/Play Button H.png", 1, 0, 0, 288, 80, g);
		buttonOne.drawButton(windowS.getWidth() / 2 - 74, windowS.getHeight() / 6, g);
		//Buttons
		buttonTwo = new Button("/Kjerne/Play Button H.png", 2, 0, 0, 288, 80, g);
		buttonTwo.drawButton(windowS.getWidth() / 2 - 74, windowS.getHeight() / 6, g);
		//Buttons
		buttonThree = new Button("/Kjerne/Play Button H.png", 3, 0, 0, 288, 80, g);
		buttonThree.drawButton(windowS.getWidth() / 2 - 74, windowS.getHeight() / 6, g);
		
		t = true;
	}

	public void update(int delta){
		Input mInput = gameContainer.getInput();
		
		if(t == true){
		
		//Mouse Pos
		mListener.tickMouse();
		mouseX = mListener.getX();
		mouseY = windowS.getHeight() - mListener.getY();
		mouse = mouseX + ":" + mouseY;
		
		/*
		//Button 1
		if(mouseX > windowS.getWidth() / 2 - 77 && mouseX < windowS.getWidth() / 2 + 74 && mouseY > windowS.getHeight() / 6 && mouseY < windowS.getHeight() / 6 + 35){
			buttonOne.highlightButton();
			if(Main.nButtonClicked == false){
			if(mInput.isMouseButtonDown(0)){
				Main.nButtonClicked = true;
				
			}
			}
		}else{
			buttonOne.unhighlightButton();
			Main.nButtonClicked = false;
		}
		
		//Button 2
		if(mouseX > windowS.getWidth() / 2 - 77 && mouseX < windowS.getWidth() / 2 + 74 && mouseY > windowS.getHeight() / 3 && mouseY < windowS.getHeight() / 3 + 35){
			buttonTwo.highlightButton();
			
			if(mInput.isMouseButtonDown(0)){
		
			}
		}else{
			buttonTwo.unhighlightButton();
		}
		
		//Button 3
		if(mouseX > windowS.getWidth() / 2 - 77 && mouseX < windowS.getWidth() / 2 + 74 && mouseY > windowS.getHeight() / 2 && mouseY < windowS.getHeight() / 2 + 35){
			buttonThree.highlightButton();
			if(mInput.isMouseButtonDown(0)){
		        glfwTerminate();
				System.exit(0);
			}
		}else{
			buttonThree.unhighlightButton();
		}
		*/
		}
	}

	public int getID() {
		return 0;
	}

}
