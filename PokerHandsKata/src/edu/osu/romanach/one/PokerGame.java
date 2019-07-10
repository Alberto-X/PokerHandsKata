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
			+ "Black:7S 3C 3H 3H 3H,"
			+ "White:AH 3D 3D 3D AD"
			+ "NEW ROUND"
			+ "Black:2S 4S 6S 8S 10S,"
			+ "White:2H 3H 4H 5H 6H";
	public static final int numCardsRequiredForPattern = 5;
	public static List<PokerPlayingCardPattern> validPatterns = new ArrayList<PokerPlayingCardPattern>();
	
	static {
		//Only instances of patterns passed around
		validPatterns.add(new ThreeOfAKind(4));
		validPatterns.add(new TwoPair(3));
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
			//PARSE DATA
			List<PokerHand> hands = PokerInputParser.parseRound(round);
			for (PokerHand hand : hands) {
				System.out.println(String.format("%s (%s)", hand.toString(), hand.getBestPattern().getPatternName()));
			}
			
			//CREATE DEALER
			PokerDealer dealer = new PokerDealer();
			
			//FIND WINNER
			PokerWinner winner = dealer.getWinner(hands);
			if (winner.getWinningHands().size() == 1) {
				PokerHand winningHand = winner.getWinningHands().get(0);
				PokerPlayingCardPattern pattern = winningHand.getBestPattern();
				
				System.out.println("WINNER!");
				System.out.println(String.format("  >> %s", winningHand.getPlayerName().toUpperCase()));
				System.out.println(String.format("  >> %s, Rank: %s", pattern.getPatternName(), pattern.getRankAsString(winner.getWinningRanks())));
			}
			else if (winner.getWinningHands().size() > 1) {
				PokerHand someWinner = winner.getWinningHands().get(0);
				PokerPlayingCardPattern pattern = someWinner.getBestPattern();
				List<String> winnerNames = winner.getWinningHands().stream()
												  				   .map(h -> h.getPlayerName().toUpperCase())
												  				   .collect(Collectors.toList());
				
				System.out.println("TIE!");
				System.out.println(String.format("  >> %s", String.join(", ", winnerNames)));
				System.out.println(String.format("  >> %s, Rank: %s", pattern.getPatternName(), pattern.getRankAsString(winner.getWinningRanks())));
			}
			else {
				System.out.println("NO WINNER!");
			}
			
			System.out.println();
		}

	}

}
