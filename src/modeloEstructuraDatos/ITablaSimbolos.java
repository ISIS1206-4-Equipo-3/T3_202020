package modeloEstructuraDatos;

import java.util.List;

public interface ITablaSimbolos <K extends Comparable<K>,V>{

//	Agregar una dupla (K, V) a la tabla. 
//	Si la llave K existe, se reemplaza su valor V asociado. 
//	V no puede ser null. En este caso una llave K solo tiene asociado un valor V.
	public void put(K key, V value);
	
//	Obtener el valor V asociado a la llave K. 
//	Se obtiene null solo si la llave K no existe. 
//	Se usa el comparador sobre las llaves para saber si existe.
	public V get(K key);
	
//	Borrar la dupla asociada a la llave K. 
//	Se obtiene el valor V asociado a la llave K. 
//	Se obtiene null solo si la llave K no existe.
	public V remove(K key);
	
//	Retorna true en el caso que la llave K se encuentre 
//	almacenada en la Tabla, o false en el caso contrario
	public boolean contains (K key);
	
//	Retorna true si la Tabla NO tiene datos, o false en caso contrario.
	public boolean isEmpty();
	
//	Retorna el número de duplas en la Tabla de Símbolos
	public int size();
	
//	Retorna todas las llaves almacenadas en la Tabla.
	public List<K> keySet();
	
//	Retorna todos los valores almacenados en la Tabla.
	public List<V> valueSet();
	
}
