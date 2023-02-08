package com.kjerne.src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler {
	@SuppressWarnings("rawtypes")
	public HashMap keyList = new HashMap();
	@SuppressWarnings("rawtypes")
	private Set keySet = new HashSet();
	
	public KeyHandler(){
	}
	
	@SuppressWarnings("unchecked")
	public void addKey(int arg1KeyCode, Object arg2Obj){
		this.keySet.add(Integer.valueOf(arg1KeyCode));
		keyList.put(arg1KeyCode, arg2Obj);
	}
	
	public void removeObject(Object arg1Obj){
		keyList.remove(arg1Obj);
	}
	
	public KeyBinding getKey(int code){
		return (KeyBinding) keyList.get(code);
	}
	
	public int keyLength(){
		return keyList.size();
	}
}
