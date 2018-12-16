package edu.iastate.cs228.hw01;

/**
 * @author Mason Walls
 *
 */
public class MyString {
	private char[] chars;

	/**
	 * Creates a MyString Object
	 * 
	 * @param character
	 *            array
	 * @exception Throws
	 *                IllegalArgumentException if the char array is null or has
	 *                length of 0
	 */
	public MyString(char[] chars) {
		if (chars == null || chars.length == 0)
			throw new IllegalArgumentException();

		this.chars = chars;
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--
	/**
	 * returns the length of MyString
	 * 
	 * @return an int
	 */
	public int length() {
		return chars.length;
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
	/**
	 * Returns the value of that char at the given index
	 * 
	 * @param intger
	 *            specifying the index
	 * @return a char
	 * @exception IndexOutOfBoundsException
	 *                if index > length or index < 0
	 */
	public char charAt(int index) {
		if (index > chars.length - 1 || index < 0)
			throw new IndexOutOfBoundsException();
		return chars[index];
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-
	/**
	 * Returns a substring from beginning index to ending index - 1
	 * 
	 * @param integer
	 *            to specify the beginning index
	 * @param integer
	 *            to specify the ending index
	 * @return a MyString object
	 * @exception IndexOutOfBoundsException
	 *                if beginning index < 0 or ending index > length or beginning
	 *                index > ending index
	 */
	public MyString substring(int begin, int end) {
		int index = 0;
		if (begin < 0 || end > chars.length || begin > end)
			throw new IndexOutOfBoundsException();
		char sub[] = new char[end - begin];
		for (int i = begin; i < end; i++) {
			sub[index] = chars[i];
			index++;
		}
		return new MyString(sub);
	}

	// It is ok to use
	// https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#toLowerCase-char-
	/**
	 * Turns all uppercase letters to lowercase letters
	 * 
	 * @return a MyString object
	 */
	public MyString toLowerCase() {
		char[] temp = new char[chars.length];
		for (int i = 0; i < chars.length; i++) {
			temp[i] = java.lang.Character.toLowerCase(chars[i]);
		}
		return new MyString(temp);
	}

	// You can assume only positive values.
	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#valueOf-int-
	/**
	 * Creates a string of the given integer
	 * 
	 * @param an
	 *            integer that will be converted to a string
	 * @return a MyString object
	 */
	public static MyString valueOf(int i) {
		int size = (int) (Math.log10(i) + 1);
		char[] num = new char[size];
		while (i > 0) {
			num[size - 1] = (char) (i % 10 + '0');
			i = i / 10;
			size -= 1;
		}
		return new MyString(num);
	}

	public char[] toCharArray() {
		return chars;
	}
}