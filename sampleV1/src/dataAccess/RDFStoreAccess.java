package dataAccess;

import java.net.URL;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.store.DatasetStore;

public class RDFStoreAccess {
	
	
	static RDFStoreAccess rdfObject=new RDFStoreAccess();
	String ont = "http://www.owl-ontologies.com/MenDomOntology.owl";
	private Store s;
	private RDFStoreAccess()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		URL file = classLoader.getResource("file/sdb.ttl");
		s = SDBFactory.connectStore(file.toString()) ;
		
		
	}
	
	public static RDFStoreAccess refreshObject()
	{
		rdfObject.s.getConnection().close();
		rdfObject.s.close();
		
		rdfObject = new RDFStoreAccess();
		
		return rdfObject;
		
	}
	
	public static RDFStoreAccess getObject()
	{
		try
		{
		if(rdfObject!=null)
			return rdfObject;
		
		else
			throw new Error("Problem with singleton object!");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	
    /*public void addTriple(String subject,String predicate,String object)
    {
    	
    	subject = ont + "#" + subject;
    	predicate = ont + "#" + predicate;
    	object = ont + "#" + object;
    	Node sub = NodeFactory.createLiteral(subject);
    	Node pred = NodeFactory.createLiteral(predicate);
    	Node ob = NodeFactory.createLiteral(object);
    	Model model = SDBFactory.connectDefaultModel(s);
    	Resource r = model.getResource(subject);
    	Property p = r.get
    	
    	System.out.println(t.getSubject());
        model.add(sub,pred,ob);
    	
    }*/
	
	public ResultSet runQueryResultset(String querystring)
	{
		Query query = QueryFactory.create(querystring) ;
	     
	    System.out.println("query string:"+querystring);  
	       
        // Must be a DatasetStore to trigger the SDB query engine.
        // Creating a graph from the Store, and adding it to a general
        // purpose dataset will not necesarily exploit full SQL generation.
        // The right answers will be obtained but slowly.
        
        Dataset ds = DatasetStore.create(s) ;
        QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
        ResultSet rs=null;
        try {
           rs = qe.execSelect() ;
           
        } finally { qe.close() ; }
        
      
       
	   return rs;
	}

	//runs the query passed to the function and return arraylist
	public ArrayList<String> runQueryList(String querystring)
	{
		ArrayList<String> list1 = new ArrayList<String>();
		Query query = QueryFactory.create(querystring) ;
	   
		System.out.println("query string:"+querystring);
	       
	       
        // Must be a DatasetStore to trigger the SDB query engine.
        // Creating a graph from the Store, and adding it to a general
        // purpose dataset will not necesarily exploit full SQL generation.
        // The right answers will be obtained but slowly.
        
        Dataset ds = DatasetStore.create(s) ;
        QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
        ResultSet rs=null;
        try {
           rs = qe.execSelect() ;
           
           while(rs.hasNext())
			{
				QuerySolution qs = rs.nextSolution();
				list1.add(qs.toString());
			}
			
           
        } finally { qe.close() ; }
        
        
		return list1;
	}
	
	
	

}
