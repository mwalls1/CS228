package edu.iastate.cs228.proj4;
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
 * @author Mason Walls
 *  
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEntryTree {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	@Test
	public void test1_add() {
		EntryTree<String, String> myTree = new EntryTree<String, String>();
		String[] keys = {"A","B","C"};
		String value = "Computer";
		assertTrue(myTree.add(keys, value));
		assertEquals("Computer",myTree.search(keys));
		value = "Screen";
		assertTrue(myTree.add(keys, value));
		assertEquals("Screen",myTree.search(keys));
		String[] keys1 = {"A"};
		assertTrue(myTree.add(keys1, value));
		String[] keys2 = {};
		assertFalse(myTree.add(keys2, value));
		String[] keys3 = null;
		assertFalse(myTree.add(keys3, value));
		value = null;
		assertFalse(myTree.add(keys, value));
		keys[1] = null;
		value = "Computer";
		ex.expect(NullPointerException.class);
		myTree.add(keys, value);
	}
	@Test
	public void test2_search() {
		EntryTree<String, String> myTree = new EntryTree<String, String>();
		String[] keys = {"A","B","C"};
		String value = "Computer";
		myTree.add(keys, value);
		keys[0] = "Z";
		value = "Processor";
		myTree.add(keys, value);
		keys[1] = "Q";
		value = "RAM";
		myTree.add(keys, value);
		assertEquals("RAM",myTree.search(keys));
		keys[1] = "B";
		assertEquals("Processor",myTree.search(keys));
		keys[0] = "A";
		assertEquals("Computer",myTree.search(keys));
		keys[2] = "R";
		assertEquals(null,myTree.search(keys));
		String[] keys1 = {};
		assertEquals(null,myTree.search(keys1));
		keys1 = null;
		assertEquals(null, myTree.search(keys1));
		ex.expect(NullPointerException.class);
		keys[0] = null;
		myTree.search(keys);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void test3_prefix()
	{
			EntryTree<String, String> myTree = new EntryTree<String, String>();
			String[] keys = {"A","B","C","D"};
			String value = "Computer";
			String[] prefixs = {"A","B","C","D","E","F","G"};
			myTree.add(keys, value);
			assertArrayEquals(keys,myTree.prefix(prefixs));
			String[] arr = {};
			prefixs[0] = "Z";
			assertEquals(null,myTree.prefix(prefixs));
			assertEquals(null,myTree.prefix(arr));
			arr = null;
			assertEquals(null,myTree.prefix(prefixs));
			keys[3] = null;
			ex.expect(NullPointerException.class);
			myTree.prefix(keys);
	}
	@Test
	public void test4_remove()
	{
		EntryTree<String, String> myTree = new EntryTree<String, String>();
		String[] keys = {"A","B","C","D"};
		String[] keys1 = {"A","B","D","C"};
		String[] keys2 = {"B","C","D","A"};
		String[] keys3 = {};
		String[] keys4 = null;
		String[] keys5 = {"B",null,"D","A"};
		String value = "Keyboard";
		String value1 = "Mouse";
		String value2 = "Monitor";
		myTree.add(keys, value);
		myTree.add(keys2, value2);
		myTree.add(keys1, value1);
		assertEquals(null,myTree.remove(keys3));
		assertEquals(null,myTree.remove(keys4));
		assertEquals(value,myTree.remove(keys));
		assertEquals(null,myTree.remove(keys));
		assertEquals(value1,myTree.remove(keys1));
		assertEquals(value2,myTree.remove(keys2));
		ex.expect(NullPointerException.class);
		myTree.remove(keys5);
		
	}
	@Test
	public void test5_getAll()
	{
		EntryTree<String, String> myTree = new EntryTree<String, String>();
		String[] keys = {"A","B","C","D"};
		String keyString = "ABCD";
		String[] keys1 = {"A","B","D","C"};
		String keyString1 = "ABDC";
		String[] keys2 = {"B","C","D"};
		String keyString2 = "BCD";
		assertEquals(null,myTree.getAll());
		String value = "Keyboard";
		String value1 = "Mouse";
		String value2 = "Monitor";
		myTree.add(keys, value);
		myTree.add(keys2, value2);
		myTree.add(keys1, value1);
		String[][] test1 = new String[3][2];
		test1[0][0] = keyString2;
		test1[0][1] = value2;
		test1[1][0] = keyString;
		test1[1][1] = value;
		test1[2][0] = keyString1;
		test1[2][1] = value1;
		assertArrayEquals(test1,myTree.getAll());
		String[][] test2 = new String[2][2];
		test2[0][0] = keyString2;
		test2[0][1] = value2;
		test2[1][0] = keyString;
		test2[1][1] = value;
		myTree.remove(keys1);
		assertArrayEquals(test2,myTree.getAll());
	}
}
