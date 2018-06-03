
/**
 * @author daval
 *
 * @param <V>
 * @param <A>
 */
public interface Grafo<V extends Comparable<V>,E> 
{

	/**
	 * A vertex with label is added to graph. If vertex with label is already in graph, no action.
	 * @param label a non-null label for vertex
	 */
	public void add(V label);
	
	/**
	 * An edge (possibly directed) is inserted between vtx1 and vtx2.
	 * vtx1 and vtx2 are labels of existing vertices
	 * @param vtx1 first label of existing vertices
	 * @param vtx2 second label of existing vertices
	 * @param label the label to be added between  vtx1 and vtx2
	 */
	public void addEdge(V vtx1, V vtx2, E label);
	
	/**
	 * Vertex with "equals" label is removed, if found
	 * @param label a non-null vertex label
	 * @return the eliminated vertex, or null if not found.
	 */
	public V remove(V label);
	
	public E removeEdge(V vLabel1, V vLabel2);
	// pre: vLabel1 and vLabel2 are labels of existing vertices
	// post: edge is removed, its label is returned
	
	public V get(V label);
	// post: returns actual label of indicated vertex
	
	public Edge<V,E> getEdge(V label1, V label2);
	// post: returns actual edge between vertices
	
	public boolean contains(V label);
	// post: returns true iff vertex with "equals" label exists
	
	public boolean containsEdge(V vLabel1, V vLabel2);
	// post: returns true iff edge with "equals" label exists
	
	public boolean visit(V label);
	// post: sets visited flag on vertex, returns previous value
	
	public boolean visitEdge(Edge<V,E> e);
	// pre: sets visited flag on edge; returns previous value
	
	public boolean isVisited(V label);
	// post: returns visited flag on labeled vertex
	
	public boolean isVisitedEdge(Edge<V,E> e);
	// post: returns visited flag on edge between vertices
	
	public void reset();
	// post: resets visited flags to false
	
	public int size();
	// post: returns the number of vertices in graph
	
	public int degree(V label);
	// pre: label labels an existing vertex
	// post: returns the number of vertices adjacent to vertex
	
	public int edgeCount();
	// post: returns the number of edges in graph
	
	public void clear();
	// post: removes all vertices from graph
	
	public boolean isEmpty();
	// post: returns true if graph contains no vertices
	
	public boolean isDirected();
	// post: returns true if edges of graph are directed
}
