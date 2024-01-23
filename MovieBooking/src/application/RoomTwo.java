package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoomTwo extends Room { 
	public RoomTwo() {
		super("Room Two", 100);
		
	}

	final private int seatNumber = 15;
    private ArrayList<Integer> roomTwo;
	   
    
    public int getSeatNumber() {
		return seatNumber;
	}
    public ArrayList<Integer> getRoomTwo() {
			return roomTwo;
		}

	public void setRoomTwo(ArrayList<Integer> roomOne) {
			this.roomTwo = roomOne;
		}

//	@Override
//	public void welcomeUser() {
//		System.out.println("Welcome to Room Two!" + "\n");
//	}
	
	public ArrayList<Integer> loadRoomTwoFromFile(String filename){
		roomTwo = new ArrayList<>();
		
		try{BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line;
		
		try {
			while((line = reader.readLine()) != null) {
				int seatNumber = Integer.parseInt(line);
				roomTwo.add(seatNumber);
			}
			reader.close();
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return roomTwo;
		
	}
}
