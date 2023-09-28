package aplicacion.servicios;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import aplicacion.dtos.LibroDTO;

/**
 * Implementacion de la interfaz LibroDTO
 */
public class ImplLibroDTO implements InterfazLibroDTO {
	
	@Override
	public void selectLibroDto(Connection conexion) {
		// Scanner para leer
		Scanner sc = new Scanner(System.in);
		
		// Inicializamos interfaz CRUD
		InterfazCRUDsql intcq = new ImplCRUDsql();
		
		// Lista donde guardaremos los libros
		List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
		
		if(preguntaSiNo("Quieres devolverlos todos")) {
			// Devuelve el select con todos
			listaLibros = intcq.selectBD(conexion, "select * from gbp_almacen.gbp_alm_cat_libros;");
		} else {
			// Preguntamos por el id_libro para hacer el select
			int idLibro;
			try {
				System.out.print("Introduzca el id de libro: ");
				idLibro = sc.nextInt();
				
				// Si esta bien introducido entonces:
				listaLibros = intcq.selectBD(conexion, "select * from gbp_almacen.gbp_alm_cat_libros where id_libro="+idLibro+";");
			} catch (InputMismatchException e) {
				System.err.println("[ERROR-ImplLibroDTO-selectLibroDto] Error " + e.getMessage());
			} catch (NoSuchElementException e) {
				System.err.println("[ERROR-ImplLibroDTO-selectLibroDto] Error " + e.getMessage());
			} catch (IllegalStateException e) {
				System.err.println("[ERROR-ImplLibroDTO-selectLibroDto] Error " + e.getMessage());
			}
		}
		
		// Recorremos la lista y mostramos por consola los libros
		for (LibroDTO aux : listaLibros) {
			System.out.printf("Id = %s , Titulo = %s, Autor = %s, Isbn = %s, Edicion = %s \n", aux.getIdLibro()
																						   , aux.getTitulo()
																						   , aux.getAutor()
																						   , aux.getIsbn()
																						   , aux.getEdicion());
		}
	}
	
	@Override
	public void updateLibroDto(Connection conexion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertLibroDto(Connection conexion) {
		
		// Lista para guardar los libros
		List<LibroDTO> lista = new ArrayList<LibroDTO>();
		
		// Pediremos datos hasta que se cancele.
		String titulo, autor, isbn;
		int edicion;
		try {
			do {
				// Scanner para leer los datos
				Scanner sc = new Scanner(System.in);
				
				// Pedimos el titulo
				System.out.print("Introduzca el titulo del libro: ");
				titulo = sc.nextLine();
				
				// Pedimos el autor
				System.out.print("Introduzca el autor del libro: ");
				autor = sc.nextLine();
				
				// Pedimos el isbn
				System.out.print("Introduzca el isbn del libro: ");
				isbn = sc.nextLine();
				
				// Pedimos la edicion
				System.out.print("Introduzca la edicion del libro: ");
				edicion = sc.nextInt();
				
				// Lo añadimos a la lista
				lista.add(new LibroDTO(edicion, titulo, autor, isbn, edicion));
			} while (preguntaSiNo("Quieres seguir"));
		} catch (Exception e) {
			System.err.println("[ERROR-ImplLibroDTO-insertLibroDto] Error " + e.getMessage());
		}
		
		// Ahora cuando ya los tenemos todos en la lista
		// Inicializamos la interfaz CRUD y llamamos al metodo insertBD
		InterfazCRUDsql intcq = new ImplCRUDsql();
		intcq.insertBD(lista, conexion);
	}
	
	/**
	 * Método que preguntara lo que se pasa por parametro
	 * Si responde que si devuelve true
	 * Si responde que no devuelve false
	 * @param txt
	 * @return
	 */
	private boolean preguntaSiNo(String txt) {
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
			else
				System.err.println("** Error: No se ha introducido una opción correcta **");
		} while (true);
	}

}
