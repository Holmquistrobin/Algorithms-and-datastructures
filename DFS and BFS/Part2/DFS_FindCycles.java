/*
 * Written by Robin Holmquist 09/10/2018 based on lass from Algorithms 4th edition 
 * R.Sedgewick & K.Wayne p.536.
 * 
 * API:
 * DepthFirstPathsModified(GraphDirected G, int s)	//Do DFS from source s in G.
 * cycle()	//Returns an Iterable<String> object with a cycle for the source s, if 
 * 			//a cycle exists.
 */
public class DFS_FindCycles {
	private boolean[] marked; // Has dfs() been called for this vertex?
	private int[] edgeTo; // last vertex on known path to this vertex
	private int s;
	private Stack<String> cycle;
	private boolean cycleExists = false;

// source
	public DFS_FindCycles(GraphDirected G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];

		this.s = s;
		dfs(G, s);
	}

	private void dfs(GraphDirected G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
			else if(w==s&&cycleExists==false) {
				cycleExists = true;
				cycle = new Stack<String>();
				cycle.push(" ");
				cycle.push(NumberRepresentation.stringRepresentation(s));
				for (int x = v; x != s; x = edgeTo[x]) {
					cycle.push(NumberRepresentation.stringRepresentation(x));
				}
				cycle.push(NumberRepresentation.stringRepresentation(s));
			}
		}
	}
	public Iterable<String> cycle(){
		return (Iterable<String>) cycle;
	}
}