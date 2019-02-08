import java.util.Hashtable;
import java.util.Scanner;
/*
 * Written by Robin Holmquist 30/09/2018.
 * Tests how many collisions of hash codes there are in a text file.
 */
public class TestHashString {

	public static void main(String[] args) {
		//Hash table where keys are hash codes of words in the text.
		Hashtable<Integer,Integer> htableHashes = new Hashtable<Integer, Integer>();
		//Hash table where keys are the actual words in the text.
		Hashtable<String,Integer> htableStrings = new Hashtable<String,Integer>();
		Scanner sc = new Scanner(System.in);
		String tempString;
		int tempHash;
		while (sc.hasNext() == true) {
			tempString = sc.next();
			tempHash = tempString.hashCode();
			htableHashes.put(tempHash, 1);	//Add hash to relevant table.
			htableStrings.put(tempString, 1);	//Add actual word.
			//Note that the value is not relevant.
		}
		sc.close();
		//How many collisions has there been.
		int sizeDifference = htableStrings.size()-htableHashes.size();
		System.out.println("Total amount of words: " + htableStrings.size());
		System.out.println("Total amount of collisions: "+ sizeDifference);
	}

}
