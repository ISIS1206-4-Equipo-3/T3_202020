package modeloLogico;

import java.io.FileReader;

import com.opencsv.CSVReader;

import modeloEstructuraDatos.TablaHashLinearProbing;
import modeloEstructuraDatos.TablaHashSeparateChaining;

public class Modelo {
	
	TablaHashLinearProbing tablaLinearProbing;
	TablaHashSeparateChaining tablaSeparateChaining;
	
	//Estos atributos se encargan de la lectura de los .CSV
	private FileReader archivoPrincipal;
	private CSVReader lectorPrincipal;
	private FileReader archivoSecundario;
	private CSVReader lectorSecundario;

	public String RUTA_DATOS_PRINCIPALES= "./data/small/MoviesCastingRaw-small.csv";
	public String RUTA_DATOS_SECUNDARIOS= "./data/small/SmallMoviesDetailsCleaned.csv";

}
