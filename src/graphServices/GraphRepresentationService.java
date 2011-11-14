package graphServices;

import graphComponents.Genre;
import graphComponents.Movie;
import graphComponents.Rating;
import graphComponents.Tag;
import graphComponents.User;

import java.util.List;

import org.neo4j.graphdb.Relationship;

public interface GraphRepresentationService {
    User createUser(int id);
    User getUser( int id );
    
    Movie createMovie(int id, String title);
    Movie getMovie(int id);
    
    Rating createUserMovieRelation( User user, Movie movie, int rating );
    
    Genre createGenre(String title);
    Genre getGenre( String title );
    
    Relationship createMovieGenreRelation( Movie movie, Genre genre);
    
    Tag createTag(String tag);
    Tag getTag(String tag);
    
    List<Relationship> createUserTagsMovieRelation( User user, Movie movie, Tag tag);
    
}
