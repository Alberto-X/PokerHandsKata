package edu.osu.romanach.one;
import java.util.ArrayList;
import java.util.List;

public class PokerInputParser {
	public static List<PokerHand> parseRound(String round) {
		String[] hands = round.split(",");
		
		List<PokerHand> pokerHands = new ArrayList<PokerHand>();
		for (String hand : hands) {
			String[] data = hand.split(":");
			String playerName = data[0];
			String[] cards = data[1].split(" ");
			
			//Add cards to list
			List<PlayingCard> playingCards = new ArrayList<PlayingCard>();
			for (String card : cards) {
				playingCards.add(new PlayingCard(card));
			}
			
			//Add poker hand to list
			PokerHand pokerHand = new PokerHand(playerName, playingCards);
			pokerHands.add(pokerHand);
		}
		
		return pokerHands;
	}
}
