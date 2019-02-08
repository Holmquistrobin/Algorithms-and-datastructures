/*
 * Class from Algorithms 4th edition R.Sedgewick & K.Wayne p.540.
 * Modified by Robin Holmquist 09/10/2018.
 * 
 * API:
 * BreadthFirstPaths(Graph G, int s)	//BFS from node s in graph G.
 * hasPathTo(int v)	//Is there a path from s to v.
 * pathTo(int v)	//Returns an Iterable<String> object with a shortest 
 * path from s to v.
 */
public class BreadthFirstPaths {
	private boolean[] marked; // Is a shortest path to this vertex known?
	private int[] edgeTo;	// last vertex on known path to this vertex
	private int s;

	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		CircularLinkedListModified<Integer> queue = new CircularLinkedListModified<Integer>();
		marked[s] = true;
		queue.add(s);
		while (queue.numberOfElements() != 0) {
			int v = queue.extractFirst(); // Remove next vertex from the queue.
			for (int w : G.adj(v)) {
				if (!marked[w]) // For every unmarked adjacent vertex,
				{
					edgeTo[w] = v; // save last edge on a shortest path,
					marked[w] = true; // mark it because path is known,
					queue.add(w);
				}
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