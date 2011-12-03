package graphServiceImplementations;

import graphComponentImplementations.UserImpl;
import graphComponents.Genre;
import graphComponents.Movie;
import graphComponents.Tag;
import graphComponents.User;
import graphServices.GraphDBSearchEngine;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

public class GraphDBSearchEngineImpl implements GraphDBSearchEngine {
	
	 private Index<Node> nodeIndex;
	 private static final String NAME_INDEX = "name";

	@Override
	public void indexUser(User user) {
		 index( user.getUserName(), ((UserImpl) user).getUnderlyingNode(),
		            NAME_INDEX, GraphDBSearchRelTypes.USER_NAME );
	}

	@Override
	public Node searchUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void indexMovie(Movie movie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node searchMovie(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void indexGenre(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node searchGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void indexTag(Tag tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node searchTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    private Node getSingleNode(String key, String value){
        IndexHits<Node> hits = nodeIndex.get( key, value );
        for ( Node node : hits )
        {
            return node;
        }
        return null;
    }


    private void index( final String value, final Node node,
		final String indexName, final GraphDBSearchRelTypes relType ){
	    Node wordNode = getSingleNode(indexName, value);
	    if ( wordNode == null ){
	        wordNode = graphDbService.createNode();
	        // not needed for the functionality
	        nodeIndex.add(wordNode, partIndexName, part);
	        wordNode.setProperty( WORD_PROPERTY, part );
	    }
	    wordNode.createRelationshipTo( node, relType );
	    wordNode.setProperty( COUNT_PROPERTY, ((Integer) wordNode
	        .getProperty( COUNT_PROPERTY, 0 )) + 1 );
    }


}
