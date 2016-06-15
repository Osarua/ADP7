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
	
	public Controller() {
		buffer = new Buffer();
	}
	
	public void starte(int anzahl) {
		Datenerzeugung erzeugung = new Datenerzeugung("weblogs");
		Lesen einlesen = new Lesen("weblogs", buffer);
		try {
			erzeugung.weblogErstellen(anzahl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
