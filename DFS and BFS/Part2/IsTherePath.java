import java.util.Scanner;
/*
 * Written by Robin Holmquist 09/10/2018.
 * 
 * Is there a path between node X and Y?
 */
public class IsTherePath {
	public static void main(String[] args) {
		int V = 49;	//Number of individual nodes.
		int X = NumberRepresentation.numberRepresentation(args[0]);
		int Y = NumberRepresentation.numberRepresentation(args[1]);

		Scanner sc = new Scanner(System.in);
		GraphDirected graph = new GraphDirected(sc, V);
		sc.close();
		DepthFirstPathsModified DFS = new DepthFirstPathsModified(graph, X);

		if (DFS.hasPathTo(Y)) {
			System.out.println("There exists a path");
		} else {
			System.out.println("There is no path");
		}

	}
}
