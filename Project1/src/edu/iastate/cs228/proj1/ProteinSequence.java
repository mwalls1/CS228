package edu.iastate.cs228.proj1;

/*
 * @author
*/

public class ProteinSequence extends Sequence {
	/**
	 * @author Mason Walls If the character array argument {@code psarr} contains a
	 *         character on which the method {@link #isValidLetter(char)} returns
	 *         {@code false}, then it throws an
	 *         {@link java.lang.IllegalArgumentException} exception with the message
	 *         {@code "Invalid sequence letter for class edu.iastate.cs228.proj1.ProteinSequence"}.
	 *         Otherwise, the constructor saves a copy of the character array
	 *         argument in the field of its superclass {@link Sequence}.
	 * 
	 * @param psarr
	 *            See {@link #ProteinSequence(char[])}.
	 * @throws IllegalArgumentException
	 *             See {@link #ProteinSequence(char[])}.
	 */
	public ProteinSequence(char[] psarr) {
		// TODO
		super(psarr);
	}

	/**
	 * @author Mason Walls The method returns {@code true} if the character argument
	 *         is equal to one of the 20 English letters that are not in the set
	 *         {@code {B, b, J, j, O, o, U, u, X, x, Z, z}}. Otherwise, it returns
	 *         {@code false}. This method overrides the one in its superclass.
	 * 
	 * @param aa
	 *            See {@link #isValidLetter(char)}.
	 */
	@Override
	public boolean isValidLetter(char aa) {
		// TODO
		String temp = "BbJjOoUuXxZz";
		if (temp.contains("" + aa)) {
			return false;
		} else
			return true;
	}
}
