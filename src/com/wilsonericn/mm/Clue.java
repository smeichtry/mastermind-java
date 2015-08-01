package com.wilsonericn.mm;

public class Clue {

	private int exact;
	private int unordered;
	
	public Clue(int exact, int unordered) {
		this.exact = exact;
		this.unordered = unordered;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Clue) {
			Clue that = (Clue) o;
			return that.exact == this.exact && that.unordered == this.unordered;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return exact + 31 * unordered;
	}
	
	@Override
	public String toString() {
		return String.format("Exact: %s, Unordered: %s", exact, unordered);
	}
	public int getExact() {
		return exact;
	}

	public int getUnordered() {
		return unordered;
	}

}
