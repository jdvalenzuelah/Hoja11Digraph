/**
 * @author David Valenzuela	#171001
 * @author Marcos Gutierrez #17909
 * @author Alexis Hengstenberg #17699
 *
 * @param <V>
 * @param <E>
 */
public class Edge<V,E>{
	
	private V vtx1;
	private V vtx2;
	private E label;
	private boolean directed;
	private boolean visited;
	
	/**
	 * Class constructor
	 * Edge associates vtx1 and vtx2; labeled with label. Directed if "directed" set true
	 * @param vtx1
	 * @param vtx2
	 * @param label
	 * @param directed
	 */
	public Edge(V vtx1, V vtx2, E label, boolean directed) {
		this.vtx1 = vtx1;
		this.vtx2 = vtx2;
		this.label = label;
		this.directed = directed;
		this.visited = false;
	}
	
	/**
	 * Obtain the first node in edge
	 * @return the first node
	 */
	public V here() { return this.vtx1; }
	
	/**
	 * Obtain the second node in edge
	 * @return the second node
	 */
	public V there() { return this.vtx2; }

	/**
	 * @return true if edge has been visited, false otherwise
	 */
	public boolean isVisited() { return visited; }

	/**
	 * Visits edge
	 * @return whether previously visited.
	 */
	public boolean visit() {
		boolean state = this.visited;
		this.visited = true;
		return state;
	}

	/**
	 * @return true if edge is directed
	 */
	public boolean isDirected() { return directed; }

	/**
	 * Returns label associated with this edge
	 * @return the label
	 */
	public E label() { return label; }

	/**
	 * Sets label of this edge to label
	 * @param label the label to set
	 */
	public void setLabel(E label) { this.label = label; }
	
	/**
	 * Resets edge's visited flag to initial state.
	 */
	public void reset() { this.visited = false;	}

}