package graphClient;

import graphComponents.Genre;
import graphComponents.Movie;
import graphComponents.Tag;
import graphComponents.User;
import graphServiceImplementations.GraphRepresentationServiceImpl;
import graphServices.GraphRepresentationService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReaderImpl implements DataReader	{
	
	private GraphRepresentationService grs;
	
	public DataReaderImpl(GraphRepresentationService grs) {
		this.grs = grs;
	}
	

	@Override
	public void parseMovieGenreData(String file) {
		if ( file == null ){
            throw new IllegalArgumentException( "Null movie file" );
        }
        try {
			BufferedReader fileReader = new BufferedReader( new FileReader( file ) );
			String line = fileReader.readLine();
			int lines = 0;
			while ( line != null && lines++ <=10){
				String[] movieData = line.split("::");
				if(movieData.length == 3){
					int movieId = Integer.parseInt(movieData[0]);
					String movieTitle = movieData[1];
					String movieGenres = movieData[2];
					Movie movie = grs.createMovie(movieId, movieTitle);
					if(movieGenres.contains("|")){
						String[]genres = movieGenres.split("|");
						for (int i = 0; i < genres.length; i++) {
							Genre genre = grs.getGenre(genres[i]);
							if(genre == null){
								genre = grs.createGenre(genres[i]);
							}
								
							grs.createMovieGenreRelation(movie, genre);
						}
					}
				}else{
					System.out.println("BADLINE - " + line);
				}
				line = fileReader.readLine();
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void parseUserMovieRatingData(String file) {
		if ( file == null ){
            throw new IllegalArgumentException( "Null ratings file" );
        }
        try {
			BufferedReader fileReader = new BufferedReader( new FileReader( file ) );
			String line = fileReader.readLine();
			int lines = 0;
			while ( line != null && lines++ <=10){
				String[] ratingData = line.split("::");
				if(ratingData.length == 4){
					int userId = Integer.parseInt(ratingData[0]);
					int movieId = Integer.parseInt(ratingData[1]);
					int rating = Integer.parseInt(ratingData[2]);
					User user = grs.createUser(userId);
					Movie movie = grs.getMovie(movieId);
					if(movie == null){
						movie = grs.createMovie(movieId, "NOT_FOUND");
					}
					grs.createUserMovieRelation(user, movie, rating);
				}else{
					System.out.println("BADLINE - " + line);
				}
				line = fileReader.readLine();
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void parseUserTagsMovieData(String file) {
		if ( file == null ){
            throw new IllegalArgumentException( "Null tags file" );
        }
        try {
			BufferedReader fileReader = new BufferedReader( new FileReader( file ) );
			String line = fileReader.readLine();
			int lines = 0;
			while ( line != null && lines++ <=10){
				String[] tagsData = line.split("::");
				if(tagsData.length == 4){
					int userId = Integer.parseInt(tagsData[0]);
					int movieId = Integer.parseInt(tagsData[1]);
					String tagTitle = tagsData[2];
					
					
					User user = grs.getUser(userId);
					//create a new user if it does not already exists
					if(user == null){
						user = grs.createUser(userId);
					}
						
					Movie movie = grs.getMovie(movieId);
					//create a new movie if it does not already exists
					if(movie == null){
						movie = grs.createMovie(movieId, "NOT_FOUND");
					}
					
					Tag tag = grs.createTag(tagTitle);
					
					grs.createUserTagsMovieRelation(user, movie, tag);
				}else{
					System.out.println("BADLINE - " + line);
				}
				line = fileReader.readLine();
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
