package einlesen;

import geteilt.Buffer;

public class Lesen {

	Buffer buffer;
	
	String verzeichnis;

	public Lesen(String verzeichnisPar, Buffer bufferPar){
		buffer = bufferPar;
		verzeichnis = verzeichnisPar;
	}
	
	public void run() {
		while(true) {
		buffer.schreibe(null);
		}
	}
}
