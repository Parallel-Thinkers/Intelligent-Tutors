package logic;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.File;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import dataAccess.OwlAccess;
import datastructures.ActivityBean;
import datastructures.CapturedBug;
import datastructures.GlobalData;
import datastructures.ProgressData;

public class LearningProfile implements ServletRequestAware {


	ArrayList<CapturedBug> bugs = new ArrayList<CapturedBug>();
//	ArrayList<CapturedBug> bugs1 = new ArrayList<CapturedBug>();
	
	private String problem;
	public static String probtypeid;
	public static String getProbtypeid() {
		return probtypeid;
	}

	public static void setProbtypeid(String probtypeid) {
		LearningProfile.probtypeid = probtypeid;
	}

	private String uname;
	HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
	ArrayList<ProgressData> progress = new ArrayList<ProgressData>();

/*	public ArrayList<CapturedBug> getBugs1() {
		return bugs1;
	}

	public void setBugs1(ArrayList<CapturedBug> bugs1) {
		this.bugs1 = bugs1;
	}
*/
	public ArrayList<ProgressData> getProgress() {
		return progress;
	}

	public void setProgress(ArrayList<ProgressData> progress) {
		this.progress = progress;
	}

	public HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HashMap<String, Integer> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Integer> resultMap) {
		this.resultMap = resultMap;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	String selectedProblemType;

	public String getSelectedProblemType() {
		return selectedProblemType;
	}

	public void setSelectedProblemType(String selectedProblemType) {
		this.selectedProblemType = selectedProblemType;
	}

	public ArrayList<CapturedBug> getBugs() {
		return bugs;
	}

	public void setBugs(ArrayList<CapturedBug> bugs) {
		this.bugs = bugs;
	}

	public String loadmissingconcepts() {
		System.out.println("I am here learning profile");
		DecimalFormat twoDForm = new DecimalFormat("#");
		GlobalData ogd = GlobalData.getObject();
		OwlAccess o = OwlAccess.getObject();
		problem = ogd.getProblemDescOfUri(ogd.getProblemUri());
		selectedProblemType = ogd.getProblemTypeSelected();
		String query1 = "select ?bugs ?open ?resolved ?feedback {<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getUsername()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#capturedBugs> ?bugs ."
				+ "?bugs <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#MissingConcept> ."
				+ "?bugs <http://www.owl-ontologies.com/MenDomOntology.owl#OpenBug> ?open ."
				+ "?bugs <http://www.owl-ontologies.com/MenDomOntology.owl#resolvedBug> ?resolved ."
				+ "?bugs <http://www.owl-ontologies.com/MenDomOntology.owl#remedialFeedback> ?feedback }";

		ArrayList<String> result = o.runQueryList(query1);

		for (String s : result) {

			CapturedBug bug = new CapturedBug();
			System.out.println("string is RAHUL:" + s);
			bug.setDescription(s.substring(s.indexOf("#") + 1, s.indexOf(">")));
			s = s.substring(s.indexOf("=") + 3);
			s = s.substring(s.indexOf("=") + 3);
			bug.setOpenCount(Integer.parseInt(s.substring(0, s.indexOf("^") - 1)));
			s = s.substring(s.indexOf("=") + 3);
			bug.setResolvedCount(Integer.parseInt(s.substring(0,
					s.indexOf("^") - 1)));
			s = s.substring(s.indexOf("=") + 3);
			bug.setFeedback(s.substring(0, s.indexOf("^") - 1));
			try {
				bug.setPerc(((double) bug.getResolvedCount() / ((double) bug
						.getResolvedCount() + (double) bug.getOpenCount())) * 100);
				bug.setPerc(Double.valueOf(twoDForm.format(bug.getPerc())));
			} catch (Exception ex) {
				bug.setPerc(0.0);
			}
			System.out.println("learning profile result:" + s);

			System.out.println(bug.getOpenCount() + " "
					+ bug.getResolvedCount() + " " + bug.getFeedback());

			bugs.add(bug);

		}

		return "success";
	}

	public String loadmissconcept() {
		System.out.println("I am here learning profile");
		GlobalData ogd1 = GlobalData.getObject();
		DecimalFormat twoDForm1 = new DecimalFormat("#");
		OwlAccess o1 = OwlAccess.getObject();
		problem = ogd1.getProblemDescOfUri(ogd1.getProblemUri());
		selectedProblemType = ogd1.getProblemTypeSelected();
		String query2 = "select ?bugs ?open ?resolved ?feedback {<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd1.getUsername()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#capturedBugs> ?bugs ."
				+ "?bugs <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#Misconcept> ."
				+ "?bugs <http://www.owl-ontologies.com/MenDomOntology.owl#OpenBug> ?open ."
				+ "?bugs <http://www.owl-ontologies.com/MenDomOntology.owl#resolvedBug> ?resolved ."
				+ "?bugs <http://www.owl-ontologies.com/MenDomOntology.owl#remedialFeedback> ?feedback }";

		ArrayList<String> result1 = o1.runQueryList(query2);

		for (String s : result1) {

			CapturedBug bug = new CapturedBug();
			System.out.println("string is:" + s);
			bug.setDescription(s.substring(s.indexOf("#") + 1, s.indexOf(">")));
			s = s.substring(s.indexOf("=") + 3);
			s = s.substring(s.indexOf("=") + 3);
			bug.setOpenCount(Integer.parseInt(s.substring(0, s.indexOf("^") - 1)));
			s = s.substring(s.indexOf("=") + 3);
			bug.setResolvedCount(Integer.parseInt(s.substring(0,
					s.indexOf("^") - 1)));
			s = s.substring(s.indexOf("=") + 3);
			bug.setFeedback(s.substring(0, s.indexOf("^") - 1));
			try {
				bug.setPerc(((double) bug.getResolvedCount() / ((double) bug
						.getResolvedCount() + (double) bug.getOpenCount())) * 100);
				bug.setPerc(Double.valueOf(twoDForm1.format(bug.getPerc())));

			} catch (Exception ex) {
				bug.setPerc(0.0);
			}
			System.out.println("learning profile result:" + s);

			System.out.println(bug.getOpenCount() + " "
					+ bug.getResolvedCount() + " " + bug.getFeedback());

//			bugs1.add(bug);
			bugs.add(bug);

		}

		return "success";
	}

	public String loadCoveringProblemTasks() {
		System.out.println("I am here learning profile inside loadCoveringProblemTasks()"+getProbtypeid()+getUname());

		GlobalData ogd = GlobalData.getObject();
		OwlAccess o = OwlAccess.getObject();
		String queryString;
		if(ogd.getProblemTypeSelected()==null){System.out.println("here");
		 queryString = "SELECT ?t "
				+ "WHERE { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getUsername()
		//		+ getUname()
				+ ">"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#completedTasks> ?t . "
				+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
				+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf> ?p . "
				+ "?p  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
			//	+ ogd.getProblemTypeSelected() + "> . " + "}";
		+ getProbtypeid() + "> . " + "}";}
		else{ queryString = "SELECT ?t "
				+ "WHERE { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getUsername()
		//		+ getUname()
				+ ">"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#completedTasks> ?t . "
				+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
				+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf> ?p . "
				+ "?p  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemTypeSelected() + "> . " + "}";
		//+ getProbtypeid() + "> . " + "}";}
		}

		ArrayList<String> result = o.runQueryListwithoutreasoner(queryString);

		for (String s : result) {

			String taskname = "";
			s = s.substring(s.indexOf("#") + 1, s.indexOf(">"));

			queryString = "SELECT ?taskname "
					+ "WHERE { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ s
					+ ">"
					+ "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?taskname . "
					+ "}";

			ArrayList<String> result1 = o
					.runQueryListwithoutreasoner(queryString);

			if (result1.size() > 0) {
				taskname = result1.get(0).substring(
						result1.get(0).indexOf("#") + 1,
						result1.get(0).indexOf(">"));
			}

			queryString = "SELECT ?taskname "
					+ "WHERE { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ s
					+ ">"
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#inverse_of_learnedConcepts> ?concept . "
					+ "?concept <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?taskname"
					+ "}";

			result1 = o.runQueryListwithoutreasoner(queryString);

			if (result1.size() > 0) {
				taskname += "\n("
						+ result1.get(0).substring(
								result1.get(0).indexOf("#") + 1,
								result1.get(0).indexOf(">")) + ")";

				System.out.println("Task name:" + taskname);
			}

			if (resultMap.get(taskname) == null) {
				resultMap.put(taskname, 1);
			}

			else {

				int count = resultMap.get(taskname);
				resultMap.put(taskname, count + 1);
			}

			System.out.println("inside load covering problem tasks:" + s);

		}

		System.out.println("result map size:" + resultMap.size());
		generateBarChart(resultMap);

		return "success";
	}

	public String loadMensurationdomain() {

		String labels[] = { "Cube", "Cuboid", "RightCircularCone",
				"RightCircularCylinder", "Hemisphere", "Sphere",
				"CombinationOfDifferentShapes","AnyStructure","FrustumOfCone" };

		GlobalData ogd = GlobalData.getObject();
		OwlAccess o = OwlAccess.getObject();
		String queryString = "SELECT ?menstype "
				+ "WHERE { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getUsername()
				+ ">"
				+ " <http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?p . "
				+ "?p  <http://www.owl-ontologies.com/MenDomOntology.owl#ofMensurationTypeProblem> ?menstype "
				+

				"}";

		ArrayList<String> menstype = o.runQueryList(queryString);
		HashMap<String, HashMap<String, Integer>> graphData = new HashMap<String, HashMap<String, Integer>>();

		for (String mtype : menstype) {

			mtype = mtype.substring(mtype.indexOf("#") + 1, mtype.indexOf(">"));
			String first = mtype.substring(0, mtype.indexOf("_"));
			String sec = mtype.substring(mtype.indexOf("_") + 1);
			if (sec.contains("Combination"))
				sec = labels[6];
			System.out.println("strings :" + first + " " + sec);
			HashMap<String, Integer> map1;
			if (graphData.get(first) == null) {
				map1 = new HashMap<String, Integer>();
				for (String label : labels)
					map1.put(label, 0);

				map1.put(sec, 1);
				graphData.put(first, map1);

			}

			else {
				map1 = graphData.get(first);

				if (map1.get(sec) == null) {
					map1.put(sec, 1);
				}

				else {
					int count = map1.get(sec);
					map1.put(sec, count + 1);

				}

			}

		}

		generatemultipleBarChart(graphData);

		return "success";

	}

	public String loadActivity() {

		GlobalData ogd = GlobalData.getObject();
		OwlAccess o = OwlAccess.getObject();
		HashMap<String, Integer> learningMap = new HashMap<String, Integer>();
		HashMap<String, Integer> solvingMap = new HashMap<String, Integer>();

		String query = "SELECT ?p ?startTime ?endTime  "
				+ "WHERE { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getUsername()
				+ ">"
				+ " <http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?p . "
				+ "?p  <http://www.owl-ontologies.com/MenDomOntology.owl#learningStartTime> ?startTime . "
				+ "?p  <http://www.owl-ontologies.com/MenDomOntology.owl#learningEndTime> ?endTime . "

				+ "}";

		ArrayList<String> resultlist = o.runQueryList(query);
		ArrayList<ActivityBean> list = new ArrayList<ActivityBean>();
		try {
			for (String res : resultlist) {
				System.out.println("the result is in activity:" + res);
				ActivityBean act = new ActivityBean();

				try {

					res = res.substring(res.indexOf("=") + 1);
					String proburi = res.substring(res.indexOf("#") + 1,
							res.indexOf(">"));

					res = res.substring(res.indexOf("=") + 3);

					String start = res.substring(0, res.indexOf("\""));
					res = res.substring(res.indexOf("=") + 3);
					String end = res.substring(0, res.indexOf("\""));
					System.out.println("activity values:" + proburi + " "
							+ start + " " + end);
					act.setProburi(proburi);
					act.setStart(Long.parseLong(start));
					act.setEnd(Long.parseLong(end));
					act.setMode('L');

					list.add(act);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		query = "SELECT ?p ?startTime ?endTime  "
				+ "WHERE { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getUsername()
				+ ">"
				+ " <http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?p . "
				+ "?p  <http://www.owl-ontologies.com/MenDomOntology.owl#solvingStartTime> ?startTime . "
				+ "?p  <http://www.owl-ontologies.com/MenDomOntology.owl#solvingEndTime> ?endTime . "

				+ "}";

		resultlist = o.runQueryList(query);

		try {
			for (String res : resultlist) {
				System.out.println("the result is in activity:" + res);
				ActivityBean act = new ActivityBean();

				try {

					res = res.substring(res.indexOf("=") + 1);
					String proburi = res.substring(res.indexOf("#") + 1,
							res.indexOf(">"));

					res = res.substring(res.indexOf("=") + 3);

					String start = res.substring(0, res.indexOf("\""));
					res = res.substring(res.indexOf("=") + 3);
					String end = res.substring(0, res.indexOf("\""));
					System.out.println("activity values:" + proburi + " "
							+ start + " " + end);
					act.setProburi(proburi);
					act.setStart(Long.parseLong(start));
					act.setEnd(Long.parseLong(end));
					act.setMode('S');

					list.add(act);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		for (ActivityBean act : list) {

			String proburi = act.getProburi();

			String queryString = "SELECT ?problemtype "
					+ "WHERE { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ proburi
					+ "> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?problemtype"

					+ "}";

			ArrayList<String> problemtypes = o.runQueryList(queryString);

			String probtype = "";

			for (String ptypes : problemtypes) {
				if (ptypes.contains("owl:Thing")
						|| ptypes
								.equals("( ?problemtype = <http://www.owl-ontologies.com/MenDomOntology.owl#Problem> )"))
					continue;

				else {
					probtype = ptypes;
					break;
				}
			}

			if (!probtype.equals("")) {
				probtype = probtype.substring(probtype.indexOf("#") + 1,
						probtype.indexOf(">"));

				int diff = (int) (act.getEnd() - act.getStart()) / 1000;
				System.out.println("time diff:" + diff);
				System.out.println("mode:" + act.getMode());
				if (diff >= 0) {
					if (act.getMode() == 'L') {
						if (learningMap.get(probtype) != null) {
							int count = learningMap.get(probtype);
							learningMap.put(probtype, count + diff);

						} else {
							learningMap.put(probtype, diff);
						}

					}

					else if (act.getMode() == 'S') {
						if (solvingMap.get(probtype) != null) {
							int count = solvingMap.get(probtype);
							solvingMap.put(probtype, count + diff);

						} else {
							solvingMap.put(probtype, diff);
						}

					}
				}

			}

		}

		System.out.println(learningMap.size() + " " + solvingMap.size());

		generatLineChart(learningMap, solvingMap);
		return "success";

	}

	public String loadProgress() {
		GlobalData ogd = GlobalData.getObject();
		OwlAccess o = OwlAccess.getObject();
		DecimalFormat twoDForm = new DecimalFormat("#");
		String queryString = "SELECT ?problemtype "
				+ "WHERE { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getUsername()
				+ ">"
				+ " <http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?p . "
				+ "?p  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?problemtype . "
				+

				"}";

		ArrayList<String> problemtypes = o.runQueryList(queryString);
		String problemtypescheck = "";
		System.out.println("size:" + problemtypes.size());

		for (String ptypes : problemtypes) {

			if (ptypes.contains("owl:Thing")
					|| ptypes
							.equals("( ?problemtype = <http://www.owl-ontologies.com/MenDomOntology.owl#Problem> )"))
				continue;
			int countCompletetasks = 0;
			int countIncompleteTasks = 0;

			ptypes = ptypes.substring(ptypes.indexOf("#") + 1,
					ptypes.indexOf(">"));

			if (problemtypescheck.contains(ptypes))
				continue;

			ProgressData p = new ProgressData();
			p.setProblemtype(ptypes);

			// easy
			String difficulty = "easy";
			queryString = "SELECT ?p "
					+ "WHERE { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ogd.getUsername()
					+ ">"
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?p . "
					+ "?p  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ptypes
					+ "> . "
					+ "?p <http://www.owl-ontologies.com/MenDomOntology.owl#difficulty> \""
					+ difficulty
					+ "\"^^<http://www.w3.org/2001/XMLSchema#string> . " + "}";

			ArrayList<String> problems = o.runQueryList(queryString);

			p.setEasy_attemps(problems.size());

			for (String problem : problems) {
				problem = problem.substring(problem.indexOf("#") + 1,
						problem.indexOf(">"));
				queryString = "SELECT ?taskname "
						+ "WHERE { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getUsername()
						+ ">"
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#completedTasks> ?t . "
						+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
						+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf>  <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problem + "> . " + "}";

				countCompletetasks += o
						.runQueryListwithoutreasoner(queryString).size();

				queryString = "SELECT ?taskname "
						+ "WHERE { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getUsername()
						+ ">"
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#IncompleteTasks> ?t . "
						+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
						+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf>  <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problem + "> . " + "}";
				countIncompleteTasks += o.runQueryListwithoutreasoner(
						queryString).size();

			}

			System.out.println("Count complete easy:" + countCompletetasks
					+ " Count incompete easy:" + countIncompleteTasks);
			if (countCompletetasks == 0)
				p.setEasy_correct(0);
			else
				p.setEasy_correct(((double) countCompletetasks * 100)
						/ ((double) countCompletetasks + (double) countIncompleteTasks));

			p.setEasy_correct(Double.valueOf(twoDForm.format(p
					.getEasy_correct())));

			// medium
			countCompletetasks = 0;
			countIncompleteTasks = 0;
			difficulty = "medium";
			queryString = "SELECT ?p "
					+ "WHERE { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ogd.getUsername()
					+ ">"
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?p . "
					+ "?p  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ptypes
					+ "> . "
					+ "?p <http://www.owl-ontologies.com/MenDomOntology.owl#difficulty> \""
					+ difficulty
					+ "\"^^<http://www.w3.org/2001/XMLSchema#string> . " + "}";

			problems = o.runQueryList(queryString);

			p.setMedium_attemps(problems.size());

			for (String problem : problems) {
				problem = problem.substring(problem.indexOf("#") + 1,
						problem.indexOf(">"));
				queryString = "SELECT ?taskname "
						+ "WHERE { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getUsername()
						+ ">"
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#completedTasks> ?t . "
						+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
						+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf>  <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problem + "> . " + "}";

				countCompletetasks += o
						.runQueryListwithoutreasoner(queryString).size();

				queryString = "SELECT ?taskname "
						+ "WHERE { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getUsername()
						+ ">"
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#IncompleteTasks> ?t . "
						+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
						+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf>  <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problem + "> . " + "}";
				countIncompleteTasks += o.runQueryListwithoutreasoner(
						queryString).size();

			}

			System.out.println("Count complete medium:" + countCompletetasks
					+ " Count incompete medium:" + countIncompleteTasks);
			if (countCompletetasks == 0)
				p.setMedium_correct(0);
			else
				p.setMedium_correct(((double) countCompletetasks * 100)
						/ ((double) countCompletetasks + (double) countIncompleteTasks));

			p.setMedium_correct(Double.valueOf(twoDForm.format(p
					.getMedium_correct())));
			// hard

			difficulty = "hard";
			countCompletetasks = 0;
			countIncompleteTasks = 0;
			queryString = "SELECT ?p "
					+ "WHERE { "
					+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ogd.getUsername()
					+ ">"
					+ " <http://www.owl-ontologies.com/MenDomOntology.owl#completedProblems> ?p . "
					+ "?p  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
					+ ptypes
					+ "> . "
					+ "?p <http://www.owl-ontologies.com/MenDomOntology.owl#difficulty> \""
					+ difficulty
					+ "\"^^<http://www.w3.org/2001/XMLSchema#string> . " + "}";

			problems = o.runQueryList(queryString);

			p.setHard_attemps(problems.size());

			for (String problem : problems) {
				problem = problem.substring(problem.indexOf("#") + 1,
						problem.indexOf(">"));
				queryString = "SELECT ?taskname "
						+ "WHERE { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getUsername()
						+ ">"
						+ " <http://www.owl-ontologies.com/MenDomOntology.owl#completedTasks> ?t . "
						+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
						+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf>  <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problem + "> . " + "}";

				countCompletetasks += o
						.runQueryListwithoutreasoner(queryString).size();

				queryString = "SELECT ?taskname "
						+ "WHERE { "
						+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ ogd.getUsername()
						+ ">"
						+ " <http://www.owl-ontologies.com/MenDomOntology.owl#IncompleteTasks> ?t . "
						+ "?t  <http://www.owl-ontologies.com/MenDomOntology.owl#isTaskOf> ?s . "
						+ "?s  <http://www.owl-ontologies.com/MenDomOntology.owl#isStrategyOf>  <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ problem + "> . " + "}";
				countIncompleteTasks += o.runQueryListwithoutreasoner(
						queryString).size();

			}

			System.out.println("Count complete hard:" + countCompletetasks
					+ " Count incompete hard:" + countIncompleteTasks);
			if (countCompletetasks == 0)
				p.setHard_correct(0);
			else
				p.setHard_correct(((double) countCompletetasks * 100)
						/ ((double) countCompletetasks + (double) countIncompleteTasks));

			p.setHard_correct(Double.valueOf(twoDForm.format(p
					.getHard_correct())));
			p.setOverall((p.getEasy_correct() + p.getHard_correct() + p
					.getMedium_correct()) / 3);

			p.setOverall(Double.valueOf(twoDForm.format(p.getOverall())));
			progress.add(p);

			problemtypescheck += ptypes + " ";

		}

		System.out.println("progress size:" + progress.size());

		return "success";
	}

	public JFreeChart generateBarChart(HashMap<String, Integer> map) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		GlobalData ogd = GlobalData.getObject();
		Set<String> keys = map.keySet();

		Iterator it = keys.iterator();

		while (it.hasNext()) {

			String value = it.next() + "";

			dataSet.setValue(map.get(value),
					"Learned tasks for " + ogd.getProblemTypeSelected(), value
							+ "");

		}
		final JFreeChart chart = ChartFactory.createBarChart3D(
				ogd.getProblemTypeSelected(), // chart title
				"Learned Concepts", // domain axis label
				"Count", // range axis label
				dataSet, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// domainAxis.setLabel("Hello");
		// domainAxis.setCategoryLabelPositionOffset(32);

		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setMaximumBarWidth(.05);

		final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.green,
				0.0f, 0.0f, Color.lightGray);

		renderer.setSeriesPaint(0, gp0);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));

		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		numberaxis.setUpperBound(5);
		numberaxis.setLowerBound(0);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		String filePath = request.getRealPath("/images");

		File f = new File(filePath, "graph.jpeg");
		try {

			ChartUtilities.saveChartAsJPEG(f, chart, 500, 500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return chart;
	}

	public JFreeChart generatemultipleBarChart(
			HashMap<String, HashMap<String, Integer>> map) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		Set<String> keys = map.keySet();

		Iterator it = keys.iterator();

		while (it.hasNext()) {

			String label = it.next() + "";
			HashMap<String, Integer> map1 = map.get(label);

			Set<String> key1 = map1.keySet();
			Iterator it1 = key1.iterator();

			while (it1.hasNext()) {

				String label1 = it1.next() + "";

				dataSet.setValue(map1.get(label1), label1, label);

			}

		}
		final JFreeChart chart = ChartFactory.createBarChart3D(
				"Learning Profile of Mensuration Domain", // chart title
				"Problem Type", // domain axis label
				"Count", // range axis label
				dataSet, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setMaximumBarWidth(.10);

		final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.cyan,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp4 = new GradientPaint(0.0f, 0.0f, Color.magenta,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp5 = new GradientPaint(0.0f, 0.0f, Color.pink,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp6 = new GradientPaint(0.0f, 0.0f, Color.yellow,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp7 = new GradientPaint(0.0f, 0.0f, Color.orange,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp8 = new GradientPaint(0.0f, 0.0f, Color.darkGray,
				0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setSeriesPaint(3, gp3);
		renderer.setSeriesPaint(4, gp4);
		renderer.setSeriesPaint(5, gp5);
		renderer.setSeriesPaint(6, gp6);
		renderer.setSeriesPaint(7, gp7);
		renderer.setSeriesPaint(8, gp8);
       
		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 3.0));

		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		numberaxis.setUpperBound(5);
		numberaxis.setLowerBound(0);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		String filePath = request.getRealPath("/images");

		File f = new File(filePath, "graph1.jpeg");
		try {

			ChartUtilities.saveChartAsJPEG(f, chart, 500, 500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return chart;
	}

	public JFreeChart generatLineChart(HashMap<String, Integer> learningmap,
			HashMap<String, Integer> solvingMap) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		Set<String> keys = learningmap.keySet();

		Iterator it = keys.iterator();

		while (it.hasNext()) {

			String label = it.next() + "";

			System.out.println("inside chart generation:"
					+ learningmap.get(label));
			dataSet.addValue(learningmap.get(label), "Time spent on Learning",
					label);

		}

		keys = solvingMap.keySet();

		it = keys.iterator();

		while (it.hasNext()) {

			String label = it.next() + "";
			System.out.println("inside chart generation:"
					+ solvingMap.get(label));
			dataSet.addValue(solvingMap.get(label), "Time spent on Solving",
					label);

		}
		final JFreeChart chart = ChartFactory.createLineChart("Activity", // chart
																			// title
				"Problem Type", // domain axis label
				"Time(sec)", // range axis label
				dataSet, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		chart.setBackgroundPaint(Color.white);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.black);

		LineAndShapeRenderer renderer = new LineAndShapeRenderer();

		plot.setRenderer(renderer);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 3.0));

		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		String filePath = request.getRealPath("/images");

		File f = new File(filePath, "graph2.jpeg");
		try {

			ChartUtilities.saveChartAsJPEG(f, chart, 500, 500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return chart;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}



	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}
