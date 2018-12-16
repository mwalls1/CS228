package edu.iastate.cs228.proj2;

import java.util.Comparator;

public class LexiconImpl implements Lexicon, Comparator<String> {

	/**
	 * @author Mason Walls
	 */
	/***
	 * Lookup table mapping characters in lexicographical order to to their input
	 * order. This is public to support automated grading.
	 */
	public CharacterValue[] characterOrdering;

	/***
	 * Creates an array of CharacterValue from characterOrdering. Sorts it using
	 * java.util.Arrays.sort().
	 * 
	 * @param characterOrdering
	 *            character order from configuration file
	 */
	public LexiconImpl(char[] characterOrdering) {
		// TODO

		this.characterOrdering = new CharacterValue[characterOrdering.length];

		for (int i = 0; i < characterOrdering.length; i++) {

			this.characterOrdering[i] = new CharacterValue(i, characterOrdering[i]);

		}
		Comparator<CharacterValue> comp = new Comparator<CharacterValue>() {

			@Override
			public int compare(CharacterValue a, CharacterValue b) {
				if (a.character==b.character) {

					return 0;

				} else if (a.character > b.character) {

					return 1;

				} else {

					return -1;

				}
			}
		};
		java.util.Arrays.sort(this.characterOrdering, comp);

	}

	/***
	 * Compares two words based on the configuration
	 * 
	 * @param a
	 *            first word
	 * @param b
	 *            second word
	 * @return negative if a<b, 0 if equal, postive if a>b
	 */
	@Override
	public int compare(String a, String b) {
		int length;
		if (a.length() < b.length())
			length = a.length();
		else
			length = b.length();
		for (int i = 0; i < length; i++) {
			if (getCharacterOrdering(a.charAt(i)) > getCharacterOrdering(b.charAt(i))) {
				return 1;
			}
			if (getCharacterOrdering(a.charAt(i)) < getCharacterOrdering(b.charAt(i))) {
				return -1;
			}
		}
		if (a.length() == b.length())
			return 0;
		else if (a.length() < b.length())
			return -1;
		return 1;
	}

	/**
	 * Uses binary search to find the order of key.
	 * 
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {
		// TODO

		CharacterValue charV = new CharacterValue(-1, key);

		return binSearch(characterOrdering, charV);

	}

	private int binSearch(CharacterValue[] characterOrdering, CharacterValue key) {

		int len = 0;
		int n = characterOrdering.length - 1;

		while (len <= n) {

			int mid = len + (n - len) / 2;

			if (key.character == characterOrdering[mid].character) {

				return characterOrdering[mid].value;

			} else if (key.character < characterOrdering[mid].character) {

				n = mid - 1;

			} else if (key.character > characterOrdering[mid].character) {

				len = mid + 1;

			}
		}

		return -1;

	}

	public static class CharacterValue {
		public int value;
		public char character;

		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}

		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue other = (CharacterValue) o;
			return value == other.value && character == other.character;
		}
	}

	/**
	 * Returns whether or not word is valid according to the alphabet known to this
	 * lexicon.
	 * 
	 * @param word
	 *            word to be checked.
	 *
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (getCharacterOrdering(word.charAt(i)) == -1)
				return false;
		}
		return true;

	}

}
