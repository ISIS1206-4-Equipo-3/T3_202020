package view;

import modeloLogico.Modelo;

public class View {
	
	public View()
	{
		
	}
	
	public void printMenu()
	{
		System.out.println("1. Realizar la carga de las fuentes de datos de las peliÌ�culas.");
		System.out.println("2. Descubrir productoras de cine (REQ1)");
		System.out.println("3. Conocer a un director (REQ2)");
		System.out.println("4. Conocer a un actor (REQ3)");
		System.out.println("5. Entender un genero cinematografico (REQ4)");
		System.out.println("6. Encontrar peliculas por pais (REQ5)");
		System.out.println("7. Informacion de creadores.");
		System.out.println("8. Cambiar datos a cargar");
		System.out.println("9. EXIT");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	
	public void printInformacionDeCreadores(){
		System.out.println("\nCreado por:");
		System.out.println("Alejandro Alcaraz 201921767");
		System.out.println("Alejandro Ahogado 201920701");
		System.out.println("Santiago Triana 201923265");
		System.out.println("Universidad de los Andes - Bogota, Colombia");
		System.out.println("Estructuras de Datos 202020_ISIS_1206_04 GRUPO 3\n");
	}
	
	public void printCambiarDatosACargar() {
		System.out.println("\n1. Cargar datos de peliculas pequenos.");
		System.out.println("2. Cargar datos de peliculas grandes.");
	}
	
	public void printErrorConRangoDeEntrada() {
		// TODO Auto-generated method stub
		System.out.println("\n++CAUTION: Ha introducido un numero que no esta dentro del menu. Opcion invalida \n");
	}
	
	public void printErrorConNumeroDeEntrada() {
		// TODO Auto-generated method stub
		System.out.println("\n++CAUTION: Ha introducido un caracter diferente a un numero. Opcion invalida \n");
	}
	public void printErrorDesconocido() {
		// TODO Auto-generated method stub
		System.out.println("\n++CAUTION: Ha sucedido un error desconocido \n");
	}

	public void printDespedida() {
		// TODO Auto-generated method stub
		printInformacionDeCreadores();
		System.out.println("\nGracias por usar nuestra base de datos :)");
	}

	public void printMenuCargaDeDatos() {
		// TODO Auto-generated method stub
		System.out.println("1. Cargar tabla de Hash con manejo de colisiones Linear Probing.");
		System.out.println("2. Cargar tabla de Hash con manejo de colisiones SeparateChaining");
		System.out.println("3. Cargar todas las anteriores");
		System.out.println("4. <--- REGRESAR");
	}

	public void printMenuPeliculasAnoProduccion()
	{
		System.out.println("1.Conocer peliculas que pertenecen a una compania de producción en un año específico utilizando manejo de colisiones por Linear Probing.");
		System.out.println("2. Conocer peliculas que pertenecen a una compania de producción en un año específico utilizando manejo de colisiones por SeparateChaining");
		System.out.println("3. EXIT");
	}
	
	

}
