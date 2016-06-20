package geteilt;



import java.util.LinkedList;
import java.util.List;

public class Element {

	private String wert;

	private List<Log> logs;

	private Status status;
	
	public Element(String wertPar, Status statusPar) {
		wert = wertPar;
		logs = new LinkedList<>();
		status = statusPar;
	}

	public void logHinzu(String log) {
		logs.add(new Log(log));
	}

	public String getwert() {
		return wert;
	}

	public List<Log> getLogs() {
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