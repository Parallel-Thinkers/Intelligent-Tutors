package logic;

import java.util.ArrayList;

import dataAccess.*;
import datastructures.GlobalData;
import datastructures.Tables;

public class Login {
	
	Boolean debug=false;
	String username;
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login()
	{
		System.out.println("password is: "+password);
		OwlAccess.refreshObject();
		Tables.refreshObject();
		GlobalData.refreshObject();
		Tables.refreshObject();
		GlobalData ogd = GlobalData.getObject();
		ogd.setUsername(username);
		//JOptionPane.showMessageDialog(null, "uname is "+username);
		
		return "success";
	/*	if(checkUser(username, password)==0)
		{
			GlobalData ogd = GlobalData.getObject();
			ogd.setUsername(username);
			//JOptionPane.showMessageDialog(null, "uname is "+username);
			return "success";
		}
		else
		{
			return "failed";
		}*/
	}
	
	public String logout(){
		OwlAccess o=OwlAccess.getObject();
		o.closeModel(1);
		System.out.println("Logout function");
		return "success";
	}
	public int checkUser(String tempUName, String tempPWord)
	{
		//OwlAccess o = OwlAccess.getObject();
		RDFStoreAccess o = RDFStoreAccess.getObject();
	
		String querystring = "select ?user { " +
				"?user <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#User> . " 
				+ "?user <http://www.owl-ontologies.com/MenDomOntology.owl#userPassword> \""+ tempPWord +"\"^^<http://www.w3.org/2001/XMLSchema#string> . " +"}";
		ArrayList<String> returnResult = o.runQueryList(querystring);
		System.out.println("Return result:"+returnResult.size());
		for(int i=0;i<returnResult.size();i++)
		{
			String userUri = returnResult.get(i);
			if(tempUName.equals(userUri.substring(userUri.indexOf("#")+1, userUri.indexOf(">"))))
			{
				if(debug)
					System.out.println(userUri.substring(userUri.indexOf("#")+1, userUri.indexOf(">")));
				return 0;
			}
		}
		return -1;
	}
}
