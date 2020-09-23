package modeloEstructuraDatos;

import java.util.Date;

public class Pelicula implements Comparable<Pelicula> {
	
	private String compania;
	
	private Date lanzamiento;
	
	private String titulo;

	private int id;	
	
	private String NombreDirector;
	
	private int vote_count;
	
	private double vote_average;
	
	private String[] actores;

	private String[] genero;
	
	public Pelicula(String pCompania, Date pLanzamiento, String pTitulo, int pId, String director, int numVotos, double promedioVotos,  String actor1, String actor2, String actor3, String actor4, String actor5, String pGenero )
	{
		compania = pCompania;
		lanzamiento = pLanzamiento;
		titulo = pTitulo;
		id = pId;
		NombreDirector = director;
		vote_count = numVotos;
		vote_average = promedioVotos;
		actores = new String[5];
		if(!actor1.equals("none"))
			actores[0] = actor1;
		if(!actor2.equals("none"))
			actores[1] = actor2;
		if(!actor3.equals("none"))
			actores[2] = actor3;
		if(!actor4.equals("none"))
			actores[3] = actor4;
		if(!actor5.equals("none"))
			actores[4] = actor5;
		genero = pGenero.split("\\|");
	}
	
	
	
	
	
	
	@Override
	public int compareTo(Pelicula o) {
		
		if(vote_average < o.getVote_average())
		return -1;
		else if(vote_average > o.getVote_average())
			return 1;
		else
			return 0;
	}






	public Date getLanzamiento() {
		return lanzamiento;
	}






	public String getCompania() {
		return compania;
	}






	public void setCompania(String compania) {
		this.compania = compania;
	}






	public void setLanzamiento(Date lanzamiento) {
		this.lanzamiento = lanzamiento;
		
	}






	public String getTitulo() {
		return titulo;
	}






	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getNombreDirector() {
		return NombreDirector;
	}






	public void setNombreDirector(String nombreDirector) {
		NombreDirector = nombreDirector;
	}






	public int getVote_count() {
		return vote_count;
	}






	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}






	public double getVote_average() {
		return vote_average;
	}






	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}






	public String[] getActores() {
		return actores;
	}






	public void setActores(String[] actores) {
		this.actores = actores;
	}






	public String[] getGenero() {
		return genero;
	}






	public void setGenero(String[] genero) {
		this.genero = genero;
	}
	
	public void imprimirPelicula() {
		System.out.println("ID # "+id);
		System.out.println("Promedio de votos: "+ vote_average);
		System.out.println("Titulo: "+titulo);
		String generoEnString = "";
		for (String gen : genero) {
			if(gen!=null) generoEnString+=gen + ", ";
		}
		System.out.println("Genero(s): "+ generoEnString.substring(0, (generoEnString.length()-2)) );
		System.out.println("Lanzamiento: " + lanzamiento);
		String actoresEnString = "";
		for (String actor : actores) {
			if(actor!=null) actoresEnString+=actor + ", ";
		}
		if(!actoresEnString.isEmpty()) 
		{
			System.out.println("Actor(es): "+ actoresEnString.substring(0,(actoresEnString.length()-2)));
		}
		else
			System.out.println("Actor(es): ninguno");
		System.out.println("Director: "+NombreDirector);
		System.out.println("Numero de votos: "+ vote_count + "\n");
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
