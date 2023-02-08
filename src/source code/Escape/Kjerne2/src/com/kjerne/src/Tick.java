package com.kjerne.src;

public class Tick {
	private static int tick = 0;
	private boolean newTick;
	public void tick(int milli){
		if(milli == 0 || milli == 50 || milli == 100 || milli == 150 || milli == 200 || milli == 250 ||
				milli == 300 || milli == 350 || milli == 400 || milli == 450 || milli == 500 || milli == 550 
				|| milli == 600 || milli == 650 || milli == 700 || milli == 750 || milli == 800 || milli == 850 
				|| milli == 900 || milli == 950 || milli > 999){
			tick++;
			newTick = true;
		}
		if(tick > 20){
			tick = 0;
		}
	}
	
	public static int returnTick(){
		return tick;
	}
	public void usedTick(){
		newTick = false;
	}
	public boolean tickNew(){
		return newTick;
	}
}
