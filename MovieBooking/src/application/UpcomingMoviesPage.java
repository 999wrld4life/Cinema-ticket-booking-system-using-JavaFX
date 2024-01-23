package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UpcomingMoviesPage extends Application {

    private List<String> moviePosters;
    private int currentPosterIndex = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Upcoming Movies");

        // Background Gradient
        Stop[] stops = new Stop[]{new Stop(0, Color.BLACK), new Stop(1, Color.BLUE)};
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        // Main Layout
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setBackground(background);

        // Go Back Button
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #4CAF50, #2196F3); -fx-text-fill: white;");
        goBackButton.setOnAction(event -> {
            new WelcomePage().start(new Stage());
            primaryStage.close();
        });

        // Movie Poster
        ImageView posterImageView = new ImageView();
        posterImageView.setPreserveRatio(true);
        posterImageView.setFitWidth(450);

        // Read movie posters from the file
        moviePosters = readMoviePostersFromFile("movie_posters.txt");

        // Update poster every 3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            if (currentPosterIndex < moviePosters.size() - 1) {
                currentPosterIndex++;
            } else {
                currentPosterIndex = 0;
            }
            updatePoster(posterImageView, moviePosters.get(currentPosterIndex));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Label
        Label titleLabel = new Label("Upcoming Movies");
        titleLabel.setStyle("-fx-font-size: 30; -fx-text-fill: white;");

        // Add Elements to Main Layout
        mainLayout.getChildren().addAll(goBackButton, titleLabel, posterImageView);

        // Scene
        Scene scene = new Scene(mainLayout, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void updatePoster(ImageView imageView, String imagePath) {
        Image posterImage = new Image(getClass().getResource(imagePath).toExternalForm());
        imageView.setImage(posterImage);
    }

    private List<String> readMoviePostersFromFile(String fileName) {
        List<String> posters = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                posters.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posters;
    }
}
