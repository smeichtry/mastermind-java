package com.wilsonericn.mm;

import java.util.ArrayList;
import java.util.List;

import com.wilsonericn.cli.Cli;
import com.wilsonericn.players.Decoder;
import com.wilsonericn.players.Encoder;
import com.wilsonericn.players.HumanPlayer;
import com.wilsonericn.players.Solver;

public class Game {

	private Cli cli = new Cli();
	private Encoder encoder;
	private Decoder decoder;
	
	public void playGame(int size) {
		List<Peg> secret = encoder.chooseSecret(size);
		List<Round> rounds = new ArrayList<Round>();
		while(notSolved(rounds, size)) {
			List<Peg> guess = decoder.requestGuess(rounds, size);
			Clue clue = Board.evaluate(guess, secret);
			Round round = new Round(guess, clue);
			rounds.add(round);
		} 
		cli.showBoard(rounds);
		System.out.println();
	}

	private boolean notSolved(List<Round> rounds, int size) {
		if (rounds.isEmpty()) {
			return true;
		}
		Round last = rounds.get(rounds.size() - 1);
		Clue clue = last.getClue();
		return clue.getExact() != size;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setDecoder(new Solver());
		game.setEncoder(new HumanPlayer());
		game.playGame(4);
	}

	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}

	public void setDecoder(Decoder decoder) {
		this.decoder = decoder;
	}
}
