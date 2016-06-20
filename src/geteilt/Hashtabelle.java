package geteilt;

import java.util.ArrayList;
import java.util.LinkedList;
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
		int i = hashwert(hashcode, j);
		int index = i;
		while (!(ht[i].getwert().equals(iP)) && ht[i].getStatus() != Status.FREI ) {
			j = j + 1;
			i = hashwert(hashcode, j);
			if(ht[i].getStatus() != Status.BELEGT && ht[index].getStatus() == Status.BELEGT) {
				index = i;
			}
		}
		if(ht[i].equals(iP)){
			ht[i].logHinzu(log);
		} else { 
			if (ht[index].getStatus() == Status.FREI && anzahl > 0.8 * ht.length) {
				rehash();
				einfuegenHashtabelle(iP, log);
		} else {
			if (ht[index].getStatus() == Status.FREI) {
				ipAddressen.add(new Ip(iP));
			anzahl = anzahl + 1;
			ht[index].setWert(iP);
			ht[index].logHinzu(log);
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
		ipAddressen.clear();
		for (int i = 0; i < htAlt.length; i++) {
			if(htAlt[i].getStatus() == Status.BELEGT) {
				List<Log> logs = htAlt[i].getLogs();
				for(int j = 0; j < logs.size(); j++) {
				einfuegenHashtabelle(htAlt[i].getwert(),logs.get(j).getLogEintrag());
				}
			}
		}
	}
	
	public List<Log> suchen (String iP) {
		long hashcode = berechneHashcode(iP);
		int j = 0;
		int i = 0;
		do {
			i = hashwert(hashcode, j);
			j = j + 1;
		} while(ht[i].getwert().equals(iP) || ht[i].getStatus() == Status.FREI);
		if (ht[i].getStatus() == Status.BELEGT) {
			return ht[i].getLogs();
		} else {
			String meldung = "Keine Log Einträge gefunden";
			List<Log> nichtGefunden = new LinkedList<>();
			nichtGefunden.add(new Log(meldung));
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
		return ipAddressen;
	}
	
}
