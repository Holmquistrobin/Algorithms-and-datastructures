/*
 * Written by Robin Holmquist 28/09/2018.
 * The program takes input from stdin and masks it so that the output contains the 
 * same characters, with the exception that all non-alphabetic characters have been
 * changed to blank space characters (ASCII 32).
 */
import java.io.*;
import java.lang.StringBuilder;

public class FilterNonAlphabetic {
	public static char filterNonAlphChar(int ascii) {
		if ((65 <= ascii && ascii <= 90) || (97 <= ascii && ascii <= 122) || (10 == ascii)) { // If alphabetic or
																								// new-line.
			return (char) ascii;
		}
		return (char) 32; // If not an alphabetical character or new line, then blank space.
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder("");
		Reader reader = new InputStreamReader(System.in);
		int temp = reader.read();
		while (temp != -1) {	//While no EOF-character.
			sb.append(filterNonAlphChar(temp));	//Append filtered character.
			temp = reader.read();
		}
		reader.close();

		System.out.println(sb.toString());
	}
}
