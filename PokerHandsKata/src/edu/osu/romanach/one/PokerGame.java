package edu.osu.romanach.one;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Determines the winner of a Poker game.
 * @author Alberto Roma�ach
 *
 */
public class PokerGame {
	private static String input = ""
			+ "Black:4H 4H AH AH AH,"
			+ "White:4D 4D AD AD AD"
			+ "NEW ROUND"
			+ "Black:2S 4S 6S 8S 10S,"
			+ "White:2H 3H 4H 5H 6H";
	public static final int numCardsRequiredForPattern = 5;
	public static List<PokerPlayingCardPattern> validPatterns = new ArrayList<PokerPlayingCardPattern>();
	
	static {
		//Only instances of patterns passed around
		validPatterns.add(new FullHouse(7));
		validPatterns.add(new Flush(6));
		validPatterns.add(new Straight(5));
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
				PokerPlayingCardPattern winningPattern = winningHand.getBestPattern();
				
				String rankFormat = "";
				String rankAsString = "";
				if (winner.getWinningRanks().size() > 0) {
					rankFormat = ", %s";
					rankAsString = winningPattern.getRankAsString(winner.getWinningRanks());
				}
				
				System.out.println(String.format("%s wins!", winningHand.getPlayerName().toUpperCase()));
				System.out.println(String.format("  >> %s" + rankFormat, winningPattern.getPatternName(), rankAsString));
			}
			else if (winner.getWinningHands().size() > 1) {
				PokerHand someWinner = winner.getWinningHands().get(0);
				PokerPlayingCardPattern winningPattern = someWinner.getBestPattern();
				List<String> winnerNames = winner.getWinningHands().stream()
												  				   .map(h -> h.getPlayerName().toUpperCase())
												  				   .collect(Collectors.toList());
				
				String rankFormat = "";
				String rankAsString = "";
				if (winner.getWinningRanks().size() > 0) {
					rankFormat = ", %s";
					rankAsString = winningPattern.getRankAsString(winner.getWinningRanks());
				}
				
				System.out.println(String.format("%s tie.", String.join("/", winnerNames)));
				System.out.println(String.format("  >> %s" + rankFormat, winningPattern.getPatternName(), rankAsString));
			}
			else {
				System.out.println("NO WINNER!");
			}
			
			System.out.println();
		}

	}

}
