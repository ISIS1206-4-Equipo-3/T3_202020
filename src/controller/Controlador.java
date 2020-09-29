package controller;

import java.util.List;
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
		String rtaPeli = " ";

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
						modelo.cargarDatosTablaHashLinearProbing(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS, 0, true);
						break;
					case 2:
						modelo.cargarDatosTablaHashSeparateChaining(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS, 2, false, true);
						break;
					case 3:
						modelo.cargarDatosTablaHashLinearProbing(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS, 0, true);
						modelo.cargarDatosTablaHashSeparateChaining(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS, 2, false, true);
						break;
					case 4:
						break;
					default:
						view.printErrorConRangoDeEntrada();
						break;
					}
					break;
				case 2:
					view.printMessage(" \n Conocer peliculas de una compania \n Dar nombre de la compania de produccion: ");
					String companyName = lectura.nextLine();
					modelo.conocerProductoras( companyName);
					break;
				case 3:
					view.printMessage("Requerimiento aun no realizado."); //ELIMINAR AL REALIZAR REQUERIMIENTO
					break;
				case 4:
					view.printMessage("Escriba el nombre del actor que desea entender");
					String actorABuscar = lectura.nextLine();
					modelo.conocerActorReq3(actorABuscar);
					break;
				case 5:
					view.printMessage("Escriba el nombre del genero que desea entender");
					String genero = lectura.nextLine();
					modelo.entenderGenero(genero);
					break;
				case 6:
					view.printMessage("Escriba el nombre del pais que desea entender");
					String paisABuscar = lectura.nextLine();
					modelo.conocerPeliculasDelPaisReq5(paisABuscar);
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
						view.printMessage("\nSe han establecido los datos pequeños para cargar.\n");
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
				case 10:
					try {
						modelo.pruebasDeDesempeno();
					}
					catch (Exception e)
					{
						view.printMessage("No se han cargado ambas tablas, por favor cargarlas antes de las pruebas de  desempeno. \n ");
					}
					break;
				default:
					view.printErrorConRangoDeEntrada();
					break;
				}

			}catch (Exception e) {
				if(e.getClass().equals(java.lang.NumberFormatException.class)) view.printErrorConNumeroDeEntrada();
				else {
					view.printErrorDesconocido();
					e.printStackTrace();

				}

			}

		}

	}
}
