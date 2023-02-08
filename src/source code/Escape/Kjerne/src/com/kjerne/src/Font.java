package com.kjerne.src;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.*;

public class Font {

	private CharList cl = new CharList();
	
	private int widthPerChar, heightPerChar;
	
	private Map<Character, BufferedImage> fontList = new HashMap<Character, BufferedImage>();
	
	public Font(Window window){
		int x=0;
		int y=0;
		for(int index=1; index<cl.getLength(); index++){
			VisualDriver vs = new VisualDriver(window, "insert path to png", x, y, widthPerChar, heightPerChar);
			fontList.put(cl.getCharacter(index), vs.getBufferedImage());
			
			x+=widthPerChar;
			y+=heightPerChar;
		}
	}

	public void drawString(int x, int y, String s){
	    glPushMatrix();
	    glTranslatef(x, y, 0);

	    //index chars
	    for(int index = 0; index < s.length(); index++){
	        char c = s.charAt(index);
	        BufferedImage ic = fontList.get(c);
	        
	        int[] pixels = new int[ic.getWidth() * ic.getHeight()];
	        ic.getRGB(0, 0, ic.getWidth(), ic.getHeight(), pixels, 0, ic.getWidth());

	        ByteBuffer buffer = BufferUtils.createByteBuffer(ic.getWidth() * ic.getHeight() * 4);
	        
	        //buffer each char image
	        for(int icY = 0; icY < ic.getHeight(); icY++){
	            for(int icX = 0; icX < ic.getWidth(); icX++){
	                int pixel = pixels[icY * ic.getWidth() + icX];
	                buffer.put((byte) ((pixel >> 16) & 0xFF));
	                buffer.put((byte) ((pixel >> 8) & 0xFF));
	                buffer.put((byte) (pixel & 0xFF));
	                buffer.put((byte) ((pixel >> 24) & 0xFF));
	            }
	        }

	        buffer.flip();

	        int textureID = glGenTextures();
	        glBindTexture(GL_TEXTURE_2D, textureID);
	        
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	        
	        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, ic.getWidth(), ic.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	        
	        //draw
			glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(-1.0f, -1.0f);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(+1.0f, -1.0f);
			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(+1.0f, +1.0f);
			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(-1.0f, +1.0f);
			glEnd();
	        
	    }
	}
	
}
