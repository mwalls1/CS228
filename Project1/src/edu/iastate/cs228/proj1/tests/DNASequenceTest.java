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

public class DNASequenceTest {
	/**
	 * @author Mason Walls Tests the constructor method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testDNAConstructor() {
		boolean thrown = false;
		char[] code1 = { 'A', 'G', 'T' };
		try {
			DNASequence seq1 = new DNASequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertFalse(thrown);
		code1[1] = '&';
		try {
			DNASequence seq2 = new DNASequence(code1);
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
		DNASequence seq1 = new DNASequence(code1);
		assertTrue(seq1.isValidLetter('a'));
		assertFalse(seq1.isValidLetter('D'));
	}
}
