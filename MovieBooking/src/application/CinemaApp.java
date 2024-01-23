package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CinemaApp extends Application {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin");

        // Load the background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("background.jpg"));

        // Login Page
        VBox loginLayout = createLoginLayout(primaryStage, backgroundImage);
        Scene loginScene = new Scene(loginLayout, 400, 300);

        // Admin Dashboard
        BorderPane adminDashboardLayout = createAdminDashboardLayout(primaryStage, backgroundImage);
        Scene adminDashboardScene = new Scene(adminDashboardLayout);

        primaryStage.setScene(loginScene);
        primaryStage.show();

        // Event handling for login button
        Button loginButton = (Button) loginLayout.lookup("#loginButton");
        TextField usernameField = (TextField) loginLayout.lookup("#usernameField");
        PasswordField passwordField = (PasswordField) loginLayout.lookup("#passwordField");

        loginButton.setOnAction(event -> {
            if (authenticateUser(usernameField.getText(), passwordField.getText())) {
                primaryStage.setScene(adminDashboardScene);
                primaryStage.setMaximized(true); // Set the window to full screen
            } else {
                showAlert("Incorrect credentials. Please try again.");
            }
        });
    }

    private VBox createLoginLayout(Stage primaryStage, Image backgroundImage) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setBackground(new Background(new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));

        Text title = new Text("Admin Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setFill(Color.WHITE); // Change text color to white

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setId("usernameField");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setId("passwordField");

        Button loginButton = new Button("Login");
        loginButton.setId("loginButton");
        loginButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #4CAF50, #2196F3); -fx-text-fill: white;"); // Style login button

        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(event -> {
            new WelcomePage().start(new Stage());
            System.out.println("Button clicked: Go Back");
            primaryStage.close(); // Close the current stage (login page)
        });
        goBackButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #4CAF50, #2196F3); -fx-text-fill: white;"); // Style go back button

        layout.getChildren().addAll(title, usernameField, passwordField, loginButton, goBackButton);
        return layout;
    }

    private BorderPane createAdminDashboardLayout(Stage primaryStage, Image backgroundImage) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setBackground(new Background(new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        Text headerText = new Text("EMDB Admin Dashboard");
        headerText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerText.setFill(Color.GHOSTWHITE);
        header.getChildren().add(headerText);
        layout.setTop(header);

        // Sidebar
        VBox sidebar = createSidebar(primaryStage);
        layout.setLeft(sidebar);

        // Main Content
        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);

        Text welcomeText = new Text("Welcome Back Admin! ");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        welcomeText.setFill(Color.WHITE); // Change text color to white

        mainContent.getChildren().addAll(welcomeText);
        layout.setCenter(mainContent);

        return layout;
    }

    private VBox createSidebar(Stage primaryStage) {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Button manageMoviesButton = new Button("Manage Movies");
        Button bookingStatisticsButton = new Button("Booking Statistics");
        Button recentBookingHistory = new Button("Recent Bookings History ");
        Button logoutButton = new Button("Logout");

        VBox additionalButtons = new VBox(10);
        additionalButtons.setPadding(new Insets(10));
        Button roomOneHistory = new Button("ROOM ONE");
        Button roomTwoHistory = new Button("ROOM TWO");
        Button roomThreeHistory = new Button("ROOM THREE");

        additionalButtons.getChildren().addAll(roomOneHistory, roomTwoHistory, roomThreeHistory);
        additionalButtons.setVisible(false);

        recentBookingHistory.setOnAction(event -> {
            additionalButtons.setVisible(!additionalButtons.isVisible());
        });

        // Event handling for sidebar buttons
        manageMoviesButton.setOnAction(event -> {
            new ManageMoviesPage().start(new Stage());
        });
        bookingStatisticsButton.setOnAction(event -> {
            new BookingStatisticsPage().start(new Stage());
        });
        roomOneHistory.setOnAction(event -> {
            new RoomOneReservationHistory().start(new Stage());
        });
        roomTwoHistory.setOnAction(event -> {
            new RoomTwoReservationHistory().start(new Stage());
        });
        roomThreeHistory.setOnAction(event -> {
            new RoomThreeReservationHistory().start(new Stage());
        });
        logoutButton.setOnAction(event -> {
            // Handle logout logic here
            new WelcomePage().start(new Stage());
            primaryStage.close();
        });

        sidebar.getChildren().addAll(manageMoviesButton, bookingStatisticsButton, recentBookingHistory, additionalButtons, logoutButton);

        return sidebar;
    }

    private boolean authenticateUser(String enteredUsername, String enteredPassword) {
        return ADMIN_USERNAME.equals(enteredUsername) && ADMIN_PASSWORD.equals(enteredPassword);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showFeatureNotImplementedAlert(String feature) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The " + feature + " feature is not implemented yet.");
        alert.showAndWait();
    }
}
