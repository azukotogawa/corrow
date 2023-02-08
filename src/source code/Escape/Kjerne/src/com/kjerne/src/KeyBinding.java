package com.kjerne.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KeyBinding {
	
	public static List keybindArray = new ArrayList();
	public static KeyHandler hash = new KeyHandler();
	public String keyName;
	public int keyCode;
	public boolean pressed;
	
		public boolean isKeyPressed(){
			return pressed;
		}
	
	  public static void setKeyState(int arg1keyCode, boolean arg2pressed) {
	        KeyBinding var2 = (KeyBinding)hash.getKey(arg1keyCode);
	    
	        if (var2 != null) {
	            var2.pressed = arg2pressed;
	        }
	    }
	public static void unpressAllKeys(){
		Iterator it = keybindArray.iterator();
		while(it.hasNext()){
			KeyBinding var1 = (KeyBinding)it.next();
			var1.upressKey();
		}
	}
	public void upressKey(){
		this.pressed = false;
	}
	public KeyBinding(String par1KeyName, int par2KeyCode){
		this.keyName = par1KeyName;
		this.keyCode = par2KeyCode;
		keybindArray.add(this);
		hash.addKey(par2KeyCode, this);
	}
}
