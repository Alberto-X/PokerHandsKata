package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

public class Flush extends PokerPlayingCardPattern {
	private static String patternName = "Flush";
	
	public Flush(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		//Ensure all cards have same suit
		String suit = cards.get(0).getSuit();
		for (PlayingCard card : cards) {
			if (card.getSuit() != suit) {
				return false;
			}
		}
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
