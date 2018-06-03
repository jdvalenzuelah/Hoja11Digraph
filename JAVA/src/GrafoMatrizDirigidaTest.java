import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafoMatrizDirigidaTest {

	@Test
	void testAddVertex() {
		GrafoMatrizDirigida<String, String> grafo = new GrafoMatrizDirigida<String, String>(2);
		grafo.add("prueba");
		assertEquals(grafo.get("prueba"), "prueba");
	}
	
	@Test
	void testAddEdge() {
		GrafoMatrizDirigida<String, String> grafo = new GrafoMatrizDirigida<String, String>(2);
		grafo.add("prueba");
		grafo.add("prueba2");
		grafo.addEdge("prueba", "prueba2", "label");
		assertEquals(grafo.getEdge("prueba", "prueba2").here(), "prueba");
	}

}
