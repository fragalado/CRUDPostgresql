package aplicacion.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacion.dtos.LibroDTO;

/**
 * Clase de utilidad que contiene los métodos de paso a DTO
 */
public class ADto {
	
	/**
	 * Método que pasa un ResultSet con libros a lista de LibroDTO
	 * @param resultadoConsulta
	 * @return
	 */
	public List<LibroDTO> resultsALibrosDto(ResultSet resultadoConsulta){
		List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
		
		// Leemos el resultado de la consulta hasta que no queden filas
		try {
			while (resultadoConsulta.next()) {
				listaLibros.add(new LibroDTO(resultadoConsulta.getLong("id_libro")
											, resultadoConsulta.getString("titulo")
											, resultadoConsulta.getString("autor")
											, resultadoConsulta.getString("isbn")
											, resultadoConsulta.getInt("edicion")));
			}
			
			System.out.println("[INFO] - Número de libros: " + listaLibros.size());
		} catch (SQLException e) {
			System.err.println("[ERROR-ADto-resultsALibrosDto] Error al pasar el ResultSet a lista de LibroDTO " + e);
		}
		
		return listaLibros; // Devolvemos la lista con los libros
	}
}
