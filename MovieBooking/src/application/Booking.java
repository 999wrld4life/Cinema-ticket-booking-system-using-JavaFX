package application;

import java.time.LocalTime;
import java.util.Date;

public class Booking {
  
  
  private String selectedSeat;
  private Date bookingDate;
  private LocalTime bookingTime;
  private String bookedRoom;
 
  
  

public Booking(Date bookingDate, LocalTime bookingTime, String selectedSeat, String bookedRoom) {
	  this.bookingDate = bookingDate;
	  this.bookingTime = bookingTime;
	  this.selectedSeat = selectedSeat;
	  this.bookedRoom = bookedRoom;
	  
  }
  
    
	
	public String getSelectedSeat() {
		return selectedSeat;
	}
	public void setSelectedSeat(String selectedSeat) {
		this.selectedSeat = selectedSeat;
	}
	public Date getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}


	public LocalTime getBookingTime() {
		return bookingTime;
	}


	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}


	public String getBookedRoom() {
		return bookedRoom;
	}


	public void setBookedRoom(String bookedRoom) {
		this.bookedRoom = bookedRoom;
	}

	
  
}
