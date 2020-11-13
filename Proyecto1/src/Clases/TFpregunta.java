package Clases;

import java.util.Scanner;

/**
 * clase donde se implementa los tipos de preguntas
 * verdado y falso
 * @author hello
 *
 */
public class TFpregunta extends Pregunta {
	private boolean respuestaCorrecta;

	/**
	 * constructor clase TFpregunta
	 * recibe texto de la pregunta ,puntaje y true o false
	 * @param text String
	 * @param peso int
	 * @param respuestaCorrecta boolean
	 */
	public TFpregunta(String text, int peso, boolean respuestaCorrecta) {
		super(text, peso);
		this.respuestaCorrecta = respuestaCorrecta;
	}


	/**
	 *metodo que muestra la pregunta, recibe la respuesta del usuario t o f
	 *en caso de que no sea ninguna de las 2 arraja un mensaje
	 */
	
	@Override
	protected boolean buscar() {
		System.out.print(text);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("  ingrese  (T o F ): " );
		String respuesta = sc.next();
		if(respuesta.equalsIgnoreCase("t") && this.respuestaCorrecta) {
			return true;
		}else {
			if(respuesta.equalsIgnoreCase("f") && this.respuestaCorrecta == false) {
				return true;
			}else {
				int intentos = 1;
				sc.nextLine();
				while(!respuesta.equalsIgnoreCase("t") && !respuesta.equalsIgnoreCase("f") && intentos < 3) {
					System.out.println("debe ingresar T o F !!!");
					System.out.print("  ingrese  (T o F ): " );
					respuesta = sc.nextLine();
					if(respuesta.equalsIgnoreCase("t") && this.respuestaCorrecta) return true;
					else {
						if(respuesta.equalsIgnoreCase("f") && this.respuestaCorrecta == false) return true;
						else intentos++;
					}
				}
				if(intentos == 3) System.out.println("   VUELVA A ESTUDIAR !!!!");
				return false;
			}
		}
	}

	/**
	 * metodo que retorna la respuesta correcta true o false
	 * @return boolean
	 */
	public boolean getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	/**
	 * metodo que cambia la respuesta correcta pasabdo por parametro 
	 * un boolean true o false
	 * @param respuestaCorrecta
	 */
	public void setRespuestaCorrecta(boolean respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

}
