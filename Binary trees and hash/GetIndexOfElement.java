/*
 * Written by Robin Holmquist 30/09/2018
 * Finds all indices of a specified word in a specified text file.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.lang.StringBuilder;

public class GetIndexOfElement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		ArrayList<String> list = new ArrayList<String>();
		while (sc.hasNext()) {
			list.add(sc.next());
		}
		sc.close();
		String word = args[0];
		// get index first
		int indexFirstOccurrence = list.indexOf(word);
		// Get index last
		int indexLastOccurrence = list.lastIndexOf(word);
		//Search for indices.
		String tempString;
		if (indexFirstOccurrence != -1) {
			if (indexFirstOccurrence != indexLastOccurrence) {
				Iterator<String> iterator = list.listIterator(indexFirstOccurrence);
				for (int i = indexFirstOccurrence; i <= indexLastOccurrence; i++) {
					tempString = iterator.next();
					if (tempString.hashCode() == word.hashCode()) {
						sb.append("Index: " + i + "\n");
					}
				}
			}
			else {
				sb.append("Index: " + indexFirstOccurrence);
			}
		}
		System.out.println(sb.toString());
	}

}
