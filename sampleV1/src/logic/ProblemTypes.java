package logic;

import java.util.ArrayList;
import java.util.Collections;

import datastructures.GlobalData;

public class ProblemTypes {
	
	ArrayList<String> ProblemTypeList;
	String selectedProblemType="CoveringProblem";
	String selectedlevel="easy";
	ArrayList<String> levels;
	
	public ProblemTypes()
	{

	}

	public String getSelectedlevel() {
		return selectedlevel;
	}

	public void setSelectedlevel(String selectedlevel) {
		this.selectedlevel = selectedlevel;
	}

	public ArrayList<String> getProblemTypeList() {
		return ProblemTypeList;
	}

	public void setProblemTypeList(ArrayList<String> tempProblemTypeList) {
		ProblemTypeList = tempProblemTypeList;
	}

	public String getSelectedProblemType() {
		return selectedProblemType;
	}

	public void setSelectedProblemType(String tempSelectedProblemType) {
		selectedProblemType = tempSelectedProblemType;
	}

	public String loadProblemTypes()
	{
		GlobalData ogd = GlobalData.getObject();
		ArrayList<String> tempProblemTypeList = new ArrayList<String>();
		ogd.loadAllProblemTypes();
		for(int i=0;i<ogd.getProblemTypeList().size();i++)
		{
			tempProblemTypeList.add(ogd.getProblemTypeList().get(i).getURI());
		}
		Collections.sort(tempProblemTypeList);
		setProblemTypeList(tempProblemTypeList);
		ogd.loadLevels();
		setLevels(ogd.getDifficultyLevels());
		System.out.println("size is: "+ProblemTypeList.size());
		return "success";
	}
	
	public ArrayList<String> getLevels() {
		return levels;
	}

	public void setLevels(ArrayList<String> levels) {
		this.levels = levels;
	}

	public String selectedType()
	{
		GlobalData ogd = GlobalData.getObject();
		ogd.setProblemTypeSelected(selectedProblemType);
		ogd.setSelectedDifficulty(selectedlevel);
		return "success";
	}
}
