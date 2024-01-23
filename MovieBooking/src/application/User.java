package application;

import java.util.Random;

public class User {
	private String firstName;
	private String lastName;
    private String username;
    private String password;
   
   
    //general constructor - we don't initialize the username here yet!
    public User(String firstName, String lastName, String password) {
	   
    	this.setFirstName(firstName);
    	this.setLastName(lastName);
    	this.setPassword(password);
	
        //initializes the username 
        generateUsername();
      }

   
   /*constructor overloading for initializing the username from the firstName 
     and lastName variables(which have been already initialized)
   */
   
   public User(String firstName, String lastName, String password, String username) {
	   this.setPassword(password);
	   this.setFirstName(firstName);
	   this.setLastName(lastName);
	   this.setUsername(username);
   }
   public User() {
	   
   }
   
   /*to prevent default toString() when printing out the user (e.g., com.movie.User@41906a77)
   and print out meaningful string representation (I'll explain more in person)
   */
   @Override
   public String toString() {
	   return username;
   }
   
   
   //generates a random username from firstName, lastName, alphabets and numbers
   public void generateUsername() {
		 Random random = new Random();
		 int randomNumber = 100 + random.nextInt(900);
		 char randomChar = (char) ('a' + random.nextInt(26));
		 String randomStr = firstName.substring(0, 3);
		 String randomStrTwo = lastName.substring(0, 2);
		 username = randomStr + randomStrTwo + randomChar + randomNumber;
		 
	}
   
   
   //getters and setters for the attributes

public String getUsername() {
	return username;
}

public String getPassword() {
	return password;
}

public void setUsername(String username) {
	this.username = username;
}

public void setPassword(String password) {
	this.password = password;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}


}

