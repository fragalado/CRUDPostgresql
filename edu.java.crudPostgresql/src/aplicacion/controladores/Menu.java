package aplicacion.controladores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import aplicacion.dtos.LibroDTO;
import aplicacion.servicios.ImplCRUDsql;
import aplicacion.servicios.ImplConexionSQL;
import aplicacion.servicios.ImplMenu;
import aplicacion.servicios.InterfazCRUDsql;
import aplicacion.servicios.InterfazConexionSQL;
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
		
		List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
		
		int opcion = -1;
		boolean esValido = false;
		do {
			try {
				opcion = intM.menu();
			} catch (Exception e) {
				System.err.println("** ERROR: No se ha introducido una opcion correcta **");
			}
			
			try {
				// Realizamos la conexion a la base de datos
				Connection c = ics.conectaBD();
				esValido = true;
			} catch (Exception e) {
				System.err.println("** [ERROR-main] " + e.getMessage() + " **");
			}
			
			if(esValido) {
				switch (opcion) {
				case 1:
					// Select
					if(intM.preguntaSiNo("Quieres devolver con condicion o sin"))
					break;
				case 2:
					// Update
					break;
				case 3:
					// Insert
					break;
				case 4:
					// Delete
					break;
				}
			}
		} while (opcion != 0);
		try {
			// Realizamos la conexion a la base de datos
			Connection c = ics.conectaBD();
			
			// Hacemos un select todos libros
			listaLibros = icsq.selectBD(c, "select * from gbp_almacen.gbp_alm_cat_libros;");
			
			// Recorremos la lista y mostramos por consola los libros
			for (LibroDTO aux : listaLibros) {
				System.out.printf("Id = %s , Titulo = %s, Autor = %s, Isbn = %s, Edicion = %s \n", aux.getIdLibro()
																							   , aux.getTitulo()
																							   , aux.getAutor()
																							   , aux.getIsbn()
																							   , aux.getEdicion());
			}
			
			// Hacemos un select con where
			listaLibros = icsq.selectBD(c, "select * from gbp_almacen.gbp_alm_cat_libros where id_libro=1;");
			
			// Recorremos la lista y mostramos por consola los libros
			System.out.println("\nSELECT CON WHERE");
			for (LibroDTO aux : listaLibros) {
				System.out.printf("Id = %s , Titulo = %s, Autor = %s, Isbn = %s, Edicion = %s \n", aux.getIdLibro()
																							   , aux.getTitulo()
																							   , aux.getAutor()
																							   , aux.getIsbn()
																							   , aux.getEdicion());
			}
			
			// Update
			
			ics.desconectarBD(c); // Desconectamos la conexion a la base de datos
		} catch (Exception e) {
			System.err.println("** [ERROR-main] " + e.getMessage() + " **");
		}

	}

}
