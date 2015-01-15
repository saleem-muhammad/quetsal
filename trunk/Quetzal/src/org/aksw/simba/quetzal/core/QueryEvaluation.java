package org.aksw.simba.quetzal.core;
import java.net.MalformedURLException;
import java.util.Collection;

import org.openrdf.OpenRDFException;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.QueryParser;
import org.openrdf.query.parser.sparql.SPARQLParserFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.RepositoryConfig;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.config.RepositoryImplConfig;
import org.openrdf.repository.http.HTTPRepository;
import org.openrdf.repository.manager.RemoteRepositoryManager;
import org.openrdf.repository.manager.RepositoryManager;
import org.openrdf.repository.sail.config.SailRepositoryConfig;
import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;
import org.openrdf.sail.config.SailImplConfig;
import org.openrdf.sail.federation.Federation;
import org.openrdf.sail.memory.config.MemoryStoreConfig;
public class QueryEvaluation {

	public static void main(String[] args) throws RepositoryException, RepositoryConfigException, SailException, MalformedQueryException {
		Federation fed = new Federation();
		String sesameServer = "http://localhost:8891/sparql";
		String repositoryID = "example-db";
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		fed.addMember(repo);
		 sesameServer = "http://localhost:8897/sparql";
		repositoryID = "example-db1";
		Repository repo1 = new HTTPRepository(sesameServer, repositoryID);
		repo1.initialize();
		fed.addMember(repo);
		fed.initialize();
		//repo.initialize();
//		RepositoryConnection con =  repo.getConnection();
//		
//		
	//	String serverUrl = "http://localhost:8080/openrdf-sesame/repositories/UOBM-univ0";
		//RemoteRepositoryManager manager = new RemoteRepositoryManager(serverUrl);
		//manager.initialize();
		String sparqlQuery = "SELECT ?predicate ?object WHERE { "
				+ "{ "
				+ "  SERVICE <http://localhost:8891/sparql>  {  <http://dbpedia.org/resource/Barack_Obama> ?predicate ?object   } "
				+ "}"
				+ " UNION  {"
				+ "    SERVICE <http://localhost:8897/sparql> "
				+ "     { "
				+ "       ?subject <http://www.w3.org/2002/07/owl#sameAs> <http://dbpedia.org/resource/Barack_Obama> ."
				+ "        ?subject ?predicate ?object "
				+ "    } "
				+ "  } "
				+ "}";
	
		
		SPARQLParserFactory factory = new SPARQLParserFactory();
		QueryParser parser = factory.getParser();
		ParsedQuery parsedQuery = parser.parseQuery(sparqlQuery, null);
		TupleExpr tupleExpr = parsedQuery.getTupleExpr();
		SailConnection conn = fed.getConnection();
		conn.evaluate(tupleExpr, null, null, false);
		
		//Federation.addMember(repo);
//		String url = "http://localhost:8080/openrdf-sesame/repositories/UOBM-univ0";
//		RepositoryManager manager  = RepositoryProvider.getRepositoryManager(url);
//		manager.initialize();
		//int size = manager.getAllRepositories().size();
	//	System.out.println(manager.getAllRepositories());
		
		// create a configuration for the SAIL stack
		SailImplConfig backendConfig = new MemoryStoreConfig();
		// create a configuration for the repository implementation
		RepositoryImplConfig repositoryTypeSpec = new SailRepositoryConfig(backendConfig);
		String repositoryId = "test-db1";
		RepositoryConfig repConfig = new RepositoryConfig(repositoryId, repositoryTypeSpec);
	//	manager.addRepositoryConfig(repConfig);

//     System.out.println(size);
	}
	void federate(RepositoryManager manager, String fedID, String description, 
	        Collection<String> memberIDs, boolean readonly, boolean distinct) 
	        throws MalformedURLException, OpenRDFException {
	    if (manager.hasRepositoryConfig(fedID)) {
	        System.err.println(fedID + " already exists.");
	    }
	    else if (validateMembers(manager, readonly, memberIDs)) {
	      //  RepositoryManagerFederator rmf = 
	       //     new RepositoryManagerFederator(manager);
	        //rmf.addFed(fedID, description, memberIDs, readonly, distinct);
	        System.out.println("Federation created.");
	    }
	}

	boolean validateMembers(RepositoryManager manager, boolean readonly, 
	         Collection<String> memberIDs) 
	         throws OpenRDFException {
	    boolean result = true;
	    for (String memberID : memberIDs) {
	        if (manager.hasRepositoryConfig(memberID)) {
	            if (!readonly) {
	                if (!manager.getRepository(memberID).isWritable()) {
	                    result = false;
	                    System.err.println(memberID + " is read-only.");
	                }
	            }
	        }
	        else {
	           result = false;
	           System.err.println(memberID + " does not exist.");
	        }
	    }
	    return result;
	}
}
