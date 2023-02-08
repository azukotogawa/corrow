package com.kjerne.src;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	
	private Graphics g;
	Image im, standingF, standingB, foward, back;
	private int intX, intY;
	private int direction = 0;
	
	public Player(String character, String path) throws SlickException{
		g = new Graphics();
		
		Image base = new Image(path);
		base.setFilter(Image.FILTER_NEAREST);

		im = base.getSubImage(0, 0, 21, 25);
		
	
	}
	
	public void drawPlayer(int x, int y) throws SlickException{
		intX = x;
		intY = y;
		
		g.drawImage(im, x, y);
	}
	
	
	public int getX(){
		return intX;
	}
	
	public int getY(){
		return intY;
	}
	
	public int getDirection(){
		return direction;
	}
}
