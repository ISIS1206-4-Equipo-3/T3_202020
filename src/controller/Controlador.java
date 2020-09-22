package controller;

import java.util.Scanner;

import modeloLogico.Modelo;
import view.View;

public class Controlador {
	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	public Controlador()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run()
	{
		Scanner lectura = new Scanner(System.in);
		boolean acabar = false;
		Integer dato = null;
		Comparable rta = " ";
		Comparable respuesta = " ";

		while(!acabar)
		{
			try {
				view.printMenu();
				int opcion = Integer.parseInt(lectura.nextLine());
				switch(opcion) {
				case 1:
					view.printMenuCargaDeDatos();
					int opcionCargaDatos = Integer.parseInt(lectura.nextLine());
					switch(opcionCargaDatos) {
					case 1:
						modelo.cargarDatosTablaHashLinearProbing(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS);
						break;
					case 2:
						modelo.cargarDatosTablaHashSeparateChaining(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS);
						break;
					case 3:
						modelo.cargarDatosTablaHashLinearProbing(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS);
						modelo.cargarDatosTablaHashSeparateChaining(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS);
						break;
					case 4:
						break;
					default:
						view.printErrorConRangoDeEntrada();
						break;
					}
					break;
				case 2:
					view.printMenuPeliculasAnoProduccion();
					int opcionConocerPeliculasAnoProduccion = Integer.parseInt(lectura.nextLine());
					switch(opcionConocerPeliculasAnoProduccion) {
					case 1:
						view.printMessage(" \n Conocer películas según año de producción usando Linear Probing \n Dar año de producción: ");
						int production_year = Integer.parseInt(lectura.nextLine());
						rta = modelo.conocerPeliculasAnoProduccionLinearProbing(production_year);
						if ( rta != null)
						{
							view.printMessage("Peliculas del año de produccion:  "+ production_year+ "\n"+ rta);
						}
						else
						{
							view.printMessage("Ninguna pelicula fue producida en el año asignado");
						}							
						break;
					case 2:
						modelo.conocerPeliculasAnoProduccionSeparateChaining();
						break;
					case 3:
						break;
					default:
						view.printErrorConRangoDeEntrada();
						break;
					}
					break;
				case 3:
					view.printMessage("Requerimiento aun no realizado."); //ELIMINAR AL REALIZAR REQUERIMIENTO
					break;
				case 4:
					view.printMessage("Requerimiento aun no realizado."); //ELIMINAR AL REALIZAR REQUERIMIENTO
					break;
				case 5:
					view.printMessage("Requerimiento aun no realizado."); //ELIMINAR AL REALIZAR REQUERIMIENTO
					break;
				case 6:
					view.printMessage("Requerimiento aun no realizado."); //ELIMINAR AL REALIZAR REQUERIMIENTO
					break;
				case 7:
					view.printInformacionDeCreadores();
					break;
				case 8:
					view.printCambiarDatosACargar();
					int opcion1 = Integer.parseInt(lectura.nextLine());
					switch(opcion1) {
					case 1:
						modelo.RUTA_DATOS_PRINCIPALES = "./data/small/MoviesCastingRaw-small.csv";
						modelo.RUTA_DATOS_SECUNDARIOS = "./data/small/SmallMoviesDetailsCleaned.csv";
						view.printMessage("\nSe han establecido los datos pequeÃ±os para cargar.\n");
						break;
					case 2:
						modelo.RUTA_DATOS_PRINCIPALES = "./data/large/AllMoviesCastingRaw.csv";
						modelo.RUTA_DATOS_SECUNDARIOS = "./data/large/AllMoviesDetailsCleaned.csv";
						view.printMessage("\nSe han establecido los datos grandes para cargar.\n");
						break;
					default:
						view.printErrorConRangoDeEntrada();
						break;
					}
					break;
				case 9:
					acabar=true;
					break;
				default:
					view.printErrorConRangoDeEntrada();
					break;
				}

			}catch (Exception e) {
				if(e.getClass().equals(java.lang.NumberFormatException.class)) view.printErrorConNumeroDeEntrada();
				else { view.printErrorDesconocido();}
			}

		}

	}
}
