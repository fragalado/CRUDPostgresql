package aplicacion.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import aplicacion.dtos.LibroDTO;
import aplicacion.util.ADto;

public class ImplCRUDsql implements InterfazCRUDsql {

	@Override
	public void insertLibro(Connection conexion) {
		
		// Lista para guardar los libros
		List<LibroDTO> lista = new ArrayList<LibroDTO>();
		
		PreparedStatement declaracion = null;
		try {
			// Pediremos datos hasta que se cancele.
			// Variables donde guardaremos los datos:
			String titulo, autor, isbn;
			int edicion;
			
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
				lista.add(new LibroDTO(0, titulo, autor, isbn, edicion));
			} while (preguntaSiNo("Quieres seguir"));
			
			
			// Ponemos el autoCommit en false, de esta manera no estará haciendo commit por
			// cada libro
			conexion.setAutoCommit(false);

			for (LibroDTO aux : lista) {
				declaracion = conexion.prepareStatement("insert into gbp_almacen.gbp_alm_cat_libros (titulo, autor, isbn, edicion) values (?, ?, ?,?);");
				declaracion.setString(1, aux.getTitulo());
				
				declaracion.setString(3, aux.getIsbn());
				declaracion.setString(2, aux.getAutor());
				declaracion.setInt(4, aux.getEdicion());
				declaracion.executeUpdate();
			}

			// Ahora hacemos el commit
			conexion.commit();

		} catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-insertLibro] Error " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERROR-ImplCRUDsql-insertLibro] Error " + e.getMessage());
		}
		
		// Cerramos la declaracion y la conexion
		try {
			conexion.close();
			declaracion.close();
		} catch (Exception e) {
			System.err.println("[ERROR-ImplCRUDsql-insertLibro] Error " + e.getMessage());
		}

	}

	@Override
	public List<LibroDTO> selectLibro(Connection conexion) {

		// Scanner para leer
		Scanner sc = new Scanner(System.in);

		// Lista donde guardaremos los libros
		List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
		
		//
		PreparedStatement declaracion = null;
		ResultSet resultadoConsulta = null;
		ADto adto = new ADto();

		try {
			// Preguntamos si se quiere hacer el select de todos los libros
			// o de uno en concreto
			if (preguntaSiNo("Quieres devolverlos todos")) {
				// Devuelve el select con todos
				declaracion = conexion.prepareStatement("select * from gbp_almacen.gbp_alm_cat_libros;");
			} else {
				// Preguntamos por el isbn para hacer el select
				System.out.print("Introduzca el isbn de libro: ");
				
				declaracion = conexion.prepareStatement("select * from gbp_almacen.gbp_alm_cat_libros where isbn=?;");
				declaracion.setString(1, sc.nextLine());
			}
			
			// Se ejecuta la declaracion
			resultadoConsulta = declaracion.executeQuery();

			// Llamada a la conversion a dto
			listaLibros = adto.resultsALibrosDto(resultadoConsulta);

		} catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-selectLibro] Error " + e.getMessage());
		} catch (InputMismatchException e) {
			System.err.println("[ERROR-ImplCRUDsql-selectLibro] Error no se ha introducido el formato correcto");
		} catch (NoSuchElementException e) {
			System.err.println("[ERROR-ImplCRUDsql-selectLibro] Error " + e.getMessage());
		} catch (IllegalStateException e) {
			System.err.println("[ERROR-ImplCRUDsql-selectLibro] Error " + e.getMessage());
		}

		// Cerramos la declaracion, el resultado y la conexion
		try {
			declaracion.close();
			resultadoConsulta.close();
			conexion.close();
		} catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-selectLibro] Error " + e.getMessage());
		}

		
		// Devolvemos la lista de tipo LibroDTO
		return listaLibros;
	}

	@Override
	public void updateLibro(Connection conexion) {
		
		// Scanner para leer datos
		Scanner sc = new Scanner(System.in);
		
		PreparedStatement declaracion = null;
		try {
			// Pedimos el isbn del libro a modificar
			System.out.print("Introduzca el isbn del libro a modificar: ");
			String isbn = sc.nextLine(); // No comprobaremos si el isbn existe o no
			
			// Ahora pedimos los nuevos datos del libro
			System.out.print("Introduzca el nuevo titulo del libro: "); // Titulo
			String nuevoTitulo = sc.nextLine();
			
			System.out.print("Introduzca el nuevo autor del libro: "); // Autor
			String nuevoAutor = sc.nextLine();
			
			System.out.print("Introduzca el nuevo isbn del libro: "); // Isbn
			String nuevoIsbn = sc.nextLine();
			
			System.out.print("Introduzca la nueva edicion del libro: "); // Edicion
			int nuevaEdicion = sc.nextInt();
			
			declaracion = conexion.prepareStatement("update gbp_almacen.gbp_alm_cat_libros set titulo=?, autor=?, isbn=?, edicion=? where isbn=?");
			declaracion.setString(1, nuevoTitulo);
			declaracion.setString(2, nuevoAutor);
			declaracion.setString(3, nuevoIsbn);
			declaracion.setInt(4, nuevaEdicion);
			declaracion.setString(5, isbn);
			
			declaracion.executeUpdate();
			
		} catch (InputMismatchException e) {
			System.err.println("[ERROR-ImplCRUDsql-updateLibro] Error no se ha introducido el formato correcto");
		} catch (NoSuchElementException e) {
			System.err.println("[ERROR-ImplCRUDsql-updateLibro] Error " + e.getMessage());
		} catch (IllegalStateException e) {
			System.err.println("[ERROR-ImplCRUDsql-updateLibro] Error " + e.getMessage());
		} 
		catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-updateLibro] Error " + e.getMessage());
		}
		
		// Cerramos la declaracion y la conexion
		try {
			conexion.close();
			declaracion.close();
		} catch (Exception e) {
			System.err.println("[ERROR-ImplCRUDsql-updateLibro] Error " + e.getMessage());
		}

	}

	@Override
	public void deleteLibro(Connection conexion) {
		
		// Scanner para leer datos
		Scanner sc = new Scanner(System.in);
		
		PreparedStatement declaracion = null;
		try {
			// Pedimos el isbn del libro a eliminar
			System.out.print("Introduzca el isbn del libro a eliminar: ");
			String isbn = sc.nextLine(); // No comprobaremos si el isbn existe o no
			
			// Preguntamos si se quiere eliminar
			// Si se responde que si se enviara la query a la base de datos y se eliminara
			if(preguntaSiNo("Estas seguro de que quieres eliminar el libro con el isbn: "+isbn)) {
				declaracion = conexion.prepareStatement("delete from gbp_almacen.gbp_alm_cat_libros where isbn=?");
				declaracion.setString(1, isbn);
				declaracion.executeUpdate();
			}
		} catch (SQLTimeoutException e) {
			System.err.println("[ERROR-ImplCRUDsql-deleteLibro] Error " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-deleteLibro] Error " + e.getMessage());
		} catch (NoSuchElementException e) {
			System.err.println("[ERROR-ImplCRUDsql-deleteLibro] Error " + e.getMessage());
		} catch (IllegalStateException e) {
			System.err.println("[ERROR-ImplCRUDsql-deleteLibro] Error " + e.getMessage());
		}
		
		// Cerramos la declaracion y la conexion
		try {
			conexion.close();
			declaracion.close();
		} catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-deleteLibro] Error " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("[ERROR-ImplCRUDsql-deleteLibro] Error " + e.getMessage());
		}
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
