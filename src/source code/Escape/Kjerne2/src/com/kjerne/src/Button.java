package com.kjerne.src;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button {
	
		public Image im;
		private Graphics g;
		private int intX, intY;
		public String name = "Null";
		private boolean t = false;
	
	public Button(String path, String name) throws SlickException{
		g = new Graphics();
		
		this.name = name;
		
		if(name == "Button One"){
		if(MainMenu.h == true){
			im = new Image("Button H.png");
			im.setFilter(Image.FILTER_NEAREST);
			Image im2 = im.getScaledCopy((float) .5);
			im = im2;
			t = true;
			}
		}
		
		if(name == "Button Two"){
		if(MainMenu.hTwo == true){
			im = new Image("Button H.png");
			im.setFilter(Image.FILTER_NEAREST);
			Image im2 = im.getScaledCopy((float) .5);
			im = im2;
			t = true;
			}
		}
		
		if(name == "Button Three"){
		if(MainMenu.hThree == true){
			im = new Image("Button H.png");
			im.setFilter(Image.FILTER_NEAREST);
			Image im2 = im.getScaledCopy((float) .5);
			im = im2;
			t = true;
			}
		}
		
		if(name == "Button Four"){
		if(MainMenu.hFour == true){
			im = new Image("Button H.png");
			im.setFilter(Image.FILTER_NEAREST);
			Image im2 = im.getScaledCopy((float) .5);
			im = im2;
			t = true;
			}
		}
		
		if(t == false){
		im = new Image(path);
		im.setFilter(Image.FILTER_NEAREST);
		Image im2 = im.getScaledCopy((float) .5);
		im = im2;
		}
		
	}
	
	public void drawButton(int x, int y) throws SlickException{
		intX = x;
		intY = y;
		g.drawImage(im, x, y);
	}
	
	public void unhighlightButton(){
		if(name == "Button One"){
		MainMenu.h = false;
		}
		if(name == "Button Two"){
			MainMenu.hTwo = false;
		}
		if(name == "Button Three"){
			MainMenu.hThree = false;
		}
		if(name == "Button Four"){
			MainMenu.hFour = false;
		}
	}
	
	public void highlightButton(){
		if(name == "Button One"){
		MainMenu.h = true;
		}
		if(name == "Button Two"){
			MainMenu.hTwo = true;
		}
		if(name == "Button Three"){
			MainMenu.hThree = true;
		}
		if(name == "Button Four"){
			MainMenu.hFour = true;
		}
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
	public String getName(){
		return name;
	}
}
