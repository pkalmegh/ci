package graphComponents;

import java.util.Iterator;

public interface Movie {
	
	public String getMovieTitle();
	public void setMovieTitle(String movieTitle);
	
	public int getMovieId();
	public void setMovieId(int movieId);
	
	public Iterator<User> getUsers(int movieId);
	public Iterator<Genre> getGenres(int movieId);
	
}
