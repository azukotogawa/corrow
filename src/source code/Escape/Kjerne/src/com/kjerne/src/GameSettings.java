package com.kjerne.src;

import org.lwjgl.glfw.GLFW.*;

public class GameSettings {
	
	public KeyBinding keyConsle = new KeyBinding("key.consle", 41);
	public KeyBinding keyUp = new KeyBinding("key.up", 17);
	public KeyBinding keyLeft = new KeyBinding("key.left", 30);
	public KeyBinding keyDown = new KeyBinding("key.down", 31);
	public KeyBinding keyRight = new KeyBinding("key.right",32);
	public KeyBinding keyChat = new KeyBinding("key.chat", 28);
	public KeyBinding[] keyBindings;
	
	public GameSettings(){
		this.keyBindings = new KeyBinding[] {this.keyConsle, this.keyUp, this.keyLeft, this.keyDown, this.keyRight, this.keyChat};
	}
	
	/*
	public static boolean isKeyDown(KeyBinding par0KeyBinding)
    {
        return par0KeyBinding.keyCode < 0 ? Mouse.isButtonDown(par0KeyBinding.keyCode + 100) : Keyboard.isKeyDown(par0KeyBinding.keyCode);
    }
    */
}
