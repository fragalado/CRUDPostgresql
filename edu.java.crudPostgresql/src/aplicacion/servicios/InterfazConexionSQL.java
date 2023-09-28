package aplicacion.servicios;

import java.sql.Connection;

/**
 * Interfaz que define los métodos que realizarán la conexion sql
 */
public interface InterfazConexionSQL {

	/**
	 * Método que hace la conexion con la base de datos a partir de los datos guardados en
	 * el archivo .properties
	 * @return Devuelve la conexion abierta
	 */
	public Connection conectaBD();
	
	/**
	 * Método que desconecta la conexion con la base de datos
	 * Desconecta la conexion que se le pasa por parametros
	 */
	public void desconectarBD(Connection conexion);
}
