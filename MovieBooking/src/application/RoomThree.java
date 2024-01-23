package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class RoomThree extends Room {
	public RoomThree() {
		super("Room Three", 30);
	
	}

	final private int seatNumber = 15;
    private ArrayList<Integer> roomThree;
	   
    
    public int getSeatNumber() {
		return seatNumber;
	}
    public ArrayList<Integer> getRoomThree() {
			return roomThree;
		}

	public void setRoomThree(ArrayList<Integer> roomOne) {
			this.roomThree = roomOne;
		}

//	@Override
//	public void welcomeUser() {
//		System.out.println("Welcome to Room Three!" + "\n");
//	}
	
	public ArrayList<Integer> loadRoomThreeFromFile(String filename){
		roomThree = new ArrayList<>();
		
		try{BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line;
		
		try {
			while((line = reader.readLine()) != null) {
				int seatNumber = Integer.parseInt(line);
				roomThree.add(seatNumber);
			}
			reader.close();
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return roomThree;
		
	}
	
}
