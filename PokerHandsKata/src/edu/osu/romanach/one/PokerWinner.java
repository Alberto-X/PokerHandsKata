package edu.osu.romanach.one;
import java.util.*;

public class PokerWinner {
	private List<PokerHand> winningHands;
	private List<PlayingCard> winningRanks;
	
	public PokerWinner(List<PokerHand> winningHands, List<PlayingCard> winningRanks) {
		this.winningHands = winningHands;
		this.winningRanks = winningRanks;
	}
	
	public List<PokerHand> getWinningHands() {
		return winningHands;
	}
	
	public List<PlayingCard> getWinningRanks() {
		return winningRanks;
	}
}
