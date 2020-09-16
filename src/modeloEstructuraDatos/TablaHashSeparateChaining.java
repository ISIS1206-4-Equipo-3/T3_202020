package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

public class TablaHashSeparateChaining<K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolos <K, V>{

	private Hashtable<Integer,ListaEncadenada<V>> tabla;
	private ListaEncadenada<K> listaConLlaves;
	private int M;
	
	/**
	 * Crea una tabla de has con manejo de colisiones separate Chaining
	 * @param pCapacidad Hace referencia a la cantidad de filas que va a tener la tabla.
	 */
	public TablaHashSeparateChaining(int pCapacidad) {
		listaConLlaves = new ListaEncadenada<K>();
		tabla=new Hashtable<Integer,ListaEncadenada<V>>(pCapacidad);
		M = pCapacidad;
	}
	
	private int hash (K key) {return ((key.hashCode() & 0x7fffffff) % M);}
	
	@Override
	public void put(K key, V value) {
		listaConLlaves.addFirst(key);
		// TODO Auto-generated method stub
		Integer hashKey = hash(key);
		if(getLista(hashKey)==null) {
			ListaEncadenada<V> nuevo = new ListaEncadenada<V>(value);
			tabla.put(hashKey, nuevo);
		}
		else {getLista(hashKey).addFirst(value);}
	}
	
	private ListaEncadenada<V> getLista(K key){return tabla.get(hash(key));}
	private ListaEncadenada<V>  getLista(Integer hashKey){return tabla.get(hashKey);}

	/**
	 * Este metodo solamente va a retornar el primer elemento asociado a esa llave, (Ultimo ananido).
	 */
	@Override
	public V get(K key) {
		if(getLista(key)!=null) return (V) getLista(key).firstElement();
		else {return null;}
	}
	
	public ListaEncadenada<V> getListaValues(K key) {
		return getLista(key);
	}

	/**
	 * Este metodo va a eliminar el ultimo valor agregado asociado a esa llave
	 * Se va a retornar solamente el ultimo elemento agregado
	 */
	@Override

	public V remove(K key) {
		listaConLlaves.removeElement(key);
		Integer hashKey = hash(key);
		V value = (V) tabla.get(hashKey).removeFirst();
		if(tabla.get(hashKey).isEmpty()) tabla.remove(hashKey);
		return value;
	}
	
	public V remove(K key, V value) {
		ListaEncadenada<V> lista = tabla.get(key);
		if(lista==null) return null;
		listaConLlaves.removeElement(key);
		return lista.removeElement(value);
	}

	@Override
	public boolean contains(K key) {
		if(tabla.get(hash(key))==null) return false;
		else {return true;}
	}

	@Override
	public boolean isEmpty() {
		return tabla.isEmpty();
	}

	@Override
	public int size() {
		int rta = 0;
		for (int i = 0 ; i<M ; i++) {
			if(tabla.get(i)!=null) rta += tabla.get(i).size();
		}
		return rta;
	}

	@Override
	public List<K> keySet() {
		return listaConLlaves.retornarLista();
	}

	@Override
	public List<V> valueSet() {
		Collection<ListaEncadenada<V>> colleccionConValores = tabla.values();
		if(colleccionConValores==null) return null;
		List <V> listaValores = new ArrayList<V>();
		for (ListaEncadenada<V> listaActual : colleccionConValores) {
			if(listaActual!=null) {
				listaValores.addAll(listaActual.retornarLista());
			}
		}
		return listaValores;
		
	}
	
}
