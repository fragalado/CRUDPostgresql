package aplicacion.servicios;

import java.util.Scanner;

/**
 * Implementacion de la interfaz Menu
 */
public class ImplMenu implements InterfazMenu {

	@Override
	public int menu() {
		// Scanner para leer
		Scanner sc = new Scanner(System.in);

		// Mostramos el menu
		int opcion = -1;
		do {
			System.out.println("1. Select");
			System.out.println("2. Update");
			System.out.println("3. Insert");
			System.out.println("4. Delete");
			System.out.println("0. Cerrar app");
			System.out.print("Introduzca una opción: ");
			opcion = sc.nextInt();

			if (opcion < 0 || opcion > 4)
				System.err.println("** Error: El valor no está dentro del rango **");
		} while (opcion < 0 || opcion > 4);

		// Devolvemos la opcion
		return opcion;
	}

	@Override
	public boolean preguntaSiNo(String txt) {
		
		// Scanner para leer la opcion
		Scanner sc = new Scanner(System.in);
		
		String opcion;
		do {
			System.out.printf("¿%s? [s=Si/n=No]: ", txt);
			opcion = sc.nextLine();
			
			if(opcion.equals("s") || opcion.equals("S"))
				return true;
			else if(opcion.equals("n") || opcion.equals("N"))
				return false;
		} while (true);
	}

}
