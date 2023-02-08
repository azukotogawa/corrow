package com.kjerne.src;

import org.lwjgl.glfw.GLFW;

public class Window {

	public static String gameName;
	public static int width;
	public static int height;
	public static int vsync;
	public static boolean isFullscreen;
	public static boolean showFPS;
	
	private int ratioX, ratioY;
	
	public Window(String name, int width, int height, int vsync, boolean isFullscreen, boolean showFPS){
		this.gameName = name;
		this.width = width;
		this.height = height;
		this.vsync = vsync;
		this.isFullscreen = isFullscreen;
		this.showFPS = showFPS;
		
		ratioX = width / 600;
		ratioY = height / 500;
	}
	
	public void setGameName(String name){
		this.gameName = name;
		GLFW.glfwSetWindowTitle(Main.window, gameName);
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public void setVsync(int vsync){
		this.vsync = vsync;
	}
	
	public void setIsFullscreen(boolean isFullscreen){
		this.isFullscreen = isFullscreen;
	}
	
	public void setShowFPS(boolean showFPS){
		this.showFPS = showFPS;
	}
	
	public String getGameName(){
		return gameName;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getVsync(){
		return vsync;
	}
	
	public boolean getIsFullscreen(){
		return isFullscreen;
	}
	
	public boolean getSHowFPS(){
		return showFPS;
	}
	
	public int getRatioX(){
		return ratioX;
	}
	
	public int getRatioY(){
		return ratioY;
	}
	
}
