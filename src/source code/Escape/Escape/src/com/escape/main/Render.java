package com.escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.Arrays;

public class Render {
	
	private int X = 0;
	private int Y = 0;
	
	public final int height;
	public final int width;
	// pixels[row][col][Entity 0=No 1=Yes]
	public final int[][][] pixels;
	public final int gridCellWidth;
	public final int gridCellHeight;      	
	//Assigns the final values
	public Render(int width, int height, int gridCellWidth, int gridCellHeight){
		this.width = width;
		this.height = height;
		this.gridCellWidth = gridCellWidth;
		this.gridCellHeight = gridCellHeight;
		pixels = new int[width + 5][height + 5][2];
	}
	
	//Creates Entities (Enemies / Characters / Friendlies / Traps / Doors)
	public void createEntities(int characterID){
	      //IT GOES BY FIVES!!		
        //this.pixels[5][5][1] = 1;
		int LocX = Player.getLocationX();
		int LocY = Player.getLocationY();
		this.pixels[LocX][LocY][1] = Character.Knight.characterNumber;
	}
	
	public void updateEntites(Graphics gfx, int nX, int nY, int entityNumber){
		if(entityNumber == Character.Knight.characterNumber){
		//gfx.setColor(Color.lightGray);
        //gfx.fillRect(nX, nY, 5, 15);
        //gfx.fillRect(nX, nY - 10, 5, 5);
        //gfx.fillRect(nX + 10, nY, 5, 15);
        //gfx.fillRect(nX + 10, nY - 10, 5, 5);
        gfx.setColor(Color.red);
        //gfx.fillRect(nX + 5, nY - 10, 5, 25);
        //gfx.fillRect(nX, nY - 5, 15, 5);
        gfx.fillRect(nX, nY, 5, 5);
		}
	}
	
	public void refresh(Graphics gfx, BufferStrategy bs){
		gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 2000, 2000);
        bs.show();
	}
	
	public void moveScreen(Render render, Graphics gfx, BufferStrategy bs, int direction){
		int xOffset = 0;
		int yOffset = 0;
		
		if(direction == 1){
			System.out.println("asd");
			xOffset = -5;
			yOffset = 0;
		}
		if(direction == 2){
			System.out.println("asd");
			xOffset = 0;
			yOffset = 5;
		}
		if(direction == 3){
			System.out.println("asd3");
			xOffset = 5;
			yOffset = 0;
		}
		if(direction == 4){
			System.out.println("asd");
			xOffset = 0;
			yOffset = -5;
		}	
		
		createCells(xOffset, yOffset);
		
	}
	
	public void resetTiles(Graphics gfx, BufferStrategy bs, int nX, int nY){
		int blockID = this.pixels[nX][nY][0];
		
		if (blockID == Block.Void.blockID) {
				gfx.setColor(Color.black);
         gfx.fillRect(nX, nY, gridCellWidth, gridCellHeight);
			}
		if (blockID == Block.Stone.blockID) {
			gfx.setColor(Color.gray);
     gfx.fillRect(nX, nY, gridCellWidth, gridCellHeight);
		}
		
		bs.show();
	}
	
	public void drawEntities(Render render, Graphics gfx, BufferStrategy bs){
		while(X < 2000 && Y < 2000){
			
			if(X != Player.getLocationX()){
				if(X != 2000){
				X+=gridCellHeight;
				}
			}
			
			
		if(this.pixels[X][Y][1] == Character.Knight.characterNumber){	
        		gfx.setColor(Color.lightGray);
                gfx.fillRect(X, Y, 5, 15);
                gfx.fillRect(X, Y - 5, 5, 5);
        }
		Y+=gridCellHeight;
		}
	}
	
	//Creates the "pixels" (which are 5 pixels wide)
	public void createCells(int xOffset, int yOffset){
		 //creates the void
		 int VoidX = xOffset;
	        while (VoidX < width) {
	            int VoidY = yOffset;
	            while (VoidY < height) {
	            	if(this.pixels[VoidX][VoidY][1] != Character.Knight.characterNumber){
	                this.pixels[VoidX][VoidY][0] = Block.Void.blockID;
	                VoidY+=gridCellHeight;
	            	}
	            }
	            VoidX+=gridCellWidth;
	        }
	        
	        //creates the stone
	        int StoneX = xOffset;
	        while(StoneX < 1001){
	        	int StoneY = yOffset;
	        	while(StoneY < 1001){
	        		if(this.pixels[StoneX][StoneY][1] != Character.Knight.characterNumber){
	        		this.pixels[StoneX][StoneY][0] = Block.Stone.blockID;
	        		StoneY+=gridCellHeight;
	        		}
	        	}
	        	StoneX+=gridCellWidth;
	        }
		}
	
	//Deletes the "pixels"
	public void unRender(Render render){
		Arrays.fill(pixels, null);
	}
	
	//Test, draws the "pixels"
	public void drawCells(Render render, Graphics gfx, BufferStrategy bs, int yOffset, int xOffset){
		gfx = bs.getDrawGraphics();
		
		int row = 0;
		int col = 0;
		
		if(col != Player.getLocationX() && row != Player.getLocationY()){
		
		if(row <= width && col <= height){

		for(row = 0; row < width; row+=gridCellWidth){
				  int x = xOffset + col;
                  int y = yOffset + row;
                  
			for(col = 0; col < height; col+=gridCellHeight){
					  x = xOffset + col;
	                     y = yOffset + row;
	                     
	                     
	                     //draws the void
	                     if(row >= 0 && row <= width){
	                     if(col >= 0 && col <= height){
	     				if (this.pixels[row][col][0] == Block.Void.blockID) {
	     					//change == to != for coolness
	     					if(this.pixels[row][col][1] != Character.Knight.characterNumber){
	     					gfx.setColor(Color.black);
	                     gfx.fillRect(x, y, gridCellWidth, gridCellHeight);
	     				}
	                     }
	                     }
				}
	                     //IT GOES BY FIVES!!		
	                    //this.pixels[5][5][0] = 1;
	                     //draws the stone
	     				if (this.pixels[row][col][0] == Block.Stone.blockID) {
	     					//change == to != for coolness
	     					if(this.pixels[row][col][1] != Character.Knight.characterNumber){
	     					gfx.setColor(Color.gray);
	                     gfx.fillRect(x, y, gridCellWidth, gridCellHeight);
				}
	     				}
			}
		}
			}
		
		if(row == 2000 & col == 2000){
			row = 0;
			col = 0;
			drawCells(render, gfx, bs, 0, 0);
		}
		
		bs.show();
		}
	}
	}
	
