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

public class GenomicDNASequenceTest {
	/**
	 * @author Mason Walls Tests the Construcotr method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testGenomicConstructor() {
		boolean thrown = false;
		char[] code1 = { 'A', 'G', 'T' };
		try {
			GenomicDNASequence seq1 = new GenomicDNASequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertFalse(thrown);
		code1[1] = '&';
		try {
			GenomicDNASequence seq2 = new GenomicDNASequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * @author Mason Walls Tests the MarkCoding method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testMarkCoding() {
		boolean thrown = false;
		char[] code1 = { 'A', 'G', 'T' };
		GenomicDNASequence seq1 = new GenomicDNASequence(code1);
		try {
			seq1.markCoding(-1, 1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		code1[1] = '&';
		try {
			seq1.markCoding(1, 2);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertFalse(thrown);
		thrown = false;
		try {
			seq1.markCoding(1, 5);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			seq1.markCoding(5, 1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
	}

	/**
	 * @author Mason Walls Tests the ExtractExons method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testExtractExons() {
		boolean thrown = false;
		char[] code1 = { 'A', 'G', 'T', 'C', 'A' };
		char[] ans = { 'G', 'T', 'C' };
		int[] exonF = { 1, 3 };
		int[] exon0 = {};
		int[] exon1 = { 1 };
		int[] exon2 = { -1, 2 };
		int[] exon3 = { 1, 10 };
		int[] exon4 = { 2, 1 };
		GenomicDNASequence seq1 = new GenomicDNASequence(code1);
		seq1.markCoding(1, 3);
		try {
			seq1.extractExons(exon0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			seq1.extractExons(exon1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			seq1.extractExons(exon2);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			seq1.extractExons(exon3);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			seq1.extractExons(exon4);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			seq1.extractExons(exonF);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertFalse(thrown);
		thrown = false;
		assertArrayEquals(ans, seq1.extractExons(exonF));
		exonF[1] = 4;
		seq1.markCoding(1, 4);
		assertFalse(Arrays.equals(ans, seq1.extractExons(exonF)));

	}

}
