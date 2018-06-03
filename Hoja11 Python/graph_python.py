# Algoritmo y Estructura de Datos
# Universidad del Valle de Guatemala
# Marcos Gutierrez			#17909
# David Valenzuela			#171001
# Alexis Hengsterberg		#17966

#Importamos las librerias de networkx	
import networkx as nx

def ruta_cercana():
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
	print "Las ciudades que se encuentran en la base de datos: '+'/".join(nodos)

	ciudad_principal = raw_input("Ingrese la ciudad a la cual desea ir: ")

	# Ciclo para verificar en la base de datos
	while ciudad_principal not in nodos:
		print "La ciudad destino no se encuentra dentro de la base de datos. "
		ciudad_principal = raw_input("Ingrese la ciudad a la cual desea ir: ")

	ciudad_secundario = raw_input("Ingrese la ciudad destino final: ")

	# Ciclo para verificar si esta la ciudad secundario dentro de la base de datos
	while ciudad_secundario not in nodos:
		print "La ciudad destino no se encuentra dentro de la base de datos"
		ciudad_secundario = raw_input("Ingrese la ciudad destino final: ")

	try:
        dato1 = path[0][ciudad_principal][ciudad_secundario]
        ciudades = []
        ciudades.append(dato1)

        # Contador
        cont = 0
        if (dato1 != ciudad_principal):
            while (cont == 0):
                dato = path[0][ciudad_principal][dato1]
                ciudades.append(dato)
                dato1 = dato
                if (dato1 == ciudad_principal):
                    cont = 1
                else:
                    dato1 = dato

        imprimir = []
        for i in reversed(ciudades):
            if (ciudad_principal != i):
                imprimir.append(i)
                
        print "La ruta mas corta entre ", ciudad_principal, " y ", ciudad_secundario, " es pasando por: "
        print ciudad_principal, " " + "	".join(imprimir)," ", ciudad_secundario

        print "La distancia en kilometros es: "
        print path[1][ciudad_principal][ciudad_secundario]

       	trafico = input("Hay trafico en la ruta?\n1.Si\n2.No")
    
        if (trafico==1):
            print "Escriba el nombre de las ciudades donde comienza el trafico "
            edge = raw_input()
            nodos_trafico = edge.split(", ")
            if nodos_trafico[0] or nodos_trafico[1] not in grafo.nodes():
                alerta = 1
            while alerta!=1:
                print "Las ciudades no existen porfavor vuelva a ingresar en el formato correcto"
                edge = raw_input(grafo.edges)
                nodos_trafico = edge.split(", ")

            
            grafo.remove_edge(nodos_trafico[0],nodos_trafico[1])
            newpath = nx.floyd_warshall_predecessor_and_distance(grafo)
            dato1 = newpath[0][ciudad_principal][ciudad_secundario]
            ciudades2 = []
            ciudades2.append(dato1)
       
            cont = 0
            if (dato1 != ciudad_principal):
                while cont == 0:
                    dato = newpath[0][ciudad_principal][dato1]
                    ciudades2.append(dato)
                    dato = dato1
                    if dato == ciudad_principal:
                        cont = 1
                    else:
                        dato1 = dato

            imprimir2 = []
            for i in reversed(ciudades2):
                if (ciudad_principal != i):
                    imprimir2.append(i)

            print ciudad_principal, " "+ " ".join(imprimir2)," ", ciudad_secundario
            print "Ruta en kilometros: "
            print newpath[1][ciudad_principal][ciudad_secundario]
            
    except KeyError:
        print "No existen rutas entre esas ciudades"

def centro():
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

	centro = nx.center(grafo, None, False)
	print "Centro del grafo es: " + "/" .join(centro)

def agregar(ciudad_uno, ciudad_dos, km):
	archivo = open("guategrafo.text", "a")
	archivo.write(ciudad_uno + "," + ciudad_dos + "," + str(km) + "\n")
	archivo.close()

	