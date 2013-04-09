package main;

import query_proc.QueryClass;
import query_proc.Resultat;
import vue.Fenetre;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class App {
	public static Fenetre mywindow;
	public static QueryClass proc1;
	public static Resultat res;
	private void query_affichage(String req2){
		 proc1 = new QueryClass();
		 res = proc1.queryexec(req2);
		 mywindow.tableauRes.setText("\n");
		 for (QuerySolution qs : res.resultvector) {
			 for (String lit : res.nomSolutions) {
				 RDFNode y = qs.get(lit);
				 mywindow.tableauRes.append(lit+" : "+y+"\n");
			 }
			 mywindow.tableauRes.append("---------------------------------------------------- \n");
		 }				
	}
	public void Query_Select(int item){
		switch (item ){
		case 0: String req0= "SELECT ?code ?definition ?label WHERE { ?code rdf:type ontology:Code  ." +
				"?code core:definition ?definition .?code core:prefLabel ?label}";	
				query_affichage(req0);
        break;
		case 1: String req1= "SELECT ?code ?definition WHERE{?code core:inScheme ontology:V . " +
				"?code core:definition ?definition}";
				query_affichage(req1);
        break;
		case 2: String req2="SELECT ?classe (count(DISTINCT *) AS ?nombre) WHERE { ?code rdf:type ontology:Code." +
				" ?code core:inScheme ?classe } GROUP BY ?classe";
				query_affichage(req2);
		break;
		case 3:  String req3="SELECT ?code ?definition WHERE {?code rdf:type ontology:Code ." +
				"?code core:definition ?definition FILTER regex(str(?definition), 'ice','i')}";
				query_affichage(req3);
		break;
		case 4: String req4="SELECT ?feature ?nom ?codePostal ?population ?label ?codeDefinition " +
				"WHERE {?feature rdf:type ontology:Feature .	?feature ontology:featureCode ontology:P.PPLA ." +
				"?feature ontology:population ?population ." +
				"?feature ontology:name ?nom .	?feature ontology:postalCode ?codePostal ." +
				"ontology:P.PPLA core:prefLabel ?label .	ontology:P.PPLA core:definition ?codeDefinition}" ;
				query_affichage(req4);
		break;
		case 5: String req5="SELECT  ?feature ?nom ?propriete ?obj WHERE {?propriete rdf:type owl:ObjectProperty ." +
				" ?feature rdf:type ontology:Feature ." +
				"?feature ontology:name ?nom." +
				" ?feature ?propriete ?obj }" ;
				query_affichage(req5);
	
		}
	}
	public void Query_Select(String query){
		proc1 = new QueryClass();
		 res = proc1.queryexec(query);
		 mywindow.tableauRes2.setText("\n");
		 for (QuerySolution qs : res.resultvector) {
			 for (String lit : res.nomSolutions) {
				 RDFNode y = qs.get(lit);
				 mywindow.tableauRes2.append(lit+" : "+y+"\n");				 
			 }
			 mywindow.tableauRes2.append("---------------------------------------------------- \n");
		 }				
	}
	public static void main(String[] args) {
		App monapp = new App();
		monapp.mywindow = new Fenetre();
		}
}