package logic;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import dao.studentprofiledao;
import modal.addindividualmodal;

public class studentprofileaction extends ActionSupport{

	/**
	 *@rahul and shan 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<addindividualmodal> list = new ArrayList<addindividualmodal>();
	private ArrayList<String> plist = new ArrayList<String>();
	private studentprofiledao dao = new studentprofiledao();
	
	public String execute(addindividualmodal obj) {

			if(dao.getstudentlist(obj,list)){
				setPlist(dao.getPlist1());
	System.out.println("successs inside student profile action"+getList()+dao.getPlist1());	
				return "success";
			}
			return "failure";
	}

	public ArrayList<addindividualmodal> getList() {
		return list;
	}

	public void setList(ArrayList<addindividualmodal> list) {
		this.list = list;
	}

	public ArrayList<String> getPlist() {
		return plist;
	}

	public void setPlist(ArrayList<String> plist) {
		this.plist = plist;
	}
	
}
