package edu.osu.romanach.one;
import java.util.*;

public class HighCard extends PokerPlayingCardPattern {
	private static String patternName = "High Card";
	
	public HighCard(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		//Any set of cards can have a high card
		return true;
	}
	
	public List<PlayingCard> getRankingCards(List<PlayingCard> cards) {
		List<PlayingCard> ranks = new ArrayList<PlayingCard>();
		
		//All cards can be the rank
		ranks.addAll(cards);
		
		//Sort cards
		ranks.sort(new PlayingCardHighToLowComparator());
		
		return ranks;
	}
}
