import java.util.Scanner;
public class test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] names = new String[10000];
		int i=0;
		int count = 0;
		boolean change = false;
		while(sc.hasNext()) {
			String temp = sc.next();
			for(int j = 0; j < 1000; j++) {
				if(temp.equals(names[j])) {
					change = true;
					break;
				}
			}
			if(!change) {
				names[i] = temp;
				i++;
			}		
			else {
				change = false;
			}
		}
		for(int j = 0; j < 1000; j++) {
			if(names[j]!=null) {
				count++;
			}
		}
		sc.close();
		System.out.println(count);
		for(int j = 0; j < count; j++) {
			System.out.printf(" %s, ",names[j]);
		}
		
	}
}
