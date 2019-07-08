package edu.osu.romanach.one;
import java.util.*;

public class PatternHighToLowComparator implements Comparator<PokerPlayingCardPattern> {
	@Override
	public int compare(PokerPlayingCardPattern o1, PokerPlayingCardPattern o2) {
		return o2.getValue() - o1.getValue();
	}
}
