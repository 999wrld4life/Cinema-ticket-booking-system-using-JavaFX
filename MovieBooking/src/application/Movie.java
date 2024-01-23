package application;

import java.util.Date;

public class Movie {
  private String title;
  private double rating;
  private String showtimes;
  private String genre;
  private String synopsis;
  private String startTime;
  
  
   /*to prevent default toString() when printing out the movie and its details 
    (e.g., com.movie.User@41906a77)
   and print out meaningful string representation (I'll explain more in person)
   */
   @Override
	public String toString() {
	 return "- Title: " + title + " - Rating: " + rating + "- Showtimes: " + showtimes + "- Genre: " + genre + "- Synopsis: " + synopsis +"- Start Time: " + startTime + "- End Time: " + endTime + "- Released Date: " + releasedDate;
	 }
	


  
   
   //getters and setters for the attributes
  public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getShowtimes() {
		return showtimes;
	}

	public void setShowtimes(String showtimes) {
		this.showtimes = showtimes;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getStartTime() {
		return startTime;
	}





	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}





	public String getEndTime() {
		return endTime;
	}





	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}





	public Date getReleasedDate() {
		return releasedDate;
	}





	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	private String endTime;
	  private Date releasedDate;

}
