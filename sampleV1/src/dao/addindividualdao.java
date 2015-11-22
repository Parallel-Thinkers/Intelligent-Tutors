package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;

import modal.addindividualmodal;

public class addindividualdao {
	private static final XSDDatatype String = null;
	private int userid;
	private String checkuser;
	private String chst;
	private String relst;
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String addindividual(addindividualmodal obj) {
		  Connection conn = null;
	      PreparedStatement prestatement = null;
	      PreparedStatement prestatement1 = null; 
	      try
	      {
//STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
//STEP 3: Open a connection
System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/tutordb","root","");
//check if student already exist in student table and relation table
			String query5 = "SELECT studentid FROM tutordb.student where useremail='"+obj.getUseremail()+"';";
			
			Statement statement5=conn.createStatement();
			ResultSet rs5 = statement5.executeQuery(query5);			

			while (rs5.next())   
			{
				setChst(rs5.getString("studentid"));
System.out.println(getChst()+"getchst");
			
			String query6 = "SELECT studentid FROM tutordb.relation where studentid='"+getChst()+"' and teacherid='"+obj.getTeacherid()+"';";
			
			Statement statement6=conn.createStatement();
			ResultSet rs6 = statement6.executeQuery(query6);			
			
				if(rs6.next()){
System.out.println("inside rs6 if");
					setRelst(rs6.getString("studentid"));
			
					if(getRelst().equalsIgnoreCase(getChst())){
						setCheckuser("exist");
						return "already";
					}
				}
				else{
//check if student already exist in student table
System.out.println("inside rs6 else");						
						String query4 = "SELECT studentid FROM tutordb.student where useremail='"+obj.getUseremail()+"';";
						Statement statement4=conn.createStatement();
						ResultSet rs4 = statement4.executeQuery(query4);			
			
						int size= 0;
						while (rs4.next()){
							size++;  
							setUserid(rs4.getInt("studentid"));
//	  setCheckuser("exist");
						}
						if(size==0){
//STEP 4: Execute a query
System.out.println("Creating statement...");
							String query1 = "Insert into tutordb.student (username,useremail,userpassword) values(?,?,?);";
//STEP 5: Insert data 
							prestatement = conn.prepareStatement(query1);
							prestatement.setString(1,obj.getStudentname());
							prestatement.setString(2,obj.getUseremail());
							obj.setDefaultpassword("welcome");
							prestatement.setString(3, obj.getDefaultpassword());
							prestatement.executeUpdate();
							String query2 = "SELECT studentid FROM tutordb.student where useremail='"+obj.getUseremail()+"';";
							Statement statement=conn.createStatement();
							ResultSet rs = statement.executeQuery(query2);			
							if(rs.next()){
System.out.println(rs.getString("studentid"));
								setUserid(rs.getInt("studentid"));
		//to insert user(student) into ontology
								dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();	
								o.createInstance(obj.getStudentname()+getUserid(),"User");
								o.addDtProperty(obj.getStudentname()+getUserid(), "userPassword", obj.getDefaultpassword(), String);
							}
						}//if close			
						String query3 = "Insert into tutordb.relation (studentid,teacherid) values(?,?);";
System.out.println(query3);
//STEP 5: Insert data 
						prestatement1 = conn.prepareStatement(query3);
System.out.println("inside preparedstatement"+getUserid());
System.out.println("inside preparedstatement"+obj.getTeacherid());
						prestatement1.setInt(1,getUserid());
						prestatement1.setInt(2,obj.getTeacherid());
						prestatement1.executeUpdate();
System.out.println("pre1");
						return "success";
					}
				}
		}catch(SQLException se){
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




	public String getCheckuser() {
		return checkuser;
	}

	public void setCheckuser(String checkuser) {
		this.checkuser = checkuser;
	}

	public String getChst() {
		return chst;
	}

	public void setChst(String chst) {
		this.chst = chst;
	}

	public String getRelst() {
		return relst;
	}

	public void setRelst(String relst) {
		this.relst = relst;
	}
}
