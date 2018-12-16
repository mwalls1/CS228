package edu.iastate.cs228.proj3;

/**
 *  @author Mason Walls
 *
 *
 *  An implementation of List<E> based on a doubly-linked list 
 *  with an array for indexed reads/writes
 *
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AdaptiveList<E> implements List<E> {
	public class ListNode {
		public E data;
		public ListNode next;
		public ListNode prev;

		public ListNode(E item) {
			data = item;
			next = prev = null;
		}
	}

	public ListNode head; // dummy node made public for testing.
	public ListNode tail; // dummy node made public for testing.
	private int numItems; // number of data items
	private boolean linkedUTD; // true if the linked list is up-to-date.

	public E[] theArray; // the array for storing elements
	private boolean arrayUTD; // true if the array is up-to-date.

	public AdaptiveList() {
		clear();
	}

	@Override
	public void clear() {
		head = new ListNode(null);
		tail = new ListNode(null);
		head.next = tail;
		tail.prev = head;
		numItems = 0;
		linkedUTD = true;
		arrayUTD = false;
		theArray = null;
	}

	public boolean getlinkedUTD() {
		return linkedUTD;
	}

	public boolean getarrayUTD() {
		return arrayUTD;
	}

	public AdaptiveList(Collection<? extends E> c) {
		clear();
		for (E e : c) {
			add(e);
		}
	}

	// Removes the node from the linked list.
	// This method should be used to remove a node
	// from the linked list.
	private void unlink(ListNode toRemove) {
		if (toRemove == head || toRemove == tail)
			throw new RuntimeException("An attempt to remove head or tail");
		toRemove.prev.next = toRemove.next;
		toRemove.next.prev = toRemove.prev;
	}

	// Inserts new node toAdd right after old node current.
	// This method should be used to add a node to the linked list.
	private void link(ListNode current, ListNode toAdd) {
		if (current == tail)
			throw new RuntimeException("An attempt to chain after tail");
		if (toAdd == head || toAdd == tail)
			throw new RuntimeException("An attempt to add head/tail as a new node");
		toAdd.next = current.next;
		toAdd.next.prev = toAdd;
		toAdd.prev = current;
		current.next = toAdd;
	}

	private void updateArray() // makes theArray up-to-date.
	{
		if (theArray == null)
			theArray = (E[]) new Object[numItems];
		if (numItems < 0)
			throw new RuntimeException("numItems is negative: " + numItems);
		if (!linkedUTD)
			throw new RuntimeException("linkedUTD is false");
		ListNode temp = head.next;
		int i = 0;
		while (temp.next != null) {
			theArray[i] = temp.data;
			temp = temp.next;
			i++;
		}
		arrayUTD = true;
	}

	private void updateLinked() // makes the linked list up-to-date.
	{
		if (numItems < 0)
			throw new RuntimeException("numItems is negative: " + numItems);
		if (!arrayUTD)
			throw new RuntimeException("arrayUTD is false");

		if (theArray == null || theArray.length < numItems)
			throw new RuntimeException("theArray is null or shorter");
		ListNode temp = head.next;
		for (int i = 0; i < theArray.length; i++) {
			temp.data = theArray[i];
			temp = temp.next;
		}
		linkedUTD = true;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public boolean add(E obj) {
		if (!linkedUTD) {
			updateLinked();
		}
		if (numItems == 0) {
			ListNode newNode = new ListNode(obj);
			head.next = newNode;
			tail.prev = newNode;
			newNode.prev = head;
			newNode.next = tail;
			numItems++;
			arrayUTD = false;
		} else {
			ListNode temp = tail.prev;
			ListNode newNode = new ListNode(obj);
			temp.next = newNode;
			newNode.prev = temp;
			newNode.next = tail;
			tail.prev = newNode;
			numItems++;
			arrayUTD = false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (!linkedUTD)
			updateLinked();
		if (c.isEmpty())
			return false;
		for (E e : c) {
			add(e);
		}
		return true;
	}

	@Override
	public boolean remove(Object obj) {
		if (!linkedUTD)
			updateLinked();
		ListNode temp = head.next;
		while (temp.next != null) {
			if (temp.data.equals(obj)) {
				numItems--;
				unlink(temp);
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	private void checkIndex(int pos) // a helper method
	{
		if (pos >= numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkIndex2(int pos) // a helper method
	{
		if (pos > numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkNode(ListNode cur) // a helper method
	{
		if (cur == null || cur == tail)
			throw new RuntimeException("numItems: " + numItems + " is too large");
	}

	private ListNode findNode(int pos) // a helper method
	{
		ListNode cur = head;
		for (int i = 0; i < pos; i++) {
			checkNode(cur);
			cur = cur.next;
		}
		checkNode(cur);
		return cur;
	}

	@Override
	public void add(int pos, E obj) {
		if (!linkedUTD)
			updateLinked();
		checkIndex2(pos);
		ListNode temp = new ListNode(obj);
		link(findNode(pos), temp);
		numItems++;

	}

	@Override
	public boolean addAll(int pos, Collection<? extends E> c) {
		if (c.isEmpty())
			return false;
		int ind = pos;
		for (E e : c) {
			add(ind, e);
			ind++;
		}
		return true;
	}

	@Override
	public E remove(int pos) {
		checkIndex(pos);
		E data = findNode(pos + 1).data;
		unlink(findNode(pos + 1));
		arrayUTD = false;
		numItems--;
		return data;
	}

	@Override
	public E get(int pos) {
		if (arrayUTD) {
			checkIndex(pos);
			return theArray[pos];
		} else {
			checkIndex(pos);
			updateArray();
			return theArray[pos];
		}
	}

	@Override
	public E set(int pos, E obj) {
		if (!arrayUTD)
			updateArray();
		checkIndex(pos);
		E data = theArray[pos];
		theArray[pos] = obj;
		linkedUTD = false;
		return data;
	}

	/**
	 * If the number of elements is at most 1, the method returns false. Otherwise,
	 * it reverses the order of the elements in the array without using any
	 * additional array, and returns true. Note that if the array is modified, then
	 * linkedUTD needs to be set to false.
	 */
	public boolean reverse() {
		if (!arrayUTD)
			updateArray();
		if (numItems < 2)
			return false;
		int numIters = numItems / 2;
		for (int i = 0; i < numIters; i++) {
			E temp = theArray[i];
			theArray[i] = theArray[numItems - i - 1];
			theArray[numItems - i - 1] = temp;
		}
		linkedUTD = false;
		return true;
	}

	/**
	 * If the number of elements is at most 1, the method returns false. Otherwise,
	 * it swaps the items positioned at even index with the subsequent one in odd
	 * index without using any additional array, and returns true. Note that if the
	 * array is modified, then linkedUTD needs to be set to false.
	 */
	public boolean reorderOddEven() {
		if (!arrayUTD)
			updateArray();
		int track = 0;
		if (numItems < 2)
			return false;
		if (numItems % 2 == 1)
			track = 1;
		for (int i = 0; i < numItems - track; i += 2) {
			E temp = theArray[i];
			theArray[i] = theArray[i + 1];
			theArray[i + 1] = temp;
		}
		linkedUTD = false;
		return true;
	}

	@Override
	public boolean contains(Object obj) {
		if (!linkedUTD)
			updateLinked();
		if (isEmpty()) {
			return false;
		} else {
			ListNode current = head.next;
			while (current.next != null) {
				if (current.data.equals(obj))
					return true;
				current = current.next;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (!linkedUTD)
			updateLinked();
		for (Object e : c) {
			if (!contains(e))
				return false;
		}
		return true;
	}

	@Override
	public int indexOf(Object obj) {
		if (!linkedUTD)
			updateLinked();
		if (isEmpty())
			return -1;
		else {
			ListNode temp = head.next;
			int index = 0;
			while (temp.next != null) {
				if (temp.data.equals(obj))
					return index;
				index++;
				temp = temp.next;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object obj) {
		if (!linkedUTD)
			updateLinked();
		if (isEmpty())
			return -1;
		else {
			ListNode temp = head.next;
			int index = 0;
			int lastIndex = -1;
			while (temp.next != null) {
				if (temp.data.equals(obj))
					lastIndex = index;
				index++;
				temp = temp.next;
			}
			return lastIndex;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if(c.isEmpty())
			return false;
		if (!linkedUTD)
			updateLinked();
		for (Object e : c) {
			remove(e);
		}
		arrayUTD = false;
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if(c.isEmpty())
			return false;
		if (!linkedUTD)
			updateLinked();
		if (isEmpty())
			return false;
		else {
			ListNode temp = head.next;
			while (temp.next != null) {
				if (!c.contains(temp.data))
					remove(temp.data);
				temp = temp.next;
			}
		}
		arrayUTD = false;
		return true;
	}

	@Override
	public Object[] toArray() {
		if (!linkedUTD)
			updateLinked();
		if (isEmpty())
			return null;
		Object[] temp = new Object[numItems];
		ListNode temps = head.next;
		int index = 0;
		while (temps.next != null) {
			temp[index] = temps.data;
			temps = temps.next;
			index++;
		}
		return temp;
	}

	/**
	 * In here you are allowed to use only java.util.Arrays.copyOf method.
	 */
	@Override
	public <T> T[] toArray(T[] arr) {
		if (arr.length < numItems)
			arr = java.util.Arrays.copyOf(arr, numItems);
		else
			java.util.Arrays.copyOf(arr, arr.length);
		if (arr.length > numItems)
			arr[numItems] = null;
		return arr;
	}

	@Override
	public List<E> subList(int fromPos, int toPos) {
		throw new UnsupportedOperationException();
	}

	private class AdaptiveListIterator implements ListIterator<E> {
		private int index; // index of next node;
		private ListNode cur; // node at index - 1
		private ListNode last; // node last visited by next() or previous()

		public AdaptiveListIterator() {
			if (!linkedUTD)
				updateLinked();
			cur = last = head.next;
			index = 0;
		}

		public AdaptiveListIterator(int pos) {
			if (!linkedUTD)
				updateLinked();
			checkIndex2(pos);
			index = pos;
			last = null;
			cur = pos == 0 ? null : findNode(pos);
		}

		@Override
		public boolean hasNext() {
			if (cur == null)
				return head != null;
			else
				return !(index>=numItems);
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			if (index > numItems)
				throw new RuntimeException("index 1");
			index++;
			E data = cur.data;
			last = cur = cur == null ? head : cur.next;
			return data;
		}

		@Override
		public boolean hasPrevious() {
			return cur != null;
		}

		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			if (index <= 0)
				throw new RuntimeException("index 2");
			index--;
			last = cur;
			cur = cur.prev;
			return last.data;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			AdaptiveList.this.remove(cur.data);
			cur = cur.next;
		}

		@Override
		public void add(E obj) {
			AdaptiveList.this.add(index,obj);
			index++;
		}

		@Override
		public void set(E obj) {
			AdaptiveList.this.set(index, obj);
		}
	} // AdaptiveListIterator

	@Override
	public boolean equals(Object obj) {
		if (!linkedUTD)
			updateLinked();
		if ((obj == null) || !(obj instanceof List<?>))
			return false;
		List<?> list = (List<?>) obj;
		if (list.size() != numItems)
			return false;
		Iterator<?> iter = list.iterator();
		for (ListNode tmp = head.next; tmp != tail; tmp = tmp.next) {
			if (!iter.hasNext())
				return false;
			Object t = iter.next();
			if (!(t == tmp.data || t != null && t.equals(tmp.data)))
				return false;
		}
		if (iter.hasNext())
			return false;
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int pos) {
		checkIndex2(pos);
		return new AdaptiveListIterator(pos);
	}

	// Adopted from the List<E> interface.
	@Override
	public int hashCode() {
		if (!linkedUTD)
			updateLinked();
		int hashCode = 1;
		for (E e : this)
			hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}

	// You should use the toString*() methods to see if your code works as expected.
	@Override
	public String toString() {
		// Other options System.lineSeparator or
		// String.format with %n token...
		// Not making data field.
		String eol = System.getProperty("line.separator");
		return toStringArray() + eol + toStringLinked();
	}

	public String toStringArray() {
		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent array:" + eol);
		strb.append('[');
		if (theArray != null)
			for (int j = 0; j < theArray.length;) {
				if (theArray[j] != null)
					strb.append(theArray[j].toString());
				else
					strb.append("-");
				j++;
				if (j < theArray.length)
					strb.append(", ");
			}
		strb.append(']');
		return strb.toString();
	}

	public String toStringLinked() {
		return toStringLinked(null);
	}

	// iter can be null.
	public String toStringLinked(ListIterator<E> iter) {
		int cnt = 0;
		int loc = iter == null ? -1 : iter.nextIndex();

		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent linked list:" + eol);
		strb.append('(');
		for (ListNode cur = head.next; cur != tail;) {
			if (cur.data != null) {
				if (loc == cnt) {
					strb.append("| ");
					loc = -1;
				}
				strb.append(cur.data.toString());
				cnt++;

				if (loc == numItems && cnt == numItems) {
					strb.append(" |");
					loc = -1;
				}
			} else
				strb.append("-");

			cur = cur.next;
			if (cur != tail)
				strb.append(", ");
		}
		strb.append(')');
		return strb.toString();
	}
}
