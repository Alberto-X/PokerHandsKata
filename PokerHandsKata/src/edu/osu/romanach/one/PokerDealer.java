package edu.osu.romanach.one;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PokerDealer {
	public PokerWinner getWinner(List<PokerHand> hands) {
		//Group hands by their best pattern
		Map<PokerPlayingCardPattern, List<PokerHand>> handsByPattern = hands.stream().collect(Collectors.groupingBy(h -> h.getBestPattern()));
		
		//Sort the patterns
		List<Entry<PokerPlayingCardPattern, List<PokerHand>>> patterns = new ArrayList<Map.Entry<PokerPlayingCardPattern,List<PokerHand>>>();
		patterns = handsByPattern.entrySet()
					  		     .stream()
					      	     .collect(Collectors.toList());
		patterns.sort(new PatternEntryHighToLowComparator<List<PokerHand>>());
		
		//Find all hands with highest pattern
		List<PokerHand> handsWithBestValue = patterns.get(0).getValue();
		if (patterns.get(0).getKey().getValue() <= 0) {
			//Return empty winner if best pattern has no value
			return new PokerWinner(new ArrayList<PokerHand>(), new ArrayList<PlayingCard>());
		}
		
		//Find hands with highest rank (add entries to 'handRanks' that win each round of rank comparisons)
		Map<PokerHand, List<PlayingCard>> handRanks = new HashMap<PokerHand, List<PlayingCard>>();
		handsWithBestValue.forEach(h -> handRanks.put(h, h.getRankingCards()));
		
		List<PlayingCard> round = new ArrayList<PlayingCard>();
		List<PlayingCard> winningRanks = new ArrayList<PlayingCard>();
		int k = -1;
		do {
			k += 1;
			
			//Begin round k of rank comparisons
			int highestRank = 0;
			round.clear();
			
			//For each hand, compare the "k-th" ranked card
			Map<PokerHand, List<PlayingCard>> winningHands = new HashMap<PokerHand, List<PlayingCard>>();
			for (Entry<PokerHand, List<PlayingCard>> entry : handRanks.entrySet()) {
				List<PlayingCard> rankList = entry.getValue();
				try {
					PlayingCard rank = rankList.get(k);
					if (rank.getValue() > highestRank) {
						highestRank = rank.getValue();
						winningHands.clear();
						winningHands.put(entry.getKey(), entry.getValue());
						
						//Store best rank for this round
						if (winningRanks.size() <= k) {
							winningRanks.add(rank);
						}
						else {
							winningRanks.set(k, rank);
						}
					}
					else if (rank.getValue() == highestRank) {
						winningHands.put(entry.getKey(), entry.getValue());
					}
					
					round.add(rank);
				} catch(Exception ex) {
					//No rank to compare for this hand
				}
			}
			
			if (winningHands.size() > 0) {
				//In case of tie, continue checking only hands with highest rank in this round
				handRanks.clear();
				handRanks.putAll(winningHands);
			}
			
		//Keep going until only one winner or no more ranks to check
		} while (handRanks.size() > 1 && round.size() > 0);
		
		List<PokerHand> winningHands = handRanks.keySet().stream().collect(Collectors.toList());
		return new PokerWinner(winningHands, winningRanks);
	}
}
