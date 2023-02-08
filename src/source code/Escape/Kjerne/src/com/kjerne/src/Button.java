package com.kjerne.src;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button {
	
		public Image im;
		
		private int intX, intY;
		
		public int ID;
	
	public Button(Window window, String path, int id, int imageX, int imageY, int width, int height) throws SlickException{
		g = new Graphics();
		this.ID = id;
		
		VisualDriver vs = new VisualDriver(window, path, imageX, imageY, 600, 500);
		im = vs.get; //not done
	}
	
	public void drawButton(int screenX, int screenY, Graphics g) throws SlickException{
		intX = screenX;
		intY = screenY;
		g.drawImage(im, screenX, screenY); //stop using graphics (slick), yay opengl, is so fun /s
	}
	
	public void unhighlightButton(){

	}
	
	public void highlightButton(){

	}

	public int getX(){
		return intX;
	}
	
	public int getXTwo(){
		return im.getWidth();
	}
	
	public int getY(){
		return intY;
	}
	
	public int getYTwo(){
		return im.getHeight();
	}
	public int getID(){
		return ID;
	}
}
