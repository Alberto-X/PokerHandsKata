package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

public class OnePair extends PokerPlayingCardPattern {
	private static String patternName = "One Pair";
	
	public OnePair(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		Set<Integer> values = new HashSet<Integer>();
		
		//One pair if any duplicate value
		for (PlayingCard card : cards) {
			if (!values.add(card.getValue())) {
				return true;
			}
		}
		return false;
	}
	
	public List<PlayingCard> getRankingCards(List<PlayingCard> cards) {
		List<PlayingCard> ranks = new ArrayList<PlayingCard>();
		
		//Sort cards
		List<PlayingCard> sortedCards = new ArrayList<PlayingCard>();
		sortedCards.addAll(cards);
		sortedCards.sort(new PlayingCardHighToLowComparator());

		//Find the pair, add to ranks (remove pair from deque)
		PlayingCard pair1 = null;
		Deque<PlayingCard> deque = new ArrayDeque<PlayingCard>();
		for (PlayingCard card : sortedCards) {
			if (pair1 == null && !deque.isEmpty() && deque.peekLast().getValue() == card.getValue()) {
				pair1 = deque.pollLast();
				ranks.add(pair1);
				continue;
			}
			deque.addLast(card);
		}
		
		//Add other cards to ranks
		ranks.addAll(deque);
		
		return ranks;
	}
	
	public String getRankAsString(List<PlayingCard> ranks) {
		PlayingCard pair1 = ranks.get(0);
		List<String> kickers = ranks.subList(1, ranks.size())
									.stream()
									.map(r -> r.getValueName())
									.collect(Collectors.toList());
		String kickerFormat = "";
		if (kickers.size() > 0) {
			kickerFormat = ", %s kicker";
		}
		return String.format("[pair of %s's" + kickerFormat + "]", pair1.getValueName(), String.join("-", kickers));
	}
}
