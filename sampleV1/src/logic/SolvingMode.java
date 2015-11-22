package logic;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import dataAccess.*;
import datastructures.*;
import datastructures.Tables.UserEntityTableEntry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;


public class SolvingMode 
{
	String givenText;
	String tofindText;
	String solutionText;
	
	String givenArea;
	String tofindArea;
	String solutionArea;
	
	String displayFormula="";
	String displaySchema="";
	String displaySchema1="";
	String msg;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDisplaySchema1() {
		return displaySchema1;
	}

	public void setDisplaySchema1(String displaySchema1) {
		this.displaySchema1 = displaySchema1;
	}

	String problemImage="images/";
	public String getProblemImage() {
		return problemImage;
	}

	public void setProblemImage(String problemImage) {
		this.problemImage = problemImage;
	}

	public static int getGivenSuccess() {
		return givenSuccess;
	}

	public static void setGivenSuccess(int givenSuccess) {
		SolvingMode.givenSuccess = givenSuccess;
	}

	public Tables getT() {
		return t;
	}

	public void setT(Tables t) {
		this.t = t;
	}

	static int givenSuccess=0;
	public String getDisplaySchema() {
		return displaySchema;
	}

	public void setDisplaySchema(String displaySchema) {
		this.displaySchema = displaySchema;
	}

	public String getDisplayFormula() {
		return displayFormula;
	}

	public void setDisplayFormula(String displayFormula) {
		this.displayFormula = displayFormula;
	}

	String problem;
	String problemType;

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	Tables t=Tables.getObject();

	class InputStatement
	{
		int returnType=0;
		String lhs="";
		String rhs="";
		String unit="";
	}
	
	public String getGivenText() {
		return givenText;
	}

	public void setGivenText(String givenText) {
		this.givenText = givenText;
	}

	public String getTofindText() {
		return tofindText;
	}

	public void setTofindText(String tofindText) {
		this.tofindText = tofindText;
	}

	public String getSolutionText() {
		return solutionText;
	}

	public void setSolutionText(String solutionText) {
		this.solutionText = solutionText;
	}

	public String getGivenArea() {
		return givenArea;
	}

	public void setGivenArea(String givenArea) {
		this.givenArea = givenArea;
	}

	public String getTofindArea() {
		return tofindArea;
	}

	public void setTofindArea(String tofindArea) {
		this.tofindArea = tofindArea;
	}

	public String getSolutionArea() {
		return solutionArea;
	}

	public void setSolutionArea(String solutionArea) {
		this.solutionArea = solutionArea;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	InputStatement parse(String input)
	{
		InputStatement returnValue = new InputStatement();
//		input=input.trim();
		if(input.indexOf('=')>-1)
		{
//			dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();	
//			o.createInstance("ankitnnn@@","User");

			returnValue.lhs=input.substring(0, input.indexOf('=')).trim();
System.out.println("lhs is::::"+returnValue.lhs);
		}
		else
		{
			returnValue.returnType = -1;
			return returnValue;
		}
		input=input.substring(input.indexOf('=')+1).trim();
System.out.println(input);
		if(input.equals("?"))
		{
			returnValue.rhs=input.trim();
System.out.println("rhs is::::"+returnValue.rhs);
		}
		if(input.indexOf(' ')>-1)
		{
			returnValue.rhs=input.substring(0, input.lastIndexOf(' ')).trim();
			System.out.println("rhs is::::"+returnValue.rhs);
		}
		else
		{
			returnValue.returnType = -2;
			return returnValue;
		}
		input=input.substring(input.lastIndexOf(' ')+1).trim();
		//System.out.println("Debargha input:"+input);
		if(input.length()>0)
		{
			returnValue.unit=input.trim();
			System.out.println("unit is::::"+returnValue.unit);

		}
		else
		{
			returnValue.returnType = -3;
			return returnValue;
		}
		
System.out.println("inside parse"+returnValue);
		return returnValue;
	}

/*	void main(String args[])
	{
		InputStatement is = parse("a=10 cm");
		System.out.println("After break is: ");
		System.out.println(is.lhs+" "+is.rhs+" "+is.unit);
	}*/

	public String givenExecute(String input)
	{
		InputStatement is = parse(input);
		if(is.returnType==0)//all are proper
		{
			if(checksysete(is,1))
			{
				if(checkinuete(is.lhs))
				{
					return "Entered value already exists!";//already exists either return an error telling already exists or dont flag anything
				}
				else
				{
					passintouserete(is);
				}
			}
			else
			{
				return "Check lhs of the statement entered";//not in sete
			}
		}
		else if(is.returnType==-1)
		{
			return "Entered statement does not have rhs";//No equal symbol
		}
		else if(is.returnType==-2)
		{
			return "Entered statement does not have unit";//No space
		}
		else if(is.returnType==-3)
		{
			return "Entered statement does not have unit";//No units after space
		}
		
		return "Success";
	}

	public String tofindExecute(String input)
	{
		InputStatement is = parse(input);
		System.out.println("In tofind: "+is.lhs+ " " +is.rhs+ " " +is.unit);
		if(is.returnType==0)
		{
			return "1.To find statements should contain only lhs followed by =?";//has all three, not valid for to find
		}
		else if(is.returnType==-1)
		{
			return "Entered statement does not have rhs";//No equals symbol
		}
		else if(is.returnType==-2)
		{
			if(is.rhs.equals("?"))
			{
				if(checksysete(is, 2))
				{
					if(checkinuete(is.lhs))
					{
						return "Entered value already exists!";//already exists either return an error telling already exists or dont flag anything
					}
					else
					{
						passintouserete(is);
					}
				}
				else
				{
					return "Check lhs of the statement entered";//does not exist in sysete
				}
			}
			else
			{
				return "To find statements should contain only lhs followed by =?";//to find rhs cannot have values
			}
		}
		else if(is.returnType==-3)
		{
			return "To find statements should not contain units";//No unit
		}
		return "Success";
	}	
	
	public ArrayList<String> checkTaskCompleted(InputStatement is)
	{
		GlobalData ogd = GlobalData.getObject();
		
		String querystring = "select ?t { " +
				"<http://www.owl-ontologies.com/MenDomOntology.owl#"+ogd.getProblemUri() +"> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s . " +
				"?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t . " +
				" ?t <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?hf . " +
				" ?hf <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r . " +
					" FILTER (?r = \""  + is.lhs +"\""+
					")}";
		
		/*String querystring="select ?ic where { <http://www.owl-ontologies.com/MenDomOntology.owl#"+ ogd.getUsername() +"> <http://www.owl-ontologies.com/MenDomOntology.owl#IncompleteTasks> ?ic ." +
				" ?ic <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?hf . " +
			" ?hf <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r . " +
				" FILTER (?r = \""  + is.lhs +"\""+
				")}";*/
		
	
		System.out.println("inside complete task query:"+querystring);
		OwlAccess o = OwlAccess.getObject();
		//GlobalData ogd = GlobalData.getObject();
		//is' lhs in a query to check if it completes any task
		ArrayList<String> completedTask = new ArrayList<String>();
		completedTask = o.runQueryListwithoutreasoner(querystring);
		System.out.println("completed task size:"+completedTask.size());
		if(completedTask.size()>0)//each lhs will always complete only one task so loop is not needed .get(0) will suffice
		{
			//loop through completed task and for each string remove from incomplete and add to complete
			
			for(int i=0;i<completedTask.size();i++)
			{
				//parsing happens here
				System.out.println("debargha inside completed task:"+completedTask.get(i)+" "+is.lhs);
				completedTask.set(i, completedTask.get(i).substring(completedTask.get(i).indexOf("#")+1, completedTask.get(i).indexOf(">")));
				
				
				System.out.println("completed task: "+completedTask.get(i));
				//if true set those tasks as completed and remove it from incomplete tasks
				//o.removeProperty("IncompleteTasks", completedTask.get(i));
				//ogd.deleteFromIncompleteTasks(completedTask.get(i));
				
				//Update resolve count if caputured before
				querystring="select ?bugs  "+ 
						"{"+
						"<http://www.owl-ontologies.com/MenDomOntology.owl#"+completedTask.get(i) +"> <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?bugs }";
						

				ArrayList<String> results = o.runQueryList(querystring);

				for (String bug : results) {
					bug = bug.substring(bug.indexOf("#") + 1,
							bug.lastIndexOf(">"));
					String query1 = "select ?bugs {<http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ ogd.getUsername()
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#capturedBugs> ?bugs ."
							+ " FILTER (?bugs =  <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ bug + ">" + ")" + "}";

					ArrayList<String> res1 = o.runQueryList(query1);
					if (res1.size() > 0) {
						System.out.println("bugs matched in task complete");
						o.updateResolvedBugCount(bug);
					}

				}
				
				o.addObjProperty(ogd.getUsername(), "completedTasks", completedTask.get(i));
				
				
			}
		}
		return completedTask;
	}
	
	public String predictBugs()
	{
		GlobalData ogd = GlobalData.getObject();
		ArrayList<String> ticl = new ArrayList<String>();
		ticl = ogd.getIncompleteTasksList();
		for(int i=0;i<ticl.size();i++)
		{
			System.out.println("Expected task:"+ticl.get(i));
			t.putExpectedBugs(ticl.get(i));//in model
		}
		//JOptionPane.showMessageDialog(null, "Please look at help provided such as schematic, formula, etc., ");
		return "success";
	}
	
	public void checkStrategy(ArrayList<String> completedTasks)
	{
		GlobalData ogd = GlobalData.getObject();
		//OwlAccess o = OwlAccess.getObject();
		
		ogd.findStrategy(completedTasks);
		//get a list of completed tasks
		//loop through that list and see which strategy gets completed with given tasks
		//enter that in applied strategy
	}
	
	public String solutionExecute(String input)
	{
		
		InputStatement is = parse(input);
System.out.println("check inside solutionexecute"+is);
		ArrayList<String> completedTasks = new ArrayList<String>();
		OwlAccess o = OwlAccess.getObject();
		GlobalData ogd = GlobalData.getObject();
		if(is.returnType==0)//has all three, valid
		{
			float solvedPartial = checkpartialsub(is);
			System.out.println("Error code is"+solvedPartial);
			if(solvedPartial==-9989) {
				
			
				
				return "Success";
			}
			if(solvedPartial>-9989)
			{
				completedTasks=checkTaskCompleted(is);
				ogd.getCompletedTasks().addAll(completedTasks);
				if(checkinuete(is.lhs)) {
					updateuete(is);
				}
				else {
					passintouserete(is);
				}
				int flagpc = checkProblemComplete();
				if(flagpc==0)
				{
					//check strategy used
					System.out.println("inside problem complete:"+ogd.getCompletedTasks().size());
					checkStrategy(ogd.getCompletedTasks());
					predictBugs();
					o.addObjProperty(ogd.getUsername(), "completedProblems", ogd.getProblemUri());
					o.updateTimestamp(ogd.getProblemUri(), "solvingEndTime");
					o.closeModel(1);
					OwlAccess.refreshObject();
//					JOptionPane.showMessageDialog(null, "Well Done! You have succesfully completed the problem.");
					
                    ogd.setCompletedProblem(ogd.getProblemUri());
					JOptionPane.showMessageDialog(null, "Well Done! You have succesfully completed the problem.");
					
					return "Success";//problem complete
				}
				else
				{
					
					if(flagpc==1)
					{
						JOptionPane.showMessageDialog(null, "Please read the problem carefully\n and check whether you have found all answers for what has been asked to find in the problem. \nPlease complete the To Find entries and then continue solving the problem.");
					}
					return "Success";//problem not complete
				}
			}
			else
			{
				//Capture bug if present
				
				String querystring = "select ?b { " +
						"<http://www.owl-ontologies.com/MenDomOntology.owl#"+ogd.getProblemUri() +"> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s . " +
						"?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t . " +
						" ?t <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?hf . " +
						" ?hf <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r . " +
						" ?t <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?b ." +
							" FILTER (?r = \""  + is.lhs +"\""+
							")}";
				
				ArrayList<String> strBugList =  o.runQueryListwithoutreasoner(querystring);
				
				for (int i = 0; i < strBugList.size(); i++) {
					// check whether bug is already present
				

					strBugList.set(
							i,
							strBugList.get(i).substring(
									strBugList.get(i).indexOf("#") + 1,
									strBugList.get(i).indexOf(">")));
					
					o.addObjProperty(ogd.getUsername(), "capturedBugs",
							strBugList.get(i));
					o.updateOpenBugCount(strBugList.get(i));

				}
				if(solvedPartial==-9997)return "check formula";
				else if( solvedPartial==-9999)return "calculation mistake";
				return "Please check the statement";
			}
			//check partial sub of rhs
			//evaluate rhs
			//if evaluate returned error (from try), then (write further comments)
		}
		else if(is.returnType==-1)//No equals symbol
		{
			return "Entered statement does not have rhs";
		}
		else if(is.returnType==-2)//No unit
		{
			return "Entered statement does not have unit";
		}
		else if(is.returnType==-3)//No unit
		{
			return "Entered statement does not have unit";
		}
		return "Success";
	}
	
	public int checkProblemComplete()
	{
		//Interaction in = Interaction.getObject();
		int problemComplete=0;
		//Get each toFind from ontology and check if it has value in user ete
		//if true then finish problem solving mode
		GlobalData ogd = GlobalData.getObject();
		String puri = ogd.getProblemUri();
		String querystring = "select ?d where { " +
				"<http://www.owl-ontologies.com/MenDomOntology.owl#" + puri + "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasToFindExpression> ?f . "+
				"?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?tf . " +
				"?tf <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?d . " +
				"}";
		OwlAccess o = OwlAccess.getObject();
		ArrayList<String> toFinds = new ArrayList<String>();
		toFinds = o.runQueryList(querystring);
		System.out.println(toFinds.size());
		String querystring2 = "select ?d where { " +
				"<http://www.owl-ontologies.com/MenDomOntology.owl#" + puri + "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasToFindExpression> ?f . "+
				"?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?tf . " +
				"?tf <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?d . " +
				"}";
		ArrayList<String> toFinds2 = new ArrayList<String>();
		toFinds2 = o.runQueryList(querystring2);
		System.out.println("to find size debargha:"+toFinds2.size());
		for(int i=0; i<toFinds2.size(); i++)
		{
			toFinds.add(toFinds2.get(i));
		}
		System.out.println("toFinds size is: "+toFinds.size());
		for(int i=0; i<toFinds.size(); i++)
		{
			toFinds.set(i, toFinds.get(i).substring(toFinds.get(i).indexOf("\"")+1, toFinds.get(i).lastIndexOf("\"")));
			System.out.println(toFinds.get(i));
			System.out.println("getuet value"+getueteval(toFinds.get(i)));
			if (!getueteval(toFinds.get(i)).equals("NotFound")) {
				if (getueteval(toFinds.get(i)).equals("?")) {
                     
					problemComplete = 0;

				} else {
					problemComplete++;
				}
			}
		}
		//in.setLines(input);
		if(toFinds.size()==problemComplete&&toFinds.size()!=0)
		{
			return 0;
		}
		else if(problemComplete>0)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
	public String loadCurrentProblem()
	{
		System.out.println("lcp Function");
		GlobalData ogd = GlobalData.getObject();
		//ogd.loadCurrentProblem();
		System.out.println("lc problem "+ogd.getProblemUri());
		givenArea = t.getGivenArea();
		solutionArea = t.getSolutionArea();
		tofindArea = t.getToFindArea();
		problemType = ogd.getProblemTypeSelected();
		problem=ogd.getProblemDescOfUri(ogd.getProblemUri());
		problemImage+=ogd.getProblemImage(ogd.getProblemUri());
		System.out.println("Problem image in solving mode:"+problemImage);
		if(t.flag)
			displayFormula=t.fullformula;
		else
			displayFormula="";
		if(t.flag2==1)
		{
			displaySchema=t.fullschema;
			msg="schematic";
		}
		else if(t.flag2==2)
		{
//			displaySchema=t.fulldetails;
			displaySchema=t.fulldetails;
			msg="semantic";
		}
		else if(t.flag2==0)
		{
			displaySchema="";
		}
		return "success";
	}
	
	float checkpartialsub(InputStatement is)//set various error msgs for each type as -9999, -9998 and so on
	{
		String r=is.rhs;
		int z=0;
		
		String temp1="", temp2="";
		
		if(r.charAt(r.length()-1)=='*'||r.charAt(r.length()-1)=='+'||r.charAt(r.length()-1)=='/'||r.charAt(r.length()-1)=='-'||r.charAt(r.length()-1)=='(')
		{
			return -9998;//return error as last char is maths symbol
		}
		
		/*
		 Code for checking partial substitution
		 */
		
		
		for(int i=0;i<r.length();i++)
		{
			System.out.println("r at " + i + " is " + r.charAt(i));
			System.out.println("r value now is "+r);
			System.out.println("temp2 value now is "+temp2);
			if(r.charAt(i)=='(') {
				temp2=temp2.concat(Character.toString(r.charAt(i)));
				r=r.substring(i+1);
				i--;
			}
			else if(r.charAt(i)==')') {
				if(i==0) {
					temp2=temp2.concat(Character.toString(r.charAt(i)));
				}
				else {
					temp1=r.substring(0, i);
					try
					{
						Float.parseFloat(temp1);
						temp2=temp2.concat(temp1);
						temp2=temp2.concat(Character.toString(r.charAt(i)));
					}
					catch(Exception ex)
					{
						if(!checkinuete(temp1))
						{
							return -9997;
						}
						else
						{
							String res=getueteval(temp1);
							if(!res.equals("?"))
							{
								temp2=temp2.concat(res);
								temp2=temp2.concat(Character.toString(r.charAt(i)));
							}
							else
							{
								return -9996;//Value in to find that is not yet found has been used in RHS (well-formed)
							}
						}
					}
					r=r.substring(i+1);
					i=-1;
				}
			}
			else if(r.charAt(i)=='*'||r.charAt(i)=='+'||r.charAt(i)=='/'||r.charAt(i)=='-')
			{
				if(i==0) {
					temp2=temp2.concat(Character.toString(r.charAt(i)));
				}
				else {
					temp1=r.substring(0,i);
					try
					{
						Float.parseFloat(temp1);
						temp2=temp2.concat(temp1);
						temp2=temp2.concat(Character.toString(r.charAt(i)));
					}
					catch(Exception ex)
					{
						if(!checkinuete(temp1))
						{
							return -9997;
						}
						else
						{
							String res=getueteval(temp1);
							if(!res.equals("?"))
							{
								temp2=temp2.concat(res);
								temp2=temp2.concat(Character.toString(r.charAt(i)));
							}
							else
							{
								return -9996;//Value in to find that is not yet found has been used in RHS (well-formed)
							}
						}
					}
				}
				r=r.substring(i+1);
				i=-1;//
			}
			else if(i==r.length()-1)
			{
				temp1=r;
				try
				{
					Float.parseFloat(temp1);
					temp2=temp2.concat(temp1);
				}
				catch(Exception ex)
				{
					if(!checkinuete(temp1))
					{
						return -9997;
					}
					else
					{
						String res=getueteval(temp1);
						if(!res.equals("?"))
						{
							temp2=temp2.concat(res);
						}
						else
						{
							return -9996;//Value in to find that is not yet found has been used in RHS (well-formed)
						}
					}
				}
			}
		}
		//temp2=r;
		System.out.println("temp2 is" + temp2);
		//r=is.rhs;
		float solved=0;
		try
		{
			solved=evaluate(temp2);
			System.out.println("Before check unit");
			System.out.println("is.lhs = "+is.lhs+" value:"+getseteval(is.lhs));
			System.out.println(getseteunit(is.lhs) + is.unit + Float.parseFloat(getseteval(is.lhs)) + solved);
			if(checkUnit(getseteunit(is.lhs),is.unit,Float.parseFloat(getseteval(is.lhs)),solved)==1)
			{
				for(int i=0;i<temp2.length();i++)
				{
					if(temp2.charAt(i)=='*'||temp2.charAt(i)=='+'||temp2.charAt(i)=='/'||temp2.charAt(i)=='-')
					{
						return -9989;//not yet fully solved
					}
				}
				return solved;
			}
			else
			{if(!checkinuete(is.lhs))return -100000;
				return -9999;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return -9995;
		}
	}
	
	boolean checkinuete(String lhs)
	{
		for(Tables.UserEntityTableEntry uete : t.userEntityTable)
		{
			String shan = uete.getLhs();
			String []rahul = shan.split("of");
			rahul[0]=rahul[0].trim();


System.out.println("check for rahul[0]::"+rahul[0]+ lhs);
System.out.println("shaaaaaaannnnnnn" + uete +uete.getLhs());
			if(lhs.equalsIgnoreCase(uete.getLhs()) || lhs.equalsIgnoreCase(rahul[0]))
				{System.out.println("rrrrrrrr");return true;}
		}
		return false;
	}
	
	String getueteval(String lhs)
	{
		for(Tables.UserEntityTableEntry uete : t.userEntityTable)
		{String shan = uete.getLhs();
		String []rahul = shan.split("of");
		rahul[0]=rahul[0].trim();
			if(lhs.equalsIgnoreCase(uete.getLhs()) || lhs.equalsIgnoreCase(rahul[0])){
				return uete.getValue();
		}
		}
		return "NotFound";
	}
	
	String getueteunit(String lhs)
	{
		for(Tables.UserEntityTableEntry uete : t.userEntityTable)
		{
			if(lhs.equalsIgnoreCase(uete.getLhs())){
				return uete.getUnit();
			}
		}
		return "NotFound";
	}
	
	String getseteunit(String lhs)
	{
		for(Tables.SystemEntityTableEntry sete : t.systemEntityTable)
		{
			if(lhs.equalsIgnoreCase(sete.getLhs())){
				return sete.getUnit();
			}
		}
		return "NotFound";
	}
	
	String getseteval(String lhs)
	{
		for(Tables.SystemEntityTableEntry sete : t.systemEntityTable)
		{
			if(lhs.equalsIgnoreCase(sete.getLhs())){
				return sete.getValue();
			}
		}
		return "NotFound";
	}
	
	void updateuete(InputStatement is)
	{
		Tables.UserEntityTableEntry uete;
		for(int i=0;i<t.userEntityTable.size();i++)
		{
			uete=t.userEntityTable.get(i);
			if(is.lhs.equalsIgnoreCase(uete.getLhs()))
			{
				t.userEntityTable.get(i).setValue(is.rhs);
				t.userEntityTable.get(i).setUnit(is.unit);
			}
		}
	}
	
	public String fetchFormulae()
	{
		System.out.println("Fetching formulas");
		if(t.flag==true)
			t.flag=false;
		else
			t.flag=true;
		return "formulasuccess";
	}
	
	public String loadschematic()
	{
		System.out.println("Load Schematic");
		if(t.flag2==0)
			t.flag2=1;
		else
			t.flag2=0;
		return "formulasuccess";
	}
	
	public String loaddetails()
	{
		System.out.println("Load details");
		if(t.flag2==2)
			t.flag2=0;
		else
			t.flag2=2;
		return "formulasuccess";
	}
	
	float evaluate(String input)
	{
		System.out.println("In Eval " + input);
		float answer=-9999;
		try
		{
			answer=Float.parseFloat(input);
			System.out.println("try 1 answer "+answer);
			return answer;
		}
		catch(Exception ex)
		{
			System.out.println("catch 1");
		}
		try
		{
			answer=Float.parseFloat(evaluatorWithParanthesis(input));
		}
		catch(Exception ex)
		{
			System.out.println("catch 2");
		}
		return answer;
	}
	
	String evaluatorWithParanthesis(String input) {
		System.out.println("In ewp fn start " + input);
		input = input.replaceAll(" ", "");
		String tempstr="";
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		int endpos,startpos;
		try {
			while(input.indexOf("(")!=-1)
			{
				endpos=input.indexOf(")");
				tempstr=input.substring(0,endpos);
				startpos=tempstr.lastIndexOf("(");
				String out= input.substring(0,startpos);
				
			    String evalstr=input.substring(startpos+1,endpos);
			    evalstr = (engine.eval(evalstr).toString());
			    out=out+evalstr;
			    System.out.println("Endpos "+endpos);
			    out=out+input.substring(endpos+1);
			    input=out;
				
				
			}
			input=(engine.eval(input).toString());
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		int pos = input.indexOf('.');
		if(pos!=-1 && (pos+5)<input.length())
		{
			input = input.substring(0,pos+5);
			
		}
		System.out.println("In ewp fn end " + input);
		return input;
	}
	
	boolean checkuserete(String str)
	{
		
		for(Tables.UserEntityTableEntry uete : t.userEntityTable)
		{
			if(str.equalsIgnoreCase(uete.getLhs())||str.equals(uete.getVariableName()))
				return true;
		}
		return false;
	}

	public boolean checksysete(InputStatement is, int mode)
	{  int k=0;
		for(Tables.UserEntityTableEntry uete : t.userEntityTable)
	{k++;}
		System.out.println("k="+k);
		if(mode==2)//check only lhs
		{
			String lhsinp=is.lhs;
			
			 for(int i=0;i<t.systemEntityTable.size();i++)
			 {  				 System.out.println("-----------"+t.systemEntityTable.size()+"-----");
				 System.out.println("Sysete contents: " + t.systemEntityTable.get(i).getLhs() +" "+ t.systemEntityTable.get(i).getValue() + t.systemEntityTable.get(i).getUnit());
				 if (lhsinp.equalsIgnoreCase(t.systemEntityTable.get(i).getLhs()))
				 {
					return true;
				 }
			 }
		}
		else
		{     System.out.println("-----------"+t.systemEntityTable.size()+"-----");
			System.out.println("Input Statement is"+is.lhs + "input unit:"+is.unit);
			String lhsinp=is.lhs;
			String valinp=is.rhs;
			String unitinp=is.unit;
			 for(int i=0;i<t.systemEntityTable.size();i++)
			 {
				 System.out.println("Sysete contents: " + t.systemEntityTable.get(i).getLhs() + t.systemEntityTable.get(i).getValue() + t.systemEntityTable.get(i).getUnit());
				 if (lhsinp.equalsIgnoreCase(t.systemEntityTable.get(i).getLhs()) && (checkUnit(unitinp,t.systemEntityTable.get(i).getUnit(),Double.parseDouble(valinp),Double.parseDouble(t.systemEntityTable.get(i).getValue())))==1)
				 {
					return true;
				 }
			 }
		}
		return false;
	}

	public void passintouserete(InputStatement is){
		UserEntityTableEntry uet=t.new UserEntityTableEntry();
		uet.setLhs(is.lhs);
		uet.setValue(is.rhs);
		uet.setUnit(is.unit);
		t.userEntityTable.add(uet);
	}

	public String givenValidation()
	{
		
		GlobalData ogd=GlobalData.getObject();
		if(ogd.getProblemUri().equals(ogd.getCompletedProblem()))
		{
			JOptionPane.showMessageDialog(null, "You have completed this problem and to move to next problem.\n Go to Home and select another type of problem. ");
			return "success";
		}
		
		String res="";
		String []shan = givenText.split("of");
		String []shaan = givenText.split("Of");
		String []shann = givenText.split("OF");
		String []shannn = givenText.split("oF");
		if(shan.length!=2&&shaan.length!=2&&shann.length!=2&&shannn.length!=2){
			JOptionPane.showMessageDialog(null,shan[0] + " of what shape");
			return "success";
		}
		res = givenExecute(givenText);
		
		System.out.println(givenText+"rahul");
		
		
		
		
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		System.out.println("Debargha given count:"+ogd.getGivenCount());
		if(res.equals("Success"))
		{
			givenSuccess++;
			if(givenArea.equals(null))
			{
				givenArea=givenText+"\n";
				t.setGivenArea(givenArea);
			}
			else
			{
				givenArea=givenArea+givenText+"\n";
				t.setGivenArea(givenArea);
			}
			givenText="";
			if(givenSuccess==ogd.getGivenCount())
			{
				String querystring="select ?bugs  "+ 
						"{"+
						"<http://www.owl-ontologies.com/MenDomOntology.owl#"+ ogd.getProblemUri() +"> <http://www.owl-ontologies.com/MenDomOntology.owl#hasGivenExpression> ?ge ."+
						"?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?bugs }";

				ArrayList<String> results = o.runQueryList(querystring);

				for (String bug : results) {
					bug = bug.substring(bug.indexOf("#") + 1,
							bug.lastIndexOf(">"));
					String query1 = "select ?bugs {<http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ ogd.getUsername()
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#capturedBugs> ?bugs ."
							+ " FILTER (?bugs =  <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ bug + ">" + ")" + "}";

					ArrayList<String> res1 = o.runQueryList(query1);
					if (res1.size() > 0) {
						System.out.println("bugs matched");
						o.updateResolvedBugCount(bug);
					}

				}
				
			}
		}
		else
		{
			String querystring="select ?bugs  "+ 
					"{"+
					"<http://www.owl-ontologies.com/MenDomOntology.owl#"+ ogd.getProblemUri() +"> <http://www.owl-ontologies.com/MenDomOntology.owl#hasGivenExpression> ?ge ."+
					"?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?bugs }";
			
			ArrayList<String> strBugList = new ArrayList<String>();
			strBugList = o.runQueryList(querystring);
			for (int i = 0; i < strBugList.size(); i++) {
				// check whether bug is already present
				

				strBugList.set(
						i,
						strBugList.get(i).substring(
								strBugList.get(i).indexOf("#") + 1,
								strBugList.get(i).indexOf(">")));
				
				o.addObjProperty(ogd.getUsername(), "capturedBugs",
						strBugList.get(i));
				o.updateOpenBugCount(strBugList.get(i));

			}
			
			JOptionPane.showMessageDialog(null, res);
                
			
		}
		//JOptionPane.showMessageDialog(null, givenArea);
		return "success";
	}

	public String tofindValidation()
	{
		GlobalData ogd=GlobalData.getObject();
		 int k=0;
			for(Tables.UserEntityTableEntry uete : t.userEntityTable)
		{k++;}
		if(ogd.getGivenCount()!=k){JOptionPane.showMessageDialog(null, "Please check the given items");return "success";}
		if(ogd.getProblemUri().equals(ogd.getCompletedProblem()))
		{
			JOptionPane.showMessageDialog(null, "You have completed this problem and to move to next problem.\n Go to Home and select another type of problem. ");
			return "success";
		}
		 
		String res="";
		String []shan = tofindText.split("of");
		String []shaan = tofindText.split("Of");
		String []shann = tofindText.split("OF");
		String []shannn = tofindText.split("oF");
		if(shan.length!=2&&shaan.length!=2&&shann.length!=2&&shannn.length!=2){
			JOptionPane.showMessageDialog(null,shan[0] + " of what shape");
			return "success";
		}
		
		res = tofindExecute(tofindText);

		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		
		if(res.equals("Success"))
		{
			tofindArea=tofindArea+tofindText+"\n";
			t.setToFindArea(tofindArea);
			tofindText="";
			
			String querystring="select ?bugs  "+ 
					"{"+
					"<http://www.owl-ontologies.com/MenDomOntology.owl#"+ ogd.getProblemUri() +"> <http://www.owl-ontologies.com/MenDomOntology.owl#hasToFindExpression> ?ge ."+
					"?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?bugs }";

			ArrayList<String> results = o.runQueryList(querystring);

			for (String bug : results) {
				bug = bug.substring(bug.indexOf("#") + 1,
						bug.lastIndexOf(">"));
				String query1 = "select ?bugs {<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getUsername()
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#capturedBugs> ?bugs ."
						+ " FILTER (?bugs =  <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ bug + ">" + ")" + "}";

				ArrayList<String> res1 = o.runQueryList(query1);
				if (res1.size() > 0) {
					System.out.println("bugs matched");
					o.updateResolvedBugCount(bug);
				}

			}
		}
		else
		{
			String querystring="select ?bugs  "+ 
					"{"+
					"<http://www.owl-ontologies.com/MenDomOntology.owl#"+ ogd.getProblemUri() +"> <http://www.owl-ontologies.com/MenDomOntology.owl#hasToFindExpression> ?ge ."+
					"?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?bugs }";
			
			ArrayList<String> strBugList = new ArrayList<String>();
			strBugList = o.runQueryList(querystring);
			for (int i = 0; i < strBugList.size(); i++) {
				// check whether bug is already present
				

				strBugList.set(
						i,
						strBugList.get(i).substring(
								strBugList.get(i).indexOf("#") + 1,
								strBugList.get(i).indexOf(">")));
				
				o.addObjProperty(ogd.getUsername(), "capturedBugs",
						strBugList.get(i));
				o.updateOpenBugCount(strBugList.get(i));

			}
			
			JOptionPane.showMessageDialog(null, res);
		}
		//JOptionPane.showMessageDialog(null, tofindArea);
		return "success";
	}

	public String solutionValidation()
	{
		String res="";
		GlobalData ogd=GlobalData.getObject();
		if(ogd.getProblemUri().equals(ogd.getCompletedProblem()))
		{
			JOptionPane.showMessageDialog(null, "You have completed this problem and to move to next problem.\n Go to Home and select another type of problem. ");
			return "success";
		}
		
		
		System.out.println("rahul 1600");

		res = solutionExecute(solutionText);
		System.out.println("check::"+res);

		if(res.equals("Success"))
		{
System.out.println("check inside if");
			solutionArea=solutionArea+solutionText+"\n";
System.out.println("check check::"+solutionArea);
			t.setSolutionArea(solutionArea);
			solutionText="";
		}
		else if(res.equals("ProblemComplete"))
		{
			
			JOptionPane.showMessageDialog(null, res);
			return "success";
		}
		else
		{
			JOptionPane.showMessageDialog(null, res);
		}
		//JOptionPane.showMessageDialog(null, solutionArea);
		return "success";
	}

	public int checkUnit(String src, String dest, double srcValue, double destValue)
	{
	
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		if(src.equalsIgnoreCase(dest))
		{
			System.out.println("src: "+src+"dest: "+dest);
			if(srcValue==destValue)
				return 1;
			else
			{
				return 0;
			}
		}
		System.out.println("unit table size:"+t.unitTable.size());
		
		for(int i=0;i<t.unitTable.size();i++)
		{
			System.out.println("debargha "+t.unitTable.get(i).getSrc()+" "+t.unitTable.get(i).getDest()+" "+src+" "+dest);
			if(t.unitTable.get(i).getSrc().equalsIgnoreCase(src)&&t.unitTable.get(i).getDest().equalsIgnoreCase(dest))
			{
				System.out.println("In check unit: " + src + " " + dest + " " + srcValue + " " + destValue + " " + t.unitTable.get(i).getFormula());
				try
				{
					String input="";
					String tempFormula=t.unitTable.get(i).getFormula();
					input=tempFormula.replaceAll(dest, Double.toString(destValue));
					double answer;
					ScriptEngineManager mgr = new ScriptEngineManager();
				    ScriptEngine engine = mgr.getEngineByName("JavaScript");
				    System.out.println("In Check unit: " + input + " is the evaluated formula");
					answer=Double.parseDouble(engine.eval(input).toString());
					srcValue = Double.valueOf(twoDForm.format(srcValue));
					answer = Double.valueOf(twoDForm.format(answer));
					System.out.println("answer:"+answer+" srcvalue:"+srcValue);
					if(answer==srcValue)
					{
						return 1;
					}
					else
					{
						return 0;
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		return 0;
	}

/*class Task {
	String taskUri;
	boolean completedFlag;
};

class Strategy {
	String strategyUri;
	ArrayList<Task> taskList = new ArrayList<Task>();
};

ArrayList<Strategy> strategyList = new ArrayList<Strategy>();

public void checkStrategies()
{
	OwlAccess o = OwlAccess.getObject();
	String queryString1 = "select ?p COUNT(?s) "+ 
			"{"+
			"?p <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#Problem> ."+
			"?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#Strategy> ."+
			"?p <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s ."+
			"}";
	//get number of strategies
	ArrayList<String> templist1 = o.runQueryList(queryString1);
	String queryString2 = "select ?s COUNT(?t) "+ 
			"{"+
			"?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#Strategy> ."+
			"?t <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#Task> ."+
			"?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t ."+
			"} group by ?s";
	//get number of tasks per strategy
	ArrayList<String> templist2 = o.runQueryList(queryString2);
	//
}*/

}
