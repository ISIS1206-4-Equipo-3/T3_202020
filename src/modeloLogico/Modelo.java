package modeloLogico;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import modeloEstructuraDatos.Nodo;
import modeloEstructuraDatos.Pelicula;
import modeloEstructuraDatos.TablaHashLinearProbing;
import modeloEstructuraDatos.TablaHashSeparateChaining;
import view.View;

public class Modelo {

	public TablaHashLinearProbing tablaLinearProbing;
	public	TablaHashSeparateChaining tablaSeparateChaining;

	//Estos atributos se encargan de la lectura de los .CSV
	private FileReader archivoPrincipal;
	private CSVReader lectorPrincipal;
	private FileReader archivoSecundario;
	private CSVReader lectorSecundario;

	private View view;

	private final static int CRITERIO_NUM_VOTOS = 1;
	private final static int CRITERIO_PROMEDIO_VOTOS = 2;
	private final static int COLUMNA_DIRECTORES = 12;
	private final static int COLUMNA_CALIFICACIONES = 35;
	private final static int COLUMNA_ID = 0;	
	private final static int COLUMNA_TITULO = 34 ;
	private final static int COLUMNA_GENERO = 20;
	private final static int COLUMNA_RELEASE_DATE = 28 ;
	private final static int COLUMNA_COMPANY = 26 ;
	private final static int COLUMNA_ACTOR_1 = 1 ;
	private final static int COLUMNA_ACTOR_2 = 3 ;
	private final static int COLUMNA_ACTOR_3 = 5 ;
	private final static int COLUMNA_ACTOR_4 = 7 ;
	private final static int COLUMNA_ACTOR_5 = 9 ;
	private final static int COLUMNA_NUM_CALIFICACIONES = 36; 
	private final static int COLUMNA_PAIS_PRODUCCION = 9; 

	public String RUTA_DATOS_PRINCIPALES= "./data/small/MoviesCastingRaw-small.csv";
	public String RUTA_DATOS_SECUNDARIOS= "./data/small/SmallMoviesDetailsCleaned.csv";

	public Modelo() {
		view = new View();
	}

	private int buscarTamanoArchivo (String pRutaPrincipal, String pRutaSecundaria) {
		try {
			int rta = 0;
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			archivoPrincipal = new FileReader(pRutaPrincipal);
			lectorPrincipal = new CSVReaderBuilder (archivoPrincipal).withCSVParser(parser).build();
			String [] lineaPrincipal = lectorPrincipal.readNext();
			while((lineaPrincipal = lectorPrincipal.readNext())!=null) {	rta++;  }
			return rta;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public void cargarDatosTablaHashLinearProbing(String pRutaPrincipal, String pRutaSecundaria, int pColumna, boolean pEsArchivoPrincipal)
	{
		try {
			long startTime = System.nanoTime();
			int tamanoArchivoFilas = buscarTamanoArchivo(pRutaPrincipal,pRutaSecundaria);
			tablaLinearProbing = new TablaHashLinearProbing<String, Pelicula>(tamanoArchivoFilas);
			System.out.println("Se ha creado una tabla de hash con manejo de colisiones linear probing de tamano " + tamanoArchivoFilas);
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			archivoPrincipal = new FileReader(pRutaPrincipal);
			archivoSecundario = new FileReader(pRutaSecundaria);
			lectorSecundario = new CSVReaderBuilder(archivoSecundario).withCSVParser(parser).build();
			lectorPrincipal = new CSVReaderBuilder (archivoPrincipal).withCSVParser(parser).build();
			String [] lineaPrincipal = lectorPrincipal.readNext();;
			String [] lineaSecundaria = lectorSecundario.readNext();
			int contador = 0;
			while(((lineaPrincipal = lectorPrincipal.readNext())!=null) && ((lineaSecundaria = lectorSecundario.readNext())!=null)){
				if(lineaPrincipal != null && lineaSecundaria != null) {
					int id = Integer.parseInt(lineaPrincipal[0]);
					int numVotos = Integer.parseInt(lineaSecundaria[18]);
					double promedioVotos = Double.parseDouble(lineaSecundaria[17]);
					Date lanzamiento = new Date();
					try
					{
					DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					lanzamiento = formato.parse(lineaSecundaria[10]);
					}
					catch (Exception e)
					{
						lanzamiento = new Date(0, 0, 0);
					}
					String director = lineaPrincipal[COLUMNA_DIRECTORES];
					String actor1 = lineaPrincipal[COLUMNA_ACTOR_1];
					String actor2 = lineaPrincipal[COLUMNA_ACTOR_2];
					String actor3 = lineaPrincipal[COLUMNA_ACTOR_3];
					String actor4 = lineaPrincipal[COLUMNA_ACTOR_4];
					String actor5 = lineaPrincipal[COLUMNA_ACTOR_5];
					String compania = lineaSecundaria[8];
					String genero = lineaSecundaria[2];
					String titulo = lineaSecundaria[16];
					Pelicula anadir = new Pelicula(compania, lanzamiento, titulo, id, director, numVotos, promedioVotos, actor1, actor2, actor3, actor4, actor5, genero);
					if(pEsArchivoPrincipal)
					{
					tablaLinearProbing.put(lineaPrincipal[pColumna], anadir);
					}
					else
					{
						tablaLinearProbing.put(lineaSecundaria[pColumna], anadir);
					}
					contador++;
					}
					contador++;
					
				}
			
			long endTime = System.nanoTime();
			System.out.println("Primera pelicula");
			((Pelicula) tablaLinearProbing.darPrimerElemento()).imprimirPelicula();
			System.out.println("Ultima pelicula");
			((Pelicula) tablaLinearProbing.darUltimoElemento()).imprimirPelicula();
			System.out.println("-------- Los datos fueron cargados correctamente ("+contador+" peliculas) --------\n");
			System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarDatosTablaHashSeparateChaining(String pRutaPrincipal, String pRutaSecundaria, int pColumna, boolean pEsArchivoPrincipal, boolean imprimirInformacion) {
		try {
			long startTime = System.nanoTime();
			int tamanoArchivoFilas = buscarTamanoArchivo(pRutaPrincipal,pRutaSecundaria)/1;
			tablaSeparateChaining = new TablaHashSeparateChaining<String, Pelicula>(tamanoArchivoFilas);
			if(imprimirInformacion) System.out.println("Se ha creado una tabla de hash con manejo de colisiones separate chaining de tamano " + tamanoArchivoFilas);
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			archivoPrincipal = new FileReader(pRutaPrincipal);
			archivoSecundario = new FileReader(pRutaSecundaria);
			lectorSecundario = new CSVReaderBuilder(archivoSecundario).withCSVParser(parser).build();
			lectorPrincipal = new CSVReaderBuilder (archivoPrincipal).withCSVParser(parser).build();
			String [] lineaPrincipal = lectorPrincipal.readNext();;
			String [] lineaSecundaria = lectorSecundario.readNext();
			int contador = 0;
			while(((lineaPrincipal = lectorPrincipal.readNext())!=null) && ((lineaSecundaria = lectorSecundario.readNext())!=null)){
				int id = Integer.parseInt(lineaPrincipal[0]);
				int numVotos = Integer.parseInt(lineaSecundaria[18]);
				double promedioVotos = Double.parseDouble(lineaSecundaria[17]);
				Date lanzamiento = new Date();
				try
				{
					DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					lanzamiento = formato.parse(lineaSecundaria[10]);
				}
				catch (Exception e)
				{
					lanzamiento = new Date(0, 0, 0);
				}
				String director = lineaPrincipal[COLUMNA_DIRECTORES];
				String actor1 = lineaPrincipal[COLUMNA_ACTOR_1];
				String actor2 = lineaPrincipal[COLUMNA_ACTOR_2];
				String actor3 = lineaPrincipal[COLUMNA_ACTOR_3];
				String actor4 = lineaPrincipal[COLUMNA_ACTOR_4];
				String actor5 = lineaPrincipal[COLUMNA_ACTOR_5];
				String compania = lineaSecundaria[8];
				String genero = lineaSecundaria[2];
				String titulo = lineaSecundaria[16];
				Pelicula anadir = new Pelicula(compania, lanzamiento, titulo, id, director, numVotos, promedioVotos, actor1, actor2, actor3, actor4, actor5, genero);
				if(pEsArchivoPrincipal)
				{
				tablaSeparateChaining.put(lineaPrincipal[pColumna], anadir);
				}
				else
				{
					tablaSeparateChaining.put(lineaSecundaria[pColumna], anadir);
				}
				contador++;
				}

			
			long endTime = System.nanoTime();
			if(imprimirInformacion) {
				System.out.println("Primera pelicula");
				((Pelicula) tablaSeparateChaining.darPrimerElemento()).imprimirPelicula();
				System.out.println("Ultima pelicula");
				((Pelicula) tablaSeparateChaining.darUltimoElemento()).imprimirPelicula();
				System.out.println("-------- Los datos fueron cargados correctamente ("+contador+" peliculas) --------\n");
				System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void entenderGenero(String pGenero)
	{
		tablaSeparateChaining.entenderGenero(pGenero);
	}

	public void conocerPeliculasAnoProduccionLinearProbing(int production_year, String company_name) {
		System.out.println("Peliculas producidas por la compania "+ company_name + " en el  ano :  "+ production_year+ "\n");
		tablaLinearProbing.conocerPeliculasAnoProduccionLinearProbing(company_name+production_year);
		 
	}

	public void conocerPeliculasAnoProduccionSeparateChaining(int production_year, String company_name) 
	{
		System.out.println("Peliculas producidas por la compania "+ company_name + " en el  ano :  "+ production_year+ "\n");
		tablaSeparateChaining.conocerPeliculas(company_name+production_year);
		
	}
	
	public void pruebasDeDesempeno() throws NullPointerException
	{
		if(tablaLinearProbing.isEmpty() || tablaSeparateChaining.isEmpty())
		{
			throw new NullPointerException();
		}
		
			
			
		System.out.println("Prueba de linear Probing, 1000 consultas, 800 existentes, 200 inexistentes");
		String[] existentes = tablaLinearProbing.pruebaExistentes();
		int[] inexistentes = tablaLinearProbing.pruebaInexistentes();
		long startTime = System.nanoTime();
		for(int i =0; i<200; i++)
		{
			tablaLinearProbing.get(inexistentes[i]);
		}
		System.out.println("Dalke");
		for(int j=0 ; j<800; j++)
		{
			tablaLinearProbing.get(existentes[j]);
		}
		long endTime = System.nanoTime();
		System.out.println("Tiempo que tardo la prueba en Linear Probing : " + (endTime-startTime)/1e6 + " ms \n\n");
		System.out.println("Tiempo promedio de realizar una consulta en Linear Probing : " + (endTime-startTime)/1e9 + " ms \n\n");
		
		System.out.println("Prueba de Separate Chaining, 1000 consultas, 800 existentes, 200 inexistentes");
		String[] llaves = tablaSeparateChaining.existentes();
		long startTime2 = System.nanoTime();
		for(int i =0; i < 200; i++)
		{
			tablaSeparateChaining.get((int)Math.random()*100000);
		}
		for(int j=0 ; j<800; j++)
		{
			tablaSeparateChaining.get(llaves[j]);
		}
		long endTime2 = System.nanoTime();
		System.out.println("Tiempo que tardo la prueba en Separate Chaining : " + (endTime2-startTime2)/1e6 + " ms \n\n");
		System.out.println("Tiempo promedio de realizar una consulta en Separate Chaining : " + (endTime2-startTime2)/1e9 + " ms \n\n");
	}
	
	public void conocerActorReq3(String nombreActorABuscar) {
		long startTime = System.nanoTime();
		System.out.println("Se realizará la busqueda del actor "+ nombreActorABuscar + " en 5 tablas diferentes");
		System.out.print("Buscando en la tabla #1");
		conocerActorReq3buscarActorEnColumna(COLUMNA_ACTOR_1, nombreActorABuscar);
		System.out.print("Buscando en la tabla #2");
		conocerActorReq3buscarActorEnColumna(COLUMNA_ACTOR_2, nombreActorABuscar);
		System.out.print("Buscando en la tabla #3");
		conocerActorReq3buscarActorEnColumna(COLUMNA_ACTOR_3, nombreActorABuscar);
		System.out.print("Buscando en la tabla #4");
		conocerActorReq3buscarActorEnColumna(COLUMNA_ACTOR_4, nombreActorABuscar);
		System.out.print("Buscando en la tabla #5");
		conocerActorReq3buscarActorEnColumna(COLUMNA_ACTOR_5, nombreActorABuscar);	
		System.out.println("Busqueda de actor terminada\n");
		long endTime = System.nanoTime();
		System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");
	}
	
	private void conocerActorReq3buscarActorEnColumna(int columna, String nombreActorABuscar) {
		boolean rta = false;
		cargarDatosTablaHashSeparateChaining(RUTA_DATOS_PRINCIPALES, RUTA_DATOS_SECUNDARIOS, columna, true, false);
		Nodo nodoConPeliculas = tablaSeparateChaining.getNodo(nombreActorABuscar);
		while(nodoConPeliculas!=null) {
			if(nodoConPeliculas.darKey().equals(nombreActorABuscar)) {
				System.out.println("");
				((Pelicula) nodoConPeliculas.darValor()).imprimirPelicula();
				rta= true;
			}
			nodoConPeliculas = nodoConPeliculas.darSiguiente();
		}
		if(!rta) System.out.println(" ----- No se encuentraron peliculas en esta tabla");
	}
	
	public void conocerPeliculasDelPaisReq5(String nombrePaisABuscar){
		System.out.println("Buscando peliculas de " + nombrePaisABuscar);
		boolean rta = false;
		long startTime = System.nanoTime();
		cargarDatosTablaHashSeparateChaining(RUTA_DATOS_PRINCIPALES, RUTA_DATOS_SECUNDARIOS, COLUMNA_PAIS_PRODUCCION, false, false);
		Nodo nodoConPeliculas = tablaSeparateChaining.getNodo(nombrePaisABuscar);
		while(nodoConPeliculas!=null) {
			if(nodoConPeliculas.darKey().equals(nombrePaisABuscar)) {
				System.out.println("");
				((Pelicula) nodoConPeliculas.darValor()).imprimirPelicula();
				rta= true;
			}
			nodoConPeliculas = nodoConPeliculas.darSiguiente();
		}
		long endTime = System.nanoTime();
		if(!rta) System.out.println("No se han encontrado peliculas del pais "+ nombrePaisABuscar);
		System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");
	}
	
}
