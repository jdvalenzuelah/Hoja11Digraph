/**
 * @author David Valenzuela	#171001
 * @author Marcos Gutierrez #17909
 * @author Alexis Hengstenberg #17699
 *
 * @param <V>
 * @param <E>
 */
public class Vertex<E>
{
	private E label;
	private boolean visited;
	int row;
	
	/**
	 * constructs unvisited vertex with label
	 * @param label Vertex label.
	 */
	public Vertex(E label, int row) {
		this.label = label;
		this.visited = false;
		this.row = row;
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
	
	/**
	 * @return the row
	 */
	public int row() { return this.row;	}
	
}
