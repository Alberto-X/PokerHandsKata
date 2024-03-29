package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

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
		
		//Sort cards
		List<PlayingCard> sortedCards = new ArrayList<PlayingCard>();
		sortedCards.addAll(cards);
		sortedCards.sort(new PlayingCardHighToLowComparator());
		
		//Add sorted cards to ranks
		ranks.addAll(sortedCards);
		
		return ranks;
	}

	public String getRankAsString(List<PlayingCard> ranks) {
		List<String> values = ranks.stream()
								   .map(r -> r.getValueName())
								   .collect(Collectors.toList());
		return String.format("[%s high]", String.join("-", values));
	}
}
