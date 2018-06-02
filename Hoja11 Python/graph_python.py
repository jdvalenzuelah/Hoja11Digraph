# Algoritmo y Estructura de Datos
# Universidad del Valle de Guatemala
# Marcos Gutierrez			#17909
# David Valenzuela			#171001
# Alexis Hengsterberg		#17966

#Importamos las librerias de networkx	
import networkx as nx

def ruta():
	# Creamos el grafo
	grafo = nx.DiGraph

	# Abrimos el archivo
	archivo = open("guategrafo.txt", "r")
	contenido = archivo.read()
	archivo.close()

	# Ciclo para recorrer el archivo 
	for lineas in contenido:
		string = lineas.split(",")
		# Agregamos datos al nodo del grafo
		grafo.add_node(string[0])
		grafo.add_node(string[1])
		# Agregamos las conexiones
		grafo.add_edge(string[0],string[1],weigth=float(string[2]))				# string[2] = peso del kilometro de cada uno

	# Ruta para el algoritmo de Floyd
	path = nx.floyd_warshall_predecessor_and_distance(grafo)
	nodos = grafo.nodes()


