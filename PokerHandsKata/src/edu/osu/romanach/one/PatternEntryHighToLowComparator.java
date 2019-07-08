package edu.osu.romanach.one;
import java.util.*;

//@SuppressWarnings("hiding")
public class PatternEntryHighToLowComparator<T> implements Comparator<Map.Entry<PokerPlayingCardPattern, T>> {
	@Override
	public int compare(Map.Entry<PokerPlayingCardPattern, T> o1, Map.Entry<PokerPlayingCardPattern, T> o2) {
		return o2.getKey().getValue() - o1.getKey().getValue();
	}
}
