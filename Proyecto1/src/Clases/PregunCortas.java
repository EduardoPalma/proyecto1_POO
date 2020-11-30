package Clases;

import java.util.Scanner;

/**
 * clase de preguntas cortas que herada de clase pregunta
 * @author hello
 *
 */
public class PregunCortas extends Pregunta {
	private String respuesta;
	
	/**
	 * contructor de clase preguntas cortas
	 * recibe el texto de la pregunta, el puntaje y la respuesta
	 * @param text String
	 * @param peso int
	 * @param respuesta String
	 */
	public PregunCortas(String text,int peso,String respuesta) {
		super(text,peso);
		this.respuesta = respuesta;
	}

	/**
	 *metodo que presenta la pregunta y captura la respuesta del usuario 
	 *retorna true si es correcta false en caso contrario
	 */


	@Override
	protected boolean buscar() {
		System.out.print(text + ": ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String respuesta = sc.nextLine();
		if(respuesta.equalsIgnoreCase(this.respuesta)) return true;
		else return false;
	}

	/**
	 * retorna la respuesta a la pregunta
	 * @return String
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * metodo que cambia la respuesta correcta
	 * recibe un string de parametro
	 * @param respuesta
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
