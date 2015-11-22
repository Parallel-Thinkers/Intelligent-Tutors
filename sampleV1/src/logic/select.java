package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dataAccess.OwlAccess;
import datastructures.GlobalData;
import datastructures.Tables;

public class select {
	private String studentlistid;
	private String problemtypeid;
	private String usrnm;
	int id;
	public String getStudentlistid() {
		return studentlistid;
	}
	public void setStudentlistid(String studentlistid) {
		this.studentlistid = studentlistid;
	}
	
	public String execute(){
		String[] temp=studentlistid.split("/");
		setUsrnm(temp[0]);
		 Connection conn = null;
	      PreparedStatement prestatement = null;
		   
		  	try
			{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      System.out.println(getProblemtypeid());
			      conn = DriverManager.getConnection("jdbc:mysql://localhost/tutordb","root","");

			      //STEP 4: Execute a query
			      System.out.println("Creating statement...");

			      //STEP 5: Insert data 

	String query2 = "SELECT studentid FROM tutordb.student where useremail='"+temp[1]+"';";
					
	 Statement statement=conn.createStatement();
	ResultSet rs = statement.executeQuery(query2);			
		if(rs.next()){
			System.out.println(rs.getString("studentid"));
			id=rs.getInt("studentid");
			
	}
		OwlAccess.refreshObject();
		Tables.refreshObject();
		GlobalData.refreshObject();
		Tables.refreshObject();
		GlobalData ogd = GlobalData.getObject();
		System.out.println(temp[0]+id+"....//");
		ogd.setUsername(temp[0]+id);
		return "success";	
			}
		  	
		  	
		  	
		  	catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
	return "failure";
}
	public String getProblemtypeid() {
		return problemtypeid;
	}
	public void setProblemtypeid(String problemtypeid) {
		this.problemtypeid = problemtypeid;
	}
	public String getUsrnm() {
		return usrnm;
	}
	public void setUsrnm(String usrnm) {
		this.usrnm = usrnm;
	}}
