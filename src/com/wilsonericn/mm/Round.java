package com.wilsonericn.mm;

import java.util.List;

public class Round {

	private List<Peg> guess;
	private Clue clue;
	public Round(List<Peg> guess, Clue clue) {
		this.guess = guess;
		this.clue = clue;
	}
	
	public List<Peg> getGuess() {
		return guess;
	}
	public void setGuess(List<Peg> guess) {
		this.guess = guess;
	}
	public Clue getClue() {
		return clue;
	}
	public void setClue(Clue clue) {
		this.clue = clue;
	}
	
}
