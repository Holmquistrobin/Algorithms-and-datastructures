/*
 * Class from From Algorithms 4th edition R.Sedgewick & K.Wayne p.470, 474.
 * Modified by Robin Holmquist 01/10/2018
 */
public class LinearProbingHashST<Key, Value> {
	private int N; // number of key-value pairs in the table
	private int M; // size of linear-probing table
	private Key[] keys; // the keys
	private Value[] vals; // the values

	public LinearProbingHashST(int size) {
		this.M = size;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	//Written by Robin Holmquist 01/10/2018.
	public Key[] keys() {
		Key[] temp = (Key[]) new Comparable[M];
		for(int i = 0; i < M;i++) {
			temp[i] = keys[i];
		}
		return temp;
	}
	private void resize(int cap) {
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<Key, Value>(cap);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				t.put(keys[i], vals[i]);
			}
		}
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}

	public void put(Key key, Value val) {
		if (N >= M / 2)
			resize(2 * M);
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}
}
