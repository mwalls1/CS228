package edu.iastate.cs228.hw03;

/**
 * A class of bags whose entries are stored in a chain of doubly linked nodes.
 * 
 * @author
 * 
 */
public class DoublyLinkedBag<T> implements BagInterface<T> {
	private DoublyLinkedNode firstNode; // Reference to first node
	private int numberOfEntries;

	/**
	 * @author Mason Walls
	 */
	public DoublyLinkedBag() {
		// TODO
		firstNode = null;
		numberOfEntries = 0;
	} // end default constructor

	/**
	 * @author Mason Walls
	 * @param A
	 *            new object to be added
	 * @return A boolean stating if it was added or not
	 */
	public boolean add(T newEntry) {
		// TODO
		DoublyLinkedNode tmp = new DoublyLinkedNode(newEntry, firstNode, null);
		if (firstNode != null) {
			firstNode.prev = tmp;
		}
		firstNode = tmp;
		numberOfEntries++;

		return true;
	} // end add

	/**
	 * @author Mason Returns the bag in the form of an array
	 */
	public T[] toArray() {
		// TODO
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		DoublyLinkedNode currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		} // end while

		return result;
	} // end toArray

	/**
	 * @author Mason Walls
	 * @return A boolean stating if the bag is empty
	 */
	public boolean isEmpty() {
		// TODO
		if (numberOfEntries == 0)
			return true;
		else
			return false;
	} // end isEmpty

	/**
	 * @author Mason Walls
	 * @return The size of the bag
	 */
	public int getCurrentSize() {
		// TODO
		return numberOfEntries;
	} // end getCurrentSize

	/**
	 * @author Mason Walls
	 * @param The
	 *            object to be counted
	 * @return The number of times that object appears in the bag
	 */
	public int getFrequencyOf(T anEntry) {
		// TODO
		int frequency = 0;
		int index = 0;
		DoublyLinkedNode currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			if (currentNode.data.equals(anEntry)) {
				frequency++;
			}
			index++;
			currentNode = currentNode.next;
		} // end while
		return frequency;
	} // end getFrequencyOf

	/**
	 * @author Mason Walls
	 * @param An
	 *            object to compare to the bag
	 * @return A boolean stating if the given object was found in the bag
	 */
	public boolean contains(T anEntry) {
		// TODO
		boolean found = false;
		DoublyLinkedNode currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				return true;
			else
				currentNode = currentNode.next;
		} // end while
		return false;
	} // end contains

	/**
	 * @author Mason Walls Removes all items in the bag
	 */
	public void clear() {
		// TODO
		while (!isEmpty()) {
			remove();
		}
	} // end clear

	/**
	 * @author Mason Walls Removes the first item in the bag
	 */
	public T remove() {
		// TODO
		if (numberOfEntries == 0) {
			return null;
		}
		T result = null;
		if (firstNode != null) {
			result = firstNode.data;
		}
		if (firstNode.next != null) {
			firstNode = firstNode.next;
			firstNode.prev = null;
		} else
			firstNode = null;
		numberOfEntries--;

		return result;
	} // end remove

	/**
	 * @author Mason Walls
	 * @param The
	 *            given object to find in the bag
	 * @return The node that the given object is contained in
	 */
	private DoublyLinkedNode getReferenceTo(T anEntry) {
		boolean found = false;
		DoublyLinkedNode currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while

		return currentNode;
	} // end getReferenceTo

	/**
	 * @author Mason Walls
	 * @param The
	 *            object to be removed
	 * @return A boolean stating if the given object was removed
	 */
	public boolean remove(T anEntry) {
		if (numberOfEntries == 0) {
			return false;
		}
		boolean result = false;
		DoublyLinkedNode nodeN = getReferenceTo(anEntry);

		if (nodeN != null) {
			nodeN.data = firstNode.data; // Replace located entry with entry in first node
			if (firstNode.next != null) {
				firstNode = firstNode.next;
			} else
				firstNode = null;// Remove first node
			numberOfEntries--;

			result = true;
		} // end if

		return result;
	} // end remove

	/**
	 * @author Mason Walls
	 * @param The
	 *            object to be added to the bag
	 * @return The object that was replaced by the given object
	 */
	public T replace(T replacement) {
		// TODO
		T item;
		if (isEmpty())
			return null;
		else {
			item = firstNode.data;
			firstNode.data = replacement;
		}
		return item;
	}

	/**
	 * @author Mason Walls
	 * @param The
	 *            object to be removed Removes every instance of the given object
	 */
	public void removeEvery(T anEntry) {
		// TODO
		// TODO Auto-generated method stub
		DoublyLinkedNode currentNode = firstNode;
		int loopCount = 0;
		while (loopCount < numberOfEntries && currentNode != null && !isEmpty()) {
			if (currentNode.data.equals(anEntry)) {
				remove(anEntry);
				loopCount--;

			}
			currentNode = currentNode.next;
			loopCount++;
		}
	}

	/**
	 * @author Mason Walls Override the equals method of Object class so that it
	 *         returns true when the contents of two DoublyLinkedBags are same. Note
	 *         that two equal DoublyLinkedBags contain the same number of entries,
	 *         and each entry occurs in each DoublyLinkedBag the same number of
	 *         times. I.e., the elements in two do not need to be in exact same
	 *         location.
	 * 
	 *         Before checking the contents inside this method make sure that the
	 *         passed in object is not null, is of the same runtime class, and the
	 *         lengths are same. If any of these fail you can return false.
	 *         Otherwise, you base your return results on contents. (At the start
	 *         you can also do the quick check if both refer to the same object in
	 *         memory.)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO
		DoublyLinkedBag<T> test = new DoublyLinkedBag<T>();
		if (obj == null || !obj.getClass().equals(test.getClass())) {
			return false;
		}
		@SuppressWarnings("unchecked")
		DoublyLinkedBag<T> bag2 = ((DoublyLinkedBag<T>) obj);
		if (bag2.getCurrentSize() > numberOfEntries) {
			return false;
		}
		DoublyLinkedBag<T> bag3 = (DoublyLinkedBag<T>) difference(bag2);
		if (bag3.getCurrentSize() == 0)
			return true;
		return false;
	}

	/**
	 * @author Mason Walls
	 * @param The
	 *            bag to compare difference with
	 * @return A bag that has all items in the current bag that arent in the given
	 *         bag
	 */
	public BagInterface<T> difference(BagInterface<T> anotherBag) {
		// TODO Auto-generated method stub
		DoublyLinkedBag<T> bag3 = new DoublyLinkedBag<T>();
		DoublyLinkedBag<T> bag2 = (DoublyLinkedBag<T>) anotherBag;
		DoublyLinkedNode currentNode = firstNode;
		int loopCount = 0;
		while (loopCount < numberOfEntries && currentNode != null && !isEmpty()) {
			bag3.add(currentNode.data);
			currentNode = currentNode.next;
			loopCount++;
		}
		currentNode = bag2.firstNode;
		loopCount = 0;
		while (loopCount < bag2.numberOfEntries && currentNode != null && !bag2.isEmpty()) {
			if (bag3.contains(currentNode.data)) {
				bag3.remove(currentNode.data);
			}
			currentNode = currentNode.next;
			loopCount++;
		}

		return bag3;
	}

	/**
	 * @author Mason Walls Returns String representation of the items in this bag.
	 *         For example, it would return [A, B, C] if bag had three Strings "A",
	 *         "B", and "C".
	 * 
	 * @return String representation of items in this bag enclosed in square
	 *         brackets, separated by comma and a single space (see example above).
	 *         You can rely on the fact that items' proper toString method was
	 *         implemented. In this method ONLY if you need to you can use String
	 *         class's methods. Also, ONLY in this method you can use fully
	 *         qualified name for StringBuffer class, and use all of its methods.
	 */
	@Override
	public String toString() {
		// TO0DO
		if (numberOfEntries == 0)
			return "[]"; // this is returned in case bag is empty.
		else {
			String result = "[";
			int count = 0;
			DoublyLinkedNode temp = firstNode;
			while (count < numberOfEntries && temp != null) {
				if (numberOfEntries == 1) {
					result += temp.data;
				} else if (numberOfEntries - 1 == count) {
					result += temp.data;
				} else {
					result += temp.data + ", ";
				}
				temp = temp.next;
				count++;
			}
			result += "]";
			return result;
		}
	}

	// A class of nodes for a chain of doubly linked nodes.
	private class DoublyLinkedNode {
		private T data; // Entry in bag
		private DoublyLinkedNode next; // Link to next node
		private DoublyLinkedNode prev; // Link to previous node

		private DoublyLinkedNode(T dataPortion) {
			this(dataPortion, null, null);
		} // end constructor

		private DoublyLinkedNode(T dataPortion, DoublyLinkedNode nextNode, DoublyLinkedNode previousNode) {
			data = dataPortion;
			next = nextNode;
			prev = previousNode;
		} // end constructor

	} // end DoublyLinkedNode

} // end DoublyLinkedBag
