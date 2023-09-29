package aplicacion.servicios;

import java.sql.Connection;
import java.util.List;

import aplicacion.dtos.LibroDTO;

/**
 * Interfaz que define los métodos que darán servicio a la base de datos
 * Tendremos el metodo insert, select, update y delete
 */
public interface InterfazCRUDsql {

	/**
	 * Método que sirve para insertar los valores del objeto pasado por parametro
	 * Va pidiendo datos de libros hasta que se cancele
	 * Una vez que se termina de pedir datos hace el insert a la base de datos
	 * @param conexion
	 */
	public void insertLibro(Connection conexion);
	
	/**
	 * Método para hacer el select de libro
	 * Preguntara si se quiere hacer el select de uno o de todos
	 * Devolvera una lista de libro
	 * @param conexion
	 * @return Devuelve la lista de libro/s
	 */
	public List<LibroDTO> selectLibro(Connection conexion);
	
	/**
	 * Método para hacer un update a un libro de la base de datos
	 * Pedira el isbn del libro y despues pedira los nuevos datos a introducir
	 * @param conexion
	 */
	public void updateLibro(Connection conexion);
	
	/**
	 * Método que hara un delete a un libro de la base de datos
	 * Pide el isbn del libro a eliminar
	 * Pregunta si se quiere eliminar y si se responde que si lo elimina de la base de datos
	 * @param conexion
	 */
	public void deleteLibro(Connection conexion);
}
