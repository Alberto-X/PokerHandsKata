package edu.osu.romanach.one;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;


public class PokerPatternTests {

	private PokerWinner getWinner(String round) {
		List<PokerHand> hands = PokerInputParser.parseRound(round);
		PokerDealer dealer = new PokerDealer();
		return dealer.getWinner(hands);
	}
	
	@Test
	void highCardWinner() {
		String round = ""
				+ "Black:2S 3S 4S 5S AH,"
				+ "White:2D 3D 4D 5D KH";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals("High Card", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'High Card'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}

	@Test
	void onePairWinner() {
		String round = ""
				+ "Black:2S 3S 4S AS AH,"
				+ "White:2D 3D 4D 5D KH";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals("One Pair", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'One Pair'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void onePairHighPairWinner() {
		String round = ""
				+ "Black:2S 3S 4S AS AH,"
				+ "White:2D 3D 4D KD KH";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals("One Pair", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'One Pair'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void onePairKickerWinner() {
		String round = ""
				+ "Black:2S 3S 4S AS AH,"
				+ "White:2D 3D KD AC AD";
		List<String> expectedWinningRanks = Arrays.asList("Ace", "King");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals("One Pair", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'One Pair'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void twoPairWinner() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:2D 3D 4D KD KH";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals("Two Pair", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'Two Pair'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void twoPairHighPairWinner() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:2D JC JD KD KH";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals("Two Pair", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'Two Pair'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void twoPairKickerWinner() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:7D JC JD AC AD";
		List<String> expectedWinningRanks = Arrays.asList("Ace", "Jack", "7");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals("Two Pair", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'Two Pair'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void twoPairTieWinner() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:2D JC JD AC AD";
		List<String> expectedWinningRanks = Arrays.asList("Ace", "Jack", "2");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals("Two Pair", winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be 'Two Pair'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
}
