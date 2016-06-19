package geteilt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hashtabelle {
	
	private enum Status {FREI, BELEGT};
	
	private Element[] ht;
	
	private List<Ip> ipAddressen;
	
	private int anzahl;
	
	public Hashtabelle() {
		ht = new Element[15];
		for (int i = 0; i <ht.length;i++) {
			ht[i] = new Element("", "", Status.FREI);
		}
		ipAddressen = new ArrayList<>();
		anzahl = 0;
		
	}
	
	public void einfuegenHashtabelle (String iP, String log) {
		String iPLong = iP.replace(".", "");
		int j = 0;
		long hashcode = Long.parseLong(iPLong);
		int i = hashwert(hashcode, j);
		int index = i;
		while (!(ht[i].getwert().equals(iP)) && ht[i].getStatus() != Status.FREI ) {
			j = j + 1;
			i = hashwert(hashcode, j);
			//System.out.printf("i: %d \n", i);
			if(ht[i].getStatus() != Status.BELEGT && ht[index].getStatus() == Status.BELEGT) {
				index = i;
			}
		}
		if(ht[i].equals(iP)){
			//ht[i].logHinzu(log);
		} else { 
			if (ht[index].getStatus() == Status.FREI && anzahl > 0.8 * ht.length) {
			System.out.print("zu viel\n");
				rehash();
			einfuegenHashtabelle(iP, log);
		} else {
			ipAddressen.add(new Ip(iP));
			if (ht[index].getStatus() == Status.FREI) {
			anzahl = anzahl + 1;
			System.out.printf("Anzahl: %d\n",anzahl);
			ht[index].setWert(iP);
			ht[index].logHinzu(log);
			ht[index].setStatus(Status.BELEGT);
			}
		}	
		}
	}
	
	private int hashwert(long wert, int j){
		int hashWert = ((int)(wert % ht.length)) + j;
		if(hashWert >= ht.length || hashWert < 0) {
			hashWert = 0;
		}
		return hashWert;
	}
	
	private void rehash () {
		float p = 5f;
		Element[] htAlt = ht;
		ht = new Element[(int)(p*ht.length)];
		for (int i = 0; i < ht.length; i++) {
			ht[i] = new Element("","",Status.FREI);
		}
		anzahl = 0;
		
		ipAddressen.clear();
		for (int i = 0; i < htAlt.length; i++) {
			if(htAlt[i].getStatus() == Status.BELEGT) {
			//	List<String> logs = htAlt[i].getLogs();;
				//for(int j = 0; j < logs.size(); j++) {
				einfuegenHashtabelle(htAlt[i].getwert(),"");
				//}
			}
		}
	}
	
	public List getLogs (String iP) {
		return null;
	}
	
	public List<Ip> getIpAddressen () {
		return ipAddressen;
	}
	
	public class Element {

		private String wert;

		private List<String> logs;

		private Status status;
		
		public Element(String wertPar, String log, Status statusPar) {
			wert = wertPar;
			logs = new LinkedList<>();
			logs.add(log);
			status = statusPar;
			
		}

		public void logHinzu(String log) {
			logs.add(log);
		}

		public String getwert() {
			return wert;
		}

		public List<String> getLogs() {
			return logs;
		}
		
		public void setWert(String wert) {
			this.wert = wert;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public Status getStatus() {
			return status;
		}

	}
	
}
