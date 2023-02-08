package com.escape.main;

public class Character {
	public static final Character[] charactersList = new Character[3];
	public Entity characterEntity;
	 public int characterNumber;
	 public int LocX = 0;
	 public int LocY = 0;
	 
		public static final Character Knight = new EntityKnight(1);
	
		public Character(int characterID, Entity entity){
		
		if(charactersList[characterID] != null){
			System.out.println("Character ID " + characterID + " has already been used!");
		}else{
			charactersList[characterID] = this;
			this.characterEntity = entity;
			this.characterNumber = characterID;
		} 
		
}
		public int getLocationX(){
			LocX = Player.getLocationX();
			return LocX;
		}
}
