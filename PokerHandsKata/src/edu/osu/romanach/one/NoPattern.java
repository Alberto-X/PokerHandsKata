package edu.osu.romanach.one;
import java.util.*;

public class NoPattern extends PokerPlayingCardPattern {
	public NoPattern(int value) {
		super(value);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		//Every set of cards has, in worst-case, "no pattern"
		return true;
	}
	
	public List<PlayingCard> getRankingCards(List<PlayingCard> cards) {
		//Return empty list, since there's no pattern to rank
		return new ArrayList<PlayingCard>();
	}
}
