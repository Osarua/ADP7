package geteilt;

import java.io.IOException;

import einlesen.Lesen;
import erzeugung.Datenerzeugung;

/**
 * TI3 ADP, SS16 
 * Gruppe: Julian Magierski (julian.magierski@haw-hamburg.de)
 * Kristian Exﬂ (kristian.exss@haw-hamburg) 
 * Aufgabenblatt 7: Hashfunktionen
 * Die Klasse kann Zeilen aus einer Textdatei lesen und diese in einen
 * Buffer schreiben.
 */
public class Controller {

	private Buffer buffer;
	
	private Datenerzeugung erzeugung;
	
	private Lesen einlesen;
	
	private Hashtabelle hashtabelle;
	
	public Controller(Hashtabelle hashtabellePar) {
		buffer = new Buffer();
		erzeugung = new Datenerzeugung("weblogs.txt");
		einlesen = new Lesen("weblogs.txt", buffer);
		hashtabelle = hashtabellePar;
	}
	
	public void starte(int anzahl) {
		try {
			erzeugung.weblogErstellen(anzahl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		einlesen.start();
		String line = null;
		String[] log;
		while(einlesen.isAlive()) {
			while(!buffer.istBufferLeer()){
				line = buffer.lese();
			log = line.split(";");
			hashtabelle.einfuegenHashtabelle(log[0], log[1]);
			}
		}
	}
}
