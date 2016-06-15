package geteilt;
/**
 * TI3 ADP, SS16 
 * Gruppe: Julian Magierski (julian.magierski@haw-hamburg.de)
 * Kristian Exß (kristian.exss@haw-hamburg) 
 * Aufgabenblatt 7: Hashfunktionen
 */

public class Buffer {

	private String buffer[];
	
	private int leseKopf;
	
	private int schreibKopf;
	
	private final static int BUFFERSIZE = 10; 
	
	private int freiePlaetze;
	
	public Buffer() {
		buffer = new String[BUFFERSIZE];
		leseKopf = -1;
		schreibKopf = 0;
		freiePlaetze = BUFFERSIZE;
	}
	
	/**
	 * Die Methode liest einen String aus dem Buffer. 
	 * @return String eines Web-Log Eintrag 
	 */
	public synchronized String lese() {
		while(istBufferLeer()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(leseKopf >= BUFFERSIZE-1) {
			leseKopf = -1;
		}
		leseKopf = leseKopf + 1;
		freiePlaetze = freiePlaetze + 1;
		notifyAll();
		return buffer[leseKopf];
	}
	
	/**
	 * Schreibt einen String in den Buffer.
	 * @param String der in den Buffer geschrieben werden soll.
	 */
	public synchronized void schreibe(String eintrag) {
		while(freiePlaetze == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[schreibKopf] = eintrag;
		schreibKopf++;
		if(schreibKopf >= BUFFERSIZE) {
			schreibKopf = 0;
		}
		freiePlaetze = freiePlaetze - 1;
		notifyAll();
	}

	public boolean istBufferLeer() {
		boolean result = false;
		if (freiePlaetze == BUFFERSIZE) {
			result = true;
		}
		return result;
	}
}
