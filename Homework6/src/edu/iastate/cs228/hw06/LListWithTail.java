package edu.iastate.cs228.hw06;

/**
 * A class that implements the ADT list by using a chain of nodes that has both
 * a head reference and a tail reference.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 4.0
 */
public class LListWithTail<T> extends CustomClass<T> {
	private Node firstNode; // Head reference to first node
	private Node lastNode; // Tail reference to last node
	private int numberOfEntries;

	public LListWithTail() {
		initializeDataFields();
	} // end default constructor

	public void clear() {
		initializeDataFields();
	} // end clear

	public void add(T newEntry) {
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);

		lastNode = newNode;
		numberOfEntries++;
	} // end add

	public void add(int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);

			if (isEmpty()) {
				firstNode = newNode;
				lastNode = newNode;
			} else if (newPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else if (newPosition == numberOfEntries + 1) {
				lastNode.setNextNode(newNode);
				lastNode = newNode;
			} else {
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if
			numberOfEntries++;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	public T remove(int givenPosition) {
		T result = null; // Return value

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			if (givenPosition == 1) // Case 1: Remove first entry
			{
				result = firstNode.getData(); // Save entry to be removed
				firstNode = firstNode.getNextNode();
				if (numberOfEntries == 1)
					lastNode = null; // Solitary entry was removed
			} else // Case 2: Not first entry
			{
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);// Disconnect the node to be removed
				result = nodeToRemove.getData(); // Save entry to be removed

				if (givenPosition == numberOfEntries)
					lastNode = nodeBefore; // Last node was removed
			} // end if

			numberOfEntries--;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");

		return result; // Return removed entry
	} // end remove

	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace

	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		} else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	public T[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];

		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		} // end while

		return result;
	} // end toArray

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while

		return found;
	} // end contains

	public int getLength() {
		return numberOfEntries;
	} // end getLength

	public boolean isEmpty() {
		boolean result;

		if (numberOfEntries == 0) // Or getLength() == 0
		{
			assert (firstNode == null) && (lastNode == null);
			result = true;
		} else {
			assert (firstNode != null) && (lastNode != null);
			result = false;
		} // end if

		return result;
	} // end isEmpty

	// Initializes the class's data fields to indicate an empty list.
	private void initializeDataFields() {
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	} // end initializeDataFields

	// Returns a reference to the node at a given position.
	// Precondition: List is not empty; 1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt(int givenPosition) {
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;

		if (givenPosition == numberOfEntries)
			currentNode = lastNode;
		else if (givenPosition > 1) {
			assert givenPosition < numberOfEntries;
			// Traverse the chain to locate the desired node
			for (int counter = 1; counter < givenPosition; counter++)
				currentNode = currentNode.getNextNode();
		} // end if

		assert currentNode != null;
		return currentNode;
	} // end getNodeAt

	private class Node {
		private T data; // Data portion
		private Node next; // Next to next node

		private Node(T dataPortion)// PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = null;
		} // end constructor

		private Node(T dataPortion, Node nextNode)// PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = nextNode;
		} // end constructor

		private T getData() {
			return data;
		} // end getData

		private void setData(T newData) {
			data = newData;
		} // end setData

		private Node getNextNode() {
			return next;
		} // end getNextNode

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node

	@Override
	public void addFirst(T newEntry) {
		// TODO Auto-generated method stub
		add(1, newEntry);
	}

	@Override
	public void addLast(T newEntry) {
		// TODO Auto-generated method stub
		add(numberOfEntries + 1, newEntry);
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		if (numberOfEntries > 1) {
			T temp = firstNode.data;
			firstNode = firstNode.next;
			numberOfEntries--;
			return temp;
		} else if (numberOfEntries == 1) {
			T temp = firstNode.data;
			firstNode = null;
			lastNode = null;
			numberOfEntries--;
			return temp;

		} else
			throw new java.util.NoSuchElementException();
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		if (numberOfEntries > 1) {
			T temp = lastNode.data;
			Node secLast = getNodeAt(numberOfEntries - 1);
			secLast.next = null;
			lastNode = secLast;
			numberOfEntries--;
			return temp;
		} else if (numberOfEntries == 1) {
			T temp = lastNode.data;
			lastNode = null;
			firstNode = null;
			numberOfEntries--;
			return temp;
		} else
			throw new java.util.NoSuchElementException();
	}

	@Override
	public T getFirst() {
		// TODO Auto-generated method stub
		if (numberOfEntries > 0)
			return firstNode.data;
		else
			throw new java.util.NoSuchElementException();
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub
		if (numberOfEntries > 0)
			return lastNode.data;
		else
			throw new java.util.NoSuchElementException();
	}

	@Override
	public void moveToEnd() {
		// TODO Auto-generated method stub
		if (numberOfEntries > 1) {
			Node oldFirst = firstNode;
			firstNode = firstNode.next;
			numberOfEntries--;
			add(oldFirst.data);
		}

	}

	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		if (numberOfEntries > 1) {
			Node current = firstNode;
			int count = 1;
			boolean found = false;
			while (current.next != null && !found) {
				if (current.data.equals(anEntry)) {
					if (count == 1) {
						found = true;
						firstNode = current.next;
						current.next = null;
						numberOfEntries--;
					} else {
						found = true;
						Node temp = getNodeAt(count - 1);
						temp.next = current.next;
						current.next = null;
						numberOfEntries--;
					}
					return true;
				} else {
					count++;
					current = current.next;
				}
			}
		}
		if (numberOfEntries == 1 && firstNode.data.equals(anEntry)) {
			firstNode = null;
			lastNode = null;
			numberOfEntries--;
			return true;
		}
		return false;
	}

	@Override
	public int getPosition(T anEntry) {
		// TODO Auto-generated method stub
		if (numberOfEntries > 1) {
			Node current = firstNode;
			int count = 1;
			while (count <= numberOfEntries) {
				if (current.data.equals(anEntry)) {
					return count;
				} else {
					count++;
					if(current.next!=null)
						current = current.next;
				}
			}
			return -1;
		}
		if (numberOfEntries == 1 && firstNode.data.equals(anEntry))
			return 1;
		return -1;
	}

} // end LListWithTail
