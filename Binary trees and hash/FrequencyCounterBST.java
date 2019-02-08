
/*
 * Written by Robin Holmquist 28/09/2018 based on class from Algorithms 4th edition 
 * R.Sedgewick & K.Wayne p.372.
 * 
 * Tests the BST class.
 */

import java.util.Scanner;

public class FrequencyCounterBST {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int minlen = Integer.parseInt(args[0]);// key-length cutoff.
		int intervalLow = Integer.parseInt(args[1]);
		int intervalHigh = Integer.parseInt(args[2]);
		BST<String, Integer> st = new BST<String, Integer>();
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

		Integer[] sortedArrayVals = new Integer[st.size()];
		String[] sortedArrayKeys = new String[st.size()];
		int i = 0;
		for (Comparable word : st.keys()) {
			if (word != null) {
				if (st.get((String) word) > st.get(highestFrequency)) {
					highestFrequency = (String) word;
				}
				sortedArrayVals[i] = st.get((String) word);
				sortedArrayKeys[i] = (String) word;
				i++;
			}
		}
		System.out.println(highestFrequency + " " + st.get(highestFrequency));
		st.intervalFrequencies(st, sortedArrayKeys, sortedArrayVals, intervalLow, intervalHigh);
	}

	
}