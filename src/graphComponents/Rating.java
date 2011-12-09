package graphComponents;

public interface Rating {
	public String getVote();
	public void setVote(String vote);
	
	public int getRating();
	public void setRating(double rating);
	
	public Movie getMovie();
	public User getUser();

}
