package com.kjerne.src;

//possible speed increase: use load as with loadScreen();

public class CharList {

	//characters usable by font and keyboard mapping (make exception for: ctrl, shift, enter, backspace, capslock, esc, and possibly alt)
	private char[] chars = new char[256];
	
	public CharList(){
		
		//lowercase
		chars[(int)'a'] = 1;
		chars[(int)'b'] = 2;
		chars[(int)'c'] = 3;
		chars[(int)'d'] = 4;
		chars[(int)'e'] = 5;
		chars[(int)'f'] = 6;
		chars[(int)'g'] = 7;
		chars[(int)'h'] = 8;
		chars[(int)'i'] = 9;
		chars[(int)'j'] = 10;
		chars[(int)'k'] = 11;
		chars[(int)'l'] = 12;
		chars[(int)'m'] = 13; //face
		chars[(int)'n'] = 14; //face
		chars[(int)'o'] = 15; //face
		chars[(int)'p'] = 16;
		chars[(int)'q'] = 17;
		chars[(int)'r'] = 18; //face
		chars[(int)'z'] = 19;
		chars[(int)'t'] = 20;
		chars[(int)'u'] = 21;
		chars[(int)'v'] = 22; //face
		chars[(int)'w'] = 23; //face
		chars[(int)'x'] = 24;
		chars[(int)'y'] = 25;
		chars[(int)'z'] = 26;
		
		//uppercase
		chars[(int)'A'] = 27;
		chars[(int)'B'] = 28;
		chars[(int)'C'] = 29; //face
		chars[(int)'D'] = 30; 
		chars[(int)'E'] = 31;
		chars[(int)'F'] = 32;
		chars[(int)'G'] = 33;
		chars[(int)'H'] = 34;
		chars[(int)'I'] = 35;
		chars[(int)'J'] = 36;
		chars[(int)'K'] = 37;
		chars[(int)'L'] = 38;
		chars[(int)'M'] = 39;
		chars[(int)'N'] = 40;
		chars[(int)'O'] = 41; //face
		chars[(int)'P'] = 42;
		chars[(int)'Q'] = 43;
		chars[(int)'R'] = 44;
		chars[(int)'S'] = 45;
		chars[(int)'T'] = 46; 
		chars[(int)'U'] = 47;
		chars[(int)'V'] = 48;
		chars[(int)'W'] = 49;
		chars[(int)'X'] = 50;
		chars[(int)'Y'] = 51;
		chars[(int)'Z'] = 52;
		
		//numbers
		chars[(int)'1'] = 53;
		chars[(int)'2'] = 54;
		chars[(int)'3'] = 55;
		chars[(int)'4'] = 56;
		chars[(int)'5'] = 57;
		chars[(int)'6'] = 58;
		chars[(int)'7'] = 59;
		chars[(int)'8'] = 60;
		chars[(int)'9'] = 61;
		chars[(int)'0'] = 62; //face
		
		//symbols
		chars[(int)'`'] = 63;
		chars[(int)'-'] = 64; //face
		chars[(int)'='] = 65; //face
		
		chars[(int)','] = 66; //face
		chars[(int)'.'] = 67; //face
		chars[(int)'/'] = 68;
		
		chars[(int)';'] = 69; //oooooo randy ;]
		chars[(int)'\''] = 70; //you're special (') single apostrophe
		
		chars[(int)'['] = 71;
		chars[(int)']'] = 72;
		chars[(int)'\\'] = 73; //aye, a friend have we (\) backslash
		
		//not usable by keyboard mapping (w/o shift)
		chars[(int)'~'] = 74; //face
		chars[(int)'!'] = 75;
		chars[(int)'@'] = 76; 
		chars[(int)'#'] = 77;
		chars[(int)'$'] = 78;
		chars[(int)'%'] = 79;
		chars[(int)'^'] = 80; //face
		chars[(int)'&'] = 81;
		chars[(int)'*'] = 82; //face
		chars[(int)'('] = 83; //face
		chars[(int)')'] = 84; //face
		chars[(int)'_'] = 85; //face
		chars[(int)'+'] = 86; 
		
		chars[(int)'<'] = 87; //face
		chars[(int)'>'] = 88; //face
		chars[(int)'?'] = 89; //cool face
		
		chars[(int)':'] = 90;
		chars[(int)'"'] = 91;
		
		chars[(int)'{'] = 92; 
		chars[(int)'}'] = 93;
		chars[(int)'|'] = 94;
		
	}
	
	public int getID(String character){
		return chars[(int)character.charAt(0)];
	}
	
	public String getCharacterString(int id){
		return chars.toString().substring(id, id++);
	}
	
	public char getCharacter(int id){
		return chars.toString().charAt(id);
	}
	
	public int getLength(){
		return chars.length;
	}
	
}
