package com.escape.main;

public class Block{

    public static final Block[] blocksList = new Block[2];
    public Material blockMaterial;
    public int blockID;

    public static final Block Void = new BlockVoid(0);
    public static final Block Stone = new BlockStone(1);
    
	public Block(int ID, Material material){
		
		if(blocksList[ID] != null){
			System.out.println("Block ID " + ID + " has already been used!");
		}else{
			blocksList[ID] = this;
			this.blockMaterial = material;
			this.blockID = ID;
		} 
		
	}
	
	
}
