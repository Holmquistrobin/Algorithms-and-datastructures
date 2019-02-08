/*
 * Class from Algorithms 4th edition R.Sedgewick & K.Wayne p. 379, 381.
 * Added to and modified by Robin Holmquist 28/09/2018.
 * 
 * API:
 * BinarySearchSt<Key,Value>()	//Create a new ST using binary search.
 * keys()	//Returns an array with all keys.
 * get(Key key)	//Returns the value corresponding to key.
 * rank(Key key) //Find index of key using binary search.
 * put(Key key,Value val)	//Add the specified pair to the ST.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] vals;
	private int N=0;
	private int capacity;

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
		this.capacity = capacity;
	}
	//Written by Robin Holmquist.
	//Resize the ST.
	private void resize(){
		capacity = capacity * 2;
		Key[] tempKeys = (Key[]) new Comparable[capacity];
		Value[] tempVals = (Value[]) new Object[capacity];
		for(int i = 0; i < N; i++) {	//Copy keys and values from previous ST.
			tempKeys[i] = keys[i];
			tempVals[i] = vals[i];
		}
		keys = tempKeys;
		vals = tempVals;
		tempKeys = null;
		tempVals = null;
	}
	public Key[] keys() {
		return keys;
	}
	public Value get(Key key) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		} else {
			return null;
		}
	}
	//Binary search.
	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				hi = mid - 1;

			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}
	//Written by Robin Holmquist.
	//Handle if capacity is reached.
	public void put(Key key, Value val) {
		if(N<capacity) {
			putPrivate(key,val);
		}
		else{
			resize();
			putPrivate(key,val);
		}
	}
	private void putPrivate(Key key, Value val) { // Search for key. Update value if found; grow table if new.
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;	//Update value.
			return;
		}
		for (int j = N; j > i; j--) {	//Else move all above keys and vals up one step.
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;	//Set new key and value on its right place.
		vals[i] = val;
		N++;	//Increment size.
	}
}