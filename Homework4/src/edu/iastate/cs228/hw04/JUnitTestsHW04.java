package edu.iastate.cs228.hw04;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

/*
 * @author Cory Smith
 * 
 * The CircularDoublyLinkedDeque tests assume that getFront() and getBack() work as they should
 * The CircularLinkedQueue tests assume that getFront() works properly
 * If you fail everything, take a look at those
 * 
 * For the recursionExercises tests, they can only make sure that the answer is right, not that the process is right
 * Make sure you use recursion to complete those methods
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTestsHW04 {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void test01_CircularDoublyLinkedDequeAddToFront() {
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.addToFront("A");
		assertEquals("A", q.getFront());
		assertEquals("A", q.getBack());
		q.addToFront("B");
		assertEquals("B", q.getFront());
		assertEquals("A", q.getBack());
		q.addToFront("C");
		assertEquals("C", q.getFront());
		assertEquals("A", q.getBack());
		q.addToFront("A");
		assertEquals("A", q.getFront());
		assertEquals("A", q.getBack());
	}
	
	@Test
	public void test02_CircularDoublyLinkedDequeAddToBack() {
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.addToBack("A");
		assertEquals("A", q.getBack());
		assertEquals("A", q.getFront());
		q.addToBack("B");
		assertEquals("B", q.getBack());
		assertEquals("A", q.getFront());
		q.addToBack("C");
		assertEquals("C", q.getBack());
		assertEquals("A", q.getFront());
		q.addToBack("A");
		assertEquals("A", q.getBack());
		assertEquals("A", q.getFront());
	}
	
	@Test
	public void test03_CircularDoublyLinkedDequeGetFrontEx() {
		ex.expect(EmptyQueueException.class);
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.getFront();
	}
	
	@Test
	public void test04_CircularDoublyLinkedDequeGetBackEx() {
		ex.expect(EmptyQueueException.class);
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.getBack();
	}
	
	@Test
	public void test05_CircularDoublyLinkedDequeRemoveFront() {
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.addToFront("A");
		q.addToFront("B");
		q.addToFront("C");
		q.addToFront("A");
		
		assertEquals("A", q.removeFront());
		assertEquals("C", q.removeFront());
		assertEquals("B", q.removeFront());
		assertEquals("A", q.removeFront());
	}
	
	@Test
	public void test06_CircularDoublyLinkedDequeRemoveFrontEx() {
		ex.expect(EmptyQueueException.class);
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.removeFront();
	}
	
	@Test
	public void test07_CircularDoublyLinkedDequeRemoveBack() {
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.addToBack("A");
		q.addToBack("B");
		q.addToBack("C");
		q.addToBack("A");
		
		assertEquals("A", q.removeBack());
		assertEquals("C", q.removeBack());
		assertEquals("B", q.removeBack());
		assertEquals("A", q.removeBack());
	}
	
	@Test
	public void test08_CircularDoublyLinkedDequeRemoveBackEx() {
		ex.expect(EmptyQueueException.class);
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.removeBack();
	}
	
	@Test
	public void test09_CircularDoublyLinkedDequeIsEmpty() {
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		assertTrue(q.isEmpty());
		q.addToFront("A");
		assertFalse(q.isEmpty());
		q.removeFront();
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void test10_CircularDoublyLinkedDequeClear() {
		CircularDoublyLinkedDeque<String> q = new CircularDoublyLinkedDeque<String>();
		q.addToFront("A");
		q.addToFront("A");
		q.addToFront("A");
		q.addToFront("A");
		q.addToFront("A");
		q.clear();
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void test11_CircularLinkedQueueEnqueue() {
		CircularLinkedQueue<String> q = new CircularLinkedQueue<String>();
		q.enqueue("A");
		assertEquals("A", q.getFront());
		q.enqueue("B");
		assertEquals("A", q.getFront());
	}
	
	@Test
	public void test12_CircularLinkedQueueDequeue() {
		CircularLinkedQueue<String> q = new CircularLinkedQueue<String>();
		q.enqueue("A");
		q.enqueue("B");
		q.enqueue("C");
		q.enqueue("D");
		assertEquals("A", q.dequeue());
		assertEquals("B", q.dequeue());
		assertEquals("C", q.dequeue());
		assertEquals("D", q.dequeue());
	}
	
	@Test
	public void test13_CircularLinkedQueueDequeueEx() {
		ex.expect(EmptyQueueException.class);
		CircularLinkedQueue<String> q = new CircularLinkedQueue<String>();
		q.dequeue();
	}
	
	@Test
	public void test14_CircularLinkedQueueIsEmpty() {
		CircularLinkedQueue<String> q = new CircularLinkedQueue<String>();
		assertTrue(q.isEmpty());
		q.enqueue("A");
		assertFalse(q.isEmpty());
		q.enqueue("A");
		assertFalse(q.isEmpty());
		q.dequeue();
		assertFalse(q.isEmpty());
		q.dequeue();
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void test15_CircularLinkedQueueClear() {
		CircularLinkedQueue<String> q = new CircularLinkedQueue<String>();
		q.enqueue("A");
		q.enqueue("B");
		q.enqueue("C");
		q.enqueue("D");
		q.clear();
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void test16_RecursionExercisesDisplayRowOfCharacters() {
		assertEquals("AAA", RecursionExercises.displayRowOfCharacters('A', 3));
		assertEquals("~~~~~~", RecursionExercises.displayRowOfCharacters('~', 6));
		assertEquals(" ", RecursionExercises.displayRowOfCharacters(' ', 1));
	}
	
	@Test
	public void test17_RecursionExercisesDisplayBackwards() {
		assertEquals("321", RecursionExercises.displayBackwards(new int[] {1, 2, 3}, 3));
		assertEquals("4-321", RecursionExercises.displayBackwards(new int[] {1, 2, -3, 4, 5}, 4));
		assertEquals("1", RecursionExercises.displayBackwards(new int[] {1, 2}, 1));
	}
	
	@Test
	public void test18_RecursionExercisesIsPalindrome() {
		assertTrue(RecursionExercises.isPalindrome("redder"));
		assertTrue(RecursionExercises.isPalindrome("racecar"));
		assertFalse(RecursionExercises.isPalindrome("abcdcbb"));
	}

	@Test
	public void test19_RecursionExercisesGetSecondSmallest() {
		assertEquals(new Integer(2), RecursionExercises.getSecondSmallest(new Integer[]{1,  2, 3}, 3));
		assertEquals(new Integer(10), RecursionExercises.getSecondSmallest(new Integer[]{12, 10, 34, 16, 28, 92, -455}, 7));
		assertEquals(new Integer(2), RecursionExercises.getSecondSmallest(new Integer[]{1,  2, 3, 4}, 4));
		assertEquals(new Integer(-93), RecursionExercises.getSecondSmallest(new Integer[]{12, 10, 34, 16, 28, -92, -455, -93}, 8));
		assertEquals(new Integer(2), RecursionExercises.getSecondSmallest(new Integer[]{2,  2, 3}, 3));
		assertEquals(new Integer(2), RecursionExercises.getSecondSmallest(new Integer[]{2,  2, 3, 4}, 4));
	}
}