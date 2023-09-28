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
	 */
	public void insertBD(List<LibroDTO> libro, Connection conexion);
	
	/**
	 * 
	 * @param conexion
	 * @param query
	 * @return
	 */
	public List<LibroDTO> selectBD(Connection conexion, String query);
	
	/**
	 * 
	 * @param conexion
	 */
	public void updateBD(Connection conexion);
	
	/**
	 * 
	 * @param conexion
	 */
	public void deleteBD(Connection conexion);
}
