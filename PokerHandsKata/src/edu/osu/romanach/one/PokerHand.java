package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

public class PokerHand {
	private String playerName;
	private List<PlayingCard> cards;
	//private PokerHandPattern bestPattern;
	
	public PokerHand(String playerName, List<PlayingCard> cards) {
		this.playerName = playerName;
		this.cards = cards;
	}
	
	private PokerPlayingCardPattern calculateBestPattern() {
		//Return NoPattern if wrong number of cards
		if (cards.size() != PokerGame.numCardsRequiredForPattern) {
			return new NoPattern(0);
		}
		
		//Go thru patterns in descending order of value
		//TODO: pattern comparator
		//PokerGame.validPatterns.sort(c);
		List<PokerPlayingCardPattern> sortedPatterns = PokerGame.validPatterns;
		for (PokerPlayingCardPattern pattern : sortedPatterns) {
			//Return highest value pattern that matches cards
			if (pattern.isFoundIn(cards)) {
				return pattern;
			}
		}
		//Should never reach here, since HighCard will always be found
		return new NoPattern(0);
	}
	
	public PokerPlayingCardPattern getBestPattern() {
		return calculateBestPattern();
	}
	
	public List<PlayingCard> getAllCards() {
		return cards;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	@Override
	public String toString() {
		List<String> cardNames = cards.stream()
									  .map(c -> c.toString())
									  .collect(Collectors.toList());
		return String.format("%s: %s", playerName, String.join(", ", cardNames));
	}
}
