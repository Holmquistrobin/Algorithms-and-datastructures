import java.util.Scanner;
/*
 * Written by Robin Holmquist 09/10/2018
 * Prints the shortest(one of the shortest) path/s from node X to node Y 
 * using depth first search.
 */

public class DFS_Test {
	public static void main(String[] args) {
		int V = 49; //Number of individual nodes.
		int X = NumberRepresentation.numberRepresentation(args[0]);
		int Y = NumberRepresentation.numberRepresentation(args[1]);
		
		Scanner sc = new Scanner(System.in);
		Graph graph = new Graph(sc,V);
		sc.close();
		DepthFirstPaths DFS = new DepthFirstPaths(graph,X);
		try {
		for(String w: DFS.pathTo(Y)) {
			System.out.printf("[%s] ",w);
		}
		}
		catch(java.lang.NullPointerException e) {
			System.out.println("No path");
		}

	}
}
