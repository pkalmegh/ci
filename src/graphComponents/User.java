package graphComponents;

import java.util.Iterator;

public interface User {
	public int getUserId();
	public void setUserId(int userId);
	public String getUserName();
	public void setName(String userName);
	
	public Iterator<Movie> getMovies(int userId);
	public Rating getRating(Movie inMovie );
	
}
