package edu.iastate.cs228.proj3;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Mason Walls
 * 
 * There is no way for me to directly test the Linked bag constructor, so errors there may make other tests fail
 * I will be assuming that nextIndex and previousIndex are correct in ResizableArrayBag in order to test the other methods, 
 * 		so errors there may make other tests fail
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAdaptiveList {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void test01_AdaptiveListCollectionConstructor() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertTrue(myList.getlinkedUTD());
		assertFalse(myList.getarrayUTD());
		assertArrayEquals(tempList,myList.toArray());
	}
	@Test
	public void test02_AdaptiveListCollectionConstructor() {
		List<String> temp = new ArrayList<String>();
		String[] tempList = null;
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertTrue(myList.getlinkedUTD());
		assertFalse(myList.getarrayUTD());
		assertArrayEquals(tempList,myList.toArray());
	}
	@Test
	public void test01_AdaptiveListUpdateArray() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertEquals(3,myList.size());
	}
	@Test
	public void test01_AdaptiveListIsEmpty() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>();
		assertTrue(myList.isEmpty());
		myList.addAll(temp);
		assertFalse(myList.isEmpty());
	}
	@Test
	public void test01_AdaptiveListAdd() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		assertArrayEquals(tempList,myList.toArray());
	}
	@Test
	public void test01_AdaptiveListAddAll() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>();
		assertTrue(myList.isEmpty());
		assertTrue(myList.addAll(temp));
		assertFalse(myList.isEmpty());
		assertArrayEquals(tempList,myList.toArray());
		List<String> temp2 = new ArrayList<String>();
		String[] tempList2 = null;
		AdaptiveList<String> myList2 = new AdaptiveList<String>();
		assertFalse(myList.addAll(temp2));
		assertTrue(myList.getlinkedUTD());
		assertFalse(myList.getarrayUTD());
		assertArrayEquals(tempList2,myList2.toArray());
	}
	@Test
	public void test01_AdaptiveListRemove() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","C"};
		String[] tempList2 = {"B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		assertArrayEquals(tempList,myList.toArray());
		assertTrue(myList.remove("A"));
		assertArrayEquals(tempList2,myList.toArray());
		assertFalse(myList.remove("D"));
		assertArrayEquals(tempList2,myList.toArray());
	}
	@Test
	public void test02_AdaptiveListAddPOS() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","C"};
		String[] tempList2 = {"A","D","B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		assertArrayEquals(tempList,myList.toArray());
		myList.add(1,"D");
		assertArrayEquals(tempList2,myList.toArray());
		ex.expect(IndexOutOfBoundsException.class);
		myList.add(11,"D");
	}
	@Test
	public void test02_AdaptiveListAddAllPOS() {
		List<String> temp = Arrays.asList("A","B","C");
		List<String> temp2 = new ArrayList<String>();
		String[] tempList = {"A","B","C"};
		String[] tempList2 = {"A","B","A","B","C","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		assertArrayEquals(tempList,myList.toArray());
		assertTrue(myList.addAll(2,temp));
		assertArrayEquals(tempList2,myList.toArray());
		ex.expect(IndexOutOfBoundsException.class);
		myList.addAll(11,temp);
		assertFalse(myList.addAll(1,temp2));
	}
	@Test
	public void test02_AdaptiveListRemovePOS() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertEquals("C",myList.remove(2));
		assertArrayEquals(tempList,myList.toArray());
		ex.expect(IndexOutOfBoundsException.class);
		myList.remove(11);
		myList.remove(-1);
	}
	@Test
	public void test01_AdaptiveListGetPos() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertEquals("C",myList.get(2));
		ex.expect(IndexOutOfBoundsException.class);
		myList.get(5);
		myList.get(-1);
	}
	@Test
	public void test01_AdaptiveListSetPos() {
		List<String> temp = Arrays.asList("A","B","C");
		String[] tempList = {"A","B","Z"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertEquals("C",myList.set(2,"Z"));
		assertArrayEquals(tempList,myList.toArray());
		ex.expect(IndexOutOfBoundsException.class);
		myList.set(7,"D");
		myList.set(-1,"D");
	}
	@Test
	public void test01_AdaptiveListReverse() {
		List<String> temp = Arrays.asList("A","B","C","D","E");
		String[] tempList = {"E","D","C","B","A"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertTrue(myList.reverse());
		assertArrayEquals(tempList,myList.toArray());
		List<String> temp2 = Arrays.asList("A","B","C","D");
		String[] tempList2 = {"D","C","B","A"};
		AdaptiveList<String> myList2 = new AdaptiveList<String>(temp2);
		assertTrue(myList2.reverse());
		assertArrayEquals(tempList2,myList2.toArray());
		List<String> temp3 = Arrays.asList("A");
		String[] tempList3 = {"A"};
		AdaptiveList<String> myList3= new AdaptiveList<String>(temp3);
		assertFalse(myList3.reverse());
		assertArrayEquals(tempList3,myList3.toArray());
		List<String> temp4 = new ArrayList<String>();
		String[] tempList4 = null;
		AdaptiveList<String> myList4= new AdaptiveList<String>(temp4);
		assertFalse(myList4.reverse());
		assertArrayEquals(tempList4,myList4.toArray());

	}
	@Test
	public void test01_AdaptiveListReorder() {
		List<String> temp = Arrays.asList("A","B","C","D","E");
		String[] tempList = {"B","A","D","C","E"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertTrue(myList.reorderOddEven());
		assertArrayEquals(tempList,myList.toArray());
		List<String> temp2 = Arrays.asList("A","B","C","D");
		String[] tempList2 = {"B","A","D","C"};
		AdaptiveList<String> myList2 = new AdaptiveList<String>(temp2);
		assertTrue(myList2.reorderOddEven());
		assertArrayEquals(tempList2,myList2.toArray());
		List<String> temp3 = Arrays.asList("A");
		String[] tempList3 = {"A"};
		AdaptiveList<String> myList3= new AdaptiveList<String>(temp3);
		assertFalse(myList3.reorderOddEven());
		assertArrayEquals(tempList3,myList3.toArray());
		List<String> temp4 = new ArrayList<String>();
		String[] tempList4 = null;
		AdaptiveList<String> myList4= new AdaptiveList<String>(temp4);
		assertFalse(myList4.reorderOddEven());
		assertArrayEquals(tempList4,myList4.toArray());

	}
	@Test
	public void test_01AdaptiveListContainsandContainsAll() {
		List<String> temp = Arrays.asList("A","B","C","D","E");
		List<String> temp2 = Arrays.asList("A","B","C","D","Z");
		String[] tempList = {"B","A","D","C","E"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertTrue(myList.contains("A"));
		assertFalse(myList.contains("Z"));
		assertTrue(myList.containsAll(temp));
		assertFalse(myList.containsAll(temp2));
	}
	@Test
	public void test_01AdaptiveListIndexandLastIndex() {
		List<String> temp = Arrays.asList("A","A","A");
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertEquals(0,myList.indexOf("A"));
		assertEquals(-1,myList.indexOf("B"));
		assertEquals(2,myList.lastIndexOf("A"));
		assertEquals(-1,myList.lastIndexOf("B"));
	}
	@Test
	public void test_01AdaptiveListRemoveAll() {
		List<String> temp = Arrays.asList("A","B","C","D","E");
		List<String> tempR = Arrays.asList("A","B");
		List<String> temp2 = new ArrayList<String>();
		String[] tempList = {"C","D","E"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertTrue(myList.removeAll(tempR));
		assertArrayEquals(tempList,myList.toArray());
		assertFalse(myList.removeAll(temp2));
		assertArrayEquals(tempList,myList.toArray());
	}
	@Test
	public void test_01AdaptiveListRetainAll() {
		List<String> temp = Arrays.asList("A","B","C","D","E");
		List<String> tempR = Arrays.asList("A","B");
		List<String> temp2 = new ArrayList<String>();
		String[] tempList = {"A","B"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertTrue(myList.retainAll(tempR));
		assertArrayEquals(tempList,myList.toArray());
		assertFalse(myList.retainAll(temp2));
		assertArrayEquals(tempList,myList.toArray());
	}	
	@Test
	public void test_01AdaptiveListToArray() {
		List<String> temp = Arrays.asList("A","B");
		String[] tempList = {"A","B","C","D","E"};
		String[] tempListCheck = {"A","B",null,"D","E"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		assertArrayEquals(tempListCheck,myList.toArray(tempList));
		List<String> temp2 = Arrays.asList("A","B","C");
		String[] tempList2 = {"A",null,null};
		String[] tempListCheck2 = {"A"};
		AdaptiveList<String> myList2= new AdaptiveList<String>(temp2);
		assertArrayEquals(tempList2,myList2.toArray(tempListCheck2));
	}
	@Test
	public void test_01AdaptiveArrayIteratorContstructors()
	{
		List<String> temp = Arrays.asList("A","B","C");
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		ListIterator<String> it1 = myList.listIterator();
		assertEquals(0,it1.nextIndex());
		assertEquals(-1,it1.previousIndex());
		ListIterator<String> it2 = myList.listIterator(1);
		assertEquals(1,it2.nextIndex());
		assertEquals(0,it2.previousIndex());
		ex.expect(IndexOutOfBoundsException.class);
		ListIterator<String> it3 = myList.listIterator(50);
		
	}
	@Test
	public void test_01AdaptiveArrayNextHasNextPrevHasPrev()
	{
		List<String> temp = Arrays.asList("A","B","C");
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		ListIterator<String> it1 = myList.listIterator();
		assertTrue(it1.hasNext());
		assertEquals("A",it1.next());
		assertEquals("B",it1.next());
		assertEquals("C",it1.next());
		assertFalse(it1.hasNext());
		ex.expect(NoSuchElementException.class);
		it1.next();
		it1.previous();
		assertTrue(it1.hasPrevious());
		assertEquals("C",it1.previous());
		assertEquals("B",it1.previous());
		assertEquals("A",it1.previous());
		assertFalse(it1.hasPrevious());
		ex.expect(NoSuchElementException.class);
		it1.previous();
	}
	@Test
	public void test_01AdaptiveArrayIteratorRemove()
	{
		List<String> temp = Arrays.asList("A","B","C");
		String[] list = {"B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		ListIterator<String> it1 = myList.listIterator();
		it1.remove();
		assertArrayEquals(list,myList.toArray());
		assertEquals("B",it1.next());
		assertEquals("C",it1.next());
	}
	@Test
	public void test_01AdaptiveArrayIteratorAdd()
	{
		List<String> temp = Arrays.asList("A","B","C");
		String[] list = {"A","D","B","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		ListIterator<String> it1 = myList.listIterator();
		it1.next();
		it1.add("D");
		assertArrayEquals(list,myList.toArray());
		assertEquals("B",it1.previous());
		assertEquals("D",it1.next());
	}
	@Test
	public void test_01AdaptiveArrayIteratorSet()
	{
		List<String> temp = Arrays.asList("A","B","C");
		String[] list = {"A","D","C"};
		AdaptiveList<String> myList= new AdaptiveList<String>(temp);
		ListIterator<String> it1 = myList.listIterator();
		it1.next();
		it1.set("D");
		assertArrayEquals(list,myList.toArray());
		assertEquals("D",it1.previous());
		assertEquals("A",it1.next());
	}
}

