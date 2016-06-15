package einlesen;
import java.io.File;
import java.io.IOException;

import com.sun.glass.ui.Pixels.Format;

import java.io.FileReader;
import java.io.BufferedReader;
import geteilt.Buffer;
/**
 * TI3 ADP, SS16 
 * Gruppe: Julian Magierski (julian.magierski@haw-hamburg.de)
 * Kristian Exﬂ (kristian.exss@haw-hamburg) 
 * Aufgabenblatt 7: Hashfunktionen
 * Die Klasse kann Zeilen aus einer Textdatei lesen und diese in einen
 * Buffer schreiben.
 */
public class Lesen extends Thread {

	/**
	 * Buffer in welchem die Zeilen geschrieben werden sollen.
	 */
	Buffer buffer;
	
	/**
	 * Pfad zum Verzeichnis
	 */
	String verzeichnis;

	public Lesen(String verzeichnisPar, Buffer bufferPar){
		buffer = bufferPar;
		verzeichnis = verzeichnisPar;
	}
	
	public void run() {
		File txtFile = new File(verzeichnis);
		try {
			BufferedReader bufferedReader = new BufferedReader ( new FileReader(txtFile));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				buffer.schreibe(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
