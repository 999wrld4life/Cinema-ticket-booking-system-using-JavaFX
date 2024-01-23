package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		BookingSystem bookingSystem = new BookingSystem();
		boolean validSeatSelected = false;

		int ch = 0;
		boolean isValid = false;
		
		while(!isValid) {
			try {
				System.out.println("Welcome to EMDb");
				System.out.println("Enter 1 to create an account");
				System.out.println("Enter 2 to log into an existing account");
				ch = scanner.nextInt();
				
				if(ch == 1 || ch ==2) {
					isValid = true;
				}else {
					System.out.println("Invalid Input. Please Enter 1 or 2");
				}
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! Please Enter a valid integer");
				scanner.next();
				//to consume the invalid input to avoid infinite loop
			}
		}
		String selectedSeatByUser = null;
		switch(ch) {
		case 1:
		    Scanner scan = new Scanner(System.in);
	    
		        // Register new user
		        System.out.println("Welcome to registration page");
		        
		        // Enter first name
//		        System.out.println("Enter your first name");
//		        String fName = scan.nextLine();
//		        
//
//		        // Enter last name
//		        System.out.println("Enter your last name");
//		        String lName = scan.nextLine();
//		        
//
//		        // Enter password
//		        System.out.println("Enter your password");
//		        String passWord = scan.nextLine();
		     // Enter first name
		        String fName = "";
		        while (true) {
		            System.out.println("Enter your first name");
		            fName = scan.nextLine();

		            if (isValidName(fName)) {
		                break; // Break the loop if the name is valid
		            } 
		        }

		        // Enter last name
		        String lName = "";
		        while (true) {
		            System.out.println("Enter your last name");
		            lName = scan.nextLine();

		            if (isValidName(lName)) {
		                break; // Break the loop if the name is valid
		            } 		        }

		        // Enter password
		        String passWord = "";
		        while (true) {
		            System.out.println("Enter your password");
		            passWord = scan.nextLine();

		            if (isValidPassword(passWord)) {
		                break; // Break the loop if the password is valid
		            }
		        }
		        System.out.println(fName);
		        System.out.println(lName);
		        System.out.println(passWord);
		        
		    

			bookingSystem.registerUser(fName, lName, passWord);
			bookingSystem.saveUserToFile("users.txt", bookingSystem.getUsers());
			bookingSystem.showUsername();
			
			//logs in the user
			User loggedUser = null;
			
				System.out.println("Welcome to login page");
				while(loggedUser==null) {
				
				System.out.println("Enter your username");
				String loginUsername = scan.nextLine();
				
				System.out.println("Enter your password");
				String loginPassword = scan.nextLine();
				
				loggedUser = bookingSystem.loginUser(loginUsername, loginPassword);
				
			
			        
			      //display the movies to the user
					if(loggedUser!=null) {
						System.out.println("Logged in successfully");
						System.out.println("List of movies: ");
						bookingSystem.displayMovies();
					
					
					//book a movie
					Movie selectedMovie = null;	
					while(selectedMovie == null) {
						System.out.println("Enter a movie name");
						String movieName = scan.nextLine();
						selectedMovie =findMovieByTitle(movieName, bookingSystem.getMovies());
						
						if(selectedMovie != null) {
							
							System.out.println("Movie found!");
							if(selectedMovie.getTitle().equalsIgnoreCase("The Other Zoey")) {
								RoomOne roomOne = new RoomOne();
							    System.out.println(roomOne.welcomeUser() + " " + roomOne.getName());
								roomOne.loadRoomOneFromFile("roomOne.txt");
								roomOne.displayRoom(roomOne.getRoomOne());
								System.out.println("Enter your preffered seat");
								selectedSeatByUser = scan.nextLine();
								int selectedSeatRoomOne = Integer.parseInt(selectedSeatByUser);
								roomOne.seatCheckerForRoom(roomOne.getRoomOne(), selectedSeatRoomOne, "roomOne.txt");
							}else if(selectedMovie.getTitle().equalsIgnoreCase("Spartacus")) {
								RoomTwo roomTwo = new RoomTwo();
								System.out.println(roomTwo.welcomeUser() + " " + roomTwo.getName());
								roomTwo.loadRoomTwoFromFile("roomTwo.txt");
								roomTwo.displayRoom(roomTwo.getRoomTwo());
								System.out.println("Enter your preffered seat");
								selectedSeatByUser = scan.nextLine();
								int selectedSeatRoomTwo = Integer.parseInt(selectedSeatByUser);
								roomTwo.seatCheckerForRoom(roomTwo.getRoomTwo(), selectedSeatRoomTwo, "roomTwo.txt");
								
							}else if(selectedMovie.getTitle().equalsIgnoreCase("The Flash")) {
								RoomThree roomThree = new RoomThree();
							    System.out.println(roomThree.welcomeUser() + " " + roomThree.getName());
								roomThree.loadRoomThreeFromFile("roomThree.txt");
								roomThree.displayRoom(roomThree.getRoomThree());
								System.out.println("Enter your preffered seat");
								selectedSeatByUser = scan.nextLine();
								int selectedSeatRoomTwo = Integer.parseInt(selectedSeatByUser);
								roomThree.seatCheckerForRoom(roomThree.getRoomThree(), selectedSeatRoomTwo, "roomThree.txt");
								
							}
							System.out.println("Enter 1 to book movie and 2 to cancel");
							int book = scan.nextInt();
							switch(book) {
							case 1: 
								bookingSystem.bookMovies(loggedUser, selectedMovie, selectedSeatByUser);
								bookingSystem.saveBookingToFIle("bookings.txt", bookingSystem.getBookings());
								System.out.println("Movie booked successfully!");
								break;
							case 2:
								System.out.println("Booking Cancelled!");
								System.exit(0);
								break;
							default: 
								break;
							}
							} else {
							System.out.println("Movie not found");
						}
					}
						
					
					System.out.println("Enter 1 to see booking history");
					int choice = scan.nextInt();
					if(choice==1) {
						bookingSystem.showBooking();
					}else {
						System.out.println("Wrong Input");
					}
					} else {
				        System.out.println("Invalid username or password. Please try again.");
				    }
			    		
				}
			
            break;
		
		
		case 2:
			bookingSystem.displayUsers();
			Scanner scannerr = new Scanner(System.in);
			User loggedUserFromLogin = null;
			
			System.out.println("Welcome to login page");
			while(loggedUserFromLogin==null) {
			
			System.out.println("Enter your username");
			String loginUsernameFromLogin = scannerr.nextLine();
			
			System.out.println("Enter your password");
			String loginPasswordFromLogin = scannerr.nextLine();
			
			loggedUserFromLogin = bookingSystem.loginUser(loginUsernameFromLogin, loginPasswordFromLogin);
			
		
		        
		      //display the movies to the user
				if(loggedUserFromLogin!=null) {
					System.out.println("Logged in successfully");
					System.out.println("List of movies: ");
					bookingSystem.displayMovies();
				
				
				//book a movie
				Movie selectedMovie = null;	
				while(selectedMovie == null) {
					System.out.println("Enter a movie name");
					String movieName = scannerr.nextLine();
					selectedMovie =findMovieByTitle(movieName, bookingSystem.getMovies());
					
					if(selectedMovie != null) {
						
						System.out.println("Movie found!");
						if(selectedMovie.getTitle().equalsIgnoreCase("The Other Zoey")) {
							RoomOne roomOne = new RoomOne();
						    System.out.println(roomOne.welcomeUser() + " " + roomOne.getName());
							roomOne.loadRoomOneFromFile("roomOne.txt");
							roomOne.displayRoom(roomOne.getRoomOne());
							System.out.println("Enter your preffered seat");
							selectedSeatByUser = scannerr.nextLine();
							int selectedSeatRoomOne = Integer.parseInt(selectedSeatByUser);
							roomOne.seatCheckerForRoom(roomOne.getRoomOne(), selectedSeatRoomOne, "roomOne.txt");
						}else if(selectedMovie.getTitle().equalsIgnoreCase("Saltburn")) {
							RoomTwo roomTwo = new RoomTwo();
							System.out.println(roomTwo.welcomeUser() + " " + roomTwo.getName());
							roomTwo.loadRoomTwoFromFile("roomTwo.txt");
							roomTwo.displayRoom(roomTwo.getRoomTwo());
							System.out.println("Enter your preffered seat");
							selectedSeatByUser = scannerr.nextLine();
							int selectedSeatRoomTwo = Integer.parseInt(selectedSeatByUser);
							roomTwo.seatCheckerForRoom(roomTwo.getRoomTwo(), selectedSeatRoomTwo, "roomTwo.txt");
							
						}else if(selectedMovie.getTitle().equalsIgnoreCase("Foe")) {
							RoomThree roomThree = new RoomThree();
						    System.out.println(roomThree.welcomeUser() + " " + roomThree.getName());
							roomThree.loadRoomThreeFromFile("roomThree.txt");
							roomThree.displayRoom(roomThree.getRoomThree());
							while(!validSeatSelected) {
								System.out.println("Enter your preffered seat");
								selectedSeatByUser = scannerr.nextLine();
								int selectedSeatRoomThree = Integer.parseInt(selectedSeatByUser);
								validSeatSelected = roomThree.seatCheckerForRoom(roomThree.getRoomThree(), selectedSeatRoomThree, "roomThree.txt");
							}
							
							
						}
						System.out.println("Enter 1 to book movie and 2 to cancel");
						int book = scannerr.nextInt();
						switch(book) {
						case 1: 
							bookingSystem.bookMovies(loggedUserFromLogin, selectedMovie, selectedSeatByUser);
							bookingSystem.saveBookingToFIle("bookings.txt", bookingSystem.getBookings());
							System.out.println("Movie booked successfully!");
							break;
						case 2:
							System.out.println("Booking Cancelled!");
							System.exit(0);
							break;
						default: 
							break;
						}
						} else {
						System.out.println("Movie not found");
					}
				}
					
				
				System.out.println("Enter 1 to see booking history");
				int choice = scannerr.nextInt();
				if(choice==1) {
					bookingSystem.showBooking();
				}else {
					System.out.println("Wrong Input");
				}
				} else {
			        System.out.println("Invalid username or password. Please try again.");
			    }
		    		
			}
		
        break;

		default: 
			break;
		}
	}
		
		
	private static Movie findMovieByTitle(String title, ArrayList<Movie> movies) {
		for(Movie movie : movies) {
			if (movie.getTitle().equalsIgnoreCase(title)){
				return movie;
			}
		}
		return null;
	}
	private static boolean isValidName(String name) {
	    if (name == null || name.trim().isEmpty()) {
	        System.out.println("No name entered. Please enter a name.");
	        return false;
	    } else if (name.length() == 1) {
	        System.out.println("Name should be greater than one character. Please enter a valid name.");
	        return false;
	    } else if (!name.matches("^[A-Za-z ]+$")) {
	        System.out.println("Invalid characters in your name. Please enter a valid name.");
	        return false;
	    } else {
	        return true;
	    }
	}
	private static boolean isValidPassword(String password) {
	    if (password == null || password.length() < 8) {
	        System.out.println("Password should be at least 8 characters. Please enter a valid password.");
	        return false;
	    } else if (!password.matches(".*\\d.*") || !password.matches(".*[A-Z].*") || !password.matches(".*[@#$%^&+=].*")) {
	        System.out.println("Invalid password format. Please include at least one uppercase letter, one digit, and one special character.");
	        return false;
	    } else {
	        return true;
	    }
	}


}



