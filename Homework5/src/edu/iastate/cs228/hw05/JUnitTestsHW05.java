package edu.iastate.cs228.hw05;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

/*
 * @author Cory Smith
 * 
 * The ArrayPriorityQueue tests assume that your toString method is correct because that is the only way to get the contents of the queue
 * ^Same for LinkedPriorityQueue
 * As usual with the exercises, I can only check your results and not your implementation, so make sure you do it right.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTestsHW05 {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void test01_ArrayPriorityQueueAdd() {
		ArrayPriorityQueue<String> q = new ArrayPriorityQueue<String>();
		assertEquals("[]", q.toString());
		q.add("A");
		assertEquals("[A]", q.toString());
		q.add("B");
		assertEquals("[A, B]", q.toString());
		q.add("C");
		assertEquals("[A, B, C]", q.toString());
		q.add("B");
		assertEquals("[A, B, B, C]", q.toString());
	}
	
	@Test
	public void test02_ArrayPriorityQueueRemove() {
		ArrayPriorityQueue<String> q = new ArrayPriorityQueue<String>();
		assertEquals(null, q.remove());
		q.add("A");
		q.add("B");
		q.add("C");
		q.add("B");
		assertEquals("C", q.remove());
		assertEquals("B", q.remove());
		assertEquals("B", q.remove());
		assertEquals("A", q.remove());
		assertEquals(null, q.remove());
	}
	
	@Test
	public void test03_ArrayPriorityQueuePeek() {
		ArrayPriorityQueue<String> q = new ArrayPriorityQueue<String>();
		assertEquals(null, q.peek());
		q.add("A");
		assertEquals("A", q.peek());
		q.add("B");
		assertEquals("B", q.peek());
		q.add("A");
		assertEquals("B", q.peek());
		q.add("C");
		assertEquals("C", q.peek());
	}
	
	@Test
	public void test04_LinkedPriorityQueueAdd() {
		LinkedPriorityQueue<String> q = new LinkedPriorityQueue<String>();
		assertEquals("[]", q.toString());
		q.add("A");
		assertEquals("[A]", q.toString());
		q.add("B");
		assertEquals("[A, B]", q.toString());
		q.add("C");
		assertEquals("[A, B, C]", q.toString());
		q.add("B");
		assertEquals("[A, B, B, C]", q.toString());
	}
	
	@Test
	public void test05_LinkedPriorityQueueRemove() {
		LinkedPriorityQueue<String> q = new LinkedPriorityQueue<String>();
		assertEquals(null, q.remove());
		q.add("A");
		q.add("B");
		q.add("C");
		q.add("B");
		assertEquals("C", q.remove());
		assertEquals("B", q.remove());
		assertEquals("B", q.remove());
		assertEquals("A", q.remove());
		assertEquals(null, q.remove());
	}
	
	@Test
	public void test06_LinkedPriorityQueuePeek() {
		LinkedPriorityQueue<String> q = new LinkedPriorityQueue<String>();
		assertEquals(null, q.peek());
		q.add("A");
		assertEquals("A", q.peek());
		q.add("B");
		assertEquals("B", q.peek());
		q.add("A");
		assertEquals("B", q.peek());
		q.add("C");
		assertEquals("C", q.peek());
	}
	
	@Test
	public void test07_SortingExercisesSelectionSortRec() {
		int[] arr = new int[] {5, 3, 7, 2, 4, 1, 6};
		SortingExercises.selectionSort_Rec(arr);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7}, arr);
	}
	
	@Test
	public void test08_SortingExercisesInsertionSortRec() {
		int[] arr = new int[] {5, 3, 7, 2, 4, 1, 6};
		SortingExercises.insertionSort_Rec(arr);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7}, arr);
	}
	
	@Test
	public void test09_SortingExercisesSelectionSortItr() {
		int[] arr = new int[] {5, 3, 7, 2, 4, 1, 6};
		SortingExercises.selectionSort_Itr(arr);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7}, arr);
	}

	@Test
	public void test10_SortingExercisesBubbleSortItr() {
		String[] arr = new String[] {"E", "C", "G", "B", "D", "A", "F"};
		SortingExercises.bubbleSort_Itr(arr);
		assertArrayEquals(new String[] {"A", "B", "C", "D", "E", "F", "G"}, arr);
	}
	
	@Test
	public void test11_SortingExercisesBubbleSortRec() {
		int[] arr = new int[] {5, 3, 7, 2, 4, 1, 6};
		SortingExercises.bubbleSort_Rec(arr);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7}, arr);
	}
}