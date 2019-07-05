package edu.osu.romanach.one;
import java.util.*;

/**
 * Base class for any poker patterns.
 * @author Alberto
 *
 */
public abstract class PokerPlayingCardPattern {
	private int value;
	
	public PokerPlayingCardPattern(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns true if {@code cards} contains this pattern.
	 */
	public abstract boolean isFoundIn(List<PlayingCard> cards);
	/**
	 * Returns a list of cards used to determine the "rank" of this pattern (for tie-breakers).
	 * @requires {@code cards} must contain this pattern
	 * @return list of cards (or null if NoPattern)
	 */
	public abstract List<PlayingCard> getRankingCards(List<PlayingCard> cards);
}
