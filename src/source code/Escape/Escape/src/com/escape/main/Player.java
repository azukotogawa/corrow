package com.escape.main;

public class Player{
	
	private static int LocationX = 300;
	private static int LocationY = 250;

	public static int setLocationX(int locationX){
		LocationX = locationX;
		return locationX;
	}
	
	public static int setLocationY(int locationY){
		LocationY = locationY;
		return LocationY;
	}
	
	public static int getLocationX(){
		return LocationX;
	}
	public static int getLocationY(){
		return LocationY;
	}
}
