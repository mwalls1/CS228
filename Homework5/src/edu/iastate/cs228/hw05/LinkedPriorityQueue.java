package edu.iastate.cs228.hw05;

/**
 * A class of priority queues represented by a chain of linked nodes.
 * 
 * @author
 * 
 * 		NOTE: 0. Put your Firstname and Lastname after above author tag. Make
 *         sure that in both cases the first letter is uppercase and all others
 *         are lowercase. 1. You are allowed to create and use your own private
 *         helper methods. 2. No additional data fields can be introduced. 3. No
 *         custom classes of your own can be introduced or used. 4. Import
 *         statements are not allowed. 5. Fully qualified class names usage is
 *         not allowed. 6. You are allowed to reuse any part of the source codes
 *         of provided source codes or shown under lecture notes. 7. You are not
 *         allowed to create arrays of objects and manipulate queue objects
 *         using arrays.
 * 
 *
 *         DESCRIPTION: A class of priority queues represented by a linked chain
 *         of nodes.
 * 
 *         For details of priority queue implementation using linked chain of
 *         nodes, check slide number 14 of
 *         "queueDequePriorityQueueImplementations_part3.pdf" file under lecture
 *         notes of Friday of Week 5 on Canvas.
 */

public final class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	private Node firstNode; // Reference to first node of chain and the front
							// of the priority queue, which has the highest priority
	private int length; // Number of entries in chain

	public LinkedPriorityQueue() {
		firstNode = null;
		length = 0;
	}

	public void add(T newEntry) {
		if (isEmpty()) {
			firstNode = new Node(newEntry);
			length++;
			return;
		} else if (firstNode.data.compareTo(newEntry) < 0) {
			Node temp = new Node(newEntry, firstNode);
			firstNode = temp;
			length++;
			return;
		} else {
			Node temp = firstNode;
			Node temp2;
			int count = 0;
			while (count <= length) {
				if (temp.data.compareTo(newEntry) < 0) {
					temp2 = new Node(temp.data, temp.next);
					temp.data = newEntry;
					temp.next = temp2;
					length++;
					return;
				} else {
					if (temp.next != null) {
						temp = temp.next;
						count++;
					} else
						count = length + 1;
				}
			}
			temp.next = new Node(newEntry);
		}
		length++;
		return;
	}

	public T remove() {
		// TODO
		T dat;
		if (isEmpty())
			return null;
		else {
			dat = firstNode.data;
			Node temp = firstNode.next;
			firstNode = firstNode.next;
		}
		length--;
		return dat;
	}

	public T peek() {
		// TODO
		if (isEmpty())
			return null;
		else
			return firstNode.data;
	}

	/**
	 * If queue is empty returns []. Else, returns as [1, 2, 3] Important: note a
	 * comma and single space before every item except the last, and after last
	 * there is no space. In both cases before and after square brackets there is no
	 * space.
	 * 
	 */
	@Override
	public String toString() {
		// TODO
		if (isEmpty())
			return "[]";
		String temp = "[";
		String help = "";
		Node tempN = firstNode;
		for (int i = 0; i < length; i++) {
			help += tempN.data;
			tempN = tempN.next;
		}
		if (help.length() == 1)
			return temp + help + "]";
		for (int j = help.length() - 1; j >= 0; j--) {
			if (j == 0)
				temp += help.substring(j, j + 1);
			else
				temp += help.substring(j, j + 1) + ", ";
		}
		temp += "]";
		return temp;
	}

	public boolean isEmpty() {
		boolean result;

		if (length == 0) {
			assert firstNode == null;
			result = true;
		} else {
			assert firstNode != null;
			result = false;
		}

		return result;
	}

	public int getSize() {
		return length;
	}

	public void clear() {
		firstNode = null;
		length = 0;
	}

	private class Node {
		private T data; // Entry in priority queue
		private Node next; // Link to next node

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
} // end LinkedPriorityQueue