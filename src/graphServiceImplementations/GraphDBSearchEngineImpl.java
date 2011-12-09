package graphServiceImplementations;

import graphComponentImplementations.GenreImpl;
import graphComponentImplementations.MovieImpl;
import graphComponentImplementations.TagImpl;
import graphComponentImplementations.UserImpl;
import graphComponents.Genre;
import graphComponents.Movie;
import graphComponents.Tag;
import graphComponents.User;
import graphServices.GraphDBSearchEngine;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

public class GraphDBSearchEngineImpl implements GraphDBSearchEngine {
	
	 private Index<Node> searchIndex;
	 private static final String COUNT_PROPERTY = "count_uses";
	 private static final String USER = "user"; 
	 private static final String MOVIE = "movie";
	 private static final String USER_ID = "userid";
	 private static final String MOVIE_ID = "movieid";
	 private static final String GENRE = "genre";
	 private static final String TAG = "tag";
	 
	 public GraphDBSearchEngineImpl(GraphDatabaseService graphDB) {
		this.searchIndex = graphDB.index().forNodes("searchindex");
	}

	@Override
	public void indexUser(User user) {
		 index( ((UserImpl) user).getUnderlyingNode(), USER, user.getUserName() );
		 index(  ((UserImpl) user).getUnderlyingNode(), USER_ID, Integer.toString(user.getUserId()));
	}

	@Override
	public Node searchUser(String name) {
		return searchSingle( USER , name);
	}

	@Override
	public Node searchUser(int userId) {
		return searchSingle(USER_ID, Integer.toString(userId));
	}

	@Override
	public void indexMovie(Movie movie) {
		index( ((MovieImpl) movie).getUnderlyingNode(), MOVIE , movie.getMovieTitle());
		index( ((MovieImpl) movie).getUnderlyingNode(), MOVIE_ID, Integer.toString(movie.getMovieId()));
	}

	@Override
	public Node searchMovie(String title) {
		return searchSingle(MOVIE, title);
	}

	@Override
	public Node searchMovie(int movieId) {
		return searchSingle(MOVIE_ID, Integer.toString(movieId) );
	}

	@Override
	public void indexGenre(Genre genre) {
		index(((GenreImpl) genre).getUnderlyingNode(), GENRE, genre.getGenre());
	}

	@Override
	public Node searchGenre(String genre) {
		return searchSingle(GENRE, genre);
	}

	@Override
	public void indexTag(Tag tag) {
		index(((TagImpl) tag).getUnderlyingNode(), TAG, tag.getTag() );
	}

	@Override
	public Node searchTag(String tag) {
		return searchSingle(TAG, tag);
	}
	
	
    private Node getSingleNode(String key, String value){
        IndexHits<Node> hits = searchIndex.get( key, value );
        if(hits != null){
            for ( Node node : hits ){
                return node;
            }
        }
        return null;
    }


    private void index( final Node node, final String keyword, final String value){
	    node.setProperty( COUNT_PROPERTY, ((Integer) node
		        .getProperty( COUNT_PROPERTY, 0 )) + 1 );
        searchIndex.add(node, keyword, value);
    }
    
    private Node searchSingle( final String indexName, final String value){
    		Node node = getSingleNode(indexName, value);
            return node;
    }


}
