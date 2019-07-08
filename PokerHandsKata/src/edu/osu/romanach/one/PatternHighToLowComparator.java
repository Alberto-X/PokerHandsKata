package edu.osu.romanach.one;
import java.util.*;

public class PatternHighToLowComparator implements Comparator<PokerPlayingCardPattern> {
	@Override
	public int compare(PokerPlayingCardPattern o1, PokerPlayingCardPattern o2) {
		if (o1.getValue() > o2.getValue()) {
			return 1;
		}
		else if (o1.getValue() == o2.getValue()) {
			return 0;
		}
		else {
			return -1;
		}
	}
}
