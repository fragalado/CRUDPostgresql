package aplicacion.servicios;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacion.dtos.LibroDTO;
import aplicacion.util.ADto;

public class ImplCRUDsql implements InterfazCRUDsql {

	@Override
	public void insertBD(List<LibroDTO> libro, Connection conexion) {
		
		Statement declaracion = null;
		try {
			// Ponemos el autoCommit en false, de esta manera no estará haciendo commit por cada libro
			conexion.setAutoCommit(false);
			
			declaracion = conexion.createStatement();
			
			String cadenaUpdate;
			
			for (LibroDTO aux : libro) {
				cadenaUpdate = "insert into gbp_almacen.gbp_alm_cat_libros (titulo, autor, isbn, edicion)"
						+ "values ('"+ aux.getTitulo()+"', '"+aux.getAutor()+"', '"+aux.getIsbn()+"', "+aux.getEdicion()+");";
				declaracion.executeUpdate(cadenaUpdate);
			}
			
			// Ahora hacemos el commit
			conexion.commit();
			
			// Cerramos la declaracion
			declaracion.close();
		} catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-insertBD] Error " + e.getMessage());
		}
		
	}

	@Override
	public List<LibroDTO> selectBD(Connection conexion, String query) {
		
		Statement declaracion = null;
		ResultSet resultadoConsulta = null;
		List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
		ADto adto = new ADto();
		
		try {
			
			// Se abre una declaracion
			declaracion = conexion.createStatement();
			// Se define a consulta de la declaracion y se ejecuta
			resultadoConsulta = declaracion.executeQuery(query);
			
			// Llamada a la conversion a dto
			listaLibros = adto.resultsALibrosDto(resultadoConsulta);
			
			// Cerramos la declaracion y el resultado
			declaracion.close();
			resultadoConsulta.close();
			
		} catch (SQLException e) {
			System.err.println("[ERROR-ImplCRUDsql-selectBD] Error " + e);
		}
		
		// Devolvemos la lista de tipo LibroDTO
		return listaLibros;
	}

	@Override
	public void updateBD(Connection conexion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBD(Connection conexion) {
		// TODO Auto-generated method stub
		
	}

}
