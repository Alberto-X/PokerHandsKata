package edu.osu.romanach.one;
import java.util.*;

public class IntegerEntryHighToLowComparator<T> implements Comparator<Map.Entry<Integer, T>> {
	@Override
	public int compare(Map.Entry<Integer, T> o1, Map.Entry<Integer, T> o2) {
		return o2.getKey() - o1.getKey();
	}
}
