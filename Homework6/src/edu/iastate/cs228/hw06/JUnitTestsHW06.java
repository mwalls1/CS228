package edu.iastate.cs228.hw06;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

/**
 * @author Cory Smith
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTestsHW06 {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void test00_AListSwapGetLengthAndGetSize() {
		AList<String> l = new AList<String>();
		if (l.getLength() != 0 || l.getSize() != 26) {
			fail("If this test fails, then you have not swapped the implementation of getSize and getLength, "
					+ "and you will run into problems later on when trying to implement functions for AList."
					+ "All you have to do is swap the return values in AList's getSize() and getLength() methods,"
					+ "and you should be good. Take a look at the announcements section on Canvas for more details.");
		}
		
	}
	
	@Test
	public void test01_AListAddFirst() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"D", "C", "A", "B"};
		l.add("A");
		l.add("B");
		l.addFirst("C");
		l.addFirst("D");
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test02_AListAddLast() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		l.addLast("C");
		l.addLast("D");
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test03_AListRemoveFirst() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"C", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals("A", l.removeFirst());
		assertEquals("B", l.removeFirst());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test04_AListRemoveLast() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"A", "B"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals("D", l.removeLast());
		assertEquals("C", l.removeLast());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test05_AListGetFirst() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		assertEquals("A", l.getFirst());
		l.add("C");
		l.add("D");
		assertEquals("A", l.getFirst());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test06_AListGetLast() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		assertEquals("B", l.getLast());
		l.add("C");
		l.add("D");
		assertEquals("D", l.getLast());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test07_AListMoveToEnd() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] { "B", "C", "D", "A"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		l.moveToEnd();
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test08_AListRemove() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"A", "B", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertTrue(l.remove("C"));
		assertFalse(l.remove("E"));
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test09_AListGetPosition() {
		AList<String> l = new AList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals(1, l.getPosition("A"));
		assertEquals(2, l.getPosition("B"));
		assertEquals(3, l.getPosition("C"));
		assertEquals(4, l.getPosition("D"));
		assertEquals(-1, l.getPosition("E"));
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test10_AListRemoveFirstEx() {
		ex.expect(java.util.NoSuchElementException.class);
		AList<String> l = new AList<String>();
		l.removeFirst();
	}
	
	@Test
	public void test11_AListRemoveLastEx() {
		ex.expect(java.util.NoSuchElementException.class);
		AList<String> l = new AList<String>();
		l.removeLast();
	}
	
	@Test
	public void test12_AListGetFirstEx() {
		ex.expect(java.util.NoSuchElementException.class);
		AList<String> l = new AList<String>();
		l.getFirst();
	}
	
	@Test
	public void test13_AListGetLastEx() {
		ex.expect(java.util.NoSuchElementException.class);
		AList<String> l = new AList<String>();
		l.getLast();
	}
	
	@Test
	public void test14_LListAddFirst() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"D", "C", "A", "B"};
		l.add("A");
		l.add("B");
		l.addFirst("C");
		l.addFirst("D");
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test15_LListAddLast() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		l.addLast("C");
		l.addLast("D");
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test16_LListRemoveFirst() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"C", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals("A", l.removeFirst());
		assertEquals("B", l.removeFirst());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test17_LListRemoveLast() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"A", "B"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals("D", l.removeLast());
		assertEquals("C", l.removeLast());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test18_LListGetFirst() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		assertEquals("A", l.getFirst());
		l.add("C");
		l.add("D");
		assertEquals("A", l.getFirst());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test19_LListGetLast() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		assertEquals("B", l.getLast());
		l.add("C");
		l.add("D");
		assertEquals("D", l.getLast());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test20_LListMoveToEnd() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] { "B", "C", "D", "A"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		l.moveToEnd();
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test21_LListRemove() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"A", "B", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertTrue(l.remove("C"));
		assertFalse(l.remove("E"));
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test22_LListGetPosition() {
		LList<String> l = new LList<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals(1, l.getPosition("A"));
		assertEquals(2, l.getPosition("B"));
		assertEquals(3, l.getPosition("C"));
		assertEquals(4, l.getPosition("D"));
		assertEquals(-1, l.getPosition("E"));
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test23_LListRemoveFirstEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LList<String> l = new LList<String>();
		l.removeFirst();
	}
	
	@Test
	public void test24_LListRemoveLastEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LList<String> l = new LList<String>();
		l.removeLast();
	}
	
	@Test
	public void test25_LListGetFirstEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LList<String> l = new LList<String>();
		l.getFirst();
	}
	
	@Test
	public void test26_LListGetLastEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LList<String> l = new LList<String>();
		l.getLast();
	}
	
	@Test
	public void test27_LListWithTailAddFirst() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"D", "C", "A", "B"};
		l.add("A");
		l.add("B");
		l.addFirst("C");
		l.addFirst("D");
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test28_LListWithTailAddLast() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		l.addLast("C");
		l.addLast("D");
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test29_LListWithTailRemoveFirst() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"C", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals("A", l.removeFirst());
		assertEquals("B", l.removeFirst());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test30_LListWithTailRemoveLast() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"A", "B"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals("D", l.removeLast());
		assertEquals("C", l.removeLast());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test31_LListWithTailGetFirst() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		assertEquals("A", l.getFirst());
		l.add("C");
		l.add("D");
		assertEquals("A", l.getFirst());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test32_LListWithTailGetLast() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		assertEquals("B", l.getLast());
		l.add("C");
		l.add("D");
		assertEquals("D", l.getLast());
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test33_LListWithTailMoveToEnd() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] { "B", "C", "D", "A"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		l.moveToEnd();
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test34_LListWithTailRemove() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"A", "B", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertTrue(l.remove("C"));
		assertFalse(l.remove("E"));
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test35_LListWithTailGetPosition() {
		LListWithTail<String> l = new LListWithTail<String>();
		String[] expected = new String[] {"A", "B", "C", "D"};
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		assertEquals(1, l.getPosition("A"));
		assertEquals(2, l.getPosition("B"));
		assertEquals(3, l.getPosition("C"));
		assertEquals(4, l.getPosition("D"));
		assertEquals(-1, l.getPosition("E"));
		assertArrayEquals(expected, l.toArray());
	}
	
	@Test
	public void test36_LListWithTailRemoveFirstEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LListWithTail<String> l = new LListWithTail<String>();
		l.removeFirst();
	}
	
	@Test
	public void test37_LListWithTailRemoveLastEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LListWithTail<String> l = new LListWithTail<String>();
		l.removeLast();
	}
	
	@Test
	public void test38_LListWithTailGetFirstEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LListWithTail<String> l = new LListWithTail<String>();
		l.getFirst();
	}
	
	@Test
	public void test39_LListWithTailGetLastEx() {
		ex.expect(java.util.NoSuchElementException.class);
		LListWithTail<String> l = new LListWithTail<String>();
		l.getLast();
	}
	
	@Test
	public void test40_SortingExercises() {
		int[] arr1 = new int[] {1, 3, 5, 7, 9, 8, 6, 4, 2};
		SortingExercise.modifiedQuickSort(arr1);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, arr1);
		
		int[] arr2 = new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 22, 20, 18, 16, 14, 12, 10, 8, 6, 4, 2};
		SortingExercise.modifiedQuickSort(arr2);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23}, arr2);
		
		int[] arr3 = new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 24, 22, 20, 18, 16, 14, 12, 10, 8, 6, 4, 2};
		SortingExercise.modifiedQuickSort(arr3);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24}, arr3);
		
		int[] arr4 = new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 50, 48, 46, 44, 42, 40, 38, 36, 34, 32, 30, 28, 26, 24, 22, 20, 18, 16, 14, 12, 10, 8, 6, 4, 2};
		SortingExercise.modifiedQuickSort(arr4);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}, arr4);
	}

}