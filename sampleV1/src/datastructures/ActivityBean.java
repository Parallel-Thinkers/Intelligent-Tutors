package datastructures;

public class ActivityBean {

	private String proburi;
	public String getProburi() {
		return proburi;
	}
	public void setProburi(String proburi) {
		this.proburi = proburi;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public char getMode() {
		return mode;
	}
	public void setMode(char mode) {
		this.mode = mode;
	}
	private long start;
	private long end;
	private char mode;
	
	
}
