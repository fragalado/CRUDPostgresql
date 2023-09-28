package aplicacion.servicios;

import java.sql.Connection;

/**
 * Interfaz que define los métodos que darán servicio a LibroDTO
 */
public interface InterfazLibroDTO {
	
	/**
	 * Método para hacer el select de libro
	 * Preguntara si se quiere hacer el select de uno o de todos
	 * Imprimira por pantalla el/los libro/s
	 */
	public void selectLibroDto(Connection conexion);
	
	/**
	 * Método 
	 * @param conexion
	 */
	public void updateLibroDto(Connection conexion);
	
	/**
	 * Método que va pidiendo datos de libros hasta que se cancele
	 * Una vez que se termina de pedir datos hace el insert a la base de datos
	 * @param conexion
	 */
	public void insertLibroDto(Connection conexion);
}
