//Nothing really actually works. (Not really my fault)

package com.kjerne.src;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame{

	public static int screenX, screenY;
	public static AppGameContainer frame;
	public static boolean showFps, isFullscreen, vSync;
	public static boolean nButtonClicked, buttonClicked;
	private static FileLoad fileLoad;
	public String gameName = "Kjerne";
	private int MainMenu = 0, MultiplayerMenu = 1, GameModeMenu = 2, OptionsMenu = 3, Playing = 4, PauseMenu = 5;
	
	public Main(String gameName) {
		super(gameName);
		this.addState(new MainMenu(MainMenu));
		this.addState(new MultiplayerMenu(MultiplayerMenu));
		this.addState(new GameModeMenu(GameModeMenu));
		this.addState(new OptionsMenu(OptionsMenu));
		this.addState(new Playing(Playing));
		this.addState(new PauseMenu(PauseMenu));
	}
	
	public void initStatesList(GameContainer paramGameContainer) throws SlickException {
		this.getState(MainMenu).init(paramGameContainer, this);
		this.getState(MultiplayerMenu).init(paramGameContainer, this);
		this.getState(GameModeMenu).init(paramGameContainer, this);
		this.getState(OptionsMenu).init(paramGameContainer, this);
		this.getState(Playing).init(paramGameContainer, this);
		this.getState(PauseMenu).init(paramGameContainer, this);
		this.enterState(MainMenu);
	}
	
	public static void main(String[] args){
		loadScreenCfg();
		try {
			frame = new AppGameContainer(new Main("Kjerne"));
			frame.setDisplayMode(screenX, screenY, isFullscreen);
			frame.setVSync(vSync);
			frame.setShowFPS(showFps);
			frame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
		showFps = Boolean.parseBoolean(info);
		
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