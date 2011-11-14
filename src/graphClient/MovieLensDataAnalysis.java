package graphClient;

public class MovieLensDataAnalysis {
	static DataReader parser;
	private static final String RATING = "ml-10M100K/ratings.dat";
	private static final String TAGS = "ml-10M100K/tags.dat";
	private static final String MOVIES = "ml-10M100K/movies.dat";
	
	public static void main(String[] args) {
		parser = new DataReaderImpl();
		parser.parseMovieGenreData(MOVIES);
		parser.parseUserMovieRatingData(RATING);
		parser.parseUserTagsMovieData(TAGS);
		
		
		
		

	}

}
