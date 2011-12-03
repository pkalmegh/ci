package graphClient;

import org.neo4j.graphdb.GraphDatabaseService;

import graphServiceImplementations.GraphDBSearchEngineImpl;
import graphServiceImplementations.GraphDBServiceImpl;
import graphServiceImplementations.GraphRepresentationServiceImpl;
import graphServices.GraphDBSearchEngine;
import graphServices.GraphDBService;
import graphServices.GraphRepresentationService;

public class MovieLensDataAnalysis {
	static DataReader parser;
	private static final String RATING = "ml-10M100K/ratings.dat";
	private static final String TAGS = "ml-10M100K/tags.dat";
	private static final String MOVIES = "ml-10M100K/movies.dat";
	
	public static void main(String[] args) {
		GraphDBService gdbs = new GraphDBServiceImpl();
		GraphDatabaseService dbService = gdbs.startGraphDb();
		
		GraphDBSearchEngine searchEngine = new GraphDBSearchEngineImpl();
		GraphRepresentationService grs = 
				new GraphRepresentationServiceImpl(dbService, searchEngine);
		
		parser = new DataReaderImpl(grs);
		parser.parseMovieGenreData(MOVIES);
		parser.parseUserMovieRatingData(RATING);
		parser.parseUserTagsMovieData(TAGS);
		
		
		
		

	}

}
