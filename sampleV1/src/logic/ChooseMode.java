package logic;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import dataAccess.OwlAccess;
import datastructures.GlobalData;
import datastructures.Tables;
import datastructures.Tables.UserEntityTableEntry;

public class ChooseMode {
	
	String problemString;
	String selectedProblemType;
	String problemImage="images/";
	
	public String getProblemImage() {
		return problemImage;
	}

	public void setProblemImage(String problemImage) {
		this.problemImage = problemImage;
	}

	public String getSelectedProblemType() {
		return selectedProblemType;
	}

	public void setSelectedProblemType(String selectedProblemType) {
		this.selectedProblemType = selectedProblemType;
	}

	public String getProblemString() {
		return problemString;
	}

	public void setProblemString(String problemString) {
		this.problemString = problemString;
	}

	public String loadLearningMode()
	{
		GlobalData ogd = GlobalData.getObject();
		OwlAccess o = OwlAccess.getObject();
		ogd.setConcepts("");
		//ogd.loadQandA(ogd.getProblemObjOfUri(ogd.getProblemUri()));
		try
		{
		//DatabaseStore store = DatabaseStore.getStore();
			String query = "select ?learning { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ogd.getProblemUri()
					+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#lerningStartTime> ?learning } ";
					
		
		ArrayList<String> res = o.runQueryList(query);
		
		
			if(res.size()==0)
			{
			   
				System.out.println("here iside addtime");
				o.updateTimestamp(ogd.getProblemUri(), "learningStartTime");
			}
		
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "success";
	}
	
	public String loadSolvingMode() throws IOException
	{
		Tables t = Tables.getObject();
		GlobalData ogd = GlobalData.getObject();
		OwlAccess o =OwlAccess.getObject();
		ogd.setCompletedProblem("");
		t.loadSystemete();
		t.loadGiven();
		t.loadTasks();
		t.loadUnitTable();
		t.loadSchemaStructure();
		t.loadFormula();
		t.setGivenArea("");
		t.setSolutionArea("");
		t.setToFindArea("");
		
		ArrayList<UserEntityTableEntry> userEntityTable = new ArrayList<UserEntityTableEntry>();
		t.userEntityTable = userEntityTable;
		ArrayList<String> completedTasks = new ArrayList<String>();
		ogd.setCompletedTasks(completedTasks);
		try
		{
			String query = "select ?solving { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ogd.getProblemUri()
					+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#solvingStartTime> ?solving } ";
					
		
		ArrayList<String> res = o.runQueryList(query);
		
		
			if(res.size()==0)
			{
				System.out.println("here iside addtime solving");
				o.updateTimestamp(ogd.getProblemUri(), "solvingStartTime");
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//ogd.loadIncompleteTasks();
		
		
		
		return "success";
	}
	
	public String loadSelectedProblem()
	{
		GlobalData ogd = GlobalData.getObject();
		ogd.loadAllProblemsUsingTypeString(ogd.getProblemTypeSelected());
		ogd.loadCurrentProblem(ogd.getSelectedDifficulty());
		//ogd.loadIncompleteTasks();
		System.out.println("Prob uri: "+ogd.getProblemUri());
		problemString = ogd.getProblemDescOfUri(ogd.getProblemUri());
		problemImage+= ogd.getProblemImage(ogd.getProblemUri());
		System.out.println("problem Image:"+problemImage);
		selectedProblemType = ogd.getProblemTypeSelected();
		return "success";
	}
}
