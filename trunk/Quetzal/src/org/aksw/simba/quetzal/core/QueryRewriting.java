package org.aksw.simba.quetzal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openrdf.query.algebra.StatementPattern;

import com.fluidops.fedx.algebra.StatementSource;

public class QueryRewriting {
	public static  Map<String, List<String>> excGroups = new ConcurrentHashMap<String, List<String>>();

	public static String doQueryRewriting(
			String query, HashMap<Integer, List<StatementPattern>> bgpGroups,
			Map<StatementPattern, List<StatementSource>> stmtToSources) {
		
		for(int bgpKey:bgpGroups.keySet()) 
		{
				excGroups.clear();
			List<StatementPattern>	stmts = bgpGroups.get(bgpKey);
			for(StatementPattern stmt:stmts)
			{			
				String triplePattern = getTriplePattern(stmt);
				List<StatementSource> sources = stmtToSources.get(stmt);
				//System.out.println(triplePattern + " , " +sources);
				if(sources.size()==1)
				{
					String sourceURL ="http://"+ sources.get(0).getEndpointID().replace("sparql_", "").replace("_", "/");
					if(excGroups.containsKey(sourceURL))
					{
						List<String> excStmts = excGroups.get(sourceURL);
						synchronized (excStmts)
						{
							excStmts.add(triplePattern);
						}
					}
					else
					{
						List<String> excStmts = new ArrayList<String>();	
						excStmts.add(triplePattern);
						excGroups.put(sourceURL, excStmts);
					}
				}
				else
				{
					String services = "";
					for(StatementSource src:sources)
					{
						String sourceURL ="http://"+ src.getEndpointID().replace("sparql_", "").replace("_", "/");
						if(services.equals(""))
							services = "{\n   SERVICE  <"+sourceURL + "> { "+triplePattern + " }\n  }";
						else
							services = services + " \n UNION \n  {\n   SERVICE  <"+sourceURL + "> { "+triplePattern + " }\n  }";
					}
					//System.out.println(services+"\n");
					query= query.replace(triplePattern, services);
				}
			}

		//System.out.println(excGroups);
		for(String serviceURL:excGroups.keySet())
		{
			String service ="";
			List<String> triples = excGroups.get(serviceURL);
			if(triples.size()==1)
			{
				service = "{ SERVICE  <"+serviceURL + "> { "+triples.get(0) + " } }";
				query =  query.replace(triples.get(0), service);
			}
			else
			{
				service = "{\n   SERVICE  <"+serviceURL + "> {\n   ";
				int count = 0;
				for(String triple:triples)
				{
					service = service+ triple+"\n   ";
					count++;
					if(count<triples.size())
						query =  query.replace(triple+"\n", "");
					else
					{
						service = service+" }\n  }";
						query = query.replace(triple, service);
					}
				}

			}
			//System.out.println(service);
		}
		}
		//System.out.println(query);
		return query;
	}

	private static String getTriplePattern(StatementPattern stmt) {
		String s, p, o ;
		if (stmt.getSubjectVar().getValue()!=null)
			s = "<"+stmt.getSubjectVar().getValue().stringValue()+">";
		else
			s ="?"+stmt.getSubjectVar().getName();  

		if (stmt.getPredicateVar().getValue()!=null)
			p = "<"+stmt.getPredicateVar().getValue().stringValue()+">";
		else
			p ="?"+stmt.getPredicateVar().getName(); 

		if (stmt.getObjectVar().getValue()!=null)
		{
			o = stmt.getObjectVar().getValue().stringValue();
			if (o.startsWith("http://") || o.startsWith("ftp://"))
			o = "<"+stmt.getObjectVar().getValue().stringValue()+">";
			else
				o= "\""+o+"\"";
		}
			
		else
			o ="?"+stmt.getObjectVar().getName(); 
		return s + " "+ p + " " + o+" .";
	}

}
