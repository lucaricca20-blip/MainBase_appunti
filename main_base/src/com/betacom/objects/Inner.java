package com.betacom.objects;

public class Inner {
	
	private String fatherClass;
	private int numero;
	private Figlio f; //Vedi giù, Figlio è una classe contenuta dentro questa classe
	
	/*
	 * Inner Class è una classe dentro un'altra classe.
	 */
	
	
	
	public class Figlio { //Classe Figlio la creo dentro Inner con public class (è come se estendo Inner)
		private int numero2;
		private String figlioClass;
		private Nipote n;
		
		public class Nipote { //Nipote è dentro Figlio (è come se estendo Figlio)
			private int numero3;
			private String nipoteClass;
			
			public int getNumero3() {
				return numero3;
			}
			public void setNumero3(int numero3) {
				this.numero3 = numero3;
			}
			public String getNipoteClass() {
				return nipoteClass;
			}
			public void setNipoteClass(String nipoteClass) {
				this.nipoteClass = nipoteClass;
			}
			
			public String displayNumberNipote () {
				return ">> padre: " + numero + " figlio: " +numero2 + " nipote: " + numero3;
			}
				
			
		} //finisce Nipote
		
		
		
		public int getNumero2() {
			return numero2;
		}
		public void setNumero2(int numero2) { //Figlio ha i suoi getter e setter
			this.numero2 = numero2;
		}
		public String getFiglioClass() {
			return figlioClass;
		}
		public void setFiglioClass(String figlioClass) {
			this.figlioClass = figlioClass;
		}
		
	    /*
	     * Nel Figlio ho visibilità degli attributi privati del padre, come posso vedere
	     * nel metodo seguente (in questo momento scrivo dentro classe Figlio, eppure
	     * posso usare l'attributo private numero del padre).
	     */
		
		public Nipote setInstanceNipote() {
			n = new Nipote();
			return n;
		}
		
		public String displayNumber() {
			return ">> padre: " + numero + " figlio: " +numero2;
		}
		
	}//finisce Figlio
	
	
	
	/*
	 * DI seguito i getter e setter di fatherClass
	 */

	public String getFatherClass() {
		return fatherClass;
	}

	public void setFatherClass(String fatherClass) {
		this.fatherClass = fatherClass;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/*
	 * Di seguito un metodo utile a creare un'istanza di tipo Figlio (non farti
	 * confondere dal set davanti)
	 */
	
	public Figlio setInstanceFiglio() {
		f = new Figlio();
		return f;
	}

}//finisce Inner
