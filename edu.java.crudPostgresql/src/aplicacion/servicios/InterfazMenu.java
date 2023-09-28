package aplicacion.servicios;

/**
 * Interfaz que define los métodos que darán servicio a Menu
 */
public interface InterfazMenu {
	
	/**
	 * Método que muestra por consola el menu y devuelve la opción elegida
	 * @return
	 */
	public int menu();
	
	/**
	 * Método que preguntara lo que se pasa por parametro
	 * Si responde que si devuelve true
	 * Si responde que no devuelve false
	 * @param txt
	 * @return
	 */
	public boolean preguntaSiNo(String txt);
}
