
/******************************************************************************************************
 * Written by Robin Holmquist 09/09/2018
 * 
 * As a client code:
 * GeneralizedQueue<Type>()	//Create a new double linked list where the content is of type Type.
 * add(Type content)	//Add content on the head of the list.
 * extract(int index)	//Remove the link at index index and return the content. Where the most 
 * 						//recently added element has index 1.
 * numberOfElements()	//Return how many links there are with content.
 * returnRepresentation()	//Returns a String representing the queue in format [x],[y],...
 * iterator()	//Create a new iterator (OBS! You need to create a new iterator if you extract 
 * 				//from the queue).
 ******************************************************************************************************/

import java.lang.StringBuilder;
import java.util.Iterator;

public class GeneralizedQueue<Item> implements Iterable<Item> {

	private Node<Item> firstNode; // Reference to first node.
	private Node<Item> node; // Reference used for iteration etc.
	private Node<Item> nextEmpty; // Reference for the next empty node.
	private Item temp;
	private StringBuilder stringBuilder; // To be used in returnRepresentation()
	private int counter;
	private int j;

	// Constructor.
	public GeneralizedQueue() {
		node = new Node<Item>();
		firstNode = node;
		nextEmpty = node;
	}
	public Iterator<Item> iterator() {
		return new queueIterator();
	}

	private class queueIterator implements Iterator<Item> {
		private Node<Item> iteratorNode = firstNode;
		public int iteratorIndex = 0;

		public boolean hasNext() {
			if (iteratorIndex < numberOfElements()) {
				return true;
			} else {
				return false;
			}
		}

		public Item next() {
			if (hasNext() == true) {
				iteratorIndex++;
				temp = iteratorNode.content;
				iteratorNode = iteratorNode.nextNode;
				return temp;
			} else {
				return null;
			}
		}

		public void remove() {
		}
	}
	public void add(Item item) {
		node.content = item; // Content to be stored.
		node.nextNode = new Node<Item>(); // Create a new empty node.
		nextEmpty = node.nextNode; // Point nextEmpty to the next empty node.
		// Link from nextEmpty backwards to node where information was just stored.
		nextEmpty.prevNode = node;
		node = nextEmpty; // Point node to the next empty node.
	}

	public Item extract(int index) {
		// Is the index valid?
		if (index <= 0) {
			System.out.println("Invalid index");
			return null;
		}
		// Is the queue empty?
		if (firstNode.content == null) {
			System.out.println("Queue is empty");
			return null;
		}
		// If not empty:
		else {
			// Move down the queue to find specified node.
			for (int i = 0; i < index; i++) {
				if (node.prevNode != null) { // Handle a too large index.
					node = node.prevNode;
				} else {
					System.out.println("Too large index");
					node = nextEmpty; // Reset the node reference.
					return null;
				}
			}
			temp = node.content; // Fetch content.
			node.content = null; // Clear content.
			// Remove the extracted node from the linking system.
			if (node == firstNode) {
				firstNode = node.nextNode;
				firstNode.prevNode = null;
			} else {
				node.prevNode.nextNode = node.nextNode;
				node.nextNode.prevNode = node.prevNode;
			}
			// Clear the rest of the extracted node.
			node.nextNode = null;
			node.prevNode = null;
			node = nextEmpty; // Reset the node reference.
			return temp;
		}
	}

	public int numberOfElements() {
		counter = 0;
		// Go from the top and count how many nodes with content there are.
		while (node.prevNode != null) {
			node = node.prevNode;
			counter++;
		}
		node = nextEmpty; // Reset the node reference to the next empty node.
		return counter;
	}

	// Private class for a generic node to be used in this double linked list.
	private class Node<Item> {
		Node<Item> nextNode;
		Node<Item> prevNode;
		Item content;

		public Node() {
			this.nextNode = null;
			this.prevNode = null;
			this.content = null;
		}
	}

	public String returnRepresentation() {
		j = numberOfElements(); // Fetch the number of elements.
		stringBuilder = new StringBuilder(4 * j); // Create a StringBuilder with enough space.
		node = firstNode; // Position node for iteration.
		for (int i = 0; i < j; i++) {
			stringBuilder.append("[");
			stringBuilder.append(node.content);
			node = node.nextNode; // Jump to the next node.
			stringBuilder.append("]");
			if (i < (j - 1)) { // Don't add ',' after the last element in the queue.
				stringBuilder.append(",");
			}
		}
		node = nextEmpty; // Reset the node reference.
		return stringBuilder.toString();
	}
}
