
/******************************************************************************************************
 * Written by Robin Holmquist 08/10/2018
 * 
 * As a client code:
 * CircularLinkedList<Type>()	//Create a new linked circular list where the content is of type Type.
 * add(Type content)	//Add content on the head of the list.
 * extract()			//Remove last link and return the content.
 * extractFirst()			//Remove the first link and return the content.
 * numberOfElements()	//Return how many links there are with content.
 * returnRepresentation()	//Returns a String representing the queue in format [x],[y],...
 * iterator()	//Create a new iterator (OBS! You need to create a new iterator if you extract 
 * 				//from the queue).
 ******************************************************************************************************/
import java.lang.StringBuilder;
import java.util.Iterator;

public class CircularLinkedListModified<Item> implements Iterable<Item> {

	private Node<Item> firstNode; // Reference to first node.
	private Node<Item> node; // Reference used for iteration etc.
	private Node<Item> nextEmpty; // Reference for the next empty node.
	private Item temp;
	private StringBuilder stringBuilder; // To be used in returnRepresentation()
	private int counter;
	int j;

	// Constructor.
	public CircularLinkedListModified() {
		node = new Node<Item>();
		firstNode = node;
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
		// Link from nextEmpty to the first node.
		nextEmpty.nextNode = firstNode;
		node = nextEmpty; // Point node to the next empty node.
	}

	public Item extract() {
		node = firstNode;
		if (firstNode.content == null) {
			System.out.println("Queue is empty");
			return null;
		} else {
			while (node.nextNode != nextEmpty) {
				node = node.nextNode;
			}
			temp = node.content;
			node.content = null;
			node.nextNode = firstNode;
			nextEmpty.nextNode = null;
			nextEmpty = node;
			return temp;
		}
	}
	public Item extractFirst() {
		if (firstNode.content == null) {
			System.out.println("Queue is empty");
			return null;
		} else {
			temp = firstNode.content; // Fetch content to be returned.
			firstNode.content = null; // Remove that content from the queue.
			firstNode = firstNode.nextNode; // Set firstNode to the second node.
			// Remove the rest of the extracted node.
			nextEmpty.nextNode.nextNode = null;
			nextEmpty.nextNode = firstNode; // Point to the first node from the last node.
			return temp; // Return content.
		}
	}

	public int numberOfElements() {
		counter = 0;
		node = firstNode;
		if (firstNode.content == null) {
			return counter;
		} else {
			// Go from the top and count how many nodes with content there are.
			while (node.nextNode != firstNode) {
				node = node.nextNode;
				counter++;
			}
			node = nextEmpty; // Reset the node reference to the next empty node.
			return counter;
		}
	}

	// Private class for a generic node to be used in this double linked list.
	private class Node<Item> {
		Node<Item> nextNode;
		Item content;

		public Node() {
			this.nextNode = null;
			this.content = null;
		}
	}
}
