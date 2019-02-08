import java.util.Iterator;

//p.149
public class Stack<Item> implements Iterable {
	private Node first; // top of stack (most recently added node)
	private int N;	// number of items
	
	private class Node { // nested class to define nodes
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void push(Item item) { // Add item to top of stack.
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Item pop() { // Remove item from top of stack.
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	public Iterator iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<Item> {
		private Node iteratorNode = first;
		public boolean hasNext() {
			if(iteratorNode.next == null) {
				return false;
			}
			return true;
		}

		public Item next() {
			if (hasNext() == true) {
				Item temp = iteratorNode.item;
				iteratorNode = iteratorNode.next;
				return temp;
			} 
			return null;
		}

		public void remove() {
		}
	}
}