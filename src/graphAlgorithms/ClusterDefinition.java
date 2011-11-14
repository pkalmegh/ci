package graphAlgorithms;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import scala.actors.threadpool.Arrays;

public class ClusterDefinition implements CommunityDetection{
	
	String[] genres = new String[] {"Action", "Adventure", "Animation", "Children's", "Comedy", "Crime", "Documentary", "Drama",
			"Fantasy", "Film-Noir",	"Horror", "Musical", "Mystery",	"Romance", "Sci-Fi", "Thriller", "War", "Western"};
	private List<String> genreList = Arrays.asList(genres);

		
	public Map<String, Set<String>> createCommunityList(String movies, String tags){
		Map<String, Set<String>> genreTagMap = 
				new LinkedHashMap<String, Set<String>>();
		Iterator<String> genreIterator = genreList.iterator();
		while(genreIterator.hasNext()){
			String genre = genreIterator.next();
			Set<String> moviesForGenre = getGenreMovieList(movies, genre);
			Set<String> tagsForGenre = getTagListForMovieGenre(tags, moviesForGenre);
			if(!genreTagMap.containsKey(genre)){
				genreTagMap.put(genre, tagsForGenre);
			}
		}
		return genreTagMap;
	}
	
	private Set<String> getGenreMovieList(String movies, String genre){
		Set<String> movieList = 
				new LinkedHashSet<String>();
		FileInputStream fstream;
		String strLine = null;
		try {
			fstream = new FileInputStream(movies);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)  {
				//Print the content on the console
				//System.out.println (" Line = "+ strLine);
				String[] columns = strLine.split("::");
				String movie = columns[0];
				
				if(genre.equals(columns[2]) && !movieList.contains(movie)){
					movieList.add(movie);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
		
	}
	
	
	

	
	private Set<String> getTagListForMovieGenre(String tagFile, Set<String> movieList){
		Set<String> tagList = new LinkedHashSet<String>();
		FileInputStream fstream;
		String strLine = null;
		try {
			fstream = new FileInputStream(tagFile);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//Read File Line By Line
			
			while ((strLine = br.readLine()) != null)  {
				//Print the content on the console
				//System.out.println (" Line = "+ strLine);
				String[] columns = strLine.split("::");
				if(movieList.contains(columns[1]) 
						&& !tagList.contains(columns[2])){
					tagList.add(columns[2]);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tagList;
	}

}
