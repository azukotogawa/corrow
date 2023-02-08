package com.kjerne.src;

import org.newdawn.slick.Graphics;

public class Bar {
	private int x,y,base,height;
	private Graphics g;
	public Bar(int x, int y, int base, int height, Graphics g){
		this.x=x;
		this.y=y;
		this.base=base;
		this.height=height;
		this.g=g;
	}
}
