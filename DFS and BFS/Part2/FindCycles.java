import java.util.Scanner;
/*
 * Written by Robin Holmquist 09/10/2018.
 * 
 * Finds one cycle for each node that is in a cycle.
 */
public class FindCycles {

	public static void main(String[] args) {
		int V = 49; //Number of individual nodes.
		Scanner sc = new Scanner(System.in);
		GraphDirected graph = new GraphDirected(sc, V);
		sc.close();
		for (int i = 0; i < 49; i++) {	//For each node.
			DFS_FindCycles DFS = new DFS_FindCycles(graph, i);
			try {
				for (String w : DFS.cycle()) {
					System.out.printf("[%s] ", w);
				}
				System.out.println();
			} catch (java.lang.NullPointerException e) {
				
			}
		}
	}

}
