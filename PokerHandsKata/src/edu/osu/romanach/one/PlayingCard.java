package edu.osu.romanach.one;
import java.util.*;

/**
 * Represents a card from a traditional deck of playing cards.
 * @author Alberto Romañach
 *
 */
public class PlayingCard {
	private int value;
	private String suit;
	private String valueName;
	private static Map<String, PlayingCardValueData> valueStrings = new HashMap<String, PlayingCardValueData>();
	private static Map<String, String> suitStrings = new HashMap<String, String>();
	
	static {
		valueStrings.put("2", new PlayingCardValueData(2, "2"));
		valueStrings.put("3", new PlayingCardValueData(3, "3"));
		valueStrings.put("4", new PlayingCardValueData(4, "4"));
		valueStrings.put("5", new PlayingCardValueData(5, "5"));
		valueStrings.put("6", new PlayingCardValueData(6, "6"));
		valueStrings.put("7", new PlayingCardValueData(7, "7"));
		valueStrings.put("8", new PlayingCardValueData(8, "8"));
		valueStrings.put("9", new PlayingCardValueData(9, "9"));
		valueStrings.put("10", new PlayingCardValueData(10, "10"));
		valueStrings.put("J", new PlayingCardValueData(11, "jack"));
		valueStrings.put("Q", new PlayingCardValueData(12, "queen"));
		valueStrings.put("K", new PlayingCardValueData(13, "king"));
		valueStrings.put("A", new PlayingCardValueData(14, "ace"));
		
		suitStrings.put("S", "spades");
		suitStrings.put("H", "hearts");
		suitStrings.put("C", "clubs");
		suitStrings.put("D", "diamond");
	}
	
	public PlayingCard(String rep) {
		int suitPosition = rep.length()-1;
		String valueStr = rep.substring(0, suitPosition);
		String suitStr = rep.substring(suitPosition);
		
		value = valueStrings.get(valueStr).value;
		valueName = valueStrings.get(valueStr).valueName;
		suit = suitStrings.get(suitStr);
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("%s of %s", firstCharToUpper(valueName), suit);
	}
	
	/**
	 * Makes first character of given string uppercase.
	 * @requires |str| > 0
	 */
	private String firstCharToUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}


