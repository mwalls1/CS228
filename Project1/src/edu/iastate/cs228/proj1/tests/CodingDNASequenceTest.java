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

public class CodingDNASequenceTest {
	/**
	 * @author Mason Walls Tests the Constructor method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testCodingDNAConstructor() {
		boolean thrown = false;
		char[] code1 = { 'A', 'G', 'T' };
		try {
			CodingDNASequence seq1 = new CodingDNASequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertFalse(thrown);
		code1[1] = '&';
		try {
			CodingDNASequence seq2 = new CodingDNASequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * @author Mason Walls Tess the CheckStartCodon method for all errors and
	 *         expected outputs.
	 */
	@Test
	public void testCheckStartCodon() {
		char[] code1 = { 'A', 'T', 'G' };
		CodingDNASequence seq1 = new CodingDNASequence(code1);
		assertTrue(seq1.checkStartCodon());
		char[] code2 = { 'A', 'T' };
		seq1 = new CodingDNASequence(code2);
		assertFalse(seq1.checkStartCodon());
		char[] code3 = { 'A', 'C', 'T' };
		seq1 = new CodingDNASequence(code3);
		assertFalse(seq1.checkStartCodon());
	}

	/**
	 * @author Mason Walls Tests the translate method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testTranslate() {
		boolean thrown = false;
		char[] code2 = { 'A', 'T' };
		CodingDNASequence seq1 = new CodingDNASequence(code2);
		try {
			seq1.translate();
		} catch (RuntimeException e) {
			thrown = true;
		}
		assertTrue(true);
		thrown = false;
		char[] code3 = { 'A', 'C', 'T' };
		seq1 = new CodingDNASequence(code3);
		try {
			seq1.translate();
		} catch (RuntimeException e) {
			thrown = true;
		}
		assertTrue(true);
		char[] code4 = { 'A', 'T', 'G', 'T', 'G', 'A' };
		char[] expected = { 'M' };
		seq1 = new CodingDNASequence(code4);
		assertArrayEquals(expected, seq1.translate());
		char[] code5 = { 'A', 'T', 'G', 'T' };
		char[] expected1 = { 'M' };
		seq1 = new CodingDNASequence(code5);
		assertArrayEquals(expected1, seq1.translate());

	}

}
