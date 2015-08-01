package com.wilsonericn.players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wilsonericn.mm.Board;
import com.wilsonericn.mm.Peg;
import com.wilsonericn.mm.Round;

public class Solver implements Encoder, Decoder {

	@Override
	public List<Peg> chooseSecret(int size) {
		List<Peg> secret = new ArrayList<Peg>();
		for (int idx = 0; idx < size; idx++) {
			secret.add(Peg.random());
		}
		return secret;
	}

	@Override
	public List<Peg> requestGuess(List<Round> rounds, int size) {
		if (rounds.size() == 0) {
			List<Peg> firstGuessTemplate = Arrays.asList(
					Peg.VIOLET, Peg.VIOLET, Peg.BLUE, Peg.BLUE, Peg.GREEN, Peg.GREEN,
					Peg.YELLOW, Peg.YELLOW, Peg.ORANGE, Peg.ORANGE, Peg.RED, Peg.RED);
			return firstGuessTemplate.subList(0, size);
		} else {
			List<List<Peg>> possible = consistentAll(rounds, size);
			if (possible.size() > 0) {
				return possible.get(0);
			}
		return null;
		}
	}
	
	public List<List<Peg>> consistentAll(List<Round> rounds, int size) {
		List<List<Peg>> possible = allCodes(size);
		for (Round round : rounds) {
			possible = filterByRound(possible, round);
		}
		return possible;
	}

	private List<List<Peg>> filterByRound(List<List<Peg>> possible, Round round) {
		List<List<Peg>> consistent = new ArrayList<List<Peg>>();
		for (List<Peg> code : possible) {
			if (consistent(round,code)) {
				consistent.add(code);
			}
		}
		return consistent;
	}
	
	public boolean consistent(Round round, List<Peg> code) {
		return Board.evaluate(round.getGuess(), code).equals(round.getClue());
	}
	
	public List<List<Peg>> allCodes(int size) {
		Peg[] pegs = Peg.values();
		List<List<Peg>> codes = new ArrayList<List<Peg>>();
		if (size == 1) {
			for (Peg peg : pegs) {
				codes.add(Arrays.asList(peg));
			}
		} else {
			List<List<Peg>> smallerCodes = allCodes(size - 1);
			for (Peg peg : pegs) {
				for (List<Peg> smallCode : smallerCodes) {
					List<Peg> code = new ArrayList<Peg>(Arrays.asList(peg));
					code.addAll(smallCode);
					codes.add(code);
				}
			}
		}
		return codes;
	}
}
