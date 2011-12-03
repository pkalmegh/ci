package graphServices;

import graphComponents.Genre;
import graphComponents.Movie;
import graphComponents.Tag;
import graphComponents.User;

import org.neo4j.graphdb.Node;

public interface GraphDBSearchEngine {

	    void indexUser( User user );
	    Node searchUser( String name );

	    void indexMovie( Movie movie );
	    Node searchMovie( String title );

	    void indexGenre( Genre genre );
	    Node searchGenre( String genre );
	    
	    void indexTag( Tag tag );
	    Node searchTag( String tag );


}
