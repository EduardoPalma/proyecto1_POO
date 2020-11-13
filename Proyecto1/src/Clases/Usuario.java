package Clases;

/**
 * clase Usuario que registra el nombre y el puntaje del usuario a hacer el examen
 * @author hello
 *
 */
public class Usuario {
	private String nombreCompleto;
	private int puntaje;
	
	
	/**
	 * contructor Usuario
	 * @param nombreCompleto
	 */
	public Usuario(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
		this.puntaje = 0;
	}


	/**
	 * metodo que retorna el nombreCompleto
	 * @return nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}


	/**
	 * metodo que retorna el puntaje del usuario
	 * @return puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}


	/**
	 * metodo que cambia el puntaje del usuario
	 * @param puntaje
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	

}
