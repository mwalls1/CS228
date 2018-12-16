
package edu.iastate.cs228.hw08;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 
 * 
 * A class that implements the ADT dictionary by using a chain of nodes. The
 * dictionary is unsorted and has distinct search keys, i.e., can have duplicate
 * values, however, those are differentiated based on their keys.
 * 
 * @author Mason Walls
 * 
 *         NOTEs and REQUIREMENTs:
 * 
 *         Exactly same as the ones listed for SortedVectorDictionary class.
 * 
 *         In addition to above ANSWER the following 6 QUESTIONS, inside these
 *         comments right below each question. Figures needed to answer
 *         questions 3, 4, and 5 are shown on Canvas under description of HW08.
 * 
 *         =========================================================================
 *         Q1. (a) What is the height of the shortest binary tree that contains
 *         22 nodes? (b) Is this tree full? (c) Is it balanced?
 * 
 *         A1. (a)5 (b)no (c)yes
 *         =========================================================================
 *         Q2. Consider a binary tree that has four levels. (a) What is the
 *         maximum number of nodes in this tree? (b) What is the maximum number
 *         of leaves in this tree?
 * 
 *         A2. (a)15 (b)8
 *         =========================================================================
 *         Q3. Consider a traversal of a binary tree, which contains Integer
 *         data. Suppose that visiting a node means to simply display the data
 *         in the node. What are the results of each of the following traversals
 *         of the binary tree shown in Figure 1. (a) Preorder (b) Postorder (c)
 *         Inorder (d) Level order
 * 
 *         A3. (a)6,4,2,1,3,5,8,7,9,10,11 (b)1,3,2,5,4,9,7,11,10,8,6 (c)1,2,3,4,5,6,7,8,9,10,11 (d)6,4,8,2,5,7,10,1,3,9,11
 * 
 *         =========================================================================
 *         Q4. Repeat Q3 but for the binary tree shwn in Figure 2. A4. (a)11,8,3,2,1,5,4,6,10,9,7 (b)2,1,4,6,3,5,8,9,7,10,11
 *         (c)2,3,1,4,5,6,8,11,9,7,10(d)11,8,10,3,5,9,7,2,1,4,6
 * 
 *         =========================================================================
 *         Q5. The two binary trees shown in Figures 1 and 2 contain Integer
 *         data. (a) Is the tree in Figure 1 a binary search tree? Why or why
 *         not? (b) Is the tree in Figure 2 a maxheap? Why or why not?
 * 
 *         A5. (a)Yes because the left subtree is all smaller than root and the right subtree is greater than the root (b)this tree is max heap because the root is the largest value
 * 
 *         =========================================================================
 *         Q6. Can a binary search tree ever be a maxheap? Explain. A6. A binary search tree cannot be max heap because the root is never the max value
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class LinkedDictionary<K, V> implements DictionaryInterface<K, V> {
	private Node firstNode; // Reference to first node of chain
	private int numberOfEntries;

	public LinkedDictionary() {
		firstNode = null;
		numberOfEntries = 0;
	}

	public V add(K key, V value) {
		if (Objects.isNull(key) || Objects.isNull(value))
			throw new IllegalArgumentException();
		else if (numberOfEntries == 0) {
			firstNode = new Node(key, value);
			numberOfEntries++;
		} else {
			V result = null;
			Node currentNode = firstNode;
			Node nodeBefore = null;
			boolean isFound = false;
			while ((currentNode != null) && !isFound) {
				if (currentNode.getKey().equals(key)) {
					V data = currentNode.getValue();
					currentNode.setValue(value);
					return data;
				} else
				{
					nodeBefore = currentNode;
					currentNode = currentNode.next;
				}
			}
			currentNode = new Node(key, value);
			nodeBefore.next = currentNode;
			numberOfEntries++;
		}
		// TODO

		return null;
	}

	public V remove(K key) {
		if (Objects.isNull(key))
			throw new IllegalArgumentException();
		if (numberOfEntries == 0)
			return null;
		// TODO
		Node currentNode = firstNode;
		Node before = null;
		boolean isFound = false;
		while (!isFound && currentNode != null) {
			if (currentNode.key.equals(key)) {
				isFound = true;
			} else {
				before = currentNode;
				currentNode = currentNode.next;
			}

		}
		if (isFound) {
			if (numberOfEntries==1) {
				V data = currentNode.value;
				firstNode = null;
				numberOfEntries--;
				return data;
			} 
			else if(before==null)
			{
				V data = currentNode.getValue();
				firstNode = currentNode.next;
				numberOfEntries--;
				return data;
			}
			else {
				V data = currentNode.value;
				before.next = currentNode.next;
				currentNode.next = null;
				numberOfEntries--;
				return data;
			}
		}
		return null;
	}

	public V getValue(K key) {
		if (Objects.isNull(key))
			throw new IllegalArgumentException();

		// TODO
		Node currentNode = firstNode;
		while (currentNode != null) {
			if (currentNode.key.equals(key)) {
				V data = currentNode.value;
				currentNode = null;
				return data;
			}
			currentNode = currentNode.next;
		}
		return null;
	}

	public boolean contains(K key) {
		if (Objects.isNull(key))
			throw new IllegalArgumentException();
		Node currentNode = firstNode;
		while (currentNode != null) {
			if (currentNode.getKey().equals(key))
				return true;
			currentNode = currentNode.next;
		}
		return false;
	}

	public boolean isEmpty() {
		// TODO
		return numberOfEntries == 0;
	}

	public int getSize() {
		// TODO
		return numberOfEntries;
	}

	public void clear() {
		// TODO
		Node currentNode = firstNode;
		while (!isEmpty() && currentNode != null) {
			K key = currentNode.key;
			currentNode = currentNode.next;
			remove(key);
		}
	}

	// Needs to output String representation in exact same
	// format as the one done by SortedVectorDictionary.
	public String toString() {
		// TODO
		if (numberOfEntries == 0)
			return "[()]";
		String temp = "[(";
		Node currentNode = firstNode;
		while (currentNode != null) {
			if (currentNode.next == null) {
				temp += currentNode.key + ":" + currentNode.value + ")]";
			} else
				temp += currentNode.key + ":" + currentNode.value + ") (";
			currentNode = currentNode.next;
		}
		return temp;
	}

	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	private class KeyIterator implements Iterator<K> {
		private Node nextNode;

		private KeyIterator() {
			// TODO
			nextNode = firstNode;
		}

		public boolean hasNext() {
			// TODO
			return nextNode != null;
		}

		public K next() {
			// TODO
			K result;
			if (hasNext()) {
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
	}

	private class ValueIterator implements Iterator<V> {
		private Node nextNode;

		private ValueIterator() {
			// TODO
			nextNode = firstNode;
		}

		public boolean hasNext() {
			// TODO
			return nextNode != null;
		}

		public V next() {
			// TODO
			V result;
			if (hasNext()) {
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
	}

	private class Node {
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
			next = null;
		}

		private Node(K searchKey, V dataValue, Node nextNode) {
			key = searchKey;
			value = dataValue;
			next = nextNode;
		}

		private K getKey() {
			return key;
		}

		private V getValue() {
			return value;
		}

		private void setValue(V newValue) {
			value = newValue;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}

		public String toString() {
			return "(" + key + ":" + value + ")";
		}
	}
}
