package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataAccess.OwlAccess;
import dataAccess.RDFStoreAccess;

public class GlobalData {
	String username;
	String password;
	int givenCount = 0;
	ArrayList<String> completedTasks = new ArrayList<String>();
    String concepts = "";
    String completedProblem="";
  //  private HttpRequest request;
    
    
	public String getCompletedProblem() {
		return completedProblem;
	}

	public void setCompletedProblem(String completedProblem) {
		this.completedProblem = completedProblem;
	}

	public String getConcepts() {
		return concepts;
	}

	public void setConcepts(String concepts) {
		this.concepts = concepts;
	}

	public ArrayList<String> getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(ArrayList<String> completedTasks) {
		this.completedTasks = completedTasks;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGivenCount() {
		return givenCount;
	}

	public void setGivenCount(int givenCount) {
		this.givenCount = givenCount;
	}

	public static GlobalData getObjGlobalData() {
		return objGlobalData;
	}

	public static void setObjGlobalData(GlobalData objGlobalData) {
		GlobalData.objGlobalData = objGlobalData;
	}

	public static void refreshObject() {
		objGlobalData = new GlobalData();
	}

	public class ProblemType {
		String URI;

		public String getURI() {
			return URI;
		}

		public void setURI(String uRI) {
			URI = uRI;
		}

		public ArrayList<Problem> getProblemList() {
			return problemList;
		}

		public void setProblemList(ArrayList<Problem> problemList) {
			this.problemList = problemList;
		}

		ArrayList<Problem> problemList = new ArrayList<Problem>();
	}

	String selectedDifficulty;

	public String getSelectedDifficulty() {
		return selectedDifficulty;
	}

	public void setSelectedDifficulty(String selectedDifficulty) {
		this.selectedDifficulty = selectedDifficulty;
	}

	public class Problem {
		String URI;
		String ProblemString;
		ArrayList<Question> questionList = new ArrayList<Question>();
	}

	public class Question {
		String URI;
		ArrayList<Answer> Options = new ArrayList<Answer>();
		String QuestionString;
	}

	public class Answer {
		String URI;
		String AnswerString;// should make this as array list for ease?
		String feedback;
	}

	ArrayList<ProblemType> problemTypeList = new ArrayList<ProblemType>();
	ArrayList<String> listofCompletedQuestions = new ArrayList<String>();
	ArrayList<String> incompleteTasksList = new ArrayList<String>();
	ArrayList<String> difficultyLevels = new ArrayList<String>();

	public ArrayList<String> getDifficultyLevels() {
		return difficultyLevels;
	}

	public void setDifficultyLevels(ArrayList<String> difficultyLevels) {
		this.difficultyLevels = difficultyLevels;
	}

	public void loadLevels() {
		difficultyLevels.clear();
		difficultyLevels.add("easy");
		difficultyLevels.add("medium");
		difficultyLevels.add("hard");
	}

	public ArrayList<String> getListofCompletedQuestions() {
		return listofCompletedQuestions;
	}

	public void setListofCompletedQuestions(
			ArrayList<String> listofCompletedQuestions) {
		this.listofCompletedQuestions = listofCompletedQuestions;
	}

	public ArrayList<String> getIncompleteTasksList() {
		return incompleteTasksList;
	}

	public void setIncompleteTasksList(ArrayList<String> incompleteTasksList) {
		this.incompleteTasksList = incompleteTasksList;
	}

	String problemTypeSelected;
	String problemUri;
	String firstUri;
	String currentQuestionUri;

	public String getProblemDescOfUri(String URI)// assuming it is of chosen
													// problem type
	{
		/*
		 * for(int i=0;i<problemTypeList.size();i++) {
		 * if(problemTypeList.get(i).URI.equals(problemTypeSelected)) { for(int
		 * j=0;j<problemTypeList.get(i).problemList.size();j++) {
		 * if(problemTypeList.get(i).problemList.get(j).URI.equals(URI)) {
		 * return problemTypeList.get(i).problemList.get(j).ProblemString; } } }
		 * }
		 */
		/* Changed */
		String querystring2 = "select ?description where { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ URI
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?description}";
		OwlAccess o = OwlAccess.getObject();
		ArrayList<String> list = o.runQueryList(querystring2);
		if (list.size() > 0) {
			return list.get(0).substring(list.get(0).indexOf("=") + 3,
					list.get(0).indexOf("^") - 1);
		}

		return "NotFound";
	}
	
	public String getProblemImage(String URI)
	{
		
		String querystring2 = "select ?image where { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ URI
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasImage> ?image}";
		OwlAccess o = OwlAccess.getObject();
		ArrayList<String> list = o.runQueryList(querystring2);
		if (list.size() > 0) {
			return list.get(0).substring(list.get(0).indexOf("=") + 3,
					list.get(0).indexOf("^") - 1);
		}

		return "NotFound";
		
	}

	public Problem getProblemObjOfUri(String URI)// assuming it is of chosen
													// problem type
	{
		Problem objproblem = new Problem();
		for (int i = 0; i < problemTypeList.size(); i++) {
			if (problemTypeList.get(i).URI.equals(problemTypeSelected)) {
				for (int j = 0; j < problemTypeList.get(i).problemList.size(); j++) {
					if (problemTypeList.get(i).problemList.get(j).URI
							.equals(URI)) {
						objproblem = problemTypeList.get(i).problemList.get(j);
						return objproblem;
					}
				}
			}
		}
		return objproblem;
	}

	public String getQuestionDescOfUri(String URI)// assuming it is of chosen
													// problem type
	{
		/*
		 * for(int i=0;i<problemTypeList.size();i++) {
		 * //System.out.println("Problem type selected is"+problemTypeSelected);
		 * if(problemTypeList.get(i).URI.equals(problemTypeSelected)) { for(int
		 * j=0;j<problemTypeList.get(i).problemList.size();j++) {
		 * //System.out.println("Problem uri is"+problemUri);
		 * if(problemTypeList.get(i).problemList.get(j).URI.equals(problemUri))
		 * {
		 * //System.out.println("size is "+problemTypeList.get(i).problemList.get
		 * (j).questionList.size()); for(int
		 * k=0;k<problemTypeList.get(i).problemList
		 * .get(j).questionList.size();k++) {
		 * System.out.println("Question URI in ogd is"
		 * +problemTypeList.get(i).problemList.get(j).questionList.get(k).URI);
		 * System.out.println("Other URI is"+URI);
		 * if(problemTypeList.get(i).problemList
		 * .get(j).questionList.get(k).URI.equals(URI)) { return
		 * problemTypeList.
		 * get(i).problemList.get(j).questionList.get(k).QuestionString; } } } }
		 * } }
		 */
		/* Changed */
		String querystring2 = "select ?description where { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ URI
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?description}";
		OwlAccess o = OwlAccess.getObject();
		ArrayList<String> list = o.runQueryList(querystring2);
		if (list.size() > 0) {
			return list.get(0).substring(list.get(0).indexOf("=") + 3,
					list.get(0).indexOf("^") - 1);
		}

		return "NotFound";
	}

	public String getAnswerDescOfUri(String URI)// assuming it is of chosen
												// problem type and is of
												// current problem
	{
		for (int i = 0; i < problemTypeList.size(); i++) {
			if (problemTypeList.get(i).URI.equals(problemTypeSelected)) {
				for (int j = 0; j < problemTypeList.get(i).problemList.size(); j++) {
					if (problemTypeList.get(i).problemList.get(j).URI
							.equals(problemUri)) {
						for (int k = 0; k < problemTypeList.get(i).problemList
								.get(j).questionList.size(); k++) {
							if (problemTypeList.get(i).problemList.get(j).questionList
									.get(k).URI.equals(currentQuestionUri)) {
								for (int l = 0; l < problemTypeList.get(i).problemList
										.get(j).questionList.get(k).Options
										.size(); l++) {
									if (problemTypeList.get(i).problemList
											.get(j).questionList.get(k).Options
											.get(l).URI.equals(URI)) {
										return problemTypeList.get(i).problemList
												.get(j).questionList.get(k).Options
												.get(l).AnswerString;
									}
								}
							}
						}
					}
				}
			}
		}
		return "NotFound";
	}

	public Map<String, String> getAnswerListOfQuestionURI(String URI)// assuming
																			// it
																			// is
																			// of
																			// chosen
																			// problem
																			// type
																			// and
																			// is
																			// of
																			// current
																			// problem
	{
		ArrayList<String> tempResult = new ArrayList<String>();
		// tempResult.clear();
		/*
		 * for(int i=0;i<problemTypeList.size();i++) {
		 * if(problemTypeList.get(i).URI.equals(problemTypeSelected)) { for(int
		 * j=0;j<problemTypeList.get(i).problemList.size();j++) {
		 * if(problemTypeList.get(i).problemList.get(j).URI.equals(problemUri))
		 * { for(int
		 * k=0;k<problemTypeList.get(i).problemList.get(j).questionList
		 * .size();k++) {
		 * if(problemTypeList.get(i).problemList.get(j).questionList
		 * .get(k).URI.equals(URI)) { for(int
		 * l=0;l<problemTypeList.get(i).problemList
		 * .get(j).questionList.get(k).Options.size();l++) {
		 * 
		 * tempResult.add(problemTypeList.get(i).problemList.get(j).questionList.
		 * get(k).Options.get(l).AnswerString); } } } } } } }
		 */
		/* Changed */
		OwlAccess o = OwlAccess.getObject();
		Map<String, String> choices = new TreeMap<String, String>();
		ArrayList<String> answerUriList = o
				.runQueryList("select ?ans { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ URI
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasOption> ?ans . } ");

		
		Collections.sort(answerUriList); 
		for (String ans : answerUriList) {
			ans = ans.substring(ans.indexOf("#") + 1, ans.indexOf(">"));
			String description = "";

			String query = "select ?answer { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ans
					+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasDescription> ?desc .  "
					+ "?desc <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?answer }";

			ArrayList<String> descrlist = o.runQueryList(query);

		
				for (String desc : descrlist) {
					System.out.println("answer description :"+desc);
					desc = desc.substring(desc.indexOf("=") + 3,
							desc.indexOf("^") - 1);
					description += desc + ",";
				}

				/*description = description
						.substring(0, description.length() - 1);*/

				//choices.put(ans, description);
			

			
				query = "select ?answer { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ans
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasDescription> ?desc .  "
						+ "?desc <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?answer }";

				descrlist = o.runQueryList(query);
				for (String desc : descrlist) {
					System.out.println("answer description :"+desc);
					desc = desc.substring(desc.indexOf("=") + 3,
							desc.indexOf("^") - 1);
					description += desc + ",";
				}

				description = description
						.substring(0, description.length() - 1);

				choices.put(ans, description);

			

		}

		return choices;
	}

	public String getAnswerFeedbackOfUri(String URI)// assuming it is of chosen
													// problem type and is of
													// current problem
	{
		for (int i = 0; i < problemTypeList.size(); i++) {
			if (problemTypeList.get(i).URI.equals(problemTypeSelected)) {
				for (int j = 0; j < problemTypeList.get(i).problemList.size(); j++) {
					if (problemTypeList.get(i).problemList.get(j).URI
							.equals(problemUri)) {
						for (int k = 0; k < problemTypeList.get(i).problemList
								.get(j).questionList.size(); k++) {
							if (problemTypeList.get(i).problemList.get(j).questionList
									.get(k).URI.equals(currentQuestionUri)) {
								for (int l = 0; l < problemTypeList.get(i).problemList
										.get(j).questionList.get(k).Options
										.size(); l++) {
									if (problemTypeList.get(i).problemList
											.get(j).questionList.get(k).Options
											.get(l).URI.equals(URI)) {
										return problemTypeList.get(i).problemList
												.get(j).questionList.get(k).Options
												.get(l).feedback;
									}
								}
							}
						}
					}
				}
			}
		}
		return "NotFound";
	}

	public String getUriOfAnswerDesc(String Desc) {
		/*
		 * for(int i=0;i<problemTypeList.size();i++) {
		 * if(problemTypeList.get(i).URI.equals(problemTypeSelected)) { for(int
		 * j=0;j<problemTypeList.get(i).problemList.size();j++) {
		 * if(problemTypeList.get(i).problemList.get(j).URI.equals(problemUri))
		 * { for(int
		 * k=0;k<problemTypeList.get(i).problemList.get(j).questionList
		 * .size();k++) {
		 * if(problemTypeList.get(i).problemList.get(j).questionList
		 * .get(k).URI.equals(currentQuestionUri)) { for(int
		 * l=0;l<problemTypeList
		 * .get(i).problemList.get(j).questionList.get(k).Options.size();l++) {
		 * if
		 * (problemTypeList.get(i).problemList.get(j).questionList.get(k).Options
		 * .get(l).AnswerString.equals(Desc)) { return
		 * problemTypeList.get(i).problemList
		 * .get(j).questionList.get(k).Options.get(l).URI; } } } } } } } }
		 */

		return "NotFound";
	}

	public String getUriOfProblemDesc(String Desc) {
		for (int i = 0; i < problemTypeList.size(); i++) {
			if (problemTypeList.get(i).URI.equals(problemTypeSelected)) {
				for (int j = 0; j < problemTypeList.get(i).problemList.size(); j++) {
					if (problemTypeList.get(i).problemList.get(j).ProblemString
							.equals(Desc)) {
						return problemTypeList.get(i).problemList.get(j).URI;
					}
				}
			}
		}
		return "NotFound";
	}

	public void loadAllProblemTypes() {
		problemTypeList.clear();
		ArrayList<String> problemTypeStringList = new ArrayList<String>();
		// Change to store access

		RDFStoreAccess store = RDFStoreAccess.getObject();

		problemTypeStringList = store
				.runQueryList("select ?probtype { "
						+ "?probtype <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://www.owl-ontologies.com/MenDomOntology.owl#Problem> ."
						+ "}");

		for (int i = 0; i < problemTypeStringList.size(); i++) {
			if (problemTypeStringList.get(i).equals(
					"( ?probtype = owl:Nothing )")) {
				// do nothing ignore owl:nothing class
			} else {
				ProblemType tempProblemType = new ProblemType();
				String problemTypeString = problemTypeStringList.get(i)
						.substring(0,
								problemTypeStringList.get(i).lastIndexOf(">"));
				System.out.println("Problem type string:" + problemTypeString);
				tempProblemType.URI = problemTypeString.substring(
						problemTypeString.indexOf("#") + 1,
						problemTypeString.lastIndexOf(">"));
				problemTypeList.add(tempProblemType);
			}
		}
	}

	public void loadCurrentProblem(String difficulty) {// Gets URI of problems
														// of current user of
														// selected type and are
														// not yet completed
		String firstAvailableProblem = "";
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		try {
			firstAvailableProblem = o
					.runQueryList(
							"select ?p { "
									+ "?p <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
									+ getProblemTypeSelected()
									+ "> . "
									+ "?p <http://www.owl-ontologies.com/MenDomOntology.owl#difficulty> \""
									+ difficulty
									+ "\"^^<http://www.w3.org/2001/XMLSchema#string> . "
									+ "optional "
									+ "{ "
									+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
									+ getUsername()
									+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?cp . "
									+ "filter(sameterm(?p, ?cp)) " + "} "
									+ "filter(!bound(?cp)) " + "}").get(0);
			problemUri = firstAvailableProblem.substring(
					firstAvailableProblem.indexOf("#") + 1,
					firstAvailableProblem.indexOf(">"));
		} catch (Exception ex) {
			firstAvailableProblem = "NotFound";
			problemUri = "NotFound";
		}
	}

	public void loadAllProblems(ProblemType objProblemType) {// All problems of
																// a given type
		ArrayList<String> ProblemsList = new ArrayList<String>();
		objProblemType.problemList.clear();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		ProblemsList = o
				.runQueryList("select ?p ?disc { "
						+ "?p <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ objProblemType.URI
						+ "> . "
						+ "?p <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?disc . "
						+ "}");
		for (int i = 0; i < problemTypeList.size(); i++) {
			if (problemTypeList.get(i).URI.equals(objProblemType.URI)) {
				for (int j = 0; j < ProblemsList.size(); j++) {
					Problem tempProblem = new Problem();
					tempProblem.URI = ProblemsList.get(j).substring(
							ProblemsList.get(j).indexOf("#") + 1,
							ProblemsList.get(j).indexOf(">"));
					ProblemsList.set(
							j,
							ProblemsList.get(j).substring(
									ProblemsList.get(j).indexOf("?disc")));
					tempProblem.ProblemString = ProblemsList.get(j).substring(
							ProblemsList.get(j).indexOf("\"") + 1,
							ProblemsList.get(j).indexOf("\""));
					problemTypeList.get(i).problemList.add(tempProblem);
				}
			}
		}
	}

	public void loadAllProblemsUsingTypeString(String ProblemTypeUri) {// All
																		// problems
																		// of a
																		// given
																		// type
		ArrayList<String> ProblemsList = new ArrayList<String>();
		// dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		RDFStoreAccess store = RDFStoreAccess.getObject();
		ProblemsList = store
				.runQueryList("select ?p ?disc { "
						+ "?p <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ProblemTypeUri
						+ "> . "
						+ "?p <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?disc . "
						+ "}");

		System.out.println(ProblemsList.size());
		for (int i = 0; i < problemTypeList.size(); i++) {
			System.out.println("problemtypeuri of ogd "
					+ problemTypeList.get(i).URI);
			if (problemTypeList.get(i).URI.equals(ProblemTypeUri)) {
				problemTypeList.get(i).problemList.clear();
				System.out
						.println("Both strings are same and resultset size is "
								+ ProblemsList.size());
				for (int j = 0; j < ProblemsList.size(); j++) {
					System.out.println(ProblemsList.get(j));
					System.out
							.println("Adding; problem objects to chosen type");
					Problem tempProblem = new Problem();
					tempProblem.URI = ProblemsList.get(j).substring(
							ProblemsList.get(j).indexOf("#") + 1,
							ProblemsList.get(j).indexOf(">"));
					tempProblem.ProblemString = ProblemsList.get(j).substring(
							ProblemsList.get(j).indexOf("\"") + 1,
							ProblemsList.get(j).lastIndexOf("\""));
					System.out.println("uri:" + tempProblem.URI
							+ " Problem string:" + tempProblem.ProblemString);

					problemTypeList.get(i).problemList.add(tempProblem);
				}
				System.out.println("Size after loading is "
						+ problemTypeList.get(i).problemList.size());
			}
		}
	}

	public void deleteFromIncompleteTasks(String strTask) {
		for (int i = 0; i < incompleteTasksList.size(); i++) {
			if (incompleteTasksList.get(i).equals(strTask)) {
				incompleteTasksList.remove(i);
				i--;
			}
		}
	}

	public void loadIncompleteTasks() {
		ArrayList<String> tempIncompleteTasksList = new ArrayList<String>();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		RDFStoreAccess store = RDFStoreAccess.getObject();
		tempIncompleteTasksList = store
				.runQueryList("select ?t { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problemUri
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s . "
						+ "?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t . "
						+ "}");
		for (int i = 0; i < tempIncompleteTasksList.size(); i++) {
			tempIncompleteTasksList.set(
					i,
					tempIncompleteTasksList.get(i).substring(
							tempIncompleteTasksList.get(i).indexOf("#") + 1,
							tempIncompleteTasksList.get(i).indexOf(">")));
			o.addObjProperty(getUsername(), "IncompleteTasks",
					tempIncompleteTasksList.get(i));

			// store.addTriple(getUsername(), "IncompleteTasks",
			// tempIncompleteTasksList.get(i));
		}
		incompleteTasksList = tempIncompleteTasksList;
		System.out.println("Incomplete tasks are:" + incompleteTasksList);
	}

	public ArrayList<String> loadCompleteTasks() {
		ArrayList<String> tempCompleteTasksList = new ArrayList<String>();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		tempCompleteTasksList = o
				.runQueryList("select ?ct { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ username
						+ "> <http://www.w3.org/1999/02/22-rdf-syntax-ns#completedTasks> ?ct . "
						+ "}");
		for (int i = 0; i < tempCompleteTasksList.size(); i++) {
			tempCompleteTasksList.set(
					i,
					tempCompleteTasksList.get(i).substring(
							tempCompleteTasksList.get(i).indexOf("#") + 1,
							tempCompleteTasksList.get(i).indexOf(">")));
		}
		return tempCompleteTasksList;
	}

	public void findStrategy(ArrayList<String> compTasks)// not completed
	{
		String querystring = "";
		OwlAccess o = OwlAccess.getObject();
		ArrayList<String> resultStrategy = new ArrayList<String>();
		ArrayList<String> resultIncomplete = new ArrayList<String>();
		System.out.println("inside find strategy:" + compTasks.get(0));
		ArrayList<String> incompletetasks = new ArrayList<String>();
		if (compTasks.size() > 0) {
			for (int i = 0; i < compTasks.size(); i++) {
				querystring = "select ?s { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problemUri
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s . "
						+ "?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ compTasks.get(i) + "> . " + "}";

				System.out.println("inside find strategy:" + querystring);

				resultStrategy = o.runQueryList(querystring);
				
			
				if (resultStrategy.size() > 0) {
					String strategyuri = resultStrategy.get(0).substring(
							resultStrategy.get(0).indexOf("#") + 1,
							resultStrategy.get(0).indexOf(">"));
					System.out.println("strategy uri:" + strategyuri);
					
					o.addObjProperty(getUsername(), "appliedStrategy",
							strategyuri);

					querystring = "select ?t { "
							+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ problemUri
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ strategyuri
							+ "> . "
							+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ strategyuri
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask>  ?t . "
							+ "}";

					resultIncomplete = o.runQueryList(querystring);

					System.out.println("inside find strategy:" + strategyuri
							+ " " + resultIncomplete.size());
					for (String tasks : resultIncomplete) {
						tasks = tasks.substring(tasks.indexOf("#") + 1,
								tasks.indexOf(">"));
						if (!compTasks.contains(tasks)) {
							System.out
									.println("Incomplete tasks inside find strategy:"
											+ tasks);
							incompletetasks.add(tasks);
							o.addObjProperty(getUsername(), "IncompleteTasks",
									tasks);
						}
					}
					this.incompleteTasksList = incompletetasks;

					break;

				}
				// write logic here
			}
		}

	}

	public void loadFirstQuestion() {
		String firstQuest;
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		firstQuest = o
				.runQueryList(
						"select ?ques ?disc { "
								+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
								+ getUsername()
								+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCurrentProblem> ?prob . "
								+ "?prob <http://www.owl-ontologies.com/MenDomOntology.owl#hasQuestion> ?ques . "
								+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#isFirst> true . "
								+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?disc . "
								+ "}").get(0);
		firstUri = firstQuest.substring(firstQuest.indexOf("#") + 1,
				firstQuest.lastIndexOf(">"));
		System.out.println("First question is getting set here"+firstUri);
	}

	public void loadCurrentQuestion() {
		ArrayList<String> currentQuestionList = new ArrayList<String>();
		GlobalData ogd = GlobalData.getObject();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		currentQuestionList = o
				.runQueryList("select ?ques ?disc { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ getUsername()
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCurrentQuestion> ?ques . "
						+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?disc ."
						+ "}");
		if (currentQuestionList.size() != 0) {
			currentQuestionUri = currentQuestionList.get(0).substring(
					currentQuestionList.get(0).indexOf("#") + 1,
					currentQuestionList.get(0).lastIndexOf(">"));
			
			String query = "select ?ques  { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getProblemUri()
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasQuestion> ?ques . "
						
						+ "}";
			
			ArrayList<String> questionlist =  o.runQueryList(query);
			boolean flag=true;
			for(String question:questionlist)
			{
				question = question.substring(
						question.indexOf("#") + 1,
						question.lastIndexOf(">"));
				System.out.println("inside check debargha:"+question+" "+currentQuestionUri);
				if(question.equals(currentQuestionUri))
				{
					flag = false;
				}
				
			}
			
			
			if(flag)
			{
				currentQuestionUri = firstUri;
				o.removeProperty("isCurrentQuestion", getUsername());
				o.addObjProperty(getUsername(), "isCurrentQuestion", getFirstUri());
			}
			
			
		} else {
			currentQuestionUri = firstUri;
			o.addObjProperty(getUsername(), "isCurrentQuestion", getFirstUri());
		}
	}

	/*
	 * public ArrayList<String> getListofCompletedQues(){
	 * listofCompletedQuestions = new ArrayList<String>();
	 * listofCompletedQuestions = o.runQueryList("select ?ques { " +
	 * "<http://www.owl-ontologies.com/MenDomOntology.owl#" + getUsername() +
	 * "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCompletedQuestion> ?ques . "
	 * + "}"); return listofCompletedQuestions; }
	 */

	public void showAll() {
		for (int i = 0; i < problemTypeList.size(); i++) {
			System.out.println("Type is " + problemTypeList.get(i).URI);
			for (int j = 0; j < problemTypeList.get(i).problemList.size(); j++) {
				System.out.println("Problem is "
						+ problemTypeList.get(i).problemList.get(j).URI);
				for (int k = 0; k < problemTypeList.get(i).problemList.get(j).questionList
						.size(); k++) {
					System.out
							.println("Question is "
									+ problemTypeList.get(i).problemList.get(j).questionList
											.get(k).URI);
					for (int l = 0; l < problemTypeList.get(i).problemList
							.get(j).questionList.get(k).Options.size(); l++) {
						System.out
								.println("Answer URI is "
										+ problemTypeList.get(i).problemList
												.get(j).questionList.get(k).Options
												.get(l).URI);
						System.out
								.println("Answer String is "
										+ problemTypeList.get(i).problemList
												.get(j).questionList.get(k).Options
												.get(l).AnswerString);
					}
				}
			}
		}
	}

	public void loadAllQuestions(Problem objProblem) {
		ArrayList<String> QuestionList = new ArrayList<String>();
		objProblem.questionList.clear();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		QuestionList = o
				.runQueryList("select ?ques ?disc { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problemUri
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasQuestion> ?ques . "
						+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?disc ."
						+ "}");
		if (QuestionList.size() == 0) {
			// this problem has no questions?
		} else {
			for (int i = 0; i < QuestionList.size(); i++) {
				Question tempQuestion = new Question();
				tempQuestion.URI = QuestionList.get(i).substring(
						QuestionList.get(i).indexOf("#") + 1,
						QuestionList.get(i).lastIndexOf(">"));
				System.out.println("Tempquestion URI: " + tempQuestion.URI);
				tempQuestion.QuestionString = QuestionList.get(i).substring(
						QuestionList.get(i).indexOf("\"") + 1,
						QuestionList.get(i).lastIndexOf("\""));
				System.out.println("Tempquestion string: "
						+ tempQuestion.QuestionString);
				objProblem.questionList.add(tempQuestion);
			}
		}
	}

	public void loadChoicesUri(Question objQuestion) {
		ArrayList<String> answerUriList = new ArrayList<String>();
		objQuestion.Options.clear();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		// RDFStoreAccess rdf = RDFStoreAccess.getObject();
		answerUriList = o
				.runQueryList("select ?ans { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ objQuestion.URI
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasOption> ?ans . "
						+ "}");
		objQuestion.Options.clear();
		for (int i = 0; i < answerUriList.size(); i++) {
			answerUriList.set(
					i,
					answerUriList.get(i).substring(
							answerUriList.get(i).indexOf("#") + 1,
							answerUriList.get(i).lastIndexOf(">")));
			Answer tempAnswer = new Answer();
			tempAnswer.URI = answerUriList.get(i);
			System.out.println("tempanswer is" + tempAnswer.URI);
			objQuestion.Options.add(tempAnswer);
		}
	}

	public void loadChoicesDesc(Question objQuestion) {
		loadChoicesRW(objQuestion);
		loadChoicesDL(objQuestion);
	}

	public void loadChoicesRW(Question objQuestion) {
		ArrayList<String> realWorldList = new ArrayList<String>();

		// part1

		for (int i = 0; i < objQuestion.Options.size(); i++) {
			String AnswerUri = objQuestion.Options.get(i).URI;
			dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
			realWorldList = o
					.runQueryList("select ?var2 { <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ AnswerUri
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasDescription> ?desc ."
							+ "?desc <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?var2}");
			String concatedMultipleOptions = "";
			for (int j = 0; j < realWorldList.size(); j++) {
				String tempStr = realWorldList.get(j).substring(
						realWorldList.get(j).indexOf("\"") + 1,
						realWorldList.get(j).lastIndexOf("\""));
				if (concatedMultipleOptions.equals(""))
					concatedMultipleOptions = tempStr;
				else
					concatedMultipleOptions = concatedMultipleOptions
							.concat(", " + tempStr);
			}

			// part3

			if (!concatedMultipleOptions.equals("")) {
				System.out.println("cmo is" + concatedMultipleOptions);
				objQuestion.Options.get(i).AnswerString = concatedMultipleOptions;
			}
		}
	}

	public void loadChoicesDL(Question objQuestion) {
		// part2
		System.out.println("In DL fn start");
		ArrayList<String> discriptionList = new ArrayList<String>();
		for (int i = 0; i < objQuestion.Options.size(); i++) {
			System.out.println("In DL fn looping all options");
			String AnswerUri = objQuestion.Options.get(i).URI;
			dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
			discriptionList = o
					.runQueryList("select ?var2 { <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ AnswerUri
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasDescription> ?desc . "
							+ "?desc <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?var2 }");
			System.out.println("In DL after query");
			String concatedMultipleOptions = "";
			for (int j = 0; j < discriptionList.size(); j++) {
				System.out.println("DL result is " + discriptionList.get(j));
				String tempStr = discriptionList.get(j).substring(
						discriptionList.get(j).indexOf("\"") + 1,
						discriptionList.get(j).lastIndexOf("\""));
				if (concatedMultipleOptions.equals(""))
					concatedMultipleOptions = tempStr;
				else
					concatedMultipleOptions = concatedMultipleOptions
							.concat(", " + tempStr);
			}
			if (!concatedMultipleOptions.equals("")) {
				System.out.println("cmo is" + concatedMultipleOptions);
				objQuestion.Options.get(i).AnswerString = concatedMultipleOptions;
			}
		}
	}

	public ArrayList<String> choicesOfQuestion(Question objQuestion) {
		ArrayList<String> choices = new ArrayList<String>();
		for (int i = 0; i < problemTypeList.size(); i++) {
			if (problemTypeList.get(i).URI.equals(problemTypeSelected)) {
				for (int j = 0; j < problemTypeList.get(i).problemList.size(); j++) {
					if (problemTypeList.get(i).problemList.get(j).URI
							.equals(problemUri)) {
						for (int k = 0; k < problemTypeList.get(i).problemList
								.get(j).questionList.size(); k++) {
							if (problemTypeList.get(i).problemList.get(j).questionList
									.get(k).equals(objQuestion.URI)) {
								for (int l = 0; l < problemTypeList.get(i).problemList
										.get(j).questionList.get(k).Options
										.size(); l++) {
									choices.add(problemTypeList.get(i).problemList
											.get(j).questionList.get(k).Options
											.get(l).AnswerString);
								}
							}
						}
					}
				}
			}
		}
		return choices;
	}

	public void loadQandA(Problem objProblem) {
		System.out.println("inside qanda");
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		o.removeProperty("isCurrentQuestion", username);
		for (int i = 0; i < problemTypeList.size(); i++) {
			System.out.println("1");
			if (problemTypeList.get(i).URI.equals(problemTypeSelected)) {
				System.out.println("2");
				for (int j = 0; j < problemTypeList.get(i).problemList.size(); j++) {
					System.out.println("3");
					if (objProblem.URI
							.equals(problemTypeList.get(i).problemList.get(j).URI)) {
						System.out.println("before loadallques");
						loadAllQuestions(problemTypeList.get(i).problemList
								.get(j));
						// load other details of question apart from Uri
						for (int k = 0; k < problemTypeList.get(i).problemList
								.get(j).questionList.size(); k++) {
							loadChoicesUri(problemTypeList.get(i).problemList
									.get(j).questionList.get(k));
							loadChoicesDesc(problemTypeList.get(i).problemList
									.get(j).questionList.get(k));
							// load other details of choices apart from Uri and
							// desc. ie., feedback
						}
					}
				}
			}
		}
	}

	static GlobalData objGlobalData = new GlobalData();

	private GlobalData() {
		// loadQandA();
	}

	public ArrayList<ProblemType> getProblemTypeList() {
		return problemTypeList;
	}

	public void setProblemTypeList(ArrayList<ProblemType> problemTypeList) {
		this.problemTypeList = problemTypeList;
	}

	public String getFirstUri() {
		return firstUri;
	}

	public void setFirstUri(String firstUri) {
		this.firstUri = firstUri;
	}

	public String getCurrentQuestionUri() {
		return currentQuestionUri;
	}

	public void setCurrentQuestionUri(String currentQuestionUri) {
		this.currentQuestionUri = currentQuestionUri;
	}

	public String getUsername() {
		return username;
	}

	public static GlobalData getObject() {
		return objGlobalData;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProblemTypeSelected() {
		return problemTypeSelected;
	}

	public void setProblemTypeSelected(String problemTypeSelected) {
		this.problemTypeSelected = problemTypeSelected;
	}

	public String getProblemUri() {
		return problemUri;
	}

	public void setProblemUri(String problemUri) {
		this.problemUri = problemUri;
	}
}