
public class GuiaTelefonosOrd {
	private Contacto[] guia;
	private int numContactos;

	// Constructor
	public GuiaTelefonosOrd(int capacidad) {
		guia = new Contacto[capacidad];
		numContactos = 0;
	}

	// Añadir contacto
	public void poner(Contacto contacto) {
		if (numContactos < guia.length) {     // hay sitio
			int i;
			for(i = numContactos - 1; i >= 0 && 
					guia[i].getNombre().compareTo(contacto.getNombre()) > 0; 
					i--)
				guia[i + 1] = guia[i];
			
			guia[i+1] = contacto;
			numContactos++;			
		}
	}

	// Eliminar contacto
	public void quitar(Contacto contacto) {
		int i;
		for (i = 0; i < numContactos && !(guia[i].getNombre().equals(contacto.getNombre())); i++){}
		if (i < numContactos) {
			for (int j = i; j < numContactos - 1; j++) {
				guia[j] = guia[j+1];
			}
		}
	}

	// Presentar con formato
	public String toString() {
		String texto = "";
		for (int k = 0; k < numContactos; k++) {  // procesar todos
			texto = texto + guia[k] + "\n";       // un contacto por línea
		}
		return texto;
	}

	// Búsquedas
	public Contacto buscarNombre(String nombre) {
		int i;
		for (i = 0; i < numContactos && !(guia[i].getNombre().equals(nombre)); i++){}
		if (i < numContactos)
			return guia[i];
		return null;
	}
	
	public Contacto buscarNumero(long numero) {
		Contacto con = null;
		for (int k = 0; k < numContactos && con == null; k++) {
			if (guia[k].igualNumero(numero)) con = guia[k];
		}
		return con;
	}
	
	// POST: busca un contacto con el nombre dado, y sustituye su número
	// por el número dado como entrada, si existe
	public void reemplazar(String nombre, long numero) {
		//int i;
		//for(i=0; i < numContactos && !guia[i].igualNombre(nombre); i++);
		Contacto contacto = buscarNombre(nombre);
		
		if (contacto != null)
			contacto.setNumero(numero);
	}
	
	// POST: devolver el número de telefonos de Madrid que hay en la guía
	// un teléfono es de Madrid si empieza por "91" o "3491"
	public int contarTelefonosMadrid() {
		int contador = 0;
		for (int i = 0; i < numContactos; i++) {
			if (guia[i].contieneNumeroMadrid()) {
				contador++;
			}
		}
		return contador;
	}
	
	public Contacto[] getTelefonosMadrid() {
		// creamos el vector resultado con el tamaño necesario
		
		Contacto[] resultado = new Contacto[contarTelefonosMadrid()];
		
		int pos = 0;
		
		for (int i =0; pos<resultado.length ; i++) {
			
			if (guia[i].contieneNumeroMadrid()) {
				
				resultado [pos] = guia [i];
				pos ++;
				
			}		
		}
		
		//recorremos la guia y metemos cada contacto de Madrid en resultado		
		return resultado;
	}
	
	

}
