package com.kjerne.src;

import org.lwjgl.input.Mouse;

public class MouseInput {
	
	private int x, y;
	
	public void tickMouse(){
		x = Mouse.getX();
		y = Mouse.getY();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
