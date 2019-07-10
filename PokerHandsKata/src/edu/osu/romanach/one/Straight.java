package edu.osu.romanach.one;
import java.util.*;

public class Straight extends PokerPlayingCardPattern {
	private static String patternName = "Straight";
	private static int aceLowerValue = 1;
	
	public Straight(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		PlayingCard two = new PlayingCard("2S");
		PlayingCard ace = new PlayingCard("AS");
		
		//Sort cards
		List<PlayingCard> sortedCards = new ArrayList<PlayingCard>();
		sortedCards.addAll(cards);
		sortedCards.sort(new PlayingCardHighToLowComparator());
		
		//Ensure cards in consecutive order
		int prevValue = sortedCards.get(0).getValue()+1;
		for (PlayingCard card : sortedCards) {
			if (card.getValue() == prevValue-1) {
				prevValue = card.getValue();
				continue;
			}
			else if (prevValue == two.getValue() && card.getValue() == ace.getValue()) {
				prevValue = aceLowerValue;
				continue;
			}
			return false;
		}
		return true;
	}
	
	public List<PlayingCard> getRankingCards(List<PlayingCard> cards) {
		List<PlayingCard> ranks = new ArrayList<PlayingCard>();
		
		//Sort cards
		List<PlayingCard> sortedCards = new ArrayList<PlayingCard>();
		sortedCards.addAll(cards);
		sortedCards.sort(new PlayingCardHighToLowComparator());
		
		//Add highest card to ranks
		ranks.add(sortedCards.get(0));
		
		return ranks;
	}
	
	public String getRankAsString(List<PlayingCard> ranks) {
		PlayingCard highCard = ranks.get(0);
		return String.format("[%s high]", highCard.getValueName());
	}
}
