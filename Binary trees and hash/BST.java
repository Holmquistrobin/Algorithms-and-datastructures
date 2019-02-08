/*
 * Class from Algorithms 4th edition R.Sedgewick & K.Wayne p. 398, 399.
 * Modified and added to by Robin Holmquist 28/09/2018.
 * 
 * API:
 * BST<Key, Value>()	//Create a binary search tree ST.
 * size()	//Returns the size of the entire tree.
 * get(Key key)	//Returns the value corresponding to key.
 * put(Key key, Value val)	//Put specified pair in the ST, or update val at key if key already exists.
 * key()	//Returns an array with all keys.
 * intervalFrequencies(BST<String, Integer> st, String[] keys, Integer[] vals, int intervalLow, int intervalHigh) //Show the nth most frequent to the nth+xth most frequent key. 
 */

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

// root of BST
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) { // Return value associated with key in the subtree rooted at x;
// return null if key not present in subtree rooted at x.
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return x.val;
		}
	}

	public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
// Change key’s value to val if key in subtree rooted at x.
// Otherwise, add new node to subtree associating key with val.
		if (x == null) {
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	// Written by Robin Holmquist.
	// Creates an array of all keys in the BST using a queue written by Robin
	// Holmquist.
	private GeneralizedQueue<Key> queue = new GeneralizedQueue<Key>();

	private void keys(Node x) {
		if (x == null) {
			return;
		}
		if (x.left != null) {
			keys(x.left);
		}
		if (x.right != null) {
			keys(x.right);
		}
		queue.add(x.key);
	}

	public Key[] keys() {
		keys(root);
		int queueSize = queue.numberOfElements();
		Key[] temp = (Key[]) new Comparable[queueSize];
		for (int i = 0; i < queueSize; i++) {
			temp[i] = queue.extract(1);
		}
		return temp;
	}

	public void intervalFrequencies(BST<String, Integer> st, String[] keys, Integer[] vals, int intervalLow,
			int intervalHigh) {
		sort(vals, keys);
		while (intervalLow <= intervalHigh) {
			System.out.println(keys[st.size() - intervalLow] + " " + vals[st.size() - intervalLow]);
			intervalLow++;
		}
	}

	private static void exch(Comparable[] a, String[] b, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
		String temp = b[i];
		b[i] = b[j];
		b[j] = temp;
	}

	// Method from Algorithms 4th ed R.Sedgewick, page 245.
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// Method from Algorithms 4th ed 2011 R.Sedgewick, page 251. Modified by Robin
	// Holmquist 13/09/2018.
	private static void sort(Comparable[] a, String[] b) { // Sort a[] into increasing order.
		int N = a.length;
		for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, b, j, j - 1);
			}
		}
	}
}