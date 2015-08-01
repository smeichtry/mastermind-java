package com.wilsonericn.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.wilsonericn.mm.Clue;
import com.wilsonericn.mm.Peg;
import com.wilsonericn.mm.Round;

public class Cli {

	public List<Peg> requestCode() {
		System.out.println("Enter code: ");
		String rawGuess = null;
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			rawGuess = bufferRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertInput(rawGuess);
	}

	List<Peg> convertInput(String input) {
		char[] chars = input.replaceAll("\\W", "").toUpperCase().toCharArray();
		List<Peg> code = new ArrayList<Peg>();
		for (char c : chars) {
			code.add(Peg.charToPeg.get(c));
		}
		return code;
	}

	public void showBoard(List<Round> rounds) {
		for (Round round : rounds) {
			System.out.println(convertOutput(round));
		}
	}

	String convertOutput(Round round) {
		List<Peg> guess = round.getGuess();
		String guessString = codeToString(guess);
		Clue clue = round.getClue();
		int size = guess.size();
		String clueString = clueToString(clue, size);
		String dashes = repeatString("-", 6 + 2 * size);
		return String.format("%s%n|%s|  |%s|", dashes, guessString, clueString);
	}

	private String clueToString(Clue clue, int size) {
		String clueStr = "";
		int numX = clue.getExact();
		int numO = clue.getUnordered();
		int numSpace = size - (numX + numO);
		clueStr += repeatString("X", numX);
		clueStr += repeatString("O", numO);
		clueStr += repeatString(" ", numSpace);
		return clueStr;
	}

	private String repeatString(String input, int times) {
		String s = "";
		for (int i = 0; i < times; i++) {
			s += input;
		}
		return s;
	}

	private String codeToString(List<Peg> code) {
		int size = code.size();
		char[] chars = new char[size];
		for (int idx = 0; idx < size; idx++) {
			chars[idx] = code.get(idx).c;
		}
		return new String(chars);
	}
}
