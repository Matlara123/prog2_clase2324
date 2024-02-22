// Colecci�n de contactos, asumiendo que no
// se repiten nombres ni n�meros de tel�fono
public class GuiaTelefonos {
	private Contacto[] guia;
	private int numContactos;

	// Constructor
	public GuiaTelefonos(int capacidad) {
		guia = new Contacto[capacidad];
		numContactos = 0;
	}

	// A�adir contacto
	public void poner(Contacto contacto) {
		if (numContactos < guia.length) {     // hay sitio
			guia[numContactos] = contacto;    // ponerlo al final
			numContactos++;
		}
	}

	// Eliminar contacto
	public void quitar(Contacto contacto) {
		int pos = -1;
		for (int k = 0; k < numContactos && pos < 0; k++) {  // buscarlo
			if (guia[k].esIgual(contacto)) pos = k;   // est� ah�
		}
		if (pos >= 0) {                // se ha encontrado
			numContactos--;
			if (pos < numContactos) {  // no era el �ltimo
				guia[pos] = guia[numContactos];  // rellenar el hueco
			}
		}
	}

	// Presentar con formato
	public String toString() {
		String texto = "";
		for (int k = 0; k < numContactos; k++) {  // procesar todos
			texto = texto + guia[k] + "\n";       // un contacto por l�nea
		}
		return texto;
	}

	// B�squedas
	public Contacto buscarNombre(String nombre) {
		Contacto con = null;
		for (int k = 0; k < numContactos && con == null; k++) {
			if (guia[k].igualNombre(nombre)) con = guia[k];
		}
		return con;
	}
	
	public Contacto buscarNumero(long numero) {
		Contacto con = null;
		for (int k = 0; k < numContactos && con == null; k++) {
			if (guia[k].igualNumero(numero)) con = guia[k];
		}
		return con;
	}
	
	// POST: busca un contacto con el nombre dado, y sustituye su n�mero
	// por el n�mero dado como entrada, si existe
	public void reemplazar(String nombre, long numero) {
		//int i;
		//for(i=0; i < numContactos && !guia[i].igualNombre(nombre); i++);
		Contacto contacto = buscarNombre(nombre);
		
		if (contacto != null)
			contacto.setNumero(numero);
	}
	
	// POST: devolver el n�mero de telefonos de Madrid que hay en la gu�a
	// un tel�fono es de Madrid si empieza por "91" o "3491"
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
		// creamos el vector resultado con el tama�o necesario
		
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