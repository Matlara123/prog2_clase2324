package reproductor;

import reproductor.cancion.Cancion;

public class ReproductorMusica {
	// contiene las canciones que se pueden reproducir
	private Cancion[] listaReproduccion;
	private int noCanciones;
	// indica la pos de la siguiente canci�n a reproducir
	// si la listaReproduccion est� vac�a, punteroReproduccion es -1
	private int punteroReproduccion; 

	public ReproductorMusica(int capacidad){
		listaReproduccion = new Cancion[capacidad];
		noCanciones = 0;
		punteroReproduccion = -1;
	}

	public String toString(){	
		String salida = "";
		for(int i=0; i < noCanciones; i++)
			salida += listaReproduccion[i].toString() + ", ";
		
		salida += "punteroApuntaA=" + 
				listaReproduccion[punteroReproduccion].toString();
		
		return salida;
	}

	/**
	 * POST: Si hay una canci�n 
	 * con el t�tulo y la duraci�n dadas,
	 * devuelve la posici�n de la primera ocurrencia
	 * e.o.c. devuelve -1 
	 *
	 */
	private int buscarCancion(String titulo, double duracion){
		int i;
		Cancion cancion = new Cancion(titulo, duracion);
		for(i=0; i < noCanciones && 
				!listaReproduccion[i].esIgual(cancion); i++);
		
		return i < noCanciones ? i : -1;
	}

	/**
	 * POST: 
	 * Si la canci�n dada est� en la lista, 
	 *   borra la primera ocurrencia de la canci�n.
	 *   Si quedan canciones, deja el puntero apuntando a la primera canci�n.
	 *   Si no quedan canciones, se deja el puntero con valor -1.
	 * e.o.c. no hace nada
	 */
	public void borrarCancion(String titulo, double duracion){
		int pos = buscarCancion(titulo, duracion);
		if (pos != -1) {
			for(int i=pos; i<noCanciones-1; i++)
				listaReproduccion[i] = listaReproduccion[i+1];
			noCanciones--;
			/*
			 * if (noCanciones == 0) punteroReproduccion = -1; 
			 * else punteroReproduccion = 0;
			 */
			punteroReproduccion = noCanciones == 0 ? -1:0;
		}
	}

	/**
	 * PRE: Hay suficiente espacio libre para las nuevas canciones
	 * POST: Inserta las canciones en la lista (al final)	
	 * Deja el puntero apuntando a la primera canci�n insertada
	 * Utiliza las canciones del vector dado y no crea copias
	 * Si canciones.length = 0, no hace nada.
	 */
	public void insertarCanciones(Cancion[] canciones){
		if (canciones.length > 0) {
			punteroReproduccion = noCanciones;
			for(int i=0; i<canciones.length; i++, noCanciones++)
				listaReproduccion[noCanciones] = canciones[i];
			/*
			 * for(Cancion cancion: canciones) { 
			 *   listaReproduccion[noCanciones] =
			 *   canciones[i]; noCanciones++; 
			 * }
			 */		
		}		
	}

	/**	
	 * POST: Si la canci�n existe, pone el puntero en la primera ocurrencia 
	 * de la canci�n con el t�tulo y la duraci�n dados
	 * e.o.c. deja el puntero igual
	 */
	public void seleccionarCancion(String titulo, double duracion){
		int pos = buscarCancion(titulo, duracion);
		if (pos != -1)
			punteroReproduccion = pos;
	}

	/**
	 * PRE: la lista no est� vac�a
	 * POST: reproduce la canci�n a la que apunta el puntero
	 */
	public void reproducirCancionSeleccionada(){
		listaReproduccion[punteroReproduccion].reproducirCancion(); 
	}

	/**
	 * PRE: la lista no est� vac�a y 
	 * el puntero no est� en la �ltima canci�n
	 * POST: avanza el puntero a la siguiente canci�n de la lista
	 */
	public void avanzarPuntero(){
		punteroReproduccion++;
	}

	/**
	 * POST: Indica si el puntero est� en la �ltima canci�n de la lista 
	 */
	public boolean haySiguiente(){
		return punteroReproduccion != noCanciones - 1;
	}

	/** 
	 * POST: Devuelve un vector con copias de las canciones que
	 * se han escuchado igual o m�s de un n�mero dado de veces.	
	 * Si no hay ninguna, se devuelve un vector de tama�o cero. 
	 */
	public Cancion[] seleccionarCancionesMasEscuchadas(int veces){
		int numeroCancionesMasEscuchadas = 0;
		for(Cancion cancion: listaReproduccion) {
			if(cancion.getNoReproducciones() >= veces)
				numeroCancionesMasEscuchadas++;
		}
		Cancion[] cancionesMasEscuchadas = new Cancion[numeroCancionesMasEscuchadas];
		int pos = 0;
		for(int i=0; pos < cancionesMasEscuchadas.length; i++) {
			if(listaReproduccion[i].getNoReproducciones() >= veces) {
				cancionesMasEscuchadas[pos] = new Cancion(listaReproduccion[i]);
				pos++;
			}
		}
		return cancionesMasEscuchadas;
	}

	
}
