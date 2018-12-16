package edu.iastate.cs228.hw02;

/**
 * A class of bags whose entries are stored in a chain of linked nodes. The bag
 * is never full.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 4.1
 */
public final class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode; // Reference to first node
	private int numberOfEntries;

	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	} // end default constructor

	/**
	 * Sees whether this bag is empty.
	 * 
	 * @return True if this bag is empty, or false if not.
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	/**
	 * Gets the number of entries currently in this bag.
	 * 
	 * @return The integer number of entries currently in this bag.
	 */
	public int getCurrentSize() {
		return numberOfEntries;
	} // end getCurrentSize

	/**
	 * Adds a new entry to this bag.
	 * 
	 * @param newEntry
	 *            The object to be added as a new entry
	 * @return True if the addition is successful, or false if not.
	 */
	public boolean add(T newEntry) // OutOfMemoryError possible
	{
		// Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode; // Make new node reference rest of chain
									// (firstNode is null if chain is empty)
		firstNode = newNode; // New node is at beginning of chain
		numberOfEntries++;

		return true;
	} // end add

	/**
	 * Retrieves all entries that are in this bag.
	 * 
	 * @return A newly allocated array of all the entries in this bag.
	 */
	public Object[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast

		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		} // end while

		return result;
	} // end toArray

	/**
	 * Counts the number of times a given entry appears in this bag.
	 * 
	 * @param anEntry
	 *            The entry to be counted.
	 * @return The number of times anEntry appears in this bag.
	 */
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;

		int counter = 0;
		Node currentNode = firstNode;
		while ((counter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				frequency++;
			} // end if

			counter++;
			currentNode = currentNode.next;
		} // end while

		return frequency;
	} // end getFrequencyOf

	/**
	 * Tests whether this bag contains a given entry.
	 * 
	 * @param anEntry
	 *            The entry to locate.
	 * @return True if the bag contains anEntry, or false otherwise.
	 */
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while

		return found;
	} // end contains

	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while

		return currentNode;
	} // end getReferenceTo

	/** Removes all entries from this bag. */
	public void clear() {
		while (!isEmpty())
			remove();
	} // end clear

	/**
	 * Removes one unspecified entry from this bag, if possible.
	 * 
	 * @return Either the removed entry, if the removal was successful, or null.
	 */
	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next; // Remove first node from chain
			numberOfEntries--;
		} // end if

		return result;
	} // end remove

	/**
	 * Removes one occurrence of a given entry from this bag, if possible.
	 * 
	 * @param anEntry
	 *            The entry to be removed.
	 * @return True if the removal was successful, or false otherwise.
	 */
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);

		if (nodeN != null) {
			nodeN.data = firstNode.data; // Replace located entry with entry in first node

			firstNode = firstNode.next; // Remove first node
			numberOfEntries--;

			result = true;
		} // end if

		return result;
	} // end remove

	private class Node {
		private T data; // Entry in bag
		private Node next; // Link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructor
	} // end Node

	/**
	 * @author Mason Walls Creates a new bag that has all items from the current and
	 *         given bag
	 * @param A
	 *            bag
	 * @return New bag with both bag's items
	 */
	public BagInterface<T> union(BagInterface<T> anotherBag) {
		// TODO Auto-generated method stub
		LinkedBag<T> bag3 = new LinkedBag<T>();
		LinkedBag<T> bag2 = (LinkedBag<T>) anotherBag;
		Node currentNode = firstNode;
		int loopCount = 0;
		while (loopCount < numberOfEntries && currentNode != null && !isEmpty()) {
			bag3.add(currentNode.data);
			loopCount++;
			currentNode = currentNode.next;
		}
		loopCount = 0;
		currentNode = bag2.firstNode;
		while (loopCount < anotherBag.getCurrentSize() && currentNode != null && !anotherBag.isEmpty()) {
			bag3.add(currentNode.data);
			loopCount++;
			currentNode = currentNode.next;
		}
		return bag3;
	}

	/**
	 * @author Mason Walls Creates a new bag with all items that the current bag and
	 *         given bag have in common (no repeats)
	 * @param A
	 *            bag
	 * @return A new bag
	 */
	public BagInterface<T> intersection(BagInterface<T> anotherBag) {
		// TODO Auto-generated method stub
		LinkedBag<T> bag3 = new LinkedBag<T>();
		LinkedBag<T> bag2 = (LinkedBag<T>) anotherBag;
		Node currentNode = firstNode;
		Node currentNode2;
		int loopCount = 0;
		int iterations = numberOfEntries;
		int iterations2 = bag2.numberOfEntries;
		while (loopCount < iterations && currentNode != null && !isEmpty()) {
			currentNode2 = bag2.firstNode;
			for (int i = 0; i < iterations2; i++) {
				if (currentNode2 != null && !bag2.isEmpty()) {
					if (currentNode.data.equals(currentNode2.data) && !bag3.contains(currentNode.data))
						bag3.add(currentNode.data);
					currentNode2 = currentNode2.next;
				}
			}
			currentNode = currentNode.next;
		}
		return bag3;
	}

	/**
	 * @author Mason Walls Creates a new bag with items that the are not found in
	 *         both bags (x,x,x) - (x,x) returns x
	 * @param A
	 *            bag to compare with
	 * @return A new bag with the difference of the bag and the given bag
	 */
	public BagInterface<T> difference(BagInterface<T> anotherBag) {
		// TODO Auto-generated method stub
		LinkedBag<T> bag3 = new LinkedBag<T>();
		LinkedBag<T> bag2 = (LinkedBag<T>) anotherBag;
		Node currentNode = firstNode;
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
	 * @author Mason Walls Replaces the first item in the bag with the given item
	 * @param An
	 *            object to replace with
	 * @return The item that was removed
	 */
	public T replace(T replacement) {
		// TODO Auto-generated method stub
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
	 * 
	 *         Removes every instance of a given entry in the bag
	 * @param The
	 *            object to be removed
	 */
	public void removeEvery(T anEntry) {
		// TODO Auto-generated method stub
		Node currentNode = firstNode;
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
	 * @author Mason Walls Determines if two bags of the same type are equal if they
	 *         have the same length and contents true if equal, false otherwise
	 * @param Another
	 *            object
	 * @return A boolean declaring if the bags are equal
	 */
	@Override
	public boolean equals(Object anotherBag) {
		LinkedBag<T> test = new LinkedBag<T>();
		if (anotherBag == null || !anotherBag.getClass().equals(test.getClass())) {
			return false;
		}
		@SuppressWarnings("unchecked")
		LinkedBag<T> bag2 = ((LinkedBag<T>) anotherBag);
		if (bag2.getCurrentSize() > numberOfEntries) {
			return false;
		}
		LinkedBag<T> bag3 = (LinkedBag<T>) difference(bag2);
		if (bag3.getCurrentSize() == 0)
			return true;
		return false;

	}
} // end LinkedBag
