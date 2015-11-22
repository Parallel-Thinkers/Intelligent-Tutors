package logic;
import modal.addindividualmodal;
import logic.addIndividualAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dataAccess.OwlAccess;
import datastructures.GlobalData;
import datastructures.Tables;

//public class loginCheckAction {
public class loginCheckAction extends ActionSupport implements ModelDriven<addindividualmodal> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private String useremail;
	private String userpassword;
	private String passwordvalidate;
	private String studentid;
	private String username;
	public static int id;
	private String chekuser;
	
	private addindividualmodal obj = new addindividualmodal();
	private addIndividualAction actionpass = new addIndividualAction();
	private studentprofileaction stprofile = new studentprofileaction();
	
	public addindividualmodal getObj() {
		return obj;
	}


	public void setObj(addindividualmodal obj) {
		this.obj = obj;
	}


	public String studentvalidate(){
		
		   Connection conn = null;
		      PreparedStatement prestatement = null;
			   
			  	try
				{
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection("jdbc:mysql://localhost/tutordb","root","");

				      //STEP 4: Execute a query
				      System.out.println("Creating statement...");

				      //STEP 5: Insert data 

		String query2 = "SELECT studentid,username,userpassword FROM tutordb.student where useremail='"+obj.getUseremail()+"';";
						
		 Statement statement=conn.createStatement();
		ResultSet rs = statement.executeQuery(query2);			
			if(rs.next()){
				System.out.println(rs.getString("studentid"));
				setPasswordvalidate(rs.getString("userpassword"));
				setStudentid(rs.getString("studentid"));
				setUsername(rs.getString("username"));

				if(getUserpassword().equalsIgnoreCase(getPasswordvalidate())){
						OwlAccess.refreshObject();
						Tables.refreshObject();
						GlobalData.refreshObject();
						Tables.refreshObject();
						GlobalData ogd = GlobalData.getObject();
						ogd.setUsername(getUsername()+getStudentid());
						return "success";
					}
				return "failure";
			}
				
				
				}
			   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(prestatement!=null)
			        	 prestatement.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			   }
		return "failure";
	}

	
//teacher login validation	
	
	public String teachervalidate(){
		System.out.println(obj.getUseremail());
		   Connection conn = null;
		      PreparedStatement prestatement = null;
			  	try
				{
				      Class.forName("com.mysql.jdbc.Driver");
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection("jdbc:mysql://localhost/tutordb","root","");
				      System.out.println("Creating statement...");
				      String query2 = "SELECT teacherid,username,userpassword FROM tutordb.teacher where useremail='"+obj.getUseremail()+"';";
		System.out.println(query2);
						
		 			Statement statement=conn.createStatement();
		 			ResultSet rs = statement.executeQuery(query2);			
		 			if(rs.next()){
		System.out.println(rs.getString("userpassword"));
						setPasswordvalidate(rs.getString("userpassword"));
						setUsername(rs.getString("username"));
System.out.println(getUsername());
						if(getUserpassword().equalsIgnoreCase(getPasswordvalidate())){
							setId(rs.getInt("teacherid"));
							
System.out.println(id+"inside if of logincheckaction:::");							



return "success";
						}
		 			}
				}
			   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(prestatement!=null)
			        	 prestatement.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			   }
		return "failure";
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		loginCheckAction.id = id;
	}


	public String pass(){
		obj.setTeacherid(getId());
		System.out.println("inside pass::::"+getId());
		System.out.println("inside pass::::"+obj.getTeacherid());
		if(actionpass.execute(obj).equalsIgnoreCase("success")){
			setChekuser(actionpass.getChkusr());
System.out.println("chekuser in pass inside logincheckaction::"+getChekuser());
		return "success";
		}

		return "failure";		
	}
	
private ArrayList<addindividualmodal> list1 = new ArrayList<addindividualmodal>();
private ArrayList<String> list2 = new ArrayList<String>();

	public String pass1(){
		obj.setTeacherid(getId());
//		System.out.println("inside pass::::"+getId());
//		System.out.println("inside pass::::"+obj.getTeacherid());
		
		if(stprofile.execute(obj).equals("success")){
			setList1(stprofile.getList());
			setList2(stprofile.getPlist());
System.out.println("successs of list1 inside login check action"+getList1());	
System.out.println("successs of list2 inside login check action"+getList2());	
			return "success";
		}
return "failure";		
	}
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getPasswordvalidate() {
		return passwordvalidate;
	}
	public void setPasswordvalidate(String passwordvalidate) {
		this.passwordvalidate = passwordvalidate;
	}
/*	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
*/	
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}


	@Override
	public addindividualmodal getModel() {
		// TODO Auto-generated method stub
		return obj;
	}


	public studentprofileaction getStprofile() {
		return stprofile;
	}


	public void setStprofile(studentprofileaction stprofile) {
		this.stprofile = stprofile;
	}


	public ArrayList<addindividualmodal> getList1() {
		return list1;
	}


	public void setList1(ArrayList<addindividualmodal> list1) {
		this.list1 = list1;
	}


	public String getChekuser() {
		return chekuser;
	}


	public void setChekuser(String chekuser) {
		this.chekuser = chekuser;
	}


	public ArrayList<String> getList2() {
		return list2;
	}


	public void setList2(ArrayList<String> list2) {
		this.list2 = list2;
	}
	


}
