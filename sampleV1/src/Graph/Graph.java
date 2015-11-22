package Graph;

import datastructures.GlobalData;

public class Graph {
	private String uri;
	GlobalData ogd = GlobalData.getObject();
	public String execute()
	{
		uri=ogd.getProblemUri()+".json";
		System.out.println("gupta"+uri);
		return "success";
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	

}
