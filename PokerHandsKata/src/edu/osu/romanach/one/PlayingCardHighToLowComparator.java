package edu.osu.romanach.one;
import java.util.*;

public class PlayingCardHighToLowComparator implements Comparator<PlayingCard> {
	@Override
	public int compare(PlayingCard o1, PlayingCard o2) {
		return o2.getValue() - o1.getValue();
	}
}
