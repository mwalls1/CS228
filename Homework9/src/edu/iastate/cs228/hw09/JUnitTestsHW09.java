package edu.iastate.cs228.hw09;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.runners.MethodSorters;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;

/**
 * @author Cory Smith
 *  
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTestsHW09 {
	
	// This is a tree that I'll be using in my tests. It should look like the one from the "treeImpls_part2" presentation notes
	BinaryTree<Character> tree = tree('a', tree('b', tree('d'), tree('e')), tree('c', tree('f', null, tree('g')), null));
	
	// Everything from here to test 1 is stuff to let JUnit check the console output to test the Traverse methods
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test
	public void test1_PreorderTraverse() {
		tree.iterativePreorderTraverse();
		assertEquals("a b d e c f g ", outContent.toString());
	}
	
	@Test
	public void test2_PostorderTraverse() {
		tree.iterativePostorderTraverse();
		assertEquals("d e b g f c a ", outContent.toString());
	}
	
	@Test
	public void test3_LevelorderTraverse() {
		tree.iterativeLevelorderTraverse();
		assertEquals("a b c d e f g ", outContent.toString());
	}
	
	@Test
	public void test04_PreorderIterator() {
		ex.expect(NoSuchElementException.class);
		Iterator<Character> i = tree.getPreorderIterator();
		assertTrue(i.hasNext());
		assertEquals(new Character('a'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('b'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('d'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('e'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('c'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('f'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('g'), i.next());
		assertFalse(i.hasNext());
		i.next();
	}
	
	@Test
	public void test05_PostorderIterator() {
		ex.expect(NoSuchElementException.class);
		Iterator<Character> i = tree.getPostorderIterator();
		assertTrue(i.hasNext());
		assertEquals(new Character('d'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('e'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('b'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('g'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('f'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('c'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('a'), i.next());
		assertFalse(i.hasNext());
		i.next();
	}
	
	@Test
	public void test06_LevelorderIterator() {
		ex.expect(NoSuchElementException.class);
		Iterator<Character> i = tree.getLevelOrderIterator();
		assertTrue(i.hasNext());
		assertEquals(new Character('a'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('b'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('c'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('d'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('e'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('f'), i.next());
		assertTrue(i.hasNext());
		assertEquals(new Character('g'), i.next());
		assertFalse(i.hasNext());
		i.next();
	}
	
	// These methods make my tree creation slightly more bearable to look at, but aren't that important for the tests
	private BinaryTree<Character> tree(Character c) {
		return new BinaryTree<Character>(c);
	}
	
	private BinaryTree<Character> tree(Character c, BinaryTree<Character> t1, BinaryTree<Character> t2) {
		return new BinaryTree<Character>(c, t1, t2);
	}
}