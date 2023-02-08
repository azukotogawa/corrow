package com.kjerne.src;

import java.util.concurrent.TimeUnit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile {
	private String character;
	private String element;
	private Image proj;
	private int x=210, y=240, traj, speed;
	private boolean inFlight;
	private Graphics g;
	public Projectile(String character, String element) throws SlickException{
		this.character = character;
		this.element = element;
		String temp = "Mage.ani";
		proj = new Image(temp);
	}
	
	public void launch(int traj, int speed, Graphics g){
		this.traj = traj;
		this.speed = speed;
		this.g = g;
		inFlight = true;
		if(character == "Mage"){
			try {
				proj = new Image(character + ".ani");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			if(element == "Fireball"){
				proj = proj.getSubImage(22, 0, 11, 12);
				g.drawImage(proj, 210, 240);
				update();
			}
		}
	}
	
	public void hit(){
		inFlight = false;
	}
	
	public void tick(){
		int i=0;
		for(;i>=26;i++){
			if(i>=25){
				System.out.println(i);
				i = 0;
			}
		}
		
		}

	public void draw(){
		g.drawImage(proj, x, y);
	}
	
	public void update(){
		if(traj == 1){
			y--;
		}
		if(traj == 2){
			x--;
		}
		if(traj == 3){
			y++;
		}
		if(traj == 4){
			x++;
		}	
		g.drawImage(proj, x, y);
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
