package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.List;

public class ListaEncadenada <T extends Comparable<T>> implements ILista<T> {

	private Nodo<T> primerElemento;

	public ListaEncadenada(){
		primerElemento = null;
	}

	public ListaEncadenada(T pPrimerElemento) {
		Nodo<T> nuevo = new Nodo<T> (pPrimerElemento);
		primerElemento = nuevo;
	}

	@Override
	public void addFirst(T element) {
		
		if(primerElemento == null) primerElemento = new Nodo<T>(element);
		else {
			Nodo<T> nuevoPrimero = new Nodo<T> (element);
			nuevoPrimero.setSiguiente(primerElemento);
			primerElemento=nuevoPrimero;
		}
	}

	@Override
	public void addLast(T element) {
		
		if(primerElemento !=null) {
			Nodo<T> act = primerElemento;
			while (act.tieneSiguiente()) {
				act = act.darSiguiente();
			}
			act.setSiguiente(new Nodo<T>(element));
		}else {
			primerElemento = new Nodo<T>(element);
		}
	}

	@Override
	public void insertElement(T element, int pos) {
		Nodo <T> nuevoElemento = new Nodo<T> (element);
		Nodo <T> act = primerElemento;
		for(int i=1; i<pos && act!=null;i++) {
			act = act.darSiguiente();
		}
		if(act!=null) {
			if(act.tieneSiguiente()) nuevoElemento.setSiguiente(act.darSiguiente());
			act.setSiguiente(nuevoElemento);
		}
		else {
			System.out.println("No es posible insertar elementos, en la posicion " + pos + " porque no hay tantas posiciones.");
		}
	}

	@Override
	public T removeFirst() {
		
		if (primerElemento!=null) {
			Nodo <T> elementoEliminado = primerElemento;
			primerElemento = primerElemento.darSiguiente();
			return elementoEliminado.darDato();
		}else {
			return null;
		}
	}

	@Override
	public T removeLast() {
		if (primerElemento!=null) {
			Nodo <T> act = primerElemento;
			if(act.tieneSiguiente()) {
				while(act.darSiguiente().tieneSiguiente()) {act = act.darSiguiente();}
				Nodo<T> elementoEliminado = act.darSiguiente();
				act.setSiguiente(null);
				return elementoEliminado.darDato();	
			}else {
				primerElemento = null;
				return act.darDato();
			}
		}else {return null;}
		
	}

	@Override
	public T deleteElement(int pos) {
		try {
			Nodo <T> elementoAEliminar;
			Nodo <T> act = primerElemento;
			if(pos==1) {
				elementoAEliminar = primerElemento;
				if(primerElemento.tieneSiguiente())primerElemento = primerElemento.darSiguiente();
				return elementoAEliminar.darDato();
			}else {
				for(int i=2; i<pos && act.tieneSiguiente();i++) {
					act = act.darSiguiente();
				}
				if(act.tieneSiguiente()) {
					elementoAEliminar = act.darSiguiente();
					act.setSiguiente(elementoAEliminar.darSiguiente());
					return elementoAEliminar.darDato();
				}else {
					System.out.println("No es posible eliminar el elemento en posicion " + pos +  " porque no hay tantas posiciones.");
					return null;
				}
			}
		} catch (Exception e) {
			System.out.println("Ha surgido un error eliminando los datos");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T firstElement() {
		
		if (primerElemento!=null) return primerElemento.darDato();
		else {return null;}
	}

	@Override
	public T lastElement() {
		if (primerElemento != null) {
			Nodo<T> act = primerElemento;
			while(act.tieneSiguiente()) act = act.darSiguiente();
			return act.darDato();
		}else {return null;}
		
	}

	@Override
	public T getElement(int pos) {
		
		Nodo <T> act = primerElemento;
		for(int i=1; i<pos && act!=null;i++) {
			act = act.darSiguiente();
		}
		if(act==null) return null;
		else {
			return act.darDato();
		}
	}
	
	@Override
	public int size() {
		int rta = 0;
		Nodo<T> act = primerElemento;
		while(act!=null) {
			act = act.darSiguiente();
			++rta;
		}
		return rta;
	}

	@Override
	public boolean isEmpty() {
		if(primerElemento == null) return true;
		else {return false;}
		
		
	}

	@Override
	public int isPresent(T element) {
		int rta = -1;
		int contadorPosiciones = 1;
		Nodo<T> act = primerElemento;
		while(act!=null) {
			if(act.darDato().equals(element)) {
				rta = contadorPosiciones;
				break;
			}
			contadorPosiciones++;
			act = act.darSiguiente();
		}
		return rta;
	}

	@Override
	public void exchange(int pos1, int pos2) {
		
		Nodo <T> nodo1 = darNodo(pos1);
		Nodo <T> nodo2 = darNodo(pos2);
		T datoNodo1 = nodo1.darDato();
		nodo1.cambiarDato(nodo2.darDato());
		nodo2.cambiarDato(datoNodo1);
	}

	@Override
	public void changeInfo(int pos, T elem) {
		darNodo(pos).cambiarDato(elem);
	}
	
	private Nodo<T> darNodo(int pos) {
		
		Nodo <T> act = primerElemento;
		for(int i=1; i<pos && act!=null;i++) {
			act = act.darSiguiente();
		}
		if(act==null) return null;
		else {
			return act;
		}
	}
	
	public T removeElement (T elem) {
		T datoEliminado;
		if(primerElemento.darDato().equals(elem)) {
			datoEliminado = primerElemento.darDato();
			primerElemento = primerElemento.darSiguiente();
			return datoEliminado;
		}else {
			Nodo<T> act = primerElemento;
			while(act.darSiguiente()!=null) {
				if(act.darSiguiente().darDato().equals(elem)) {
					datoEliminado = act.darSiguiente().darDato();
					act.setSiguiente(act.darSiguiente().darSiguiente());
					return datoEliminado;
				}
				act= act.darSiguiente();
			}
		}
		return null;
	}
	
	public List<T> retornarLista() {
		List<T> lista = new ArrayList<T>();
		if(!isEmpty()) {
			Nodo<T> act = primerElemento;
			while (act!=null) {
				lista.add(act.darDato());
				act = act.darSiguiente();
			}
			return lista;
		}else {
			return null;
		}
		
	}

}
