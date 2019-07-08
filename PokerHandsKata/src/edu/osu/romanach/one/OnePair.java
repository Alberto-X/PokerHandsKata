package edu.osu.romanach.one;
import java.util.*;

public class OnePair extends PokerPlayingCardPattern {
	private static String patternName = "One Pair";
	
	public OnePair(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		//TODO: search logic here
		return false;
	}
	
	public List<PlayingCard> getRankingCards(List<PlayingCard> cards) {
		//TODO: search logic here
		List<PlayingCard> ranks = new ArrayList<PlayingCard>();
		return ranks;
	}
}
