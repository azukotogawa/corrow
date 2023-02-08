//Nothing really actually works. (Not really my fault)

package com.kjerne.src;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
 


import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main{

	public static Window windowS;
	
	public static int screenX=600, screenY=500, vSync=1;
	public static final String gameName = "Kjerne";
	
	public GameSettings gameSettings;
	
	public static boolean isFullscreen, showFps;
	public static boolean nButtonClicked, buttonClicked;
	private static FileLoad fileLoad;
	
	public static int state=0;
	static StateHandler sh = new StateHandler();
	
	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback   keyCallback;
	 
	public static long window;
	
	public Main(){
	}
	
	private void run(){
		loadScreenCfg();
		windowS = new Window(gameName, screenX, screenY, vSync, isFullscreen, showFps);
		
		try {
	         init();
	         loop();
	         
	         glfwDestroyWindow(window);
	         keyCallback.release();
	    }finally{
	         glfwTerminate();
	         errorCallback.release();
	    }
	}
	
	private void init() {
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
 
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
 
        glfwDefaultWindowHints(); 
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        
        window = glfwCreateWindow(screenX, screenY, gameName, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
 
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GL_TRUE); 
            }
        });
 
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
            window,
            (GLFWvidmode.width(vidmode) - screenX) / 2,
            (GLFWvidmode.height(vidmode) - screenY) / 2
        );
 
        glfwMakeContextCurrent(window);
        glfwSwapInterval(vSync);
        glfwShowWindow(window);
        
        state++;
        statesCreation();
        State current = sh.getState(1);
        current.init();
	}
	
	public void statesCreation(){
		sh.addState(1, new MainMenu(1));
		sh.addState(2, new OptionsMenu(2));
		sh.addState(3, new GameModeMenu(3));
		sh.addState(4, new MultiplayerMenu(4));
		sh.addState(99, new Playing(99));
	}
	
	  private void loop() {
	        GLContext.createFromCurrent();

	        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
	            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	 
	            glfwSwapBuffers(window);
	 
	            glfwPollEvents();
	        }
	    }
	
	  public static int getState(){
		  return state;
	  }
	  
	  public static StateHandler getStateHandler(){
		  return sh;
	  }
	  
	public static void main(String[] args) { 
		new Main().run();
	}
	
	public static void loadScreenCfg(){
		fileLoad = new FileLoad("screen.cfg");
		
		fileLoad.breakInformation();
		String info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		screenX = Integer.parseInt(info);
		
		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		screenY = Integer.parseInt(info);

		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		showFps = Boolean.parseBoolean(info);

		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		isFullscreen = Boolean.parseBoolean(info);

		fileLoad.breakInformation();
		info = fileLoad.getBrokenInformation();
		info = fileLoad.purifyInformation(info);
		vSync = Integer.parseInt(info);
	}
}