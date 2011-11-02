package graphComponents;

import java.util.Iterator;

public interface Genre {
	public String getGenre(int movieId);
	public void setGenre(int movieId, String genre);
	
	public Iterator<Movie> getMovies(String genre);
	
}
