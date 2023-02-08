package com.escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Menu{
	
	public void startMenu(Render render, Graphics gfx, BufferStrategy bs){
		gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 2000, 2000);
        
    	gfx.setColor(Color.darkGray);
		gfx.fillRect(175, 25, 250, 315);
        
        gfx.setColor(Color.white);
        
      //gfx.fillRect(x, y, width, height)
        
        //PLAY
        //top
        gfx.fillRect(205, 50, 190, 5);
        //left
        gfx.fillRect(200, 55, 5, 45);
        //bottom
        gfx.fillRect(205, 100, 190, 5);
        //right
        gfx.fillRect(395, 55, 5, 45);
        
        //OPTIONS
        //top
        gfx.fillRect(205, 150, 190, 5);
        //left
        gfx.fillRect(200, 155, 5, 45);
        //bottom
        gfx.fillRect(205, 200, 190, 5);
        //right
        gfx.fillRect(395, 155, 5, 45);
        
        //EXIT
        //top
        gfx.fillRect(205, 250, 190, 5);
        //left
        gfx.fillRect(200, 255, 5, 45);
        //bottom
        gfx.fillRect(205, 300, 190, 5);
        //right
        gfx.fillRect(395, 255, 5, 45);
        
        gfx.setColor(Color.orange);
        //PLAY
        gfx.fillRect(250, 60, 5, 35);
        gfx.fillRect(250, 60, 15, 5);
        gfx.fillRect(265, 60, 5, 15);
        gfx.fillRect(250, 75, 20, 5);
        
        gfx.fillRect(275, 60, 5, 35);
        gfx.fillRect(275, 90, 20, 5);
        
        gfx.fillRect(300, 60, 5, 35);
        gfx.fillRect(315, 60, 5, 35);
        gfx.fillRect(300, 60, 20, 5);
        gfx.fillRect(300, 75, 20, 5);
        
        gfx.fillRect(325, 60, 5, 15);
        gfx.fillRect(345, 60, 5, 15);
        gfx.fillRect(325, 70, 25, 5);
        gfx.fillRect(335, 75, 5, 20);
        //gfx.fillRect(x, y, width, height)
        
        //OPTIONS
        gfx.fillRect(210, 160, 5, 35);
        gfx.fillRect(225, 160, 5, 35);
        gfx.fillRect(210, 160, 20, 5);
        gfx.fillRect(210, 190, 20, 5);
        
        gfx.fillRect(235, 160, 5, 35);
        gfx.fillRect(235, 160, 15, 5);
        gfx.fillRect(250, 160, 5, 15);
        gfx.fillRect(235, 175, 20, 5);
        
        gfx.fillRect(260, 160, 25, 5);
        gfx.fillRect(270, 160, 5, 35);
        
        gfx.fillRect(290, 160, 25, 5);
        gfx.fillRect(290, 190, 25, 5);
        gfx.fillRect(300, 160, 5, 35);
        
        gfx.fillRect(320, 160, 5, 35);
        gfx.fillRect(335, 160, 5, 35);
        gfx.fillRect(320, 160, 20, 5);
        gfx.fillRect(320, 190, 20, 5);
        
        gfx.fillRect(345, 160, 5, 35);
        gfx.fillRect(360, 160, 5, 35);
        gfx.fillRect(350, 165, 5, 5);
        gfx.fillRect(355, 170, 5, 5);
        
        gfx.fillRect(370, 160, 20, 5);
        gfx.fillRect(370, 160, 5, 15);
        gfx.fillRect(370, 175, 20, 5);
        gfx.fillRect(385, 175, 5, 20);
        gfx.fillRect(370, 190, 20, 5);
        
        //EXIT
        gfx.fillRect(250, 260, 5, 35);
        gfx.fillRect(250, 260, 20, 5);
        gfx.fillRect(250, 275, 20, 5);
        gfx.fillRect(250, 290, 20, 5);
        gfx.fillRect(275, 260, 5, 5);
        gfx.fillRect(275, 265, 5, 5);
        gfx.fillRect(275, 270, 5, 5);
        gfx.fillRect(290, 285, 5, 5);
        gfx.fillRect(290, 290, 5, 5);
        gfx.fillRect(290, 260, 5, 5);
        gfx.fillRect(290, 265, 5, 5);
        gfx.fillRect(290, 270, 5, 5);
        gfx.fillRect(290, 280, 5, 5);
        gfx.fillRect(275, 280, 5, 5);
        gfx.fillRect(275, 285, 5, 5);
        gfx.fillRect(275, 290, 5, 5);
        gfx.fillRect(280, 275, 10, 5);
        
        gfx.fillRect(300, 260, 15, 5);
        gfx.fillRect(300, 290, 15, 5);
        gfx.fillRect(305, 260, 5, 35);
        
        gfx.fillRect(320, 260, 25, 5);
        gfx.fillRect(330, 260, 5, 35);
        
	}
	
	public void Options(Render render, Graphics gfx, BufferStrategy bs){
		gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 2000, 2000);
        Main.inGame = false;
        bs.show();
	}
	
	public void joinGameMenu(Render render, Graphics gfx, BufferStrategy bs){
		gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 2000, 2000);
        //temp
        Main.inGame = true;
        bs.show();
	}
	
	public void Pause(Render render, Graphics gfx, BufferStrategy bs){
		gfx.setColor(Color.darkGray);
		gfx.fillRect(175, 25, 250, 315);
		
		gfx.setColor(Color.white);
		//RESUME
        gfx.fillRect(205, 50, 190, 5);
        gfx.fillRect(200, 55, 5, 45);
        gfx.fillRect(205, 100, 190, 5);
        gfx.fillRect(395, 55, 5, 45);
        
        //OPTIONS
        gfx.fillRect(205, 150, 190, 5);
        gfx.fillRect(200, 155, 5, 45);
        gfx.fillRect(205, 200, 190, 5);
        gfx.fillRect(395, 155, 5, 45);
        
        //LEAVE
        gfx.fillRect(205, 250, 190, 5);
        gfx.fillRect(200, 255, 5, 45);
        gfx.fillRect(205, 300, 190, 5);
        gfx.fillRect(395, 255, 5, 45);
        
        gfx.setColor(Color.orange);
        
        //RESUME
        gfx.fillRect(225, 60, 5, 35);
        gfx.fillRect(225, 60, 20, 5);
        gfx.fillRect(240, 60, 5, 15);
        gfx.fillRect(240, 80, 5, 15);
        gfx.fillRect(235, 75, 5, 5);
        
        gfx.fillRect(250, 60, 5, 35);
        gfx.fillRect(250, 75, 15, 5);
        gfx.fillRect(250, 60, 20, 5);
        gfx.fillRect(250, 90, 20, 5);
        
        gfx.fillRect(275, 60, 20, 5);
        gfx.fillRect(275, 60, 5, 15);
        gfx.fillRect(275, 75, 20, 5);
        gfx.fillRect(290, 75, 5, 20);
        gfx.fillRect(275, 90, 20, 5);
        
        gfx.fillRect(300, 60, 5, 35);
        gfx.fillRect(315, 60, 5, 35);
        gfx.fillRect(300, 90, 20, 5);
        
        gfx.fillRect(325, 60, 5, 35);
        gfx.fillRect(345, 60, 5, 35);
        gfx.fillRect(330, 65, 5, 5);
        gfx.fillRect(335, 70, 5, 5);
        gfx.fillRect(340, 65, 5, 5);
        
        gfx.fillRect(355, 60, 5, 35);
        gfx.fillRect(355, 75, 15, 5);
        gfx.fillRect(355, 60, 20, 5);
        gfx.fillRect(355, 90, 20, 5);
        
        //OPTIONS
        gfx.fillRect(210, 160, 5, 35);
        gfx.fillRect(225, 160, 5, 35);
        gfx.fillRect(210, 160, 20, 5);
        gfx.fillRect(210, 190, 20, 5);
        
        gfx.fillRect(235, 160, 5, 35);
        gfx.fillRect(235, 160, 15, 5);
        gfx.fillRect(250, 160, 5, 15);
        gfx.fillRect(235, 175, 20, 5);
        
        gfx.fillRect(260, 160, 25, 5);
        gfx.fillRect(270, 160, 5, 35);
        
        gfx.fillRect(290, 160, 25, 5);
        gfx.fillRect(290, 190, 25, 5);
        gfx.fillRect(300, 160, 5, 35);
        
        gfx.fillRect(320, 160, 5, 35);
        gfx.fillRect(335, 160, 5, 35);
        gfx.fillRect(320, 160, 20, 5);
        gfx.fillRect(320, 190, 20, 5);
        
        gfx.fillRect(345, 160, 5, 35);
        gfx.fillRect(360, 160, 5, 35);
        gfx.fillRect(350, 165, 5, 5);
        gfx.fillRect(355, 170, 5, 5);
        
        gfx.fillRect(370, 160, 20, 5);
        gfx.fillRect(370, 160, 5, 15);
        gfx.fillRect(370, 175, 20, 5);
        gfx.fillRect(385, 175, 5, 20);
        gfx.fillRect(370, 190, 20, 5);
        
        //LEAVE
        gfx.fillRect(235, 260, 5, 35);
        gfx.fillRect(235, 290, 20, 5);
        
        gfx.fillRect(260, 260, 5, 35);
        gfx.fillRect(260, 260, 20, 5);
        gfx.fillRect(260, 275, 15, 5);
        gfx.fillRect(260, 290, 20, 5);
        
        gfx.fillRect(285, 260, 5, 35);
        gfx.fillRect(300, 260, 5, 35);
        gfx.fillRect(285, 260, 15, 5);
        gfx.fillRect(285, 275, 15, 5);
        
        gfx.fillRect(310, 260, 5, 15);
        gfx.fillRect(315, 275, 5, 15);
        gfx.fillRect(320, 290, 5, 6);
        gfx.fillRect(325, 275, 5, 15);
        gfx.fillRect(330, 260, 5, 15);
        
        gfx.fillRect(340, 260, 5, 35);
        gfx.fillRect(340, 260, 20, 5);
        gfx.fillRect(340, 275, 15, 5);
        gfx.fillRect(340, 290, 20, 5);
        
        bs.show();
        
	}

	
	
}
