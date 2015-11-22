package datastructures;

public class Interaction
{
	String lines="";
	String history="";
	
	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = this.lines+"\n"+lines;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = this.history+"\n"+history;
	}
	
	static Interaction interaction = new Interaction();
	
	private Interaction()
	{
		
	}
	
	public static Interaction getObject()
	{
		return interaction;
	}
}
