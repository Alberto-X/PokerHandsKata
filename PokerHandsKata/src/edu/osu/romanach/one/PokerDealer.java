package edu.osu.romanach.one;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
		List<PokerHand> handsWithBestValue = new ArrayList<PokerHand>();
		int highestValue = hands.get(0).getBestPattern().getValue();
		for (PokerHand hand : hands) {
			if (hand.getBestPattern().getValue() == highestValue) {
				handsWithBestValue.add(hand);
			}
		}
		
		//Find hands with highest rank (re-add entries to 'hands' that win each round of rank comparisons)
		Map<PokerHand, List<PlayingCard>> hands = new HashMap<PokerHand, List<PlayingCard>>();
		handsWithBestValue.forEach(h -> hands.put(h, h.getRankingCards()));
		
		List<PlayingCard> ranks = new ArrayList<PlayingCard>();
		int k = -1;
		do {
			k += 1;
			
			//Begin round k of rank comparisons
			int highestRank = 0;
			ranks.clear();
			
			//For each hand, compare the "k-th" ranked card
			Map<PokerHand, List<PlayingCard>> winningHands = new HashMap<PokerHand, List<PlayingCard>>();
			for (Entry<PokerHand, List<PlayingCard>> entry : hands.entrySet()) {
				List<PlayingCard> rankList = entry.getValue();
				try {
					PlayingCard rank = rankList.get(k);
					if (rank.getValue() > highestRank) {
						winningHands.clear();
						winningHands.put(entry.getKey(), entry.getValue());
					}
					else if (rank.getValue() == highestRank) {
						winningHands.put(entry.getKey(), entry.getValue());						
					}
					
					ranks.add(rank);
				} catch(Exception ex) {}
			}
			
			//In case of tie, continue checking only hands with highest rank in this round
			hands.clear();
			hands.putAll(winningHands);
			
		//Keep going until only one winner or no more ranks to check
		} while (hands.size() == 1 || ranks.size() > 0);
		
		return hands.keySet().stream()
							 .collect(Collectors.toList());
	}
}
