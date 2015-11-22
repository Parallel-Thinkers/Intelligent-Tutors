package datastructures;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import dataAccess.OwlAccess;

public class Tables {
	public static void refreshObject() {
		tables = new Tables();
	}

	public String fullformula = "";

	public class EntityTableEntry {
		String lhs;
		String value;
		String unit;

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getLhs() {
			return lhs;
		}

		public void setLhs(String lhs) {
			this.lhs = lhs;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public class SystemEntityTableEntry extends EntityTableEntry {

	}

	public class UserEntityTableEntry extends EntityTableEntry {
		String variableName;

		public String getVariableName() {
			return variableName;
		}

		public void setVariableName(String variableName) {
			this.variableName = variableName;
		}
	}

	public class unitds {
		String src;
		String dest;
		String formula;

		public String getSrc() {
			return src;
		}

		public void setSrc(String src) {
			this.src = src;
		}

		public String getDest() {
			return dest;
		}

		public void setDest(String dest) {
			this.dest = dest;
		}

		public String getFormula() {
			return formula;
		}

		public void setFormula(String formula) {
			this.formula = formula;
		}
	}

	public ArrayList<unitds> unitTable = new ArrayList<unitds>();
	GlobalData ogd = GlobalData.getObject();
	public ArrayList<SystemEntityTableEntry> systemEntityTable = new ArrayList<SystemEntityTableEntry>();
	public ArrayList<UserEntityTableEntry> userEntityTable = new ArrayList<UserEntityTableEntry>();

	static Tables tables = new Tables();

	private Tables() {

	}

	String givenArea;

	public String getGivenArea() {
		return givenArea;
	}

	public void setGivenArea(String givenArea) {
		this.givenArea = givenArea;
	}

	public String getToFindArea() {
		return toFindArea;
	}

	public void setToFindArea(String toFindArea) {
		this.toFindArea = toFindArea;
	}

	public String getSolutionArea() {
		return solutionArea;
	}

	public void setSolutionArea(String solutionArea) {
		this.solutionArea = solutionArea;
	}

	String toFindArea;
	String solutionArea;

	class SchemaStructure {
		String schematic;
		String semantic;
		String detail;
	}

	public boolean flag = false;
	public int flag2 = 0;

	public ArrayList<SchemaStructure> schemaStructureList = new ArrayList<SchemaStructure>();

	public String fullschema = "";
	public String fulldetails = "";

	public static Tables getObject() {
		return tables;
	}

	public void putExpectedBugs(String strTask) {
		String querystring = "select ?b where { "
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ strTask
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasBugs> ?b. }";
		System.out.println("debargha inside put expected bugs:" + strTask);
		OwlAccess o = OwlAccess.getObject();
		ArrayList<String> bugName = new ArrayList<String>();
		bugName = o.runQueryList(querystring);
		if (bugName.size() > 0) {
			for (int i = 0; i < bugName.size(); i++) {

				bugName.set(
						i,
						bugName.get(i).substring(
								bugName.get(i).indexOf("#") + 1,
								bugName.get(i).indexOf(">")));
				System.out.println("Bug name is: " + bugName.get(i));
				o.addObjProperty(ogd.getUsername(), "capturedBugs",
						bugName.get(i));
				o.updateOpenBugCount(bugName.get(i));

			}
		}
	}

	public void loadSchemaStructure() {
		fullschema = "";
		fulldetails = "";
		System.out.println("Am loading schema structure ");
		GlobalData ogd = GlobalData.getObject();
		OwlAccess o = OwlAccess.getObject();
		ArrayList<String> schemaList = new ArrayList<String>();
		/*
		 * String querystring =
		 * "select ?var ?f ?i where { ?var <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://www.owl-ontologies.com/MenDomOntology.owl#PS_"
		 * + ogd.getProblemTypeSelected() +"> . " +
		 * "?var2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?var ."+
		 * "?var2 <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
		 * + "?f <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?i"+ "}";
		 */

		String querystring = "select ?schema where { <http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStructure> ?st . "
				+ "?st <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?schema ."
				+ "}";
		System.out.println("About to run schemastructure query");
		schemaList = o.runQueryListSansReasoner(querystring);
		System.out.println("schemastructure query answer size "
				+ schemaList.size());
		for (int i = 0; i < schemaList.size(); i++) {

			System.out.println("Schemalist is" + schemaList.get(i));
			// parse array list and add into scst here
			String tempStr = schemaList.get(i);
			
			tempStr = tempStr.substring(tempStr.indexOf("#") + 1,
					tempStr.indexOf(">"));

			// list all instances
			if (!fullschema.contains(tempStr)) {
				String query = "select ?var2 where {?var2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#"
						+ tempStr + "> }";
				ArrayList<String> result = o.runQueryList(query);
				tempStr=tempStr.replace('_', ' ');
				fullschema = fullschema + tempStr + "\n";
				for (String res : result) {
					res = res.substring(res.indexOf("#") + 1,
							res.indexOf(">"));
					if (res.contains(ogd.getProblemUri())) {
						

						query = "select  ?f ?i where {<http://www.owl-ontologies.com/MenDomOntology.owl#"
								+ res
								+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
								+ "?f <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?i"
								+ "}";

						ArrayList<String> semantic = o
								.runQueryListwithoutreasoner(query);

						for (String s : semantic) {

							System.out.println("semantic string:" + s);
							s=s.replace('_', ' ');
							String feature = s.substring(s.indexOf("#") + 1,
									s.indexOf(">"));

							s = s.substring(s.indexOf("?i"));

							String type = s.substring(s.indexOf("#") + 1,
									s.indexOf(">"));
							
							fulldetails += tempStr + " : " + feature + "("
									+ type + ")\n";

						}

					}

				}
			}
		}
		System.out.println("full schema is: " + fullschema);
		System.out.println("full details is: " + fulldetails);
		System.out.println("Size of schema list after parsing is: "
				+ schemaStructureList.size());
	}

	public void loadFormula() {
		fullformula = "";
		OwlAccess o = OwlAccess.getObject();

		ArrayList<String> formulaList = new ArrayList<String>();

		/*
		 * String querystring =
		 * "select ?formula ?d where { ?formula <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.owl-ontologies.com/MenDomOntology.owl#Formula>. "
		 * +
		 * "?formula <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?d. "
		 * + "}";
		 */

		String querystring = "select ?f ?d where "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#formulaeUsed> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#discription> ?d"
				+ "}";
		formulaList = o.runQueryList(querystring);

		for (int i = 0; i < formulaList.size(); i++) {
			String formula;
			String desc;
			formula = formulaList.get(i).substring(
					formulaList.get(i).indexOf("#") + 1,
					formulaList.get(i).indexOf(">"));
			desc = formulaList.get(i).substring(
					formulaList.get(i).indexOf("\"") + 1,
					formulaList.get(i).lastIndexOf("\""));
			System.out.println("formula list is" + formulaList.get(i));
			fullformula = fullformula + formula + " : " + desc + "\n\n";
		}
		System.out.println("Full formula is: " + fullformula);
	}

	public void loadSystemete() {
		Tables t = Tables.getObject();
		loadGiven();
		loadTasks();
		loadUnitTable();
		System.out.println(t.systemEntityTable.size());
		for (int i = 0; i < t.systemEntityTable.size(); i++) {
			System.out.println(t.systemEntityTable.get(i).getLhs() + " "
					+ t.systemEntityTable.get(i).getValue());
		}
	}

	public void loadGiven() {
		Tables t = Tables.getObject();
		// Load task lines as well to sysete also for parallel strategies,
		// if tasks are repeated make sure the task is entered only once
		GlobalData ogd = GlobalData.getObject();
		String querystring = "select ?r ?v ?u  "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasGivenExpression> ?ge ."
				+ "?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasOneDUnit> ?u."
				+ "}";

		System.out.println("Debargha query string:" + querystring);
		ResultSet resultsnew1;
		try {
			OwlAccess o = OwlAccess.getObject();
			resultsnew1 = o.runQueryResultset(querystring);
			System.out.println(querystring);
			while (resultsnew1.hasNext()) {
				QuerySolution qs = resultsnew1.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = qs.toString();
				System.out.println("tqs " + tqs);
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				System.out.println("tlhs " + tlhs);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				System.out.println("tval " + tval);
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				System.out.println("tqs " + tqs);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				System.out.println("tunit " + tunit);
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();
				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				System.out.println(tlhs + tval + tunit);
				if (!checksysete(tlhs, tval, tunit)) {
					t.systemEntityTable.add(sete);
					ogd.givenCount++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 2 D
		querystring = "select ?r ?v ?u "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasGivenExpression> ?ge ."
				+ "?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasTwoDUnit> ?u."
				+ "}";
		ResultSet resultsnew2;
		try {
			OwlAccess o = OwlAccess.getObject();
			resultsnew2 = o.runQueryResultset(querystring);
			System.out.println(querystring);
			while (resultsnew2.hasNext()) {
				QuerySolution qs = resultsnew2.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = qs.toString();
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();
				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				if (!checksysete(tlhs, tval, tunit)) {
					t.systemEntityTable.add(sete);
					ogd.givenCount++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 3 D
		querystring = "select ?r ?v ?u "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasGivenExpression> ?ge ."
				+ "?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasThreeDUnit> ?u."
				+ "}";
		ResultSet resultsnew3;
		try {
			OwlAccess o = OwlAccess.getObject();
			resultsnew3 = o.runQueryResultset(querystring);
			System.out.println(querystring);
			while (resultsnew3.hasNext()) {
				QuerySolution qs = resultsnew3.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = qs.toString();
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();
				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				if (!checksysete(tlhs, tval, tunit)) {
					t.systemEntityTable.add(sete);
					ogd.givenCount++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 0 D
		querystring = "select ?r ?v ?u "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasGivenExpression> ?ge ."
				+ "?ge <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasZeroDUnit> ?u."
				+ "}";
		ResultSet resultsnew4;
		try {
			OwlAccess o = OwlAccess.getObject();
			resultsnew4 = o.runQueryResultset(querystring);
			System.out.println(querystring);
			while (resultsnew4.hasNext()) {
				QuerySolution qs = resultsnew4.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = qs.toString();
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();
				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				if (!checksysete(tlhs, tval, tunit)) {
					t.systemEntityTable.add(sete);
					ogd.givenCount++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadTasks() {
		Tables t = Tables.getObject();
		String taskquerystring = "select ?r ?v ?u "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s ."
				+ "?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t ."
				+ "?t <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasOneDUnit> ?u."
				+ "}";
		ResultSet taskresultsnew1;
		try {
			OwlAccess o = OwlAccess.getObject();
			taskresultsnew1 = o.runQueryResultset(taskquerystring);
			System.out.println(taskquerystring);
			while (taskresultsnew1.hasNext()) {
				QuerySolution taskqs = taskresultsnew1.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = taskqs.toString();
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				// System.out.println(tent+" "+tattr+" "+tval+" "+tunit);
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();

				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				if (!checksysete(tlhs, tval, tunit))
					t.systemEntityTable.add(sete);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 2 D
		taskquerystring = "select ?r ?v ?u "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s ."
				+ "?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t ."
				+ "?t <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasTwoDUnit> ?u."
				+ "}";
		ResultSet taskresultsnew2;
		try {
			OwlAccess o = OwlAccess.getObject();
			taskresultsnew2 = o.runQueryResultset(taskquerystring);
			System.out.println(taskquerystring);
			while (taskresultsnew2.hasNext()) {
				QuerySolution taskqs = taskresultsnew2.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = taskqs.toString();
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				// System.out.println(tent+" "+tattr+" "+tval+" "+tunit);
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();

				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				if (!checksysete(tlhs, tval, tunit))
					t.systemEntityTable.add(sete);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 3 D
		taskquerystring = "select ?r ?v ?u "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s ."
				+ "?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t ."
				+ "?t <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasThreeDUnit> ?u."
				+ "}";
		ResultSet taskresultsnew3;
		try {
			OwlAccess o = OwlAccess.getObject();
			taskresultsnew3 = o.runQueryResultset(taskquerystring);
			System.out.println(taskquerystring);
			while (taskresultsnew3.hasNext()) {
				QuerySolution taskqs = taskresultsnew3.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = taskqs.toString();
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				// System.out.println(tent+" "+tattr+" "+tval+" "+tunit);
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();

				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				if (!checksysete(tlhs, tval, tunit))
					t.systemEntityTable.add(sete);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 0 D
		taskquerystring = "select ?r ?v ?u "
				+ "{"
				+ "<http://www.owl-ontologies.com/MenDomOntology.owl#"
				+ ogd.getProblemUri()
				+ "> <http://www.owl-ontologies.com/MenDomOntology.owl#hasStrategy> ?s ."
				+ "?s <http://www.owl-ontologies.com/MenDomOntology.owl#hasTask> ?t ."
				+ "?t <http://www.owl-ontologies.com/MenDomOntology.owl#hasFeature> ?f ."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasRealWorldAttribute> ?r."
				+ "?f <http://www.owl-ontologies.com/MenDomOntology.owl#hasValueUnit> ?vu."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasValue> ?v."
				+ "?vu <http://www.owl-ontologies.com/MenDomOntology.owl#hasZeroDUnit> ?u."
				+ "}";
		ResultSet taskresultsnew4;
		try {
			OwlAccess o = OwlAccess.getObject();
			taskresultsnew4 = o.runQueryResultset(taskquerystring);
			System.out.println(taskquerystring);
			while (taskresultsnew4.hasNext()) {
				QuerySolution taskqs = taskresultsnew4.nextSolution();
				// qs.toString().substring(0, qs.toString().indexOf("."));//get
				// each part separately here and load in tables
				String tqs = taskqs.toString();
				String tlhs = tqs.substring(tqs.indexOf("\"") + 1,
						tqs.indexOf("^") - 1);
				tqs = tqs.substring(tqs.indexOf("?v") + 6);
				String tval = tqs.substring(0, tqs.indexOf("\""));
				tqs = tqs.substring(tqs.indexOf("?u") + 6);
				String tunit = tqs.substring(tqs.indexOf("#") + 1,
						tqs.indexOf(">"));
				// System.out.println(tent+" "+tattr+" "+tval+" "+tunit);
				Tables.SystemEntityTableEntry sete = t.new SystemEntityTableEntry();

				sete.setLhs(tlhs);
				sete.setValue(tval);
				sete.setUnit(tunit);
				if (!checksysete(tlhs, tval, tunit))
					t.systemEntityTable.add(sete);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadUnitTable() {
		Tables t = Tables.getObject();
		char ch;
		StringBuffer src = new StringBuffer(""), dest = new StringBuffer(""), formula = new StringBuffer(
				"");
		int addin = 0;
		File f = new File("/tmp/ttl/unitInput.txt");
		try {
			FileInputStream fis = new FileInputStream(f);
			while ((ch = (char) fis.read()) != (char) -1) {
				if (ch == '\n') {
					unitds ud = new unitds();
					ud.setSrc(src.toString());
					ud.setDest(dest.toString());
					ud.setFormula(formula.toString());
					t.unitTable.add(ud);
					addin = 0;
					src.delete(0, src.length());
					dest.delete(0, dest.length());
					formula.delete(0, formula.length());
				} else if (ch == '\t') {
					addin++;
				} else if (addin == 0) {
					src.append(ch);
				} else if (addin == 1) {
					dest.append(ch);
				} else if (addin == 2) {
					formula.append(ch);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(t.unitTable.size());
		for (int i = 0; i < t.unitTable.size(); i++) {
			System.out.println(t.unitTable.get(i).getSrc() + " "
					+ t.unitTable.get(i).getDest() + " "
					+ t.unitTable.get(i).getFormula());
		}
	}

	public boolean checksysete(String l, String v, String u) {
		Tables t = Tables.getObject();
		String lhsinp = l;
		for (int i = 0; i < t.systemEntityTable.size(); i++) {
			if ((lhsinp.equals(t.systemEntityTable.get(i).getLhs()))) {
				return true;
			}
		}
		return false;
	}
}