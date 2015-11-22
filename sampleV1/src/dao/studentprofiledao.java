package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import datastructures.GlobalData;
import modal.addindividualmodal;

public class studentprofiledao {
	private ArrayList<String> plist1 = new ArrayList<String>();

	public ArrayList<String> getPlist1() {
		return plist1;
	}

	public void setPlist1(ArrayList<String> plist1) {
		this.plist1 = plist1;
	}

	public boolean getstudentlist(addindividualmodal obj, ArrayList<addindividualmodal> list) {
		// TODO Auto-generated method stub
		System.out.println(obj.getTeacherid()+"teacherid inside student profile");
		Connection conn = null;
			  	try
				{
				      Class.forName("com.mysql.jdbc.Driver");
				      conn = DriverManager.getConnection("jdbc:mysql://localhost/tutordb","root","");
						String query2 = "SELECT studentid FROM tutordb.relation where teacherid='"+obj.getTeacherid()+"';";
						 Statement statement=conn.createStatement();
						ResultSet rs = statement.executeQuery(query2);			
	System.out.println("inside query2");
						while(rs.next()){
							addindividualmodal obj1 = new addindividualmodal();
								obj1.setStudentid(rs.getInt("studentid"));
								String query3 = "SELECT	username,useremail FROM tutordb.student where studentid='"+obj1.getStudentid()+"';";
System.out.println(query3);
								Statement statement1=conn.createStatement();
								ResultSet rs1 = statement1.executeQuery(query3);			
	//System.out.println("inside query3"+obj1.getStudentid()+obj1.getStudentname());
								while(rs1.next()){
								obj1.setStudentname(rs1.getString("username"));
								obj1.setUseremail(rs1.getString("useremail"));
								System.out.println(obj1.getUseremail()+"useremail");
								}
	System.out.println("inside query3"+obj1.getStudentid()+obj1.getStudentname());
								list.add(obj1);
							}
							
			System.out.println(list+""+"::::list");				

			GlobalData ogd = GlobalData.getObject();
			ArrayList<String> tempProblemTypeList = new ArrayList<String>();
			ogd.loadAllProblemTypes();
			for(int i=0;i<ogd.getProblemTypeList().size();i++)
			{
				tempProblemTypeList.add(ogd.getProblemTypeList().get(i).getURI());
			}
			Collections.sort(tempProblemTypeList);
			plist1=tempProblemTypeList;	
			System.out.println(plist1+""+"::::plist");				
			
				return true;
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
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			   }
		return false;

	}

}
