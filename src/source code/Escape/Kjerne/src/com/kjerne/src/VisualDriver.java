package com.kjerne.src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.GL11.*;

public class VisualDriver {
	
	private BufferedImage im;
	
	private int textureID;
	
	private static final int BYTES_PER_PIXEL = 4;
	
	public VisualDriver(Window window, String path, int imageX, int imageY, int imageWidth, int imageHeight){
		int ratioX = window.getRatioX();
		int ratioY = window.getRatioY();
		
		GLContext.createFromCurrent();
		glEnable(GL_TEXTURE_2D);
		
		try{
		im = ImageIO.read(new File(path));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		int[] pixels = new int[imageWidth * imageHeight];
		im.getRGB(imageX, imageY, imageWidth, imageHeight, pixels, 0, imageWidth);
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(imageWidth * imageHeight * BYTES_PER_PIXEL);
		
		  for(int y = 0; y < imageHeight; y++){
	            for(int x = 0; x < imageWidth; x++){
	                int pixel = pixels[y * imageWidth + x];
	                buffer.put((byte) ((pixel >> 16) & 0xFF));
	                buffer.put((byte) ((pixel >> 8) & 0xFF));
	                buffer.put((byte) (pixel & 0xFF));
	                buffer.put((byte) ((pixel >> 24) & 0xFF));
	            }
	        }
		  
		  buffer.flip();
		  
		  textureID = glGenTextures(); //Generate texture ID
	      glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID
	        
	      //Setup wrap mode
	      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

	      //Setup texture scaling filtering
	      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		  
		  glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, imageWidth, imageHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		 
		  glViewport(imageX, imageY, imageWidth*ratioX, imageHeight*ratioY); // set viewport

		  glMatrixMode(GL_MODELVIEW);
		  glLoadIdentity();
		  glMatrixMode(GL_PROJECTION);
		  glLoadIdentity();
		  glColor3f(1.0f, 1.0f, 1.0f); // set "white" color
		  glDisable(GL_CULL_FACE); // disable backface culling
		  glDisable(GL_LIGHTING); // disable lighting
		  glDisable(GL_DEPTH_TEST); // disable depth test
	}
	
	public int getTextureID(){
		return textureID;
	}
	
	public void drawImage(int screenX, int screenY){
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
	
	public BufferedImage getBufferedImage(){
		return im;
	}
	
}
