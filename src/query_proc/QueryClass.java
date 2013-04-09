package query_proc;

import java.util.Iterator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class QueryClass {
	
	Resultat res;
	
	static final String geonames = "geonames.rdf";
	static final String montpellier = "feature_montpellier.rdf";
	static final String beziers = "feature_beziers.rdf";
	static final String toulouse = "feature_toulouse.rdf";
    static final String NL = System.getProperty("line.separator") ;
    static final String core ="http://www.w3.org/2004/02/skos/core#";
    static final String owl="http://www.w3.org/2002/07/owl#";
	static final String xsd="http://www.w3.org/2001/XMLSchema#";
	static final String ontology="http://www.geonames.org/ontology#";
	static final String prolog1 = "PREFIX core: <"+core+">" ;
	static final String prolog1_1="PREFIX owl:<"+owl+">";
	static final String prolog1_2="PREFIX xsd:<"+xsd+">";
	static final String prolog1_3="PREFIX ontology:<"+ontology+">";
	static final String prolog2 = "PREFIX rdf: <"+RDF.getURI()+">" ;
    static final String prolog3 = "PREFIX rdfs: <"+RDFS.getURI()+">" ;
    //regroupement des prolog
    static final String prolog = prolog1 + NL + prolog1_1 + NL+ prolog1_2 + 
    							NL+ prolog1_3 + NL+ prolog2 + NL + prolog3 + NL;
  
    public static Model m = ModelFactory.createDefaultModel();
    public static InfModel inf_m = ModelFactory.createRDFSModel(m);
    
    public Resultat queryexec(String requette){
    	FileManager.get().readModel( inf_m, geonames );
    	FileManager.get().readModel(inf_m, montpellier);
    	FileManager.get().readModel(inf_m, toulouse);
    	FileManager.get().readModel(inf_m, beziers);
	    Query query = QueryFactory.create(prolog+requette,Syntax.syntaxSPARQL_11) ;
    	res = new Resultat();
        QueryExecution qexec = QueryExecutionFactory.create(query, inf_m) ;
        
        try {
            ResultSet rs = qexec.execSelect() ;            
            Iterator<String> solutionName = rs.next().varNames();
            while(solutionName.hasNext()){
            res.nomSolutions.add(solutionName.next());
            }
            while(rs.hasNext())
            {
               QuerySolution rb = rs.nextSolution() ;
               res.resultvector.add(rb);
            }
        }
        finally{
            // QueryExecution objects should be closed to free any system resources
            qexec.close() ;           
        }
        return res;
    }
}
