package com.kjerne.src;

import org.newdawn.slick.Input;

public class KeyInput {
	
	public Input key;
	private int x = 215, y = 240;
	private boolean keyDown;
	
	public KeyInput(Input input){
		key = input;
	}
	
	public String getMoveKey(){
		if(key.isKeyDown(key.KEY_W) == true){
			return "UP";
		}else if(key.isKeyDown(key.KEY_A) == true){
			return "LEFT";
		}else if(key.isKeyDown(key.KEY_S) == true){
			return "DOWN";
		}else if(key.isKeyDown(key.KEY_D) == true){
			return "RIGHT";
		}
			return "NONE";
	}
	
	public void keyPressed(String key, int pX, int pY){
		if(key == "UP"){
			y = pY - 5;
		}else if(key == "LEFT"){
			x = pX - 5;
		}else if(key == "DOWN"){
			y = pY + 5;
		}else if(key == "RIGHT"){
			x = pX + 5;
		}
		
		}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean isKeyDown(){
		if(keyDown == true){
			return true;
		}else{
			return false;
		}
	}
}
