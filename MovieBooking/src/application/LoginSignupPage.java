package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginSignupPage extends Application {
    
    private Stage stage;
    private VBox loginPanel, signupPanel;
    private TextField usernameField, firstNameField, lastNameField;
    private PasswordField passwordField;
    private Button loginButton, signupButton;
    private Label signUpLabel, signInLabel;
    BookingSystem bookingSystem = new BookingSystem();

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Login/Signup");
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        try {
			bookingSystem.loadUserFromFile("users.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			bookingSystem.displayUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        loginPanel = new VBox();
        signupPanel = new VBox();

        initializeLoginPanel();
        initializeSignupPanel();

        VBox root = new VBox();
        root.getChildren().add(loginPanel);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    private void initializeLoginPanel() {
        loginPanel.setAlignment(javafx.geometry.Pos.CENTER);
        loginPanel.setSpacing(10);

        Label welcomeLabel = new Label("Welcome to My App");
        welcomeLabel.setTextFill(Color.WHITE);

        HBox usernameBox = createLabeledTextField("Username:", usernameField = new TextField());
        HBox passwordBox = createLabeledTextField("Password:", passwordField = new PasswordField());

        loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        signUpLabel = new Label("Don't have an account? Register now");
        signUpLabel.setTextFill(Color.BLUE);
        signUpLabel.setCursor(javafx.scene.Cursor.HAND);
        

        signUpLabel.setOnMouseClicked(event -> changePanel(signupPanel));
        loginButton.setOnAction(event -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getCharacters().toString();

            // Perform any logic you need with the entered username and password
           
            System.out.println("Username: " + enteredUsername);
            System.out.println("Password: " + enteredPassword);
            User loggedInUser = bookingSystem.loginUser(enteredUsername, enteredPassword);
            if (loggedInUser != null) {
                // Login successful, you can open a new scene or perform other actions
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Login successful!");
                alert.showAndWait();

                // Optionally, you can pass the logged-in user to another method or scene
                // handleLoggedInUser(loggedInUser);
            } else {
                // Login failed, show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid username or password. Please try again.");
                alert.showAndWait();
            }
            // Example: Validate the credentials
            

            // Clear the fields if needed
            usernameField.clear();
            passwordField.clear();
        });
        

        loginPanel.getChildren().addAll(welcomeLabel, usernameBox, passwordBox, loginButton, signUpLabel);
    }

    private void initializeSignupPanel() {
        signupPanel.setAlignment(javafx.geometry.Pos.CENTER);
        signupPanel.setSpacing(10);

        Label signupLabel = new Label("Sign Up");
        signupLabel.setTextFill(Color.WHITE);

        HBox firstNameBox = createLabeledTextField("First Name:", firstNameField = new TextField());
        HBox lastNameBox = createLabeledTextField("Last Name:", lastNameField = new TextField());
        HBox passwordBox = createLabeledTextField("Password:", passwordField = new PasswordField());

        signupButton = new Button("Sign Up");
        signupButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        signInLabel = new Label("Already have an account? Sign in");
        signInLabel.setTextFill(Color.BLUE);
        signInLabel.setCursor(javafx.scene.Cursor.HAND);

        signInLabel.setOnMouseClicked(event -> changePanel(loginPanel));
        signupButton.setOnAction(event -> {
            String enteredFname = firstNameField.getText();
            String enteredLname = lastNameField.getText();
            String enteredPassword = passwordField.getText();

            // Perform any logic you need with the entered username and password
            System.out.println("Fname: " + enteredFname);
            System.out.println("Lname: " + enteredLname);
            System.out.println("Password: " + enteredPassword);

            // Example: Validate the credentials
            

            // Clear the fields if needed
            usernameField.clear();
            passwordField.clear();
        });

        signupPanel.getChildren().addAll(signupLabel, firstNameBox, lastNameBox, passwordBox, signupButton, signInLabel);
    }

    private void changePanel(VBox panel) {
        VBox root = (VBox) stage.getScene().getRoot();
        root.getChildren().clear();
        root.getChildren().add(panel);
    }

    private HBox createLabeledTextField(String labelText, TextField textField) {
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hbox.setSpacing(10);

        Label label = new Label(labelText);
        label.setTextFill(Color.WHITE);

        hbox.getChildren().addAll(label, textField);
        return hbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
