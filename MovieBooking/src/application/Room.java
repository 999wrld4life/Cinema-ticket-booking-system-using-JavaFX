package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Room {
  private String name;
  private int seatNumber;
 
  public Room(String aName, int aSeatNumber) {
	  this.name = aName;
	  this.setSeatNumber(aSeatNumber);
  }
   
  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public String welcomeUser() {
	return "Welcome to";
	}
	
public void displayRoom(ArrayList<Integer> roomNumber) {
		
		System.out.println("Available seats: \n");
		for(Integer room : roomNumber) {
			System.out.println(room);
		}
	}



public void updateRoomFile(ArrayList<Integer> roomOne, String filename) throws IOException {
   
	
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
        // Write each seat number to the file
        for (Integer seatNumber : roomOne) {
            writer.write(seatNumber.toString());
            writer.newLine();  // Move to the next line
        }
    }
}



public boolean seatCheckerForRoom(ArrayList<Integer> seats, int selectedSeat, String filename) throws IOException {
    if (seats.contains(selectedSeat)) {
        seats.remove(Integer.valueOf(selectedSeat));  // Remove the selected seat
        updateRoomFile(seats, filename);
        System.out.println("Seat Available!");
        return true;
    } else { 
        System.out.println("Invalid seat selection. Seat already booked or doesn't exist.");
        return false;
    }
}

public int getSeatNumber() {
	return seatNumber;
}

public void setSeatNumber(int seatNumber) {
	this.seatNumber = seatNumber;
}
}




