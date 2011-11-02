package graphComponentImplementations;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import graphComponents.Movie;
import graphComponents.RelTypes;
import graphComponents.Tag;
import graphComponents.User;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;

public class TagImpl implements Node, Tag{
	public String tag = null;
	private static final String TITLE_PROPERTY = "title";
	private final Node underlyingNode;
	
	public TagImpl(Node node) {
		this.underlyingNode = node;
	}
	
    public Node getUnderlyingNode(){
        return this.underlyingNode;
    }

	@Override
	public String getTag() {
		return (String) underlyingNode.getProperty(TITLE_PROPERTY);
	}

	@Override
	public void setTag( String tag) {
		 underlyingNode.setProperty(TITLE_PROPERTY, tag );
	}


	@Override
	public Iterator<User> getUsers() {
		final List<User> users = new LinkedList<User>();
        for(Relationship rel : underlyingNode.getRelationships( RelTypes.CREATES_TAG, Direction.INCOMING)){
            users.add(new UserImpl(rel.getStartNode()));
        }
        return users.iterator();
	}

	@Override
	public Iterator<Movie> getMovies() {
        final List<Movie> movies = new LinkedList<Movie>();
        for ( Relationship rel :
        	underlyingNode.getRelationships(RelTypes.IS_TAGGED, Direction.INCOMING ) ){
            movies.add(new MovieImpl(rel.getStartNode()));
        }
        return movies.iterator();
	}


	
	@Override
    public boolean equals(final Object otherTag){
        if(otherTag instanceof UserImpl){
            return this.underlyingNode.equals(((TagImpl)otherTag).getUnderlyingNode());
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
