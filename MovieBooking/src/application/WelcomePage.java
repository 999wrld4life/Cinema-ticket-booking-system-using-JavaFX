package application;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomePage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to EMDB Cinema");
        primaryStage.setMaximized(true);

        // Create a welcome text with a fade-in animation
        Text welcomeText = new Text("Welcome to EMDB Cinema");
        welcomeText.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        welcomeText.setFill(Color.WHITE);
        welcomeText.setOpacity(0); // Initially set opacity to 0

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), welcomeText);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(Timeline.INDEFINITE);
        fadeIn.play();

        // Create buttons with a scale-in animation
        Button goToMovieListButton = createAnimatedButton("Available Movies");
        Button goToAdminPanelButton = createAnimatedButton("Go to Admin's Panel");
        Button upcomingMoviesButton = createAnimatedButton("Upcoming Movies");

        // Set button actions
        goToMovieListButton.setOnAction(e -> {
            System.out.println("Button clicked: Go to Movie List");
            new MovieListingPage().start(new Stage());
        });

        goToAdminPanelButton.setOnAction(e -> {
            System.out.println("Go to Admin's Panel");
            new CinemaApp().start(new Stage());
            primaryStage.close();
        });

        upcomingMoviesButton.setOnAction(e -> {
            new UpcomingMoviesPage().start(new Stage());
            primaryStage.close();
            System.out.println("Upcoming Movies");
        });

        // Load the background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("background.jpg"));

        // Set up the background image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        // Create a layout with a background gradient and image
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(80));
        root.setBackground(new Background(background));

        // Add components to the layout
        root.getChildren().addAll(welcomeText, goToMovieListButton, goToAdminPanelButton, upcomingMoviesButton);

        // Set up the scene
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    // Create animated button with a scale-in animation
    private Button createAnimatedButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        button.setScaleX(0); // 
        button.setScaleY(0);

        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(1), button);
        scaleIn.setFromX(0);
        scaleIn.setToX(1.2);
        scaleIn.setFromY(0);
        scaleIn.setToY(1.2);
        scaleIn.play();

        return button;
    }
}
