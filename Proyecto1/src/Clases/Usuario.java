package Clases;

public class Usuario {
	private String nombreCompleto;
	private int puntaje;
	
	
	public Usuario(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
		this.puntaje = 0;
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}


	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	public int getPuntaje() {
		return puntaje;
	}


	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	

}
