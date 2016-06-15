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
	
	public Controller() {
		buffer = new Buffer();
		erzeugung = new Datenerzeugung("weblogs.txt");
		einlesen = new Lesen("weblogs.txt", buffer);
	}
	
	public void starte(int anzahl) {
		try {
			erzeugung.weblogErstellen(anzahl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		einlesen.start();
		String line = null;
		while(einlesen.isAlive()) {
			while(!buffer.istBufferLeer()){
				line = buffer.lese();
				System.out.printf("CONTROLLER: %s\n",line);
			// Hashen
			}
		}
	}
}
