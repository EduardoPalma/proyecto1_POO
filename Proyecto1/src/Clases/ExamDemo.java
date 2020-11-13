package Clases;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * clase testeadora para el desarrollo del proyecto
 * @author hello
 *
 */
public class ExamDemo {
	/**
	 * procedimiento que hace las ejecuciones de los menu y donde existe
	 * el menu de admin y el de realizacion de examen
	 * @throws IOException
	 */
	public static void menu() throws IOException {
		Exam e = new Exam();
		examenes(e);
		Scanner sc = new Scanner(System.in);
		boolean ejecuta = true;
		while(ejecuta) {
			System.out.println("------------------------------------");
			System.out.println(" BIENVENIDO A SISTEMA DE EXAMENES ");
			System.out.print("    desea hacer un examen (S/N) : ");
			String codigo = sc.nextLine();
			if(e.admin(codigo)) {
				modoAdmin(sc,e);
			}else {
				if(codigo.equalsIgnoreCase("S")) {
					System.out.println("---------------------------");
					System.out.println("EXAMENES EN EL SISTEMA ");
					if(e.getNamExam().size() == 0) System.out.println("\n NO HAY EXAMENES EN EL SISTEMA ");
					int i;
					boolean si = false;
					for(i=0;i<e.getNamExam().size();i++) {
						System.out.println((i+1)+" examen "+e.getNamExam().get(i));
						System.out.print("desea hacer este examen (S/N) : ");
						String sino = sc.nextLine();
						if(sino.equalsIgnoreCase("S")) {
							si = true;
							break;
						}
					}
					System.out.println();
					if(si) {
						lecturaExamen(e.getNamExam().get(i),e);
						System.out.print("ingrese nombre completo para empezar la prueba : ");
						String nombreUsuario = sc.nextLine();
						Usuario u = new Usuario(nombreUsuario);
						System.out.println();
						int total = e.darExam();
						System.out.println("      puntaje obtenido es : "+total+"%");
						u.setPuntaje(total);
						e.agregarUsuario(u);
						archivoUsuarios(u,e.getNamExam().get(i));
						e.getPreguntas().clear();
					}else {
						System.out.println(" ¡¡¡ GRACIAS POR PARTICIPAR !!! ");
						ejecuta = false;
					}
				}else {
					ejecuta = false;
					System.out.println(" ¡¡¡ GRACIAS POR PARTICIPAR !!! ");
				}
			}
		}
		
	}
	
	/**
	 * procedimiento que muestra el menu de admin y sus opciones donde el admin ingresa la opcion a resolver
	 * @param sc
	 * @param e
	 * @throws IOException
	 */
	public static void modoAdmin(Scanner sc,Exam e) throws IOException {
		boolean ejecuta = true;
		while(ejecuta) {
			System.out.println();
			System.out.println("    MODO ADMINISTRADOR   ");
			System.out.println("-------------------------");
			System.out.println(" CREAR UN NUEVO EXAMEN                             (1)");
			System.out.println(" ELIMINAR UN EXAMEN DEL SISTEMA                    (2)");
			System.out.println(" MOSTRAR RESULTADOS DE USUARIOS DE EXAMENES HECHOS (3)");
			System.out.println(" VOLVER A MENU PARA REALIZAR PRUEBA                (4)");
			System.out.println("-------------------------");
			System.out.print(" ingrese opcion : ");
			String opcion = sc.nextLine();
			if(opcion.equals("1")) crearExamen(sc,e);
			if(opcion.equals("2")) eliminarExamen(e,sc);
			if(opcion.equals("3")) resultadosExamenes();
			if(opcion.equals("4")) ejecuta = false;
		}
	}
	
	/**
	 * procedimiento que elimina un examen del sistema 
	 * se pregunta si desea eliminarlo y se elimina (se elimna el archivo del examen) 
	 * @param e
	 * @param sc
	 * @throws IOException
	 */
	public static void eliminarExamen(Exam e,Scanner sc) throws IOException {
		int i;
		boolean si = false;
		for(i=0;i<e.getNamExam().size();i++) {
			System.out.println((i+1)+" examen "+e.getNamExam().get(i));
			System.out.print("desea eliminar este Examen (S/N) : ");
			String sino = sc.nextLine();
			if(sino.equalsIgnoreCase("S")) {
				si = true;
				break;
			}
		}
		if(si) {
			File archivo = new File("archivos/examen_"+e.getNamExam().get(i)+".txt");
			FileWriter ficheroExamen = new FileWriter("archivos/examenes.txt");
			PrintWriter archivoExamen = new PrintWriter(ficheroExamen);
			e.getNamExam().remove(i);
			for(int j=0;j<e.getNamExam().size();j++) {
				archivoExamen.println(e.getNamExam().get(j));
			}
			archivoExamen.close();
			ficheroExamen.close();
			if(archivo.delete())System.out.println(" EXAMEN A SIDO ELIMINADO ");
			
		}
		
	}
	
	/**
	 * lee un examen del archivo correspondiente y la guarda en el sistema para la realizacion de este
	 * @param nombreExamen
	 * @param e
	 * @throws FileNotFoundException
	 */
	public static void lecturaExamen(String nombreExamen,Exam e) throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("archivos/examen_"+nombreExamen+".txt"));
		while(archivo.hasNextLine()) {
			String linea = archivo.nextLine();
			String [] parte = linea.split(",");
			if(parte[0].equals("PTF")) {
				Pregunta p = new TFpregunta(parte[1],Integer.parseInt(parte[2]),Boolean.parseBoolean(parte[3]));
				e.getPreguntas().add(p);
			}
			if(parte[0].equals("PC")) {
				Pregunta p = new PregunCortas(parte[1],Integer.parseInt(parte[2]),parte[3]);
				e.getPreguntas().add(p);
			}
			if(parte[0].equals("PSM")) {
				int i = Integer.parseInt(parte[4]);
				String [] alternativas = new String[6];
				for(int j=0;j<i;j++) {
					alternativas[j] = parte[4+j+1];
				}
				Pregunta p = new PreguntSelecMul(parte[1],Integer.parseInt(parte[2]),alternativas,Integer.parseInt(parte[3]));
				e.getPreguntas().add(p);
			}
		}
		archivo.close();
	}
	
	/**
	 * muestra los resultados de los examenes hechos de un archivo en el cual estan los resultados
	 * de cada usuario y la prueba hecha
	 * @throws FileNotFoundException
	 */
	public static void resultadosExamenes() throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("archivos/puntajes_usuarios.txt"));
		while(archivo.hasNextLine()) {
			String linea = archivo.nextLine();
			String [] parte = linea.split(",");
			System.out.println("nombre de la prueba : "+parte[2]+" | usuario : "+parte[0] + " | porcentaje obtenido : "+parte[1]+"%");
		}
	}
	/**
	 * lee el archivo con los nombre de los examenes y los guarda en el sistema
	 * @param examen
	 * @throws FileNotFoundException
	 */
	public static void examenes(Exam examen) throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("archivos/examenes.txt"));
		while(archivo.hasNextLine()) {
			String nombre = archivo.nextLine();
			examen.agregarNombrExamen(nombre);
		}
		archivo.close();
	}
	/**
	 * guarda el examen nuevo en un archivo nuevo generado con la estructura
	 * predifinada para la lectura del mismo al ser seleccionado
	 * @param examen
	 * @param nombreExamen
	 * @throws IOException
	 */
	public static void archivoExamen(Exam examen,String nombreExamen) throws IOException {
		FileWriter ficheroExamen = new FileWriter("archivos/examenes.txt",true);
		PrintWriter archivoExamen = new PrintWriter(ficheroExamen);
		archivoExamen.println(nombreExamen);
		FileWriter ficheroNuevo = new FileWriter("archivos/examen_"+nombreExamen+".txt");
		PrintWriter archivo = new PrintWriter(ficheroNuevo);
		for(int i=0;i<examen.getPreguntas().size();i++) {
			if(examen.getPreguntas().get(i) instanceof TFpregunta) {
				TFpregunta aux = (TFpregunta) examen.getPreguntas().get(i);
				archivo.println("PTF,"+aux.getText()+","+aux.getPeso()+","+aux.getRespuestaCorrecta());
			}else {
				if(examen.getPreguntas().get(i) instanceof PregunCortas) {
					PregunCortas aux = (PregunCortas) examen.getPreguntas().get(i);
					archivo.println("PC,"+aux.getText()+","+aux.getPeso()+","+aux.getRespuesta());
				}else {
					if(examen.getPreguntas().get(i) instanceof PreguntSelecMul) {
						PreguntSelecMul aux = (PreguntSelecMul) examen.getPreguntas().get(i);
						int z;
						for(z=0;z<aux.getRespuestas().length;z++) {
							if(aux.getRespuestas()[z] == null) break;
						} 
						archivo.print("PSM,"+aux.getText()+","+aux.getPeso()+","+aux.getRespuesta()+","+z);
						for(int j=0;j<z;j++) {
							archivo.print(","+aux.getRespuestas()[j]);
						}
						archivo.println();
					}
				}
			}
		}
		ficheroExamen.close();
		archivoExamen.close();
		ficheroNuevo.close();
		archivo.close();
	}
	
	
	/**
	 * menu donde se crea un nuevo examen se muestra la opciones de los tipo de pregunta a incluir
	 * y el admin debe ingresar la opcion pertinente
	 * @param sc
	 * @param examen
	 * @throws IOException
	 */
	public static void crearExamen(Scanner sc,Exam examen) throws IOException {
		System.out.println("----------------------------------");
		System.out.println("       CREACION DE EXAMEN          ");
		System.out.print(" ingrese nombre del examen : ");
		String nombre = sc.nextLine();
		System.out.println("el examen consta de maximo 10 preguntas ");
		int cant = 0;
		int sigue = 1;
		while(cant < 10 && sigue == 1) {
			System.out.println("  Pregunta de Verdadero y Falso   (1)");
			System.out.println("  Preguntas Cortas                (2)");
			System.out.println("  Preguntas de Seleccion Multiple (3)");
			System.out.print("  ingrese el numero de la pregunta a ingresar (4 para terminar) : ");
			String sPregunta = sc.nextLine();
			if(sPregunta.equals("1")) {
				System.out.println(" \n   Pregunta de Verdadero y Falso ");
				preguntaTF(sc,examen);
				cant++;
			}else {
				if(sPregunta.equals("2")) {
					System.out.println(" \n   Pregunta Corta ");
					preguntaCorta(sc,examen);
					cant++;
				}else {
					if(sPregunta.equals("3")) {
						System.out.println(" \n   Pregunta de Seleccion Multiple ");
						preguntaSelecMul(sc,examen);
						cant++;
					}else {
						if(sPregunta.equals("4")){
							if(cant > 1) sigue=0;
							else System.out.println("  DEBE SER UN MINIMO DE 2 ALTERNATIVAS !!!!! ");
						}
					}
				}
			}
		}
		examen.getNamExam().add(nombre);
		archivoExamen(examen,nombre);
		examen.getPreguntas().clear();
		
	}
	/**
	 * en este caso guarda el puntaje, nombre y la prueba del usuario que 
	 * realiza el examen en el archivo correspondiente 
	 * @param u
	 * @param nombrePrueba
	 * @throws IOException
	 */
	public static void archivoUsuarios(Usuario u,String nombrePrueba) throws IOException {
		FileWriter fichero = new FileWriter("archivos/puntajes_usuarios.txt",true);
		PrintWriter archivo = new PrintWriter(fichero);
		archivo.println(u.getNombreCompleto()+","+u.getPuntaje()+","+nombrePrueba);
		fichero.close();
		archivo.close();
	}
	
	/**
	 * procedimiento que solo toma los valores requeridos para la creacion de una
	 * pregunta de tipo verdadero y falso, donde procede a crearlo y guardarlo en el sistema
	 * @param sc
	 * @param examen
	 */
	public static void preguntaTF(Scanner sc,Exam examen) {
		System.out.println("ingrese el contenido de la pregunta en cuestion : ");
		String text = sc.nextLine();
		int peso;
		try {
			System.out.print("ingrese el puntaje para esa pregunta : ");
			peso = sc.nextInt();
		}catch (Exception e ){
			System.out.print("el valor ingresado debe ser un numero por defecto se le tomara como valor 5");
			peso = 5;
		}
		System.out.print("ingrese (true o false) de la respuesta correcta : ");
		sc.nextLine();
		String bool = sc.nextLine();
		if(!bool.equalsIgnoreCase("true") && !bool.equalsIgnoreCase("false")) {
			System.out.println("valor debe ser true o false(tomara el dato como false)");
			boolean log = false;
			Pregunta p = new TFpregunta(text,peso,log);
			examen.agregarPregunta(p);
		}else {
			if(bool.equalsIgnoreCase("true")) {
				boolean log = true;
				Pregunta p = new TFpregunta(text,peso,log);
				examen.agregarPregunta(p);
			}else{
				boolean log = false;
				Pregunta p = new TFpregunta(text,peso,log);
				examen.agregarPregunta(p);
			}
		}

	}
	
	/**
	 * procedimiento que pregunta lo correspondiente para la creacion de una pregunta corta
	 * donde lo crea y lo guarda en el sistema
	 * @param sc
	 * @param exam
	 */
	public static void preguntaCorta(Scanner sc,Exam exam) {
		System.out.println("ingrese el contenido de la pregunta en cuestion : ");
		String text = sc.nextLine();
		int peso;
		try {
			System.out.print("ingrese el puntaje para esa pregunta : ");
			peso = sc.nextInt();
		}catch (Exception e ){
			System.out.print("el valor ingresado debe ser un numero por defecto se le tomara como valor 10");
			peso = 10;
		}
		System.out.println("ingrese la respuesta corta a la pregunta : ");
		sc.nextLine();
		String respuesta = sc.nextLine();
		Pregunta p = new PregunCortas(text,peso,respuesta);
		exam.agregarPregunta(p);
	
	}
	
	/**
	 * procedimiento que pregunta lo necesario para la creacion de una nueva pregunta de seleccion
	 * multiple, donde lo crea y lo guarda en el sistema
	 * @param sc
	 * @param exam
	 */
	public static void preguntaSelecMul(Scanner sc,Exam exam) {
		System.out.println("ingrese el contenido de la pregunta en cuestion : ");
		String text = sc.nextLine();
		int peso;
		try {
			System.out.print("ingrese el puntaje para esa pregunta : ");
			peso = sc.nextInt();
		}catch (Exception e ){
			System.out.println("el valor ingresado debe ser un numero por defecto se le tomara como valor 7");
			peso = 7;
		}
		sc.nextLine();
		System.out.println("ingrese las respuestas para la pregunta de seleccion multiple");
		System.out.println("minima de alternativas = 2");
		System.out.println("maxima de alternativas = 6");
		String [] respuestas = new String[6];
		String sn;
		for(int i=0;i<6;i++) {
			System.out.println("ingrese alternativa : ");
			String respuesta = sc.nextLine();
			respuestas[i] = respuesta;
			if(i>=2) {
				System.out.println("desea seguir agregando alternativas (S/N)");
				sn = sc.nextLine();
				if(sn.equalsIgnoreCase("N")) break;
			}
		}
		int posRepuesta;
		try {
			System.out.print("ingrese la posicion de la altarnativa correcta : ");
			posRepuesta = sc.nextInt();
		}catch (Exception e) {
			System.out.println("el valor ingresado debe ser un numero por defecto se le ingresara la posicion 0");
			posRepuesta = 0;
		}
		sc.nextLine();
		Pregunta p = new PreguntSelecMul(text,peso,respuestas,posRepuesta);
		exam.agregarPregunta(p);
		
	}
	
	public static void main(String [] args) throws IOException {
		menu();
	}
}
