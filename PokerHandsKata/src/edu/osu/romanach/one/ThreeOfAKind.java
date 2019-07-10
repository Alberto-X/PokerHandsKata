package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

public class ThreeOfAKind extends PokerPlayingCardPattern {
	private static String patternName = "Three of a Kind";
	
	public ThreeOfAKind(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		//Group cards by value
		Map<Integer, List<PlayingCard>> cardsByValue = cards.stream().collect(Collectors.groupingBy(c -> c.getValue()));
		
		//Search for group of size 3
		for (Map.Entry<Integer, List<PlayingCard>> group : cardsByValue.entrySet()) {
			if (group.getValue().size() >= 3) {
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
		
		//Find triplet, add to ranks
		Deque<PlayingCard> kickers = new ArrayDeque<PlayingCard>();
		for (Map.Entry<Integer, List<PlayingCard>> group : sortedGroups) {
			int size = group.getValue().size();
			if (size >= 3) {
				PlayingCard trip = group.getValue().get(0);
				ranks.add(trip);
				kickers.addAll(group.getValue().subList(3, size));
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
		PlayingCard trip = ranks.get(0);
		
		List<String> kickers = new ArrayList<String>();
		String kickerFormat = "";
		if (ranks.size() >= 2) {
			kickers = ranks.subList(1, ranks.size())
						   .stream()
						   .map(r -> r.getValueName())
						   .collect(Collectors.toList());
			kickerFormat = ", %s kicker";
		}
		
		return String.format("[trip %s's" + kickerFormat + "]", trip.getValueName(), String.join("-", kickers));
	}
}
