package graphComponents;

import java.util.Iterator;

import org.neo4j.graphdb.Node;

public interface Tag {
	public String getTag();
	public void setTag( String tag);
	public Iterator<User> getUsers();
	public Iterator<Movie> getMovies();
}
