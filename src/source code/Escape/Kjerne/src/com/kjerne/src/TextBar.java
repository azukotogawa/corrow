package com.kjerne.src;

import org.newdawn.slick.Graphics;

public class TextBar extends Bar{
	private String text;
	private Graphics g;
	public TextBar(int x, int y, int base, int height, Graphics g){
		super(x, y, base, height, g);
		this.g = g;
	}
	
	public void createTextBar(String text){
		g.drawString(text, 100, 100);
	}
	
	public String getText(){
		return text;
	}
}
