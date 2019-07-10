package edu.osu.romanach.one;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;


public class PokerPatternTests {

	private PokerWinner getWinner(String round) {
		List<PokerHand> hands = PokerInputParser.parseRound(round);
		PokerDealer dealer = new PokerDealer();
		return dealer.getWinner(hands);
	}
	
	//HIGH CARD
	
	@Test
	void highCardWinner() {
		String round = ""
				+ "Black:2S 3S 4S 5S KH,"
				+ "White:2D 3D 4D 5D QH";
		String expectedPattern = "High Card";
		List<String> expectedWinningRanks = Arrays.asList("King");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}

	//ONE PAIR
	
	@Test
	void onePairWinner() {
		String round = ""
				+ "Black:2S 3S 4S AS AH,"
				+ "White:2D 3D 4D 5D KH";
		String expectedPattern = "One Pair";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void onePairHighPairWinner() {
		String round = ""
				+ "Black:2S 3S 4S AS AH,"
				+ "White:2D 3D 4D KD KH";
		String expectedPattern = "One Pair";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void onePairKickerWinner() {
		String round = ""
				+ "Black:2S 3S 4S AS AH,"
				+ "White:2D 3D KD AC AD";
		String expectedPattern = "One Pair";
		List<String> expectedWinningRanks = Arrays.asList("Ace", "King");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void onePairTie() {
		String round = ""
				+ "Black:2S 3S KS AS AH,"
				+ "White:2D 3D KD AC AD";
		String expectedPattern = "One Pair";
		List<String> expectedWinningRanks = Arrays.asList("Ace", "King", "3", "2");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	// TWO PAIR
	
	@Test
	void twoPairWinner() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:2D 3D 4D KD KH";
		String expectedPattern = "Two Pair";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void twoPairHighPairWinner() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:2D JC JD KD KH";
		String expectedPattern = "Two Pair";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void twoPairKickerWinner() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:7D JC JD AC AD";
		String expectedPattern = "Two Pair";
		List<String> expectedWinningRanks = Arrays.asList("Ace", "Jack", "7");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void twoPairTie() {
		String round = ""
				+ "Black:2S JS JH AS AH,"
				+ "White:2D JC JD AC AD";
		String expectedPattern = "Two Pair";
		List<String> expectedWinningRanks = Arrays.asList("Ace", "Jack", "2");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	//THREE OF A KIND
	
	@Test
	void threeOfAKindWinner() {
		String round = ""
				+ "Black:2S 7S 10S 10C 10H,"
				+ "White:2D 7D 7S 10C 10H";
		String expectedPattern = "Three of a Kind";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void threeOfAKindHighTripWinner() {
		String round = ""
				+ "Black:2S 7S 10S 10C 10H,"
				+ "White:2D 7D 7S 7C 10H";
		String expectedPattern = "Three of a Kind";
		List<String> expectedWinningRanks = Arrays.asList("10");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void threeOfAKindKickerWinner() {
		String round = ""
				+ "Black:2S 7S 10S 10C 10H,"
				+ "White:2D 9D 10S 10C 10H";
		String expectedPattern = "Three of a Kind";
		List<String> expectedWinningRanks = Arrays.asList("10", "9");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void threeOfAKindTie() {
		String round = ""
				+ "Black:2S 7S 10S 10C 10H,"
				+ "White:2D 7D 10S 10C 10H";
		String expectedPattern = "Three of a Kind";
		List<String> expectedWinningRanks = Arrays.asList("10", "7", "2");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	//STRAIGHT
	
	@Test
	void straightWinner() {
		String round = ""
				+ "Black:9S 10S JS QS KH,"
				+ "White:9D 10D JD QD AC";
		String expectedPattern = "Straight";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void straightHighCardWinner() {
		String round = ""
				+ "Black:9S 10S JS QS KH,"
				+ "White:8D 9D 10D JD QC";
		String expectedPattern = "Straight";
		List<String> expectedWinningRanks = Arrays.asList("King");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void straightAceLowWinner() {
		String round = ""
				+ "Black:KS AS 2S 3S 4H,"
				+ "White:AD 2D 3D 4D 5C";
		String expectedPattern = "Straight";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void straightAceLowTie() {
		String round = ""
				+ "Black:AS 2S 3S 4S 5H,"
				+ "White:AD 2D 3D 4D 5C";
		String expectedPattern = "Straight";
		List<String> expectedWinningRanks = Arrays.asList("5");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void straightTie() {
		String round = ""
				+ "Black:9S 10S JS QS KH,"
				+ "White:9D 10D JD QD KC";
		String expectedPattern = "Straight";
		List<String> expectedWinningRanks = Arrays.asList("King");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	//FLUSH
	
	@Test
	void flushWinner() {
		String round = ""
				+ "Black:2S 4S 6S 8S JS,"
				+ "White:2D 3D 4D 5D 6H";
		String expectedPattern = "Flush";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void flushHighCardWinner() {
		String round = ""
				+ "Black:2S 4S 6S 8S JS,"
				+ "White:2D 4D 5D 8D JD";
		String expectedPattern = "Flush";
		List<String> expectedWinningRanks = Arrays.asList("Jack", "8", "6");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void flushTie() {
		String round = ""
				+ "Black:2S 4S 6S 8S JS,"
				+ "White:2D 4D 6D 8D JD";
		String expectedPattern = "Flush";
		List<String> expectedWinningRanks = Arrays.asList("Jack", "8", "6", "4", "2");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	//FULL HOUSE
	
	@Test
	void fullHouseWinner() {
		String round = ""
				+ "Black:9S 9C 9H AC AH,"
				+ "White:2D 9D 9D AD AD";
		String expectedPattern = "Full House";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void fullHouseUpperWinner() {
		String round = ""
				+ "Black:9S 9C 9H AC AH,"
				+ "White:4S 4C 4H AC AH";
		String expectedPattern = "Full House";
		List<String> expectedWinningRanks = Arrays.asList("9");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void fullHouseLowerWinner() {
		String round = ""
				+ "Black:9S 9C 9H QC QH,"
				+ "White:9S 9C 9H AC AH";
		String expectedPattern = "Full House";
		List<String> expectedWinningRanks = Arrays.asList("9", "Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void fullHouseTie() {
		String round = ""
				+ "Black:9S 9C 9H QC QH,"
				+ "White:9S 9C 9H QC QH";
		String expectedPattern = "Full House";
		List<String> expectedWinningRanks = Arrays.asList("9", "Queen");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	//FOUR OF A KIND
	
	@Test
	void fourOfAKindWinner() {
		String round = ""
				+ "Black:7S 7C 7H 7D KD,"
				+ "White:7S 7C 7H AH AD";
		String expectedPattern = "Four of a Kind";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void fourOfAKindHighQuadWinner() {
		String round = ""
				+ "Black:7S 7C 7H 7D KD,"
				+ "White:6S 6C 6H 6D AD";
		String expectedPattern = "Four of a Kind";
		List<String> expectedWinningRanks = Arrays.asList("7");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void fourOfAKindKickerWinner() {
		String round = ""
				+ "Black:7S 7C 7H 7D KD,"
				+ "White:7S 7C 7H 7D AD";
		String expectedPattern = "Four of a Kind";
		List<String> expectedWinningRanks = Arrays.asList("7", "Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("White", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'White'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Test
	void fourOfAKindTie() {
		String round = ""
				+ "Black:7S 7C 7H 7D KD,"
				+ "White:7S 7C 7H 7D KD";
		String expectedPattern = "Four of a Kind";
		List<String> expectedWinningRanks = Arrays.asList("7", "King");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	//STRAIGHT FLUSH
	
	@Ignore
	@Test
	void straightFlushWinner() {
		String round = ""
				+ "Black:10S JS QS KS AS,"
				+ "White:7H JH QH KH AH";
		String expectedPattern = "Straight Flush";
		List<String> expectedWinningRanks = Arrays.asList();
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Ignore
	@Test
	void straightFlushHighCardWinner() {
		String round = ""
				+ "Black:10S JS QS KS AS,"
				+ "White:AH 2H 3H 4H 5H";
		String expectedPattern = "Straight Flush";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(1, winner.getWinningHands().size(), "Should be 1 winner");
		assertEquals("Black", winner.getWinningHands().get(0).getPlayerName(), "Winning player should be 'Black'");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
	
	@Ignore
	@Test
	void straightFlushTie() {
		String round = ""
				+ "Black:10S JS QS KS AS,"
				+ "White:10H JH QH KH AH";
		String expectedPattern = "Straight Flush";
		List<String> expectedWinningRanks = Arrays.asList("Ace");
		PokerWinner winner = getWinner(round);
		
		assertEquals(2, winner.getWinningHands().size(), "Should be 2 winners");
		assertEquals(expectedPattern, winner.getWinningHands().get(0).getBestPattern().getPatternName(), "Winning pattern should be '" + expectedPattern + "'");
		assertEquals(expectedWinningRanks, winner.getWinningRanks().stream().map(r -> r.getValueName()).collect(Collectors.toList()), "Winning ranks should be " + expectedWinningRanks);
	}
}
