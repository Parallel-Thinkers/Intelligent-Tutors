package dataAccess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
//import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;


//Implemented as a singleton class so that data loaded once can be
//re-used within the entire program without any problems
public class OwlAccess 
{ 
	
	//Paths and default values given at one place as modification becomes easier
	String ont = "http://www.owl-ontologies.com/MenDomOntology.owl";
	OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC ); // PelletReasonerFactory.THE_SPEC
	OntModel model2 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
	OntModel model3= ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_TRANS_INF, null);
	//ClassLoader classLoader = getClass().getClassLoader();
	//URL file = classLoader.getResource("file1/MenDomOnto.owl");
	String readpath = "/home/shan/Final_Monto/MenDomOnto.owl";
	String writepath = "/home/shan/Final_Monto/MenDomOnto.owl";
	
	
	//This is the only object created for this class OwlAccess
	//as it is made as a singleton class
	static OwlAccess soaObj = new OwlAccess();
	
	
	//Data loading is done in constructor
	public OwlAccess()
	{
		FileInputStream in = null;
		FileInputStream in2 = null;
		FileInputStream in3 = null;
		try
		{
			//FileOutputStream out1 = new FileOutputStream(writepath);
			//model.write(out1);
			//out1.close();
			in = new FileInputStream(readpath);
			in2 = new FileInputStream(readpath);
			
			model.read(in, ont);
			model2.read(in2, ont);
			
			model.prepare();
			model2.prepare();
			in.close();
			in2.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//This functions is used to get an object of OwlAccess class.
	//Only one object is created so only that will be returned always.
	
	public static void refreshObject()
	{
		soaObj = new OwlAccess();
	}
	
	public static OwlAccess getObject()
	{
		try
		{
			if(soaObj!=null)
			{
				return soaObj;
			}
			else
				throw new Error("Problem with singleton object!");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	//Creates a new class adds it in model and returns its handler to the calling function
	public OntClass createClass(String className)
	{
		className=ont+"#"+className;
		try
		{
			if(model.getOntClass(className)==null)//class not exists
			{
				model.createClass(className);
			}
			return model.getOntClass(className);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	//Creates a new individual to a class adds it to model and returns its handler to the calling function
	public Individual createInstance(String indvName, String className)
	{
		
		indvName = ont+"#"+indvName;
		className = ont+"#"+className;
		System.out.println("check inside create instance:::"+indvName + className);
		try
		{
			if(model.getOntClass(className)!=null)//class exists
			{
                
System.out.println("first if");
				if(model.createIndividual(indvName, model.getOntClass(className))==null)//individual not exists
				{
					System.out.println("second if");
//					System.out.println("ddd"+model3.createIndividual(indvName, model3.getOntClass(className)));
					model.createIndividual(indvName, model.getOntClass(className));
					//model2.prepare();
					//model2.write(System.out);
				}
				FileOutputStream out = null;
				out = new FileOutputStream(writepath);
				model.write(out);
				out.close();
				//model.close();
				return model.getIndividual(indvName);
			}
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	//all remove functionalities and add functionalities should check for functional
	//and return an error as functions are not written for functional
	
	//add an existing object property at instance level returns 0 for success and -1 for failure
	public int addObjProperty(String indv1Name, String propertyName, String indv2Name)
	{
		indv1Name = ont + "#" + indv1Name;
		propertyName = ont + "#" + propertyName;
		indv2Name = ont + "#" + indv2Name;
		try
		{
			if(model.getObjectProperty(propertyName)!=null && model.getIndividual(indv1Name)!=null && model.getIndividual(indv2Name)!=null)//all input are valid
			{
				if((model.getObjectProperty(propertyName).isFunctionalProperty() && !model.getIndividual(indv1Name).hasProperty(model.getObjectProperty(propertyName)))||(!model.getObjectProperty(propertyName).isFunctionalProperty()))
				{
					model.getIndividual(indv1Name).addProperty(model.getObjectProperty(propertyName), model.getIndividual(indv2Name));
					model.prepare();
					return 0;
				}
				else
					return -1;
			}
			if(model2.getObjectProperty(propertyName)!=null && model2.getIndividual(indv1Name)!=null && model2.getIndividual(indv2Name)!=null)//all input are valid
			{
				if((model2.getObjectProperty(propertyName).isFunctionalProperty() && !model2.getIndividual(indv1Name).hasProperty(model2.getObjectProperty(propertyName)))||(!model2.getObjectProperty(propertyName).isFunctionalProperty()))
				{
					model2.getIndividual(indv1Name).addProperty(model2.getObjectProperty(propertyName), model2.getIndividual(indv2Name));
					model2.prepare();
					return 0;
				}
				else
					return -1;
			}
			
			else
				return -1;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	
	
	//Added by debargha
	public void updateOpenBugCount(String bugUri)
	{
		try
		{
		String bugUri1 = ont + "#" + bugUri;
		
		String property = ont+"#"+"OpenBug";
		String property1 = ont+"#"+"timeStamp";
		Individual  i = model.getIndividual(bugUri1);
		
		Property p = model.getDatatypeProperty(property);
		Property p1 = model.getDatatypeProperty(property1);
		System.out.println("Property:"+p.toString()+"individual:"+i.toString());
		int count= i.getProperty(p).getInt(); 
		System.out.println("count:"+count);
		
		i.getProperty(p).changeLiteralObject(++count);
		Date d = new Date();
		
		if(i.getProperty(p1)!=null)
		 i.getProperty(p1).changeLiteralObject(d.getTime());
		
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void updateResolvedBugCount(String bugUri)
	{
		try
		{
		String bugUri1 = ont + "#" + bugUri;
		
		String property = ont+"#"+"resolvedBug";
		String property1 = ont+"#"+"timeStamp";
		Individual  i = model.getIndividual(bugUri1);
		
		Property p = model.getDatatypeProperty(property);
		Property p1 = model.getDatatypeProperty(property1);
		System.out.println("Property:"+p.toString()+"individual:"+i.toString());
		int count= i.getProperty(p).getInt(); 
		System.out.println("count:"+count);
		
		i.getProperty(p).changeLiteralObject(++count);
		Date d = new Date();
		
		if(i.getProperty(p1)!=null)
		 i.getProperty(p1).changeLiteralObject(d.getTime());
		
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//addition for activity
	
	public void updateTimestamp(String probUri,String property)
	{
		try
		{
		
		System.out.println("inside update timestamp:"+probUri+" "+property);	
		String probUri1 = ont + "#" + probUri;
		
		
		String property1 = ont+"#"+property;
		
		Individual  i = model.getIndividual(probUri1);
		System.out.println("individual:"+i.toString());
		Property p = model.getDatatypeProperty(property1);
		
		System.out.println("inside update timestamp Property:"+p.toString());
		
		i.addProperty(p, new Date().getTime()+"");
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//end of addition
	
	//end of additin
	
	//add an existing datatype property at instance level returns 0 for success and -1 for failure
	public int addDtProperty(String indv1Name, String propertyName, String value, XSDDatatype datatype)
	{System.out.println("I M ADDING DTP::");
		indv1Name = ont + "#" + indv1Name;
		propertyName = ont + "#" + propertyName;
		try
		{
			if(model.getDatatypeProperty(propertyName)!=null && model.getIndividual(indv1Name)!=null)//all input are valid
			{
				if((model.getDatatypeProperty(propertyName).isFunctionalProperty() && !model.getIndividual(indv1Name).hasProperty(model.getDatatypeProperty(propertyName)))||(!model.getDatatypeProperty(propertyName).isFunctionalProperty()))
				{System.out.println("I M ADDING PROP::");
					model.getIndividual(indv1Name).addProperty(model.getDatatypeProperty(propertyName), value, datatype);
					FileOutputStream out = null;
					out = new FileOutputStream(writepath);
					model.write(out);
					out.close();
					return 0;
				}
				else
					return -1;
			}
			else
				return -1;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	
	//is class1name is same as domain? and class2Name same as range?
	//create new object property and add it to model, returns a handler to the newly created property
	public ObjectProperty createObjProperty(String class1Name, String propertyName, String class2Name , String domain, String range)
	{
		class1Name = ont + "#" + class1Name;
		propertyName = ont + "#" + propertyName;
		class2Name = ont + "#" + class2Name;
		domain = ont + "#" + domain;
		range = ont + "#" + range;
		try
		{
			if(model.getOntClass(class1Name) != null && model.getOntClass(class2Name) != null && model.getObjectProperty(propertyName) != null)
			{
				model.getOntClass(class1Name).addProperty(model.createObjectProperty(propertyName), model.getOntClass(class2Name));
				//model.getObjectProperty(propertyName).addDomain(model.getOntClass(domain));
				//model.getObjectProperty(propertyName).addRange(model.getOntClass(range));
				return model.getObjectProperty(propertyName);
			}
			else
				return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	//is class1Name same as domain? and datatype same as range?
	//create new datatype property and add it to model, returns handler to the newly created property
	/*public DatatypeProperty createDtProperty(String class1Name, String propertyName, XSDDatatype datatype, String domain, String range)
	{//not working
		class1Name = ont + "#" + class1Name;
		propertyName = ont + "#" + propertyName;
		domain = ont + "#" + domain;
		range = ont + "#" + range;
		try
		{
			if(model.getOntClass(class1Name) != null && model.getDatatypeProperty(propertyName) != null)
			{
				model.getOntClass(class1Name).addProperty(model.createDatatypeProperty(propertyName), "", datatype);
				//model.getDatatypeProperty(ont+"#"+propertyName).addDomain(model.getOntClass(ont+"#"+domain));
				//model.getDatatypeProperty(ont+"#"+propertyName).addRange(model.getOntClass(ont+"#"+range));
				return model.getDatatypeProperty(ont+"#"+propertyName);
			}
			else
				return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}*/
	
	//remove object and datatype property from instance, returns 0 for success and -1 for failure
	public int removeProperty(String propertyName, String instanceName)
	{
		propertyName = ont+"#"+propertyName;
		instanceName = ont+"#"+instanceName;
		try
		{
		Resource sub = model.getIndividual(instanceName).asResource();
		Property predicate = model.getProperty(propertyName);
	    sub.removeAll(predicate);
	    
	    sub = model2.getIndividual(instanceName).asResource();
	    predicate = model2.getProperty(propertyName);
	    sub.removeAll(predicate);
		}
		/*RDFNode object = sub.getProperty(predicate).getObject();
		
		StmtIterator iter = model.listStatements(sub, predicate, object);
			while(iter.hasNext())
			{
				Statement stmt  = iter.nextStatement(); 
				System.out.println("Statement in removeproperty debargha:"+stmt.toString());
				stmt.remove();
			}
			
			
		}*/
		catch(Exception ex)
		{
		   ex.printStackTrace();	
		}
	
		
		
	/*	boolean flagbool=false;
		try
		{
			StmtIterator iter = model.listStatements();
			Statement stmt;
			Property  predicate;
			Resource subject;
			while (iter.hasNext()) 
			{
				stmt      = iter.nextStatement();         // get next statement
				subject = stmt.getSubject();
				predicate = stmt.getPredicate(); // get the predicate
				if(predicate.toString().contains(propertyName)&&subject.toString().contains(instanceName))
				{
					System.out.println("Statement in removeproperty:"+stmt.toString());
					stmt.remove();
					flagbool=true;
		         	//break;
				}
			}
			
		
			if(flagbool)
				return 0;
			else
				return -1;*/
		
		return 1;
		
	}
	
	//remove datatype and object property from class, returns 0 for success and -1 for failure
	public int deleteProp(String propertyName)
	{
		boolean flagbool=false;
		propertyName = ont+"#"+propertyName;
		try
		{
			StmtIterator iter = model.listStatements();
			Statement stmt = null;
			Resource subject;
			Property  predicate;
			RDFNode object;
			while (iter.hasNext()) 
			{
				stmt = iter.nextStatement();         // get next statement
				subject = stmt.getSubject();
				predicate = stmt.getPredicate(); // get the predicate
				object = stmt.getObject();
				if(predicate.toString().contains(propertyName)||subject.toString().contains(propertyName)||object.toString().contains(propertyName))
				{
		         	stmt.remove();
		         	flagbool=true;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		if(flagbool==true)
			return 0;
		else
			return -1;
	}
	
	//runs the query passed to the function and return resultset
	public ResultSet runQueryResultset(String querystring)
	{
		Query query1;
		QueryExecution qe1;
		ResultSet resultsnew1;
		try
		{
			query1 = QueryFactory.create(querystring);
			qe1 = QueryExecutionFactory.create(query1, model);
			resultsnew1 = qe1.execSelect();
			//ResultSetFormatter.out(System.out, resultsnew1, query1);
			return resultsnew1;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	

	//runs the query passed to the function and return arraylist
	public ArrayList<String> runQueryList(String querystring)
	{
		ArrayList<String> list1 = new ArrayList<String>();
		System.out.println("quesry string:"+querystring);
		Query query1;
		QueryExecution qe1;
		ResultSet resultsnew1;
		try
		{
			query1 = QueryFactory.create(querystring);
			qe1 = QueryExecutionFactory.create(query1, model);
			resultsnew1 = qe1.execSelect();
			System.out.println(querystring);
			while(resultsnew1.hasNext())
			{
				QuerySolution qs = resultsnew1.nextSolution();
				
				list1.add(qs.toString());
			}
			
			return list1;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list1;
	}
	
	//runs the query passed to the function and return arraylist
		public ArrayList<String> runQueryListwithoutreasoner(String querystring)
		{
			ArrayList<String> list1 = new ArrayList<String>();
			System.out.println("quesry string:"+querystring);
			Query query1;
			QueryExecution qe1;
			ResultSet resultsnew1;
			try
			{
				query1 = QueryFactory.create(querystring);
				qe1 = QueryExecutionFactory.create(query1, model2);
				resultsnew1 = qe1.execSelect();
				System.out.println(querystring);
				while(resultsnew1.hasNext())
				{
					QuerySolution qs = resultsnew1.nextSolution();
					
					list1.add(qs.toString());
				}
				
				return list1;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return list1;
		}
	
	public ArrayList<String> runQueryListSansReasoner(String querystring)
	{
		ArrayList<String> list1 = new ArrayList<String>();
		Query query1;
		QueryExecution qe1;
		ResultSet resultsnew1;
		
		try
		{
			query1 = QueryFactory.create(querystring);
			qe1 = QueryExecutionFactory.create(query1, model2);
			resultsnew1 = qe1.execSelect();
			System.out.println(querystring);
			//printModel(model2);
			while(resultsnew1.hasNext())
			{
				QuerySolution qs = resultsnew1.nextSolution();
				list1.add(qs.toString());
			}
			
			return list1;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list1;
	}
	
	//Iterate over triples to find defined classes in a model, returns it in array list
	public ArrayList<String> printClassnames()
	{
		try
		{
			ArrayList<String> list = new ArrayList<String>();
			ExtendedIterator i = null;
			i = model.listClasses();
			while(i.hasNext()) 
			{
				Resource val = (Resource) i.next();
				list.add(val.getLocalName());
				System.out.println(val.getLocalName());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	//Iterate over triples to find defined instances of a given class in a model, returns it in array list
	public ArrayList<String> printInstances(String classname)
	{
		try
		{
			ArrayList<String> list = new ArrayList<String>();
			ExtendedIterator i = null;
			i = model.getOntClass(ont+"#"+classname).listInstances();
			while(i.hasNext()) 
			{
				Resource val = (Resource) i.next();
				list.add(val.getLocalName());
				System.out.println(val.getLocalName());
			}
			return list;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	//print all object properties, return arraylist
	public ArrayList<String> printObjectproperties()
	{
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			StmtIterator iter = model.listStatements();
			Statement stmt = null;
			Resource  subject;
			Property  predicate;
			RDFNode   object;
			while (iter.hasNext()) 
			{
				stmt      = iter.nextStatement();         // get next statement
				subject   = stmt.getSubject();   // get the subject
				predicate = stmt.getPredicate(); // get the predicate
				object    = stmt.getObject();    // get the object
				if(subject.toString().contains(ont)&&predicate.toString().contains("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")&&object.toString().contains("http://www.w3.org/2002/07/owl#ObjectProperty"))
		        {
		         	list.add(subject.toString().substring(subject.toString().lastIndexOf("#")+1));
		         	System.out.println(subject.toString().substring(subject.toString().lastIndexOf("#")+1));
		        }
			}
			return list;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}			

	//print all datatype properties, return arraylist
	public ArrayList<String> printDatatypeproperties()
	{
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			StmtIterator iter = model.listStatements();
			Statement stmt = null;
			Resource  subject;
			Property  predicate;
			RDFNode   object;
			while (iter.hasNext()) 
			{
				stmt      = iter.nextStatement();         // get next statement
				subject   = stmt.getSubject();   // get the subject
				predicate = stmt.getPredicate(); // get the predicate
				object    = stmt.getObject();    // get the object
				if(subject.toString().contains(ont)&&predicate.toString().contains("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")&&object.toString().contains("http://www.w3.org/2002/07/owl#DatatypeProperty"))
				{
					list.add(subject.toString().substring(subject.toString().lastIndexOf("#")+1));
					System.out.println(subject.toString().substring(subject.toString().lastIndexOf("#")+1));
				}
			}
			return list;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public int printModel(OntModel model)
	{
		model.write(System.out);
		return 0;
	}
	
	//do print functions have to be written specifically using iterate methods only rather than sparql? will that solve that multiple values problem?
	
	public int closeModel(int choice)//choice=0 do not write model to file else write model to file
	{
		try
		{
			if(choice!=0)
			{
				FileOutputStream out = null;
				out = new FileOutputStream(writepath);
				model.write(out);
				out.close();
			}
			model.close();
			model2.close();
			return 0;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
}