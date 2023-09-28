package aplicacion.controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aplicacion.dtos.LibroDTO;
import aplicacion.servicios.ImplCRUDsql;
import aplicacion.servicios.ImplConexionSQL;
import aplicacion.servicios.ImplLibroDTO;
import aplicacion.servicios.ImplMenu;
import aplicacion.servicios.InterfazCRUDsql;
import aplicacion.servicios.InterfazConexionSQL;
import aplicacion.servicios.InterfazLibroDTO;
import aplicacion.servicios.InterfazMenu;

/**
 * Clase principal que contendrá el main
 */
public class Menu {
	
	/**
	 * Método de acceso a la aplicación de consola
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Inicializamos la interfaz Menu
		InterfazMenu intM = new ImplMenu();
		
		// Inicializamos la interfaz conexion sql
		InterfazConexionSQL ics = new ImplConexionSQL();
		
		// Inicializamos la interfaz CRUD
		InterfazCRUDsql icsq = new ImplCRUDsql();
		
		// Inicializamos la interfaz LibroDTO
		InterfazLibroDTO intL = new ImplLibroDTO();
		
		List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
		
		// Scanner para leer datos
		Scanner sc = new Scanner(System.in);
		
		int opcion;
		boolean esValido = false;
		Connection c = null;
		do {
			opcion = -1; // Actualizamos el valor de la opcion
			
			// Mostramos el menu
			try {
				opcion = intM.menu();
			} catch (Exception e) {
				System.err.println("** ERROR: No se ha introducido una opcion correcta **");
			}
			
			// Conexion base de datos
			if(opcion <=4 && opcion >= 1) {
				try {
					// Realizamos la conexion a la base de datos
					c = ics.conectaBD();
					if(c != null)
						esValido = true;
				} catch (Exception e) {
					System.err.println("** [ERROR-main] No se ha podido conectar a la base de datos. " + e.getMessage() + " **");
				}
			}
			
			// Si la conexion es valida
			if(esValido) {
				// Controlamos las opciones
				switch (opcion) {
				case 1:
					// Select
					try {
						intL.selectLibroDto(c);
					} catch (Exception e) {
						System.err.println("** [ERROR-main] Error no se ha podido hacer el select **");
					}
					break;
				case 2:
					// Update
					try {
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case 3:
					// Insert
					try {
						intL.insertLibroDto(c);
					} catch (Exception e) {
						System.err.println("** [ERROR-main] Error no se ha podido hacer el insert **");
					}
					break;
				case 4:
					// Delete
					break;
					
				case 0:
					// Cerramos la conexion
					try {
						c.close();
					} catch (Exception e) {
						System.err.println("** [ERROR-main] No se ha podido cerrar la conexion a la bd **");
					}
					break;
				}
			}
		} while (opcion != 0);
	}

}
