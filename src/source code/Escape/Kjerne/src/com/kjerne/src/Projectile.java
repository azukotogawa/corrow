package com.kjerne.src;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile {
	private String character;
	private String element;
	private Image proj;
	private int x=210, y=240, traj;
	private Graphics g;
	private int speed=0;
	private int tickrate=0;
	private int arg6=0;
	
	public Projectile(int name, int speed, int startLocX, int startLocY){
		if(speed != 0){
			this.speed = speed;
		}else{
			this.speed = 20;
		}
		tickrate = 1000/speed;
		this.x = startLocX;
		this.y = startLocY;
	}
	
	public void assign(String character, String element) throws SlickException{
		this.character = character;
		this.element = element;
		String temp = "Mage.ani";
		proj = new Image(temp);
	}
	
	public void launch(int traj, Graphics g){
		this.traj = traj;
		this.g = g;
		if(character == "Mage"){
			try {
				proj = new Image(character + ".ani");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			if(element == "Fireball"){
				proj = proj.getSubImage(22, 0, 11, 12);
				g.drawImage(proj, x, y);
			}
		}
	}
	
	public void update(){
		for(int dir=traj; traj<=4;
			
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
	
	public void refresh(){
		g.drawImage(proj, x, y);
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getSpeed(){
		return speed;
	}
	public boolean tickReady(int arg3){
		arg6++;
		if(arg6>=tickrate){
			arg6=0;
			return true;
		}else{
			return false;
		}
	}
}
