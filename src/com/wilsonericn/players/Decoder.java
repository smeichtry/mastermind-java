package com.wilsonericn.players;

import java.util.List;

import com.wilsonericn.mm.Peg;
import com.wilsonericn.mm.Round;

public interface Decoder {

	List<Peg> requestGuess(List<Round> rounds, int size);

}
