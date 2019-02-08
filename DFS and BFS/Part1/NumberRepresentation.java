/*
 * Written by Robin Holmquist 09/10/2018.
 * Acts as a symbol table to convert node-names from a specific database file to 
 * integer values, and vice versa.
 */
public class NumberRepresentation {
	public static String[] options = {"AL","FL", "GA", "MS","TN","AR","LA","MO","OK",
			"TX","AZ","CA","NM","NV","UT","OR","CO","KS", "NE","WY",
			"CT","MA","NY","RI","DC","MD","VA","DE","NJ","PA","NC","SC",
			"IA","IL","MN","SD", "WI","ID","MT","WA","IN","KY","MI","OH", 
			"WV","NH","VT","ME","ND"};
	public static int numberRepresentation(String a) {
		for(int i = 0; i < 49; i++) {
			if(a.equals(options[i])) {
				return i;
			}
		}
		return 0;
	}
	public static String stringRepresentation(int index) {
		return options[index];
	}
}
