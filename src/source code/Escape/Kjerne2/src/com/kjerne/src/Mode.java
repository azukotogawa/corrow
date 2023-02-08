package com.kjerne.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mode {

	//Mode Chages Map List!!!
	
	@SuppressWarnings("rawtypes")
	public List modeList = new ArrayList();
	public Iterator<Object> it;
	public String modeName;
	public int numMode;
	public int modeNum = 1;
	
	@SuppressWarnings("unchecked")
	public Mode(int i){
		numMode = i;
		it = modeList.iterator();
		
		String map = "Some Stuff";
		modeList.add(map);
		
		map = "Defense Maybe?";
		modeList.add(map);
		
		map = "I dunno running? ?";
		modeList.add(map);
		
		modeName = (String) modeList.get(1);
	}
	
	public String nextMode(){
		modeNum++;
		if(modeNum > numMode){
			modeNum = 1;
		}
		modeName = (String) modeList.get(modeNum - 1);
		return modeName;
	}
	
}
