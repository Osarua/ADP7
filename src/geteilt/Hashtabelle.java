package geteilt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hashtabelle {
	
	private Element[] hashtabelle;
	
	private List<Ip> ipAddressen;
	
	public Hashtabelle() {
		hashtabelle = new Element[15];
		ipAddressen = new ArrayList<>();
	}
	
	public void hash (String iP, String log) {
		iP = iP.replace(".", "");
		String iPSub = iP.substring(5);
		String iPSub2 = iP.substring(0, 5);
		long hashcode = Integer.parseInt(iPSub)/ 100 + Integer.parseInt(iPSub2)/ 100;
		int hashwert = (int)(hashcode % 15);
		if(hashtabelle[hashwert] == null) {
			ipAddressen.add(new Ip(iP));
			hashtabelle[hashwert] = new Element(iP, log);
		} else if(hashtabelle[hashwert].getHashWert().equals(iP)) {
			hashtabelle[hashwert].logHinzu(log);
		} else {
			
			
		}
		
	}
	
	private void rehash () {
		
	}
	
	public List getLogs (String iP) {
		return null;
	}
	
	public List<Ip> getIpAddressen () {
		return ipAddressen;
	}
	
	public class Element {

		private String hashWert;

		private List<String> logs;

		public Element(String hashwertPar, String log) {
			hashWert = hashwertPar;
			logs = new LinkedList<>();
			logs.add(log);
		}

		public void logHinzu(String log) {
			logs.add(log);
		}

		public String getHashWert() {
			return hashWert;
		}

		public List<String> getLogs() {
			return logs;
		}

	}
	
}
