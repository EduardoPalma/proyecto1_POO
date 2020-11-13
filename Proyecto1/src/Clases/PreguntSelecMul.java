package Clases;


import java.util.Scanner;

/**
 * clase preguntas de seleccion multiple que herada de pregunta
 * @author hello
 *
 */
public class PreguntSelecMul extends Pregunta {
	private String [] respuestas;
	private int respuesta;
	
	/**
	 * contructor clase preguntas de seleccion multiple
	 * recibe el texto de la pregunta, el puntaje,el array de respuestas y el indice
	 * de la respuesta correcta
	 * @param text String
	 * @param peso int
	 * @param respuestas array String
	 * @param respuesta int
	 */
	public PreguntSelecMul(String text,int peso,String [] respuestas,int respuesta) {
		super(text,peso);
		this.respuestas = respuestas;
		this.respuesta = respuesta;
	}

	/**
	 *metodo buscar donde muestra la pregunta y el arreglo de respuestas
	 *pregunta la letra de la respuesta al usuario verfica que sea correcta 
	 *retorna true si esta correcta en caso contraio false
	 */

	@Override
	protected boolean buscar() {
		System.out.println(text);
		char c = 'a';
		for(int i=0;i<respuestas.length;i++) {
			if(respuestas[i] == null) break;
			System.out.println("  "+c+". "+respuestas[i]);
			c++;
		}
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("ingrese la letra de la respuesta correcta : ");
		String respuesta = sc.next();
		
		if(respuesta.length() > 1) {
			System.out.println("ingreso no permitido se toma como incorrecta la respuesta!!!!");
			return false;
		}else {
			char c1 = 'a';
			for(int i=0;i<respuestas.length;i++) {
				if(i == this.respuesta && c1 == respuesta.charAt(0)) return true;
				c1++;
			}
		}
		return false;
	}

	/**
	 * metodo que retorna el indice de la respuesta correcta
	 * @return int
	 */
	public int getRespuesta() {
		return respuesta;
	}

	/**
	 * metodo que cambia el inidice de la respuesta correcta 
	 * recibe un int de parametro
	 * @param respuesta
	 */
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * retorna el array de respuestas 
	 * @return array String
	 */
	public String[] getRespuestas() {
		return respuestas;
	}

	/**
	 * cambia el array de respuesta 
	 * recibe un array de string 
	 * @param respuestas
	 * 
	 */
	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}
}
