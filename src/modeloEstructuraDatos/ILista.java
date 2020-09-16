package modeloEstructuraDatos;

public interface ILista <T extends Comparable<T>> {
	
	/**
	 * Agrega un elemento al inicio de la lista
	 */
	public void addFirst(T element);
	
	
	/**
	 * Agrega un elemento al final de la lista
	 */
	public void addLast(T element);
	
	
	/**
	 * Agrega un elemento en la posicion pos si la posicion es 
	 * una posicion valida. Los elementos que estan a partir de la 
	 * posicion dada deben correrse una posicion a la derecha. 
	 * Las posiciones validas son posiciones donde ya hay un elemento en 
	 * la lista. La posicion del primer elemento es 1, la del segundo es 2 y 
	 * asi sucesivamente.
	 */
	public void insertElement(T element, int pos);
	
	
	/**
	 * Elimina el primer elemento. Se retorna el elemento eliminado.
	 */
	public T removeFirst( );
	
	
	/**
	 * Elimina el último elemento. Se retorna el elemento eliminado.
	 */
	public T removeLast( );
	
	
	/**
	 * Elimina el elemento de una posición válida. Se retorna el elemento eliminado.
	 */
	public T deleteElement( int pos);
	
	
	/**
	 * Retorna el primer elemento
	 */
	public T firstElement( );
	
	
	/**
	 * Retorna el ultimo elemento
	 */
	public T lastElement();
	
	
	/**
	 * Retorna el elemento en una posición válida. La posición del primer elemento es 
	 * 1, la del segundo es 2 y así sucesivamente.
	 */
	public T getElement( int pos);
	
	
	/**
	 * Retorna el número de datos en el arreglo
	 */
	public int size( );

	
	/**
	 * Retorna true si el arreglo No tiene datos. false en caso contrario.
	 */
	public boolean isEmpty( );

	
	/**
	 * Retorna la posición válida de un elemento. 
	 * Si no se encuentra el elemento, el valor retornado es -1.
	 */
	public int isPresent (T element);

	
	/**
	 * Intercambia la información de los elementos en dos posiciones válidas.
	 */
	public void exchange (int pos1, int pos2);

	
	/**
	 * Actualiza el elemento en una posición válida.
	 */
	public void changeInfo (int pos, T elem);
	
}
