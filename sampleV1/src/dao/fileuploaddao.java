package dao;

import java.io.BufferedReader;

import modal.userdetailmodal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import authentication.SendEmail;

import org.apache.commons.io.FileUtils;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;

import modal.fileuploadmodal;


public class fileuploaddao {
	private static final XSDDatatype String = null;
	private userdetailmodal user=new userdetailmodal();
	private SendEmail sendEmail=new SendEmail();
	private int teacherid1;
	private int userid;
	private String checkuser;
	private String chst;
	private String relst;
	

	public boolean insertfile(fileuploadmodal obj) {
System.out.println("inside dao");
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
//STEP 4: Execute a query
System.out.println("Creating statement...");
		      String query1 = "Insert into tutordb.fileupload(file,useremail) values(?,?);";
//STEP 5: Insert data 
		      prestatement = conn.prepareStatement(query1);
System.out.println("rahul heressss"+obj.getProductimage1ContentType());
System.out.println(obj.getProductimage1FileName());
System.out.println(obj.getProductimage1Path());
System.out.println(obj.getProductimage1());
				File destFile1 = new File(obj.getProductimage1Path(),obj.getProductimage1FileName());
				FileUtils.copyFile(obj.getProductimage1(), destFile1);
System.out.println(destFile1.getAbsolutePath()+"ssssssss");
// System.out.println(destFile1.getName()+""+destFile1.toString());;
				FileInputStream inputStream1 = new FileInputStream(destFile1);
				BufferedReader r=new BufferedReader(new FileReader(destFile1.getAbsolutePath()));
				String l;
// userdetailmodal user=new userdetailmodal();
				while((l=r.readLine())!=null){
System.out.println(l);
					String[] temp=l.split(",");
// setMessage(dao.addindividual(obj));
//System.out.println(getMessage());
System.out.println("rrrrrrrrrrrraaaaaaaaaaaaaaaaahhhhhhhh"+obj.getUseremail());

//check if student already exist in student table and relation table
/*					String query7 = "SELECT studentid FROM tutordb.student where useremail='"+temp[1]+"';";
System.out.println("rahul after query 7");					
					Statement statement7=conn.createStatement();
					ResultSet rs7 = statement7.executeQuery(query7);			
					System.out.println("rahul before while in query 7");					
					while (rs7.next()){
						System.out.println("rahul after while in query 7");					
						setChst(rs7.getString("studentid"));
System.out.println(getChst()+"getchst");
						String query8 = "SELECT studentid FROM tutordb.relation where studentid='"+getChst()+"' and teacherid in(SELECT teacherid FROM tutordb.teacher where useremail='"+obj.getUseremail()+"');";
						Statement statement8=conn.createStatement();
						ResultSet rs8 = statement8.executeQuery(query8);			
						if(rs8.next()){
System.out.println("inside rs8 if");

							setRelst(rs8.getString("studentid"));
							if(getRelst().equalsIgnoreCase(getChst())){
System.out.println("inside rs8 second if");
								setCheckuser("exist");
								continue;
//													return "already";
}
						}
						else{
*/
//check if student already exist
System.out.println("inside rs8 else");							
							String query6 = "SELECT studentid FROM tutordb.student where useremail='"+temp[1]+"';";
							Statement statement6=conn.createStatement();
							ResultSet rs6 = statement6.executeQuery(query6);			
							int size= 0;
							while (rs6.next())   
							{  
							  size++;  
							  userid=rs6.getInt("studentid");
//							  setCheckuser("exist");
							}
							if(size==0){
								String query2 = "Insert into tutordb.student (username,useremail,userpassword) values(?,?,?);";
						      //STEP 5: Insert data 
								prestatement = conn.prepareStatement(query2);
								prestatement.setString(1,temp[0]);
								prestatement.setString(2,temp[1]);
								//obj.setDefaultpassword("welcome");
								prestatement.setString(3, "welcome");
								prestatement.executeUpdate();
								String query3 = "SELECT studentid FROM tutordb.student where useremail='"+temp[1]+"';";
								Statement statement=conn.createStatement();
								// int userid;
								ResultSet rs = statement.executeQuery(query3);			
								if(rs.next()){
System.out.println(rs.getString("studentid"));
									userid=rs.getInt("studentid");
System.out.println(userid+"before storing into ontology from file");
//to insert user(student) into ontology
									dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();	
									o.createInstance(temp[0]+userid,"User");
									o.addDtProperty(temp[0]+userid, "userPassword", "welcome", String);
								}
								user.setName(temp[0]);
					  			user.setEmail(temp[1]);
					  			user.setPassword("welcome");
					  			user.setLoginid(user.getEmail());
					  			sendEmail.sendUserDetailsToMail(user);
							}//end of if		
							String query9="SELECT relationid FROM tutordb.relation where studentid='"+userid+"'and teacherid in(SELECT teacherid FROM tutordb.teacher where useremail='"+obj.getUseremail()+"')";
							Statement statement9=conn.createStatement();
							// int teacherid;
							ResultSet rs9 = statement9.executeQuery(query9);
							int ct=0;
							if(rs9.next())ct++;
							if(ct==0){
							String query4 = "Insert into tutordb.relation (studentid,teacherid) values(?,?);";
System.out.println(query4);
							//STEP 5: Insert data 
							prestatement1 = conn.prepareStatement(query4);
//System.out.println("inside preparedstatement"+getUserid());
//System.out.println("inside preparedstatement"+obj.getTeacherid());
							prestatement1.setInt(1,userid);
							String query5 = "SELECT teacherid FROM tutordb.teacher where useremail='"+obj.getUseremail()+"';";
							Statement statement1=conn.createStatement();
// int teacherid;
							ResultSet rs1 = statement1.executeQuery(query5);			
							if(rs1.next()){
System.out.println(rs1.getString("teacherid")+"::::check for teacherid");
								teacherid1=rs1.getInt("teacherid");}
System.out.println(teacherid1+"::::check for teacherid1");
								prestatement1.setInt(2,teacherid1);
								prestatement1.executeUpdate();
System.out.println("pre1");
//	if(getMessage().equalsIgnoreCase("success")){
					  			
						  }}
				 return true;
//				}
//			}
	}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
			  	return false;
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
