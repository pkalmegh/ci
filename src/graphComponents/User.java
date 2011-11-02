package graphComponents;

import java.util.Iterator;

public interface User {
	public int getUserId();
	public void setUserId(int userId);
	
	public Iterator<Movie> getMovies(int userId);
	public Rating getRating(Movie inMovie );
}
