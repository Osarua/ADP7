package geteilt;

import java.util.ArrayList;
import java.util.List;

public class Hashtabelle {
	
	private Element[] ht;
	
	private List<Ip> ipAddressen;
	
	private int anzahl;
	
	public Hashtabelle() {
		ht = new Element[15];
		for (int i = 0; i <ht.length;i++) {
			ht[i] = new Element("", Status.FREI);
		}
		ipAddressen = new ArrayList<>();
		anzahl = 0;
		
	}
	
	public void einfuegenHashtabelle (String iP, String log) {
		int j = 0;
		long hashcode = berechneHashcode(iP);
		int index = hashwert(hashcode, j);
		while (!(ht[index].getwert().equals(iP)) && ht[index].getStatus() != Status.FREI ) {
			j = j + 1;
			index = hashwert(hashcode, j);
		}
		if(ht[index].getwert().equals(iP)){
			ht[index].logHinzu(log);
		} else { 
			if (ht[index].getStatus() == Status.FREI && anzahl > 0.8 * ht.length) {
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
	
	private int hashwert(long wert, int j){
		int hashWert = 0;
		if (j==0) {
			hashWert = (int) (wert % ht.length);
		}
		else {
			hashWert = (int) ((j + wert) % (ht.length-1));
		}
		return hashWert;
	}
	
	private void rehash () {
		float p = 1.5f;
		Element[] htAlt = ht;
		ht = new Element[(int)(p*ht.length)];
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
			i = hashwert(hashcode, j);
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
			i = hashwert(hashcode, j);
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
