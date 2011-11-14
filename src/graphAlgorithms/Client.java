package graphAlgorithms;

import java.util.Map;
import java.util.Set;

public class Client {
	private static final String RATING = "ml-10M100K/ratings.dat";
	private static final String TAGS = "ml-10M100K/tags.dat";
	private static final String MOVIES = "ml-10M100K/movies.dat";
	public static void main(String[] args){
/*		CommunityDetection cd = new ClusterDefinition();
		Map<String, Set<String>> genreTagsMap = cd.createCommunityList(MOVIES, TAGS);*/
		GraphRepresentation gr = new UserSimilarity();
		double threshold = 2.5;
		Map<String, Set<String>> userLikesTagMap = gr.createTaglist(RATING, TAGS, threshold, true);
		Map<String, Set<String>> userUnLikesTagMap = gr.createTaglist(RATING, TAGS, threshold, false);
		
		System.out.println("Done!!");
	}

}
