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

import graphComponents.Movie;
import graphComponents.Rating;
import graphComponents.RelTypes;
import graphComponents.User;

public class UserImpl implements Node, User{
	public int userId = 0;
	private static final String ID_PROPERTY = "id";
    private final Node underlyingNode;
    
    public UserImpl(final Node node ){
        this.underlyingNode = node;
    }
    
    public Node getUnderlyingNode(){
        return this.underlyingNode;
    }
    
	@Override
	public int getUserId() {
		return Integer.parseInt((String) underlyingNode.getProperty(ID_PROPERTY));
	}

	@Override
	public void setUserId(int userId) {
		 underlyingNode.setProperty(ID_PROPERTY, userId);
	}

	@Override
	public Iterator<Movie> getMovies(int userId) {
        final List<Movie> movies = new LinkedList<Movie>();
        for ( Relationship rel :
        	underlyingNode.getRelationships(RelTypes.RATED, Direction.OUTGOING ) ){
            movies.add(new MovieImpl(rel.getEndNode()));
        }
        return movies.iterator();
	}
	
	public Rating getRating(Movie movie ){
	       final Node movieNode = ((MovieImpl)movie ).getUnderlyingNode();
	       for(Relationship rel : underlyingNode.getRelationships(RelTypes.RATED, Direction.OUTGOING)){
	           if (rel.getEndNode().equals(movieNode)){
	               return new RatingImpl(rel);
	           }
	       }
	       return null;
   }

	@Override
    public boolean equals(final Object otherUser){
        if(otherUser instanceof UserImpl){
            return this.underlyingNode.equals(((UserImpl)otherUser).getUnderlyingNode());
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
