import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author David Valenzuela	#171001
 * @author Marcos Gutierrez #17909
 * @author Alexis Hengstenberg #17699
 *
 * @param <V>
 * @param <E>
 */
public class GrafoMatrizDirigida<V,E extends Comparable<E>> implements Grafo<V,E>{
	
	protected int size; // allocation size for graph
	protected Object data[][]; // matrix - array of arrays
	protected Map<V,Vertex<V>> dict; // labels -> vertices
	protected List<Integer> freeList; // available indices in matrix
	private boolean negativeCycle;
	Number[][] distances;
	
	/**
	 * Class Constructor.
	 * @param size maximum size of the graph
	 */
	protected GrafoMatrizDirigida(int size) {
		this.size = size;
		this.data = new Object[size][size];
		this.distances = new Number[size][size];
		this.dict = new HashMap<>(size);
        this.freeList = new ArrayList<>();
        for (int row = size-1; row >= 0; row--) { freeList.add(row); }
        for(int i = 0; i < distances.length; i++) {
        	for(int j = 0; j < distances.length; j++) {
        		distances[i][j] = Double.POSITIVE_INFINITY;
        	}
        }
	}

	/* (non-Javadoc)
	 * @see Grafo#add(java.lang.Object)
	 */
	@Override
	public void add(V label) {
		// if there already, do nothing
		if (dict.containsKey(label)) return;
		// verificar que aun existan indices disponibles para el vertice
		// allocate a free row and column
		int row = freeList.remove(0);
		// add vertex to dictionary
		dict.put(label, new Vertex<V>(label, row));
	}

	/* (non-Javadoc)
	 * @see Grafo#addEdge(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean addEdge(V vtx1, V vtx2, E label) {
		Vertex<V> vertice1 = dict.get(vtx1);
		Vertex<V> vertice2 = dict.get(vtx2);
		// Verify that the vertices are on the map
		if(vertice1 == null || vertice2 == null) {
			return false;
		}else {
			data[vertice1.row()][vertice2.row()] = new Edge<V, E>(vtx1, vtx2, label, true);
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see Grafo#remove(java.lang.Object)
	 */
	@Override
	public V remove(V label) {
		Vertex<V> vert = dict.remove(label);
		if(vert == null) {
			return null;
		}else {
			int index = vert.row();
			for(int i = 0; i < index; i++) {
				data[index][i] = null;
				data[i][index] = null;
			}
			freeList.add(index);
			return vert.label();
		}
	}

	/* (non-Javadoc)
	 * @see Grafo#removeEdge(java.lang.Object, java.lang.Object)
	 */
	@Override
	public E removeEdge(V vLabel1, V vLabel2) {
		Vertex<V> vtx1 = dict.get(vLabel1);
		Vertex<V> vtx2 = dict.get(vLabel2);
		if(vtx1 == null || vtx2 == null) {
			return null;
		} else {
			Edge<V, E> del = (Edge<V, E>) data[vtx1.row()][vtx2.row()];
			if(del == null) return null;
			data[vtx1.row()][vtx2.row()] = null;
			this.floydWarshall();
			return del.label();
		}
	}

	/* (non-Javadoc)
	 * @see Grafo#get(java.lang.Object)
	 */
	@Override
	public V get(V label) {
		Vertex<V> v = dict.get(label);
		return (v == null) ? null : v.label();
	}

	/* (non-Javadoc)
	 * @see Grafo#getEdge(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Edge<V, E> getEdge(V label1, V label2) {
		Vertex<V> vtx1 = dict.get(label1);
		Vertex<V> vtx2 = dict.get(label2);
		return (vtx1 == null || vtx2 == null) ? null : (Edge<V, E>) data[vtx1.row()][vtx2.row()];
	}

	/* (non-Javadoc)
	 * @see Grafo#visit(java.lang.Object)
	 */
	@Override
	public boolean visit(V label) {
		Vertex<V> v = dict.get(label);
		return (v == null) ? null : v.visit();
	}

	/* (non-Javadoc)
	 * @see Grafo#visitEdge(Edge)
	 */
	@Override
	public boolean visitEdge(V label1, V label2) {
		Vertex<V> vtx1 = dict.get(label1);
		Vertex<V> vtx2 = dict.get(label2);
		if(vtx1 == null || vtx2 == null) return false;
		Edge<V, E> edge = (Edge<V, E>) data[vtx1.row()][vtx2.row()];
		return (edge == null) ? null : edge.visit();
	}

	/* (non-Javadoc)
	 * @see Grafo#isVisited(java.lang.Object)
	 */
	@Override
	public boolean isVisited(V label) {
		Vertex<V> v = dict.get(label);
		return (v == null) ? false : v.isVisited();
	}

	/* (non-Javadoc)
	 * @see Grafo#isVisitedEdge(Edge)
	 */
	@Override
	public boolean isVisitedEdge(V label1, V label2) {
		Vertex<V> vtx1 = dict.get(label1);
		Vertex<V> vtx2 = dict.get(label2);
		if (vtx1 == null || vtx2 == null) return false;
		Edge<V, E> edge = (Edge<V, E>) data[vtx1.row()][vtx2.row()];
		return (edge == null) ? false : edge.isVisited();
	}
	
	/**
	 * Obtener el valor de la ruta mas corta entre vertice con label1 y vertice con label2
	 * @param label1 label del vertice1
	 * @param label2 label del vertice2
	 * @return la distancia mas corta
	 */
	public String getRutaMasCorta(V label1, V label2) {
		Vertex<V> vtx1 = dict.get(label1);
		Vertex<V> vtx2 = dict.get(label2);
		if (vtx1 == null || vtx2 == null) return "No hay conexion!";
		return "La ruta mas corta es de: " + this.distances[vtx1.row()][vtx2.row()] + ".";
	}
	
	public V getCentroGrafo(V label){
		int[] columnMaxIndex = new int[distances.length];
		for(int i = 0; i < distances.length; i++) {
			Number currentMax = distances[i][0];
			int currentMaxIndex = 0;
			for(int j = 0; j < distances.length; j++) {
				Number temp = distances[i][j];
				if(temp.doubleValue() > currentMax.doubleValue()) {
					currentMax = temp;
					currentMaxIndex = j;
				}
			}
			columnMaxIndex[i] = currentMaxIndex;
		}
		
		int currentMinIndex = 0;
		Number currentMin = columnMaxIndex[0];
		for(int x = 0; x < columnMaxIndex.length; x++) {
			Number temp = columnMaxIndex[x];
			if(temp.doubleValue() < currentMin.doubleValue()) {
				currentMin = temp;
				currentMinIndex = x;
			}
		}
		
		Edge<V, E> centro = (Edge<V, E>) data[columnMaxIndex[currentMinIndex]][currentMinIndex];
		try {
			return (centro.here() == null) ? label : centro.here();
		} catch(Exception e) {
			return label;
		}
		
	}
	
    /**
     * The Floyd-Warshall algorithm is used to find the shortest path between
     * all pairs of nodes in a weighted graph with either positive or negative
     * edge weights but without negative edge cycles.
     * 
     * The running time of the algorithm is O(n^4), being n the number of nodes in
     * the graph.
     * 
     * This is a modify version of the algorithm, for it to work with a generic graph were the label is used as the distance if and only if it is a numeric value, found on:
     * https://github.com/kennyledet/Algorithm-Implementations/blob/master/Floyd_Warshall/Java/dalleng/FloydWarshall.java
     * Developed by Diego Allen.
     * 
     * @return
     */
	public void floydWarshall() {
        int n = this.data.length;
        for(int x = 0; x < n; x++) {
        	for(int y = 0; y < n; y++) {
        		Edge<V, E> temp = (data[x][y] == null) ? null : (Edge<V, E>) data[x][y];
        		E label = (temp.label() == null) ? null : temp.label();
        		if(label instanceof Number) {
        			distances[x][y] = (label == null) ? Double.POSITIVE_INFINITY : (Number) label;
        		} else {
        			return;
        		}
        		
        	}
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j].doubleValue(), distances[i][k].doubleValue() + distances[k][j].doubleValue());
                }
            }
            if ((Double) distances[k][k] < 0.0) {
                this.negativeCycle = true;
            }
        }
}
}
