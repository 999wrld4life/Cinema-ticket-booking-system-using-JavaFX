package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BookingSystem {
	
	private ArrayList<Extra>bookings = new ArrayList<>();
	private ArrayList<User>users = new ArrayList<>();
	private ArrayList<Movie>movies = loadMoviesFromFile("movies.txt");
	 ArrayList<Integer>roomOne = new ArrayList<>();
	 

	    
	
	
	
	public ArrayList<Extra> getBookings() {
		return bookings;
	}
	public void setBookings(ArrayList<Extra> bookings) {
		this.bookings = bookings;
	}
	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public ArrayList<Integer> getRoomOne() {
		return roomOne;
	}
	public void setRoomOne(ArrayList<Integer> roomOne) {
		this.roomOne = roomOne;
	}
	

	
	
	//registers the user
	public void registerUser(String firstName, String lastName, String password) {
		User newUser = new User(firstName, lastName, password);
		users.add(newUser);
		System.out.println("User registered successfully!");
	}
	
	//saves user's credentials to file(basically its username and password)
	public void saveUserToFile(String filename, ArrayList<User> users) throws IOException {
		FileWriter writer = new FileWriter(filename, true);
		for(User user : users) {
			writer.write(user.getUsername() + " " + user.getPassword() + "\n");
		}
		writer.close();
	}
	
	
	
	
	
	
	//shows the generated username to the user
	public void showUsername() {
		
		for(User user : users) {
			System.out.println("Your auto-generated username is: " + user.getUsername());
		}
	}
	
	
	
	//logs in the user using the generated username and password
	public User loginUser(String username, String password) {
		for(User user : users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				System.out.println("Logged in successfully");
				return user;
				//we return user so that we can access that specific logged in user
			}
		}
		System.out.println("Invalid username or password");
		return null;
		
	}
	
	

	
	
	//loads the user's credentials from file
	public ArrayList<User> loadUserFromFile(String filename) throws IOException{
		//initializes the users arraylist
		users = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		
		while((line=reader.readLine())!= null) {//means until the reader runs out of any characters
			String[] part = line.split(" ");//spliting the credentials using a space
			
			//storing the first part as username and the second as password
			String username = part[0];
			String password = part[1];
			
			//creating an instance of the User class to store the loaded username
			//and password
			User user1 = new User();
			user1.setUsername(username);
			user1.setPassword(password);
			
			//adding the user's credentials to his respective spot inside the users arraylist
			users.add(user1);
			
		}
		return users;
	}
	
	
	public ArrayList<Integer> loadRoomOneFromFile(String filename){
		ArrayList<Integer> roomOne = new ArrayList<>();
		
		try{BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line;
		
		try {
			while((line = reader.readLine()) != null) {
				int seatNumber = Integer.parseInt(line);
				roomOne.add(seatNumber);
			}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return roomOne;
	}
	


	public void displayRoomOne(ArrayList<Integer> roomOne) {
		
		System.out.println("Available seats: \n");
		for(Integer room : roomOne) {
			System.out.println(room);
		}
	}
	
	public void updateRoomOneFile(ArrayList<Integer> roomOne, String filename) throws IOException {
        // Use BufferedWriter for efficient writing
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            // Write each seat number to the file
            for (Integer seatNumber : roomOne) {
                writer.write(seatNumber.toString());
                writer.newLine();  // Move to the next line
            }
        }
	}
	public boolean seatCheckerForRoomOne(ArrayList<Integer> seats, int selectedSeat, String filename) throws IOException {
	    if (seats.contains(selectedSeat)) {
	        seats.remove(Integer.valueOf(selectedSeat));  // Remove the selected seat
	        updateRoomOneFile(seats, filename);
	        System.out.println("Seat Available!");
	        return true;
	    } else { 
	        System.out.println("Invalid seat selection. Seat already booked or doesn't exist.");
	        return false;
	    }
	}

        
	

	
	//fills the users arraylist with the loaded credentials and displays the information
	public void displayUsers() throws IOException {
		//fills the users array with the loaded credentials
		ArrayList<User>users = loadUserFromFile("users.txt");
		
		//displays the users credentials(for experiment purpose only)
		for(User user : users) {
			System.out.println(user.getUsername() + " " + user.getPassword());
		}
	}
	
	
	//loads the list of movies from file and populates the movies arraylist
	public static ArrayList<Movie> loadMoviesFromFile(String filename){
		ArrayList<Movie> movies = new ArrayList<>();
		
		try{BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line;
		
		try {
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("/ ");
			
			
			
				String title = parts[0];
				double rating = Double.parseDouble(parts[1]);
				String showtime = parts[2];
				String genre = parts[3];
				String synopsis = parts[4];
				
				
			
			Movie moviee1 = new Movie();
			moviee1.setTitle(title);
			moviee1.setRating(rating);
			moviee1.setShowtimes(showtime);
			moviee1.setGenre(genre);
			moviee1.setSynopsis(synopsis);
			movies.add(moviee1);
}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return movies;
	}
	
	//displays the movies from the arraylist
	public void displayMovies() {
		System.out.println("Available movies: ");
		for(Movie movie : movies) {
			System.out.println(movie.getTitle() + " - Rating: " + movie.getRating() + " - Showtimes: " + movie.getShowtimes() + " - Genre: " + movie.getGenre() + "\n" + "- Synopsis: " + movie.getSynopsis()+ "\n");
		}
	}

	
	//books a movie
	public Extra bookMovies(User user, Movie movie, String selectedSeat) {
		Extra newBooking = new Extra(user, movie, selectedSeat);
		bookings.add(newBooking);
		return newBooking;
	}
	
	
	
	//saves the booking to file
	public void saveBookingToFIle(String filename, ArrayList<Extra>bookings ) throws IOException {
		FileWriter writer = new FileWriter("bookings.txt", true);
		for(Extra booking : bookings) {
			writer.write(("username: " + booking.getUser() + "| " + " Movie: " + booking.getMovie() + "| " + " selected seat: " + booking.getSelectedSeat() + "\n"));
		}
		writer.close();
		}
	
	
	
	
	//shows the booking history for the user
	public void showBooking() throws IOException {
		
		for(Extra booking : bookings) {
			
				System.out.println("username: " + booking.getUser() + "\nMovie: " + booking.getMovie() + "\nselected seat: " + booking.getSelectedSeat() + "\n");
				
			}
			    
		
	}
	

}
	
	


	
	

