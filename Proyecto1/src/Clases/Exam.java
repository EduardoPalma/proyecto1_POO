package Clases;

import java.util.ArrayList;

/**
 * clase examen que hace los requerimientos esenciales y las inicializacion de las variables
 * a guardar 
 * @author hello
 *
 */
public class Exam implements ExamI {
	private static final String CODIGOADMIN = "ABCDT";
	private ArrayList<String> namExam;
	private ArrayList<Pregunta> preguntas;
	private ArrayList<Usuario> usuarios;
	private int contador;
	
	/**
	 * constructor de la clase Exam
	 * inicializa las variables
	 */
	public Exam() {
		this.namExam = new ArrayList<String>();
		this.preguntas = new ArrayList<Pregunta>();
		this.usuarios = new ArrayList<Usuario>();
		this.contador = 0;
	}
	
	/**
	 * metodo que retorna el contador de preguntas del examen
	 * @return
	 */
	public int getContador() {
		return contador;
	}
	
	/**
	 * metodo que ingresa el nombre de un examen al sistema
	 * @param nombreExamen
	 */
	public void agregarNombrExamen(String nombreExamen) {
		this.namExam.add(nombreExamen);
	}

	@Override
	public boolean agregarUsuario(Usuario u) {
		this.usuarios.add(u);
		return true;
	}
	
	/**
	 * metodo para verificar que el usuario es administrador o va admnistrar los examenes
	 * return true si es correcto el codigo de ingreso en caso contrario false
	 * @param codigo
	 * @return boolean
	 */
	public boolean admin(String codigo) {
		if(CODIGOADMIN.equals(codigo)) return true;
		else return false;
	}

	@Override
	public boolean agregarPregunta(Pregunta p) {
		if(this.contador < 10 ) {
			preguntas.add(p);
			this.contador++;
			return true;
		}else return false;
	}

	@Override
	public int darExam() {
		double puntos = 0;
		double puntosTotales = 0;
		for(int i=0;i<preguntas.size();i++) {
			if(preguntas.get(i) instanceof TFpregunta) {
				TFpregunta aux = (TFpregunta) preguntas.get(i);
				if(aux.buscar()) {
					puntos = puntos + aux.peso;
					puntosTotales = puntosTotales + aux.peso;
					System.out.println("Respuesta Correcta !! \n");
				}
				else {
					puntosTotales = puntosTotales + aux.peso;
					System.out.println("RESPUESTA INCORRECTA !!!\n");
				}
			}else {
				if(preguntas.get(i) instanceof PreguntSelecMul) {
					PreguntSelecMul aux = (PreguntSelecMul) preguntas.get(i);
					if(aux.buscar()) {
						puntos = puntos + aux.peso;
						puntosTotales = puntosTotales + aux.peso;
						System.out.println("Respuesta Correcta !! \n");
					}
					else {
						puntosTotales = puntosTotales + aux.peso;
						System.out.println("RESPUESTA INCORRECTA !!!\n");
					}
				}else {
					if(preguntas.get(i) instanceof PregunCortas) {
						PregunCortas aux = (PregunCortas) preguntas.get(i);
						if(aux.buscar()) {
							puntos = puntos + aux.peso;
							puntosTotales = puntosTotales + aux.peso;
							System.out.println("Respuesta Correcta !! \n");
						}
						else {
							puntosTotales = puntosTotales + aux.peso;
							System.out.println("RESPUESTA INCORRECTA !!!\n");
						}
					}
				}
			}
		}
		double resultado = Math.round((puntos/puntosTotales)*100); 
		return (int) resultado;
	}

	/**
	 * metodo que retorna la lista de usuarios
	 * @return
	 */
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * metodo que retorna la lista de preguntas del examen
	 * @return Arraylist
	 */
	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}

	/**
	 * metodo que retorna la lista de nombre de examenes en el sistema
	 * @return
	 */
	public ArrayList<String> getNamExam() {
		return namExam;
	}
}
