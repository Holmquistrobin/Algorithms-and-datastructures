import java.lang.StringBuilder;
/*
 * Class from Algorithms 4th edition R.Sedgewick & K.Wayne p.536.
 * Modified by Robin Holmquist 09/10/2018.
 * 
 * API:
 * DepthFirstPathsModified(GraphDirected G, int s)	//Do DFS from source s in G.
 * hasPathTo(int v)	//Is v accessible from s.
 */
public class DepthFirstPathsModified {

	private boolean[] marked; // Has dfs() been called for this vertex?
	private int[] edgeTo; // last vertex on known path to this vertex
	private int s;
	private int V;

	// source
	public DepthFirstPathsModified(GraphDirected G, int s) {
		this.V = G.V();
		marked = new boolean[V];
		edgeTo = new int[V];

		this.s = s;
		dfs(G, s);
	}

	private void dfs(GraphDirected G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

}
