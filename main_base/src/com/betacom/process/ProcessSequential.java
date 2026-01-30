package com.betacom.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ProcessInterface;

public class ProcessSequential implements ProcessInterface{

    @Override
    public boolean execute() throws Exception {
        System.out.println("Begin ProcessSequential");

        String filePath = "/Users/Betacom/Desktop/Academy LR/provaSequential/fileToRead.txt"; //file che dobbiamo leggere
        String filePathOut = "/Users/Betacom/Desktop/Academy LR/provaSequential/fileToWrite.txt"; //file che creiamo col metodo writeFile

        /*
         * Nella prossima riga usiamo il readFile (vedi sotto come abbiamo
         * scritto questo metodo) e gli diamo in ingresso il percorso del txt
         * (che è nella string filePath). Tutte le righe del txt ottenute col
         * metodo readFile ( che sfrutta readLine ecc) vengono salvate nella
         * lista di stringhe records.
         */
        List<String> records = readFile(filePath);

        for (String line:records) { //legge la lista che contiene tutte le strighe (che erano righe del txt) e le stampa
            System.out.println(line);
        }
        
        /*
         * Adesso creo una lista (recWrite) in cui vado a mettere tutte le 
         * stringhe che voglio inserire in un nuovo file txt, usando il 
         * secondo metodo (writeFile, guarda in basso). Aggiungo tutte le
         * stringhe che voglio con add.
         */
        List <String> recWrite = new ArrayList<String>();
        recWrite.add("write 1");
        recWrite.add("write 2");
        recWrite.add("write 3");
        recWrite.add("write 4");
        recWrite.add("write 5");
        recWrite.add("write 6");
        recWrite.add("write 7");
        recWrite.add("write 8");
        recWrite.add("write 9");
        recWrite.add("write 10");
        recWrite.add("write 11");
        recWrite.add("write 12");
        
        /*
         * Nel successivo Syso voglio riportare quante righe sono state fatte
         * nel nuovo file. writeFile crea il file, ma in video nella console
         * vedrò i record scritti. writeFile prende in ingresso una stringa, che è il
         * percorso in cui voglio che venga creato il nuovo file, e poi una
         * lista, che contiene le righe che voglio aggiungere nel nuovo file.
         * Lascia stare il terzo parametro in ingresso, il boolean, che serve invece
         * per il terzo metodo (puoi vederlo in basso), che è simile al secondo.
         */
        System.out.println("numero di records scritti: " + writeFile(filePathOut, recWrite, true)); //Il boolean finale serve per il terzo metodo
        //Se vado nel txt vedo i vari write creati
        return false;
    }

    /*
     * Vediamo come leggere un file, usando il metodo readFile (leggerà i record
     * del txt contenuto nel percorso indicato in path
     */
    private List<String> readFile(String path) {
        List<String> r = new ArrayList();
        
        /*
         * Il BufferedReader ha bisogno intanto dell'azione di FileReader.
         * Quindi intanto FileReader legge il percorso (contenuto in path) del
         * file txt. Interviene solo dopo BufferedReader che accumula i dati
         * e ci permette di usare il metodo readLine (che è un metodo di 
         * BufferedReader, nativo), il quale legge un'intera riga di testo nel
         * txt, si ferma quando incontra un "a capo" e infine restituisce una
         * string con ciò che ha letto. Col while gli diciamo proprio di 
         * continuare a leggere il file finché il file non è finito, e aggiunge
         * le varie righe (line) del txt in una Lista di stringhe "r"(v.sopra).
         * Alla fine del file readLine() ritorna un null, e lì si ferma (perché la 
         * condizione del while diventa falsa), dato che è "line diverso da
         * null".
         */

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                r.add(line);
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }
    
    /*
     * Stavolta vogliamo invece creare il file. Il metodo writeFile prende in
     * ingresso una stringa e una lista.
     * Il tipo File crea appunto
     * un nuovo file, che chiamiamo f e che vuole tra parentesi una stringa,
     * che è quella che prende in ingresso il metodo writeFile (e sarà
     * filePathOut, che è il percorso dove vogliamo mettere il nuovo file
     * che creiamo).
     */
    private int writeFile(String path, List<String> inp) {
    	int num = 0; //alla fine farà return di questo num che è solo un contatore
    	//che ci dice quante righe verranno scritte nel nuovo file.
    	//Infatti nel for più in basso metterò num++.
    	File f = new File(path); //devo fare import di File
    	if (f.exists()) { //il file f esiste? Se sì, prosegui e stampa percorso
    		System.out.println("File " +path + "exists");
    		f.delete();//Se esiste già, lo cancello prima di crearne uno nuovo (per non sovrascrivere)		
    	}
    	
    	/*
    	 * Adesso creo un file di tipo FileWriter (è una classe nativa) vuoto.
    	 * Poi all'interno di "o" (ricorda, se non metto il new, a sinistra
    	 * indico solo un indirizzo che può contenere un oggetto). Riempio "o"
    	 * con un nuovo oggetto di tipo FileWriter, che nel suo costruttore
    	 * vuole un file, e metto f.
    	 * Adesso col for gli dico di leggere tutte le stringhe contenute
    	 * nella lista inp (che il metodo ha preso in ingresso); nel nostro
    	 * caso la lista è rec (vedi in alto) e gli dico che con tutte le
    	 * stringhe contenute nella lista (chiama rec pure le stringhe che 
    	 * il metodo deve leggere) deve usare o.write, cioè deve scriverle
    	 * nel file f con il FileWriter o.
    	 */
    	FileWriter o = null; //importo FileWriter
    	try {
    		o = new FileWriter(f);
    		for (String rec:inp) {
    			o.write(rec + "\n");
    			o.write("\n"); //Stessa cosa del precedente
    			num++;
    		}
    	//	o.close(); vedi come l'ha messo giù
    	} catch (IOException e) { //all'inizio dà errore perché non ci sono in quel momento eccezioni di quel tipo
    		e.printStackTrace();
    	} finally { //Il finally nel try/catch viene eseguito sia che il try sia andato bene o meno
    		try {//se va in tilt mentre scriviamo libera la memoria comunque con o.close, che chiude in FileWriter
    		o.close(); //serve per chiudere il file e libera la memoria associata
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	return num; //era il contatore, mi serve solo per sapere quante righe
    	//ho scritto nel nuovo file. Infatti poi scriverò:
    	//"Numero di record scritti: _____". Nella firma del metodo infatti
    	//ha scritto che il metodo in ingresso restituisce un int.
    }
    /*
     * 
     *mode: true --> :extend file
     *    : false --> replace
     *    Terzo metodo.
     *    Voglio che si eviti il fatto di dover eliminare il file precedente
     *    come ho fatto sopra con IF nel primo metodo. Infatti è simile al
     *    primo metodo.
     *     
     * 
     */
    private int writeFile (String path, List<String> inp, boolean mode) {
    	int num = 0;
    	BufferedWriter o = null;
    	
    	try {
    		o = new BufferedWriter (new FileWriter(path, mode));  //stavolta metto pure mode. Ho incapsulato BufferedWriter dentro FileWriter
    		for (String rec:inp) {
    			o.write(rec + "\n"); //Stessa cosa del metodo precedente
    			o.newLine(); // permette di andare a capo su tutti i sistemi (su Windows basta \n)
    			num++;
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally { //Il finally nel try catch viene eseguito sia che il try sia andato bene o meno
    		try {
    			o.flush(); //Questa e la successiva sono best pratices perché evitano sovraccarico memoria
    			o.close();
    		} catch (IOException e){
    			e.printStackTrace();
    		}

    	}
    	
    	return num;
    }

}