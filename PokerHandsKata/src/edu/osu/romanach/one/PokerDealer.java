package edu.osu.romanach.one;
import java.util.*;

public class PokerDealer {
	private List<PokerHand> hands = new ArrayList<PokerHand>();
	
	public void giveHands(List<PokerHand> hands) {
		this.hands.addAll(hands);
	}
	
	public List<PokerHand> getWinner() {
		//Sort hands by their best pattern
		//TODO: hand comparator
		//hands.sort(c);
		
		//Find all hands with highest pattern
		List<PokerHand> handsWithBestPattern = new ArrayList<PokerHand>();
		int highestValue = hands.get(0).getBestPattern().getValue();
		for (PokerHand hand : hands) {
			if (hand.getBestPattern().getValue() == highestValue) {
				handsWithBestPattern.add(hand);
			}
		}
		
		//Find all hands with highest rank
		List<PokerHand> winners = new ArrayList<PokerHand>();
		for (int i = 0; i < handsWithBestPattern.size(); i++) {
			//TODO: use "getRankingCards" to determine winner amongst hands with best pattern
		}
		winners = handsWithBestPattern;
		return winners;
	}
}
