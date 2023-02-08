package com.escape.main;

public class Material {
	
	private boolean isMaterial;
	
	public static final Material VOID = new Material().setImmaterial();
	public static final Material STONE = new Material().setMaterial();
	
	protected Material setImmaterial(){
		isMaterial = false;
		return this;
	}
	
	protected Material setMaterial(){
		isMaterial = true;
		return this;
	}
	
	public boolean getIsMaterial(){
		return isMaterial;
	}
}
