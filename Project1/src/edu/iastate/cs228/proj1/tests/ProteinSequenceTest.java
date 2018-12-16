package edu.iastate.cs228.proj1.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import edu.iastate.cs228.proj1.*;

/**
 * @author Mason Walls
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ProteinSequenceTest {
	/**
	 * @author Mason Walls Tests the constructor method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testProteinConstructor() {
		boolean thrown = false;
		char[] code1 = { 'A', 'G', 'T' };
		try {
			ProteinSequence seq1 = new ProteinSequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertFalse(thrown);
		thrown = false;
		code1[1] = '&';
		try {
			Sequence seq2 = new Sequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * @author Mason Walls Tests the isValidLetter method for all errors and
	 *         expected outputs.
	 */
	@Test
	public void testIsValidLetter() {
		char[] code1 = { 'A', 'G', 'T' };
		ProteinSequence seq1 = new ProteinSequence(code1);
		assertFalse(seq1.isValidLetter('B'));
		assertTrue(seq1.isValidLetter('A'));
	}

}
