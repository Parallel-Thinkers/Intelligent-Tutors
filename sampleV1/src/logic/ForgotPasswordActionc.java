package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modal.userdetailmodal;
import authentication.SendEmail;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ForgotPasswordActionc extends ActionSupport implements ModelDriven<userdetailmodal> {

	/**
	 * @author rahul and shan
	 */
	private static final long serialVersionUID = 1L;

	private userdetailmodal obj = new userdetailmodal();

	private String checkemail;
	private SendEmail sendEmail=new SendEmail();
	
	public String execute(){
		
		   Connection conn = null;
		      PreparedStatement prestatement = null;
			  	try
				{
				      Class.forName("com.mysql.jdbc.Driver");
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection("jdbc:mysql://localhost/tutordb","root","");
				      System.out.println("Creating statement...");
				      String query2 = "SELECT username,useremail,userpassword FROM tutordb.student where useremail='"+getCheckemail()+"';";
		System.out.println(query2);
						
		 			Statement statement=conn.createStatement();
		 			ResultSet rs = statement.executeQuery(query2);			
		 			if(rs.next()){
		 				obj.setEmail(rs.getString("useremail"));

		 				if(getCheckemail().equalsIgnoreCase(obj.getEmail())){
			 				obj.setPassword(rs.getString("userpassword"));
			 				obj.setName(rs.getString("username"));
			 				sendEmail.sendforgotpassword(obj);
		 					
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

			  	return "failure";
	}
	
	public String getCheckemail() {
		return checkemail;
	}

	public void setCheckemail(String checkemail) {
		this.checkemail = checkemail;
	}


	public userdetailmodal getObj() {
		return obj;
	}

	public void setObj(userdetailmodal obj) {
		this.obj = obj;
	}

	@Override
	public userdetailmodal getModel() {
		// TODO Auto-generated method stub
		return obj;
	}
	
	

}
