package graphComponentImplementations;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;

import graphComponents.Genre;
import graphComponents.Movie;
import graphComponents.RelTypes;

public class GenreImpl implements Node, Genre{
	public String genre = null;
	private static final String TITLE_PROPERTY = "title";
	private final Node underlyingNode;
	
	public GenreImpl(Node node) {
		this.underlyingNode = node;
	}
	
    public Node getUnderlyingNode(){
        return this.underlyingNode;
    }

	@Override
	public String getGenre() {
		return (String) underlyingNode.getProperty(TITLE_PROPERTY);
	}

	@Override
	public void setGenre(String genre) {
		 underlyingNode.setProperty(TITLE_PROPERTY, genre );
	}

	@Override
	public Iterator<Movie> getMovies(String genre) {
        final List<Movie> movies = new LinkedList<Movie>();
        for ( Relationship rel :
        	underlyingNode.getRelationships(RelTypes.BELONGS_TO, Direction.INCOMING ) ){
            movies.add(new MovieImpl(rel.getStartNode()));
        }
        return movies.iterator();
	}
	
	@Override
    public boolean equals(final Object otherGenre){
        if(otherGenre instanceof GenreImpl){
            return this.underlyingNode.equals(((GenreImpl)otherGenre).getUnderlyingNode());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.underlyingNode.hashCode();
    }

	@Override
	public GraphDatabaseService getGraphDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProperty(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProperty(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> getPropertyKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Object> getPropertyValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasProperty(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object removeProperty(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Relationship createRelationshipTo(Node arg0, RelationshipType arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Relationship> getRelationships() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Relationship> getRelationships(RelationshipType... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Relationship> getRelationships(Direction arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Relationship> getRelationships(Direction arg0,
			RelationshipType... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Relationship> getRelationships(RelationshipType arg0,
			Direction arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relationship getSingleRelationship(RelationshipType arg0,
			Direction arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRelationship() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRelationship(RelationshipType... arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRelationship(Direction arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRelationship(Direction arg0, RelationshipType... arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRelationship(RelationshipType arg0, Direction arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Traverser traverse(Order arg0, StopEvaluator arg1,
			ReturnableEvaluator arg2, Object... arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Traverser traverse(Order arg0, StopEvaluator arg1,
			ReturnableEvaluator arg2, RelationshipType arg3, Direction arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Traverser traverse(Order arg0, StopEvaluator arg1,
			ReturnableEvaluator arg2, RelationshipType arg3, Direction arg4,
			RelationshipType arg5, Direction arg6) {
		// TODO Auto-generated method stub
		return null;
	}
    


}
