package Clases;

/**
 * clase abstracta Pregunta
 * @author hello
 *
 */
public abstract class Pregunta {
	protected String text;
	protected int peso;

	/**
	 *constructor clase pregunta
	 * @param text
	 * @param peso
	 */
	public Pregunta(String text, int peso) {
		this.text = text;
		this.peso = peso;
	}

	/**
	 * metedo que retorna el contenido de la pregunta
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * metodo que retorna el peso de la pregunta (puntaje)
	 * @return
	 */
	public int getPeso() {
		return peso;
	}

	/**
	 * metodo que cambia el peso de la pregunta (puntaje)
	 * @param peso
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * metodo abtracto que se implementa en los hijos de la clase pregunta
	 * @return
	 */
	protected abstract boolean buscar();

}
