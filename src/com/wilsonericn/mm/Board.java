package com.wilsonericn.mm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

	public static Clue evaluate(List<Peg> guess, List<Peg> actual) {
		int exact = exactMatches(guess, actual);
		int unordered = unorderedMatches(guess, actual);
		return new Clue(exact, unordered - exact);
	}
	
	private static int exactMatches(List<Peg> guess, List<Peg> actual) {
		int matches = 0;
		for (int idx = 0; idx < actual.size(); idx++) {
			if (actual.get(idx) ==  guess.get(idx)) {
				matches++;
			}
		}
		return matches;
	}
	
	private static int unorderedMatches(List<Peg> guess, List<Peg> actual) {
		Map<Peg,Integer> guessFreqs = count(guess);
		Map<Peg,Integer> actualFreqs = count(actual);
		return addMins(guessFreqs, actualFreqs);
	}

	private static int addMins(Map<Peg, Integer> guessFreqs, Map<Peg, Integer> actualFreqs) {
		int minSum = 0;
		for (Peg guessPeg : guessFreqs.keySet()) {
			if (actualFreqs.containsKey(guessPeg)) {
				minSum += Math.min(guessFreqs.get(guessPeg), actualFreqs.get(guessPeg));
			}
		}
		return minSum;
	}

	private static Map<Peg, Integer> count(List<Peg> guess) {
		Map<Peg,Integer> freqs = new HashMap<Peg,Integer>();
		for (Peg peg : guess) {
			if (freqs.containsKey(peg)) {
				Integer count = freqs.get(peg);
				freqs.put(peg, ++count);
			} else {
				freqs.put(peg, 1);
			}
		}
		return freqs;
	}
}
