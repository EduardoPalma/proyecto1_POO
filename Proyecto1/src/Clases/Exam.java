package Clases;

import java.util.ArrayList;

public class Exam implements ExamI {
	private static final String CODIGOADMIN = "ABCDT";
	private ArrayList<String> namExam;
	private ArrayList<Pregunta> preguntas;
	private ArrayList<Usuario> usuarios;
	private int contador;
	
	public Exam() {
		this.namExam = new ArrayList<String>();
		this.preguntas = new ArrayList<Pregunta>();
		this.usuarios = new ArrayList<Usuario>();
		this.contador = 0;
	}
	
	public int getContador() {
		return contador;
	}
	
	public void agregarNombrExamen(String nombreExamen) {
		this.namExam.add(nombreExamen);
	}
	@Override
	public boolean agregarUsuario(Usuario u) {
		this.usuarios.add(u);
		return true;
	}
	
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

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public ArrayList<String> getNamExam() {
		return namExam;
	}

	public void setNamExam(ArrayList<String> namExam) {
		this.namExam = namExam;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
}
