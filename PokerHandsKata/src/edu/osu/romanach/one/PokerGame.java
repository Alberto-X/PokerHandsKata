package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Determines the winner of a Poker game.
 * @author Alberto Romañach
 *
 */
public class PokerGame {
	private static String input = ""
			+ "Black:2S 2C 3H 4H 5H,"
			+ "White:2H 2D 3D 4D 5D"
			+ "NEW ROUND"
			+ "Black:2S 4S 6S 8S 10S,"
			+ "White:2H 3H 4H 5H 6H";
	public static final int numCardsRequiredForPattern = 5;
	public static List<PokerPlayingCardPattern> validPatterns = new ArrayList<PokerPlayingCardPattern>();
	
	static {
		//Only instances of patterns passed around
		//validPatterns.add(new TwoPair(3));
		validPatterns.add(new OnePair(2));
		validPatterns.add(new HighCard(1));
	}
	
	/**
	 * Main entry-point of program.
	 * @param args
	 */
	public static void main(String[] args) {
		String[] rounds = input.split("NEW ROUND");
		
		for (String round : rounds) {
			String[] hands = round.split(",");
			
			//READ IN DATA
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
				System.out.println(String.format("%s (best - %s)", pokerHand.toString(), pokerHand.getBestPattern().getValue()));
			}
			
			//CREATE DEALER
			PokerDealer dealer = new PokerDealer();
			
			//FIND WINNER
			PokerWinner winner = dealer.getWinner(pokerHands);
			if (winner.getWinningHands().size() == 1) {
				PokerHand winningHand = winner.getWinningHands().get(0);
				List<String> winningRanks = winner.getWinningRanks().stream()
																	.map(r -> ((Integer)r.getValue()).toString())
																	.collect(Collectors.toList());
				
				System.out.println("WINNER!");
				System.out.println(String.format("  >> %s", winningHand.getPlayerName().toUpperCase()));
				System.out.println(String.format("  >> %s, Rank: %s", winningHand.getBestPattern().getPatternName(), winningRanks));
			}
			else if (winner.getWinningHands().size() > 1) {
				PokerHand someWinner = winner.getWinningHands().get(0);
				List<String> winnerNames = winner.getWinningHands().stream()
												  				   .map(h -> h.getPlayerName().toUpperCase())
												  				   .collect(Collectors.toList());
				List<String> winningRanks = winner.getWinningRanks().stream()
																	.map(r -> ((Integer)r.getValue()).toString())
																	.collect(Collectors.toList());
				
				System.out.println("TIE!");
				System.out.println(String.format("  >> %s", String.join(", ", winnerNames)));
				System.out.println(String.format("  >> %s, Rank: %s", someWinner.getBestPattern().getPatternName(), winningRanks));
			}
			else {
				System.out.println("NO WINNER!");
			}
			
			System.out.println();
		}

	}

}
