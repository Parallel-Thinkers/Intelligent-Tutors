/**
*
* author: rahul and shan  
*
*/

package logic;

import java.sql.Connection;
import authentication.SendEmail;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;


public class SignUpAction {

	private static final XSDDatatype String = null;
	private String username;
	private String useremail;
	private String userpassword;
	private String signupradio;
	private String userid;
	private String dbtablename;
	SendEmail o1=new SendEmail();
	public String execute(){
		
		dbtablename = getSignupradio();
		
//		if(getSignupradio().equalsIgnoreCase("student")){


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
				      String query1 = "Insert into tutordb."+dbtablename+"(username,useremail,userpassword) values(?,?,?);";

				      //STEP 5: Insert data 
						prestatement = conn.prepareStatement(query1);

						prestatement.setString(1,getUsername());
//						System.out.println("i am in try2");		
						prestatement.setString(2,getUseremail());
						prestatement.setString(3, getUserpassword());

						prestatement.executeUpdate();
						o1.welcome(getUsername(),getUserpassword(),getUseremail());
	if(getSignupradio().equalsIgnoreCase("student")){
		String query2 = "SELECT studentid FROM tutordb.student where useremail='"+getUseremail()+"';";
						
		 Statement statement=conn.createStatement();
		ResultSet rs = statement.executeQuery(query2);			
			if(rs.next()){
				System.out.println(rs.getString("studentid"));
				setUserid(rs.getString("studentid"));
//to insert user(student) into ontology
				dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();	
				o.createInstance(getUsername()+getUserid(),"User");
				o.addDtProperty(getUsername()+getUserid(), "userPassword", getUserpassword(), String);
			}
			
			
	}
				return "success";
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
			
			
//		}
		return "failure";
	}


//getters and setters for sign up
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSignupradio() {
		return signupradio;
	}
	public void setSignupradio(String signupradio) {
		this.signupradio = signupradio;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}



}
