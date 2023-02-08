package com.kjerne.src;

import java.util.HashMap;
import java.util.Map;

public class StateHandler {

	public Map<Integer, State> stateList = new HashMap<Integer, State>();
	public int stateID;
	public State state;
	
	public StateHandler(){
	}
	
	public void addState(int ID, State state){
		this.stateID = ID; 
		this.state = state;
		
		stateList.put(ID, state);
	}
	
	public void removeState(int ID){
		stateList.remove(ID);
	}
	
	public State getState(int ID){
		return (State) stateList.get(ID);
	}
		
}
