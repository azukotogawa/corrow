package com.kjerne.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileHandler {

	public List projList = new ArrayList();
	public List projCodeList = new ArrayList();
	public Iterator<Object> it;
	
	public ProjectileHandler(){
		it = projList.iterator();
	}
	
	public void addCode(int inte){
		projCodeList.add(inte);
	}
	
	public void addObject(Projectile proj){
		projList.add(proj);
	}
	
	public void removeCode(int inte){
		projCodeList.remove(inte);
	}
	
	public void removeObject(Projectile proj){
		projList.remove(proj);
	}
	
	public Projectile getProj(int code){
		return (Projectile) projList.get(code);
	}
	
	public int projLength(){
		return projList.size();
	}
	public int codeLength(){
		return projCodeList.size();
	}
	
}
