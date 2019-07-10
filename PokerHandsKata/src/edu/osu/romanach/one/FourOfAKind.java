package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

public class FourOfAKind extends PokerPlayingCardPattern {
	private static String patternName = "Four of a Kind";
	
	public FourOfAKind(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		//Group cards by value
		Map<Integer, List<PlayingCard>> cardsByValue = cards.stream().collect(Collectors.groupingBy(c -> c.getValue()));
		
		//Search for group of size 4
		for (Map.Entry<Integer, List<PlayingCard>> group : cardsByValue.entrySet()) {
			if (group.getValue().size() >= 4) {
				return true;
			}
		}
		return false;
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
		
		//Find quadruplet, add to ranks
		Deque<PlayingCard> kickers = new ArrayDeque<PlayingCard>();
		for (Map.Entry<Integer, List<PlayingCard>> group : sortedGroups) {
			int size = group.getValue().size();
			if (size >= 4) {
				PlayingCard quad = group.getValue().get(0);
				ranks.add(quad);
				kickers.addAll(group.getValue().subList(4, size));
			}
			else {
				kickers.addAll(group.getValue());
			}
		}
		
		//Add kickers to ranks
		ranks.addAll(kickers);
		
		return ranks;
	}
	
	public String getRankAsString(List<PlayingCard> ranks) {
		PlayingCard quad = ranks.get(0);
		
		List<String> kickers = new ArrayList<String>();
		String kickerFormat = "";
		if (ranks.size() >= 2) {
			kickers = ranks.subList(1, ranks.size())
						   .stream()
						   .map(r -> r.getValueName())
						   .collect(Collectors.toList());
			kickerFormat = ", %s kicker";
		}
		
		return String.format("[quad %s's" + kickerFormat + "]", quad.getValueName(), String.join("-", kickers));
	}
}
