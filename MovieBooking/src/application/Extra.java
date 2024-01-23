package application;



public class Extra {
  
  private User user;
  private Movie movie;
  private String selectedSeat;
 
  
  public Extra(User user, Movie movie, String selectedSeat) {
	  this.user = user;
	  this.movie = movie;
	  this.selectedSeat = selectedSeat;
	  
  }
  
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getSelectedSeat() {
		return selectedSeat;
	}
	public void setSelectedSeat(String selectedSeat) {
		this.selectedSeat = selectedSeat;
	}
	
  
}

