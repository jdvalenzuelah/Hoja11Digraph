/**
 * @author David Valenzuela	#171001
 * @author Marcos Gutierrez #17909
 * @author Alexis Hengstenberg #17699
 *
 * @param <V>
 * @param <E>
 */
public interface Grafo<V,E> 
{

	/**
	 * A vertex with label is added to graph. If vertex with label is already in graph, no action.
	 * If graph is full the first value is deleted.
	 * @param label a non-null label for vertex
	 */
	public void add(V label);
	
	/**
	 * An edge (possibly directed) is inserted between vtx1 and vtx2.
	 * vtx1 and vtx2 are labels of existing vertices
	 * @param vtx1 first label of existing vertices
	 * @param vtx2 second label of existing vertices
	 * @param label the label to be added between  vtx1 and vtx2
	 * @return true if the edge was successfully added, false otherwise
	 */
	public boolean addEdge(V vtx1, V vtx2, E label);
	
	/**
	 * Vertex with "equals" label is removed, if found
	 * @param label a non-null vertex label
	 * @return the eliminated vertex, or null if not found.
	 */
	public V remove(V label);
	
	/**
	 * Edge is removed, its label is returned
	 * @param vLabel1 label of existing vertices
	 * @param vLabel2 label of existing vertices
	 * @return label of the removed edge.
	 */
	public E removeEdge(V vLabel1, V vLabel2);
	
	/**
	 * @param label to be searched
	 * @return returns actual label of indicated vertex
	 */
	public V get(V label);
	
	/** returns actual edge between vertices
	 * @param label1 vertex 1
	 * @param label2 vertex 2
	 * @return edge between vertex 1 and vertex 2
	 */
	public Edge<V,E> getEdge(V label1, V label2);
	
	/**
	 * Sets visited flag on vertex, returns previous value
	 * @param label vertex to visit
	 * @return previous state
	 */
	public boolean visit(V label);
	
	/**
	 * Sets visited flag on vertex, returns previous value
	 * @param e Edge to visit
	 * @return previous state
	 */
	public boolean visitEdge(V label1, V label2);
	
	/**
	 * returns visited flag on labeled vertex
	 * @param label vertex to search
	 * @return true if it was visited, false otherwise
	 */
	public boolean isVisited(V label);
	
	/**
	 * returns visited flag on labeled vertex
	 * @param e Edge to search
	 * @return true if it was visited, false otherwise
	 */
	public boolean isVisitedEdge(V label1, V label2);
	
}
