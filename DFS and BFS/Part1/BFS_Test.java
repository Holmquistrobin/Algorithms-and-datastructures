import java.util.Scanner;
/*
 * Written by Robin Holmquist 09/10/2018.
 * Prints the shortest(one of the shortest) path/s from node X to node Y 
 * using breadth first search.
 */
public class BFS_Test {

	public static void main(String[] args) {
		int V = 49;	//Number nodes.
		int X = NumberRepresentation.numberRepresentation(args[0]);	//Source.
		int Y = NumberRepresentation.numberRepresentation(args[1]);	//Destination.

		Scanner sc = new Scanner(System.in);
		Graph graph = new Graph(sc, V);	//New graph on database file.
		sc.close();
		BreadthFirstPaths BFS = new BreadthFirstPaths(graph, X);
		try {	//Print path to Y.
			for (String w : BFS.pathTo(Y)) {
				System.out.printf("[%s] ", w);
			}
		} catch (java.lang.NullPointerException e) {
			System.out.println("No path");
		}

	}
}
