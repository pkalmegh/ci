package graphComponentImplementations;

import graphComponents.Movie;
import graphComponents.Rating;
import graphComponents.User;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

public class RatingImpl implements Relationship, Rating{
	public double rating = 0;
	public String vote = null;
	public int movieId = 0;
	public int userId = 0;

	private static final String RATE_PROPERTY = "rate";
	private static final String VOTE_PROPERTY = "vote";

    private final Relationship underlyingRel;

    public RatingImpl(final Relationship rel){
        this.underlyingRel = rel;
    }

    public Relationship getUnderlyingRelationship(){
        return this.underlyingRel;
    }

	@Override
	public String getVote() {
		return (String) underlyingRel.getProperty(VOTE_PROPERTY);
	}

	@Override
	public void setVote(String vote) {
		underlyingRel.setProperty(VOTE_PROPERTY, vote );
	}

	@Override
	public int getRating() {
		return Integer.parseInt((String) underlyingRel.getProperty(RATE_PROPERTY));
	}

	@Override
	public void setRating(int rating) {
		underlyingRel.setProperty(RATE_PROPERTY, rating );
	}

	@Override
	public Movie getMovie() {
		 return new MovieImpl(underlyingRel.getEndNode());
	}

	@Override
	public User getUser() {
		return new UserImpl( underlyingRel.getStartNode() );
	}
	
	@Override
	public boolean equals(Object otherRating ){
        if (otherRating instanceof RatingImpl){
            return this.underlyingRel.equals(((RatingImpl)otherRating).getUnderlyingRelationship());
        }
        return false;
    }
	
	@Override
    public int hashCode(){
        return this.underlyingRel.hashCode();
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
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getEndNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node[] getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getOtherNode(Node arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getStartNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelationshipType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isType(RelationshipType arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
