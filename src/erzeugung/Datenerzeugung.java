package erzeugung;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
/**
 * TI3 ADP, SS16 
 * Gruppe: Julian Magierski (julian.magierski@haw-hamburg.de)
 * Kristian Exß (kristian.exss@haw-hamburg) 
 * Aufgabenblatt 7: Hashfunktionen
 */
public class Datenerzeugung {
	
	String dateiname;
	
	public Datenerzeugung(String dateinamePar) {
		dateiname = dateinamePar;
	}
	
	public void weblogErstellen (int anzahlPar) throws IOException {
		LinkedList<String> fifo = new LinkedList<String> ();
		String [] namen = {"Hans","Peter","Sigmar","Gabriel","Jürgen","Boris","Jesus","Max","Christopfer","Reichshauptweltkomplex"};
		String [] verb = {" hat "," singt "," reinigt "," dreht "," zeichnet "," befiehlt "," kontrolliert "," bewundert "," operiert "," sieht "};
		for (int i=0;i<anzahlPar;i++) {
			int erste = zahlErzeugenOhneNull()*100+zahlErzeugen()*10+zahlErzeugen();
			int zweite = zahlErzeugenOhneNull()*100+zahlErzeugen()*10+zahlErzeugen();
			int dritte = zahlErzeugenOhneNull()*10+zahlErzeugen();
			int vierte = zahlErzeugenOhneNull()*10+zahlErzeugen();
			fifo.addLast(erste+"."+zweite+"."+dritte+"."+vierte+";"+namen[zahlErzeugen()]+verb[zahlErzeugen()]+namen[zahlErzeugen()]);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter (dateiname)); 
		while (fifo.size()!=0) {
			writer.write(fifo.pop());
			writer.newLine();
		}
		writer.close();
	}
	public static int zahlErzeugenOhneNull () {
		int zahl = zahlErzeugen ();
		if (zahl==0) {
			zahl = zahlErzeugenOhneNull();
		}
		return zahl;
	}
	
	public static int zahlErzeugen () {
		int zahl = (int)(Math.random()*10);
		if (zahl == 10) {
			zahl = zahlErzeugen ();
		}
		return zahl;
	}

}
