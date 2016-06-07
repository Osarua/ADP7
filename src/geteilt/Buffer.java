package geteilt;


public class Buffer {

	private String buffer[];
	
	private int leseKopf;
	
	private int schreibKopf;
	
	private final static int BUFFERSIZE = 10; 
	
	private int freiePlaetze = 0;
	
	public Buffer() {
		buffer = new String[10];
		leseKopf = -1;
		schreibKopf = 0;
		freiePlaetze = BUFFERSIZE;
	}
	
	public String lese() {
		while(freiePlaetze == BUFFERSIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		leseKopf++;
		if(leseKopf > BUFFERSIZE) {
			leseKopf = -1;
		}
		freiePlaetze++;
		notifyAll();
		return buffer[leseKopf];
	}
	
	public void schreibe(String eintrag) {
		while(freiePlaetze == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[schreibKopf] = eintrag;
		schreibKopf++;
		if(schreibKopf > BUFFERSIZE) {
			schreibKopf = 0;
		}
		freiePlaetze++;
		notifyAll();
	}
}
