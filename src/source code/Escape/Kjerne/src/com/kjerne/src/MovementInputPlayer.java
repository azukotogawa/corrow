package com.kjerne.src;

public class MovementInputPlayer extends MovementInput{
	private GameSettings gameSettings;
	private int x, y;
	
	public MovementInputPlayer(GameSettings par1GameSettings){
		this.gameSettings = par1GameSettings;
	}
	
	public void updatePlayerMoveState(int px, int py, int speed){
		this.x = px;
		this.y = py;
		if(this.gameSettings.keyUp.pressed){
			y = y-speed;
		}
		if(this.gameSettings.keyDown.pressed){
			y = y+speed;
		}
		if(this.gameSettings.keyLeft.pressed){
			x = x-speed;
		}
		if(this.gameSettings.keyRight.pressed){
			x = x+speed;
		}
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
