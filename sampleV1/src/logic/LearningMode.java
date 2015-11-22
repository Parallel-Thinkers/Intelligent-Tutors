package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import dataAccess.OwlAccess;
import datastructures.GlobalData;

public class LearningMode {

	private String problem;
	private String question;
	private Map<String, String> choiceList;
	public Map<String, String> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(Map<String, String> choiceList) {
		this.choiceList = choiceList;
	}

	String problemImage="images/";
    private String concepts = "";
	public String getConcepts() {
		return concepts;
	}

	public void setConcepts(String concepts) {
		this.concepts = concepts;
	}

	public String getProblemImage() {
		return problemImage;
	}

	public void setProblemImage(String problemImage) {
		this.problemImage = problemImage;
	}

	

	public void setChoiceList(HashMap<String, String> choiceList) {
		this.choiceList = choiceList;
	}

	public ArrayList<String> getCompletedQuestions() {
		return completedQuestions;
	}

	public void setCompletedQuestions(ArrayList<String> completedQuestions) {
		this.completedQuestions = completedQuestions;
	}

	ArrayList<String> completedQuestions = new ArrayList<String>();
	private String selected;
	private String feedback = " ";
	String selectedProblemType;

	public String getSelectedProblemType() {
		return selectedProblemType;
	}

	public void setSelectedProblemType(String selectedProblemType) {
		this.selectedProblemType = selectedProblemType;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String loadProblem() {
		try {
			GlobalData ogd = GlobalData.getObject();
			OwlAccess o = OwlAccess.getObject();
			long start = System.currentTimeMillis();
			o.removeProperty("isCurrentProblem", ogd.getUsername());

			o.addObjProperty(ogd.getUsername(), "isCurrentProblem",
					ogd.getProblemUri());
			System.out.println("removing problem time:"
					+ (System.currentTimeMillis() - start));
			problem = ogd.getProblemDescOfUri(ogd.getProblemUri());
			problemImage+= ogd.getProblemImage(ogd.getProblemUri());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}

	public String loadQuestion() {
		try {
			GlobalData ogd = GlobalData.getObject();
			ogd.loadCurrentQuestion();
			selectedProblemType = ogd.getProblemTypeSelected();

			question = ogd.getQuestionDescOfUri(ogd.getCurrentQuestionUri());

			System.out.println("question uri" + ogd.getCurrentQuestionUri());
			System.out.println("Question is" + question);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}

	public String loadChoices() {
		try {
			GlobalData ogd = GlobalData.getObject();
			choiceList = ogd.getAnswerListOfQuestionURI(ogd
					.getCurrentQuestionUri());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}

	public String loadAll() {
		try {
			GlobalData ogd = GlobalData.getObject();
			System.out.println("load prob start");
			OwlAccess o = OwlAccess.getObject();
			
			
		
			

			ArrayList<String> result = o
					.runQueryList("select ?eb { "
							+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ ogd.getUsername()
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCompletedProblemForLearning> ?eb . "
							+ "  FILTER (?eb =  <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ ogd.getProblemUri() + ">" + ")" + "}");

			if (result.size() != 0) {
				
				JOptionPane.showMessageDialog(null,
						"You have already learned the problem Press OK to see the questions and answers");
				
				ArrayList<String> question = o
						.runQueryList("select ?desc ?ans { "
								+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
								+ ogd.getProblemUri()
								+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasQuestion> ?ques . "
								+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?desc ."
								+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#correctOption> ?option ."
								+ "?option <http://www.owl-ontologies.com/MenDomOntology.owl#hasDescription> ?answer ."
			                    + "?answer <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?ans"
							 + "}");
				
				System.out.println("inside learning mode:"+question.size());
				String qa="";
				int count =1;
				for(String s:question)
				{
					System.out.println("inside learned questions:"+s);
					
					qa += "Question"+count+":  "+s.substring(s.indexOf("=")+2, s.indexOf("^")-2)+"\n";
					qa+= "Answer"+count+":  "+s.substring(s.indexOf("?ans =")+8,s.lastIndexOf("^")-2)+"\n";
					count++;
					
				}
				
				question = o
						.runQueryList("select ?desc ?ans { "
								+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
								+ ogd.getProblemUri()
								+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasQuestion> ?ques . "
								+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?desc ."
								+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#correctOption> ?option ."
								+ "?option <http://www.owl-ontologies.com/MenDomOntology.owl#hasDescription> ?answer ."
			                    + "?answer <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?ans"
							 + "}");
		 
				for(String s:question)
				{
					System.out.println("inside learned questions:"+s);
					
					qa += "Question"+count+":  "+s.substring(s.indexOf("=")+3, s.indexOf("^")-2)+"\n";
					qa+= "Answer"+count+":  "+s.substring(s.indexOf("?ans =")+8,s.lastIndexOf("^")-2)+"\n";
					count++;
					
				}
				
				JOptionPane.showMessageDialog(null,
						qa); 
				
				return "nextp";
			}

			
			loadProblem();

			ogd.loadFirstQuestion();

			loadQuestion();

			loadChoices();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}

	public String resetques() {
		GlobalData ogd = GlobalData.getObject();

		ogd.loadFirstQuestion();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		o.removeProperty("isCurrentQuestion", ogd.getUsername());
		// o.addObjProperty(ogd.getUsername(), "isCurrentQuestion",
		// ogd.getFirstUri());
		// completedQuestions=ogd.getListofCompletedQues();
		System.out
				.println(o
						.runQueryList(
								"select ?ques { "
										+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
										+ ogd.getUsername()
										+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCompletedQuestion> ?ques."
										+ "}").size());
		// System.out.println("Completed question is: "+completedQuestions.get(0));
		// for(int p=0;p<completedQuestions.size();p++){
		o.removeProperty("isCompletedQuestion", ogd.getUsername());
		// }

		loadProblem();
		loadQuestion();
		loadChoices();
		System.out.println("Question after start over is: " + question);
		return "success";
	}

	public String checkAnswer() {
		GlobalData ogd = GlobalData.getObject();
		dataAccess.OwlAccess o = dataAccess.OwlAccess.getObject();
		String questUri = ogd.getCurrentQuestionUri();
		String choiceUri = selected;
       
		String userUri = ogd.getUsername();

		int retval = o.addObjProperty(questUri, "selectedOption", choiceUri);
		System.out.println("Retval is: " + retval);
		// int retval1=o.removeProperty("selectedOption", questUri);
		// System.out.println("Retval1 is: "+retval1);
		String result = "";
		String result1 = "";
		
		try {

			result = o
					.runQueryList(
							"select ?ques { "
									+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
									+ userUri
									+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCurrentQuestion> ?ques . "
									+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
									+ userUri
									+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCompletedQuestion> ?ques."
									+ "}").get(0);
			System.out.println("try1");
			try {
				result = o
						.runQueryList(
								"select ?ques { "
										+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
										+ userUri
										+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCurrentQuestion> ?ques . "
										+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
										+ userUri
										+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#isCompletedQuestion> ?ques."
										+ "?ques <http://www.owl-ontologies.com/MenDomOntology.owl#isLast> true."
										+ "}").get(0);
				System.out.println("try2");
			} catch (Exception ex1) {
				// r2
				// added by Debargha
				o.addObjProperty(ogd.getUsername(), "isCompletedQuestion",
						questUri);

				String query = "select ?bugs {<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ questUri
						+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasOption> ?options."
						+ "?options <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?bugs}";

				ArrayList<String> res = o.runQueryList(query);

				for (String bug : res) {
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

				// end of addition

				result = o
						.runQueryList(
								"select ?nextq { "
										+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
										+ questUri
										+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasNext> ?nextq . "
										+ "}").get(0);
				result = result.substring(result.indexOf("#") + 1,
						result.lastIndexOf(">"));
				System.out.println("Next question: " + result);
				result1 = o
						.runQueryList(
								"select ?descval { "
										+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
										+ choiceUri
										+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeedback> ?fdb . "
										+ " ?fdb <http://www.owl-ontologies.com/MenDomOntology.owl#discription>  ?descval . "
										+ "}").get(0);
				// result1=result1.substring(result1.indexOf("\"")+1,
				// result1.lastIndexOf("\""));
				System.out.println("nextqFeedback: " + result1);
				result1 = result1.substring(result1.indexOf("\"") + 1,
						result1.lastIndexOf("\""));
				setFeedback(result1);
				int retval1 = o.removeProperty("selectedOption", questUri);
				System.out.println("Retval1 is: " + retval1);
				o.removeProperty("isCurrentQuestion", userUri);
				o.addObjProperty(userUri, "isCurrentQuestion", result);
				// JOptionPane.showMessageDialog(null,result);
				//Added for concepts learned
				
				String question = ogd.getQuestionDescOfUri(questUri);
				Map<String,String> choiceList = ogd.getAnswerListOfQuestionURI(questUri);
				
				
				
				String answer = choiceList.get(choiceUri);
				
				ogd.setConcepts(ogd.getConcepts()+"Question: "+question+"\nAnswer: "+answer+"\n");
				
				
				System.out.println("concepts:"+ogd.getConcepts());
				
				//end of addition
				
				
				loadProblem();
				loadQuestion();
				loadChoices();
				JOptionPane.showMessageDialog(null, result1);
				System.out.println("catch2");
				// completedQuestions=ogd.getListofCompletedQues();
				// System.out.println("Completed question is: "+completedQuestions.get(0));
				setConcepts(ogd.getConcepts());
				return "nextq";

			}
		} catch (Exception ex2) {
			// r1
			// put bug here
			o.removeProperty("selectedOption", questUri);
			result = o
					.runQueryList(
							"select ?descval { "
									+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
									+ choiceUri
									+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeedback> ?fdb . "
									+ " ?fdb <http://www.owl-ontologies.com/MenDomOntology.owl#discription>  ?descval ."
									+ "}").get(0);
			result = result.substring(result.indexOf("\"") + 1,
					result.lastIndexOf("\""));
			System.out.println("wrongFeedback: " + result);
			setFeedback(result);
			JOptionPane.showMessageDialog(null, result);
			ArrayList<String> strBugList = new ArrayList<String>();
			strBugList = o
					.runQueryList("select ?eb { "
							+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
							+ choiceUri
							+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?eb . "
							+ "}");
			for (int i = 0; i < strBugList.size(); i++) {
				// check whether bug is already present
				System.out.println("Expected bugs for answer:" + choiceUri
						+ " " + strBugList.get(i));

				strBugList.set(
						i,
						strBugList.get(i).substring(
								strBugList.get(i).indexOf("#") + 1,
								strBugList.get(i).indexOf(">")));
				
				o.addObjProperty(ogd.getUsername(), "capturedBugs",
						strBugList.get(i));
				o.updateOpenBugCount(strBugList.get(i));

			}
			loadProblem();
			loadQuestion();
			loadChoices();
			System.out.println("catch1");
			setConcepts(ogd.getConcepts());
			return "wrong";
		}
		// l1 return p completed
		// loadAll();
		int retval1 = o.removeProperty("selectedOption", questUri);

		String query = "select ?bugs {<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ questUri
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasOption> ?options."
				+ "?options <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?bugs}";

		ArrayList<String> res = o.runQueryList(query);

		for (String bug : res) {
			bug = bug.substring(bug.indexOf("#") + 1, bug.lastIndexOf(">"));
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

		System.out.println("Retval1 is: " + retval1);
		result = o
				.runQueryList(
						"select ?descval { "
								+ " <http://www.owl-ontologies.com/MenDomOntology.owl#"
								+ choiceUri
								+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeedback> ?fdb . "
								+ " ?fdb <http://www.owl-ontologies.com/MenDomOntology.owl#discription>  ?descval . "
								+ "}").get(0);
		result = result.substring(result.indexOf("\"") + 1,
				result.lastIndexOf("\""));
		System.out.println("nextpFeedback: " + result);
		setFeedback(result);
		// last questions add for completion
		o.addObjProperty(ogd.getUsername(), "isCompletedQuestion", questUri);
		// Problem added for learning
		o.addObjProperty(ogd.getUsername(), "isCompletedProblemForLearning",
				ogd.getProblemUri());
		o.removeProperty("isCurrentQuestion", ogd.getUsername());
		o.updateTimestamp(ogd.getProblemUri(), "learningEndTime");
		JOptionPane.showMessageDialog(null, result);
		//added for activity
		/*DatabaseStore store = DatabaseStore.getStore();
		
		query = "update activity set end="+new Date().getTime()+" where proburi='"+ogd.getProblemUri()+"' and mode='L'";
		System.out.println("Update query:"+query);
		System.out.println(store.update(query));*/
		//end of addition
		
		return "nextp";
	}

}
