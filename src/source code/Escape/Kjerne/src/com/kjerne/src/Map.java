package com.kjerne.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {

	@SuppressWarnings("rawtypes")
	public List mapList = new ArrayList();
	public Iterator<Object> it;
	public String mapName;
	public int numMap;
	public int mapNum = 1;
	
	@SuppressWarnings("unchecked")
	public Map(int i){
		numMap = i;
		it = mapList.iterator();
		
		String map = "Gardens";
		mapList.add(map);
		
		map = "Hallway";
		mapList.add(map);
		
		map = "Test";
		mapList.add(map);
		
		mapName = (String) mapList.get(1);
	}
	
	public String nextMap(){
		mapNum++;
		if(mapNum > numMap){
			mapNum = 1;
		}
		mapName = (String) mapList.get(mapNum - 1);
		return mapName;
	}
		
}
