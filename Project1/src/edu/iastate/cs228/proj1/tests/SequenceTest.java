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
public class SequenceTest {
	/**
	 * @author Mason Walls Tests the constructor method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testSequenceConstrucotr() {
		boolean thrown = false;
		char[] code1 = { 'A', 'G', 'T' };
		try {
			Sequence seq1 = new Sequence(code1);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertFalse(thrown);
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
	public void testisValidLetter() {
		char[] code1 = { 'A', 'G', 'T' };
		Sequence seq1 = new Sequence(code1);
		assertTrue(seq1.isValidLetter('c'));
		assertTrue(seq1.isValidLetter('C'));
		assertFalse(seq1.isValidLetter('&'));
	}

	/**
	 * @author Mason Walls Tests the SequenceLength method for all errors and
	 *         expected outputs.
	 */
	@Test
	public void testSequenceLength() {
		char[] code1 = { 'A', 'G', 'T' };
		Sequence seq1 = new Sequence(code1);
		assertEquals(3, seq1.seqLength());
	}

	/**
	 * @author Mason Walls Tests the getSequence method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testGetSequence() {
		char[] code1 = { 'A', 'G', 'T' };
		Sequence seq1 = new Sequence(code1);
		assertArrayEquals(code1, seq1.getSeq());
		char[] code2 = { 'A', 'D', 'T' };
		Sequence seq2 = new Sequence(code2);
		assertFalse(Arrays.equals(seq1.getSeq(), seq2.getSeq()));
	}

	/**
	 * @author Mason Walls Tests the toString method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testToString() {
		String chars = "AGT";
		char[] code1 = { 'A', 'G', 'T' };
		Sequence seq1 = new Sequence(code1);
		assertEquals(chars, seq1.toString());
		char[] code2 = { 'A', 'D', 'T' };
		Sequence seq2 = new Sequence(code2);
		assertFalse(chars.equals(seq2.toString()));
	}

	/**
	 * @author Mason Walls Tests the equals method for all errors and expected
	 *         outputs.
	 */
	@Test
	public void testEquals() {
		char[] code1 = { 'A', 'T', 'G' };
		char[] code2 = { 'A', 'T', 'G' };
		char[] code3 = { 'A', 'T', 'G', 'A' };
		Sequence seq1 = new Sequence(code1);
		Sequence seq2 = new Sequence(code2);
		Sequence seq3 = new Sequence(code3);
		ProteinSequence pseq1 = new ProteinSequence(code1);
		ProteinSequence pseq2 = new ProteinSequence(code2);
		ProteinSequence pseq3 = new ProteinSequence(code3);
		GenomicDNASequence gseq1 = new GenomicDNASequence(code1);
		GenomicDNASequence gseq2 = new GenomicDNASequence(code2);
		GenomicDNASequence gseq3 = new GenomicDNASequence(code3);
		DNASequence dseq1 = new DNASequence(code1);
		DNASequence dseq2 = new DNASequence(code2);
		DNASequence dseq3 = new DNASequence(code3);
		CodingDNASequence cseq1 = new CodingDNASequence(code1);
		CodingDNASequence cseq2 = new CodingDNASequence(code2);
		CodingDNASequence cseq3 = new CodingDNASequence(code3);
		// Sequence to Sequence
		assertTrue(seq1.equals(seq2));
		assertFalse(seq1.equals(seq3));
		// Sequence to Protein
		assertTrue(seq1.equals(pseq2));
		assertFalse(seq1.equals(pseq3));
		// Sequence to Genomic
		assertTrue(seq1.equals(gseq2));
		assertFalse(seq1.equals(gseq3));
		// Sequence to DNA
		assertTrue(seq1.equals(dseq2));
		assertFalse(seq1.equals(dseq3));
		// Sequence to coding
		assertTrue(seq1.equals(cseq2));
		assertFalse(seq1.equals(cseq3));
		// Protein to Protein
		assertTrue(pseq1.equals(pseq2));
		assertFalse(pseq1.equals(pseq3));
		// Protein to Genomic
		assertTrue(pseq1.equals(gseq2));
		assertFalse(pseq1.equals(gseq3));
		// Protein to DNA
		assertTrue(pseq1.equals(dseq2));
		assertFalse(pseq1.equals(dseq3));
		// Protein to Coding
		assertTrue(pseq1.equals(cseq2));
		assertFalse(pseq1.equals(cseq3));
		// Genomic to Genomic
		assertTrue(gseq1.equals(gseq2));
		assertFalse(gseq1.equals(gseq3));
		// Genomic to DNA
		assertTrue(gseq1.equals(dseq2));
		assertFalse(gseq1.equals(dseq3));
		// Genomic to Coding
		assertTrue(gseq1.equals(cseq2));
		assertFalse(gseq1.equals(cseq3));
		// Coding to coding
		assertTrue(cseq1.equals(cseq2));
		assertFalse(cseq1.equals(cseq3));

	}
}
