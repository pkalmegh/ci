package graphClient;

import java.util.Iterator;

import graphComponentImplementations.MovieImpl;
import graphComponents.Movie;
import graphComponents.User;
import graphServiceImplementations.GraphDBIndexServiceImpl;
import graphServiceImplementations.GraphDBSearchEngineImpl;
import graphServiceImplementations.GraphDBServiceImpl;
import graphServiceImplementations.GraphRepresentationServiceImpl;
import graphServices.GraphDBIndexService;
import graphServices.GraphDBSearchEngine;
import graphServices.GraphDBService;
import graphServices.GraphRepresentationService;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

public class MovieLensDataAnalysis {
	static DataReader parser;
	private static final String RATING = "ml-10M100K/ratings.dat";
	private static final String TAGS = "ml-10M100K/tags.dat";
	private static final String MOVIES = "ml-10M100K/movies.dat";
	
	public static void main(String[] args) {
		GraphDBService dbService = new GraphDBServiceImpl();
		GraphDatabaseService graphDB = dbService.startGraphDb();
		
		GraphDBIndexService indexService = new GraphDBIndexServiceImpl(graphDB);
		GraphDBSearchEngine searchEngine = new GraphDBSearchEngineImpl(graphDB);
		GraphRepresentationService grs = 
				new GraphRepresentationServiceImpl(graphDB, indexService,  searchEngine);
		
		Transaction transaction = graphDB.beginTx();
		try{
			
			parser = new DataReaderImpl(grs);
			System.out.println("Parsing movies data...");
			parser.parseMovieGenreData(MOVIES);
			System.out.println("Parsing ratings data...");
			parser.parseUserMovieRatingData(RATING);
			System.out.println("Parsing tags data...");
			parser.parseUserTagsMovieData(TAGS);

	    	for(int i = 1; i <= 65133; i = i + 1000){
		    	Node result = searchEngine.searchMovie(i);
		    	String title = (String) result.getProperty("title");
		    	int id = (Integer) result.getProperty("id");
		    	int count =  (Integer) result.getProperty("count_uses");
	    		System.out.println(id + " - " + title + " - " + count);
	    	}
	    	
	    	for(int i = 1; i <= 71567; i = i + 1000){
		    	Node result = searchEngine.searchUser(i);
		    	String title = (String) result.getProperty("title");
		    	int id = (Integer) result.getProperty("id");
		    	int count =  (Integer) result.getProperty("count_uses");
	    		System.out.println(id + " - " + title + " - " + count);
	    	}

	    	transaction.success();
		}
		finally{
			transaction.finish();
			dbService.shutdownGraphDb();
		}
		
		
		

	}

}
