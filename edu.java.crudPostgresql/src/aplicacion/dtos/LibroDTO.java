package aplicacion.dtos;

/**
 * Entidad que contendrá los libros
 */
public class LibroDTO {
	
	// Atributos
	
	private long idLibro;
	private String titulo;
	private String autor;
	private String isbn;
	private int edicion;
	
	// Constructores -> Constructor con todos los parámetros, el constructor vacío no lo tiene
	
	public LibroDTO(long idLibro, String titulo, String autor, String isbn, int edicion) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.edicion = edicion;
	}
	
	// Getters
	
	public long getIdLibro() {
		return idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getEdicion() {
		return edicion;
	}
	
}
