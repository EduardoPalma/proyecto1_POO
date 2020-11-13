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

	public String getText() {
		return text;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * metodo abtracto que se implementa en los hijos de la clase pregunta
	 * @return
	 */
	protected abstract boolean buscar();

}
