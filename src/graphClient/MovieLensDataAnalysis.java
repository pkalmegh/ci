package graphClient;

import graphServiceImplementations.GraphDBSearchEngineImpl;
import graphServiceImplementations.GraphDBServiceImpl;
import graphServiceImplementations.GraphRepresentationServiceImpl;
import graphServices.GraphDBSearchEngine;
import graphServices.GraphDBService;
import graphServices.GraphRepresentationService;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

public class MovieLensDataAnalysis {
	static DataReader parser;
	private static final String RATING = "ml-10M100K/ratings.dat";
	private static final String TAGS = "ml-10M100K/tags.dat";
	private static final String MOVIES = "ml-10M100K/movies.dat";
	
	public static void main(String[] args) {
		GraphDBService gdbs = new GraphDBServiceImpl();
		GraphDatabaseService dbService = gdbs.startGraphDb();
		Transaction transaction = dbService.beginTx();
		try{
			GraphDBSearchEngine searchEngine = new GraphDBSearchEngineImpl();
			GraphRepresentationService grs = 
					new GraphRepresentationServiceImpl(dbService, searchEngine);
			
			parser = new DataReaderImpl(grs);
			System.out.println("Parsing movies data...");
			parser.parseMovieGenreData(MOVIES);
			System.out.println("Parsing ratings data...");
			parser.parseUserMovieRatingData(RATING);
			System.out.println("Parsing tags data...");
			parser.parseUserTagsMovieData(TAGS);
			
			transaction.success();
			transaction.finish();
		}
		finally{
			gdbs.shutdownGraphDb();
		}
		
		
		

	}

}
