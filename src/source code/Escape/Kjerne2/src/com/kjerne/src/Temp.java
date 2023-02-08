
package com.kjerne.src;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Temp{
	public static int screenX, screenY;
	public static boolean isResizable, isFullscreen, vSync;
	private static FileLoad fileLoad;
	
	public static void main(String[] args){
		loadScreenCfg();
		try {
			Display.setDisplayMode(new DisplayMode(screenX, screenY));
			Display.setResizable(isResizable);
			Display.setFullscreen(isFullscreen);
			Display.setTitle("Kjerne");
			Display.setVSyncEnabled(vSync);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		while(!Display.isCloseRequested()){
			Display.update();
		}
			Display.destroy();
	}
	
	public static void loadScreenCfg(){
		fileLoad = new FileLoad("screen.cfg");
		
		fileLoad.breakInformation();
		String info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		screenX = Integer.parseInt(info);
		
		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		screenY = Integer.parseInt(info);
		
		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		isResizable = Boolean.parseBoolean(info);
		
		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		isFullscreen = Boolean.parseBoolean(info);
		
		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		vSync = Boolean.parseBoolean(info);
		
	}
}