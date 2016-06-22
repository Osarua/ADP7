package geteilt;

import java.util.ArrayList;
import java.util.List;


public class Hashtabelle {
	
	private Element[] ht;
	
	private List<Ip> ipAddressen;
	
	private int anzahl;
	
	private final int arraylaenge [] = {15,22, 33, 49, 73, 109, 163, 244, 366, 549, 823, 1234, 1851, 2776, 4164, 6246, 9369, 14053,
			21079,31618, 47427, 71140, 106710, 160065, 240097, 360145, 540217, 810325, 1215487, 1823230, 2734845, 4102267, 6153400, 
			9230100, 13845150};
	private final int primzahlen[] = {13, 19 , 31 , 47, 73, 109, 163, 241, 359, 547, 823, 1231, 1847, 2767, 4159, 6229, 9349, 14051,
			21067, 31607, 47419 ,71129 ,106703, 160049, 240089, 360091, 540217, 810319 , 1215463, 1823219 , 2734819,4102249 , 6153383, 
			9230083 , 13845133};
	
	private int primzahlPos;
	
	public Hashtabelle() {
		primzahlPos=0;
		ht = new Element[arraylaenge[primzahlPos]];
		for (int i = 0; i <ht.length;i++) {
			ht[i] = new Element("", Status.FREI);
		}
		ipAddressen = new ArrayList<>();
		anzahl = 0;
		
	}
	
	public void einfuegenHashtabelle (String iP, String log) {
		int j = 0;
		long hashcode = berechneHashcode(iP);
		int index = hashwert(hashcode, j, 0);
		while (!(ht[index].getwert().equals(iP)) && ht[index].getStatus() != Status.FREI ) {
			j = j + 1;
			index = hashwert(hashcode, j, index);
		}
		if(ht[index].getwert().equals(iP)){
			ht[index].logHinzu(log);
		} else { 
			if (ht[index].getStatus() == Status.FREI && anzahl > 0.8 * primzahlen[primzahlPos]) {
				rehash();
				einfuegenHashtabelle(iP, log);
		} else {
			if (ht[index].getStatus() == Status.FREI) {
				anzahl = anzahl + 1;
				ht[index].setWert(iP);
				ht[index].setInitLog(log);
				ht[index].setStatus(Status.BELEGT);
			}
		}	
		}
	}
	
	private long berechneHashcode (String iP) {
		String iPLong = iP.replace(".", "");
		return Long.parseLong(iPLong);
	}
	
	private int hashwert(long wert, int j, int index){
		int hashWert = 0;
		if (j==0) {
			hashWert = (int) (wert % primzahlen[primzahlPos]);
		}
		else {
			hashWert = (index) %  (primzahlen[primzahlPos]-1) +1;
			//hashWert = (int)((index * ((Math.sqrt(5)-1)/2)) % primzahlen[primzahlPos]);
			
		}
		return hashWert;
	}
	
	private void rehash () {
		Element[] htAlt = ht;
		primzahlPos++;
		int laenge = arraylaenge[primzahlPos];
		ht = new Element[laenge];
		for (int i = 0; i < ht.length; i++) {
			ht[i] = new Element("",Status.FREI);
		}
		anzahl = 0;	
		for (int i = 0; i < htAlt.length; i++) {
			if(htAlt[i].getStatus() == Status.BELEGT) {
				String logs = htAlt[i].getLogs();
				einfuegenHashtabelle(htAlt[i].getwert(),logs);
			}
		}
		htAlt = null;
		System.gc();
	}
	
	public Element suchen (String iP) {
		long hashcode = berechneHashcode(iP);
		int j = 0;
		int i = 0;
		while (true) {
			i = hashwert(hashcode, j, i);
			j = j + 1;
			if (ht[i].getwert().equals(iP)|| ht[i].getStatus() == Status.FREI) {
				break;
			}
		}
		//System.out.println("Eingabe: "+hashcode+ " gefunden "+ ht[i].getLogs() + " " + ht[i].getwert() + " " + i);
		if (ht[i].getStatus() == Status.BELEGT) {
			return ht[i];
		} else {
			String meldung = "Keine Log Einträge gefunden";
			Element nichtGefunden = new Element("",Status.ENTFERNT);
			nichtGefunden.logHinzu(meldung);
			return nichtGefunden;
		}
	}
	
	public boolean loeschen (String iP) {
		int j = 0;
		int i = 0;
		long hashcode = berechneHashcode(iP);
		do {
			i = hashwert(hashcode, j,i);
			j = j + 1;
		} while(ht[i].getwert().equals(iP) || ht[i].getStatus() == Status.FREI);
		if (ht[i].getStatus() == Status.BELEGT) {
			ht[i].setStatus(Status.ENTFERNT);
			return true;
		} else {
			return false;
		}
	}
	
	public List<Ip> getIpAddressen () {
		for (int i = 0; i < ht.length; i++) {
			if(ht[i].getStatus() == Status.BELEGT) {
				ipAddressen.add(new Ip(ht[i].getwert()));
			}
		}
		return ipAddressen;
	}
	
}
