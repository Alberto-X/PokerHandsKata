package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

public class FullHouse extends PokerPlayingCardPattern {
	private static String patternName = "Full House";
	
	public FullHouse(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		//Group cards by value
		Map<Integer, List<PlayingCard>> cardsByValue = cards.stream().collect(Collectors.groupingBy(c -> c.getValue()));
		
		//Search for groups of size 3 and size 2
		boolean trip = false;
		boolean pair = false;
		for (Map.Entry<Integer, List<PlayingCard>> group : cardsByValue.entrySet()) {
			int size = group.getValue().size();
			if (size >= 3 && !trip) {
				trip = true;
				if (size >= 5 && !pair) {
					pair = true;
					break;
				}
			}
			else if (size >= 2 && !pair) {
				pair = true;
			}
		}
		return trip && pair;
	}
	
	public List<PlayingCard> getRankingCards(List<PlayingCard> cards) {
		List<PlayingCard> ranks = new ArrayList<PlayingCard>();
		
		//Group cards by value
		Map<Integer, List<PlayingCard>> cardsByValue = cards.stream().collect(Collectors.groupingBy(c -> c.getValue()));
		
		//Sort groups
		List<Map.Entry<Integer, List<PlayingCard>>> sortedGroups = cardsByValue.entrySet()
																			   .stream()
																			   .sorted(new IntegerEntryHighToLowComparator<>())
																			   .collect(Collectors.toList());
		
		//Find triplet, add to ranks; find pair
		PlayingCard trip = null;
		PlayingCard pair = null;
		Deque<PlayingCard> kickers = new ArrayDeque<PlayingCard>();
		for (Map.Entry<Integer, List<PlayingCard>> group : sortedGroups) {
			int size = group.getValue().size();
			if (size >= 3 && trip == null) {
				trip = group.getValue().get(0);
				ranks.add(trip);
				if (size >= 5 && pair == null) {
					pair = group.getValue().get(3);
					kickers.addAll(group.getValue().subList(5, size));
				}
				else {
					kickers.addAll(group.getValue().subList(3, size));
				}
			}
			else if (size >= 2 && pair == null) {
				pair = group.getValue().get(0);
				kickers.addAll(group.getValue().subList(2, size));
			}
		}
		
		//Add pair to ranks
		ranks.add(pair);
		
		//Add kickers to ranks
		ranks.addAll(kickers);
		
		return ranks;
	}
	
	public String getRankAsString(List<PlayingCard> ranks) {
		PlayingCard trip = ranks.get(0);
		String tripFormat = "upper %s's";
		if (ranks.size() >= 2) {
			tripFormat = "%s's";
		}
		
		PlayingCard pair = new PlayingCard("2S");	//dummy value
		String pairFormat = "";
		if (ranks.size() >= 2) {
			pair = ranks.get(1);
			pairFormat = " on %s's";
		}
		
		List<String> kickers = new ArrayList<String>();
		String kickerFormat = "";
		if (ranks.size() >= 3) {
			kickers = ranks.subList(2, ranks.size())
						   .stream()
						   .map(r -> r.getValueName())
						   .collect(Collectors.toList());
			kickerFormat = ", %s kicker";
		}
		
		return String.format("[" + tripFormat + pairFormat + kickerFormat + "]", trip.getValueName(), pair.getValueName(), String.join("-", kickers));
	}
}
