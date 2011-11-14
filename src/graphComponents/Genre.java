package graphComponents;

import java.util.Iterator;

public interface Genre {
	public String getGenre();
	public void setGenre(String genre);
	
	public Iterator<Movie> getMovies(String genre);
	
}
