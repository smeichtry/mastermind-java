package com.wilsonericn.mm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum Peg { 
	RED('R'), 
	ORANGE('O'), 
	YELLOW('Y'), 
	GREEN('G'), 
	BLUE('B'), 
	VIOLET('V'); 

	public char c;
	
	private Peg(char c) {
		this.c = c;
	}
	
	public static Map<Character,Peg> charToPeg = new HashMap<Character,Peg>();
	
	static {
		for (Peg peg : Peg.values()) {
			charToPeg.put(peg.c, peg);
		}
	}
	
	private static Random random = new Random();
	
	public static Peg random() {
		Peg[] pegs = Peg.values();
		return pegs[random.nextInt(pegs.length)];
	}
}
