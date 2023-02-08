package com.escape.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FontCreation {
	
	public static Font font;
	public InputStream is;

	public FontCreation(){
		try {
			is = new FileInputStream("C:/Users/Cole/Desktop/Escape Data/Images/Font.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
     
	}
}
