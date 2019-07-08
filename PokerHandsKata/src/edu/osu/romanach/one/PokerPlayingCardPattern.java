package edu.osu.romanach.one;
import java.util.*;

/**
 * Base class for any poker patterns.
 * @author Alberto Romañach
 *
 */
public abstract class PokerPlayingCardPattern {
	private int value;
	private String patternName;
	
	public PokerPlayingCardPattern(int value, String patternName) {
		this.value = value;
		this.patternName = patternName;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getPatternName() {
		return patternName;
	}
	
	/**
	 * Returns true if {@code cards} contains this pattern.
	 */
	public abstract boolean isFoundIn(List<PlayingCard> cards);
	/**
	 * Returns a list of cards used to determine the "rank" of this pattern (for tie-breakers).
	 * @requires {@code cards} must contain this pattern
	 * @return list of cards (empty list if NoPattern)
	 */
	public abstract List<PlayingCard> getRankingCards(List<PlayingCard> cards);
}
