package graphClient;

public interface DataReader {
    void parseMovieGenreData(String moviesFile);
    void parseUserMovieRatingData(String ratingsFile);
    void parseUserTagsMovieData(String tagsFile);
}
