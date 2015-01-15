package org.aksw.simba.quetzal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aksw.sparql.query.algebra.helpers.BGPGroupGenerator;
import org.openrdf.OpenRDFException;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.algebra.Slice;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.parser.ParsedGraphQuery;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.ParsedTupleQuery;
import org.openrdf.query.parser.QueryParser;
import org.openrdf.query.parser.sparql.SPARQLParserFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

public class SPARQLRewriteExample {

	public static void main(String[] args) throws RepositoryException, MalformedQueryException {
		String repositoryID = "example-db";
		 List<String> classes = new ArrayList<String>();
		Repository repo = new HTTPRepository("http://localhost:8890/sparql", repositoryID);
		repo.initialize();
		  RepositoryConnection con = repo.getConnection();	
		String queryStr = "PREFIX drugbank:<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>"
				+ "PREFIX drug-cat:<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugcategory/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX kegg: <http://bio2rdf.org/ns/kegg#>"
				+ "PREFIX bio2rdf: <http://bio2rdf.org/ns/bio2rdf#>"
				+ "PREFIX purl: <http://purl.org/dc/elements/1.1/>"
				+ "SELECT  * "
				+ "				WHERE "
				+ "				  { ?drug drugbank:drugCategory drug-cat:micronutrient .      "
				+ "				    ?keggDrug rdf:type kegg:Drug .                                     "
				+ "				    ?keggDrug bio2rdf:xRef ?id .                                         "
				+ "				    ?drug drugbank:casRegistryNumber ?id .                        "
				+ "				    ?keggDrug purl:title ?title                                     "
				+ "				  } ";
		TupleQuery query = con.prepareTupleQuery(QueryLanguage.SPARQL, queryStr);
		SPARQLParserFactory factory = new SPARQLParserFactory();
		QueryParser parser = factory.getParser();
		ParsedQuery pq = parser.parseQuery(queryStr, null);
				           System.out.println(pq);
				           HashMap<Integer, List<StatementPattern>> bgpGroups =  BGPGroupGenerator.generateBgpGroups(queryStr);
		            if (pq instanceof ParsedTupleQuery || pq instanceof ParsedGraphQuery)
		            {
		                final DetectAndAdaptSlice detectAndAdaptSlice = new DetectAndAdaptSlice(100);
		                try
		                {
		                  
		                	for (Integer no :  bgpGroups.keySet()) 
		        			{
		                		List<StatementPattern> stmts = bgpGroups.get(no);
		                		for(StatementPattern stmt:stmts){
		                			
		                		}
		        			}
		                	System.out.println(pq.getSourceString());
		                	pq.getTupleExpr().visit(detectAndAdaptSlice);
		                } catch (OpenRDFException e)
		                {
		                   System.out.println("Can not change the offset or limit"+e);
		                }
		                if (!detectAndAdaptSlice.isDetected())
		                {
		                    //adapt the logic of the query
		                    Slice sl = new Slice(pq.getTupleExpr(), 0, Math.max(0, 100));
		                    pq.setTupleExpr(sl);
		                    //render the query again into a string
		                    try {
		                 //   String newQuery = 	new SPARQLQueryRenderer().render(pq);
		                   // System.out.println(newQuery);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                }
		            }
		} 

	

}
