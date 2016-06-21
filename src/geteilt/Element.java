package geteilt;


public class Element {

	private String wert;

	private String logs;

	private Status status;
	
	public Element(String wertPar, Status statusPar) {
		wert = wertPar;
		logs = "";
		status = statusPar;
	}

	public void logHinzu(String log) {
		logs = logs + log + "\n";
	}

	public String getwert() {
		return wert;
	}
	
	public void setInitLog(String log) {
		logs = log;
	}

	public String getLogs() {
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