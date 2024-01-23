package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoomOne extends Room {
  
	public RoomOne() {
		super("Room One", 50);
		
	}

	final private int seatNumber = 15;
    private ArrayList<Integer> roomOne;
	   
    
    public int getSeatNumber() {
		return seatNumber;
	}
    public ArrayList<Integer> getRoomOne() {
			return roomOne;
		}

	public void setRoomOne(ArrayList<Integer> roomOne) {
			this.roomOne = roomOne;
		}

//	@Override
//	public void welcomeUser() {
//		System.out.println("Welcome to Room One!" + "\n");
//	}
	
	public ArrayList<Integer> loadRoomOneFromFile(String filename){
		roomOne = new ArrayList<>();
		
		try{BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line;
		
		try {
			while((line = reader.readLine()) != null) {
				int seatNumber = Integer.parseInt(line);
				roomOne.add(seatNumber);
			}
			reader.close();
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return roomOne;
		
	}

	
}
