import java.util.Iterator;
/*
 * Class from Algorithms 4th edition R.Sedgewick & K.Wayne p.536.
 * Modified by Robin Holmquist 09/10/2018.
 * 
 * API:
 * DepthFirstPaths(Graph G, int s)	//Do DFS from source s in G.
 * hasPathTo(int v)	//Is v accessible from s.
 * pathTo(int v)	//Returns an Iterable<String> object with one 
 * 					//of the shortest paths from s to v.
 */
public class DepthFirstPaths {
	private boolean[] marked; // Has dfs() been called for this vertex?
	private int[] edgeTo; // last vertex on known path to this vertex
	private int s;
	private int[] depth;	//Depth of nodes in the graph.

// source
	public DepthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		depth = new int[G.V()];
		depth[s] = 0;

		this.s = s;
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]||depth[w]>depth[v]) {
				depth[w] = depth[v] + 1;
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<String> pathTo(int v) {
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<String> path = new Stack<String>();
		path.push(NumberRepresentation.stringRepresentation(v));
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(NumberRepresentation.stringRepresentation(x));
		}
		path.push(NumberRepresentation.stringRepresentation(s));
		return (Iterable<String>) path;
	}
}