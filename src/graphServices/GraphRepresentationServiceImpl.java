package graphServices;

import graphComponentImplementations.GenreImpl;
import graphComponentImplementations.MovieImpl;
import graphComponentImplementations.RatingImpl;
import graphComponentImplementations.TagImpl;
import graphComponentImplementations.UserImpl;
import graphComponents.Genre;
import graphComponents.Movie;
import graphComponents.Rating;
import graphComponents.RelTypes;
import graphComponents.Tag;
import graphComponents.User;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class GraphRepresentationServiceImpl 
	implements GraphRepresentationService{

	 
	    private GraphDBService graphDbService;
	    private IndexService userIndexService;
	    private IndexService movieIndexService;
	    private IndexService genreIndexService;
	    private IndexService tagIndexService;
	    
	    private static final String ID_PROPERTY = "id";
	    private static final String TITLE_PROPERTY = "title";
	    
	@Override
	public User createUser(int id) {
		String name = "User-" + id;
		final Node userNode = graphDbService.createNode();
        final User user = new UserImpl( userNode );
        user.setUserId(id);
        user.setName( name );
        userIndexService.index( userNode, ID_PROPERTY, id );
        return user;
    }

	@Override
	public Movie createMovie(int id, String title) {
		final Node movieNode = graphDbService.createNode();
        final Movie movie = new MovieImpl( movieNode );
        movie.setMovieId(id);
        movie.setMovieTitle( title );
        //searchEngine.indexMovie( movie );
        movieIndexService.index( movieNode, ID_PROPERTY, id );
        return movie;	
	}

	@Override
	public Rating createUserMovieRelation(User user, Movie movie, int rating) {
		if ( user == null ){
            throw new IllegalArgumentException( "Null user" );
        }
        if ( movie == null ){
            throw new IllegalArgumentException( "Null movie" );
        }
        final Node userNode = ((UserImpl)user).getUnderlyingNode();
        final Node movieNode = ((MovieImpl)movie).getUnderlyingNode();
        final Relationship rel 
        				= userNode.createRelationshipTo( movieNode, RelTypes.RATED);
        final Rating rate = new RatingImpl( rel );
       	rate.setRating(rating);
       	if(rating <= 2.5){
       		rate.setVote("UNLIKE");
       	}else{
       		rate.setVote("LIKE");
       	}
        return rate;	
	}

	@Override
	public User getUser(int id) {
      Node userNode = userIndexService.getSingleNode( ID_PROPERTY, id );
/*        if ( userNode == null ){
            userNode = searchEngine.searchUser(id);
        }
*/        User user = null;
        if ( userNode != null ){
            user = new UserImpl( userNode );
        }
        return user;	
	}

	@Override
	public Movie getMovie(int movieId) {
		Node movieNode = movieIndexService.getSingleNode( ID_PROPERTY, movieId );
/*        if ( movieNode == null ){
            movieNode = searchEngine.searchMovie(title);
        }
*/        Movie movie = null;
        if ( movieNode != null ){
            movie = new MovieImpl( movieNode );
        }
        return movie;	
	}

	@Override
	public Genre createGenre(String title) {
		final Node genreNode = graphDbService.createNode();
        final Genre genre = new GenreImpl( genreNode );
        genre.setGenre(title);
        //searchEngine.indexGenre( genre );
        genreIndexService.index( genreNode, TITLE_PROPERTY, title );
        return genre;	
	}

	@Override
	public Genre getGenre(String title) {
		Node genreNode = genreIndexService.getSingleNode(TITLE_PROPERTY, title);
/*        if ( genreNode == null ){
            genreNode = searchEngine.searchgenre(title);
        }
*/        Genre genre = null;
        if ( genreNode != null ){
            genre = new GenreImpl( genreNode );
        }
        return genre;	
	}

	@Override
	public Relationship createMovieGenreRelation(Movie movie, Genre genre) {
		if(movie == null){
			 throw new IllegalArgumentException( "Null movie" );
		}
		if(genre == null){
			 throw new IllegalArgumentException( "Null genre" );	
		}
		final Node movieNode = ((MovieImpl)movie).getUnderlyingNode();
		final Node genreNode = ((GenreImpl)genre).getUnderlyingNode();
		Relationship movieGenreRel = movieNode.createRelationshipTo(genreNode, RelTypes.BELONGS_TO);
		return movieGenreRel;
	}

	@Override
	public Tag createTag(String title) {
		final Node tagNode = graphDbService.createNode();
		final Tag tag = new TagImpl(tagNode);
		tag.setTag(title);
		tagIndexService.index(tagNode, TITLE_PROPERTY, title);
		return tag;
	}

	@Override
	public Tag getTag(String title) {
		Node tagNode = genreIndexService.getSingleNode(TITLE_PROPERTY, title);
        Tag tag = null;
        if ( tagNode != null ){
            tag = new TagImpl( tagNode );
        }
        return tag;	
	}

	@Override
	public List<Relationship> createUserTagsMovieRelation(User user, Movie movie,
			Tag tag) {
		List<Relationship> relationships = new LinkedList<Relationship>();
		if(user == null){
			 throw new IllegalArgumentException( "Null user" );
		}
		if(movie == null){
			 throw new IllegalArgumentException( "Null movie" );
		}
		if(tag == null){
			 throw new IllegalArgumentException( "Null tag" );	
		}
		final Node userNode = ((UserImpl)user).getUnderlyingNode();
		int userId = user.getUserId();
		
		final Node movieNode = ((MovieImpl)movie).getUnderlyingNode();
		String movieName = movie.getMovieTitle();
		
		final Node tagNode = ((TagImpl)tag).getUnderlyingNode();
		
		Relationship userTagRel = userNode.createRelationshipTo(tagNode, RelTypes.CREATES_TAG);
		userTagRel.setProperty(TITLE_PROPERTY, movieName);
		relationships.add(userTagRel);

		Relationship movieTagRel = movieNode.createRelationshipTo(tagNode, RelTypes.IS_TAGGED);
		movieTagRel.setProperty(ID_PROPERTY, userId);
		relationships.add(movieTagRel);
		
		return relationships;
	}

}
