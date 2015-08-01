package com.wilsonericn.players;

import java.util.List;

import com.wilsonericn.cli.Cli;
import com.wilsonericn.mm.Peg;
import com.wilsonericn.mm.Round;

public class HumanPlayer implements Encoder, Decoder {

	private Cli cli = new Cli();
	
	@Override
	public List<Peg> chooseSecret(int size) {
		return cli.requestCode();
	}

	@Override
	public List<Peg> requestGuess(List<Round> rounds, int size) {
		cli.showBoard(rounds);
		return cli.requestCode();
	}

}
