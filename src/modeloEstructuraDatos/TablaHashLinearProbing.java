package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class TablaHashLinearProbing <K, V> implements ITablaSimbolos <K, V>{
	

	K[] listaLlaves;
	V[] listaValores;
	int M;
	
	public TablaHashLinearProbing (int pCapacidadInicial) {
		M = pCapacidadInicial;
		listaValores = (V[]) new Object [pCapacidadInicial];
		listaLlaves = (K[]) new Object[pCapacidadInicial];
	}
	
	private int hash (K key) {return ((key.hashCode() & 0x7fffffff) % M);}
			
	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		int hashKey = hash(key);
		int cantidadDeDatosPasados = 0;
		boolean datoCargado = false;
		while(!datoCargado && (cantidadDeDatosPasados<M)) {
			if(listaValores[hashKey]!=null) {
				hashKey = (hashKey+1)%M;
				cantidadDeDatosPasados ++;
			}else {
				listaValores[hashKey] = value;
				listaLlaves[hashKey] = key;
				datoCargado = true;
			}
		}
		/* System.out.println(cantidadDeDatosPasados) */
		if (!datoCargado) {
			reHash();
			put(key, value);
		}
	}

	private void reHash() {
		K[] listaAntiguaLlaves = listaLlaves;
		V[] listaAntiguaValores = listaValores;
		int MAntigua = M;
		M = 2*M;
		listaLlaves = (K[]) new Object[M];
		listaValores = (V[]) new Object[M];
		for (int i = 0; i<MAntigua;i++) {
			put(listaAntiguaLlaves[i], listaAntiguaValores[i]);
		}
		System.out.println("Se ha realizado un rehash de la tabla con manejo de colisiones linear probing");
	}

	@Override
	public V get(K key) {
		int hashKey = hash(key);
		int cantidadDeDatosPasados = 0;
		while(cantidadDeDatosPasados<M) {
			if(listaLlaves[hashKey]==null) 
			{
				return null;
			}
			if(listaLlaves[hashKey].equals(key)) {
				return listaValores[hashKey];
			}
			else {
				hashKey = (hashKey+1)%M;
				cantidadDeDatosPasados ++;
			}
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int hashKey = hash(key);
		int cantidadDeDatosPasados = 0;
		while(cantidadDeDatosPasados<M) {
			if(listaLlaves[hashKey].equals(key)) {
				V borrado = listaValores[hashKey];
				listaValores[hashKey] = null;
				listaLlaves[hashKey] = null;
				return borrado;
			}else {
				hashKey = (hashKey+1)%M;
				cantidadDeDatosPasados ++;
			}
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		int hashKey = hash(key);
		int cantidadDeDatosPasados = 0;
		while(cantidadDeDatosPasados<M) {
			if(listaLlaves[hashKey].equals(key)) {
				return true;
			}else {
				hashKey = (hashKey+1)%M;
				cantidadDeDatosPasados ++;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i<M; i++) {
			if(listaValores[i]!=null) return false;
		}
		return true;
	}

	@Override
	public int size() {
		int rta =0;
		for (int i = 0; i<M; i++) {
			if(listaValores[i]!=null || listaLlaves[i]!=null){
				rta++;
				if(!(listaValores[i]!=null && listaLlaves[i]!=null)) System.out.println("CAUTION: Existe una inconsistencia en los datos");
			}
		}
		return rta;
	}

	@Override
	public List<K> keySet() {
		List<K> listaRta  = new ArrayList<K>();
		for (K k : listaLlaves) {
			if(k!=null)listaRta.add(k);
		}
		return listaRta;
	}

	@Override
	public List<V> valueSet() {
		List<V> listaRta  = new ArrayList<V>();
		for (V v : listaValores) {
			if(v!=null)listaRta.add(v);
		}
		return listaRta;
	}
	
	public V darPrimerElemento() {
		for(int i =0 ; i < listaValores.length; i++)
		{
			if(listaValores[i] != null) return listaValores[i];
		}
		return listaValores[1000];
		

	}
	public V darUltimoElemento() {
		for(int i =M-1 ; i >0; i--)
		{
			if(listaValores[i] != null)
				{return listaValores[i];}
		}
		return listaValores[1000];
	}
	public int[] pruebaInexistentes()
	{
		int[] container = new int[200];
		boolean seLLeno = false;
		int j = 0; 
		
		for(int i =0; i<M && j <200; i ++)
		{
			if(listaValores[i]==null)
			{
				container[j] = i;
				j++;
			}
			i++;
		}
		return container;
	
	}
	public String[] pruebaExistentes()
	{
		String[] container = new String[800];
		boolean seLLeno = false;
		int j = 0; 
		
		for(int i =0; i<M && j <800; i ++)
		{
			if(listaValores[i]!=null)
			{
				container[j] = (String)listaLlaves[i];
				j++;
			}
			i++;
		}
		return container;
	}

	public void conocerPeliculasAnoProduccionLinearProbing(K key) {
		Pelicula peli = null;
		if (this.size()!=0) {
			if (this.get(key)==null) 
				System.out.println("No existen peliculas con las caracter�sticas dadas");
			else {
				for (int i = 0; i <listaLlaves.length; i++) {
					if (listaLlaves[i]!=null && listaLlaves[i].equals(key.toString()) ) {
						peli = (Pelicula) listaValores[i];
						peli.imprimirPelicula();

					}
			}

		}
		
		
	}	

	    
}
	

}
