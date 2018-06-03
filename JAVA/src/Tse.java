import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author David Valenzuela	#171001
 * @author Marcos Gutierrez #17909
 * @author Alexis Hengstenberg #17699
 *
 * @param <V>
 * @param <E>
 */
public class Tse {
	
	static GrafoMatrizDirigida<String, Double> grafo = new GrafoMatrizDirigida<String, Double>(13);

	public static void main(String[] args) {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese direccion del archivo con datos: ");
		String filepath = sc.nextLine();
		loadData(filepath);
		
		while(running) {
			System.out.println(" 1. Calcular la ruta mas cercana\n 2. Calcular el centro del grafo\n 3. Agregar conexion entre 2 ciudades \n 4. Salir");
			String opt = sc.nextLine();
			
			switch(opt) {
			case "1":
				System.out.println("Ingrese la primera ciudad: ");
				String c1 = sc.nextLine();
				System.out.println("Ingrese la segunda ciudad: ");
				String c2 = sc.nextLine();
				System.out.println(grafo.getRutaMasCorta(c1, c2));
				break;
			case "2":
				System.out.println("El centro del grafo es: " + grafo.getCentroGrafo("Guatemala"));
				break;
			case "3":
				System.out.println("Ingrese la primera ciudad: ");
				c1 = sc.nextLine();
				System.out.println("Ingrese la segunda ciudad: ");
				c2 = sc.nextLine();
				System.out.println("Ingrese distancia entre ciudades (kilometros): ");
				String dist = sc.nextLine();
				grafo.add(c1);
				grafo.add(c2);
				boolean added = grafo.addEdge(c1, c2, Double.parseDouble(dist));
				System.out.println(((added) ? "Agregado exitosamente" : "Error al ingresar conexion!"));
				break;
			case "4":
				running = false;
				break;
			default:
				System.out.println("Opcion incorrecta!");
			}
		}
		sc.close();

	}
	
	public static void loadData(String filePath) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = reader.readLine()) != null) {
				String[] kv = line.split(":");
				if(kv.length > 0) {
					grafo.add(kv[0]);
					grafo.add(kv[1]);
					double n = Double.parseDouble(kv[2]);
					grafo.addEdge(kv[0], kv[1], n);
				}
			}
			reader.close(); // Cerramos el archivo
		} catch (Exception e) {
			System.out.println("Error");
			System.out.println(e.getMessage());
			e.printStackTrace();	
		}	
	}

}
