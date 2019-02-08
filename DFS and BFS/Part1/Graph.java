import java.util.Scanner;
/*
 * Class from Class from Algorithms 4th edition R.Sedgewick & K.Wayne p.526.
 * Modified by Robin Holmquist 09/10/2018.
 * 
 * API:
 * Graph(int V)	//Creates a graph with V nodes.
 * Graph(Scanner in, int V)	//Creates a graph with V nodes from database file.
 * V()	//Return amount of nodes.
 * E()	//Return amount of edges.
 * addEdge(int v, int w)	//Add directionless edge between v and w.
 * adj(int v)	//Return adjacency list of node v.
 */
public class Graph {
	private int V; // number of vertices
	private int E; // number of edges
	private CircularLinkedListModified<Integer>[] adj;// adjacency lists

	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (CircularLinkedListModified<Integer>[]) new CircularLinkedListModified[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new CircularLinkedListModified<Integer>();
		}
	}

	public Graph(Scanner in, int V) {
		this.V = V;
		adj = (CircularLinkedListModified<Integer>[]) new CircularLinkedListModified[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new CircularLinkedListModified<Integer>();
		}
		while(in.hasNext()) {
			int v = NumberRepresentation.numberRepresentation(in.next());
			int w = NumberRepresentation.numberRepresentation(in.next());
			addEdge(v,w);
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return  adj[v];
	}
}