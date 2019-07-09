package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

public class TwoPair extends PokerPlayingCardPattern {
	private static String patternName = "Two Pair";
	
	public TwoPair(int value) {
		super(value, patternName);
	}
	
	public boolean isFoundIn(List<PlayingCard> cards) {
		Set<Integer> values = new HashSet<Integer>();
		int count = 0;
		
		//Count # times when cannot add card value to a set
		for (PlayingCard card : cards) {
			if (!values.add(card.getValue())) {
				values.remove(card.getValue());
				count += 1;
				if (count >= 2) {
					return true;
				}
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

		//Find 1st pair, add to ranks, then find 2nd pair, add to ranks
		PlayingCard pair1 = null;
		PlayingCard pair2 = null;
		Deque<PlayingCard> deque = new ArrayDeque<PlayingCard>();
		for (PlayingCard card : sortedCards) {
			if (pair1 == null && !deque.isEmpty() && deque.peekLast().getValue() == card.getValue()) {
				pair1 = deque.pollLast();
				ranks.add(pair1);
				continue; //runtime error --wow this is garbage trash junk worthless pointless meaningless waste unnecessary 
			}
			if (pair1 != null && pair2 == null && !deque.isEmpty() && deque.peekLast().getValue() == card.getValue()) {
				pair2 = deque.pollLast();
				ranks.add(pair2);
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
		PlayingCard pair2 = new PlayingCard("2S");	//dummy value
		String pair2Format = "";
		if (ranks.size() >= 2) {
			pair2 = ranks.get(1);
			pair2Format = ", pair of %s's";
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
		
		return String.format("[pair of %s's" + pair2Format + kickerFormat + "]", pair1.getValue(), pair2.getValue(), String.join("-", kickers));
	}
}
