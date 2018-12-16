package edu.iastate.cs228.hw07;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Cory Smith
 * 
 * There is no way for me to directly test the Linked bag constructor, so errors there may make other tests fail
 * I will be assuming that nextIndex and previousIndex are correct in ResizableArrayBag in order to test the other methods, 
 * 		so errors there may make other tests fail
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTestHW07 {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void test01_LinkedBagHasNext() {
		LinkedBag<String> b = new LinkedBag<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		Iterator<String> i = b.iterator();
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		assertTrue(i.hasNext());
		i.next();
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test02_LinkedBagNext() {
		LinkedBag<String> b = new LinkedBag<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		Iterator<String> i = b.iterator();
		assertEquals("C", i.next());
		assertEquals("B", i.next());
		assertEquals("A", i.next());
	}
	
	@Test
	public void test03_LinkedBagNextEx() {
		ex.expect(NoSuchElementException.class);
		LinkedBag<String> b = new LinkedBag<String>();
		b.add("A");
		Iterator<String> i = b.iterator();
		i.next();
		i.next();
	}
	
	@Test
	public void test04_LinkedBagRemove() {
		LinkedBag<String> b = new LinkedBag<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		Iterator<String> i = b.iterator();
		i.next();
		String s = i.next();
		i.remove();
		assertFalse(b.contains(s));
	}
	
	@Test
	public void test05_LinkedBagRemoveEx() {
		ex.expect(IllegalStateException.class);
		LinkedBag<String> b = new LinkedBag<String>();
		b.add("A");
		Iterator<String> i = b.iterator();
		i.remove();
	}
	
	@Test
	public void test06_ResizableArrayBagIteratorConstructor() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator();
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
		
		b.add("A");
		b.add("B");
		
		i = b.listIterator();
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
	}
	
	@Test
	public void test07_ResizableArrayBagIteratorConstructorWithIndex() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator(0);
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
		
		b.add("A");
		b.add("B");
		
		i = b.listIterator(1);
		assertEquals(0, i.previousIndex());
		assertEquals(1, i.nextIndex());
	}
	
	@Test
	public void test08_ResizableArrayBagIteratorConstructorWithIndexEx1() {
		ex.expect(IndexOutOfBoundsException.class);
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator(-1);
	}
	
	@Test
	public void test09_ResizableArrayBagIteratorConstructorWithIndexEx2() {
		ex.expect(IndexOutOfBoundsException.class);
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		b.add("A");
		b.add("B");
		ListIterator<String> i = b.listIterator(3);
	}
	
	@Test
	public void test10_ResizableArrayBagHasNext() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		assertTrue(i.hasNext());
		i.next();
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test11_ResizableArrayBagHasPrevious() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		ListIterator<String> i = b.listIterator(3);
		assertTrue(i.hasPrevious());
		i.previous();
		assertTrue(i.hasPrevious());
		i.previous();
		assertTrue(i.hasPrevious());
		assertTrue(i.hasPrevious());
		i.previous();
		assertFalse(i.hasPrevious());
	}
	
	@Test
	public void test12_ResizableArrayBagNext() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		assertEquals("A", i.next());
		assertEquals("B", i.next());
		assertEquals("C", i.next());
	}
	
	@Test
	public void test13_ResizableArrayBagNextEx() {
		ex.expect(NoSuchElementException.class);
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		i.next();
		i.next();
		i.next();
		i.next();
	}
	
	@Test
	public void test14_ResizableArrayBagPrevious() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		ListIterator<String> i = b.listIterator(2);
		assertEquals("C", i.previous());
		assertEquals("B", i.previous());
		assertEquals("A", i.previous());
	}
	
	@Test
	public void test15_ResizableArrayBagPreviousEx() {
		ex.expect(NoSuchElementException.class);
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		ListIterator<String> i = b.listIterator(3);
		i.previous();
		i.previous();
		i.previous();
		i.previous();
	}
	
	@Test
	public void test16_ResizableArrayBagRemove() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		i.next();
		String s = i.next();
		i.remove();
		assertFalse(b.contains(s));
	}
	
	@Test
	public void test17_ResizableArrayBagRemoveEx() {
		ex.expect(IllegalStateException.class);
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator();
		i.remove();
	}
	
	@Test
	public void test18_ResizableArrayBagSet() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		i.next();
		i.next();
		i.set("D");
		assertEquals("D", i.previous());
		i.set("E");
		assertEquals("E", i.next());
	}
	
	@Test
	public void test19_ResizableArrayBagAdd() {
		ResizableArrayBag<String> b = new ResizableArrayBag<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		ListIterator<String> i = b.listIterator(2);
		String next = i.next();
		i.previous();
		String prev = i.previous();
		i.next();
		i.add("D");
		assertEquals("nextIndex and previousIndex should be increased by 1 after the add method according to the docs", 2, i.previousIndex());
		assertEquals("nextIndex and previousIndex should be increased by 1 after the add method according to the docs", 3, i.nextIndex());
		assertEquals("D", i.next());
		assertEquals(null, i.previous());
		assertEquals("D", i.previous());
		assertEquals(prev, i.previous());
	}
	
	@Test
	public void test20_SearchingExercisesFindMinInterval() {
		assertArrayEquals(new Integer[] {-1, 5}, 
				SearchingExercises.findMinInterval(new Integer[] {5, 8, 10, 13, 15, 20, 22, 26}, Arrays.asList(new Integer[] {8, 2, 9, 17})));
	}
	
	@Test
	public void test21_SearchingExercisesFind2D() {
		Integer[][] t = new Integer[][] {{1, 4, 55, 88}, {7, 15, 61, 91}, {14, 89, 90, 99}};
		assertTrue(SearchingExercises.find2D(t, 61));
		assertFalse(SearchingExercises.find2D(t, 12));
	}

}
