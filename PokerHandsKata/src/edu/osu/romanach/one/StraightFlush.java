package edu.osu.romanach.one;
import java.util.*;

public class StraightFlush extends PokerPlayingCardPattern {
	private static String patternName = "Straight Flush";
	private static int lowerAceValue = 1;
	
	public StraightFlush(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		PlayingCard ace = new PlayingCard("AS");
		PlayingCard king = new PlayingCard("KS");
		PlayingCard two = new PlayingCard("2S");
		
		//Ensure all suits the same
		String suit = cards.get(0).getSuit();
		for (PlayingCard card : cards) {
			if (card.getSuit() != suit) {
				return false;
			}
		}
		
		//Sort cards
		List<PlayingCard> sortedCards = new ArrayList<PlayingCard>();
		sortedCards.addAll(cards);
		sortedCards.sort(new PlayingCardHighToLowComparator());
		
		//Put ace at end if no succeeding king
		if (sortedCards.get(0).getValue() == ace.getValue() && sortedCards.get(1).getValue() != king.getValue()) {
			sortedCards.add(sortedCards.remove(0));
		}
		
		//Ensure cards in consecutive order
		int prevValue = sortedCards.get(0).getValue()+1;
		for (PlayingCard card : sortedCards) {
			if (card.getValue() == prevValue-1) {
				prevValue = card.getValue();
				continue;
			}
			else if (prevValue == two.getValue() && card.getValue() == ace.getValue()) {
				prevValue = lowerAceValue;
				continue;
			}
			return false;
		}
		return true;
	}
	
	public List<PlayingCard> getRankingCards(List<PlayingCard> cards) {
		PlayingCard ace = new PlayingCard("AS");
		PlayingCard king = new PlayingCard("KS");
		List<PlayingCard> ranks = new ArrayList<PlayingCard>();
		
		//Sort cards
		List<PlayingCard> sortedCards = new ArrayList<PlayingCard>();
		sortedCards.addAll(cards);
		sortedCards.sort(new PlayingCardHighToLowComparator());
		
		//Put ace at end if no succeeding king
		if (sortedCards.get(0).getValue() == ace.getValue() && sortedCards.get(1).getValue() != king.getValue()) {
			sortedCards.add(sortedCards.remove(0));
		}
		
		//Add highest card to ranks
		ranks.add(sortedCards.get(0));
		
		return ranks;
	}
	
	public String getRankAsString(List<PlayingCard> ranks) {
		PlayingCard highCard = ranks.get(0);
		return String.format("[%s high]", highCard.getValueName());
	}
}
