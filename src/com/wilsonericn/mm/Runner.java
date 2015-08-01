package com.wilsonericn.mm;

import java.util.List;

import com.wilsonericn.players.Encoder;
import com.wilsonericn.players.Solver;

public class Runner {

	private Solver solver = new Solver();

	public void runAllCodes(int size) {
		List<List<Peg>> allCodes = solver.allCodes(size);
		for (final List<Peg> code : allCodes) {
			Encoder robot = makeRobot(code);
			Game game = new Game();
			game.setEncoder(robot);
			game.setDecoder(solver);
			game.playGame(size);
		}
	}

	private Encoder makeRobot(final List<Peg> code) {
		Encoder robot = new Encoder() {
			@Override
			public List<Peg> chooseSecret(int size) {
				return code;
			}
		};
		return robot;
	}
	
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.runAllCodes(5);
	}
}
