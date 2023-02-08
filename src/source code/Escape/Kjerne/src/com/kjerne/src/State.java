package com.kjerne.src;

public interface State {
	
	public int getID();
	
	public void init();
	
	public void render();
	
	public void update(int delta);
}
