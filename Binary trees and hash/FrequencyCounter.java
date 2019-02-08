
/*
 * Written by Robin Holmquist 28/09/2018 based on class from Algorithms 4th edition 
 * R.Sedgewick & K.Wayne p.372.
 * 
 * Tests the BinarySearchST class.
 */

import java.util.Scanner;

public class FrequencyCounter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int minlen = Integer.parseInt(args[0]);// key-length cutoff.
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(200);
		while (sc.hasNext() == true) {
			String word = sc.next();
			if (word.length() > minlen) {
				if (st.get(word) == null) {
					st.put(word, 1);
				} else {
					st.put(word, st.get(word) + 1);
				}
			}
		}
		sc.close();
		String highestFrequency = "";
		st.put(highestFrequency, 0);
		for (Comparable word : st.keys()) {
			if (word != null) {
				if (st.get((String) word) > st.get(highestFrequency)) {
					highestFrequency = (String) word;
				}
			}
		}
		System.out.println(highestFrequency + " " + st.get(highestFrequency));

	}
}