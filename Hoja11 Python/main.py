# Algoritmo y Estructura de Datos
# Universidad del Valle de Guatemala
# Marcos Gutierrez			#17909
# David Valenzuela			#171001
# Alexis Hengsterberg		#17966

# Importamos las funciones del grafo
from graph_python import *

print(" 1. Calcular la ruta mas cercana\n 2. Calcular el centro del grafo\n 3. Agregar\n 4. Salir")
opcion = input("Que desea hacer?")
# Ciclo para recorrer el menu
while(opcion != 4):
	# Opcion 1
	if(opcion == 1):
		# Funcion para la ruta mas cercana
		ruta()
	if(opcion == 2):
		# Funcion para calcular el centro del grafo
		centro()
	if(opcion == 3):
		ciudad_uno = raw_input("Ingrese la ciudad de origen: ")
		ciudad_dos = raw_input("Ingrese la ciudad de destino: ")
		km = input("Ingrese los kilometros de las ciudades: ")

		otro =  raw_input("Desea ingresar otra ciudad? s/n\n")
		# Ciclo para ingrear otro ciudad
		while("s" == otro):
			ciudad_uno = raw_input("Ingrese la ciudad de origen: ")
			ciudad_dos = raw_input("Ingrese la ciudad de destino: ")
			km = input("Ingrese los kilometros de las ciudades: ")
		agregar(ciudad_uno, ciudad_dos, km)
		print "Agregado "
		break