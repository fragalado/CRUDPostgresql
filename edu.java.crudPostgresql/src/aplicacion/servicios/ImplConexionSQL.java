package aplicacion.servicios;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Implementación de la interfaz de conexion sql
 */
public class ImplConexionSQL implements InterfazConexionSQL {

	@Override
	public Connection conectaBD() {
		Connection conexion = null;
		String[] parametrosConexion = parametrosConexion();
		
		if(!parametrosConexion[1].isEmpty()) {
			try {
				// Instancia un objeto de la clase interfaz que se le pasa
				Class.forName("org.postgresql.Driver");
				
				// Se establece la conexion
				conexion = DriverManager.getConnection(parametrosConexion[2], parametrosConexion[0], parametrosConexion[1]);
				
				boolean esValida = conexion.isValid(50000); // Devuelve true si la conexion es valida
				if(!esValida) {
					conexion = null;
				}
				System.out.println(esValida ? "La conexion es valida" : "[ERROR-ImplConexionSQL-conectaBBDD] La conexion no es valida");
				return conexion;
			} catch (ClassNotFoundException e) {
				System.err.println("[ERROR-ImplConexionSQL-conectaBBDD] La clase no ha sido encontrada " + e);
				return conexion = null; // Ponemos null para asegurar
			} catch (SQLException e) {
				System.err.println("[ERROR-ImplConexionSQL-conectaBBDD] Error sql " + e.getMessage());
				return conexion = null;
			}
		} else {
			System.err.println("[ERROR-ImplConexionSQL-conectaBBDD] Los parametros de conexion no se han establecido correctamente");
			return conexion;
		}
	}
	
	/**
	 * Método que obtiene los parametros para la conexion de un fichero .properties
	 * Y devuelve los parametros en forma de array
	 * @return
	 */
	private String[] parametrosConexion() {
		String user, pass, port, host, db, url;
		
		Properties p = new Properties();
		try {
			p.load(new FileReader("src/aplicacion/util/configuracion.properties"));
			user = p.getProperty("user");
			pass = p.getProperty("pass");
			port = p.getProperty("port");
			host = p.getProperty("host");
			db = p.getProperty("db");
			url = "jdbc:postgresql://"+ host +":"+ port +"/" + db;
			String[] vParametros = {user,pass,url};
			
			return vParametros;
		} catch (IOException e) {
			System.err.println("[ERROR-ImplConexionSQL-parametrosConexion] No se pudo cargar el archivo");
			return null;
		}
	}

	@Override
	public void desconectarBD(Connection conexion) {
		try {
			conexion.close();
		} catch (SQLException e) {
			System.err.println("[ERROR-ImplConexionSQL-desconectarBD] " + e);
		} catch (NullPointerException e) {
			System.err.println("[ERROR-ImplConexionSQL-desconectarBD] " + e);
		}
	}

}
