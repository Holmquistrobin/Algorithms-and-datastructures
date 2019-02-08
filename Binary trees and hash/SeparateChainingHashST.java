/*
 * From Algorithms 4th edition R.Sedgewick & K.Wayne p.465, 375.
 */
public class SeparateChainingHashST<Key, Value> {
	private int N; // number of key-value pairs
	private int M; // hash table size
	private SequentialSearchST<Key, Value>[] st; // array of ST objects

	public SeparateChainingHashST() {
		this(997);
	}

	public SeparateChainingHashST(int M) { // Create M linked lists.
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearchST();
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public Value get(Key key) {
		return (Value) st[hash(key)].get(key);
	}

	public void put(Key key, Value val) {
		st[hash(key)].put(key, val);
	}

	private class SequentialSearchST<Key, Value> {
		private Node first;	// first node in the linked list
		private class Node { // linked-list node
			Key key;
			Value val;
			Node next;

			public Node(Key key, Value val, Node next) {
				this.key = key;
				this.val = val;
				this.next = next;
			}
		}

		public Value get(Key key) { // Search for key, return associated value.
			for (Node x = first; x != null; x = x.next) {
				if (key.equals(x.key)) {
					return x.val; // search hit
				}
			}
			// search miss
			return null;
		}

		public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
			for (Node x = first; x != null; x = x.next) {
				if (key.equals(x.key)) {
					x.val = val; // Search hit: update val.
					return;
				}
			}
			first = new Node(key, val, first); // Search miss: add new node.
		}
	}
}
