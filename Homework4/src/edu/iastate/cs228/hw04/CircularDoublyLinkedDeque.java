package edu.iastate.cs228.hw04;/** *  * @author Mason Walls *  *         NOTE: 0. Put your Firstname and Lastname after above author tag. Make *         sure that in both cases the first letter is uppercase and all others *         are lowercase (and a space). 1. You are allowed to create and use *         your own private helper methods. 2. No additional data fields can be *         introduced. 3. No custom classes of your own can be introduced or *         used. 4. Import statements are not allowed. 5. Fully qualified class *         names usage is not allowed. 6. You are allowed to reuse any part of *         the source codes provided or shown in lecture notes of week 5 (or *         before). 7. You are not allowed to create arrays of objects and *         manipulate queue objects using arrays. * * *         DESCRIPTION: A class that implements the ADT deque by using a *         "circular doubly linked chain" of nodes. In doubly linked chain, the *         first and last nodes each contain one null reference, since the first *         node has no previous node and the last node has no node after it. *         (For details of doubly linked chain implementation of deque, check *         slide number 3 of "queueDequePriorityQueueImplementations_part3.pdf" *         file under lecture notes of Friday of Week 5 on Canvas.) In a *         "circular doubly linked chain", the first node references the last *         node, and the last node references the first. Only one external *         reference is necessary - a reference to the first node - since you *         can quickly get to the last node from the first node. *  */public class CircularDoublyLinkedDeque<T> implements DequeInterface<T> {	private DLNode firstNode; // References node for front of deque	public CircularDoublyLinkedDeque() {		firstNode = null;	} // end default constructor	public void addToBack(T newEntry) {		// TODO		DLNode nextNode = new DLNode(newEntry);		DLNode lastNode;		if (firstNode == null) {			nextNode.next = nextNode;			nextNode.previous = nextNode;			firstNode = nextNode;		} else {			lastNode = firstNode.previous;			lastNode.next = nextNode;			nextNode.previous = lastNode;			nextNode.next = firstNode;			firstNode.previous = nextNode;		}		return;	}	public void addToFront(T newEntry) {		// TODO		DLNode node1 = new DLNode(newEntry);		if (firstNode == null) {			firstNode = node1;			firstNode.next = firstNode;			firstNode.previous = firstNode;		} else {			DLNode last = firstNode.previous;			node1.next = firstNode;			node1.previous = last;			last.next = node1;			firstNode = node1;		}		return;	}	public T getFront() {		// TODO		if (firstNode == null)			throw new EmptyQueueException();		return firstNode.data;	}	public T getBack() {		// TODO		if (firstNode == null)			throw new EmptyQueueException();		return firstNode.previous.data;	}	public T removeFront() {		// TODO		if (firstNode == null) {			throw new EmptyQueueException();		} else if (firstNode.next == firstNode && firstNode.previous == firstNode) {			T temp = firstNode.data;			firstNode = null;			return temp;		} else {			DLNode temp = firstNode;			DLNode last = firstNode.previous;			firstNode = temp.next;			firstNode.previous = last;			last.next = firstNode;			return temp.data;		}	}	public T removeBack() {		// TODO		if (firstNode == null) {			throw new EmptyQueueException();		} else if (firstNode.next == firstNode && firstNode.previous == firstNode) {			T temp = firstNode.data;			firstNode = null;			return temp;		} else {			DLNode last = firstNode.previous;			DLNode newLast = last.previous;			firstNode.previous = newLast;			newLast.next = firstNode;			return last.data;		}	}	public void clear() {		// TODO		while (firstNode != null) {			removeFront();		}	}	public boolean isEmpty() {		// TODO		if (firstNode == null)			return true;		return false;	}	private class DLNode {		private T data; // Deque entry		private DLNode next; // Link to next node		private DLNode previous; // Link to previous node		private DLNode() {			this(null, null, null);		} // end default constructor		private DLNode(T dataPortion) {			this(null, dataPortion, null);		} // end constructor		private DLNode(DLNode previousNode, T dataPortion, DLNode nextNode) {			data = dataPortion;			next = nextNode;			previous = previousNode;		} // end constructor		private T getData() {			return data;		} // end getData		private void setData(T newData) {			data = newData;		} // end setData		private DLNode getNextNode() {			return next;		} // end getNextNode		private void setNextNode(DLNode nextNode) {			next = nextNode;		} // end setNextNode		private DLNode getPreviousNode() {			return previous;		} // end getPreviousNode		private void setPreviousNode(DLNode previousNode) {			previous = previousNode;		} // end setPreviousNode	} // end DLNode} // end CircularDoublyLinkedDeque