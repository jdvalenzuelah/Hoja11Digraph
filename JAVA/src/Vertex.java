/**
 * @author daval
 *
 * @param <E>
 */
public class Vertex<E>
{
	private E label;
	private boolean visited;
	
	/**
	 * constructs unvisited vertex with label
	 * @param label Vertex label.
	 */
	public Vertex(E label) {
		this.label = label;
		this.visited = false;
	}
	
	/**
	 * @return user label associated w/vertex
	 */
	public E label() { return this.label; }
	
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
	 * @return true if edge has been visited, false otherwise
	 */
	public boolean isVisited() { return visited; }
	
	/**
	 * Resets edge's visited flag to initial state.
	 */
	public void reset() { this.visited = false;	}
	
}
