package Clases;

public interface ExamI {
	
	/**
	 * metodo que agrega una pregunta a el array de preguntas 
	 * retorna true si fue ingresado false en caso contrario
	 * @param p
	 * @return true o false
	 */
	boolean agregarPregunta(Pregunta p);
	/**
	 * metodo que agrega un usuario que va a hacer la prueba 
	 * retorn true si fue ingresado, false en caso contrario
	 * @param u
	 * @return true o false
	 */
	boolean agregarUsuario(Usuario u);
	/**
	 * metodo que aplica el examen donde muestra todas las preguntas y el usuario debe constestarlas
	 * retorna el puntaje en porcentaje de la prueba realizada
	 * @return
	 */
	int darExam();
}
